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
package org.topicquests.model;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.nex.util.DateUtil;
import org.topicquests.model.api.IValueMatrix;
import org.topicquests.model.api.IXMLFields;
import org.topicquests.model.api.legends.IEventLegend;
import org.topicquests.model.api.legends.IPersonLegend;
import org.topicquests.model.api.node.IAddressableInformationResource;
import org.topicquests.model.api.node.ICitation;
import org.topicquests.model.api.node.IConceptualGraph;
import org.topicquests.model.api.node.INode;
import org.topicquests.model.api.node.IPersonEvent;
import org.topicquests.model.api.node.ITuple;
import org.topicquests.model.api.query.INodeQuery;
import org.topicquests.common.ResultPojo;
import org.topicquests.common.api.IResult;
import org.topicquests.common.api.ITopicQuestsOntology;
import org.topicquests.common.api.IRelationsLegend;
import org.topicquests.util.JSONUtil;

/**
 * @author park
 * <p>This is a plain Node implementation: it stores dates as their
 * 	long timestamp</p>
 */
public class Node implements 
		INode, ITuple, ICitation, 
		IValueMatrix, IConceptualGraph, IPersonEvent,
		IAddressableInformationResource {
	/** data */
	private JSONObject properties;
	/** AIRs can be created native to this topic, or they can
	 * be transcluded in from other topics
	 */
	private List<IAddressableInformationResource> airs = null;
	
	/**
	 * Generic constructor
	 */
	public Node() {
		properties = new JSONObject();
		//never return a null list
		this.setTransitiveClosure(new ArrayList<String>());
	}

	/**
	 * Constructor from a {@link JSONObject}
	 * @param jo
	 */
	public Node(JSONObject jo) {
		properties = jo;
	}
	
	//////////////////////////////////////
	// CORE INode
	//////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getLocator()
	 */
	@Override
	public String getLocator() {
		return (String)properties.get(ITopicQuestsOntology.LOCATOR_PROPERTY);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getLabel()
	 */
	@Override
	public String getLabel(String language) {
		String field = makeField(ITopicQuestsOntology.LABEL_PROPERTY,language);
		return getFirstListValue(field);
	}
	
	@Override
	public String makeField(String fieldBase, String language) {
		String result = fieldBase;
		if (!language.equals("en"))
			result += language;
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getNodeType()
	 */
	@Override
	public String getNodeType() {
		return (String)properties.get(ITopicQuestsOntology.INSTANCE_OF_PROPERTY_TYPE);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getSmallImage()
	 */
	@Override
	public String getSmallImage() {
		return (String)properties.get(ITopicQuestsOntology.SMALL_IMAGE_PATH);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getImage()
	 */
	@Override
	public String getImage() {
		return (String)properties.get(ITopicQuestsOntology.LARGE_IMAGE_PATH);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getCreatorId()
	 */
	@Override
	public String getCreatorId() {
		return (String)properties.get(ITopicQuestsOntology.CREATOR_ID_PROPERTY);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getDetails()
	 */
	@Override
	public String getDetails(String language) {
		String field = makeField(ITopicQuestsOntology.DETAILS_PROPERTY,language);
		return getFirstListValue(field);
	}


	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setLabel(java.lang.String)
	 * IS MULTIVALUED
	 */
	@Override
	public void addLabel(String label, String language, String userId, boolean isLanguageAddition) {
		String field = makeField(ITopicQuestsOntology.LABEL_PROPERTY,language);
		addMultivaluedSetStringProperty(field, label);
	}


	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setDetails(java.lang.String)
	 */
	@Override
	public void addDetails(String details, String language, String userId, boolean isLanguageAddition) {
		String field = makeField(ITopicQuestsOntology.DETAILS_PROPERTY,language);
		addMultivaluedSetStringProperty(field, details);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setLocator(java.lang.String)
	 */
	@Override
	public void setLocator(String locator) {
		properties.put(ITopicQuestsOntology.LOCATOR_PROPERTY, locator);
	}

	
	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setDate(java.util.Date)
	 */
	@Override
	public void setDate(Date date) {
		properties.put(ITopicQuestsOntology.CREATED_DATE_PROPERTY, DateUtil.defaultTimestamp(date));
	}

	@Override
	public void setLastEditDate(Date date) {
		properties.put(ITopicQuestsOntology.LAST_EDIT_DATE_PROPERTY, DateUtil.defaultTimestamp(date));
	}

	
	@Override
	public Date getDate() {
		String dx = (String)properties.get(ITopicQuestsOntology.CREATED_DATE_PROPERTY);
		return DateUtil.fromDefaultTimestamp(dx);
	}
	
	@Override
	public Date getLastEditDate() {
		String dx = (String)properties.get(ITopicQuestsOntology.LAST_EDIT_DATE_PROPERTY);
		return DateUtil.fromDefaultTimestamp(dx);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setSmallImage(java.lang.String)
	 */
	@Override
	public void setSmallImage(String img) {
		properties.put(ITopicQuestsOntology.SMALL_IMAGE_PATH, img);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setImage(java.lang.String)
	 */
	@Override
	public void setImage(String img) {
		properties.put(ITopicQuestsOntology.LARGE_IMAGE_PATH, img);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setCreatorId(java.lang.String)
	 */
	@Override
	public void setCreatorId(String id) {
		properties.put(ITopicQuestsOntology.CREATOR_ID_PROPERTY, id);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setNodeType(java.lang.String)
	 */
	@Override
	public void setNodeType(String typeLocator) {
		properties.put(ITopicQuestsOntology.INSTANCE_OF_PROPERTY_TYPE, typeLocator);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#addSuperclassId(java.lang.String)
	 */
	@Override
	public void addSuperclassId(String superclassLocator) {
		List<String> ids = listSuperclassIds();
		if (ids == null || ids.size() == 0) {
			ids = new ArrayList<String>();
			properties.put(ITopicQuestsOntology.SUBCLASS_OF_PROPERTY_TYPE, ids);
		}
		if (!ids.contains(superclassLocator))
			ids.add(superclassLocator);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#listSuperclassIds()
	 */
	@Override
	public List<String> listSuperclassIds() {
		return getMultivaluedProperty(ITopicQuestsOntology.SUBCLASS_OF_PROPERTY_TYPE);
	}

	@Override
	public void addAIR(IAddressableInformationResource air) {
		if (airs == null)
			airs = new ArrayList<IAddressableInformationResource>();
		air.addHostProxyLocator(this.getLocator());
		air.setPurpleNumber(Integer.toString(airs.size()+1), this.getLocator());
		this.addMultivaluedSetStringProperty(ITopicQuestsOntology.AIR_LIST_PROPERTY_TYPE, air.toJSON());
	}

	@Override
	public IResult getAIR(String airLocator) {
		IResult result = new ResultPojo();
		if (airs != null && !airs.isEmpty()) {
			IAddressableInformationResource a;
			Iterator<IAddressableInformationResource>itr = airs.iterator();
			while (itr.hasNext()) {
				a = itr.next();
				if (a.getLocator().equals(airLocator)) {
					result.setResultObject(a);
					return result;
				}
			}
			
		} else {
			List<String>airx = (List<String>)properties.get(ITopicQuestsOntology.AIR_LIST_PROPERTY_TYPE);
			if (airx != null) {
				String lox = "\""+airLocator+"\"";
				String aix;
				Iterator<String>itr = airx.iterator();
				int where = -1;
				while (itr.hasNext()) {
					aix = itr.next();
					if (aix.indexOf(lox) > -1) {
						JSONParser p = new JSONParser();
						try {
							result.setResultObject(this.jsonStringToAIR(aix, p));
						} catch (Exception e) {
							result.addErrorString(e.getMessage());
							e.printStackTrace();
						}
					}
						
				}
			}
		}
		return result;
	}

/*	@Override
	public List<String> listAIRLocators() {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public IResult listAIRs() {
		IResult result = new ResultPojo();
		if (airs != null && !airs.isEmpty())
			result.setResultObject(airs);
		else {
			List<String>airx = (List<String>)properties.get(ITopicQuestsOntology.AIR_LIST_PROPERTY_TYPE);
			if (airx != null) {
				String aix;
				Iterator<String>itr = airx.iterator();
				try {
					JSONParser p = new JSONParser();
					airs = new ArrayList<IAddressableInformationResource>();
					while (itr.hasNext()) {
						aix = itr.next();
						airs.add(this.jsonStringToAIR(aix, p));
					}
					result.setResultObject(airs);
				} catch (Exception e) {
					result.addErrorString(e.getMessage());
					e.printStackTrace();
				}
			}		}
		return result;
	}

	@Override
	public void removeAIR(String airLocator) {
		//deal with AIRS
		IAddressableInformationResource toRemove = null;
		if (airs != null && !airs.isEmpty()) {
			IAddressableInformationResource a;
			Iterator<IAddressableInformationResource>itr = airs.iterator();
			while (itr.hasNext()) {
				a = itr.next();
				if (a.getLocator().equals(airLocator)) {
					toRemove = a;
				}
			}
			
		} 
		//deal with properties
		String as = null;
		List<String>airx = (List<String>)properties.get(ITopicQuestsOntology.AIR_LIST_PROPERTY_TYPE);
		if (airx != null) {
			String lox = "\""+airLocator+"\"";
			String aix;
			Iterator<String>itr = airx.iterator();
			int where = -1;
			while (itr.hasNext()) {
				aix = itr.next();
				if (aix.indexOf(lox) > -1) {
					as = aix;
				}
					
			}
		}
		if (as != null) {
			airx.remove(as);
			properties.put(ITopicQuestsOntology.AIR_LIST_PROPERTY_TYPE, airx);
		}
		
	}

	@Override
	public List<String> listTransitiveClosure() {
		List<String>result = (List<String>)properties.get(ITopicQuestsOntology.TRANSITIVE_CLOSURE_PROPERTY_TYPE);
		return result;
	}

	@Override
	public void setTransitiveClosure(List<String> tc) {
		properties.put(ITopicQuestsOntology.TRANSITIVE_CLOSURE_PROPERTY_TYPE, tc);
	}

	@Override
	public void addTransitiveClosureLocator(String locator) {
		List<String>result = (List<String>)properties.get(ITopicQuestsOntology.TRANSITIVE_CLOSURE_PROPERTY_TYPE);
		//should never be null, but must look
		if (result == null)
			result = new ArrayList<String>();
		if (!result.contains(locator)) {
			result.add(locator);
			properties.put(ITopicQuestsOntology.TRANSITIVE_CLOSURE_PROPERTY_TYPE, result);
		}
		
	}

	@Override
	public void removeTransitiveClosureLocator(String locator) {
		List<String>result = (List<String>)properties.get(ITopicQuestsOntology.TRANSITIVE_CLOSURE_PROPERTY_TYPE);
		//should never be null, but must look
		if (result != null) {
			result.remove(locator);
			properties.put(ITopicQuestsOntology.TRANSITIVE_CLOSURE_PROPERTY_TYPE, result);
		}
	}
	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setIsPrivate(boolean)
	 */
	@Override
	public void setIsPrivate(boolean isPrivate) {
		String x = (isPrivate ? "true":"false");
		properties.put(ITopicQuestsOntology.IS_PRIVATE_PROPERTY, x);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getIsPrivate()
	 */
	@Override
	public boolean getIsPrivate() {
		Object x = properties.get(ITopicQuestsOntology.IS_PRIVATE_PROPERTY);
		if (x != null) {
			if (x instanceof String) {
				if (x != null) {
					return Boolean.parseBoolean((String)x);
				}
			} else {
				return ((Boolean)x).booleanValue();
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setURL(java.lang.String)
	 */
	@Override
	public void setURL(String url) {
		properties.put(ITopicQuestsOntology.RESOURCE_URL_PROPERTY, url);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getURL()
	 */
	@Override
	public String getURL() {
		return (String)properties.get(ITopicQuestsOntology.RESOURCE_URL_PROPERTY);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#toMap()
	 */
	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}

	@Override
	public List<String> listRestrictionCredentials() {
		List<String> result = getMultivaluedProperty(ITopicQuestsOntology.RESTRICTION_PROPERTY_TYPE);
		return result;
	}

	/**
	 * <p>Utility method for multivalued properties which may return a String</p>
	 * <p>If this turns a string value into a list value, it installs that list in properties</p>
	 * @param key
	 * @return does not return <code>null</code>
	 */
	List<String> getMultivaluedProperty(String key) {
		List<String> result = null;
		Object op = properties.get(key);
		if (op != null) {
			if (op instanceof String) {
				result = new ArrayList<String>();
				result.add((String)op);
			} else {
				result = (List<String>)op;
			} 
		} else {
			result = new ArrayList<String>();
			properties.put(key, result);
		}
		return result;
	}
	
	@Override
	public void addRestrictionCredential(String userId) {
		List<String> l = getMultivaluedProperty(ITopicQuestsOntology.RESTRICTION_PROPERTY_TYPE);
		if (!l.contains(userId))
			l.add(userId);
	}

	@Override
	public void removeRestrictionCredential(String userId) {
		List<String> l = (List<String>)properties.get(ITopicQuestsOntology.RESTRICTION_PROPERTY_TYPE);
		if (l != null)
			l.remove(userId);
	}

	@Override
	public boolean containsRestrictionCredentials(String userId) {
		List<String> creds = listRestrictionCredentials();
		return creds.contains(userId);
	}
	
	@Override
	public void addPSI(String psi) {
//		properties.put(ITopicQuestsOntology.PSI_PROPERTY_TYPE, psi);
		List<String> ids = getMultivaluedProperty(ITopicQuestsOntology.PSI_PROPERTY_TYPE);
		// no duplicates allowed
		if (!ids.contains(psi))
			ids.add(psi);
	}

	@Override
	public List<String> listPSIValues() {
		List<String> result = getMultivaluedProperty(ITopicQuestsOntology.PSI_PROPERTY_TYPE);
		return result;
	}

//TODO HUGE ISSUE WITH internal updates -- must do version if _version_ is available

	@Override
	public void addTuple(String tupleLocator) {
//		System.out.println("ADDTUPLE- "+properties);
		addMultivaluedSetStringProperty(ITopicQuestsOntology.TUPLE_LIST_PROPERTY,tupleLocator);
//		System.out.println("ADDTUPLE-0 "+properties.get(ITopicQuestsOntology.TUPLE_LIST_PROPERTY));
	}
	@Override
	public void addRestrictedTuple(String tupleLocator) {
//		System.out.println("ADDTUPLERestricted- "+properties);
		addMultivaluedSetStringProperty(ITopicQuestsOntology.TUPLE_LIST_PROPERTY_RESTRICTED,tupleLocator);
//		System.out.println("ADDTUPLERestricted-0 "+properties.get(ITopicQuestsOntology.TUPLE_LIST_PROPERTY_RESTRICTED));
	}

	@Override
	public List<String> listTuples() {
		List<String> result = this.getMultivaluedProperty(ITopicQuestsOntology.TUPLE_LIST_PROPERTY);
		if (result == null)
			result = new ArrayList<String>();
		return result;
	}

	@Override
	public List<String> listRestrictedTuples() {
		List<String> result = this.getMultivaluedProperty(ITopicQuestsOntology.TUPLE_LIST_PROPERTY_RESTRICTED);
		if (result == null)
			result = new ArrayList<String>();
		return result;
	}

	@Override
	public List<String> listLabels() {
		List<String> l = fetchLabels(ITopicQuestsOntology.LABEL_PROPERTY);
		return concatinateStringLists(l);
	}

	@Override
	public List<String> listDetails() {
		List<String> l = fetchLabels(ITopicQuestsOntology.DETAILS_PROPERTY);
		return concatinateStringLists(l);
	}

	@Override
	public void setObject(String value) {
		properties.put(ITopicQuestsOntology.TUPLE_OBJECT_PROPERTY, value);
	}

	@Override
	public void setObjectType(String typeLocator) {
		properties.put(ITopicQuestsOntology.TUPLE_OBJECT_TYPE_PROPERTY, typeLocator);
	}

	@Override
	public String getObject() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_OBJECT_PROPERTY);
	}

	@Override
	public String getObjectType() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_OBJECT_TYPE_PROPERTY);
	}

	@Override
	public void setSubjectLocator(String locator) {
		properties.put(ITopicQuestsOntology.TUPLE_SUBJECT_PROPERTY, locator);
	}

	@Override
	public String getSubjectLocator() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_SUBJECT_PROPERTY);
	}

	@Override
	public void setSubjectType(String subjectType) {
		properties.put(ITopicQuestsOntology.TUPLE_SUBJECT_TYPE_PROPERTY, subjectType);
	}

	@Override
	public String getSubjectType() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_SUBJECT_TYPE_PROPERTY);
	}
/**
	public void setRelationType(String typeLocator) {
		properties.put(ITopicQuestsOntology.TUPLE_RELATION_TYPE_PROPERTY, typeLocator);
	}

	public String getRelationType() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_RELATION_TYPE_PROPERTY);
	}

	public void setRelationLocator(String relationLocator) {
		properties.put(ITopicQuestsOntology.TUPLE_RELATION_LOCATOR_PROPERTY, relationLocator);
	}

	public String getRelationLocator() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_RELATION_LOCATOR_PROPERTY);
	}
*/
	@Override
	public void setIsTransclude(boolean isT) {
		String x = (isT ? "true":"false");
		properties.put(ITopicQuestsOntology.TUPLE_IS_TRANSCLUDE_PROPERTY, x);
	}

	@Override
	public boolean getIsTransclude() {
		Object x = properties.get(ITopicQuestsOntology.TUPLE_IS_TRANSCLUDE_PROPERTY);
		if (x != null) {
			if (x instanceof String) {
				if (x != null) {
					return Boolean.parseBoolean((String)x);
				}
			} else {
				return ((Boolean)x).booleanValue();
			}
		}
		return false;
	}

	@Override
	public String toJSON() {
		return properties.toJSONString();
	}

	@Override
	public IResult doUpdate() {
		IResult result = new ResultPojo();
		IResult temp;
		//change lastEditDate
		setLastEditDate(new Date());
		return result;
	}


	@Override
	public String toXML() {
		StringBuilder buf = new StringBuilder();
		String nx = IXMLFields.NODE;
		String test = getSubjectLocator();
		//is this a node or a tuple?
		if (isTuple())
			nx = IXMLFields.TUPLE;
		buf.append("<"+nx+" "+IXMLFields.LOCATOR_ATT+"=\""+getLocator()+"\">\n");
		Iterator<String> keys = this.properties.keySet().iterator();
		Iterator<String>itrA, itrB;
		String key, key2, key3;
		Object val;
		Map<String,Map<String,String>>airx;
		Map<String,String>airy;
		String sval;
		while (keys.hasNext()) {
			key = keys.next();
			if (!key.equals(ITopicQuestsOntology.LOCATOR_PROPERTY)) {
				if (key.equals(ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE)) {
					airx = (Map<String,Map<String,String>>)properties.get(key);
					if (airx != null) {
						buf.append("  <"+IXMLFields.PROPERTY+" "+IXMLFields.KEY_ATT+"=\""+key+"\" >\n");
						//Take apart this map
						itrA = airx.keySet().iterator();
						while (itrA.hasNext()) {
							key2 = itrA.next();
							buf.append("  <"+IXMLFields.PROPERTY+" "+IXMLFields.KEY_ATT+"=\""+ITopicQuestsOntology.AIR_HOST_PROPERTY_TYPE+"\" >\n");
							//key2 is airhostlocator
							buf.append("    <"+IXMLFields.VALUE+"><![CDATA["+key2+"]]></"+IXMLFields.VALUE+">\n");
							buf.append("  </"+IXMLFields.PROPERTY+">\n");
							airy = airx.get(key2);
							if (airy != null) {
								itrB = airy.keySet().iterator();
								while (itrB.hasNext()) {
									key3 = itrB.next();
									//key3 is an Air-related key: parent or purple
									sval = airy.get(key3);
									if (sval != null) {
									buf.append("    <"+IXMLFields.PROPERTY+" "+IXMLFields.KEY_ATT+"=\""+key3+"\" >\n");
									buf.append("      <"+IXMLFields.VALUE+"><![CDATA["+sval+"]]></"+IXMLFields.VALUE+">\n");
									buf.append("    </"+IXMLFields.PROPERTY+">\n");
									}
								}
							}
						}
						buf.append("  </"+IXMLFields.PROPERTY+">\n");
					}
					
				} else {
					buf.append("  <"+IXMLFields.PROPERTY+" "+IXMLFields.KEY_ATT+"=\""+key+"\" >\n");
					val = properties.get(key);
					appendValue(val,buf,key);
					buf.append("  </"+IXMLFields.PROPERTY+">\n");
				}
			}
		}
		buf.append("</"+nx+">\n");
//		System.out.println("XXXX "+buf.toString());
		return buf.toString();
	}

	private void appendValue(Object value, StringBuilder buf, String key) {
		if (value instanceof List) {
			Iterator<String>itr = ((List<String>)value).iterator();
			while (itr.hasNext()) 
				buf.append("    <"+IXMLFields.VALUE+"><![CDATA["+itr.next()+"]]></"+IXMLFields.VALUE+">\n");

		} else {
			//MUST TEST FOR DATES
			if (key.equals(ITopicQuestsOntology.CREATED_DATE_PROPERTY) || 
				key.equals(ITopicQuestsOntology.LAST_EDIT_DATE_PROPERTY)) {
				//Theoretically speaking, we don't store Date objects here
				if (value instanceof Date) {
					Date d = (Date)(value); //
					buf.append("    <"+IXMLFields.VALUE+"><![CDATA["+d.getTime()+"]]></"+IXMLFields.VALUE+">\n");
				} else
					buf.append("    <"+IXMLFields.VALUE+"><![CDATA["+value+"]]></"+IXMLFields.VALUE+">\n");
			} else
				buf.append("    <"+IXMLFields.VALUE+"><![CDATA["+value+"]]></"+IXMLFields.VALUE+">\n");
		}
	}

	@Override
	public boolean isTuple() {
		String test = getSubjectLocator();
		//is this a node or a tuple?
		if (test != null && ! test.equals(""))
			return true;
		return false;
	}

	@Override
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}

	@Override
	public Object getProperty(String key) {
		return properties.get(key);
	}

	@Override
	public void addPropertyValue(String key, String value) {
		Object vx = getProperty(key);
		if (vx == null)
			setProperty(key,value);
		else {
			List<String>vl = null;
			if (vx instanceof String) {
				vl = new ArrayList<String>();
				vl.add((String)vx);
				properties.put(key, vl);
			} else
				vl = (List<String>)vx;
			vl.add(value);
		}
		
	}

	@Override
	public void setIsTransclude(String t) {
		if (t.equalsIgnoreCase("true"))
			setIsTransclude(true);
		else
			setIsTransclude(false);
	}

	@Override
	public void setIsPrivate(String t) {
		if (t.equalsIgnoreCase("true"))
			setIsPrivate(true);
		else
			setIsPrivate(false);		
	}

	//////////////////////////////////////
	// CORE ITuple
	//////////////////////////////////////

	@Override
	public void setObjectRole(String roleLocator) {
		properties.put(ITopicQuestsOntology.TUPLE_OBJECT_ROLE_PROPERTY, roleLocator);
	}

	@Override
	public String getObjectRole() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_OBJECT_ROLE_PROPERTY);
	}

	@Override
	public void setSubjectRole(String roleLocator) {
		properties.put(ITopicQuestsOntology.TUPLE_SUBJECT_ROLE_PROPERTY, roleLocator);
	}

	@Override
	public String getSubjectRole() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_SUBJECT_ROLE_PROPERTY);
	}

	@Override
	public void addScope(String scopeLocator) {
		this.addPropertyValue(ITopicQuestsOntology.SCOPE_LIST_PROPERTY_TYPE, scopeLocator);
	}

	@Override
	public List<String> listScopes() {
		return this.getMultivaluedProperty(ITopicQuestsOntology.SCOPE_LIST_PROPERTY_TYPE);
	}

	@Override
	public void setRelationWeight(double weight) {
		properties.put(IRelationsLegend.RELATION_WEIGHT, new Double(weight));
	}

	@Override
	public double getRelationWeight() {
		Double d = (Double)properties.get(IRelationsLegend.RELATION_WEIGHT);
		if (d == null)
			return -9999;
		return d.doubleValue();
	}

	@Override
	public void addSmallLabel(String label, String language, String userId,
			boolean isLanguageAddition) {
		String field = makeField(ITopicQuestsOntology.SMALL_LABEL_PROPERTY,language);
		addMultivaluedSetStringProperty(field, label);
	}

	@Override
	public String getSmallLabel(String language) {
		String field = makeField(ITopicQuestsOntology.SMALL_LABEL_PROPERTY,language);
		return getFirstListValue(field);
	}

	String getFirstListValue(String key) {
		List<String> l = this.getMultivaluedProperty(key);
		if (l.size()> 0)
			return l.get(0);		
		return "";
	}
	@Override
	public List<String> listLabels(String language) {
		String field = makeField(ITopicQuestsOntology.LABEL_PROPERTY,language);
		return this.getMultivaluedProperty(field);
	}

	@Override
	public List<String> listSmallLabels() {
		List<String> l = fetchLabels(ITopicQuestsOntology.SMALL_LABEL_PROPERTY);
		return concatinateStringLists(l);
	}

	@Override
	public List<String> listSmallLabels(String language) {
		String field = makeField(ITopicQuestsOntology.SMALL_LABEL_PROPERTY,language);
		return this.getMultivaluedProperty(field);
	}

	@Override
	public List<String> listDetails(String language) {
		String field = makeField(ITopicQuestsOntology.DETAILS_PROPERTY,language);
		return this.getMultivaluedProperty(field);
	}

	/**
	 * Concatinate all the List<String> values indexed by <code>keys</code>
	 * @param keys
	 * @return
	 */
	List<String> concatinateStringLists(List<String>keys) {
		List<String>result = new ArrayList<String>();
		int len= keys.size();
		String key;
		for (int i=0;i<len;i++) {
			key = keys.get(i);
			result.addAll(this.getMultivaluedProperty(key));
		}
		return result;
	}

	@Override
	public List<String> listConceptLocators() {
		List<String> result = getMultivaluedProperty(ITopicQuestsOntology.GRAPH_CONCEPT_LIST_PROPERTY_TYPE);
		return result;
	}

	@Override
	public void addConceptLocator(String locator) {
		addMultivaluedSetStringProperty(ITopicQuestsOntology.GRAPH_CONCEPT_LIST_PROPERTY_TYPE,locator);
	}

	@Override
	public void removeConceptLocator(String locator) {
		List<String> l = (List<String>)properties.get(ITopicQuestsOntology.GRAPH_CONCEPT_LIST_PROPERTY_TYPE);
		if (l != null)
			l.remove(locator);
	}

	@Override
	public List<String> listRelationLocators() {
		List<String> result = getMultivaluedProperty(ITopicQuestsOntology.GRAPH_RELATION_LIST_PROPERTY_TYPE);
		return result;
	}

	@Override
	public void addRelationLocator(String locator) {
		addMultivaluedSetStringProperty(ITopicQuestsOntology.GRAPH_RELATION_LIST_PROPERTY_TYPE,locator);
	}

	@Override
	public void removeRelationLocator(String locator) {
		List<String> l = (List<String>)properties.get(ITopicQuestsOntology.GRAPH_RELATION_LIST_PROPERTY_TYPE);
		if (l != null)
			l.remove(locator);
	}

	@Override
	public String getParentGraphLocator() {
		return (String)properties.get(ITopicQuestsOntology.GRAPH_PARENT_GRAPH_PROPERTY_TYPE);
	}

	@Override
	public void setParentGraphLocator(String locator) {
		properties.put(ITopicQuestsOntology.GRAPH_PARENT_GRAPH_PROPERTY_TYPE, locator);
	}

	@Override
	public boolean isA(String typeLocator) {
		if (this.getNodeType().equals(typeLocator))
			return true;
		List<String>sups = this.listTransitiveClosure();
		if (sups != null && !sups.isEmpty())
			return sups.contains(typeLocator);
		return false;
	}

	@Override
	public void setFirstName(String firstName) {
		properties.put(IPersonLegend.FIRST_NAME_PROPERTY, firstName);
	}

	@Override
	public String getFirstName() {
		return (String)properties.get(IPersonLegend.FIRST_NAME_PROPERTY);
	}

	@Override
	public void setMiddleNames(String middleNames) {
		properties.put(IPersonLegend.MIDDLE_NAMES_PROPERTY, middleNames);
	}

	@Override
	public String getMiddleNames() {
		return (String)properties.get(IPersonLegend.MIDDLE_NAMES_PROPERTY);
	}

	@Override
	public void setFamilyName(String familyName) {
		properties.put(IPersonLegend.FAMILY_NAME_PROPERTY, familyName);
	}

	@Override
	public String getFamilyName() {
		return (String)properties.get(IPersonLegend.FAMILY_NAME_PROPERTY);
	}

	@Override
	public void setNameAppendages(String appendages) {
		properties.put(IPersonLegend.NAME_APPENDAGES, appendages);
	}

	@Override
	public String getNameAppendages() {
		return (String)properties.get(IPersonLegend.NAME_APPENDAGES);
	}

	@Override
	public void setStartDate(Date startDate) {
		properties.put(IEventLegend.STARTING_DATE_PROPERTY, DateUtil.defaultTimestamp(startDate));
	}
	@Override
	public Date getStartDate() {
		String dx = (String)properties.get(IEventLegend.STARTING_DATE_PROPERTY);
		return DateUtil.fromDefaultTimestamp(dx);
	}

	@Override
	public void setEndDate(Date endDate) {
		properties.put(IEventLegend.ENDING_DATE_PROPERTY, DateUtil.defaultTimestamp(endDate));
	}
	@Override
	public Date getEndDate() {
		String dx = (String)properties.get(IEventLegend.ENDING_DATE_PROPERTY);
		return DateUtil.fromDefaultTimestamp(dx);
	}

	@Override
	public void setLocationOfOrginLocator(String locationLocator) {
		this.setProperty(IEventLegend.LOCATION_OF_ORIGIN_SYMBOL_PROPERTY, locationLocator);
	}

	@Override
	public void setLocationOfOriginName(String locationName) {
		this.setProperty(IEventLegend.LOCATION_OF_ORIGIN_NAME_PROPERTY, locationName);
	}
	@Override
	public String getLocationOfOriginLocator() {
		return (String)getProperty(IEventLegend.LOCATION_OF_ORIGIN_SYMBOL_PROPERTY);
	}

	@Override
	public String getLocationOfOriginName() {
		return (String)getProperty(IEventLegend.LOCATION_OF_ORIGIN_NAME_PROPERTY);
	}


	@Override
	public void addNickName(String nickName) {
		this.addMultivaluedSetStringProperty(IPersonLegend.NIC_NAMES, nickName);
	}

	@Override
	public List<String> listNickNames() {
		return this.getMultivaluedProperty(IPersonLegend.NIC_NAMES);
	}

	@Override
	public void setIsVirtualProxy(boolean t) {
		String x = (t ? "true":"false");
		properties.put(ITopicQuestsOntology.IS_VIRTUAL_PROXY, x);
	}

	@Override
	public boolean getIsVirtualProxy() {
		Object x = properties.get(ITopicQuestsOntology.IS_VIRTUAL_PROXY);
		if (x != null) {
			if (x instanceof String) {
				if (x != null) {
					return Boolean.parseBoolean((String)x);
				}
			} else {
				return ((Boolean)x).booleanValue();
			}
		}
		return false;
	}

	@Override
	public void addMergeReason(String reason) {
		this.addMultivaluedSetStringProperty(ITopicQuestsOntology.MERGE_REASON_RULES_PROPERTY, reason);
	}

	@Override
	public List<String> listMergeReasons() {
		return this.getMultivaluedProperty(ITopicQuestsOntology.MERGE_REASON_RULES_PROPERTY);
	}

	@Override
	public void addMergeTupleLocator(String locator) {
		Object o = properties.get(ITopicQuestsOntology.MERGE_TUPLE_PROPERTY);
		if (o == null)
			properties.put(ITopicQuestsOntology.MERGE_TUPLE_PROPERTY, locator);
		else if (o instanceof String) {
			if (!o.equals(locator)) {
				List<String> x = new ArrayList<String>();
				x.add((String)o);
				x.add(locator);
				properties.put(ITopicQuestsOntology.MERGE_TUPLE_PROPERTY, x);
			}
		} else {
			List<String>x = (List<String>)o;
			if (!x.contains(locator)) {
				x.add(locator);
				properties.put(ITopicQuestsOntology.MERGE_TUPLE_PROPERTY, x);
			}
		}
	}

	@Override
	public String getMergeTupleLocator() {
		Object o = properties.get(ITopicQuestsOntology.MERGE_TUPLE_PROPERTY);
		if (o instanceof List) 
			return ((List<String>)o).get(0);
		else
			return (String)o;
	}

	@Override
	public List<String> listMergeTupleLocators() {
		Object o = properties.get(ITopicQuestsOntology.MERGE_TUPLE_PROPERTY);
		List<String>result = null;
		if (o instanceof List) 
			result = (List<String>)o;
		else {
			result = new ArrayList<String>();
			result.add((String)o);
		}
		return result;
	}

	@Override
	public void setThemeLocator(String themeLocator) {
		properties.put(ITopicQuestsOntology.TUPLE_THEME_PROPERTY, themeLocator);
	}

	@Override
	public String getThemeLocator() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_THEME_PROPERTY);
	}

	@Override
	public void setSignature(String signature) {
		properties.put(ITopicQuestsOntology.TUPLE_SIGNATURE_PROPERTY, signature);
	}

	@Override
	public String getSignature() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_SIGNATURE_PROPERTY);
	}

	//////////////////////////////////////
	// CORE IAddressableInformationResource
	//////////////////////////////////////

	@Override
	public void setSubject(String text, String language) {
		this.addLabel(text, language, this.getCreatorId(), false);
		
	}
	@Override
	public String getSubject(String language) {
		return this.getLabel(language);
	}
	@Override
	public void setBody(String text, String language) {
		this.addDetails(text, language, this.getCreatorId(), false);
	}

	@Override
	public String getBody(String language) {
		return this.getDetails(language);
	}

	@Override
	public void setVersion(int version) {
		properties.put(ITopicQuestsOntology.VERSION_PROPERTY_TYPE, version);
		
	}

	@Override
	public int getVersion() {
		int result = 0;
		String x = (String)properties.get(ITopicQuestsOntology.VERSION_PROPERTY_TYPE);
		if (x != null)
			result = Integer.parseInt(x);
		return result;
	}

	@Override
	public void setPurpleNumber(String purpleNumber, String hostProxyLocator) {
		Map<String,Map<String,String>> m = getModelMap();
		Map<String,String>m2 = m.get(hostProxyLocator);
		if (m2 == null) {
			m2 = new HashMap<String,String>();
			m.put(hostProxyLocator, m2);
		}
		m2.put(ITopicQuestsOntology.AIR_PURPLE_NUMBER_PROPERTY_TYPE, purpleNumber);
		// TODO Auto-generated method stub
		properties.put(ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE, m);
	}

	@Override
	public String getPurpleNumber(String hostProxyLocator) {
		Map<String,Map<String,String>> m = getModelMap();
		Map<String,String>m2 = m.get(hostProxyLocator);
		if (m2 == null)
			return null;
		return (String)m2.get(ITopicQuestsOntology.AIR_PURPLE_NUMBER_PROPERTY_TYPE);
	}

	@Override
	public void addHostProxyLocator(String hostProxyLocator) {
		Map<String,Map<String,String>> m = getModelMap();
		Map<String,String>m2 = m.get(hostProxyLocator);
		if (m2 == null) {
			m2 = new HashMap<String,String>();
			m.put(hostProxyLocator, m2);
			//adding an airhost just creates this new map
			properties.put(ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE, m);
		}
	}

	@Override
	public void setParentNodeLocator(String parentLocator, String hostProxyLocator) {
		Map<String,Map<String,String>> m = getModelMap();
		String lox = hostProxyLocator;
		if (lox == null)
			lox = this.getLocator();
		Map<String,String>m2 = m.get(lox);
		if (m2 == null) {
			m2 = new HashMap<String,String>();
			m.put(lox, m2);
		}
		m2.put(ITopicQuestsOntology.AIR_PARENT_PROPERTY_TYPE, lox);
		properties.put(ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE, m);
	}
	
	@Override
	public String getParentNodeLocator(String hostProxyLocator) {
		Map<String,Map<String,String>> m = getModelMap();
		String lox = hostProxyLocator;
		if (lox == null)
			lox = this.getLocator();
		Map<String,String>m2 = m.get(lox);
		if (m2 != null)
			return (String)m2.get(ITopicQuestsOntology.AIR_PARENT_PROPERTY_TYPE);
		return null;
	}


	/**
	 * Does not return <code>null</code>
	 * @return
	 */
	private Map<String,Map<String,String>> getModelMap() {
		Map<String,Map<String,String>> result = (Map<String,Map<String,String>>)properties.get(ITopicQuestsOntology.AIR_MODEL_PROPERTY_TYPE);
		if (result == null)
			result = new HashMap<String,Map<String,String>>();
		return result;
	}

	//////////////////////////////////////
	// Internal Support
	//////////////////////////////////////

	private void addMultivaluedSetStringProperty(String key, String value) {
		
		Object o = properties.get(key);
		List<String> ll;
		if (o == null) {
			ll = new ArrayList<String>();
		} else if ( o instanceof String) {
			ll = new ArrayList<String>();
			ll.add((String)o);
		} else {
			ll = (List<String>)o;
		}
		if (!ll.contains(value))
			ll.add(value);
		properties.put(key, ll);
	}

	/**
	 * Return an {@link IAddressableInformationResource} from the
	 * given <code>jsonString</code>
	 * @param jsonString
	 * @param p
	 * @return
	 * @throws Exception
	 */
	private IAddressableInformationResource jsonStringToAIR(String jsonString, JSONParser p) throws Exception{
		JSONObject jo = (JSONObject)p.parse(jsonString);
		IAddressableInformationResource result = new Node(jo);
		return result;
	}
	/**
	 * Given a <code>baseField</code>, e.g. "label", fetch all
	 * label fields regardless of language codes
	 * @param baseField
	 * @return
	 */
	private List<String> fetchLabels(String baseField) {
		List<String>result = new ArrayList();
		Iterator<String>itr = properties.keySet().iterator();
		String key;
		while (itr.hasNext()) {
			key = itr.next();
			if (key.startsWith(baseField))
				result.add(key);
		}
		return result;
	}





}
