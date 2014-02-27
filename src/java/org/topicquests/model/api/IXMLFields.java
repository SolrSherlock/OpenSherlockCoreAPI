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
package org.topicquests.model.api;

/**
 * @author park
 *
 */
public interface IXMLFields {
	
	public static final String
		NODES			= "nodes",
		NODE			= "node", // locator is attribute
		TUPLES			= "tuples",
		TUPLE			= "tuple",
		PROPERTIES		= "properties",
		PROPERTY		= "property", // key is attribute
		VALUE			= "value",
		DATABASE		= "database",
		LOCATOR_ATT		= "locator",
		KEY_ATT			= "key",
		LANGUAGE_ATT	= "language";

}
