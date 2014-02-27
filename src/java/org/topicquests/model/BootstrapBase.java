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
package org.topicquests.model;

import java.util.HashSet;
import java.util.Set;

import org.topicquests.common.api.IResult;
import org.topicquests.common.api.ITopicQuestsOntology;
import org.topicquests.model.api.ITicket;
import org.topicquests.model.api.node.INode;
import org.topicquests.model.api.node.INodeModel;
import org.topicquests.model.api.provider.IDataProvider;
import org.topicquests.model.api.provider.ITopicDataProvider;

/**
 * @author park
 *
 */
public class BootstrapBase {
	protected ITopicDataProvider database;
	protected INodeModel model;
	protected ITicket credentials;

	/**
	 * 
	 */
	public BootstrapBase(ITopicDataProvider db) {
		database = db;
		model = database.getNodeModel();
		credentials = new TicketPojo(ITopicQuestsOntology.SYSTEM_USER);
		System.out.println("BootstrapBase "+database+" "+model);
	}

	/**
	 * Node utility
	 * @param type
	 * @param locator
	 * @param icon
	 * @param smallIcon
	 * @param label
	 * @param description
	 * @param result
	 */
	protected void makeInstanceNode(String type, String locator, String icon, String smallIcon, String label, String description, IResult result) {
		System.out.println("MAKEINST "+type+" "+locator);
		
		INode node =  model.newInstanceNode(locator, type, label, description, "en", 
									 ITopicQuestsOntology.SYSTEM_USER, smallIcon, icon, false);
		IResult temp = database.putNode(node);
		model.recycleNode(node);
	}
	
	/**
	 * Node utility
	 * @param type
	 * @param locator
	 * @param icon
	 * @param smallIcon
	 * @param label
	 * @param description
	 * @param result
	 */
	protected void makeSubclassNode(String type, String locator, String icon, String smallIcon, String label, String description, IResult result) {
		System.out.println("MAKESUB "+type+" "+locator);
		IResult temp =  null;
		INode node;
		if (type == null)
			node = model.newNode(locator, label, description, "en", ITopicQuestsOntology.SYSTEM_USER, 
					smallIcon, icon, false);
		else
			node = model.newSubclassNode(locator, type, label, description, "en", 
					ITopicQuestsOntology.SYSTEM_USER, smallIcon, icon, false);
		temp = database.putNode(node);
		if (temp.hasError()) {
			result.addErrorString(temp.getErrorString());
			result.setResultObject(new Boolean(false));
			
			//TEMPORARY
			throw new RuntimeException(temp.getErrorString());
		}		
		model.recycleNode(node);
	}
}
