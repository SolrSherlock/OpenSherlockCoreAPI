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

import java.util.Date;

import org.topicquests.common.api.IResult;
import org.topicquests.model.api.node.INode;

/**
 * @author park
 *
 */
public interface IPersonEventModel {

	
	/**
	 * Create an instance of {@link INode} for this person
	 * @param firstName
	 * @param middleNames
	 * @param familyName
	 * @param nameAppendages
	 * @param description
	 * @param language
	 * @param dateOfBirth
	 * @param locationOfBirth
	 * @param userId
	 * @param isPrivate
	 * @return
	 */
	IResult newPerson(String firstName, String middleNames, String familyName, String nameAppendages,
			String description, String language,
			Date dateOfBirth, String locationOfBirth, String userId, boolean isPrivate);
	
	/**
	 * Create an instance of {@link INode} for this event
	 * @param eventTypeLocator can be <code>null</code> or locator of particular event type
	 * @param eventName
	 * @param eventLocation
	 * @param description
	 * @param language
	 * @param eventStartDate
	 * @param eventEndDate
	 * @param userId
	 * @param isPrivate
	 * @return
	 */
	IResult newEvent(String eventTypeLocator, String eventName, String eventLocation, String description, 
			String language, Date eventStartDate, Date eventEndDate, String userId, boolean isPrivate );

}
