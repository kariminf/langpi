package kariminf.langpi.wordnet;

import java.io.File;
import java.net.URL;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;

public class WNTools {
	
	public static final String wnLoc = "/wordnetDB/dict/";
	
	//PS: just put it in the bin/
	private static JWIRequestor jwir = getRequestor();
	private static JWIRequestor getRequestor(){
		String resUrl = WNTools.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		//String resUrl = new File("").getAbsolutePath();
		//String resUrl = System.getProperty("user.dir");
		File file = new File(resUrl + wnLoc);
		System.out.println(resUrl);
		IDictionary dict = new Dictionary(file) ;
		return new JWIRequestor(dict);
	}
	
	public static int getLexFileNumber(int synset, String pos){
		return jwir.getLexFileNumber(synset, pos);
	}
	
	
	//public static int get
	
	
	public static void main(String[] args){
		System.out.println(WNTools.getLexFileNumber(15168185, "NOUN"));
		
	}

}
