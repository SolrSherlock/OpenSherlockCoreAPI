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
 *
 */
public interface IHarvestingOntology {
	/**
	 * Properties (fields)
	 */
	public static final String
			CLUSTER_WEIGHT		= "clusterWeight", //implemented as a <code>double</code> field
			/** resource topics as members of a cluster node */
			CLUSTER_MEMBER_LIST = "clusterMembers",
			/** cluster nodes in a cluster */
			CLUSTER_NODE_MEMBER_LIST = "clusterNodeMembers";
			
	
	/**
	 * Types
	 */
	public static final String
			HARVEST_AGENT_USER			= "HarvestAgentUser",
			/** overriding Cluster, based on a query */
			CLUSTER_TYPE				= "ClusterType",
			/** individual cluster node in a cluster, with weights and members */
			CLUSTER_NODE_TYPE			= "ClusterNodeType",
			/** cluster label as a tag */
			CLUSTER_TAG_TYPE			= "ClusterTagType",
			/** nodes created by carrot2 */
			CARROT2_CLUSTERED_NODE_TYPE	= "Carrot2ClusteredNodeType",
			/** Carrot2 as a user agent*/
			CARROT2_AGENT_USER			= "Carrot2AgentUser",
			/** Tuples, when crafted during agent-based harvesting,
			 * need this scope type; it intends to capture the full
			 * measure of provenance of the <em>assertion</em> made
			 * during harvesting.
			 */
			HARVEST_SCOPE_TYPE			= "HarvestScopeType";

}
