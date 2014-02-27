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

import org.topicquests.common.ResultPojo;
import org.topicquests.common.api.IBootstrap;
import org.topicquests.common.api.ICoreIcons;
import org.topicquests.common.api.IResult;
import org.topicquests.common.api.ITopicQuestsOntology;
import org.topicquests.model.api.provider.IDataProvider;
import org.topicquests.model.api.provider.ITopicDataProvider;
import org.topicquests.common.api.IBiblioLegend;

/**
 * @author park
 *
 */
public class BiblioBootstrap extends BootstrapBase implements IBootstrap  {

	/**
	 * @param db
	 */
	public BiblioBootstrap(ITopicDataProvider db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IResult bootstrap() {
		IResult result = new ResultPojo();
		result.setResultObject(new Boolean(true)); // optimistic default
		//Look for the root object
		IResult temp = database.getNode(IBiblioLegend.AFFILIATIONS_LIST_PROPERTY, credentials);
		if (temp.hasError())
			result.addErrorString(temp.getErrorString());
		if (temp.getResultObject() == null) {
			temp = doBootstrap();
			if (temp.hasError())
				result.addErrorString(temp.getErrorString());	
			result.setResultObject(temp.getResultObject());
		}
		return result;
	}
	/**
	 * Returns a Boolean <code>true</code> if everything bootstraps well; otherwise <code>false</code>
	 * @return
	 */
	private IResult doBootstrap() {
		IResult result = new ResultPojo();
		result.setResultObject(new Boolean(true)); // optimistic default value
		//CLASSES
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.BLOG_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Blog Publication Type", "The TopicQuests Biblio typology blog publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.BOOK_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Book Publication Type", "The TopicQuests Biblio typology book publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.DATABASE_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Database Publication Type", "The TopicQuests Biblio typology database publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.JOURNAL_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Journal Publication Type", "The TopicQuests Biblio typology journal publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.NEWSPAPER_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Newspaper Publication Type", "The TopicQuests Biblio typology newspaper publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.OTHER_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Other Publication Type", "The TopicQuests Biblio typology other publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.PATENT_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Patent Publication Type", "The TopicQuests Biblio typology patent publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.REPORT_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Report Publication Type", "The TopicQuests Biblio typology report publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.WEBPAGE_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Webpage Publication Type", "The TopicQuests Biblio typology webpage publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.WIKI_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Wiki Publication Type", "The TopicQuests Biblio typology wiki publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.MICROBLOG_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Microblog Publication Type", "The TopicQuests Biblio typology microblog publication type.", result);
		makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IBiblioLegend.ONTOLOGY_PUBLICATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Ontology Publication Type", "The TopicQuests Biblio typology ontology publication type.", result);

		
		//PROPERTIES
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.AFFILIATIONS_LIST_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Affiliations List Property Type", "The TopicQuests Biblio typology affiliations list property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.AUTHOR_LIST_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Author List Property Type", "The TopicQuests Biblio typology cluster author list property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.DOI_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"DOI Property Type", "The TopicQuests Biblio typology DOI property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.ISBN_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"ISBN Property Type", "The TopicQuests Biblio typology ISBN property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.ISSN_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"ISSN Property Type", "The TopicQuests Biblio typology ISSN property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.JOURNAL_NUMBER_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Journal Number Property Type", "The TopicQuests Biblio typology journal number property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.JOURNAL_TITLE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Journal Title Property Type", "The TopicQuests Biblio typology journal title property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.JOURNAL_VOLUME_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Journal Volume Property Type", "The TopicQuests Biblio typology journal volume property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.LANGUAGES_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Languages Property Type", "The TopicQuests Biblio typology languages property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.PAGES_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Pages Property Type", "The TopicQuests Biblio typology pages property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.PUBLICATION_DATE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Publication Date Property Type", "The TopicQuests Biblio typology publication date property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.PUBLICATION_TYPES_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Publication Types Property Type", "The TopicQuests Biblio typology publication types property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.PUBLISHER_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Publisher Property Type", "The TopicQuests Biblio typology publisher property type.", result);
		makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IBiblioLegend.RIGHTS_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Rights Property Type", "The TopicQuests Biblio typology rights property type.", result);
		return result;
	}

}
