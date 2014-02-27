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
package org.topicquests.common.api;

/**
 * @author park
 * <p>Relation and Role types, most from Cohere</p>
 */
public interface IRelationsLegend {
	
	/**
	 * CLASSES
	 */
	public static final String
		//TODO ADD IBIS relations
		/** Cause needs a plethora of synonym words and phrases to support
		 * <em>anticipatory parsing</em>
		 */
		CAUSES_RELATION_TYPE				= "CausesRelationType", //
		EXPLAINS_WHAT_RELATION_TYPE			= "ExplainsWhatRelationType", //
		EXPLAINS_WHY_RELATION_TYPE			= "ExplainsWhyRelationType", //
		EXPLAINS_HOW_RELATION_TYPE			= "ExplainsHowRelationType", //
//		IDENTICAL_TO_RELATION_TYPE 			= "IdenticalToRelationType", now IS_SAME_AS
		IS_SIMILAR_TO_RELATION_TYPE 		= "IsSimilarToRelationType", //
  		SHARES_ISSUES_WITH_RELATION_TYPE 	= "SharesIssuesWithRelationType",//
  		IS_ANALOGOUS_RELATION_TYPE 			= "IsAnalogousRelationType", //
  		IS_METAPHOR_RELATION_TYPE 			= "IsMetaphorRelationType", //
//  		IS_ABOUT_RELATION_TYPE 				= "IsAboutRelationType",
  		AGREES_WITH_RELATION_TYPE 			= "AgreesWithRelationType", //
  		IS_DIFFERENT_TO_RELATION_TYPE 		= "IsDifferentToRelationType", //
  		IS_OPPOSITE_OF_RELATION_TYPE 		= "IsOppositeOfRelationType",//
  		/**Note merge implications on this one -- might need a daemon watching it */
  		IS_SAME_AS_RELATION_TYPE			= "IsSameAsRelationType",//
  		IS_NOT_ANALOGOUS_RELATION_TYPE 		= "IsNotAnalogousRelationType", //
  		HAS_NOTHING_TO_DO_WITH_RELATION_TYPE = "HasNothingToDoWithRelationType",//
  		DISAGREES_WITH_RELATION_TYPE 		= "DisagreesWithRelationType", //
  		USES_RELATION_TYPE 					= "UsesRelationType", //
  		IMPLIES_RELATION_TYPE 				= "ImpliesRelationType", //
  		//issue: most are right handed: a implies b
  		// this is left handed: a is enabled by b
  		// maybe should standardize on right handed? e.g. ENABLES
  		//ENABLED_BY_RELATION_TYPE 			= "EnabledByRelationType",
  		ENABLES_RELATION_TYPE				= "EnablesRelationType", //
  		IMPROVES_ON_RELATION_TYPE 			= "ImprovesOnRelationType", //
  		ADDRESSES_RELATION_TYPE 			= "AddressesRelationType", //
  		SOLVES_RELATION_TYPE 				= "SolvesRelationType", //
  		IS_PREREQUISITE_FOR_RELATION_TYPE 	= "IsPrerequisiteForRelationType",
  		IMPAIRS_RELATION_TYPE 				= "ImpairsRelationType", //
  		PREVENTS_RELATION_TYPE 				= "PreventsRelationType", //
  		PROVES_RELATION_TYPE 				= "ProvesRelationType", //
  		REFUTES_RELATION_TYPE 				= "RefutesRelationType", //
  		IS_EVIDENCE_FOR_RELATION_TYPE 		= "IsEvidenceForRelationType", //
  		IS_EVIDENCE_AGAINST_RELATION_TYPE 	= "IsEvidenceAgainstRelationType", //
  		IS_CONSISTENT_WITH_RELATION_TYPE 	= "IsConsistentWithRelationType", //
  		IS_EXAMPLE_OF_RELATION_TYPE 		= "IsExampleRelationType", //
  		PREDICTS_RELATION_TYPE 				= "PredictsRelationType",
  		ENVISAGES_RELATION_TYPE 			= "EnvisagesRelationRelationType", //
  		UNLIKELY_TO_AFFECT_RELATION_TYPE 	= "UnlikelyToAffectRelationType",
  		IS_INCONSISTENT_WITH_RELATION_TYPE 	= "InconsistentWithRelationType", //
  		RESPONDS_TO_RELATION_TYPE 			= "RespondsToRelationType", //
  		//ROLES
  		IDEA_ROLE_TYPE 						= "IdeaRoleType", //
  		IDEOLOGY_ROLE_TYPE 					= "IdeologyRoleType",//
  		METHOD_ROLE_TYPE 					= "MethodRoleType",//
  		/** not sure about this one */
  		NATURAL_PHENOMENON_ROLE_TYPE 		= "NaturalPhenomenonRoleType",//
  		OPINION_ROLE_TYPE 					= "OpinionRoleType",//
  		PREDICTION_ROLE_TYPE 				= "PredictionRoleType",//
  		PROBLEM_ROLE_TYPE 					= "ProblemRoleType",//
  		SCENARIO_ROLE_TYPE 					= "ScenarioRoleType",//
  		SOFTWARE_ROLE_TYPE 					= "SoftwareRoleType",//
  		SOLUTION_ROLE_TYPE 					= "SolutionRoleType",//
  		THEORY_ROLE_TYPE 					= "TheoryRoleType",//
  		//properties
  		RELATION_WEIGHT						= "RelationWeightPropertyType",
  		
		BOOMARK_RELATION_TYPE	= "BookmarkRelationType",
		TAG_RELATION_TYPE		= "TagRelationType",
		//more relations from looking at OBO ontologies
		IS_RELATED_TO_RELATION_TYPE			= "IsRelatedToRelationType",
		IS_PART_OF_RELATION_TYPE			= "IsPartOfRelationType",
		IS_CONTAINED_IN_RELATION_TYPE		= "IsContainedInRelationType",
		HAS_ROLE_RELATION_TYPE				= "HasRoleRelationType";


}
