package kariminf.langpi.eval.ats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import kariminf.ktoolja.file.FileManager;
import kariminf.langpi.basic.ISO639_1;
import kariminf.langpi.basic.Segmenter;
import kariminf.langpi.basic.arabic.ArSegmenter;
import kariminf.langpi.basic.basque.EuSegmenter;
import kariminf.langpi.basic.bulgarian.BgSegmenter;
import kariminf.langpi.basic.catalan.CaSegmenter;
import kariminf.langpi.basic.chinese.ZhSegmenter;
import kariminf.langpi.basic.czech.CsSegmenter;
import kariminf.langpi.basic.def.DefSegmenter;
import kariminf.langpi.basic.dutch.NlSegmenter;
import kariminf.langpi.basic.english.EnSegmenter;
import kariminf.langpi.basic.finnish.FiSegmenter;
import kariminf.langpi.basic.french.FrSegmenter;
import kariminf.langpi.basic.german.DeSegmenter;
import kariminf.langpi.basic.greek.ElSegmenter;
import kariminf.langpi.basic.hebrew.HeSegmenter;
import kariminf.langpi.basic.hindi.HiSegmenter;
import kariminf.langpi.basic.hungarian.HuSegmenter;
import kariminf.langpi.basic.indonesian.IdSegmenter;
import kariminf.langpi.basic.italian.ItSegmenter;
import kariminf.langpi.basic.japanese.JaSegmenter;
import kariminf.langpi.basic.norwegian.NoSegmenter;
import kariminf.langpi.basic.nynorsk.NnSegmenter;
import kariminf.langpi.basic.persian.FaSegmenter;
import kariminf.langpi.basic.portuguese.PtSegmenter;
import kariminf.langpi.basic.romanian.RoSegmenter;
import kariminf.langpi.basic.russian.RuSegmenter;
import kariminf.langpi.basic.spanish.EsSegmenter;
import kariminf.langpi.basic.swedish.SvSegmenter;
import kariminf.langpi.basic.thai.ThSegmenter;
import kariminf.langpi.basic.turkish.TrSegmenter;
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
		{
				"af",
				"bg",
				"ca",
				"cs",
				"de",
				"el",
				"eo",
				"es",
				"eu",
				"fa",
				"fi",
				"fr",
				"he",
				"hr",
				"hu",
				"id",
				//"it",
				"ja",
				"ka",
				"ko",
				"ms",
				"nl",
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
				"zh",
		};
	
	
	
	private static void init(){
		results.clear();
		currentRouges.clear();
		gramTypes.clear();
		currentEvalID = "";
	}


	private static void readXMLFile(String path, String lang){

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
					addModel(modelPath, lang);
				}
				
				NodeList peers = ( (Element) eval.getElementsByTagName("PEERS").item(0)).
						getElementsByTagName("P");

				
				for (int peerNum = 0; peerNum < peers.getLength(); peerNum++) {
					Element peer = (Element) peers.item(peerNum);

					String peerID = peer.getAttribute("ID");
					String peerPath = peerRoot + "/" + peer.getTextContent().
							replaceAll("[\n\r]", "");

					System.out.printf("Peer %s\tPath: %s\n", peerID, peerPath);
					
					evaluatePeer(peerID, peerPath, lang);
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
	
	private static void addModel(String path, String lang){
		List<List<String>> sentences = processFile(path, lang);
		
		currentRouges.get(GramType.UNI).newModel();
		currentRouges.get(GramType.BI).newModel();
		
		for (List<String> sentence: sentences){
			currentRouges.get(GramType.UNI).addModelSentence(sentence);
			currentRouges.get(GramType.BI).addModelSentence(sentence);
		}
	}
	
	private static void evaluatePeer(String id, String path, String lang){
		List<List<String>> sentences = processFile(path, lang);
		
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
	
	private static List<List<String>> processFile(String filePath, String lang){
		List<List<String>> content = new ArrayList<>();
		
		Segmenter s = getSegmenter(lang);
		
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
	
	public static Segmenter getSegmenter(String lang){
		lang = lang.toUpperCase();
		
		ISO639_1 iso;
		
		try{
			iso = ISO639_1.valueOf(lang);
		} catch (IllegalArgumentException e){
			iso = ISO639_1.EN;
		}
		
		switch (iso){
		case AR:
			return new ArSegmenter();

		case BG:
			return new BgSegmenter();

		case CA:
			return new CaSegmenter();

		case CS:
			return new CsSegmenter();

		case DE:
			return new DeSegmenter();

		case EL:
			return new ElSegmenter();

		case EN:
			return new EnSegmenter();

		case ES:
			return new EsSegmenter();

		case EU:
			return new EuSegmenter();

		case FA:
			return new FaSegmenter();
			
		case FI:
			return new FiSegmenter();

		case FR:
			return new FrSegmenter();

		case HE:
			return new HeSegmenter();
	
		case HI:
			return new HiSegmenter();

		case HU:
			return new HuSegmenter();

		case ID:
			return new IdSegmenter();

		case IT:
			return new ItSegmenter();

		case JA:
			return new JaSegmenter();

		case NL:
			return new NlSegmenter();

		case NN:
			return new NnSegmenter();

		case NO:
			return new NoSegmenter();

		case PT:
			return new PtSegmenter();

		case RO:
			return new RoSegmenter();

		case RU:
			return new RuSegmenter();

		case SV:
			return new SvSegmenter();

		case TH:
			return new ThSegmenter();

		case TR:
			return new TrSegmenter();

		case ZH:
			return new ZhSegmenter();

		default:
			return new DefSegmenter();

		}

	}

	public static void main(String[] args) {
		
		for (String lang: langs){
			init();
			String path = "/home/kariminf/Data/ATS/Mss15Train/tests/" + lang + "2017";
			readXMLFile(path + ".xml", lang);
			writeResultsCSV(path + ".csv");
		}

	}

}
