/**
 * 
 */
package org.topicquests.model.util;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.topicquests.model.api.node.IInfoBox;

/**
 * @author park
 *
 */
public class InfoBox implements IInfoBox {
	private JSONObject data;

	/**
	 * 
	 */
	public InfoBox() {
		data = new JSONObject();
	}
	
	public InfoBox(String json) throws Exception {
		JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
		data = (JSONObject)p.parse(json);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		data.put(IInfoBox.NAME, name);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#getName()
	 */
	@Override
	public String getName() {
		return (String)data.get(IInfoBox.NAME);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#setCreatorId(java.lang.String)
	 */
	@Override
	public void setCreatorId(String creatorId) {
		data.put(IInfoBox.CREATOR_ID, creatorId);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#getCreatorId()
	 */
	@Override
	public String getCreatorId() {
		return (String)data.get(IInfoBox.CREATOR_ID);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#setDate(java.lang.String)
	 */
	@Override
	public void setDate(String date) {
		data.put(IInfoBox.DATE, date);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#getDate()
	 */
	@Override
	public String getDate() {
		return (String)data.get(IInfoBox.DATE);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#setLastEditDate(java.lang.String)
	 */
	@Override
	public void setLastEditDate(String date) {
		data.put(IInfoBox.LAST_EDIT_DATE, date);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#getLastEditDate()
	 */
	@Override
	public String getLastEditDate() {
		return (String)data.get(IInfoBox.LAST_EDIT_DATE);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String key, Object value) {
		data.put(key, value);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#getProperty(java.lang.String)
	 */
	@Override
	public Object getProperty(String key) {
		return data.get(key);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.node.IInfoBox#toJSON()
	 */
	@Override
	public String toJSON() {
		return data.toJSONString();
	}

}
