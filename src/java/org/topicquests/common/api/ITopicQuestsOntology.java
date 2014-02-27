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
 * Bootstrap Ontology to provide a <em>core legend</em>
 * @author jackpark
 *
 */
public interface ITopicQuestsOntology {
	public static final String 
	BASE_URI 										= "http://topicquests.org/",
	CORE_URI 										= ITopicQuestsOntology.BASE_URI+"core/",
	CORE_NS 										= "tscore",
	


	ROLE_BASED_RELATION_SUFFIX 						= "_RBReln",
	LINGUISTIC_RELATION_SUFFIX 						= "_LReln",
	SIMPLE_ASSERTION_SUFFIX 						= "_S_Assn",
	DESCRIPTION_SUFFIX 								= "_Description",
	CREATOR_SUFFIX 									= "_Creator",
	AIR_SUFFIX 										= "_Air",
	NAME_SUFFIX 									= "_Name",

	GUEST_USER										= "guest",
	SYSTEM_USER 									= "SystemUser",
	SYSTEM_USER_PASSWORD 							= "SystemUser!",
	
	//////////////////////////////////////
	// Language codes
	//////////////////////////////////////
	DEFAULT_LANGUAGE		        				= "en", //english
	
	/** Synonym prefix, e.g. SYN_en */
	SYNONYM_PREFIX									= "SYN_",
	/** Opposite relation label/details prefix */
	OPPOSITE_RELATION_PREFIX						= "OPP_",
	
	//////////////////////////////////////
	// CLASS TYPES
	// Code indenting indicates subclass relations
	//////////////////////////////////////
	/**
	 * Provides a source legend for bootstrap properties
	 */
	CORE_LEGEND 									= "CoreLegend",
	//////////////////////////////////////////
	// TYPOLOGY starts here
	//////////////////////////////////////////
	TYPE_TYPE									= "TypeType",
			 //   	SUBJECT_MAP_TYPE 							= "SubjectMapType",
		    /**
		     * Internal SubjectProxy types, subclasses of which are NAME_TYPE_NODE & ????
		     */
//		    SUBJECT_PROXY_TYPE 							= "SubjectProxyType",
//		    	AIR_PROXY_TYPE 							= "AirProxyType",
			    /**
			     * Creates a SubjectProxy that aggregates merged SubjectProxy objects
			     */
//			    VIRTUAL_PROXY_TYPE 						= "VirtualProxyType",
			    /**
			     * Creates a SubjectProxy that links to a SubjectProxy in a different database
			     */
//			    REMOTE_PROXY_TYPE 						= "RemoteProxyType",
		
	    CLASS_TYPE 									= "ClassType",
	    	//needed in export of a tuple
	    	NODE_TYPE								= "NodeType",
	    		AIR_TYPE							= "AirType",
	    		GRAPH_TYPE							= "GraphType",
	    	//needed in merge and export of a tuple
	    		VIRTUAL_NODE_TYPE					= "VirtualNodeType",
	    ONTOLOGY_TYPE 								= "OntologyType",
	
	    RULE_TYPE 									= "RuleType",
	    	MERGE_RULE_TYPE 						= "MergeRuleType",
	    RESOURCE_TYPE 								= "ResourceType",
	    	WEB_RESOURCE_TYPE						= "WebResourceType",
	    RELATION_TYPE 								= "RelationType",
	    	    ///////////////////////
	    	    // Assertions
	    	    ///////////////////////
	    	    ASSERTION_TYPE 								= "AssertionType",
	    			MERGE_ASSERTION_TYPE 					= "MergeAssertionType",
	    			POSSIBLE_MERGE_ASSERTIONTYPE			= "PossibleMergeAssertionType",
	    			UNMERGE_ASSERTION_TYPE					= "UnMergeAssertionType",
	    			
	    			//ROLE_BASED_RELATION_TYPE 				= "RoleBasedRelationType",
	    			//LINGUISTIC_RELATION_TYPE 				= "linguisticRelationType",
	    			//SIMPLE_ASSERTION_TYPE 					= "SimpleAssertionType",
	    		    /**
	    		     * Predefines as subclass of LEGEND_TYPE: user must subclass which assertion type
	    		     */
	    		    //LEGEND_ASSERTION_TYPE 					= "LegendAssertionType",
	    ROLE_TYPE 									= "RoleType",
	    USER_TYPE 									= "UserType",
	    	UNKNOWN_USER_TYPE						= "UnknownUserType",
		    /**
		     * A USER_TYPE generated on import from a different map
		     */
		    FOREIGN_USER_TYPE 						= "ForeignUserType",
		    AGENT_TYPE								= "AgentType",
		    	MERGE_AGENT_TYPE					= "MergeAgentType",
		    	HARVEST_AGENT_TYPE					= "HarvestAgentType",

	    /**
	     * The following <em>LEGEND</em> types are defined for exporting.
	     * Is a CLASS_TYPE
	     */
	    LEGEND_TYPE 								= "LegendType",
	    SCOPE_TYPE 									= "ScopeType",
	    	MERGE_RULE_SCOPE_TYPE					= "MergeRuleScopeType",
	    	THEME_TYPE								= "ThemeType",

	    
		//////////////////////////////////////
		// PROPERTY TYPES
		//////////////////////////////////////
	    PROPERTY_TYPE 								= "PropertyType",
	    
	    //////////////////////////////////
	    // NODE MODELING
	    //////////////////////////////////
	    	// Identity
	     	//////////////////////////////
			/**
			 * The official, unique identifier for any proxy (node)
			 */
			LOCATOR_PROPERTY							= "locator",
		    /**
		     * Predefines as subclass of LEGEND_TYPE and PROPERTY_TYPE
		     */
		    //LEGEND_PROPERTY_TYPE 						= "LegendPropertyType", //later
		    /**
		     * In theory, used to identify foreign legends for imported resources
		     */
		    //DEFINING_LEGEND_PROPERTY_TYPE 				= "DefiningLegendPropertyType", //later

	    	//////////////////////////////
	    	// TAXONOMY
	    	//////////////////////////////
	    
		    SUBCLASS_OF_PROPERTY_TYPE					= "subOf",
		    INSTANCE_OF_PROPERTY_TYPE					= "instanceOf",
		    /**
		     * A multi-valued property which is all the parents of this node
		     */
		    TRANSITIVE_CLOSURE_PROPERTY_TYPE			= "TransitiveClosureListPropertyType",

			AIR_LIST_PROPERTY_TYPE						= "AirListPropertyType",
			
			/** used for unrestricted tuples -- all nodes are public */
			TUPLE_LIST_PROPERTY							= "TupleListPropertyType",
			/** used where any node in the tuple is not public */
			TUPLE_LIST_PROPERTY_RESTRICTED				= "RestrictedTupleListPropertyType",

		    /**
		     * If a proxy has a RestrictionProperty, then it might be:
		     * <ul>
		     * <li>Not public</li>
		     * <li>Editable by only selected people</li>
		     * <li>...</li>
		     * </ul>
		     */
		    RESTRICTION_PROPERTY_TYPE 					= "RestrictionsPropertyType",
		    
		    /**
		     * Published Subject Indicators
		     */
		    PSI_PROPERTY_TYPE 							= "psi",

		    RESOURCE_URL_PROPERTY			 			= "resourceURL",
			LARGE_IMAGE_PATH							= "largeIcon",
			SMALL_IMAGE_PATH							= "smallIcon",
			
			CREATOR_ID_PROPERTY							= "creatorId",
			
			//Date
			CREATED_DATE_PROPERTY						= "createdDate",
			LAST_EDIT_DATE_PROPERTY						= "lastEditDate",
			//boolean
			IS_PRIVATE_PROPERTY							= "isPrivate",
			IS_VIRTUAL_PROXY							= "isVirtProxy",
			
		    ///////////////////////////////////
			// Merge support
			//////////////////////////////////
			/**
			 * Added to Merge Assertion Node
			 */
			MERGE_REASON_RULES_PROPERTY					= "mergeReasonRules",
			
			/** each node can have one and only one merge tuple */
			MERGE_TUPLE_PROPERTY						= "mergetuple",
			MERGE_LIST_PROPERTY							= "mergeList",
			//////////////////////////////////
			// NODE CONTENT
			//////////////////////////////////
			/** some nodes which represent web pages might be href'd by other pages */
			BACKLINK_LIST_PROPERTY						= "backlinks",
				//////////////////////////////////
				// Text Modeling
				//////////////////////////////////
		    /**
		     * Label is e.g. the IBIS statement in a node
		     */
			LABEL_PROPERTY								= "label",
			/**
			 * Very short label: 70 characters, like DebateGraph
			 */
			SMALL_LABEL_PROPERTY						= "smallLabel",
			
			/**
			 * Details are like a description except just one per node
			 */
			DETAILS_PROPERTY							= "details",

				//////////////////////////////////
				// AIR Modeling
				// AIR Subject reuses Label property
				// AIR Body reuses Details property
				//////////////////////////////////
		    VERSION_PROPERTY_TYPE						= "VersionPropertyType",
		    
		    
		    //AIR_BODY_PROPERTY_TYPE					= "AirBodyPropertyType",
		    //AIR_SUBJECT_PROPERTY_TYPE					= "AirSubjectPropertyType",
		    /** An AIR must model its <em>host</em> topic, the context of which
		     * determines parents, hosts, and purple numbers. That entails
		     * a {@link java.util.Map} object hosted at the AIR_MODEL_PROPERTY_TYPE
		     */
		    AIR_MODEL_PROPERTY_TYPE						= "AirModelPropertyType",
		    	AIR_PARENT_PROPERTY_TYPE					= "AirParentPropertyType",
		    	AIR_PURPLE_NUMBER_PROPERTY_TYPE				= "PurpleNumberPropertyType",
		    	AIR_HOST_PROPERTY_TYPE						= "AirHostPropertyType",
		    		
		    // can't see where this is used
			//TRANSCLUDED_ROOT_PROXY_PROPERTY 			= "TranscludedRootProxyProperty", //????
		   
			//TRANSCLUDE_LIST_PROPERTY					= "transcludes",
			
			////////////////////////////
			//Tuple Support
			////////////////////////////
			TUPLE_OBJECT_PROPERTY						= "TupleObjectPropertyType",
			TUPLE_OBJECT_TYPE_PROPERTY					= "TupleObjectTypePropertyType",
			TUPLE_SUBJECT_PROPERTY						= "TupleSubjectPropertyType",
			TUPLE_SUBJECT_TYPE_PROPERTY					= "TupleSubjectTypePropertyType",
			TUPLE_IS_TRANSCLUDE_PROPERTY				= "IsTranscludePropertyType",
			TUPLE_SUBJECT_ROLE_PROPERTY					= "TupleSubjectRolePropertyType",
			TUPLE_OBJECT_ROLE_PROPERTY					= "TypleObjectRolePropertyType",
			//Theme might be redundant with scope/theme
			TUPLE_THEME_PROPERTY						= "TupleThemePropertyType",
			TUPLE_SIGNATURE_PROPERTY					= "TupleSignaturePropertyType",
		    /** list of symbols of Scope topics */
		    SCOPE_LIST_PROPERTY_TYPE 					= "TupleScopeListPropertyType",
		    
		    ///////////////////////////////////
			// Ontology Modeling, generally used for importing ontologies
			//////////////////////////////////
		    ONTOLOGY_OBJECT_PROPERTY_TYPE				= "OntologyObjectPropertyType",
		    	ONTOLOGY_INVERSE_FUNDTIONAL_PROPERTY_TYPE	= "OntologyInverseFunctionalPropertyType",
		    ONTOLOGY_FUNCTIONAL_PROPERTY_TYPE			= "OntologyFunctionalPropertyType",
		    ONTOLOGY_DATATYPE_PROPERTY_TYPE				= "OntologyDatatypePropertyType",
		    ONTOLOGY_ANNOTATION_PROPERTY_TYPE			= "OntologyAnnotationPropertyType",
		    DOMAIN_PROPERTY_TYPE						= "DomainPropertyType",
		    RANGE_PROPERTY_TYPE							= "RangePropertyType",
		    EQUIVALENT_TO_PROPERTY_TYPE					= "EquivalentToPropertyType",
		    INVERSE_OF_PROPERTY_TYPE					= "InverseOfPropertyType",

			
			///////////////////////////////////
		    //from IConceptualGraph  not used anytime soon
		    ///////////////////////////////////
		    GRAPH_CONCEPT_LIST_PROPERTY_TYPE			= "graphconlist",
		    GRAPH_RELATION_LIST_PROPERTY_TYPE			= "graphrelnlist",
		    GRAPH_PARENT_GRAPH_PROPERTY_TYPE			= "graphparent";
		
		    
			

			
	  
}
