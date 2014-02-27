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
package org.topicquests.model.api.query;

import org.topicquests.model.api.IPredicate;
import org.topicquests.model.api.ITicket;
import org.topicquests.model.api.node.INode;
import org.topicquests.model.api.node.ITuple;
import org.topicquests.common.api.IResult;

/**
 * @author park
 * <p>Loosely based on the tinkerpop blueprints VertexQuery</p>
 */
public interface INodeQuery {
	
	/**
	 * Returns a List<ITuple> of tuples in this query after
	 * execution of filters
	 * @param credentials TODO
	 * @return
	 */
	IResult tuples(ITicket  credentials);
	
	/**
	 * Returns a List<INode> of nodes in this query after
	 * execution of filters
	 * @param credentials
	 * @return
	 */
	IResult nodes(ITicket  credentials);
	
	/**
	 *<p> Returns an {@link INodeQuery2} object based on the relationType
	 * of {@link ITuple} objects linked to this {@link INode}</p>
	 * <p>This <em>starts a query</em>; it can be iterated as necessary</p>
	 * @param relationType 
	 * @param start TODO
	 * @param count TODO
	 * @param credentials
	 * @return
	 */
	INodeQuery setRelationType(String relationType, int start, int count, ITicket  credentials);
	
	/**
	 * Returns a List<String> of {@link INode} locators at the opposite
	 * side of returned {@link ITuple} objects found in this query
	 * @param credentials TODO
	 * @return
	 */
	IResult nodeLocators(ITicket  credentials);
	
	/**
	 * Filter query based on found objects having a property 
	 * identified by <code>key</code> with a comparable <code>value>
	 * @param key
	 * @param value
	 * @return
	 */
	INodeQuery tupleHas(String key, Object value);
	
	/**
	 * Filter query based on found objects not having a property 
	 * identified by <code>key</code> with a comparable <code>value<code>
	 * @param key
	 * @param value
	 * @return
	 */
	INodeQuery tupleHasNot(String key, Object value);

	/**
	 * Filter query based on found objects having a property 
	 * identified by <code>key</code> and a comparable <code>value</code>
	 * as tested by <code>predicate</code>
	 * @param key
	 * @param predicate
	 * @param value
	 * @return
	 */
	INodeQuery tupleHas(String key, IPredicate predicate, Object value);
	
	/**
	 * <p>Return number of unfiltered {@link ITuple} objects returned from
	 * this query</p>
	 * <p>Cannot be used unless <code>setLabels</code> has been called</p>
	 * @return
	 */
	long count();

	INodeQuery tupleHas(String key);
	
	INodeQuery tupleHasNot(String key);
}
