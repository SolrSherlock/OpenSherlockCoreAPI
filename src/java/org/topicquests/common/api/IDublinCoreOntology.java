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
 * @author Park
 */
public interface IDublinCoreOntology {
	  public static final String uri 			= "http://purl.org/dc/elements/1.0/",

	  				DUBLIN_CORE_LEGEND_ROOT 			= "DublinCoreLegendRoot",
					  
					  DC_CREATOR 		= "DublinCoreCreatorProperty",
					  DC_TITLE 			= "DublinCoreTitleProperty",
					  DC_CONTRIBUTOR 	= "DublinCoreContributorProperty",
					  DC_COVERAGE 		= "DublinCoreCoverageProperty",
					  DC_DATE 			= "DublinCoreDateProperty",
					  DC_DESCRIPTION 	= "DublinCoreDescriptionProperty",
					  DC_FORMAT 		= "DublinCoreFormatProperty",
					  DC_LANGUAGE 		= "DublinCoreLanguageProperty",
					  DC_IDENTIFIER 	= "DublinCoreIdentifierProperty",
					  DC_PUBLISHER 		= "DublinCorePublisherProperty",
					  DC_RELATION 		= "DublinCoreRelationProperty",
					  DC_RIGHTS 		= "DublinCoreRightsProperty",
					  DC_SOURCE 		= "DublinCoreSourceProperty",
					  DC_SUBJECT 		= "DublinCoreSubjectProperty",
					  DC_TYPE 			= "DublinCoreTypeProperty";
}
