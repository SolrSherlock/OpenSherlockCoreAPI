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
package org.topicquests.model.api.provider;

import java.io.Writer;
import java.util.Set;

import org.topicquests.common.api.IResult;
import org.topicquests.model.api.IMergeImplementation;
import org.topicquests.model.api.ITicket;
import org.topicquests.model.api.node.IAddressableInformationResource;
import org.topicquests.model.api.node.INode;
import org.topicquests.model.api.node.ITuple;
import org.topicquests.model.api.provider.IDataProvider;

/**
 * @author park
 * Factored pure topic map access out of IDataProvider
 */
public interface ITopicDataProvider extends IDataProvider {

	
	  /**
	   * <p>Install an {@link IMergeImplementation} in this system</p>
	   * <p>The implementation is declared in the <code>config.xml</code> file</p>
	   * @param merger
	   */
	  void setMergeBean(IMergeImplementation merger);
	  
	  /**
	   * Export the entire database to <code>out</code>
	   * @param out
	   * @param credentials
	   * @return
	   * @deprecated   WILL NOT SCALE -- not implemented
	   */
	  IResult exportXmlFile(Writer out, ITicket credentials);
	  
	  /**
	   * <p>Export a tree root and it's entire subtree.</p>
	   * <p>A subtree is defined as all related nodes; this gets rather complex
	   * because it calls for uprooting all tuples as well as nodes which are related
	   * to the given <code>treeRootLocator</code>.</p>
	   * <p>There is a risk that this method will not scale due to really rich graphs
	   * growing up around some root.</p>
	   * <p>There is a risk that this method, at larger graphs, will need to be threaded
	   * in order to prevent blocking normal operation of the server.</p>
	   * <p>When we enter with <code>treeRootLocator</code>, we do not explore and export
	   * any parent nodes. If this node is a subclass, or instance of some type or parent,
	   * we do not rise above and export those.</p>
	   * @param treeRootLocator
	   * @param out
	   * @param credentials
	   * @return
	   */
	  IResult exportXmlTreeFile(String treeRootLocator, Writer out, ITicket credentials);
	
	  /**
	   * <p>Fetch a node. <code>credentials</code> are required in case
	   * the node is private and a credential must be tested</p>
	   * <p>Error message will be returned if the node is private and insufficient
	   * credentials are presented</p>
	   * <p>Returns <code>null</code> as the result object if there is no node or
	   * if credentials are insufficient</p>
	   * @param locator
	   * @param credentials
	   * @return
	   */
	  IResult getNode(String locator, ITicket credentials);
	  
	  /**
	   * Returns a raw {@link INode} as a {@link JSONObject}
	   * @param locator
	   * @param credentials
	   * @return
	   */
	  IResult getNodeJSON(String locator, ITicket credentials);
	  
	  /**
	   * Assemble a node view based on the node and its various related nodes
	   * @param locator
	   * @param credentials
	   * @return
	   */
	  IResult getNodeView(String locator, ITicket credentials);
	  
	  /**
	   * <p>Remove a node from the database</p>
	   * <p>This is used for all nodes and tuples</p>
	   * @param locator
	   * @param credentials
	   * @return
	   */
	  IResult removeNode(String locator, ITicket credentials);
	  
	  /**
	   * Remove <code>node</code>
	   * @param node
	   * @param credentials
	   * @return
	   */
	  IResult removeNode(INode node, ITicket credentials);

	  /**
	   * <p>Put <code>node</code> in the database. Subject it to merge and harvest</p>
	   * <p>Can return an <em>OptimisticLockException</em> error message if version numbers
	   * are not appropriate.</p>
	   * @param node
	   * @param checkVersion TODO
	   * @return
	   */
	  IResult putNode(INode node, boolean checkVersion);
	  
	  /**
	   * Put <code>node</code> in the database. Subject to harvest; no merge performed
	   * @param node
	 * @param checkVersion TODO
	   * @return
	   */
	  IResult putNodeNoMerge(INode node, boolean checkVersion);
	  
	  
	  /**
	   * <p>Original (version 1) instances of {@link IAddressableInformationResource}
	   * are stored with the topic to which they belong. When an <em>AIR</em> is edited,
	   * a new <em>version is created, and that one replaces the original with the owning
	   * topic. The original version is then persisted here.</p>
	   * <p>Internally, the system will store <em>AIRs</em> in a separate index with an
	   * identifier composed of the AIR's <code>locator</code> plus its <code>version</code></p>
	   * <p>It is the responsibility of a user, when creating a new version, to take the version
	   * of the original <em>AIR</em> and bump it to the next version number for the new instance.
	   * Typically, an <em>AIR</em> is versioned for reasons of edit changes to the text it contains.</p>
	   * @param air
	 * @param checkVersion TODO
	   * @return
	   */
//	  IResult putAIRVersion(IAddressableInformationResource air, boolean checkVersion);
	  
	  /**
	   * Return a specific <code>version</code> of an {@link IAddressableInformationResource}
	   * @param airLocator
	   * @param version
	   * @param credentials
	   * @return
	   */
//	  IResult getAIRVersion(String airLocator, int version, ITicket credentials);
	  
	  /**
	   * List all version numbers of {@link IAddressableInformationResource} identified by
	   * <code>airLocator</code>. Will return <code>null</code> inside {@link IResult} 
	   * if no versions exist.
	   * @param airLocator
	   * @return
	   */
//	  IResult listAIRVersions(String airLocator);
	  
	  /**
	   * <p>If <code>node</code> is a <em>merged node</em>, then
	   * return the <em>virtual node</em> which represents it. Otherwise,
	   * return <code>null</code> inside {@link IResult}
	   * @param node
	   * @param credentials
	   * @return
	   */
	  IResult getVirtualNodeIfExists(INode node, ITicket credentials);
	  
	  /**
	   * Returns a Boolean <code>true</code> if there exists an {@link ITuple} of 
	   * <code>relationLocator</code> and
	   * either a <em>subject</em> or </em>object</em> identified by <code>theLocator</code>
	   * @param theLocator
	   * @param relationLocator
	   * @return
	   */
	  IResult existsTupleBySubjectOrObjectAndRelation(String theLocator, String relationLocator);
	  
	  /**
	   * Returns a Boolean <code>true</code> if an {@link INode} exists for the given
	   * <code>locator<?code>
	   * @param locator
	   * @return
	   */
	  IResult existsNode(String locator);

	  /**
	   * <p>Tests whether <code>nodeLocator</code> is of type or a subclass of 
	   * <code>targetTypeLocator</code></p>
	   * @param nodeLocator
	   * @param targetTypeLocator
	   * @param credentials
	   * @return
	   */
	  IResult nodeIsA(String nodeLocator, String targetTypeLocator, ITicket credentials);
	  
	  /**
	   * <p>List nodes associated with <code>psi</code></p>
	   * <p>Note: a <code>psi</code> is theoretically a <em>unique</em> identifier
	   * for a node; there shoule be just one node returned, if any.</p>
	   * @param psi
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listNodesByPSI(String psi, int start, int count, ITicket credentials);
	  
	  /**
	   * <p>List nodes by the combination of a <code>label</code> and <code>typeLocator</code></p>
	   * @param label
	   * @param typeLocator
	   * @param language
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listNodesByLabelAndType(String label, String typeLocator,String language, int start, int count, ITicket credentials);
	  
	  /**
	   * <p>List nodes by <code>label</code></p>
	   * @param lagel
	   * @param language 
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listNodesByLabel(String label,String language, int start, int count, ITicket credentials);
	  
	  /**
	   * <p>Return nodes with labels that are <em>like</em> <code>labelFragment</code></p>
	   * <p>A <em>wildcard</em> is added before and after <code>labelFragment</code></p>
	   * <p>Example: given the string "My favorite topic"; would be matched with My, favorite, or topic</p>
	   * <p>Results are case sensitive</p>
	   * @param labelFragment
	   * @param language 
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listNodesByLabelLike(String labelFragment, String language, int start, int count, ITicket credentials);
	  
	  /**
	   * <p>Return nodes with details that are <em>like</em> <code>detailsFragment</code></p>
	   * @param detailsFragment
	   * @param language 
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listNodesByDetailsLike(String detailsFragment, String language, int start, int count, ITicket credentials);
	  
	  /**
	   * Answer a particular Solr query string
	   * @param queryString
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listNodesByQuery(String queryString,int start, int count, ITicket credentials);
	  
	  /**
	   * Return nodes created by <code>creatorId</code>
	   * @param creatorId
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listNodesByCreatorId(String creatorId, int start, int count, ITicket credentials);
	  
	  /**
	   * Return nodes of type <code>typeLocator</code>
	   * @param typeLocator
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listNodesByType(String typeLocator,int start, int count, ITicket credentials);
	  
	  /**
	   * List all {@link ITuple} objects with <code>signature</code>
	   * @param signature
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listTuplesBySignature(String signature, int start, int count, ITicket credentials);
	  
	  /**
	   * Really, this is the same as <code>listNodesByType</code>
	   * @param typeLocator
	   * @param start
	   * @param count
	   * @param credentials
	   * @return a list of [@link INode} objects or <code>null</code>
	   */
	  IResult listInstanceNodes(String typeLocator, int start, int count, ITicket credentials);
	  
	  /**
	   * <p>List nodes by type, except if any nodes are merged, do not list them. All virtual nodes
	   * will be listed</p>
	   * @param typeLocator
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listTrimmedInstanceNodes(String typeLocator, int start, int count, ITicket credentials);
	  
	  /**
	   * List nodes which are subclasses of <code>superclassLocator</code>
	   * @param superclassLocator
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listSubclassNodes(String superclassLocator, int start, int count, ITicket credentials);

	  ////////////////////////////////////
	  //Tuple support
	  ///////////////////////////////////	  
	  
	  /**
	   * 
	   * @param tuple
	 * @param checkVersion TODO
	   * @return
	   */
	  IResult putTuple(ITuple tuple, boolean checkVersion);
	  
	  /**
	   * Return an <code>ITuple</code> inside an {@link IResult} object or <code>null</code> if not found
	   * @param tupleLocator
	   * @param credentials
	   * @return -- an IResult object that contains either an ITuple or an error message
	   */
	  IResult getTuple(String tupleLocator, ITicket credentials);
	  
	  	  
	  /**
	   * Behaves as if to <em>replace</em> <code>node</code>
	   * @param node
	 * @param checkVersion TODO
	   * @return
	   */
	  IResult updateNode(INode node, boolean checkVersion);

	  //////////////////////////////////////////////////
	  // General query support
	  //////////////////////////////////////////////////
	  /**
	   * <p>Note: <code>queryString</code> is composed of various elements
	   * which take the form <code>field:stuff</code> where stuff could be
	   * in the form of text to find, e.g. "over the rainbow".  In the case
	   * of text to find, that text must be escaped by <code>QueryUtil.escapeQueryCulprits(...)</code></p>
	   * @param queryString
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult runQuery(String queryString, int start, int count, ITicket credentials);

}
