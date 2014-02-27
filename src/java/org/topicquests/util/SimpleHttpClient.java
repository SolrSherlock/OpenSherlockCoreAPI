/*
 * Copyright 2012, TopicQuests
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
package org.topicquests.util;

import org.topicquests.common.ResultPojo;
import org.topicquests.common.api.IResult;

import java.net.*;
import java.io.*;
/**
 * @author park
 * <p>A very simple HTTP client for simple routines</p>
 */
public class SimpleHttpClient {

	/**
	 * 
	 */
	public SimpleHttpClient() {
		//
	}

	public IResult put(String url, String queryString) {
		return handle(url,queryString,"POST");
	}
	
	public IResult get(String url, String queryString) {
		return handle(url,queryString,"GET");
	}
	
	private IResult handle(String url, String queryString, String mode) {
		IResult result = new ResultPojo();
		BufferedReader rd = null;
		HttpURLConnection con = null;
		try {
			URL urx = new URL(url+queryString);
			con = (HttpURLConnection)urx.openConnection();
			con.setRequestMethod(mode);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder buf = new StringBuilder();
			String line;
		    while ((line = rd.readLine()) != null) {
		    	buf.append(line + '\n');
	        }
		    result.setResultObject(buf.toString());
	     
		} catch (Exception e) {
			e.printStackTrace();
			result.addErrorString(e.getMessage());
		} finally {
			try {
				if (rd != null)
					rd.close();
				if (con != null)
					con.disconnect();
			} catch (Exception x) {
				x.printStackTrace();
				result.addErrorString(x.getMessage());
			}
		}
		return result;

	}
}
