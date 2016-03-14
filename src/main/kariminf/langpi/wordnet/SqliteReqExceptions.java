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


package kariminf.langpi.wordnet;

public class SqliteReqExceptions {
	
	/**
	 * This exception happens when the database is not sqlite, 
	 * or the file has not been found
	 * 
	 * @author Abdelkrime Aries
	 *
	 */
	public static class NoSqliteBase extends Exception {
		private static final long serialVersionUID = 1L;
	}
	
	/**
	 * This exception happens when the database doesn't contain the specified language
	 * 
	 * @author Abdelkrime Aries
	 *
	 */
	public static class LangNotFound extends Exception {
		private static final long serialVersionUID = 1L;
		
	}
	
	

}
