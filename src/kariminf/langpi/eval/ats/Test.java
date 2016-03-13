package kariminf.langp.testing.ats;

import java.util.ArrayList;
import java.util.List;

import aak.as.preProcess.def.DefNormalizer;
import aak.as.preProcess.def.DefSWEliminator;
import aak.as.preProcess.def.DefSegmenter;
import aak.as.preProcess.def.DefStemmer;
import aak.as.preProcess.lang.Normalizer;
import aak.as.preProcess.lang.PreProcessInfo;
import aak.as.preProcess.lang.SWEliminator;
import aak.as.preProcess.lang.Segmenter;
import aak.as.preProcess.lang.Stemmer;
import aak.as.tools.JarLoader;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		KROUGE krouge = new KROUGE();
		
		JarLoader jarLoader = 
				new JarLoader("preProcess/", "aak/as/preProcess", PreProcessInfo.version);
		
		PreProcessInfo info = jarLoader.getInfoService("en", PreProcessInfo.class);

		Normalizer normalizer = jarLoader.getLangService(info, Normalizer.class);
		if (normalizer == null){
			System.out.println("No Normalizer, using default");
			normalizer = new DefNormalizer();
		}
		
		Segmenter segmenter = jarLoader.getLangService(info,Segmenter.class);
		if (segmenter == null){
			System.out.println("No Segmenter, using default");
			segmenter = new DefSegmenter();
		}
		
		SWEliminator sweliminator = jarLoader.getLangService(info, SWEliminator.class);
		if (sweliminator == null){
			System.out.println("No SWEliminator, using default");
			sweliminator = new DefSWEliminator();
		}
		
		Stemmer stemmer = jarLoader.getLangService(info, Stemmer.class);
		
		if (stemmer == null){
			System.out.println("No Stemmer, using default");
			stemmer = new DefStemmer();
		}

		
		List<String> m = new ArrayList<String>();
		m.add("The history of Texas A&M University, the first public institution of higher education in Texas, began in 1871, when the Agricultural and Mechanical College of Texas was established as a land-grant college by the Texas Legislature. Classes began on October 4, 1876. Although Texas A&M was originally established under the Texas Constitution as a branch of the yet-to-be-created University of Texas, subsequent acts of the Texas Legislature never gave the University any authority over Texas A&M.");
		m.add("For much of its first century, enrollment at Texas A&M was restricted to men who were willing to participate in the Corps of Cadets and receive military training. During this time, a limited number of women were allowed to attend classes but forbidden from gaining a degree. During World War I, 49% of A&M graduates were in military service, and in 1918, the senior class was mustered into military service to fight in France. During World War II, Texas A&M produced over 20,000 combat troops, contributing more officers than both of the military academies combined.");
		m.add("Shortly after World War II, the Texas Legislature redefined Texas A&M as a university and the flagship school of the Texas A&M University System, making official the school's status as a clear and separate institution from the University of Texas. In the 1960s, the state legislature renamed the school Texas A&M University, with the \"A&M\" becoming purely symbolic. Under the leadership of James Earl Rudder, the school became racially integrated and coeducational. Membership in the Corps of Cadets became voluntary.");
		m.add("In the latter half of the twentieth century, the university was recognized for its research with the designations sea-grant university and space-grant university. The school was further honored in 1997 with the establishment of the George Bush Presidential Library on the western edge of the campus.");
		
		
		for (String mi : m){
			List<String> words = segmenter.segmentWords(mi);
			//sweliminator.deleteSW(words);
			words = stemmer.stemListWords(words);
			krouge.addModelSentence(words);
		}
			
		/*
		for (String mi : m)
			krouge.addModelSentence(getWords(mi));*/
		
		List<String> p = new ArrayList<String>();
		p.add("States were granted public lands to be sold at auctions to establish a permanent fund to support the schools.");
		p.add("Despite its name, the college taught no classes in agriculture, instead concentrating on classical studies, languages, literature, and applied mathematics.");
		p.add("Enrollment, which had climbed as high as 500�students, declined to only 80�students in 1883, the year the University of Texas opened in Austin, Texas.");
		p.add("For the next several decades during the summers cadets were not required to be in uniform and women could attend class and participate in intramural activities.");
		p.add("Unprepared for the growth, between 1949 and 1953 Texas A&M used the former Bryan Air Force Base as an extension of the campus.");;
		p.add("More change ensued, as, in 1965, the Board of Directors voted to make membership in the Corps of Cadets voluntary.");
		p.add("The curriculum had been broadened, with upgraded academic and faculty standards, and the school had initiated a multi-million dollar building program.");;
		p.add("The incident received nationwide attention, with over 50 satellite trucks broadcasting from the Texas A&M campus within hours.");
		p.add("The two Texas schools quickly began to battle over the limited funds that the state legislature made available for higher education.");
		p.add("As Ross began to make improvements, parents began to send their children to the school in the hopes that they would learn from Ross's example.");
		p.add("The Texas Legislature in 1911 refused to give A&M permission to hold a summer semester unless women were also permitted to attend.");
		p.add("Within several years of his arrival, the 58th Legislature of Texas officially changed the name of the school from the A&M College of Texas to Texas A&M University.");
		p.add("Women were originally prohibited from serving in leadership positions or in the more elite Corps units such as the band and Ross Volunteers.");
		
		
		for (String pi: p){
			List<String> words = segmenter.segmentWords(pi);
			//sweliminator.deleteSW(words);
			words = stemmer.stemListWords(words);
			krouge.addPeerSentence(words);
		}
			
		
		/*
		for (String pi: p)
			krouge.addPearSentence(getWords(pi));*/
		
		
		krouge.calculateROUGE_2();
		
		System.out.println(krouge.getR());

	}
	
	public static List<String> getWords (String sent){
		//sent = sent.replaceAll(" +", " ");
		sent = sent.replaceAll("[^\\p{L}\\p{N}]", " ").replaceAll("[ ]+", " ");
		List<String> res = new ArrayList<String>();
		
		String[] words = sent.split(" ");
		
		for (String word: words)
			res.add(word);
		
		return res;
	}

}
