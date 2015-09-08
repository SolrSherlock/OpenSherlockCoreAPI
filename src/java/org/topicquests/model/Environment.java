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
package org.topicquests.model;

import java.util.List;
import java.util.Map;

import org.nex.config.ConfigPullParser;
import org.topicquests.model.api.IEnvironment;
import org.topicquests.model.api.IMergeImplementation;
import org.topicquests.model.api.provider.IDataProvider;
import org.topicquests.model.api.provider.ITopicDataProvider;
import org.topicquests.model.api.query.IQueryModel;
import org.topicquests.util.LoggingPlatform;
import org.topicquests.util.Tracer;

/**
 * @author park
 *
 */
public class Environment implements IEnvironment {
	public LoggingPlatform log = LoggingPlatform.getInstance("logger.properties");
	private Map<String,Object>props;
	public ITopicDataProvider database;
	public IQueryModel model;

	/**
	 * 
	 * @param p
	 * @param isExtended
	 */
	public Environment(Map<String,Object>p, boolean isExtended) {
		props = p;
		init(isExtended);
	}
	
	/**
	 * Assumes <em>config-props.xml</em> is in the install directory
	 * @param isExtended
	 */
	public Environment(boolean isExtended) {
		ConfigPullParser p = new ConfigPullParser("config-props.xml");
		props = p.getProperties();
	}
	
	/**
	 * <code>isExtended</code> will be <code>true</code> if this
	 * class is extended by another class, in which case, the
	 * database and model features will be handled by it.
	 * @param p
	 * @param isExtended
	 */
	void init(boolean isExtended) {
		if (!isExtended) {
			try {
				//TODO finish this
				int cachesize = Integer.parseInt(getStringProperty("MapCacheSize"));
				String cp = (String)props.get("DataProviderClass");
				Object o;
				if (cp != null) {
					o = 
					database = (ITopicDataProvider)Class.forName(cp).newInstance();
					database.init(this, cachesize);
				}
				IMergeImplementation merger;
				cp = (String)props.get("MergeImplementation");
				//this installation might not deal with merge bean
				if (cp != null) {
					merger = (IMergeImplementation)Class.forName(cp).newInstance();
					merger.init(this);
					database.setMergeBean(merger);
				}
				model = new QueryModel(this);
				String bs = (String)props.get("ShouldBootstrap");
				boolean shouldBootstrap = false; // default value
				if (bs != null)
					shouldBootstrap = bs.equalsIgnoreCase("Yes");
				if (shouldBootstrap)
					bootstrap();
				 
			} catch (Exception e) {
	System.out.println("Environment error "+e.getMessage());
				logError(e.getMessage(),e);
				e.printStackTrace();
			}
		}
		logDebug("Environment Started");
	}
	
	/**
	 * Available to extensions if needed
	 */
	public void bootstrap() {
		//Bootstraps handled by others
/**		CoreBootstrap cbs = new CoreBootstrap(database);
		cbs.bootstrap();
		BiblioBootstrap bbs = new BiblioBootstrap(database);
		bbs.bootstrap();
		RelationsBootstrap rbs = new RelationsBootstrap(database);
		rbs.bootstrap(); */
	}
	
	@Override
	public IDataProvider getDataProvider() {
		return database;
	}
		
	@Override
	public Map<String,Object> getProperties() {
		return props;
	}
	
	@Override
	public String getStringProperty(String key) {
		return (String)props.get(key);
	}
	
	@Override
	public List<List<String>> getListProperty(String key) {
		return (List<List<String>>)props.get(key);
	}

	@Override
	public void shutDown() {
		//
	}

	/////////////////////////////
	// Utilities
	@Override
	public void logDebug(String msg) {
		log.logDebug(msg);
	}
	
	@Override
	public void logError(String msg, Exception e) {
		log.logError(msg,e);
	}
	
	@Override
	public void record(String msg) {
		log.record(msg);
	}

	@Override
	public Tracer getTracer(String name) {
		return log.getTracer(name);
	}


}
