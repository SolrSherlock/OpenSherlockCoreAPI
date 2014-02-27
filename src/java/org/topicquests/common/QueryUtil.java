/*
 * Copyright 2013, TopicQuests
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.topicquests.common;

/**
 * @author park
 *
 */
public class QueryUtil {

	/**
	 * 
	 */
	public QueryUtil() {
	}

	public static String escapeNodeData(String s)
	 {
		return s;
		//It turns out you don't mess with the data going up
		//we keep this around in case we need to mess with data that is XML-like
/*		 StringBuilder sb = new StringBuilder();
		 for (int i = 0; i < s.length(); i++)
		 {
			 char c = s.charAt(i);
			 // These characters are part of the query syntax and must be escaped
			 if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':'
					 || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
					 || c == '*' || c == '?' || c == '|' || c == '&' || c == ';'
					 ) {
				 sb.append('\\');
			 }
			 sb.append(c);
		 }
		 return sb.toString(); */
	 }

	/**
	 * A SolrQuery must be escaped. This is the approach to escaping a query.
	 * @param s
	 * @return
	 */
	public static String escapeQueryCulprits(String s)
	 {
		 StringBuilder sb = new StringBuilder();
		 for (int i = 0; i < s.length(); i++)
		 {
			 char c = s.charAt(i);
			 // These characters are part of the query syntax and must be escaped
			 if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':'
					 || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
					 || c == '*' || c == '?' || c == '|' || c == '&' || c == ';'
					 ) {
				 sb.append('\\');
			 }
		//	 if(Character.isWhitespace(c))
		//		 sb.append(" \\ ");
			 sb.append(c);
		 }
		 String x = sb.toString();
		 if (x.indexOf(' ') > -1) {
			 x = "\""+x+"\"";  //turn into a phrase query
		 }
		 return x;
	 }
	
	public static String unEscapeQueryCulprits(String s) {
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		char c, c1;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (c == '\\') {
				if ((i+1) < (len-1)) {
					c1 = s.charAt(i+1);
					if (c1 == '\\') {
						sb.append(c);
						i++;
					} else if (c1 == '+') {
						sb.append(c1);
						i++;
					} else if (c1 == '-') {
						sb.append(c1);
						i++;
					} else if (c1 == '!') {
						sb.append(c1);
						i++;
					} else if (c1 == '(') {
						sb.append(c1);
						i++;
					} else if (c1 == ')') {
						sb.append(c1);
						i++;
					} else if (c1 == ':') {
						sb.append(c1);
						i++;
					} else if (c1 == '^') {
						sb.append(c1);
						i++;
					} else if (c1 == '[') {
						sb.append(c1);
						i++;
					} else if (c1 == ']') {
						sb.append(c1);
						i++;
					} else if (c1 == '\"') {
						sb.append(c1);
						i++;
					} else if (c1 == '+') {
						sb.append(c1);
						i++;
					} else if (c1 == '{') {
						sb.append(c1);
						i++;
					} else if (c1 == '}') {
						sb.append(c1);
						i++;
					} else if (c1 == '~') {
						sb.append(c1);
						i++;
					} else if (c1 == '*') {
						sb.append(c1);
						i++;
					} else if (c1 == '?') {
						sb.append(c1);
						i++;
					} else if (c1 == '|') {
						sb.append(c1);
						i++;
					} else if (c1 == '&') {
						sb.append(c1);
						i++;
					} else if (c1 == ';') {
						sb.append(c1);
						i++;
					} //????
				} // ????
			} else
				sb.append(c);
		}
		return sb.toString();
	}

}
