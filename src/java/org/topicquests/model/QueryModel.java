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
package org.topicquests.model;


import org.topicquests.common.api.ITopicQuestsOntology;
import org.topicquests.model.api.ITicket;
import org.topicquests.model.api.query.IQueryIterator;
import org.topicquests.model.api.query.IQueryModel;
import org.topicquests.common.QueryUtil;

/**
 * @author park
 *
 */
public class QueryModel implements IQueryModel {
	private Environment environment;
	private final String labelQuery = ITopicQuestsOntology.LABEL_PROPERTY;
	private final String detailsQuery = ITopicQuestsOntology.DETAILS_PROPERTY;
	private final String instanceQuery = ITopicQuestsOntology.INSTANCE_OF_PROPERTY_TYPE+":";
	private final String subClassQuery = ITopicQuestsOntology.SUBCLASS_OF_PROPERTY_TYPE+":";

	/**
	 * 
	 */
	public QueryModel(Environment env) {
		environment = env;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IQueryModel#listNodesByLabel(java.lang.String, java.lang.String, int, java.util.Set)
	 */
	@Override
	public IQueryIterator listNodesByLabel(String label, String language,
			int count, ITicket  credentials) {
		IQueryIterator itr = new QueryIterator(environment);
		
		itr.start(makeField(labelQuery,language)+":"+QueryUtil.escapeQueryCulprits(label), count, credentials);
		return itr;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IQueryModel#listNodesByDetails(java.lang.String, java.lang.String, int, java.util.Set)
	 */
	@Override
	public IQueryIterator listNodesByDetails(String details, String language,
			int count, ITicket  credentials) {
		IQueryIterator itr = new QueryIterator(environment);
		itr.start(makeField(detailsQuery,language)+":"+QueryUtil.escapeQueryCulprits(details), count, credentials);
		return itr;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IQueryModel#listTuplesByRelation(java.lang.String, int, java.util.Set)
	 */
	@Override
	public IQueryIterator listTuplesByRelation(String relationType, int count,
			ITicket  credentials) {
		IQueryIterator itr = new QueryIterator(environment);
		itr.start(instanceQuery+relationType, count, credentials);
		return itr;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IQueryModel#listNodeInstances(java.lang.String, int, java.util.Set)
	 */
	@Override
	public IQueryIterator listNodeInstances(String nodeTypeLocator, int count,
			ITicket  credentials) {
		IQueryIterator itr = new QueryIterator(environment);
		itr.start(instanceQuery+nodeTypeLocator, count, credentials);
		return itr;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IQueryModel#listNodeSubclasses(java.lang.String, int, java.util.Set)
	 */
	@Override
	public IQueryIterator listNodeSubclasses(String superClassLocator,
			int count, ITicket  credentials) {
		IQueryIterator itr = new QueryIterator(environment);
		itr.start(subClassQuery+superClassLocator, count, credentials);
		return itr;
	}

	/**
	 * Calculate the appropriate Solr field
	 * @param fieldBase
	 * @param language
	 * @return
	 */
	String makeField(String fieldBase, String language) {
		//TODO sort this out: do we still need it for non-SOLR topic maps?
		String result = fieldBase;
		if (!language.equals("en"))
			result += language;
		return result;
	}

}
