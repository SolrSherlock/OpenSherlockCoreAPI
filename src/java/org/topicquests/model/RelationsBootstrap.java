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
import org.topicquests.common.api.ICoreIcons;
import org.topicquests.common.api.IRelationsLegend;
import org.topicquests.common.api.IBootstrap;
import org.topicquests.common.api.IResult;
import org.topicquests.common.api.ISocialLegend;
import org.topicquests.common.api.ITopicQuestsOntology;
import org.topicquests.model.api.provider.IDataProvider;
import org.topicquests.model.api.provider.ITopicDataProvider;

/**
 * @author park
 *
 */
public class RelationsBootstrap extends BootstrapBase implements IBootstrap {

	/**
	 * @param db
	 */
	public RelationsBootstrap(ITopicDataProvider db) {
		super(db);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.common.api.IBootstrap#bootstrap()
	 */
	@Override
	public IResult bootstrap() {
		IResult result = new ResultPojo();
		result.setResultObject(new Boolean(true)); // optimistic default
		//Look for the root object
		IResult temp = database.getNode(IRelationsLegend.CAUSES_RELATION_TYPE, credentials);
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
		//RELATIONS
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.CAUSES_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Cause Relation Type", "The TopicQuests Relations typology cause relation type: A cause B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.EXPLAINS_WHAT_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Explains What Relation Type", "The TopicQuests Relations typology explains what relation type: A explains what B is about.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.EXPLAINS_WHY_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Explains Why Relation Type", "The TopicQuests Relations typology explains what relation type: A explains why B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.EXPLAINS_HOW_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Explains How Relation Type", "The TopicQuests Relations typology explains how relation type: A explains how to do B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_ANALOGOUS_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Analogous Relation Type", "The TopicQuests Relations typology is analogous relation type: A is analogous to B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_NOT_ANALOGOUS_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Not Analogous Relation Type", "The TopicQuests Relations typology responds to relation type: A is not analogous to B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.AGREES_WITH_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Agrees With Relation Type", "The TopicQuests Relations typology cause relation type: A agrees with B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.DISAGREES_WITH_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Disagrees With Relation Type", "The TopicQuests Relations typology disagrees with relation type: A disagrees with B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.USES_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Uses Relation Type", "The TopicQuests Relations typology uses relation type: A uses B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IMPLIES_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Implies Relation Type", "The TopicQuests Relations typology implies relation type: A implies B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.ENABLES_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Enables Relation Type", "The TopicQuests Relations typology enables relation type: A enables B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IMPROVES_ON_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Improves On Relation Type", "The TopicQuests Relations typology improves on relation type: A improves on B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IMPAIRS_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Impairs Relation Type", "The TopicQuests Relations typology impairs relation type: A impairs B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.ADDRESSES_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Addresses Relation Type", "The TopicQuests Relations typology addresses relation type: A addresses B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.SOLVES_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Solves Relation Type", "The TopicQuests Relations typology solves relation type: A solves B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_PREREQUISITE_FOR_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Prerequisite For Relation Type", "The TopicQuests Relations typology is prerequisite for relation type: A is prerequisite for B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.PREVENTS_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Prevents Relation Type", "The TopicQuests Relations typology prevents relation type: A prevents B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.PROVES_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Proves Relation Type", "The TopicQuests Relations typology proves relation type: A proves B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.REFUTES_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Refutes Relation Type", "The TopicQuests Relations typology refutes relation type: A refutes B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_EVIDENCE_FOR_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Evidence For Relation Type", "The TopicQuests Relations typology is evidence for relation type: A is evidence for B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_EVIDENCE_AGAINST_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Evidence Against Relation Type", "The TopicQuests Relations typology is evidence against relation type: A cause B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_CONSISTENT_WITH_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Consistent With Relation Type", "The TopicQuests Relations typology is consistent with relation type: A is consistent with B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_INCONSISTENT_WITH_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Inconsistent With Relation Type", "The TopicQuests Relations typology is inconsistent with relation type: A is inconsistent with B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_EXAMPLE_OF_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Example Of Relation Type", "The TopicQuests Relations typology is example of relation type: A is example of B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.RESPONDS_TO_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Responds To Relation Type", "The TopicQuests Relations typology responds to relation type: A responds to B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_SAME_AS_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Same As Relation Type", "The TopicQuests Relations typology is same as relation type: A is same as B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_DIFFERENT_TO_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Different To Relation Type", "The TopicQuests Relations typology is different to relation type: A is different to B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_OPPOSITE_OF_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Opposite Of Relation Type", "The TopicQuests Relations typology responds to relation type: A is opposite of B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_METAPHOR_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Metaphor Relation Type", "The TopicQuests Relations typology is metaphor relation type: A is metaphor for B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_SIMILAR_TO_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Similar To Relation Type", "The TopicQuests Relations typology is similar to relation type: A is similar to B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.SHARES_ISSUES_WITH_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Shares Issues With Relation Type", "The TopicQuests Relations typology shares issues with relation type: A shares issues with to B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.BOOMARK_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Bookmark Relation Type", "The TopicQuests bookmark relation type: A is bookmarked by B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.TAG_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Tag Relation Type", "The TopicQuests tag relation type: A is tagged by B.", result);

		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_RELATED_TO_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Related To Relation Type", "The TopicQuests tag relation type: A is related to B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_PART_OF_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Part Of Relation Type", "The TopicQuests tag relation type: A is part of B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.IS_CONTAINED_IN_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Is Contained In Relation Type", "The TopicQuests tag relation type: A is contained in B.", result);
		makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,IRelationsLegend.HAS_ROLE_RELATION_TYPE,ICoreIcons.RELATION_ICON_SM,ICoreIcons.RELATION_ICON,"Has Role Relation Type", "The TopicQuests tag relation type: A has role B.", result);

		//ROLES
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.IDEA_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Idea Role Type", "The TopicQuests Relations typology idea role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.IDEOLOGY_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Ideology Role Type", "The TopicQuests Relations typology ideology role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.METHOD_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Method Role Type", "The TopicQuests Relations typology method role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.NATURAL_PHENOMENON_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Natural Phenomenon Role Type", "The TopicQuests Relations typology natural phenomenon role type.", result);
//dup		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.IDEA_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Idea Role Type", "The TopicQuests Relations typology idea role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.OPINION_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Opinion Role Type", "The TopicQuests Relations typology opinion role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.PREDICTION_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Prediction Role Type", "The TopicQuests Relations typology prediction role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.PROBLEM_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Problem Role Type", "The TopicQuests Relations typology problem role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.SCENARIO_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Scenario Role Type", "The TopicQuests Relations typology scenario role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.SOFTWARE_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Software Role Type", "The TopicQuests Relations typology software role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.SOLUTION_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Solution Role Type", "The TopicQuests Relations typology solution role type.", result);
		makeSubclassNode(ITopicQuestsOntology.ROLE_TYPE,IRelationsLegend.THEORY_ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Theory Role Type", "The TopicQuests Relations typology theory role type.", result);

		
		return result;
	}

}
