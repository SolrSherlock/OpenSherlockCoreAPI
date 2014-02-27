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

import java.util.Map;

/**
 * @author park
 * <p>An <code>IMergeRuleMethod</code> makes one and only one test on two
 * {@link Map} objects for equality</p>
 * <p>These methods are loaded into any merge engine with <code>Class.forName</code>
 * from a properties file</code>
 */
public interface IMergeRuleMethod {

	/**
	 * Each <code>IMergeRuleMethod</code> is associated with an {@link INode} in the
	 * topic map; we create the <code>locator</code> for that node here
	 * @return
	 */
	String getNodeLocator();
	
	/**
	 * Return the name of this test
	 * @return
	 */
	String getLabel();
	
	/**
	 * Return the description of this test
	 * @return
	 */
	String getDescription();
	
	/**
	 * Run a test which returns a Boolean or an error string
	 * @param sourceNode
	 * @param targetNode
	 * @return
	 */
	IResult doTest(Map<String,Object> sourceNode, Map<String,Object> targetNode);
}
