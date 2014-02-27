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
package org.topicquests.common;

import org.topicquests.common.api.IResult;

/**
 * @author park
 *
 */
public class ResultPojo implements IResult {
	private String errorString = "";
	private boolean isError = false;
	private Object returnObject = null;
	private Object returnObjectA = null;

	/**
	 * 
	 */
	public ResultPojo() {
	}

	/**
	 * 
	 * @param v
	 */
	public ResultPojo(Object v) {
		setResultObject(v);
	}
	
	/* (non-Javadoc)
	 * @see org.topicquests.common.api.IResult#hasError()
	 */
	public boolean hasError() {
		return isError;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.common.api.IResult#getErrorString()
	 */
	public String getErrorString() {
		return errorString;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.common.api.IResult#addErrorString(java.lang.String)
	 */
	public void addErrorString(String e) {
		errorString += "; "+e;
		isError = true;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.common.api.IResult#getReturnObject()
	 */
	public Object getResultObject() {
		return returnObject;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.common.api.IResult#setReturnValue(java.lang.Object)
	 */
	public void setResultObject(Object v) {
		returnObject = v;
	}

	public void setResultObjectA(Object v) {
		returnObjectA = v;
	}

	public Object getResultObjectA() {
		return returnObjectA;
	}

}
