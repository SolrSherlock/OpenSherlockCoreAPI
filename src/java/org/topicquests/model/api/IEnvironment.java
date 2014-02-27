/**
 * 
 */
package org.topicquests.model.api;

import java.util.*;

import org.topicquests.model.api.provider.IDataProvider;
import org.topicquests.util.Tracer;

/**
 * @author park
 *
 */
public interface IEnvironment {

	IDataProvider getDataProvider();
	
	Map<String,Object> getProperties();
	
	String getStringProperty(String key);
	
	List<List<String>> getListProperty(String key);
	
	void shutDown();
	
	void logDebug(String msg);
	
	void logError(String msg, Exception e);
	
	void record(String msg);
	
	Tracer getTracer(String name);
}
