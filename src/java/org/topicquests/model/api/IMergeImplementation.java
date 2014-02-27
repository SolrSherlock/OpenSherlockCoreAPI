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
package org.topicquests.model.api;

import java.util.Map;

import org.topicquests.common.api.IResult;
import org.topicquests.model.api.IEnvironment;
import org.topicquests.model.api.node.INodeModel;

/**
 * @author park
 *
 */
public interface IMergeImplementation {

	/**
	 * Initialize the engine
	 * @param environment
	 */
	void init(IEnvironment environment);

	
	/**
	 * Set the INodeModel
	 * @param m
	 */
	void setNodeModel(INodeModel m);
	
	  /**
	   * <p>Assert a merge, which fires up a VirtualProxy, creates a MergeAssertion node (not a triple)
	   * and adds the list of rule locators to the merge assertion proxy</p>
	   * <p>The merge must be mindful of a nodes place in some graph. If the node has
	   * a parent node of some time, then the VirtualProxy must substitute for that,
	   * and both nodes must be removed as child nodes; the VirtualProxy always stands
	   * for those nodes in that graph.</p>
	   * @param sourceNodeLocator
	 * @param targetNodeLocator
	 * @param mergeData
	 * @param mergeConfidence
	 * @param virtualizer TODO
	 * @param userLocator
	   * @return
	   */
	  IResult assertMerge(String sourceNodeLocator, String targetNodeLocator, Map<String, Double> mergeData, double mergeConfidence, IVirtualizer virtualizer, String userLocator);
	
}
