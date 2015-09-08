/**
 * 
 */
package org.topicquests.model.api.node;

/**
 * @author park
 *
 */
public interface IChildStruct {
	//Must be same as in TQTopicMap.childstruct
	public static final String
		CONTEXT_LOCATOR	= "contextLocator",
		LOCATOR			= "locator",
		ICON			= "smallImagePath",
		SUBJECT			= "subject",
		TRANSCLUDER_ID	= "transcluder";

	void setContextLocator(String context);
	String getContextLocator();
	void setLocator(String locator);
	String getLocator();
	void setSmallIcon(String iconPath);
	String getSmallIcon();
	void setSubject(String subject);
	String getSubject();
	void setTranscluderLocator(String transcluderId);
	String getTranscluderId();
	
	String toJSON();

}
