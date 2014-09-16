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

import org.topicquests.common.api.IHarvestingOntology;
import org.topicquests.model.api.legends.IEventLegend;
import org.topicquests.model.api.legends.IPersonLegend;
import org.topicquests.model.api.provider.IDataProvider;
import org.topicquests.model.api.provider.ITopicDataProvider;
import org.topicquests.common.ResultPojo;
import org.topicquests.common.api.IBiblioLegend;
import org.topicquests.common.api.IBootstrap;
import org.topicquests.common.api.ICoreIcons;
import org.topicquests.common.api.IRelationsLegend;
import org.topicquests.common.api.IResult;
import org.topicquests.common.api.ISocialLegend;
import org.topicquests.common.api.ITopicQuestsOntology;
import org.topicquests.common.api.INodeTypes;

//TODO IDublinCoreOntology? other?

/**
 * @author park
 * <p>A class to bootstrap all features into a new database</p>
 */
public class CoreBootstrap extends BootstrapBase implements IBootstrap {
	
	/**
	 * 
	 */
	public CoreBootstrap(ITopicDataProvider db) {
		super(db);
	}

	/**
	 * Test the database to see if it contains core features. If not, add those features.
	 * @return
	 */
	public IResult bootstrap() {
		IResult result = new ResultPojo();
		result.setResultObject(new Boolean(true)); // optimistic default
		//Look for the root object
		IResult temp = database.getNode(ITopicQuestsOntology.TYPE_TYPE, credentials);
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
		makeSubclassNode(null,ITopicQuestsOntology.TYPE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Type Type", "The TopicQuests typology root node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.CLASS_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Class Type", "The TopicQuests typology class node type.", result);
				makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,ITopicQuestsOntology.NODE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Node Type", "The TopicQuests typology node type.", result);
					makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,ITopicQuestsOntology.GRAPH_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Graph Type", "The TopicQuests typology graph type.", result);
					makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,ITopicQuestsOntology.VIRTUAL_NODE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Virtual Node Type", "The TopicQuests typology virtual node type.", result);
	//				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,ITopicQuestsOntology.AIR_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Addressable Information Resource Type", "The TopicQuests typology air node type.", result);
				makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,ISocialLegend.TAG_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Tag Type", "The TopicQuests Social typology tag type.", result);
					makeSubclassNode(ISocialLegend.TAG_TYPE,IHarvestingOntology.CLUSTER_TAG_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Cluster Tag Type", "The TopicQuests tag type made from a Cluster label.", result);
				makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,ISocialLegend.ANNOTATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Annotation Type", "The TopicQuests Social typology annotation type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.AVATAR_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Avatar Type", "The TopicQuests NodeTypes typology avatar type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.BLOG_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Blog Type", "The TopicQuests NodeTypes typology blog type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.CHALLENGE_TYPE,ICoreIcons.CHALLENGE_SM,ICoreIcons.CHALLENGE,"Challenge Type", "The TopicQuests NodeTypes typology challenge type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.CLAIM_TYPE,ICoreIcons.CLAIM_SM,ICoreIcons.CLAIM,"Claim Type", "The TopicQuests NodeTypes typology claim type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.CON_TYPE,ICoreIcons.CON_SM,ICoreIcons.CON,"Con Argument Type", "The TopicQuests NodeTypes typology con argument type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.CONVERSATION_MAP_TYPE,ICoreIcons.MAP_SM,ICoreIcons.MAP,"Conversation Map Type", "The TopicQuests NodeTypes typology conversation map type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.ESSAY_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Essay Type", "The TopicQuests NodeTypes typology essay type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.EVIDENCE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Evidence Type", "The TopicQuests NodeTypes typology evidence type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.GUILD_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Guild Type", "The TopicQuests NodeTypes typology guild type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.ISSUE_TYPE,ICoreIcons.ISSUE_SM,ICoreIcons.ISSUE,"Issue Type", "The TopicQuests NodeTypes typology issue type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.MICROBLOG_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Microblog Type", "The TopicQuests NodeTypes typology microblog type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.ORGANIZATION_TYPE,ICoreIcons.ORGANIZATION_SM,ICoreIcons.ORGANIZATION,"Organization Type", "The TopicQuests NodeTypes typology organization type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.PERSPECTIVE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Perspective Type", "The TopicQuests NodeTypes typology perspective type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.POSITION_TYPE,ICoreIcons.POSITION_SM,ICoreIcons.POSITION,"Position Type", "The TopicQuests NodeTypes typology position type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.PRO_TYPE,ICoreIcons.PRO_SM,ICoreIcons.PRO,"Pro Argument Type", "The TopicQuests NodeTypes typology pro argument type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.PROJECT_TYPE,ICoreIcons.PROJECT_SM,ICoreIcons.PROJECT,"Project Type", "The TopicQuests NodeTypes typology project type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.RESOURCE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Resource Type", "The TopicQuests NodeTypes typology resource type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.SOLUTION_TYPE,ICoreIcons.SOLUTION_SM,ICoreIcons.SOLUTION,"Solution Type", "The TopicQuests NodeTypes typology solution type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.SUMMARY_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Summary Type", "The TopicQuests NodeTypes typology summary type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.TAG_TYPE,ICoreIcons.TAG_SM,ICoreIcons.TAG,"Tag Type", "The TopicQuests NodeTypes typology tag type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.THEME_TYPE,ICoreIcons.THEME_SM,ICoreIcons.THEME,"Theme Type", "The TopicQuests NodeTypes typology theme type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.BOOKMARK_TYPE,ICoreIcons.BOOKMARK_SM,ICoreIcons.BOOKMARK,"Bookmark Type", "The TopicQuests NodeTypes typology bookmark type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.QUEST_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Quest Type", "The TopicQuests NodeTypes typology quest type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.CITATION_NODE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Citation Node Type", "The TopicQuests NodeTypes typology citation node type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.GRAPH_NODE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Graph Node Type", "The TopicQuests NodeTypes typology graph node type.", result);
				makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,INodeTypes.ONTOLOGY_NODE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Ontology Node Type", "The TopicQuests NodeTypes ontology node type.", result);

				
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.RELATION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Relation Type", "The TopicQuests typology relation node type.", result);
				makeSubclassNode(ITopicQuestsOntology.RELATION_TYPE,ITopicQuestsOntology.ASSERTION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Assertion Type", "The TopicQuests typology assertion node type.", result);
					makeSubclassNode(ITopicQuestsOntology.ASSERTION_TYPE,ITopicQuestsOntology.MERGE_ASSERTION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Merge Assertion Type", "The TopicQuests typology merge assertion node type.", result);
					makeSubclassNode(ITopicQuestsOntology.ASSERTION_TYPE,ITopicQuestsOntology.POSSIBLE_MERGE_ASSERTIONTYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Possible Merge Assertion Type", "The TopicQuests typology possible merge assertion node type.", result);
					makeSubclassNode(ITopicQuestsOntology.ASSERTION_TYPE,ITopicQuestsOntology.UNMERGE_ASSERTION_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Unmerge Assertion Type", "The TopicQuests typology unmerge assertion node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.ONTOLOGY_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Ontology Type", "The TopicQuests typology ontology node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.PROPERTY_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Property Type", "The TopicQuests typology property node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.RULE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Rule Type", "The TopicQuests typology rule node type.", result);
				makeSubclassNode(ITopicQuestsOntology.RULE_TYPE,ITopicQuestsOntology.MERGE_RULE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Merge Rule Type", "The TopicQuests typology merge rule node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.ROLE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Role Type", "The TopicQuests typology role node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.USER_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"User Type", "The TopicQuests typology user node type.", result);
				makeInstanceNode(ITopicQuestsOntology.USER_TYPE,ITopicQuestsOntology.SYSTEM_USER,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"System user", "The TopicQuests System User.", result);
				makeSubclassNode(ITopicQuestsOntology.USER_TYPE,ITopicQuestsOntology.UNKNOWN_USER_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Unknown User Type", "The TopicQuests typology unknown user node type.", result);
				makeSubclassNode(ITopicQuestsOntology.USER_TYPE,ITopicQuestsOntology.FOREIGN_USER_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Foreign User Type", "The TopicQuests typology foreign user node type.", result);
				makeSubclassNode(ITopicQuestsOntology.USER_TYPE,ITopicQuestsOntology.AGENT_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Agent User Type", "The TopicQuests typology agent user node type.", result);
					makeSubclassNode(ITopicQuestsOntology.AGENT_TYPE,ITopicQuestsOntology.MERGE_AGENT_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Merge Agent User Type", "The TopicQuests typology merge agent node type.", result);
					makeSubclassNode(ITopicQuestsOntology.AGENT_TYPE,ITopicQuestsOntology.HARVEST_AGENT_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Harvest Agent User Type", "The TopicQuests typology harvest agent node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.RESOURCE_TYPE, ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Resource Type", "The TopicQuests typology resource node type.", result);
				makeSubclassNode(ITopicQuestsOntology.RESOURCE_TYPE,ITopicQuestsOntology.WEB_RESOURCE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Web Resource Type", "The TopicQuests typology web resource node type.", result);
					makeSubclassNode(ITopicQuestsOntology.WEB_RESOURCE_TYPE,ISocialLegend.BOOKMARK_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Bookmark Type", "The TopicQuests Social typology bookmark type.", result);
	
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.LEGEND_TYPE, ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Legend Type", "The TopicQuests typology legend node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,ITopicQuestsOntology.SCOPE_TYPE, ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Scope Type", "The TopicQuests typology scope node type.", result);
				makeSubclassNode(ITopicQuestsOntology.SCOPE_TYPE,ITopicQuestsOntology.MERGE_RULE_SCOPE_TYPE, ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Merge Rule Scope Type", "The TopicQuests typology merge rule scope node type.", result);
				makeSubclassNode(ITopicQuestsOntology.SCOPE_TYPE,ITopicQuestsOntology.THEME_TYPE, ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Theme Type", "The TopicQuests typology theme node type.", result);
			makeSubclassNode(ITopicQuestsOntology.TYPE_TYPE,IEventLegend.EVENT_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Person Type", "The TopicQuests typology ontology person type.", result);
		//PROPERTIES
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.LOCATOR_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Node Locator Property Type", "The TopicQuests typology locator property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.CREATED_DATE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Created Date Property Type", "The TopicQuests typology created date property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.LAST_EDIT_DATE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Last Edit Date Property Type", "The TopicQuests typology last edit date property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.CREATOR_ID_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Creator ID Property Type", "The TopicQuests typology creator id property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.LABEL_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Label String Property Type", "The TopicQuests typology label string property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.SMALL_LABEL_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Small Label String Property Type", "The TopicQuests typology small label string property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.DETAILS_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Details Property Type", "The TopicQuests typology details property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.VERSION,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Version Property Type", "The TopicQuests typology version property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.SUBCLASS_OF_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Subclass Of Property Type", "The TopicQuests typology subclassOf property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.INSTANCE_OF_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Instance Of Property Type", "The TopicQuests typology instanceOf property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.RESTRICTION_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Restriction Property Type", "The TopicQuests typology restriction property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.IS_PRIVATE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Is Private Property Type", "The TopicQuests typology: true when node is private.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.IS_VIRTUAL_PROXY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Is Virtual Proxy Property Type", "The TopicQuests typology: true when node is a virtual proxy.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.LARGE_IMAGE_PATH,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Large Image Path Property Type", "The TopicQuests typology large image path property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.SMALL_IMAGE_PATH,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Small Image Path Property Type", "The TopicQuests typology small image path property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_IS_TRANSCLUDE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Is Transcluded Property Type", "The TopicQuests typology tuple isTranscluded property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_LIST_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple List Property Type", "The TopicQuests typology tuple list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_LIST_PROPERTY_RESTRICTED,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Restricted Tuple List Property Type", "The TopicQuests typology restricted tuple list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_OBJECT_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Object Property Type", "The TopicQuests typology tuple object property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_OBJECT_TYPE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Object Type Property Type", "The TopicQuests typology tuple object type property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_SUBJECT_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Subject Property Type", "The TopicQuests typology tuple subject property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_SUBJECT_TYPE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Subject Type Property Type", "The TopicQuests typology tuple subject type property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_SUBJECT_ROLE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Subject Role Property Type", "The TopicQuests typology tuple subject's role property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_OBJECT_ROLE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Object Role Property Type", "The TopicQuests typology tuple object's role property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_THEME_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Theme Property Type", "The TopicQuests typology tuple theme property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TUPLE_SIGNATURE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Tuple Signature Property Type", "The TopicQuests typology tuple signature property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.SCOPE_LIST_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Scope List Property Type", "The TopicQuests typology scope list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.PSI_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"PSI Property Type", "The TopicQuests typology PSI (published subject indicator) property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.RESOURCE_URL_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Resource URL Property Type", "The TopicQuests typology resource URL property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.MERGE_REASON_RULES_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Merge Reason Rules List Property Type", "The TopicQuests typology merge reason rules list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.MERGE_LIST_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Merge List Property Type", "The TopicQuests typology merge list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.MERGE_TUPLE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Merge Tuple Property Type", "The TopicQuests typology merge tuple property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.BACKLINK_LIST_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Backlink List Property Type", "The TopicQuests typology backlink list property type.", result);
//			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TRANSCLUDE_LIST_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Transclude List Property Type", "The TopicQuests typology transclude list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.TRANSITIVE_CLOSURE_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Transitive Closure Property Type", "The TopicQuests typology transitive closure property type.", result);
//			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.AIR_LIST_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"AIR List Property Type", "The TopicQuests typology AIR list property type.", result);
//			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"AIR Model Property Type", "The TopicQuests typology AIR model property type.", result);
//				makeSubclassNode(ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE,ITopicQuestsOntology.AIR_PARENT_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"AIR Parent Property Type", "The TopicQuests typology AIR parent property type.", result);
//				makeSubclassNode(ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE,ITopicQuestsOntology.AIR_HOST_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"AIR Host Property Type", "The TopicQuests typology AIR host property type.", result);
//				makeSubclassNode(ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE,ITopicQuestsOntology.AIR_PURPLE_NUMBER_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"AIR Purple Number Property Type", "The TopicQuests typology AIR purple number property type.", result);

			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IRelationsLegend.RELATION_WEIGHT,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Relation Weight Property Type", "The TopicQuests typology relation weight property type.", result);

			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.ONTOLOGY_OBJECT_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Ontology Object Property Type", "The TopicQuests typology Ontology Object Property type.", result);
				makeSubclassNode(ITopicQuestsOntology.ONTOLOGY_OBJECT_PROPERTY_TYPE,ITopicQuestsOntology.ONTOLOGY_INVERSE_FUNDTIONAL_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Ontology Inverse Functional Property Type", "The TopicQuests typology Ontology Inverse Functional Property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.ONTOLOGY_FUNCTIONAL_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Ontology Functional Property Type", "The TopicQuests typology Ontology Functional Property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.ONTOLOGY_DATATYPE_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Ontology Datatype Property Type", "The TopicQuests typology Ontology Datatype Property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.ONTOLOGY_ANNOTATION_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Ontology Annotation Property Type", "The TopicQuests typology Ontology Annotation Property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.DOMAIN_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Domain Property Type", "The TopicQuests typology Domain Property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.RANGE_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Range Property Type", "The TopicQuests typology Range Property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.EQUIVALENT_TO_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Equivalent To Property Type", "The TopicQuests typology equivalent to Property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.INVERSE_OF_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Inverse Of Property Type", "The TopicQuests typology inverse of Property type.", result);

			
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.GRAPH_CONCEPT_LIST_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Graph Concept List Property Type", "The TopicQuests typology graph concept list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.GRAPH_PARENT_GRAPH_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Graph Parent Property Type", "The TopicQuests typology graph parent property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,ITopicQuestsOntology.GRAPH_RELATION_LIST_PROPERTY_TYPE,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Graph Relation List Property Type", "The TopicQuests typology graph relation list property type.", result);

			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IEventLegend.ENDING_DATE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Ending Date Property Type", "The TopicQuests typology ending date property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IEventLegend.LOCATION_OF_ORIGIN_NAME_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Location of Origin Name Property Type", "The TopicQuests typology location of origin name property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IEventLegend.LOCATION_OF_ORIGIN_SYMBOL_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Location of Origin Symbol Property Type", "The TopicQuests typology location of origin symbol property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IEventLegend.STARTING_DATE_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Starting Date Property Type", "The TopicQuests typology starting date property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IPersonLegend.FAMILY_NAME_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Family Name Property Type", "The TopicQuests typology family name property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IPersonLegend.FIRST_NAME_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"First Name Property Type", "The TopicQuests typology first name property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IPersonLegend.MIDDLE_NAMES_PROPERTY,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Middle Names Property Type", "The TopicQuests typology middle names property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IPersonLegend.NAME_APPENDAGES,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Name Appendages Property Type", "The TopicQuests typology name appendages property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IPersonLegend.NIC_NAMES,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Nick Names Property Type", "The TopicQuests typology nick names property type.", result);

			
			
			//added from HarvestBootstrap
			//CLASSES
			makeSubclassNode(ITopicQuestsOntology.CLASS_TYPE,IHarvestingOntology.CLUSTER_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Cluster Type", "The TopicQuests Harvesting typology cluster type.", result);
			makeSubclassNode(ITopicQuestsOntology.NODE_TYPE,IHarvestingOntology.CLUSTER_NODE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Cluster Node Type", "The TopicQuests Harvesting typology cluster node type.", result);
			makeSubclassNode(IHarvestingOntology.CLUSTER_NODE_TYPE,IHarvestingOntology.CARROT2_CLUSTERED_NODE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Carrot2 Clustered Node Type", "The TopicQuests Harvesting typology carrot2 clustered node type.", result);
			makeSubclassNode(ITopicQuestsOntology.AGENT_TYPE,IHarvestingOntology.CARROT2_AGENT_USER,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Carrot2 Agent User Type", "The TopicQuests Harvesting typology carrot2 agent user node type.", result);
			makeSubclassNode(ITopicQuestsOntology.SCOPE_TYPE,IHarvestingOntology.HARVEST_SCOPE_TYPE,ICoreIcons.CLASS_ICON_SM,ICoreIcons.CLASS_ICON,"Harvest Scope Type", "The TopicQuests Harvesting typology harvest scope node type.", result);
			//PROPERTIES
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IHarvestingOntology.CLUSTER_MEMBER_LIST,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Cluster Member List Property Type", "The TopicQuests Harvesting typology cluster member list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IHarvestingOntology.CLUSTER_NODE_MEMBER_LIST,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Cluster Node Member List Property Type", "The TopicQuests Harvesting typology cluster node member list property type.", result);
			makeSubclassNode(ITopicQuestsOntology.PROPERTY_TYPE,IHarvestingOntology.CLUSTER_WEIGHT,ICoreIcons.PROPERTY_ICON_SM,ICoreIcons.PROPERTY_ICON,"Cluster Weight Property Type", "The TopicQuests Harvesting typology cluster weight property type.", result);


		return result;
	}
	

}
