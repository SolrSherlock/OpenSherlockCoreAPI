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
package org.topicquests.common.api;

/**
 * @author park
 * @see http://dtd.nlm.nih.gov/book/tag-library/n-epa2.html
 */
public interface IBiblioLegend {
	//CLASSES
	public static final String
		PUBLICATION_TYPE			= "PublicationType",
		JOURNAL_PUBLICATION_TYPE	= "JournalPublicationType",
		BOOK_PUBLICATION_TYPE		= "BookPublicationType",
		BLOG_PUBLICATION_TYPE		= "BlogPublicationType",
		WIKI_PUBLICATION_TYPE		= "WikiPublicationType",
		WEBPAGE_PUBLICATION_TYPE	= "WebpagePublicationType",
		NEWSPAPER_PUBLICATION_TYPE	= "NewspaperPublicationType",
		THESIS_PUBLICATION_TYPE		= "ThesisPublicationType",
		DATABASE_PUBLICATION_TYPE	= "DatabasePublicationType",
		REPORT_PUBLICATION_TYPE		= "ReportPublicationType",
		PATENT_PUBLICATION_TYPE		= "PatentPublicationType",
		OTHER_PUBLICATION_TYPE		= "OtherPublicationType",
		MICROBLOG_PUBLICATION_TYPE	= "MicroblogPublicationType",
		ONTOLOGY_PUBLICATION_TYPE	= "OntologyPublicationType";
		
	//PROPERTIES
	//NOTE: Title goes in ITopicQuestsOntology.LABEL_PROPERTY
	public static final String
		/** authors can be a comma-separated collection 
		 * It becomes a task to separate those out and build topics for each
		 */
		AUTHOR_LIST_PROPERTY		= "authors",
		/** typically a comma-separated list in same order as authors
		 * It becomes a task to separate those out and build topics for each
		 * and use an "affiliated-with" tuple to connect institute with author
		 * a kind of social network
		 */
		AFFILIATIONS_LIST_PROPERTY	= "affiliations",
		PUBLISHER_PROPERTY			= "publisher",
		DOI_PROPERTY				= "doi",
		ISBN_PROPERTY				= "isbn",
		ISSN_PROPERTY				= "issn",
		PUBLICATION_DATE_PROPERTY	= "publDate",
		PUBLICATION_TYPES_PROPERTY	= "pubTypes",
		/** multivalue allowing that a doc could be in several languages */
		LANGUAGES_PROPERTY			= "languages",
		JOURNAL_TITLE_PROPERTY		= "journalTitle",
		JOURNAL_VOLUME_PROPERTY		= "journalVolume",
		JOURNAL_NUMBER_PROPERTY		= "journalNumber",
		PAGES_PROPERTY				= "pages",
		RIGHTS_PROPERTY				= "rights";
}
