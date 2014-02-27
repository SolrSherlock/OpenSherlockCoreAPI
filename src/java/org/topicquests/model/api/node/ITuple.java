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

/**
 * @author park
 * <p>A Tuple is a container for any kind of triple. This means
 * that we need to know several things:
 * <li>Locator (identity)</li>
 * <li>Subject Locator</li>
 * <li>Subject type -- could be node or another tuple, which means a tuple needs identity</li>
 * <li>Predicate locator, which is the <em>type</em> locator for the tuple</li>
 * <li>Object type</li>
 * <li>Object locator or object value, depending on type</li></p>
 * <p>A Tuple is an object persisted, and supports queries of the relational type</p>
 * <p>Tuples represent virtually everything except taxonomic/partonomic relations, such as
 * tree structures in IBIS conversations, any other graph-type relations which include
 * all associations in a topic map</p>
 */
public interface ITuple extends INode {
	
	
	/**
	 * Set relational weight
	 * @param weight
	 */
	void setRelationWeight(double weight);
	
	/**
	 * Can return <code>-9999</code> if this {@link ITuple} is not weighted
	 * @return
	 */
	double getRelationWeight();
	
	/**
	 * <p>Object refers to the object of a {subject,predicate,object} triple.</p>
	 * <p>An object could be one of
	 * <li>A literal (typed) value</li>
	 * <li>A symbol, typically an identifier of another entity which could be one of
	 * <li>Another tuple</li>
	 * <li>A node</li></li></p>
	 * @param value
	 */
	void setObject(String value);
	
	/**
	 * Set the object's type
	 * @param typeLocator
	 */
	void setObjectType(String typeLocator);
	
	/**
	 * Return the object
	 * @return
	 */
	String getObject();
	
	/**
	 * Return the object's type
	 * @return
	 */
	String getObjectType();
	
	/**
	 * Set the object's role
	 * @param roleLocator
	 */
	void setObjectRole(String roleLocator);
	
	/**
	 * Return the object's role
	 * @return can return <code>null</code>
	 */
	String getObjectRole();
	
	/**
	 * <p>A subject is the subject in a {subject,predicate,object} triple</p>
	 * <p>A subject is always the locator (identifier) for another entity, which could be one of
	 * <li>A node</li>
	 * <li>A tuple</li></p>
	 * @param locator
	 */
	void setSubjectLocator(String locator);
	
	/**
	 * Return the subject locator
	 * @return
	 */
	String getSubjectLocator();
	
	/**
	 * SubjectType refers to whether this subject is an ITuple type or some other
	 * type in the typology
	 * @param subjectType
	 */
	void setSubjectType(String subjectType);
	
	/**
	 * Return the subject's type
	 * @return
	 */
	String getSubjectType();
	
	/**
	 * Roles are appropriate to relations among role-playing actors
	 * @param roleLocator
	 */
	void setSubjectRole(String roleLocator);
	
	/**
	 * Return the subject/s role
	 * @return can return <code>null</code>
	 */
	String getSubjectRole();
	
	/**
	 * Transclude means this tuple is transcluded from another source
	 * @param isT
	 */
	void setIsTransclude(boolean isT);
	
	/**
	 * <p>A <em>signature</em> is a large string composed of:
	 * subjectLocator + tupleType + objectLocator</p>
	 * <p>It is intended to be used on tuples which link nodes</p>
	 * @param signature
	 */
	void setSignature(String signature);
	
	/**
	 * Return this tuple's signature
	 * @return can return <code>null</code>
	 */
	String getSignature();
	
	/**
	 * Utility
	 * @param t
	 */
	void setIsTransclude(String t);
	
	/**
	 * Return <code>true</code> if this tuple represents a transcluded node
	 * @return
	 */
	boolean getIsTransclude();
	
	/**
	 * Scopes are topics
	 * @param scopeLocator
	 */
	void addScope(String scopeLocator);
	
	/**
	 * Add a merge reason given by fired merge rules
	 * @param reason
	 */
	void addMergeReason(String reason);
	
	/**
	 * Return merge reasons
	 * @return can return <code>null</code>
	 */
	List<String> listMergeReasons();
	
	/**
	 * 
	 * @return does not return <code>null</code>
	 */
	List<String> listScopes();
	
	/**
	 * <p>A tuple can be used to represent a scene in which something occurs in a transaction.</p>
	 * <p>Example: Mary gave a ball to Joe, where the <em>ball</em> is the theme in a <em>give</em>
	 * transaction between two parties, Mary and Joe</p>
	 * @param themeLocator
	 */
	void setThemeLocator(String themeLocator);
	
	/**
	 * 
	 * @return can return <code>null</code>
	 */
	String getThemeLocator();
}
