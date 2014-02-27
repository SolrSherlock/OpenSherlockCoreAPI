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
package org.topicquests.common.api;

/**
 * @author park
 *
 */
public interface IResult {
	
	/**
	 * Return <code>true</code> if any error message was added
	 * @return
	 */
	boolean hasError();
	
	/**
	 * Return the accumulated error messages
	 * @return
	 */
	String getErrorString();
	
	/**
	 * Add an error message
	 * @param e
	 */
	void addErrorString(String e);
	
	/**
	 * Return the primary object
	 * @return can return <code>null</code>
	 */
	Object getResultObject();
	
	/**
	 * Set the primary result
	 * @param v
	 */
	void setResultObject(Object v);

	/**
	 * Set an additional result
	 * @param v 
	 */
	void setResultObjectA(Object v);
	
	/**
	 * Return an additional result
	 * @return can return <code>null</code>
	 */
	Object getResultObjectA();
}
