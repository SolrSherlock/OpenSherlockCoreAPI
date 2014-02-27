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
package org.topicquests.model.api.node;

import java.util.Date;
import java.util.List;

import org.topicquests.common.api.IResult;

/**
 * @author park
 * <p>The behavior is to create an INode label with all of the person names given</p>
 * <p>In the event there are appendages, e.g. Joe Smith M.D., there would be two labels
 * created: one for Joe Smith, and one for Joe Smith M.D.</p>
 * <p>Events use INode label for names</p>
 */
public interface IPersonEvent extends INode {
	
	/**
	 * <p>A required field</p>
	 * <p>In special cases, can be an empty String <code>""</code></p>
	 * @param firstName
	 */
	void setFirstName(String firstName);
	
	String getFirstName();
	
	/**
	 * An optional field
	 * @param middleNames can be <code>null</code>
	 */
	void setMiddleNames(String middleNames);
	
	/**
	 * 
	 * @return can return <code>null</code>
	 */
	String getMiddleNames();
	
	/**
	 * A required field
	 * @param familyName
	 */
	void setFamilyName(String familyName);
	
	/**
	 * 
	 * @return
	 */
	String getFamilyName();
	
	/**
	 * <p>Optional: typically, e.g. M.D., PhD, etc</p>
	 * @param appendages
	 */
	void setNameAppendages(String appendages);
	
	/**
	 * 
	 * @return can return <code>null</code>
	 */
	String getNameAppendages();
	
	
	/**
	 * Same as <em>birthdate</em> for persons
	 * @param startDate
	 */
	void setStartDate(Date startDate);
	
	Date getStartDate();
	
	/**
	 * Same as <em>date of death</em> for persons
	 * @param endDate
	 */
	void setEndDate(Date endDate);
	
	Date getEndDate();
	
	/**
	 * <p>Same as <em>birth location</em> for persons</p>
	 * <p>This is used to link to the {@link INode} which represents the location</p>
	 * @param locationLocator
	 */
	void setLocationOfOrginLocator(String locationLocator);
	
	String getLocationOfOriginLocator();
	
	/**
	 * <p>Same as <em>birth location</em> for persons</p>
	 * <p>This is used to simply give the location's name</p>
	 * @param locationName
	 */
	void setLocationOfOriginName(String locationName);
	
	String getLocationOfOriginName();
	
	/**
	 * Add a nickname
	 * @param nickName
	 */
	void addNickName(String nickName);
	
	/**
	 * List of nicknames for this person
	 * @return possibly empty list
	 */
	List<String> listNickNames();
	
}
