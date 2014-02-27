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
import org.topicquests.common.api.ITopicQuestsOntology;
/**
 * @author park
 * @see ITopicQuestsOntology
 * Events use {@link ITopicQuestsOntology$LABEL_PROPERTY} for names
 */
public interface IEventLegend {
	//classes
	//
	public static final String
		/** root type -- will be subclassed */
		EVENT_TYPE		= "EventType";
	
	//properties
	public static final String
		LOCATION_OF_ORIGIN_SYMBOL_PROPERTY	= "originLocator", 	//type = symbol
		LOCATION_OF_ORIGIN_NAME_PROPERTY	= "originLabel", 	//type = string
		STARTING_DATE_PROPERTY				= "startingDate", 	// type = Date
		ENDING_DATE_PROPERTY				= "endingDate";		// type = Date
		
}
