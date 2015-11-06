/*
 * AllSumarizer v2
 * This file is part of AllSummarizer project; an implementation of the method
 * described in this paper:
 * http://dx.doi.org/10.1117/12.2004001
 * 
 * Arabic Stemming 
 * Copyright (C) 2014  Abdelkrime Aries <kariminfo0@gmail.com>
 * Copyright (C) 2012  Faris AlFaris, IbrahimQ and Turki Saud 
 * 					   <http://sourceforge.net/projects/arabicstemmer/>
 * Copyright (C) 2002  Shereen Khoja <s.Khoja@lancaster.ac.uk>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package dz.aak.as.preProcess.arabic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import aak.as.preProcess.lang.Stemmer;

public class ArStemmer implements Stemmer {

	private Vector<Vector<String>> staticFiles ;
	private String  pathToStemmerFiles = "/ressources/stemming/arabic/";
	private static boolean rootFound = false;
	private static boolean fromSuffixes = false;
	

	public ArStemmer() {
		initComponents();
	}

	@Override
	public List<String> stemListWords(List<String> listWords) {
		
		List<String> stemmedLW = new ArrayList<String>();

		for (String word: listWords){
			//initComponents();
			rootFound = false;
			fromSuffixes = false;
			stemmedLW.add(stemWord(word));
		}
		
		return stemmedLW;
	}


	private void initComponents() {
		
		rootFound = false;
		
		staticFiles = new Vector<Vector<String>> ( );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "definite_article.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "duplicate.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "first_waw.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "first_yah.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "last_alif.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "last_hamza.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "last_maksoura.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "last_yah.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "mid_waw.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "mid_yah.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "prefixes.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "punctuation.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "quad_roots.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "stopwords.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "suffixes.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "tri_patt.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "tri_roots.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "diacritics.txt" ).toString ( ) );
		addVectorFromFile ( new StringBuffer ( pathToStemmerFiles + "strange.txt" ).toString ( ) );
	}

	private String checkForPrefixes ( String word ) {

		String prefix = "";
		String modifiedWord = word;
		Vector<String> prefixes = staticFiles.elementAt ( 10 );
		//System.out.println("for checkForPrefix");
		// for every prefix in the list
		for ( int i = 0; i < prefixes.size ( ); i++ )
		{
			prefix =  prefixes.elementAt ( i );
			// if the prefix was found
			if ( prefix.regionMatches ( 0, modifiedWord, 0, prefix.length ( ) ) )
			{
				modifiedWord = modifiedWord.substring ( prefix.length ( ) );

				// check to see if the word is a root of three or four letters
				// if the word has only two letters, test to see if one was removed
				if ( modifiedWord.length ( ) == 2 )
					modifiedWord = isTwoLetters ( modifiedWord );
				else if ( modifiedWord.length ( ) == 3 && !rootFound )
					modifiedWord = isThreeLetters ( modifiedWord );
				else if ( modifiedWord.length ( ) == 4 )
					isFourLetters ( modifiedWord );

				// if the root hasn't been found, check for patterns
				if ( !rootFound && modifiedWord.length ( ) > 2 )
					modifiedWord = checkPatterns ( modifiedWord );

				// if the root STILL hasn't been found
				if ( !rootFound && !fromSuffixes)// && !stopwordFound
				{
					// check for suffixes
					modifiedWord = checkForSuffixes ( modifiedWord );
				}

				/*if ( stopwordFound )
					return modifiedWord;*/

				// if the root was found, return the modified word
				if ( rootFound )//&& !stopwordFound 
				{
					return modifiedWord;
				}
			}
		}
		return word;
	}

	private String isTwoLetters ( String word ) {
		// if the word consists of two letters, then this could be either
		// - because it is a root consisting of two letters (though I can't think of any!)
		// - because a letter was deleted as it is duplicated or a weak middle or last letter.

		word = duplicate ( word );

		// check if the last letter was weak
		if ( !rootFound )
			word = lastWeak(word);

		// check if the first letter was weak
		if ( !rootFound )
			word = firstWeak(word);

		// check if the middle letter was weak
		if ( !rootFound )
			word = middleWeak ( word );

		return word;
	}


	// if the word consists of three letters
	private String isThreeLetters ( String word ) {   

		StringBuffer modifiedWord = new StringBuffer(word);
		String root = "";
		// if the first letter is a 'ا', 'ؤ'  or 'ئ'
		// then change it to a 'أ'
		if ( word.length ( ) > 0 )
		{
			if (word.charAt(0) == 'ا' || word.charAt(0) == 'ؤ' || word.charAt(0) == 'ئ')
			{
				modifiedWord.setLength ( 0 );
				modifiedWord.append ( 'أ' );
				modifiedWord.append (word.substring(1));
				root = modifiedWord.toString ( );
			}

			// if the last letter is a weak letter or a hamza
			// then remove it and check for last weak letters
			if (word.charAt(2) == 'و' || word.charAt(2) == 'ي' || word.charAt(2) == 'ا' ||
					word.charAt ( 2 ) == 'ى' || word.charAt ( 2 ) == 'ء' || word.charAt ( 2 ) == 'ئ' )
			{
				root = word.substring ( 0, 2 );
				root = lastWeak ( root );
				if ( rootFound )
					return root;
	
			}

			// if the second letter is a weak letter or a hamza
			// then remove it
			if ( word.charAt ( 1 ) == 'و' || word.charAt ( 1 ) == 'ي' || word.charAt ( 1 ) == 'ا' || word.charAt ( 1 ) == 'ئ' )
			{
				root = word.substring ( 0, 1 );
				root = root + word.substring ( 2 );

				root = middleWeak ( root );
				if ( rootFound )
				{
					return root;
				}
			}

			// if the second letter has a hamza, and it's not on a alif
			// then it must be returned to the alif
			if ( word.charAt ( 1 ) == 'ؤ' || word.charAt ( 1 ) == 'ئ' )
			{
				if ( word.charAt ( 2 ) == 'م' || word.charAt ( 2 ) == 'ز' || word.charAt ( 2 ) == 'ز' )
				{
					root = word.substring ( 0, 1 );
					root = root + 'ا';
					root = root+ word.substring ( 2 );
				}
				else
				{
					root = word.substring ( 0, 1 );
					root = root + 'أ';
					root = root + word.substring ( 2 );
				}
			}

			// if the last letter is a shadda, remove it and
			// duplicate the last letter
			if ( word.charAt ( 2 ) == 'ّ')
			{
				root = word.substring ( 0, 1 );
				root = root + word.substring ( 1, 2 );
			}
		}

		// if word is a root, then rootFound is true
		if ( root.length ( ) == 0 )
		{
			if ( ( staticFiles.elementAt(16)).contains(word))
			{
				rootFound = true;
				return word;
			}
		}
		// check for the root that we just derived
		else if ((staticFiles.elementAt(16)).contains(root))
		{
			rootFound = true;
			return root;
		}

		return word;
	}


	// if the word has four letters
	private void isFourLetters ( String word ) {   

		// if word is a root, then rootFound is true
		if (staticFiles.elementAt(12).contains(word))
			rootFound = true;

	}

	// check if the word matches any of the patterns
	private String checkPatterns ( String word ) {   
		StringBuffer root = new StringBuffer ( "" );
		// if the first letter is a hamza, change it to an alif
		if ( word.length ( ) > 0 )
			if ( word.charAt ( 0 ) == 'أ' || word.charAt ( 0 ) == 'إ' || word.charAt ( 0 ) == 'آ' )
			{
				root.append ( "j" );
				root.setCharAt ( 0, 'ا' );
				root.append ( word.substring ( 1 ) );
				word = root.toString ( );
			}

		// try and find a pattern that matches the word
		Vector<String> patterns = staticFiles.elementAt ( 15 );
		int numberSameLetters = 0;
		String pattern = "";
		String modifiedWord = "";

		// for every pattern
		for( int i = 0; i < patterns.size ( ); i++ )
		{
			pattern =  patterns.elementAt ( i );
			root.setLength ( 0 );
			// if the length of the words are the same
			if ( pattern.length ( ) == word.length ( ) )
			{
				numberSameLetters = 0;
				// find out how many letters are the same at the same index
				// so long as they're not a fa, ain, or lam
				for ( int j = 0; j < word.length ( ); j++ )
					if ( pattern.charAt ( j ) == word.charAt ( j ) &&
					pattern.charAt ( j ) != 'ف' &&
					pattern.charAt ( j ) != 'ع' &&
					pattern.charAt ( j ) != 'ل' )
						numberSameLetters ++;

				// test to see if the word matches the pattern افعلا
				if ( word.length ( ) == 6 && word.charAt ( 3 ) == word.charAt ( 5 ) && numberSameLetters == 2 )
				{
					root.append ( word.charAt ( 1 ) );
					root.append ( word.charAt ( 2 ) );
					root.append ( word.charAt ( 3 ) );
					modifiedWord = root.toString ( );
					modifiedWord = isThreeLetters ( modifiedWord );
					if ( rootFound )
						return modifiedWord;
					else
						root.setLength ( 0 );
				}


				// if the word matches the pattern, get the root
				if ( word.length ( ) - 3 <= numberSameLetters )
				{
					// derive the root from the word by matching it with the pattern
					for ( int j = 0; j < word.length ( ); j++ )
						if ( pattern.charAt ( j ) == 'ف' ||
						pattern.charAt ( j ) == 'ع' ||
						pattern.charAt ( j ) == 'ل'   )
							root.append ( word.charAt ( j ) );

					modifiedWord = root.toString ( );
					modifiedWord = isThreeLetters ( modifiedWord );

					if ( rootFound )
					{
						word = modifiedWord;
						return word;
					}
				}
			}
		}
		return word;
	}


	// METHOD CHECKFORSUFFIXES
	private String checkForSuffixes ( String word )
	{   
		String suffix = "";
		String modifiedWord = word;
		Vector<String> suffixes = staticFiles.elementAt ( 14 );
		fromSuffixes = true;

		// for every suffix in the list
		for ( int i = 0; i < suffixes.size ( ); i++ )
		{
			suffix =  suffixes.elementAt ( i );

			// if the suffix was found
			if( suffix.regionMatches ( 0, modifiedWord, modifiedWord.length ( ) - suffix.length ( ), suffix.length ( ) ) )
			{
				modifiedWord = modifiedWord.substring ( 0, modifiedWord.length ( ) - suffix.length ( ) );

				// check to see if the word is a root of three or four letters
				// if the word has only two letters, test to see if one was removed
				if ( modifiedWord.length ( ) == 2 )
				{
					modifiedWord = isTwoLetters ( modifiedWord );
				}
				else if ( modifiedWord.length ( ) == 3 )
				{
					modifiedWord = isThreeLetters ( modifiedWord );
				}
				else if ( modifiedWord.length ( ) == 4 )
				{
					isFourLetters ( modifiedWord );
				}

				// if the root hasn't been found, check for patterns
				if ( !rootFound && modifiedWord.length( ) > 2 )
				{
					modifiedWord = checkPatterns( modifiedWord );
				}

				// if the root was found, return the modified word
				if ( rootFound )
				{
					fromSuffixes = false;
					return modifiedWord;
				}
			}
		}
		fromSuffixes = false;
		return word;
	}


	// handle duplicate letters in the word
	private String duplicate ( String word )
	{   

		// check if a letter was duplicated
		if (staticFiles.elementAt(1).contains(word))
		{
			// if so, then return the deleted duplicate letter
			word = word + word.substring ( 1 );

			// root was found, so set variable
			rootFound = true;

			return word;
		}
		return word;
	}

	// check if the last letter of the word is a weak letter
	private String lastWeak ( String word )
	{   

		StringBuffer stemmedWord = new StringBuffer ( "" );
		// check if the last letter was an alif
		if ( ( ( Vector<String> )staticFiles.elementAt ( 4 ) ).contains ( word ) )
		{
			stemmedWord.append ( word );
			stemmedWord.append ( "ا" );
			word = stemmedWord.toString ( );
			stemmedWord.setLength ( 0 );

			// root was found, so set variable
			rootFound = true;
			return word;
		}
		// check if the last letter was an hamza
		else if ( ( staticFiles.elementAt ( 5 ) ) .contains ( word ) )
		{
			stemmedWord.append ( word );
			stemmedWord.append ( "آ" );
			word = stemmedWord.toString ( );
			stemmedWord.setLength ( 0 );

			// root was found, so set variable
			rootFound = true;
			return word;
		}
		// check if the last letter was an maksoura
		else if ( ( staticFiles.elementAt ( 6 ) ) .contains ( word ) )
		{
			stemmedWord.append ( word );
			stemmedWord.append ( "ى" );
			word = stemmedWord.toString ( );
			stemmedWord.setLength ( 0 );

			// root was found, so set variable
			rootFound = true;
			return word;
		}
		// check if the last letter was an yah
		else if ( ( staticFiles.elementAt ( 7 ) ).contains ( word ) )
		{
			stemmedWord.append ( word );
			stemmedWord.append ( "ي" );
			word = stemmedWord.toString ( );
			stemmedWord.setLength ( 0 );

			// root was found, so set variable
			rootFound = true;
			return word;
		}
		return word;
	}

	//--------------------------------------------------------------------------

	// check if the first letter is a weak letter
	private String firstWeak ( String word )
	{   

		StringBuffer stemmedWord = new StringBuffer ( "" );
		// check if the firs letter was a waw
		if( ( staticFiles.elementAt ( 2 ) ) .contains ( word ) )
		{
			stemmedWord.append ( "و" );
			stemmedWord.append ( word );
			word = stemmedWord.toString ( );
			stemmedWord.setLength ( 0 );

			// root was found, so set variable
			rootFound = true;
			return word;
		}
		// check if the first letter was a yah
		else if ( ( staticFiles.elementAt ( 3 ) ) .contains ( word ) )
		{
			stemmedWord.append ( "ي" );
			stemmedWord.append ( word );
			word = stemmedWord.toString ( );
			stemmedWord.setLength ( 0 );

			// root was found, so set variable
			rootFound = true;
			return word;
		}
		return word;
	}

	//--------------------------------------------------------------------------

	// check if the middle letter of the root is weak
	private String middleWeak ( String word )
	{   

		StringBuffer stemmedWord = new StringBuffer ( "j" );
		// check if the middle letter is a waw
		if ( ( staticFiles.elementAt ( 8 ) ) .contains ( word ) )
		{
			// return the waw to the word
			stemmedWord.setCharAt ( 0, word.charAt ( 0 ) );
			stemmedWord.append ( "و" );
			stemmedWord.append ( word.substring ( 1 ) );
			word = stemmedWord.toString ( );
			stemmedWord.setLength ( 0 );

			// root was found, so set variable
			rootFound = true;
			return word;
		}
		// check if the middle letter is a yah
		else if ( ( staticFiles.elementAt ( 9 ) ) .contains ( word ) )
		{
			// return the waw to the word
			stemmedWord.setCharAt ( 0, word.charAt ( 0 ) );
			stemmedWord.append ( "ي" );
			stemmedWord.append ( word.substring ( 1 ) );
			word = stemmedWord.toString ( );
			stemmedWord.setLength ( 0 );

			// root was found, so set variable
			rootFound = true;
			return word;
		}
		return word;
	}

	// read in the contents of a file, put it into a vector, and add that vector
	// to the Vector<String> composed of vectors containing the static files
	protected boolean addVectorFromFile ( String fileName )
	{   

		boolean returnValue;
		try
		{
			// the Vector<String> we are going to fill
			Vector<String> vectorFromFile = new Vector<String> ( );

			// create a buffered reader
			InputStream fileInputStream = ArStemmer.class.getResourceAsStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader ( fileInputStream, "UTF-16" );

			//If the bufferedReader is not big enough for a file, I should change the size of it here
			BufferedReader bufferedReader = new BufferedReader ( inputStreamReader, 20000 );

			// read in the text a line at a time
			String part;
			StringBuffer word = new StringBuffer ( );
			while ( ( part = bufferedReader.readLine ( ) ) != null )
			{
				// add spaces at the end of the line
				part = part + "  ";

				// for each line
				for ( int index = 0; index < part.length ( ) - 1; index ++ )
				{
					// if the character is not a space, append it to a word
					if ( ! ( Character.isWhitespace ( part.charAt ( index ) ) ) )
					{
						word.append ( part.charAt ( index ) );
					}
					// otherwise, if the word contains some characters, add it
					// to the vector
					else
					{
						if ( word.length ( ) != 0 )
						{
							vectorFromFile.addElement ( word.toString ( ) );
							word.setLength ( 0 );
						}
					}
				}
			}

			// trim the vector
			vectorFromFile.trimToSize ( );

			// destroy the buffered reader
			bufferedReader.close ( );
			fileInputStream.close ( );

			// add the Vector<String> to the Vector<String> composed of vectors containing the
			// static files
			staticFiles.addElement ( vectorFromFile );
			returnValue = true;
		}
		catch ( Exception exception )
		{
			//JOptionPane.showMessageDialog ( arabicStemmerGUI, "Could not open '" + fileName + "'.", " Error ", JOptionPane.ERROR_MESSAGE );
			returnValue = false;
		}
		return returnValue;
	}
	// stem the word
	public String stemWord ( String word )
	{   

		// check if the word consists of two letters
		// and find it's root
		if ( word.length ( ) == 2 )
			word = isTwoLetters(word);

		// if the word consists of three letters
		if( word.length ( ) == 3 && !rootFound )
			// check if it's a root
			word = isThreeLetters ( word );

		// if the word consists of four letters
		if( word.length ( ) == 4 )
			// check if it's a root
			isFourLetters ( word );

		// if the root hasn't yet been found
		if( !rootFound )
		{
			// check if the word is a pattern
			word = checkPatterns ( word );
		}

		// if the root still hasn't been found
		if ( !rootFound )
		{
			// check for a definite article, and remove it
			word = checkDefiniteArticle ( word );
		}

		// if the root still hasn't been found
		if ( !rootFound )//&& !stopwordFound 
		{
			// check for the prefix waw
			word = checkPrefixWaw ( word );
		}

		// if the root STILL hasnt' been found
		if ( !rootFound )//&& !stopwordFound 
		{
			// check for suffixes
			word = checkForSuffixes ( word );
		}

		// if the root STILL hasn't been found
		if ( !rootFound )//&& !stopwordFound 
		{
			// check for prefixes
			word = checkForPrefixes ( word );
		}
		return word;
	}

	// check and remove the definite article
	private String checkDefiniteArticle ( String word )
	{   

		// looking through the Vector<String> of definite articles
		// search through each definite article, and try and
		// find a match
		String definiteArticle = "";
		String modifiedWord = "";
		Vector<String> definiteArticles = staticFiles.elementAt ( 0 );

		// for every definite article in the list
		for ( int i = 0; i < definiteArticles.size ( ); i++ )
		{
			definiteArticle =  definiteArticles.elementAt ( i );
			// if the definite article was found
			if ( definiteArticle.regionMatches ( 0, word, 0, definiteArticle.length ( ) ) )
			{
				// remove the definite article
				modifiedWord = word.substring ( definiteArticle.length ( ), word.length ( ) );

				// check to see if the word is a root of three or four letters
				// if the word has only two letters, test to see if one was removed
				if ( modifiedWord.length ( ) == 2 )
					modifiedWord = isTwoLetters ( modifiedWord );
				else if ( modifiedWord.length ( ) == 3 && !rootFound )
					modifiedWord = isThreeLetters ( modifiedWord );
				else if ( modifiedWord.length ( ) == 4 )
					isFourLetters ( modifiedWord );

				// if the root hasn't been found, check for patterns
				if ( !rootFound && modifiedWord.length ( ) > 2 )
					modifiedWord = checkPatterns ( modifiedWord );

				// if the root STILL hasnt' been found
				if ( !rootFound )//&& !stopwordFound 
				{
					// check for suffixes
					modifiedWord = checkForSuffixes ( modifiedWord );
				}

				// if the root STILL hasn't been found
				if ( !rootFound )//&& !stopwordFound 
				{
					// check for prefixes
					modifiedWord = checkForPrefixes ( modifiedWord );
				}

				// if the root was found, return the modified word
				if ( rootFound )//&& !stopwordFound 
				{
					return modifiedWord;
				}
			}
		}
		if ( modifiedWord.length ( ) > 3 )
			return modifiedWord;
		return word;
	}

	// check and remove the special prefix (waw)
	private String checkPrefixWaw ( String word )
	{   

		String modifiedWord = "";

		if ( word.length ( ) > 3 && word.charAt ( 0 ) == 'و' )
		{
			modifiedWord = word.substring ( 1 );

			// check to see if the word is a root of three or four letters
			// if the word has only two letters, test to see if one was removed
			if ( modifiedWord.length ( ) == 2 )
				modifiedWord = isTwoLetters( modifiedWord );
			else if ( modifiedWord.length ( ) == 3 && !rootFound )
				modifiedWord = isThreeLetters( modifiedWord );
			else if ( modifiedWord.length ( ) == 4 )
				isFourLetters ( modifiedWord );

			// if the root hasn't been found, check for patterns
			if ( !rootFound && modifiedWord.length ( ) > 2 )
				modifiedWord = checkPatterns ( modifiedWord );

			// if the root STILL hasnt' been found
			if ( !rootFound )//&& !stopwordFound 
			{
				// check for suffixes
				modifiedWord = checkForSuffixes ( modifiedWord );
			}

			// iIf the root STILL hasn't been found
			if ( !rootFound )//&& !stopwordFound 
			{
				// check for prefixes
				modifiedWord = checkForPrefixes ( modifiedWord );
			}

			/*if ( stopwordFound )
				return modifiedWord;*/

			if ( rootFound )//&& !stopwordFound 
			{
				return modifiedWord;
			}
		}
		return word;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArStemmer Stemmer=new ArStemmer();
		String ArabicWord="تستعمل";
		List<String> lst = new ArrayList<String>();
		lst.add("تستعمل");
		lst.add("المعلوماتية");
		lst.add("العليا");
		lst.add("للإعلام");
		
		System.out.println(Stemmer.stemWord(ArabicWord));
		lst = Stemmer.stemListWords(lst);
		System.out.println(lst.toString());	

	}

}
