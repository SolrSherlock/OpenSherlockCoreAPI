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

/**
 * @author park
 * <p>A Graph object which can replace a Tuple for certain situations</p>
 * <p>A Graph deals with concepts, {@link INode} objects.</p>
 */
public interface IConceptualGraph extends INode {

	List<String> listConceptLocators();
	void addConceptLocator(String locator);
	void removeConceptLocator(String locator);
	
	List<String> listRelationLocators();
	void addRelationLocator(String locator);
	void removeRelationLocator(String locator);
	
	String getParentGraphLocator();
	void setParentGraphLocator(String locator);
}
