package kariminf.langpi.eval.ats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import kariminf.ktoolja.file.FileManager;
import kariminf.langpi.basic.Segmenter;
import kariminf.langpi.basic.arabic.ArSegmenter;
import kariminf.langpi.basic.english.EnSegmenter;
import kariminf.langpi.eval.ats.KRouge.GramType;

public final class XKRouge {

	// PeerName -> EvalName --> [Recall, Precision, F1score, Recall, Precision, ...]
	private static HashMap<String, HashMap<String, List<Double>>> results = 
			new HashMap<>();
	
	private static HashMap<GramType, KRouge> currentRouges = 
			new HashMap<>();
	
	private static List<GramType> gramTypes = new ArrayList<>();
	
	private static String currentEvalID = "";
	
	private static final String [] langs = 
		{"ka", 
		"ko", 
				"ml", 
				"ms", 
				"nl", 
				"nn", 
				"no", 
				"pl", 
				"pt", 
				"ro", 
				"ru", 
				"sh", 
				"sk", 
				"sl", 
				"sr", 
				"sv", 
				"th", 
				"tr", 
				"vi", 
		"zh"};


	private static void readXMLFile(String path){

		try {

			Document doc = DocumentBuilderFactory.newInstance().
					newDocumentBuilder().
					parse(new File(path));

			doc.getDocumentElement().normalize();

			NodeList evals = doc.getElementsByTagName("EVAL");

			for (int evalNum = 0; evalNum < evals.getLength(); evalNum++) {

				Element eval = (Element) evals.item(evalNum);

				String evalID = eval.getAttribute("ID");
				String peerRoot = 
						eval.getElementsByTagName("PEER-ROOT").
						item(0).getTextContent().
						replaceAll("[\n\r]", "");
				String modelRoot = 
						eval.getElementsByTagName("MODEL-ROOT").
						item(0).getTextContent().
						replaceAll("[\n\r]", "");
				String type = 
						( (Element) eval.getElementsByTagName("INPUT-FORMAT").
								item(0)).getAttribute("TYPE");

				System.out.printf("Eval %s, type: %s \n======\n", evalID, type);

				
				NodeList models = ( (Element) eval.getElementsByTagName("MODELS").item(0)).
						getElementsByTagName("M");
				
				beginEval(evalID);

				for (int modelNum = 0; modelNum < models.getLength(); modelNum++) {
					Element model = (Element) models.item(modelNum);

					String modelID = model.getAttribute("ID");
					String modelPath = modelRoot + "/" + model.getTextContent().
							replaceAll("[\n\r]", "");

					System.out.printf("Model %s\tPath: %s\n", modelID, modelPath);
					addModel(modelPath);
				}
				
				NodeList peers = ( (Element) eval.getElementsByTagName("PEERS").item(0)).
						getElementsByTagName("P");

				
				for (int peerNum = 0; peerNum < peers.getLength(); peerNum++) {
					Element peer = (Element) peers.item(peerNum);

					String peerID = peer.getAttribute("ID");
					String peerPath = peerRoot + "/" + peer.getTextContent().
							replaceAll("[\n\r]", "");

					System.out.printf("Peer %s\tPath: %s\n", peerID, peerPath);
					
					evaluatePeer(peerID, peerPath);
				}
				
				endEval();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	
	private static void beginEval(String evalID){
		currentRouges.put(GramType.UNI, new KRouge(GramType.UNI));
		currentRouges.put(GramType.BI, new KRouge(GramType.BI));
		currentEvalID = evalID;
	}
	
	private static void endEval(){
		currentRouges.clear();
	}
	
	private static void addModel(String path){
		List<List<String>> sentences = processFile(path);
		
		currentRouges.get(GramType.UNI).newModel();
		currentRouges.get(GramType.BI).newModel();
		
		for (List<String> sentence: sentences){
			currentRouges.get(GramType.UNI).addModelSentence(sentence);
			currentRouges.get(GramType.BI).addModelSentence(sentence);
		}
	}
	
	private static void evaluatePeer(String id, String path){
		List<List<String>> sentences = processFile(path);
		
		KRouge uniRouge = currentRouges.get(GramType.UNI);
		KRouge biRouge = currentRouges.get(GramType.BI);
		
		uniRouge.resetPeer();
		biRouge.resetPeer();
		
		for (List<String> sentence: sentences){
			uniRouge.addPeerSentence(sentence);
			biRouge.addPeerSentence(sentence);
		}
		
		uniRouge.calculate();
		biRouge.calculate();
		
		HashMap<String, List<Double>> evalsRes = 
				(results.containsKey(id))?
						results.get(id): 
							new HashMap<String, List<Double>>();
		results.put(id, evalsRes);
		
		List<Double> rp = new ArrayList<>();
		evalsRes.put(currentEvalID, rp);
		
		rp.add(uniRouge.getRecall());
		rp.add(uniRouge.getPrecision());
		rp.add(uniRouge.getFScore(0.5));
		rp.add(biRouge.getRecall());
		rp.add(biRouge.getPrecision());
		rp.add(biRouge.getFScore(0.5));
		
	}
	
	private static List<List<String>> processFile(String filePath){
		List<List<String>> content = new ArrayList<>();
		
		Segmenter s = new ArSegmenter();
		
		try {

			BufferedReader input = new BufferedReader(new FileReader(filePath));
			
			String line;
			
			while ((line = input.readLine()) != null){
				List<String> words = s.segmentWords(line);
				if(words != null && words.size() > 0)
					content.add(words);
			}

			input.close();

			return content;

		} catch (IOException e) {
			//e.printStackTrace();
			//System.exit(1);
			System.err.println("Input/Output problem");
			return content;
		}
	}
	
	private static void writeResults(String path){
		//TODO complete writing results
		
		String s = "";
		
		for (String peerID: results.keySet()){
			
			HashMap<String, List<Double>> peerRes = results.get(peerID);
			
			s += ".............................................\n";
			for(String evalID: peerRes.keySet()){
				List<Double> rpf = peerRes.get(evalID);
				s += peerID + " ROUGE-1 Eval " + evalID + "." + peerID + " ";
				s += "R:" + rpf.get(0);
			}
			
			
			
		}
	}
	
	//Temporary
	private static void writeResultsCSV(String path){
		String s = "Peer,Rouge-1,,,Rouge2,,\n";
		s+= ",R,P,F1,R,P,F1\n";
		
		Set<String> ordRes = new TreeSet<String>();
		ordRes.addAll(results.keySet());
		for (String peerID: ordRes){
			
			HashMap<String, List<Double>> peerRes = results.get(peerID);
			
			double[] avg = new double[] {
					0.0, 0.0, 0.0,
					0.0, 0.0, 0.0
			};
			
			for(String evalID: peerRes.keySet()){
				List<Double> rpf = peerRes.get(evalID);
				
				for (int i =0; i< 6; i++)
					avg[i] += rpf.get(i);
			}
			
			for (int i =0; i< 6; i++)
				avg[i] = avg[i]/(double)peerRes.size();
			
			s += peerID + "," + String.format("%.5f", avg[0]) + ",";
			s += String.format("%.5f", avg[1]) + "," + String.format("%.5f", avg[2]);
			s += "," + String.format("%.5f", avg[3]) + ",";
			s += String.format("%.5f", avg[4]) + "," + String.format("%.5f", avg[5]) + "\n";
			
		}
		
		try {
			FileManager.saveFile(path, s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		for (String lang: langs){
			String path = "/home/kariminf/Data/ATS/Mss15Train/tests/" + lang + "2017";
			readXMLFile(path + ".xml");
			writeResultsCSV(path + ".csv");
		}

	}

}
