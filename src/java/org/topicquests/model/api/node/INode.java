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
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.topicquests.model.api.node.IRelationStruct;
import org.topicquests.model.api.node.IChildStruct;
//import org.json.simple.JSONObject;
import org.topicquests.common.api.IResult;

/**
 * @author park
 *
 */
public interface INode {

	
	/**
	 * Do a single update event when changes have been made as compared to
	 * individual updates. This method would update LastEditDate
	 * @return
	 */
	IResult doUpdate();
	
	/**
	 * Calculate a property key (field) from language for
	 * either labels or details
	 * @param fieldBase
	 * @param language
	 * @return
	 */
	String makeField(String fieldBase, String language);
	
	/**
	 * Perform a simple test on nodeType and superClasses
	 * @param typeLocator
	 * @return
	 */
	boolean localIsA(String typeLocator);
	
	/**
	 * Locator is the identifier for this tuple
	 * @param tupleLocator
	 */
	void setLocator(String tupleLocator);
	
	/**
	 * Return this node's locator
	 * @return
	 */
	String getLocator();

	/**
	 * Set creatorId
	 * @param id
	 */
	void setCreatorId(String id);
	
	/**
	 * Return creatorId
	 * @return
	 */
	String getCreatorId();
	
	/**
	 * <p>A node can have one and only one merge tuple if it has been merged</p>
	 * <p>A VirtualNode can have many merge tuples</p>
	 * @param locator
	 */
	void addMergeTupleLocator(String locator);
	
	/**
	 * Can return <code>null</code>
	 * @return
	 */
	String getMergeTupleLocator();
	
	/**
	 * For VirtualNodes
	 * @return
	 */
	List<String> listMergeTupleLocators();
	
	/**
	 * Return <code>true</code> if this {@link INode} is a {@link ITuple}
	 * @return
	 */
	boolean isTuple();

	/**
	 * SOLR4 does a <code>_version_</code> field
	 * @param version
	 */
	void setVersion(String version);
	
	/**
	 * Return the SOLR4 version as a String
	 * @return
	 */
	String getVersion();
	
	/**
	 *  YYYY-MM-DDThh:mm:ssZ; this is the createdDate
	 * @param date
	 */
	void setDate(Date date);
	
	/**
	 * Internally, we need to set two dates when creating a node.
	 * One is the creation date, the other is lastEdit date. By
	 * allowing for a single {@link Date} to {@link String} conversion
	 * we save cycles.
	 * @param date
	 */
	void setDate(String date);
	
	/**
	 * Return createdDate
	 * @return
	 */
	Date getDate();
	
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param date
	 */
	void setLastEditDate(Date date);
	
	void setLastEditDate(String date);
	/**
	 * Return the lastEditDate
	 * @return
	 */
	Date getLastEditDate();
	
    /**
     * Solr expects a {@link Map}
     * @return
     */
	Map<String,Object> getProperties();

	/**
	 * Convert this node to a JSON String
	 * @return
	 */
	String toJSON();
	
	/**
	 * Return this node expressed in XML
	 * @return
	 */
	String toXML();
	
	//////////////////////////////////////////////
	//At the moment, it's not clear what "federated" means:
	// We can easily tell if the node has been merged by 
	// testing its mergeTuple property
	//////////////////////////////////////////////
	/**
	 * If a node has been federated, set to <code>true</code>; default return if <code>false</code>
	 * @param t
	 */
	void setIsFederated(boolean t);
	
	boolean getIsFederated();
	
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * Labels can come in many languages, or also be synonyms, acronyms, etc
	 * @param label
	 * @param language
	 * @param userId can be <code>null</code> if not an update
	 * @param isLanguageAddition almost always false
	 */
	void addLabel(String label, String language, String userId, boolean isLanguageAddition);
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * Small label is limited to 70 characters, as found in http://debategraph.org/
	 * @param label
	 * @param language
	 * @param userId
	 * @param isLanguageAddition
	 */
	void addSmallLabel(String label, String language, String userId, boolean isLanguageAddition);
	  
	/**
	 * Will return the <em>first</em>label for the given <code>language</code>
	 * but if that <code>language</code> does not exist, returns the first label
	 * @param language
     * @return can return "" empty string by default
	 */
	String getLabel(String language);
	  
	/**
	 * Return first label if many
	 * @param language
	 * @return can return "" if none found
	 */
	String getSmallLabel(String language);
	  
	/**
	 * List all labels
	 * @return
	 */
	List<String> listLabels();
	  
	/**
	 * List labels for given <code>language</code>
	 * @param language
	 * @return
	 */
	List<String> listLabels(String language);
	  
	/**
	 * List all small labels
	 * @return
	 */
	List<String> listSmallLabels();
	  
	/**
	 * List small labels for <code>language</code>
	 * @param language
	 * @return
	 */
	List<String> listSmallLabels(String language);
	  
	/**
	 * Details can come in many languages
	 * @param details
	 * @param language
	 * @param userId can be <code>null</code> if not an update
	 * @param isLanguageAddition almost always false
	 */
	void addDetails(String details, String language, String userId, boolean isLanguageAddition);

	/**
	 * Will return the <em>first</em>details for the given <code>language</code>
	 * but if that <code>language</code> does not exist, returns the first details
	 * @param language
	 * @return can return "" empty string by default
	 */
	String getDetails(String language);

	/**
	 * List all details; ignores language codes
	 * @return
	 */
	List<String> listDetails();
	  
	/**
	 * List details for <code>language</code>; returns first entry if many
	 * @param language
	 * @return
	 */
	List<String> listDetails(String language);
	  	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * Images are really icons, not pictures
	 * @param img can be <code>null</code>
	 * <em>should not be an empty string</em>
	 */
	void setSmallImage(String img);
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param img can be <code>null</code>
	 * <em>should not be an empty string</em>
	 */
	void setImage(String img);
	  
	/**
	 * 
	 * @return can return <code>null</code>
	 */
	String getSmallImage();
	  
	/**
	 * 
	 * @return can return <code>null</code>
	 */
	String getImage();

	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param typeLocator
	 */
	void setNodeType(String typeLocator);
	  
	/**
	 * 
	 * @return can return <code>null</code>
	 */
	String getNodeType();

	/**
	 * A <em>VirtualProxy</em> is an {@link INode} which serves as a <em>hub</em>
	 * in a collection of nodes which have been merged.
	 * @param t
	 */
	void setIsVirtualProxy(boolean t);
	  
	/**
	 * <p>Return <code>true</code> if this is a "virtual proxy"</p>
	 * <p>Virtual proxies are created when two nodes are merged: the virtual proxy
	 * is created to represent both, or all so-merged nodes; each merged node is then
	 * linked with a "merge assertion" to the virtual proxy.</p>
	 * @return
	 */
	boolean getIsVirtualProxy();
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param superclassLocator
	 */
	void addSuperclassId(String superclassLocator);
	  
	/**
	 * <p>A generic method<p>
	 * <em>Risks overwriting a property</em>
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param key
	 * @param value one of <code>String</code> or <code>List<String></code>
	 */
	void setProperty(String key, Object value);
	  
	/**
	 * <p>Utility method. The first time a <code>value</code> is
	 * added to this <code>key</code>, it goes in as a String. The next time,
	 * the property will be converted to a <code>List</code></p>
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param key
	 * @param value
	 */
	void addPropertyValue(String key, String value);
	
	/**
	 * <p>If property is still a String, it is removed entirely</p>
	 * <p>If it is a list, value is removed from the list.</p>
	 * @param key
	 * @param value
	 * @return
	 */
	boolean removePropertyValue(String key, String value);
	  
	/**
	 * Returns one of <code>String</code> or <code>List<String></code>
	 * @param key
	 * @return can return <code>null</code>
	 */
	Object getProperty(String key);
	  
	/**
	 * @return can return <code>null</code>
	 */
	List<String> listSuperclassIds();
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param isPrivate
	 */
	void setIsPrivate(boolean isPrivate);
	  
	/**
	 * Utility
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param t
	 */
	void setIsPrivate(String t);
	  
	/**
	 * Defaults to <code>false</code>
	 * @return
	 */
	boolean getIsPrivate();
	
	/**
	 * <p>Intended thus:
	 * <li><code>t</code> = <code>true</code> only in the case where
	 * a node was set to <code>false</code> before</li>
	 * <li><code>t</code> = <code>false</code> to mean that this node
	 * has been <em>deleted</code></li>
	 * </p>
	 * @param t
	 */
	void setIsLive(boolean t);
	
	/**
	 * Defaults <code>true</code> if the value has not been set.
	 * @return
	 */
	boolean getIsLive();
	
	/**
	 * Return <code>true</code> if <code>typeLocator</code> is found in 
	 * this node's <em>transitive closure</em>
	 * @param typeLocator
	 * @return
	 */
	boolean isA(String typeLocator);

	  
	////////////////REPLACED BY ACLs
	/**
	 * 
	 * @return does not return <code>null</code>
	 * /
	List<String> listRestrictionCredentials();
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param userId
	 * /
	void addRestrictionCredential(String userId);
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param userId
	 * /
	void removeRestrictionCredential(String userId);
	  
	/**
	 * 
	 * @param userId
	 * @return
	 * /
	boolean containsRestrictionCredentials(String userId);
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * @param url
	 */
	void setURL(String url);
	  
	/**
	 * Return the URL if this node represents a WebResource
	 * @return can return <code>null</code>
	 */
	String getURL();
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * <p>A node can be <em>tagged</em> with Published Subject Indicator values.</p>
	 * <p>If one remains faithful to the topic mapping standards, a node (topic) can
	 * have just <em>one</em> PSI; in work outside topic mapping, it is possible for
	 * a node to have more than one PSI.</p>
	 * @param psi
	 */
	void addPSI(String psi);
	  
	/**
	 * 
	 * @return does not return <code>null</code>
	 */
	List<String> listPSIValues();
	/////////////////////////////
	// Transitive Closure
	/////////////////////////////
	/**
	 * Does not return <code>null</code>. Can return an empty list
	 * for the root node
	 * @return
	 */
	List<String> listTransitiveClosure();
	
	void setTransitiveClosure(List<String>tc);
	
	void addTransitiveClosureLocator(String locator);
	
	void removeTransitiveClosureLocator(String locator);

	/////////////////////////////
	//Tuples
	/////////////////////////////

	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * Add an unrestricted {@link ITuple} to this node
	 * @param relationTypeLocator TODO
	 * @param relationLocator TODO
	 * @param relationLabel TODO
	 * @param targetSmallIcon TODO
	 * @param targetLocator TODO
	 * @param targetLabel TODO
	 * @param sourceOrTarget "t" if targetNode is the target in the relation, "s" otherwise
	 * @param transcluderId can be <code>null</code>
	 */
	void addRelation(String relationTypeLocator,  String relationLocator, String relationLabel, String targetSmallIcon, 
			String targetLocator, String targetLabel, String nodeType, String sourceOrTarget);
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * Add a restricted (not public) {@link ITuple} to this node
	 * @param relationTypeLocator TODO
	 * @param relationLocator TODO
	 * @param relationLabel TODO
	 * @param targetSmallIcon TODO
	 * @param targetLocator TODO
	 * @param targetLabel TODO
	 * @param sourceOrTarget TODO
	 * @param transcluderId TODO
	 */
	void addRestrictedRelation(String relationTypeLocator, String relationLocator, String relationLabel, 
			String targetSmallIcon, String targetLocator, String targetLabel, String nodeType, String sourceOrTarget);
	  
	/**
	 * List tuples linked to this node which are unrestricted: returns JSON
	 * String representation of {@link IRelationStruct}
	 * @param relationType if <code>null</code>, returns all relations as JSON strings
	 * @return does not return <code>null</code>
	 */
	List<String> listRelationsByRelationType(String relationType);
	  
	/**
	 * List tuples linked to this node which are restricted (not public)
	 * @param relationType if <code>null</code>, returns all relations
	 * @return does not return <code>null</code>
	 */
	List<String> listRestrictedRelationsByRelationType(String relationType);
	
	/////////////////////////////
	//Pivots: special kinds of tuples
	/////////////////////////////
	void addPivot(String relationTypeLocator, String relationLocator,
			String relationLabel,String documentSmallIcon, String targetLocator, 
			String targetLabel, String nodeType, String sourceOrTarget);
	  
	void addRestrictedPivot(String relationTypeLocator, String relationLocator,
			String relationLabel,String documentSmallIcon, String targetLocator, String targetLabel, String nodeType, String sourceOrTarget);
	/**
	 * <p>Returns all if <code>relationType</code> = <code>null</code></p>
	 * <p>JSONObject behaves like {@link IRelationStruct}</p>
	 * @param relationType
	 * @return returns a list of strings which are JSON representatinos of {@link IRelationStruct}
	 */
	List<String> listPivotsByRelationType(String relationType);
	
	List<String> listRestrictedPivotsByRelationType(String relationType);
	/////////////////////////////
	//Graph
	/////////////////////////////
	
	/**
	 * 
	 * @param contextLocator
	 * @param smallIcon
	 * @param locator
	 * @param subject
	 * @param transcluderLocator can be <code>null</code>
	 */
	void addChildNode(String contextLocator, String smallIcon, String locator, String subject, String transcluderLocator);
	
	/**
	 * <p>Returns list of childNode objects.<p>
	 * <p>If <code>contextLocator</code> = <code>null</code>, returns
	 * all objects</p>
	 * <p>JSONObjects returned behave according to {@link IChildStruct} keys
	 * @param contextLocator <code>null</code> means list all child nodes
	 * @return a list of JSON strings which are representations of {@link IChildStruct}
	 */
	List<String> listChildNodes(String contextLocator);
	
	void addParentNode(String contextLocator, String smallIcon, String locator, String subject);
	
	/**
	 * Return list of parentNode objects
	 * @param contextLocator <code>null</code> means return all parent nodes
	 * @return a list of JSON strings which are representations of {@link IChildStruct}
	 */
	List<String> listParentNodes(String contextLocator);
	
	/////////////////////////////
	//ACL
	/////////////////////////////

	void addACLValue(String value);
	
	void removeACLValue(String value);
	
	List<String>listACLValues();
	
	boolean containsACL(String value);
	
	/////////////////////////////
	//InfoBox
	// JSONObjects for InfoBoxes behave according to user-specified
	// microformats
	/////////////////////////////

	void putInfoBox(IInfoBox infoBox);
	
	/**
	 * Returns a JSONString representation of an infobox
	 * @param name
	 * @return can return <code>null</code>
	 */
	String getInfoBox(String name);
	
	void removeInfoBox(String name);
	
	/**
	 * Return the list of all IInfoBox JSON objects
	 * @return
	 */
	List<String> listInfoBoxes();
	
	/////////////////////////////
	//AIRs
	// These are different from ordinary label/details
	// Instead, they are subject/body -- with language concerns
	//  And, they are versioned
	/////////////////////////////

	/**
	 * An AIR has one and only one Subject, though can exist in different languages
	 * @param subjectString
	 * @param language
	 * @param userLocator
	 */
	void setSubject(String subjectString, String language, String userLocator);
	
	String getSubject(String language);
	
	void updateSubject(String updatedSubject, String language, String userLocator, String comment);
	
	void setBody(String bodyString, String language, String userLocator);
	
	String getBody(String language);
	
	void updateBody(String updatedBody, String language, String userLocator, String comment);
	
	List<String> listBodyVersions(String language);
	
	List<String> listSubjectVersions(String language);
}
