
package kariminf.as.preProcess.indonesian;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.id.IndonesianStemmer;

import kariminf.as.preProcess.lang.Stemmer;

public class IdStemmer implements Stemmer {
	
	private IndonesianStemmer stemmer = new IndonesianStemmer();

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public String stemWord (String word){
		
		
		char[] wordseq = word.toCharArray();
		
		//true: use full stemming, not just the light one
		int newlength = stemmer.stem(wordseq, word.length(),true);

		char[] newwordseq = new char[newlength];
		System.arraycopy(wordseq, 0, newwordseq, 0, newlength);

		return new String(newwordseq);
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new IdStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("salah");
		tstList.add("satu");
		tstList.add("penjelasan");
		tstList.add("waktu");
		tstList.add("standar");
		tstList.add("muncul");
		tstList.add("abad");
		tstList.add("masehi");
		tstList.add("buku");
		tstList.add("astronomi");
		tstList.add("surya");
		tstList.add("siddhanta");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





