package kariminf.langpi.eval.ats;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import kariminf.langpi.basic.def.DefSegmenter;
import kariminf.langpi.basic.def.DefStemmer;
import kariminf.langpi.basic.BasicInfo;
import kariminf.langpi.basic.Segmenter;
import kariminf.langpi.basic.Stemmer;
import kariminf.ktoolja.math.Calculus;
import kariminf.ktoolja.file.FileManager;
import kariminf.ktoolja.plugins.JarLoader;

public class MmsTrainingR {

	private static final String peerFolder = "/home/kariminf/Data/ATS/multilingMms2015Training/training2015/";

	private static final String summaryFolder = "/home/kariminf/Data/ATS/multilingMms2015Training/";

	private static final String [][] langs = 
		{
		{"arabic","ar"},
		{"chinese","zh"},
		{"czech","cs"},
		{"english","en"},
		{"french","fr"},
		{"greek","el"},
		{"hebrew","he"},
		{"hindi","hi"},
		{"romanian","ro"},
		{"spanish","es"}
		};

	private static String[] features = { "TFU"
		, "TFB", "Pos", "RLeng", "PLeng", 
		};

	// add static thresholds let say 31 from 0.00 to 0.30
	/*private static String[] th_name = { "mean", "median", "variance", "Hmode",
		"Lmode", "s_Dn", "D_sn", "D_s", "00", "01", "02", "03", "04", "05",
		"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
		"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
		"28", "29", "30" };*/
	
	private static String[] th_name = { "mean", "median"
	, "variance", "Hmode",
	"Lmode", "s_Dn", "D_sn", "D_s"
	};

	public static HashMap<String, List<String>> getFiles(String lng){
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		
		File folder = new File(summaryFolder + lng + "/models/");
		if (! folder.isDirectory()){
			System.out.println(folder.getAbsolutePath() + ": is not a folder");
			return result;
		}
		
		File[] files = folder.listFiles();
		
		for (File file: files){
			String filename = file.getName();
			String topic = filename.substring(0, 4);
			if (! result.containsKey(topic)){
				List<String> fileList = new ArrayList<String>();
				result.put(topic, fileList);
			}
			result.get(topic).add(file.getAbsolutePath());
		}
		
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> featuresComb = new ArrayList<String>();
		{
			for (int combNbr = 1; combNbr <= features.length; combNbr++){
				List<List<Integer>> combs = Calculus.getCombinations(features.length, combNbr);
				
				for (List<Integer> oneComb : combs){
					String featused = "";
					for (int index : oneComb)
						featused += features[index] + "-";
					featused = featused.substring(0, featused.length() - 1);
					
					if (featused.matches(".*(TFU|TFB).*")){
						featuresComb.add(featused);
					}
					
				}
				
			}
		}
		
			

		for (String[] lang : langs) {// langs

			//*************************************
			JarLoader jarLoader = 
					new JarLoader("preProcess/", "aak/as/preProcess", BasicInfo.version);

			BasicInfo info = jarLoader.getInfoService(lang[1], BasicInfo.class);

			Segmenter segmenter = jarLoader.getClassService(info, Segmenter.class);
			if (segmenter == null){
				System.out.println(lang[0] + ": No Segmenter, using default");
				segmenter = new DefSegmenter();
			}

			Stemmer stemmer = jarLoader.getClassService(info, Stemmer.class);
			if (stemmer == null){
				System.out.println(lang[0] + ": No Stemmer, using default");
				stemmer = new DefStemmer();
			}
			//*************************************

			//-------------------------------------
			HashMap<String,HashMap<String, HashMap<String, Double>>> results = 
					new HashMap<String,HashMap<String, HashMap<String, Double>>>();
			// file.th.feat.r
			//-------------------------------------
			HashMap<String, HashMap<String, Double>> sums = 
					new HashMap<String, HashMap<String, Double>>();
			//th.feat.r
			//-------------------------------------

			
			
			HashMap<String, List<String>> topics = 
					getFiles(lang[0]);
			
			if(topics.size() <1) continue;

			int count=0;
			for (String topic : topics.keySet()) {
				
				count++;

				KRouge krouge = new KRouge();

				List<String> sumFiles = topics.get(topic);
				
				for(String file: sumFiles){
					String text = FileManager.readFile(new File(file));
					text = delLines(text);
					List<String> sents = segmenter.splitToSentences(text);
					for (String sent: sents){
						List<String> words = segmenter.segmentWords(sent);
						words = stemmer.stemListWords(words);
						krouge.addModelSentence(words);
					}

				}
				
				
				HashMap<String, HashMap<String, Double>> fileinfo = 
						new HashMap<String, HashMap<String, Double>>();


				for (int th = 0; th < th_name.length; th++) {// threshold 40
					
					HashMap<String, Double> thinfo = new HashMap<String, Double>();

					for (String featused: featuresComb) {// features combinations

						{
							String peerUrl = peerFolder + lang[0] + "/" + topic + "/" +
									th_name[th] + "/" + featused + ".asz";
							String text = FileManager.readFile(new File(peerUrl));
							//text = delLines(text);
							String[] sents = text.split(System.lineSeparator());
									
									//segmenter.splitToSentences(text);
							
							krouge.resetPeers();
							
							for (String sent: sents){
								List<String> words = segmenter.segmentWords(sent);
								words = stemmer.stemListWords(words);
								krouge.addPeerSentence(words);
							}

						}
						
						krouge.calculateROUGE_2();
						
						thinfo.put(featused, krouge.getR());
						
						if (! sums.containsKey(th_name[th]))
							sums.put(th_name[th], new HashMap<String, Double>());
						
						double dsum = krouge.getR();
						if (sums.get(th_name[th]).containsKey(featused))
							dsum += sums.get(th_name[th]).get(featused);
						sums.get(th_name[th]).put(featused, dsum);
						
						System.out.println(lang[0] + count + "=>" + topic + ":"+ krouge.getR());
						
					} // features combinations
					fileinfo.put(th_name[th], thinfo);

				} // thresholds
				results.put(topic, fileinfo);

			} // files
			
			
			/*
			try {
				SpreadsheetDocument doc = SpreadsheetDocument.newSpreadsheetDocument();
				Table sheet = doc.getSheetByIndex(0);
				int j=1;
				for (String key : results.keySet()){
					HashMap<String, Double> dr = results.get(key);
					sheet.getCellByPosition(1, j).setStringValue(key);
					int i=1;
					for (String key2: dr.keySet()){
						sheet.getCellByPosition(i, 0).setStringValue(key2);
						sheet.getCellByPosition(i, 0).setDoubleValue(dr.get(key2));
						i++;
					}
					j++;
				}
				
				doc.save(File.createTempFile("odf", ".ods"));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}*/
			
			
			String csv = "";
			for (String feat: featuresComb)
					csv += "," + feat;
			
			for (String doc: results.keySet()){
				csv += "\n" + doc + ",\n";
				
				HashMap<String, HashMap<String, Double>> thinfo = results.get(doc);
				for(String th: thinfo.keySet()){
					
					csv += th;
					HashMap<String, Double> feats = thinfo.get(th);
					
					for(String feat: featuresComb)
						csv += "," + feats.get(feat);
					csv += "\n";
					
				}
			}
			
			csv += "\nAverages,\n";
			for (String th: sums.keySet()){
				
				csv += th;
				HashMap<String, Double> feats = sums.get(th);
				
				for(String feat: featuresComb)
					csv += "," + (feats.get(feat)/30);
				csv += "\n";
				
			}
			
			try {
				FileManager.saveFile(peerFolder + "ROUGE/csv/" + lang[0] + ".csv", csv);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}// languages

	}

	private static String delLines(String text){
		text = text.replaceAll("[\\r\\n]", " ");
		text = text.replaceAll(" +", " ");
		return text;
	}

}
