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
package org.topicquests.model.api.query;

import org.topicquests.common.api.IResult;
import org.topicquests.model.api.ITicket;
import org.topicquests.model.api.node.INode;
import org.topicquests.model.api.node.ITuple;

/**
 * @author park
 * <p>Common Queries to be implemented for different databases</p>
 */
public interface ITupleQuery {
	
	/**
	 * <p>List all available {@link ITuple}s which are instances of <code>predType</code>
	 * and have <code>obj</code> as either the subject or object</p>
	 * @param predType
	 * @param obj
	 * @param start
	 * @param count
	 * @param credentials
	 * @return
	 */
	IResult listTuplesByPredTypeAndObjectOrSubject(String predType, String obj, int start, int count, ITicket credentials);
	
	/**
	 * <p>List locators of all available {@link ITuple}s which have any of the <code>labels</code></p>
	 * @param labels
	 * @param start
	 * @param count
	 * @param credentials 
	 * @return
	 */
	IResult listTuplesByLabel(String [] labels, int start, int count, ITicket credentials);
	
	/**
	 * 
	 * @param subjectLocator
	 * @param start
	 * @param count
	 * @param credentials
	 * @return
	 */
	IResult listTuplesBySubject(String subjectLocator, int start, int count, ITicket credentials);

	/**
	 * For tuples where the object is a nodeLocator, list all of them
	 * @param objectLocator
	 * @param start 
	 * @param count 
	 * @param credentials
	 * @return
	 */
	IResult listTuplesByObjectLocator(String objectLocator, int start, int count, ITicket credentials);
	
	
	  /**
	   * <p>Return a list of <code>ITuple</code> objects inside an {@link IResult} object</p>
	   * <p>This is the core way to fetch an list of <code>ITuple</code> object when
	   * the desired result is to learn all the <code>subjectId</code> values that contain that
	   * key/value pair.</p>
	   * @param predType
	   * @param obj
	   * @param start
	   * @param count
	   * @param credentials
	   * @return -- an IResult object that contains List[ITuple] or an error message
	   */
	  IResult listTuplesByPredTypeAndObject(String predType, String obj, int start, int count, ITicket credentials);
	
	  /**
	   * <p>Return a list of <code>ITuple</code> objects inside an {@link IResult} object</p>
	   * <p>This is the core way to fetch an list of <code>ITuple</code> objects</p>
	   * @param subjectLocator
	   * @param predType
	   * @param start
	   * @param count
	   * @param credentials
	   * @return
	   */
	  IResult listTuplesBySubjectAndPredType(String subjectLocator, String predType, int start, int count, ITicket credentials);
	/**
	 * <p>Return a possibly empty list of {@link INode} objects which correspond
	 * with <code>objectLocator</code> by relation <code>relationLocator</code></p>
	 * @param objectLocator
	 * @param relationLocator
	 * @param start 
	 * @param count 
	 * @param credentials
	 * @return <code>List<INode></code>
	 */
	IResult listSubjectNodesByObjectAndRelation(String objectLocator, String relationLocator, int start, int count, ITicket credentials);
	
	/**
	 * <p>Return a possibly empty list of {@link INode} objects which correspond with
	 * <code>subjectLocator</code> by relation <code>relationLocator</code></p>
	 * @param subjectLocator
	 * @param relationLocator
	 * @param start 
	 * @param count 
	 * @param credentials
	 * @return <code>List<INode></code>
	 */
	IResult listObjectNodesBySubjectAndRelation(String subjectLocator, String relationLocator, int start, int count, ITicket credentials);
	
	
	/**
	 * <p>Return a possibly empty list of {@link INode} objects which correspond with
	 * <code>subjectLocator</code> by relation <code>relationLocator</code>, and includes the scope <code>scopeLocator</code></p>
	 * @param subjectLocator
	 * @param relationLocator
	 * @param start 
	 * @param count 
	 * @param credentials
	 * @return <code>List<INode></code>
	 */
	IResult listObjectNodesBySubjectAndRelationAndScope(String subjectLocator, String relationLocator, String scopeLocator, int start, int count, ITicket credentials);
	
	/**
	 * <p>Return a possibly empty list of {@link INode} objects which correspond
	 * with <code>objectLocator</code> by relation <code>relationLocator</code></p>
	 * @param objectLocator
	 * @param relationLocator
	 * @param start 
	 * @param count 
	 * @param credentials
	 * @return <code>List<INode></code>
	 */
	IResult listSubjectNodesByObjectAndRelationAndScope(String objectLocator, String relationLocator, String scopeLocator, int start, int count, ITicket credentials);
	
	IResult listSubjectNodesByRelationAndObjectRole(String relationLocator, String objectRoleLocator, int start, int count, ITicket credentials);
	
	IResult listSubjectNodesByRelationAndSubjectRole(String relationLocator, String subjectRoleLocator, int start, int count, ITicket credentials);
	
	IResult listObjectNodesByRelationAndSubjectRole(String relationLocator, String subjectRoleLocator, int start, int count, ITicket credentials);
	
	IResult listObjectNodesByRelationAndObjectRole(String relationLocator, String objectRoleLocator, int start, int count, ITicket credentials);
	//TODO fetching tuples which weight criteria
}
