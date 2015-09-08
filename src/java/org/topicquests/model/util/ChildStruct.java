/**
 * 
 */
package org.topicquests.model.util;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.topicquests.model.api.node.IChildStruct;

/**
 * @author park
 *
 */
public class ChildStruct implements IChildStruct {
	private JSONObject data;

	/**
	 * 
	 */
	public ChildStruct() {
		data = new JSONObject();
	}

	public ChildStruct(String json) throws Exception {
		JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
		data = (JSONObject)p.parse(json);
	}
	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#setContextLocator(java.lang.String)
	 */
	@Override
	public void setContextLocator(String context) {
		data.put(IChildStruct.CONTEXT_LOCATOR, context);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#getContextLocator()
	 */
	@Override
	public String getContextLocator() {
		return (String)data.get(IChildStruct.CONTEXT_LOCATOR);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#setLocator(java.lang.String)
	 */
	@Override
	public void setLocator(String locator) {
		data.put(IChildStruct.LOCATOR, locator);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#getLocator()
	 */
	@Override
	public String getLocator() {
		return (String)data.get(IChildStruct.LOCATOR);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#setSmallIcon(java.lang.String)
	 */
	@Override
	public void setSmallIcon(String iconPath) {
		data.put(IChildStruct.ICON, iconPath);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#getSmallIcon()
	 */
	@Override
	public String getSmallIcon() {
		return (String)data.get(IChildStruct.ICON);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#setTranscluderLocator(java.lang.String)
	 */
	@Override
	public void setTranscluderLocator(String transcluderId) {
		data.put(IChildStruct.TRANSCLUDER_ID, transcluderId);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#getTranscluderId()
	 */
	@Override
	public String getTranscluderId() {
		return (String)data.get(IChildStruct.TRANSCLUDER_ID);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.IChildStruct#toJSON()
	 */
	@Override
	public String toJSON() {
		return data.toJSONString();
	}

	@Override
	public void setSubject(String subject) {
		data.put(IChildStruct.SUBJECT, subject);
	}

	@Override
	public String getSubject() {
		return (String)data.get(IChildStruct.SUBJECT);
	}

}
