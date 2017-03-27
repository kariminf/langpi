package kariminf.langpi.eval.ats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KROUGE {
	
	private HashMap<String, Integer> peer = new HashMap<String, Integer>();
	private List<HashMap<String, Integer>> models = new ArrayList<HashMap<String, Integer>>();
	private HashMap<String, Integer> currentModel = new HashMap<String, Integer>();
	//private int peerGrams = 0;
	private int currMGrams = 0;
	private List<Integer> modelsGrams = new ArrayList<Integer>();
	
	//private final double alpha = 0.5;
	
	private double _R = 0.0;
	//private double _P = 0.0;
	//private double _F = 0.0;
	
	public void addPeerSentence(List<String> sentence){
		int grams = addBigrams(peer, sentence);
		//peerGrams += grams;
	}
	
	public void resetPeers(){
		peer = new HashMap<String, Integer>();
		_R = 0.0;
		//_P = 0.0;
		//_F = 0.0;
	}
	
	public void addModelSentence(List<String> sentence){
		
		int grams = addBigrams(currentModel, sentence);
		currMGrams += grams;
	}
	
	public void newModel(){
		if (currentModel.size() > 0){
			models.add(currentModel);
			currentModel = new HashMap<String, Integer>();
			
			modelsGrams.add(currMGrams);
			currMGrams = 0;
		}	
	}
	
	
	public void calculateROUGE_2 (){
		newModel(); //in order to add the last model to moddels
		
		if (models.size()< 1 || peer.size() < 1) return;
		
		int totalHits = 0;
	    int totalMgrams = 0;
	    
		for (int i=0; i< models.size(); i++){
			
			HashMap<String, Integer> model =  models.get(i);
			Set<String> commons = new HashSet<String>(model.keySet());
			commons.retainAll(peer.keySet());
			
			int hits = 0;
			for (String common: commons){
				int commonValue = (peer.get(common) <= model.get(common))?
						peer.get(common) : model.get(common);
				hits += commonValue;
						
			}
			
			totalHits += hits;
			totalMgrams += modelsGrams.get(i);
		}
		
		_R = (double) totalHits / (double) totalMgrams;
		
	}
	
	
	private int addBigrams(HashMap<String, Integer> summary, List<String> sentence){
		
		int grams = 0;
		
		for (int i=1; i < sentence.size(); i++){
			grams++;
			String bigram = sentence.get(i-1) + "$" + sentence.get(i);
			int tf = summary.containsKey(bigram)?
					summary.get(bigram) + 1:1;
			
			summary.put(bigram, tf);
		}
		
		return grams;
	}
	
	public double getR(){
		return _R;
	}
	

}
