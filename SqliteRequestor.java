/* NaLanGen: Natural Language Generation tool:
 * It contains tools to generate texts in many languages
 * --------------------------------------------------------------------
 * Copyright (C) 2015 Abdelkrime Aries (kariminfo0@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package kariminf.nalangen.wordnet;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kariminf.nalangen.wordnet.SqliteReqExceptions.LangNotFound;
import kariminf.nalangen.wordnet.SqliteReqExceptions.NoSqliteBase;

import org.sqlite.JDBC;



public class SqliteRequestor implements WNRequestor {

	private String lang = "";
	private Connection DB ;
	
	/**
	 * This class needs the 
	 * @param lang
	 */
	private SqliteRequestor(String lang, Connection DB) {
		this.lang = lang;
		this.DB = DB;
	}
	
	
	public static SqliteRequestor create(String lang, String basePath) 
			throws NoSqliteBase, LangNotFound {
		
		if (! new File(basePath).exists()){
			throw new SqliteReqExceptions.NoSqliteBase();
		}
		
		Properties prop = new Properties();
		
		Connection con;
		try {
			con = JDBC.createConnection("jdbc:sqlite:" + basePath, prop);
			String query = "SELECT * \n";
			query += "FROM sqlite_sequence \n";
			query += "WHERE name = '" + lang + "';";
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery(query);
			if (! res.next()){
				throw new SqliteReqExceptions.LangNotFound();
			}
			res.close();
			stat.close();
		} catch (SQLException e) {
			throw new SqliteReqExceptions.NoSqliteBase();
		}
		
		return new SqliteRequestor(lang, con);
	}
	
	/**
	 * 
	 * @param synset The synset of the word in Wordnet
	 * @param pos is a string which is the "Part of speech" of the word. 
	 * It can be: "VERB", "NOUN", "ADJECTIVE" or "ADVERB"
	 * @return 
	 */
	public String getWord(int synset, String pos){
		
		String query = "SELECT word \n";
		query += "FROM " + lang + " \n";
		query += "WHERE synset = " + synset + "\n";
		query += "AND POS = '" + pos + "';";
		
		try {
			Statement stat = DB.createStatement();
			ResultSet res = stat.executeQuery(query);
			if (res.next()){
				return res.getString("word");
			}
			res.close();
			stat.close();
		} catch (SQLException e) {
			return "";
		}
		
		return "";
	}


	@Override
	/**
	 * 
	 * @param synset The synset of the word in Wordnet
	 * @param pos is a string which is the "Part of speech" of the word. 
	 * It can be: "VERB", "NOUN", "ADJECTIVE" or "ADVERB"
	 * @return a list of synonyms
	 */
	public List<String> getWords(int synset, String pos) {
		ArrayList<String> words = new ArrayList<String>();
		
		String query = "SELECT word \n";
		query += "FROM " + lang + " \n";
		query += "WHERE synset = " + synset + "\n";
		query += "AND POS = '" + pos + "';";
		
		try {
			Statement stat = DB.createStatement();
			ResultSet res = stat.executeQuery(query);
			while (res.next()){
				words.add(res.getString("word"));
			}
			res.close();
			stat.close();
		} catch (SQLException e) {
			return words;
		}
		
		return words;
	}


	@Override
	public int getSynset(String word, String pos) {
		String query = "SELECT synset \n";
		query += "FROM " + lang + " \n";
		query += "WHERE word = \"" + word + "\"\n";
		query += "AND POS = '" + pos + "';";
		
		try {
			Statement stat = DB.createStatement();
			ResultSet res = stat.executeQuery(query);
			if (res.next()){
				return res.getInt("synset");
			}
			res.close();
			stat.close();
		} catch (SQLException e) {
			return -1;
		}
		
		return -1;
	}

}
