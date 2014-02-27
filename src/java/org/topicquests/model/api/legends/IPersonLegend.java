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
package org.topicquests.model.api.legends;


/**
 * @author park
 * <p>This legend presumes that people are modeled on their <em>roles</em></p>
 */
public interface IPersonLegend extends IEventLegend {
	
	public static final String
		/** subclasses EventType */
		PERSON_TYPE		= "PersonType";

//properties
public static final String
	FIRST_NAME_PROPERTY			= "firstName", 		//type = string
	/** Some people have several middle names, some have none */
	MIDDLE_NAMES_PROPERTY		= "middleNames", 	// type = string
	FAMILY_NAME_PROPERTY		= "familyName",		// type = string
	/** e.g. M.D. etc */
	NAME_APPENDAGES				= "nameAppendages",	//type = string
	NIC_NAMES					= "nicknames";		// type = string multivalue

	
}
