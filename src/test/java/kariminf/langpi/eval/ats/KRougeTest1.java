package kariminf.langpi.eval.ats;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class KRougeTest1 {

	//Model.sentence.words
	static List<List<List<String>>> models = new ArrayList<>();
	static List<List<String>> peer = new ArrayList<>();

	//Calculated using ROUGE-1.5.5.pl
	static double[] rouge1 = {
			0.61111,
			0.68750,
			0.64706
	};

	//Calculated using ROUGE-1.5.5.pl
	static double[] rouge2 = {
			0.14706,
			0.16667,
			0.15625
	};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		models.add(readFile("/krouge/m1.txt"));
		models.add(readFile("/krouge/m2.txt"));

		peer = readFile("/krouge/e1.txt");

		//System.out.println(models);
		//System.out.println(peer);

	}

	@Test
	public void testRouge1() {

		KRouge kr = new KRouge(KRouge.GramType.GRAM1);

		for(List<List<String>> model: models){
			kr.newModel();
			for (List<String> sent: model)
				kr.addModelSentence(sent);
		}

		for (List<String> sent: peer)
			kr.addPeerSentence(sent);

		kr.calculate();
		
		assertEquals(rouge1[0], kr.getRecall(), 0.0001);
		assertEquals(rouge1[1], kr.getPrecision(), 0.0001);
		assertEquals(rouge1[2], kr.getFScore(0.5), 0.0001);

		//System.out.println(kr.getRecall());
		//System.out.println(kr.getPrecision());

	}

	@Test
	public void testRouge2() {
		KRouge kr = new KRouge(KRouge.GramType.GRAM2);

		for(List<List<String>> model: models){
			kr.newModel();
			for (List<String> sent: model)
				kr.addModelSentence(sent);
		}

		for (List<String> sent: peer)
			kr.addPeerSentence(sent);

		kr.calculate();

		assertEquals(rouge2[0], kr.getRecall(), 0.0001);
		assertEquals(rouge2[1], kr.getPrecision(), 0.0001);
		assertEquals(rouge2[2], kr.getFScore(0.5), 0.0001);

	}

	public static List<List<String>> readFile(String path){
		List<List<String>> sentences = new ArrayList<>();
		try {

			InputStream in = KRougeTest1.class.getResourceAsStream(path);
			InputStreamReader ir = new InputStreamReader(in, "UTF-8");
			BufferedReader input = new BufferedReader(ir);
			for(String line = input.readLine(); line != null; line = input.readLine()){
				String[] ws = line.split("\\s+");
				sentences.add(Arrays.asList(ws));
			}

			//stopwords.add(line);
			input.close();

			return sentences;

		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		} 
	}

}
