/**
 * 
 */
package org.topicquests.model.util;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.topicquests.model.api.node.IAirStruct;
import org.topicquests.model.api.node.IChildStruct;

/**
 * @author park
 *
 */
public class AirStruct implements IAirStruct {
	private JSONObject data;

	/**
	 * 
	 */
	public AirStruct() {
		data = new JSONObject();
	}
	public AirStruct(String json) throws Exception {
		JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
		data = (JSONObject)p.parse(json);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IAirStruct#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		data.put(IAirStruct.TEXT, text);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IAirStruct#getText()
	 */
	@Override
	public String getText() {
		return (String)data.get(IChildStruct.CONTEXT_LOCATOR);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IAirStruct#setLastEditDate(java.lang.String)
	 */
	@Override
	public void setLastEditDate(String date) {
		data.put(IAirStruct.LAST_EDIT_DATE, date);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IAirStruct#getLastEditDate()
	 */
	@Override
	public String getLastEditDate() {
		return (String)data.get(IAirStruct.LAST_EDIT_DATE);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IAirStruct#setEditComment(java.lang.String)
	 */
	@Override
	public void setEditComment(String comment) {
		data.put(IAirStruct.EDIT_COMMENT, comment);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IAirStruct#getEditComment()
	 */
	@Override
	public String getEditComment() {
		return (String)data.get(IAirStruct.EDIT_COMMENT);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IAirStruct#toJSON()
	 */
	@Override
	public String toJSON() {
		return data.toJSONString();
	}
	@Override
	public void setCreator(String creatorId) {
		data.put(IAirStruct.CREATOR_ID, creatorId);
	}
	@Override
	public String getCreatorId() {
		return (String)data.get(IAirStruct.CREATOR_ID);
	}

}
