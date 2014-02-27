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
package org.topicquests.common.api;

import java.util.List;
import java.util.Set;

/**
 * @author park
 *
 */
public interface ICommonQueries {

	/**
	 * Returns <em>all</em> instances of the node identified by <code>locator</code>
	 * @param locator
	 * @param credentials
	 * @return
	 */
	IResult listAllInstances(String locator, Set<String>credentials);
	
	/**
	 * <p>Returns only those nodes which are one of:
	 * <li>not merged</li>
	 * <li>a virtual node for merged nodes</li></p>
	 * @param locator
	 * @param credentials
	 * @return
	 */
	IResult listFilteredInstances(String locator, Set<String>credentials);
	
	IResult listAllSubclasses(String locator, Set<String>credentials);
	
	IResult listFilteredSubclasses(String locator, Set<String>credentials);
}
