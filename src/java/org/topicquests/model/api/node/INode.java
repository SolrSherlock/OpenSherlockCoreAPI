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

import org.json.simple.JSONObject;
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
	 * @param tupleLocator
	 */
	void addTuple(String tupleLocator);
	  
	/**
	 * <p>Used while building, not while modifying after stored.</p>
	 * <p>To use after node is stored, must pay attention to update methods</p>
	 * Add a restricted (not public) {@link ITuple} to this node
	 * @param tupleLocator
	 */
	void addRestrictedTuple(String tupleLocator);
	  
	/**
	 * List tuples linked to this node which are unrestricted
	 * @return does not return <code>null</code>
	 */
	List<String> listTuples();
	  
	/**
	 * List tuples linked to this node which are restricted (not public)
	 * @return does not return <code>null</code>
	 */
	List<String> listRestrictedTuples();
	
	/////////////////////////////
	//Pivots: special kinds of tuples
	/////////////////////////////
	void addPivot(String relationType, String relationLabel,
			String documentSmallIcon,String targetLocator, String targetLabel, String nodeType);
	  
	/**
	 * <p>Returns all if <code>relationType</code> = <code>null</code></p>
	 * <p>JSONObject behaves like {@link IRelationStruct}</p>
	 * @param relationType
	 * @return
	 */
	List<JSONObject> listPivotsByRelationType(String relationType);
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
	 * @param contextLocator
	 * @return
	 */
	List<JSONObject> listChildNodes(String contextLocator);
	
	void addParentNode(String contextLocator, String smallIcon, String locator, String subject);
	
	List<JSONObject> listParentNodes(String contextLocator);
	
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

	void putInfoBox(String name, String jsonString);
	
	JSONObject getInfoBox(String name);
	
	void removeInfoBox(String name);
	
	JSONObject getInfoBoxes();
	
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
