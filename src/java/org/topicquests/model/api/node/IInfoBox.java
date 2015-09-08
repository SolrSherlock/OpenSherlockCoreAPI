/**
 * 
 */
package org.topicquests.model.api.node;

/**
 * @author park
 * An InfoBox is a <em>named</em> object which constitutes
 * a named JSON string
 */
public interface IInfoBox {
	public static final String
		CREATOR_ID		= "creator",
		DATE			= "dat",
		LAST_EDIT_DATE	= "leDat",
		NAME			= "name";
	
	void setName(String name);
	String getName();
	void setCreatorId(String creatorId);
	String getCreatorId();
	void setDate(String date);
	String getDate();
	void setLastEditDate(String date);
	String getLastEditDate();
	
	//////////////////////////////////////
	// The API of an InfoBox is to;
	// 1- take care of provenance
	// 2- allow for adding any information which can
	//    be represented as JSON, either a string, or a collection
	//    of string objects
	// 3- export to a JSON string
	//////////////////////////////////////
	void setProperty(String key, Object value);
	Object getProperty(String key);
	
	String toJSON();

}
