/**
 * 
 */
package org.topicquests.model.api.node;

/**
 * @author park
 *
 */
public interface IAirStruct {
	//Must be same as in TQTopicMap.airstruct
	public static final String
		TEXT			= "theText",
		LAST_EDIT_DATE	= "lastEditDate",
		CREATOR_ID	 	= "creatorId",
		EDIT_COMMENT	= "editComment";
	
	void setText(String text);
	String getText();
	void setLastEditDate(String date);
	String getLastEditDate();
	void setEditComment(String comment);
	String getEditComment();
	void setCreator(String creatorId);
	String getCreatorId();
	
	String toJSON();
}
