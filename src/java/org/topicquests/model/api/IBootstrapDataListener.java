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
package org.topicquests.model.api;

/**
 * @author park
 * <p>This interface defines a listener which will
 * allows users to gain access to terminology and to
 * topics which use that terminology as names (labels)</p>
 */
public interface IBootstrapDataListener {
	//TODO move this to TopicQuestsCoreAPI codebase
	/**
	 * Data built during any topic bootstrapping process, which
	 * could include built-in bootstrapping, and any data importing
	 * such as ontologies, word-lists which become topics, etc.
	 * @param label
	 * @param topicLocator
	 */
	void acceptBootstrapData(String label, String topicLocator);
}
