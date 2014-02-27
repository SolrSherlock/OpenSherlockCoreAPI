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

import org.topicquests.common.api.IResult;

import java.util.Set;
import java.util.List;

import org.topicquests.model.api.ITicket;
import org.topicquests.model.api.node.INode;

/**
 * @author park
 * <p>A QueryIterator is initialized with:
 * <li>The query string</li>
 * <li>The number of hits requested per iteration</li></p>
 * <p>Once initialized, then queries to <code>next</code> will
 * start the process</p>
 * <p>Note, some other implementations, e.g. Solr, require a different
 * INode so this is an optional class</p>
 */
public interface IQueryIterator {

	/**
	 * Will initialize the iterator
	 * @param queryString
	 * @param hitCount
	 * @param credentials
	 */
	void start(String queryString, int hitCount, ITicket  credentials);
	
	/**
	 * Set the cursor back to 0
	 */
	void reset();
	
	/**
	 * Fetch the next number of hits
	 * @return a {@link List} of {@link INode} objects, possibly empty
	 */
	IResult next();
	
	/**
	 * Fetch the previous number of hits
	 * @return a {@link List} of {@link INode} objects, possibly empty
	 */
	IResult previous();
	
	
}
