package kariminf.langpi.eval.ats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KRouge {

	public static enum GramType {
		UNI,
		BI,
		SU4

	}

	private Class<? extends GramModel> gramModelClass;

	private List<GramModel> models = new ArrayList<>();
	private GramModel currentModel = null;
	private GramModel peer;

	//private final double alpha = 0.5;


	private double recall = 0.0;
	private double precision = 0.0;
	//private double _F = 0.0;

	public KRouge (GramType gramType){
		switch (gramType) {
		case BI:
			gramModelClass = BiGramModel.class;
			break;
		default:
			gramModelClass = UniGramModel.class;
			break;
		}

		//newModel();
		resetPeer();
	}



	public void addPeerSentence(List<String> sentence){
		peer.addSentence(sentence);
	}

	public void resetPeer(){
		try {
			peer = gramModelClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addModelSentence(List<String> sentence){
		
		if (currentModel == null) newModel();

		currentModel.addSentence(sentence);
	}


	public void newModel(){

		try {
			currentModel = gramModelClass.newInstance();
			models.add(currentModel);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @return
	 */
	public KRouge calculate(){
		
		

		int totalHits = 0;
		int totalMgrams = 0;
		int totalPgrams = 0;

		for (int i=0; i< models.size(); i++){

			GramModel model =  models.get(i);
			Set<String> commons = new HashSet<String>(model.getTerms());
			commons.retainAll(peer.getTerms());

			int hits = 0;
			for (String common: commons){
				int commonValue = 
						Math.min(peer.getFrequency(common), 
								model.getFrequency(common));
				hits += commonValue;

			}

			totalHits += hits;
			totalMgrams += model.getGramsAmount();
			totalPgrams += peer.getGramsAmount();
		}

		if (totalMgrams == 0) recall = 0.0;
		else recall = (double) totalHits / (double) totalMgrams;
		
		if (totalPgrams == 0) precision = 0.0;
		else precision = (double) totalHits / (double) totalPgrams;
		
		//System.out.println("model: " + totalMgrams);
		//System.out.println("peer: " + totalPgrams);
		//System.out.println("hits: " + totalHits);

		return this;
	}

	public double getRecall(){
		return recall;
	}

	public double getPrecision(){
		return precision;
	}

	public double getFScore(double alpha){
		if (alpha < 0) alpha = 0.0;
		else if (alpha > 1.0) alpha = 1.0;
		
		double fraq = (1-alpha) * precision + alpha * recall; 
		
		if (fraq == 0) return 0.0;
		
		return recall * precision / fraq;
	}


}
