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

import java.util.*;

import org.topicquests.common.api.IBiblioLegend;
/**
 * @author Park
 * @see {@link IBiblioLegend}
 */
public interface ICitation extends INode {
	
	/**
	 * 
	 * @param title
	 * @param language defaults to "en" if <code>null</code>
	 * @param userId TODO
	 */
	void setDocumentTitle(String title, String language, String userId);
	
	/**
	 * 
	 * @param language
	 * @return defaults to "en" if <code>null</code>
	 */
	String getDocumentTitle(String language);
	
	/**
	 * 
	 * @param abs
	 * @param language defaults to "en" if <code>null</code>
	 * @param userId TODO
	 */
	void setDocumentAbstract(String abs, String language, String userId);
	
	/**
	 * 
	 * @param language defaults to "en" if <code>null</code>
	 * @return
	 */
	String getDocumentAbstract(String language);
	
	/**
	 * A citation may have many authors
	 * @param authorLocator
	 */
	void addAuthorLocator(String authorLocator);
	
	List<String> listAuthorLocators();
	
	void setPublicationTypeLocator(String typeLocator);
	
	String getPublicationTypeLocator();
	
	void setPublisherLocator(String publisherLocator);
	
	String getPublisherLocator();
	
	void setDOI(String doi);
	
	String getDOI();
	
	void setISBN(String isbn);
	
	String getISBN();
	
	void setISSN(String issn);
	
	String getISSSN();
	
	/**
	 * Has its own field; the paper's title uses INode label field
	 * @param journalTitle
	 */
	void setJournalTitle(String journalTitle);
	
	/**
	 * 
	 * @return
	 */
	String getJournalTitle();
	
	void setPublicationDate(Date d);
	
	/**
	 *  
	 * @return can return <code>null</code> if not set
	 */
	Date getPublicationDate();
	
	void setJournalVolume(String vol);
	
	String getJournalVolume();
	
	void setJournalNumber(String num);
	
	String getJournalNumber();
	
	void setPages(String pages);
	
	String getPages();
	
	
	
}
