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
package org.topicquests.model.api.node;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.topicquests.common.api.IResult;
import org.topicquests.model.api.ITicket;

/**
 * @author park
 *
 */
public interface INodeModel {
	
	/**
	 * Preferred way to add a <code>superClassLocator</code>
	 * to an existing <code>node</code> to deal with
	 * changes to transitive closure
	 * @param node
	 * @param superClassLocator
	 * @return
	 */
	IResult addSuperClass(INode node, String superClassLocator);
	
	/**
	 * Preferred way to set a <code>typeLocator</code> to an
	 * existing <code>node</code> to deal with changes to 
	 * transitive closure
	 * @param node
	 * @param typeLocator
	 * @return
	 */
	IResult setNodeType(INode node, String typeLocator);
	
	/**
	 * Return a new {@link IAddressableInformationResource}
	 * @param locator
	 * @param subject can be <code>null</code>
	 * @param body can be <code>null</code>
	 * @param language can be <code>null</code>
	 * @param userId
	 * @param isPrivate
	 * @return
	 * /
	IAddressableInformationResource newAIR(String locator, String subject, String body, String language,
			String userId, boolean isPrivate);
	
	  /**
	   * Return a new {@link INode} with the given <code>locator</code>
	   * @param locator
	   * @param label
	   * @param description
	   * @param lang
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isPrivate
	   * @return
	   */
	  INode newNode(String locator,String label, String description, String lang, 
			  String userId, String smallImagePath, String largeImagePath, boolean isPrivate);
	  
	  /**
	   * Return a new {@link INode} with a database-created <code>locator</code>
	   * @param label
	   * @param description
	   * @param lang
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isPrivate
	   * @return
	   */
	  INode newNode(String label, String description, String lang, String userId, 
			  String smallImagePath, String largeImagePath, boolean isPrivate);
	  
	  /**
	   * Return a new {@link INode} with the given <code>locator</code> and
	   * <code>superclassLocator</code>
	   * @param locator
	   * @param superclassLocator
	   * @param label
	   * @param description
	   * @param lang
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isPrivate
	   * @return
	   */
	  INode newSubclassNode(String locator,String superclassLocator,String label, 
			  String description, String lang, String userId, String smallImagePath, 
			  String largeImagePath, boolean isPrivate);
	  
	  /**
	   * Return a new {@link INode} with the database-fabricated <code>locator</code>
	   * and given <code>superclassLocator</code>
	   * @param superclassLocator
	   * @param label
	   * @param description
	   * @param lang
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isPrivate
	   * @return
	   */
	  INode newSubclassNode(String superclassLocator,String label, String description, 
			  String lang, String userId, String smallImagePath, String largeImagePath, boolean isPrivate);
	  
	  /**
	   * Return a new {@link INode} with the given <code>locator</code> and
	   * given <code>typeLocator</code>
	   * @param locator
	   * @param typeLocator
	   * @param label
	   * @param description
	   * @param lang
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isPrivate
	   * @return
	   */
	  INode newInstanceNode(String locator,String typeLocator,String label, String description, 
			  String lang, String userId, String smallImagePath, String largeImagePath, boolean isPrivate);
	  
	  /**
	   * Return a new {@link INode} with the database-fabricated <code>locator</code>
	   * and given <code>typeLocator</code>
	   * @param typeLocator
	   * @param label
	   * @param description
	   * @param lang
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isPrivate
	   * @return
	   */
	  INode newInstanceNode(String typeLocator,String label, String description, String lang, 
			  String userId, String smallImagePath, String largeImagePath, boolean isPrivate);

	  
	  /**
	   * <p>Used for
	   * <li>Editing an existing node to change a label or details or both</li>
	   * <li>Adding or editing some language translation</li></p>
	   * <p>Note: this has been subject to a lot of development; seems rarely used in favor of local
	   * node surgical updates</p>
	   * @param nodeLocator
	   * @param updatedLabel  <code>null</code> if no change
	   * @param updatedDetails <code>null</code> if no change
	   * @param language
	   * @param oldLabel if <code>null</code> or "", this is a new label
	   * @param oldDetails if <code>null</code> or "", this is a new details
	   * @param userId
	   * @param isLanguageAddition <code>true</code> if is new translation
	   * @param credentials
	   * @return
	   */
	  IResult updateNode(String nodeLocator, String updatedLabel, String updatedDetails, String language, 
			  String oldLabel, String oldDetails, String userId, boolean isLanguageAddition, ITicket  credentials);
	  	  
	  /**
	   * <p>Perform a single, surgical change to a particular <code>key</code> (field)</p>
	   * <p>NOTE: appropriate to Solr 4+ and requires that {@link ISolrClient} uses
	   * the {@link XMLResponseParser}</p>
	   * @param node
	   * @param key
	   * @param newValue
	   * @return
	   */
	  IResult changePropertyValue(INode node, String key, String newValue);  
	  
	  /**
	   * <p>Perform surgery to a multi-valued <code>key</code> (field) by adding
	   * <code>newValue</code> to it</p>
	   * <p>NOTE: appropriate to Solr 4+ and requires that {@link ISolrClient} uses
	   * the {@link XMLResponseParser}</p>
	   * @param node
	   * @param key
	   * @param newValue
	   * @return
	   */
	  IResult addPropertyValueInList(INode node, String key, String newValue);
	  
	  /**
	   * <p>Form an {@link ITuple} between <code>sourceNodeLocator</code> and <code>targetNodeLocator</code></p>
	   * <p>The meaning of <code>isTransclude</code> is that <code>targetNodeLocator</code> is a
	   * <em>transcluded</code> {@link INode} when <code>isTransclude</code> is <code>true</code></p>
	   * <p>Note: internally, this must deal with restricted vs unrestricted nodes</p>
	   * @param sourceNodeLocator
	   * @param targetNodeLocator
	   * @param relationTypeLocator
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isTransclude
	   * @param isPrivate
	   * @return
	   */
	  IResult relateNodes(String sourceNodeLocator, String targetNodeLocator, String relationTypeLocator, 
			  String userId, String smallImagePath, String largeImagePath, boolean isTransclude, boolean isPrivate);
	  
	  /**
	   * <p>Form an {@link ITuple} between <code>sourceNode</code> and <code>targetNode</code></p>
	   * <p>This method is appropriate <em>only</em> to nodes which are in the database</p>
	   * @param sourceNode
	   * @param targetNode
	   * @param relationTypeLocator
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isTransclude
	   * @param isPrivate
	   * @return the locator of the created {@link ITuple}
	   */
	  IResult relateExistingNodes(INode sourceNode, INode targetNode, String relationTypeLocator, 
			  String userId, String smallImagePath, String largeImagePath, boolean isTransclude, boolean isPrivate);
	  
	  /**
	   * <p>Form an {@link ITuple} between <code>sourceNodeLocator</code> and <code>targetNodeLocator</code></p>
	   * <p>This method takes to <em>unsaved</em> nodes, relates them, and saves them after the relation is formed</p>
	   * @param sourceNode
	   * @param targetNode
	   * @param relationTypeLocator
	   * @param userId
	   * @param smallImagePath
	   * @param largeImagePath
	   * @param isTransclude
	   * @param isPrivate
	   * @return
	   */
	  IResult relateNewNodes(INode sourceNode, INode targetNode, String relationTypeLocator, 
			  String userId, String smallImagePath, String largeImagePath, boolean isTransclude, boolean isPrivate);
	 
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
	   * @param userLocator
	   * @return the locator of the created {@link ITuple}
	   */
	  IResult assertMerge(String sourceNodeLocator, String targetNodeLocator, 
			  Map<String, Double> mergeData, double mergeConfidence, String userLocator);
	  
	  /**
	   * Assert that the two nodes <em>might need to be merged</em> based on the
	   * collection of reasons and votes.
	   * @param sourceNodeLocator
	   * @param targetNodeLocator
	   * @param mergeData
	   * @param mergeConfidence
	   * @param userLocator
	   * @return
	   * NOTE: not yet implemented
	   */
	  IResult assertPossibleMerge(String sourceNodeLocator, String targetNodeLocator, 
			  Map<String, Double> mergeData, double mergeConfidence, String userLocator);

	  /**
	   * Assert that these two nodes must not be merged; they were before, but for reasons given,
	   * they should not be merged now.
	   * @param sourceNodeLocator
	   * @param targetNodeLocator
	   * @param mergeData
	   * @param mergeConfidence
	   * @param userLocator
	   * @return
	   *Note: not implemented yet
	   */
	  IResult assertUnmerge(String sourceNodeLocator, INode targetNodeLocator, 
			  Map<String, Double> mergeData, double mergeConfidence, String userLocator);
	  
	  /**
	   * Removes the identified node
	   * @param locator
	   * @return
	   */
	  IResult removeNode(String locator);
	  
	  /**
	   * Return a <em>weak</em> credentials based on <code>userId</code>
	   * @param userId
	   * @return
	   */
	  ITicket  getDefaultCredentials(String userId);
	  
	  
	  /**
	   * Take the opportunity to create a <em>NodeFactory</em> inside
	   * and recycle components
	   * @param node
	   */
	  void recycleNode(INode node);

}
