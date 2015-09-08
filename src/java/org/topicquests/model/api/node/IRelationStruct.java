/**
 * 
 */
package org.topicquests.model.api.node;

/**
 * @author park
 * 
 */
public interface IRelationStruct {
	//must be same as in TQTopicMap.relnstruct
	public static final String
		RELATION_TYPE		= "relationType",
		RELATION_LOCATOR	= "relationLocator",
		RELATION_LABEL		= "relationLabel",
		TARGET_TYPE			= "documentType",
		TARGET_ICON			= "documentSmallIcon",
		TARGET_LOCATOR		= "documentLocator",
		TARGET_LABEL		= "documentLabel",
		SOURCE_OR_TARGET	= "sORt";

	void setRelationLocator(String locator);
	String getRelationLocator();
	void setRelationType(String type);
	String getRelationType();
	void setRelationLabel(String label);
	String getRelationLabel();
	void setTargetIcon(String iconPath);
	String getTargetIcon();
	void setTargetLocator(String locator);
	String getTargetLocator();
	void setTargetLabel(String label);
	String getTargetLabel();
	void setTargetNodeType(String nodeType);
	String getTargetNodeType();
	void setSourceOrTarget(String sORt);
	String getSourceOrTarget();
	
	String toJSON();
}
