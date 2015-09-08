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

//import org.json.simple.JSONObject;
import net.minidev.json.JSONObject;

import org.nex.util.DateUtil;
import org.topicquests.model.api.node.ICitation;
import org.topicquests.model.api.legends.IEventLegend;
import org.topicquests.model.api.node.IInfoBox;
import org.topicquests.model.api.node.INode;
import org.topicquests.model.api.node.IRelationStruct;
import org.topicquests.model.api.node.IChildStruct;
import org.topicquests.model.api.node.IAirStruct;
import org.topicquests.model.util.ChildStruct;
import org.topicquests.model.util.RelationStruct;
//import org.topicquests.model.util.InfoBox;
import org.topicquests.model.util.AirStruct;
//import org.topicquests.model.api.query.INodeQuery;
import org.topicquests.model.api.node.IPersonEvent;
import org.topicquests.model.api.legends.IPersonLegend;
import org.topicquests.model.api.node.ITuple;
import org.topicquests.model.api.IValueMatrix;
import org.topicquests.model.api.IXMLFields;
import org.topicquests.model.api.node.IConceptualGraph;
import org.topicquests.common.ResultPojo;
import org.topicquests.common.api.IBiblioLegend;
import org.topicquests.common.api.IResult;
import org.topicquests.common.api.ITopicQuestsOntology;
import org.topicquests.common.api.IRelationsLegend;
import org.topicquests.util.JSONUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author park
 * <p>This is a plain Node implementation: it stores dates as their
 * 	long timestamp</p>
 */
public class Node implements 
		INode, ITuple, ICitation, 
		IValueMatrix, IConceptualGraph, IPersonEvent {
	private JSONObject properties;
	
	/**
	 * 
	 */
	public Node() {
		properties = new JSONObject();
	}

	public Node(JSONObject jo) {
		properties = jo;
	}
	
	/**
	 * Constructor used when creating from a Solr hit
	 * @param props 
	 */
	public Node(Map<String,Object> props) {
		properties = JSONUtil.map2JSONObject(props);
	}
	
	 JSONObject jsonToMap(Map<String,Object> props) {
		//TODO this might need to be converted
		return  (JSONObject)props;
	}
	
	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getLocator()
	 */
	public String getLocator() {
		return (String)properties.get(ITopicQuestsOntology.LOCATOR_PROPERTY);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getLabel()
	 */
	public String getLabel(String language) {
		String field = makeField(ITopicQuestsOntology.LABEL_PROPERTY,language);
		return getFirstListValue(field);
	}
	
	public String makeField(String fieldBase, String language) {
		String result = fieldBase;
		if (!language.equals("en"))
			result += language;
		return result;
	}
//	String stripLanguage(String inString) {
//		return inString.substring(0,inString.lastIndexOf('@'));
//	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getNodeType()
	 */
	public String getNodeType() {
		return (String)properties.get(ITopicQuestsOntology.INSTANCE_OF_PROPERTY_TYPE);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getSmallImage()
	 */
	public String getSmallImage() {
		return (String)properties.get(ITopicQuestsOntology.SMALL_IMAGE_PATH);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getImage()
	 */
	public String getImage() {
		return (String)properties.get(ITopicQuestsOntology.LARGE_IMAGE_PATH);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getCreatorId()
	 */
	public String getCreatorId() {
		return (String)properties.get(ITopicQuestsOntology.CREATOR_ID_PROPERTY);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getDetails()
	 */
	public String getDetails(String language) {
		String field = makeField(ITopicQuestsOntology.DETAILS_PROPERTY,language);
		return getFirstListValue(field);
	}


	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setLabel(java.lang.String)
	 * IS MULTIVALUED
	 */
	public void addLabel(String label, String language, String userId, boolean isLanguageAddition) {
		String field = makeField(ITopicQuestsOntology.LABEL_PROPERTY,language);
		addMultivaluedSetStringProperty(field, label);
	}

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

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setDetails(java.lang.String)
	 */
	public void addDetails(String details, String language, String userId, boolean isLanguageAddition) {
		String field = makeField(ITopicQuestsOntology.DETAILS_PROPERTY,language);
		addMultivaluedSetStringProperty(field, details);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setLocator(java.lang.String)
	 */
	public void setLocator(String locator) {
		properties.put(ITopicQuestsOntology.LOCATOR_PROPERTY, locator);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setDate(java.util.Date)
	 * /
	public void setDate(String date) {
		//In Theory, Solr converts this to Z (UTC)
		properties.put(ITopicQuestsOntology.CREATED_DATE_PROPERTY, date);
	} */
	public void setDate(Date date) {
		properties.put(ITopicQuestsOntology.CREATED_DATE_PROPERTY, DateUtil.formatIso8601(date));//DateUtil.defaultTimestamp(date));
	}
	@Override
	public void setDate(String date) {
		properties.put(ITopicQuestsOntology.CREATED_DATE_PROPERTY, date);
	}

	public void setLastEditDate(Date date) {
		properties.put(ITopicQuestsOntology.LAST_EDIT_DATE_PROPERTY, DateUtil.formatIso8601(date));//DateUtil.defaultTimestamp(date));
	}
	@Override
	public void setLastEditDate(String date) {
		properties.put(ITopicQuestsOntology.LAST_EDIT_DATE_PROPERTY, date);//DateUtil.defaultTimestamp(date));
	}
	
	public Date getDate() {
		String dx = (String)properties.get(ITopicQuestsOntology.CREATED_DATE_PROPERTY);
		return DateUtil.fromIso8601(dx); //fromDefaultTimestamp(dx);
	}
	public Date getLastEditDate() {
		String dx = (String)properties.get(ITopicQuestsOntology.LAST_EDIT_DATE_PROPERTY);
		return DateUtil.fromIso8601(dx); //fromDefaultTimestamp(dx);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setSmallImage(java.lang.String)
	 */
	public void setSmallImage(String img) {
		properties.put(ITopicQuestsOntology.SMALL_IMAGE_PATH, img);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setImage(java.lang.String)
	 */
	public void setImage(String img) {
		properties.put(ITopicQuestsOntology.LARGE_IMAGE_PATH, img);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setCreatorId(java.lang.String)
	 */
	public void setCreatorId(String id) {
		properties.put(ITopicQuestsOntology.CREATOR_ID_PROPERTY, id);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setNodeType(java.lang.String)
	 */
	public void setNodeType(String typeLocator) {
		properties.put(ITopicQuestsOntology.INSTANCE_OF_PROPERTY_TYPE, typeLocator);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#addSuperclassId(java.lang.String)
	 */
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
	public List<String> listSuperclassIds() {
		return getMultivaluedProperty(ITopicQuestsOntology.SUBCLASS_OF_PROPERTY_TYPE);
	}


	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setIsPrivate(boolean)
	 */
	public void setIsPrivate(boolean isPrivate) {
		String x = (isPrivate ? "true":"false");
		properties.put(ITopicQuestsOntology.IS_PRIVATE_PROPERTY, x);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getIsPrivate()
	 */
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
	
	@Override
	public boolean isA(String typeLocator) {
		if (this.getNodeType() != null && this.getNodeType().equals(typeLocator))
			return true;
		List<String>sups = this.listTransitiveClosure();
		if (sups != null && !sups.isEmpty())
			return sups.contains(typeLocator);
		return false;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#setURL(java.lang.String)
	 */
	public void setURL(String url) {
		properties.put(ITopicQuestsOntology.RESOURCE_URL_PROPERTY, url);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#getURL()
	 */
	public String getURL() {
		return (String)properties.get(ITopicQuestsOntology.RESOURCE_URL_PROPERTY);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.model.api.INode#toMap()
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	@Override
	public List<String> listACLValues() {
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
	public void addACLValue(String value) {
		List<String> l = getMultivaluedProperty(ITopicQuestsOntology.RESTRICTION_PROPERTY_TYPE);
		if (!l.contains(value))
			l.add(value);
	}

	@Override
	public void removeACLValue(String value) {
		List<String> l = (List<String>)properties.get(ITopicQuestsOntology.RESTRICTION_PROPERTY_TYPE);
		if (l != null)
			l.remove(value);
	}

	@Override
	public boolean containsACL(String value) {
		List<String> creds = listACLValues();
		return creds.contains(value);
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
	public void addRelation(String relationTypeLocator, String relationLocator, String relationLabel, String targetSmallIcon, String targetLocator, String targetLabel, String nodeType, String sourceOrTarget) {
		IRelationStruct s = new RelationStruct();
		s.setRelationLocator(relationLocator);
		s.setRelationType(relationTypeLocator);
		s.setRelationLabel(relationLabel);
		s.setTargetIcon(targetSmallIcon);
		s.setTargetLocator(targetLocator);
		s.setTargetLabel(targetLabel);
		s.setTargetNodeType(nodeType);
		addMultivaluedSetStringProperty(ITopicQuestsOntology.TUPLE_LIST_PROPERTY,s.toJSON());
//		System.out.println("ADDTUPLE-0 "+properties.get(ITopicQuestsOntology.TUPLE_LIST_PROPERTY));
	}
	
	@Override
	public void addRestrictedRelation(String relationTypeLocator, String relationLocator, String relationLabel, String targetSmallIcon, String targetLocator, String targetLabel, String nodeType, String sourceOrTarget) {
		IRelationStruct s = new RelationStruct();
		s.setRelationLocator(relationLocator);
		s.setRelationType(relationTypeLocator);
		s.setRelationLabel(relationLabel);
		s.setTargetIcon(targetSmallIcon);
		s.setTargetLocator(targetLocator);
		s.setTargetLabel(targetLabel);
		s.setTargetNodeType(nodeType);
		addMultivaluedSetStringProperty(ITopicQuestsOntology.TUPLE_LIST_PROPERTY_RESTRICTED,s.toJSON());
//		System.out.println("ADDTUPLERestricted-0 "+properties.get(ITopicQuestsOntology.TUPLE_LIST_PROPERTY_RESTRICTED));
	}
	@Override
	public void addPivot(String relationTypeLocator, String relationLocator,
			String relationLabel, String documentSmallIcon, String targetLocator,
			String targetLabel, String nodeType, String sourceOrTarget) {
		IRelationStruct s = new RelationStruct();
		s.setRelationLocator(relationLocator);
		s.setRelationType(relationTypeLocator);
		s.setRelationLabel(relationLabel);
		s.setTargetIcon(documentSmallIcon);
		s.setTargetLocator(targetLocator);
		s.setTargetLabel(targetLabel);
		s.setTargetNodeType(nodeType);
		addMultivaluedSetStringProperty(ITopicQuestsOntology.PIVOT_LIST_PROPERTY,s.toJSON());
	}
	
	@Override
	public void addRestrictedPivot(String relationTypeLocator, String relationLocator,
			String relationLabel,String documentSmallIcon, String targetLocator, String targetLabel, String nodeType, String sourceOrTarget){
		IRelationStruct s = new RelationStruct();
		s.setRelationLocator(relationLocator);
		s.setRelationType(relationTypeLocator);
		s.setRelationLabel(relationLabel);
		s.setTargetIcon(documentSmallIcon);
		s.setTargetLocator(targetLocator);
		s.setTargetLabel(targetLabel);
		s.setTargetNodeType(nodeType);
		addMultivaluedSetStringProperty(ITopicQuestsOntology.RESTRICTED_PIVOT_LIST_PROPERTY,s.toJSON());
	}
	
	@Override
	public List<String> listPivotsByRelationType(String relationType) {
		List<String> relns = this.getMultivaluedProperty(ITopicQuestsOntology.PIVOT_LIST_PROPERTY);
		if (relns == null)
			return new ArrayList<String>();
		else if (relationType == null)
			return relns;
		else {
			//This is messy because we are dealing with JSON strings
			// based on IRelationStruct
			List<String> result = new ArrayList<String>();
			String rln,x;
			int len = relns.size();
			for (int i=0;i<len;i++) {
				rln = relns.get(i);
				x = pluckRelationTypeFromJSONString(rln);
				if (x != null && x.equals(relationType))
					result.add(rln);
			}
			return result;
		}
	}
	
	public List<String> listRestrictedPivotsByRelationType(String relationType) {
		List<String> relns = this.getMultivaluedProperty(ITopicQuestsOntology.RESTRICTED_PIVOT_LIST_PROPERTY);
		if (relns == null)
			return new ArrayList<String>();
		else if (relationType == null)
			return relns;
		else {
			//This is messy because we are dealing with JSON strings
			// based on IRelationStruct
			List<String> result = new ArrayList<String>();
			String rln,x;
			int len = relns.size();
			for (int i=0;i<len;i++) {
				rln = relns.get(i);
				x = pluckRelationTypeFromJSONString(rln);
				if (x != null && x.equals(relationType))
					result.add(rln);
			}
			return result;
		}
	}

	public List<String> listRelationsByRelationType(String relationType) {
		List<String> relns = this.getMultivaluedProperty(ITopicQuestsOntology.TUPLE_LIST_PROPERTY);
		if (relns == null)
			return new ArrayList<String>();
		else if (relationType == null)
			return relns;
		else {
			//This is messy because we are dealing with JSON strings
			// based on IRelationStruct
			List<String> result = new ArrayList<String>();
			String rln,x;
			int len = relns.size();
			for (int i=0;i<len;i++) {
				rln = relns.get(i);
				x = pluckRelationTypeFromJSONString(rln);
				if (x != null && x.equals(relationType))
					result.add(rln);
			}
			return result;
		}
	}

	String pluckRelationTypeFromJSONString(String json) {
		String result = null;
		String str = json;
		int where = str.indexOf(IRelationStruct.RELATION_TYPE);
		if (where > -1) {
			str = StringUtils.substring(str, where+(IRelationStruct.RELATION_TYPE.length()));
			//that should leave :"foo" and we want foo
			where = str.indexOf('\"');
			str = StringUtils.substring(str, where);
			where = str.indexOf('\"');
			str = StringUtils.substring(str, 0, where);
			result = str;
		}
		System.out.println("Node.pluckRelationTypeFromJSONString "+json+" | "+result);
		return result;
	}
	
	public List<String> listRestrictedRelationsByRelationType(String relationType) {
		List<String> relns = this.getMultivaluedProperty(ITopicQuestsOntology.TUPLE_LIST_PROPERTY_RESTRICTED);
		if (relns == null)
			return new ArrayList<String>();
		else if (relationType == null)
			return relns;
		else {
			//This is messy because we are dealing with JSON strings
			// based on IRelationStruct
			List<String> result = new ArrayList<String>();
			String rln,x;
			int len = relns.size();
			for (int i=0;i<len;i++) {
				rln = relns.get(i);
				x = pluckRelationTypeFromJSONString(rln);
				if (x != null && x.equals(relationType))
					result.add(rln);
			}
			return result;
		}
	}

	public List<String> listLabels() {
		List<String> l = fetchLabels(ITopicQuestsOntology.LABEL_PROPERTY);
		return concatinateStringLists(l);
	}

	public List<String> listDetails() {
		List<String> l = fetchLabels(ITopicQuestsOntology.DETAILS_PROPERTY);
		return concatinateStringLists(l);
	}

	public void setObject(String value) {
		properties.put(ITopicQuestsOntology.TUPLE_OBJECT_PROPERTY, value);
	}

	public void setObjectType(String typeLocator) {
		properties.put(ITopicQuestsOntology.TUPLE_OBJECT_TYPE_PROPERTY, typeLocator);
	}

	public String getObject() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_OBJECT_PROPERTY);
	}

	public String getObjectType() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_OBJECT_TYPE_PROPERTY);
	}

	public void setSubjectLocator(String locator) {
		properties.put(ITopicQuestsOntology.TUPLE_SUBJECT_PROPERTY, locator);
	}

	public String getSubjectLocator() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_SUBJECT_PROPERTY);
	}

	public void setSubjectType(String subjectType) {
		properties.put(ITopicQuestsOntology.TUPLE_SUBJECT_TYPE_PROPERTY, subjectType);
	}

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
	public void setIsTransclude(boolean isT) {
		String x = (isT ? "true":"false");
		properties.put(ITopicQuestsOntology.TUPLE_IS_TRANSCLUDE_PROPERTY, x);
	}

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

	public String toJSON() {
		return properties.toJSONString();
	}

	public IResult doUpdate() {
		IResult result = new ResultPojo();
		IResult temp;
		//change lastEditDate
		setLastEditDate(new Date());
		return result;
	}

	@Override
	public void setVersion(String version) {
		properties.put(ITopicQuestsOntology.VERSION, version);
	}

	@Override
	public String getVersion() {
		return (String)properties.get(ITopicQuestsOntology.VERSION);
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
		String key;
		Object val;
		while (keys.hasNext()) {
			key = keys.next();
			if (!key.equals(ITopicQuestsOntology.LOCATOR_PROPERTY)) {
				buf.append("  <"+IXMLFields.PROPERTY+" "+IXMLFields.KEY_ATT+"=\""+key+"\" >\n");
				val = properties.get(key);
				appendValue(val.toString(),buf,key);
				buf.append("  </"+IXMLFields.PROPERTY+">\n");
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
	public boolean removePropertyValue(String key, String value) {
		Object vx = getProperty(key);
		if (vx != null) {
			if (vx instanceof String) {
				this.properties.remove(key);
				return true;
			} else {
				List<String>vl = (List<String>)vx;
				boolean t = vl.remove(value);
				if (t) {
					properties.put(key, vl);
				}
				return t;
			}
		}
		return false;
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
	/**
	 * Given a <code>baseField</code>, e.g. "label", fetch all
	 * label fields regardless of language codes
	 * @param baseField
	 * @return
	 */
	List<String> fetchLabels(String baseField) {
		List<String>result = new ArrayList<String>();
		Iterator<String>itr = properties.keySet().iterator();
		String key;
		while (itr.hasNext()) {
			key = itr.next();
			if (key.startsWith(baseField))
				result.add(key);
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
	public boolean localIsA(String typeLocator) {
		if (this.getNodeType().equals(typeLocator))
			return true;
		List<String>sups = this.listSuperclassIds();
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
		properties.put(IEventLegend.STARTING_DATE_PROPERTY, DateUtil.formatIso8601(startDate));//defaultTimestamp(startDate));
	}
	@Override
	public Date getStartDate() {
		String dx = (String)properties.get(IEventLegend.STARTING_DATE_PROPERTY);
		return DateUtil.fromIso8601(dx); //fromDefaultTimestamp(dx);
	}

	@Override
	public void setEndDate(Date endDate) {
		properties.put(IEventLegend.ENDING_DATE_PROPERTY, DateUtil.formatIso8601(endDate)); //defaultTimestamp(endDate));
	}
	@Override
	public Date getEndDate() {
		String dx = (String)properties.get(IEventLegend.ENDING_DATE_PROPERTY);
		return DateUtil.fromIso8601(dx); //fromDefaultTimestamp(dx);
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
/**
	@Override
	public void setSignature(String signature) {
		properties.put(ITopicQuestsOntology.TUPLE_SIGNATURE_PROPERTY, signature);
	}

	@Override
	public String getSignature() {
		return (String)properties.get(ITopicQuestsOntology.TUPLE_SIGNATURE_PROPERTY);
	}
*/

	@Override
	public void addChildNode(String contextLocator, String smallIcon,
			String locator, String subject, String transcluderLocator) {
		IChildStruct s = new ChildStruct();
		s.setContextLocator(contextLocator);
		s.setLocator(locator);
		s.setSmallIcon(smallIcon);
		s.setSubject(subject);
		if (transcluderLocator != null)
			s.setTranscluderLocator(transcluderLocator);
		String json = s.toJSON();
		List<String> relns = this.getMultivaluedProperty(ITopicQuestsOntology.CHILD_NODE_LIST);
		if (relns == null)
			relns = new ArrayList<String>();
		if (!relns.contains(json)) {
			relns.add(json);
			this.properties.put(ITopicQuestsOntology.CHILD_NODE_LIST, relns);
		}
	}

	@Override
	public List<String> listChildNodes(String contextLocator) {
		List<String> relns = this.getMultivaluedProperty(ITopicQuestsOntology.CHILD_NODE_LIST);
		if (relns == null)
			return new ArrayList<String>();
		else if (contextLocator == null)
			return relns;
		else {
			//This is messy because we are dealing with JSON strings
			// based on IChildStruct
			List<String> result = new ArrayList<String>();
			String rln,x;
			int len = relns.size();
			for (int i=0;i<len;i++) {
				rln = relns.get(i);
				x = pluckContextFromJSONString(rln);
				if (x != null && x.equals(contextLocator))
					result.add(rln);
			}
			return result;
		}
	}
	
	String pluckContextFromJSONString(String json) {
		String result = null;
		String str = json;
		int where = str.indexOf(IChildStruct.CONTEXT_LOCATOR);
		if (where > -1) {
			str = StringUtils.substring(str, where+(IChildStruct.CONTEXT_LOCATOR.length()));
			//that should leave :"foo" and we want foo
			where = str.indexOf('\"');
			str = StringUtils.substring(str, where);
			where = str.indexOf('\"');
			str = StringUtils.substring(str, 0,where);
			result = str;
		}
		System.out.println("Node.pluckRelationTypeFromJSONString "+json+" | "+result);
		return result;
	}

	@Override
	public void addParentNode(String contextLocator, String smallIcon,
			String locator, String subject) {
		IChildStruct s = new ChildStruct();
		s.setContextLocator(contextLocator);
		s.setLocator(locator);
		s.setSmallIcon(smallIcon);
		s.setSubject(subject);
		String json = s.toJSON();
		List<String> relns = this.getMultivaluedProperty(ITopicQuestsOntology.PARENT_NODE_LIST);
		if (relns == null)
			relns = new ArrayList<String>();
		if (!relns.contains(json)) {
			relns.add(json);
			this.properties.put(ITopicQuestsOntology.PARENT_NODE_LIST, relns);
		}
	}

	@Override
	public List<String> listParentNodes(String contextLocator) {
		List<String> relns = this.getMultivaluedProperty(ITopicQuestsOntology.PARENT_NODE_LIST);
		if (relns == null)
			return new ArrayList<String>();
		else if (contextLocator == null)
			return relns;
		else {
			//This is messy because we are dealing with JSON strings
			// based on IChildStruct
			List<String> result = new ArrayList<String>();
			String rln,x;
			int len = relns.size();
			for (int i=0;i<len;i++) {
				rln = relns.get(i);
				x = pluckContextFromJSONString(rln);
				if (x != null && x.equals(contextLocator))
					result.add(rln);
			}
			return result;
		}
	}

	/////////////////////////////////////////////
	// AN InfoBox structure is a MAP of named infoboxes
	// Since we are dealing with JSON here, this should be easy!
	/////////////////////////////////////////////

	/**
	 * Internal
	 * @return does not return <code>null</code>
	 */
	Map<String,String> getInfoBoxes() {
		Map<String,String> result = (Map<String,String>)this.properties.get(ITopicQuestsOntology.INFO_BOX_LIST_PROPERTY);
		if (result == null)
			result = new HashMap<String,String>();
		return result;
	}
	
	/**
	 * Internal
	 * @param boxes
	 */
	void setInfoBoxes(Map<String,String> boxes) {
		this.properties.put(ITopicQuestsOntology.INFO_BOX_LIST_PROPERTY, boxes);
	}
	
	@Override
	public void putInfoBox(IInfoBox infoBox) {
		Map<String,String>ib = getInfoBoxes();
		ib.put(infoBox.getName(), infoBox.toJSON());
		setInfoBoxes(ib);
	}

	@Override
	public String getInfoBox(String name) {
		Map<String,String>ib = getInfoBoxes();
		return ib.get(name);
	}

	@Override
	public void removeInfoBox(String name) {
		Map<String,String>ib = getInfoBoxes();
		ib.remove(name);
		setInfoBoxes(ib);
	}

	@Override
	public List<String> listInfoBoxes() {
		List<String>result = new ArrayList<String>();
		Map<String,String>ib = getInfoBoxes();
		Iterator<String>itr = ib.keySet().iterator();
		while (itr.hasNext())
			result.add(ib.get(itr.next()));
		return result;
	}

	@Override
	public void setSubject(String subjectString, String language,
			String userLocator) {
		String key = makeField(ITopicQuestsOntology.AIR_SUBJECT_PROPERTY, language);
		IAirStruct s = new AirStruct();
		s.setLastEditDate(DateUtil.formatIso8601(new Date())); //defaultTimestamp(new Date()));
		s.setText(subjectString);
		s.setEditComment("");
		s.setCreator(userLocator);
		this.properties.put(key, s.toJSON());
	}

	@Override
	public String getSubject(String language) {
		String key = makeField(ITopicQuestsOntology.AIR_SUBJECT_PROPERTY, language);
		return (String)this.properties.get(key);
	}

	@Override
	public void updateSubject(String updatedSubject, String language,
			String userLocator, String comment) {
		String key = makeField(ITopicQuestsOntology.AIR_SUBJECT_PROPERTY, language);
		String versionKey = makeField(ITopicQuestsOntology.AIR_SUBJECT_VERSION_PROPERTY, language);
		//Updating means making a new version, and pushing the old one onto a version stack
		String current = getSubject(language);
		List<String>versions = this.listSubjectVersions(language);
		IAirStruct s = new AirStruct();
		s.setLastEditDate(DateUtil.formatIso8601(new Date())); //defaultTimestamp(new Date()));
		s.setText(updatedSubject);
		s.setEditComment(comment);
		s.setCreator(userLocator);
		this.properties.put(key, s.toJSON());
		versions.add(current);
		this.properties.put(versionKey, versions);
	}

	@Override
	public void setBody(String bodyString, String language, String userLocator) {
		String key = makeField(ITopicQuestsOntology.AIR_BODY_PROPERTY, language);
		IAirStruct s = new AirStruct();
		s.setLastEditDate(DateUtil.formatIso8601(new Date())); //defaultTimestamp(new Date()));
		s.setText(bodyString);
		s.setEditComment("");
		s.setCreator(userLocator);
		this.properties.put(key, s.toJSON());
	}

	@Override
	public String getBody(String language) {
		String key = makeField(ITopicQuestsOntology.AIR_BODY_PROPERTY, language);
		return (String)this.properties.get(key);
	}

	@Override
	public void updateBody(String updatedBody, String language,
			String userLocator, String comment) {
		String key = makeField(ITopicQuestsOntology.AIR_BODY_PROPERTY, language);
		String versionKey = makeField(ITopicQuestsOntology.AIR_BODY_VERSION_PROPERTY, language);
		String current = getSubject(language);
		List<String>versions = this.listSubjectVersions(language);
		IAirStruct s = new AirStruct();
		s.setLastEditDate(DateUtil.formatIso8601(new Date())); //defaultTimestamp(new Date()));
		s.setText(updatedBody);
		s.setEditComment(comment);
		s.setCreator(userLocator);
		this.properties.put(key, s.toJSON());
		versions.add(current);
		this.properties.put(versionKey, versions);
	}

	@Override
	public List<String> listBodyVersions(String language) {
		String versionKey = makeField(ITopicQuestsOntology.AIR_BODY_VERSION_PROPERTY, language);
		List<String>result = (List<String>)this.properties.get(versionKey);
		if (result == null)
			result = new ArrayList<String>();
		return result;
	}

	@Override
	public List<String> listSubjectVersions(String language) {
		String versionKey = makeField(ITopicQuestsOntology.AIR_SUBJECT_VERSION_PROPERTY, language);
		List<String>result = (List<String>)this.properties.get(versionKey);
		if (result == null)
			result = new ArrayList<String>();
		return result;
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

	///////////////////////////////////
	// These are not in TQTopicMap
	// These are ICitation, which might become an InfoBox
	///////////////////////////////////
	@Override
	public void setDocumentTitle(String title, String language, String userId) {
		this.setSubject(title, language, userId);
	}

	@Override
	public String getDocumentTitle(String language) {
		return this.getSubject(language);
	}

	@Override
	public void setDocumentAbstract(String abs, String language, String userId) {
		this.setBody(abs, language, userId);
	}

	@Override
	public String getDocumentAbstract(String language) {
		return this.getBody(language);
	}

	@Override
	public void addAuthorLocator(String authorLocator) {
		List<String> ids = getMultivaluedProperty(IBiblioLegend.AUTHOR_LIST_PROPERTY);
		// no duplicates allowed
		if (!ids.contains(authorLocator))
			ids.add(authorLocator);
	}

	@Override
	public List<String> listAuthorLocators() {
		// TODO Auto-generated method stub
		return (List<String>)properties.get(IBiblioLegend.AUTHOR_LIST_PROPERTY);
	}

	@Override
	public void setPublicationTypeLocator(String typeLocator) {
		properties.put(IBiblioLegend.PUBLICATION_TYPE, typeLocator);
	}

	@Override
	public String getPublicationTypeLocator() {
		return (String)properties.get(IBiblioLegend.PUBLICATION_TYPE);
	}

	@Override
	public void setPublisherLocator(String publisherLocator) {
		properties.put(IBiblioLegend.PUBLISHER_PROPERTY, publisherLocator);
	}

	@Override
	public String getPublisherLocator() {
		return (String)properties.get(IBiblioLegend.PUBLISHER_PROPERTY);
	}

	@Override
	public void setDOI(String doi) {
		properties.put(IBiblioLegend.DOI_PROPERTY, doi);
	}

	@Override
	public String getDOI() {
		return (String)properties.get(IBiblioLegend.DOI_PROPERTY);
	}

	@Override
	public void setISBN(String isbn) {
		properties.put(IBiblioLegend.ISBN_PROPERTY, isbn);
	}

	@Override
	public String getISBN() {
		return (String)properties.get(IBiblioLegend.ISBN_PROPERTY);
	}

	@Override
	public void setISSN(String issn) {
		properties.put(IBiblioLegend.ISSN_PROPERTY, issn);
	}

	@Override
	public String getISSSN() {
		return (String)properties.get(IBiblioLegend.ISSN_PROPERTY);
	}

	@Override
	public void setJournalTitle(String journalTitle) {
		properties.put(IBiblioLegend.JOURNAL_TITLE_PROPERTY, journalTitle);
	}

	@Override
	public String getJournalTitle() {
		return (String)properties.get(IBiblioLegend.JOURNAL_TITLE_PROPERTY);
	}

	@Override
	public void setPublicationDate(Date d) {
		properties.put(IBiblioLegend.PUBLICATION_DATE_PROPERTY, DateUtil.formatIso8601(d)); //defaultTimestamp(d));
	}

	@Override
	public Date getPublicationDate() {
		String dx = (String)properties.get(IBiblioLegend.PUBLICATION_DATE_PROPERTY);
		return DateUtil.fromIso8601(dx); //fromDefaultTimestamp(dx);
	}

	@Override
	public void setJournalVolume(String vol) {
		properties.put(IBiblioLegend.JOURNAL_VOLUME_PROPERTY, vol);
	}

	@Override
	public String getJournalVolume() {
		return (String)properties.get(IBiblioLegend.JOURNAL_VOLUME_PROPERTY);
	}

	@Override
	public void setJournalNumber(String num) {
		properties.put(IBiblioLegend.JOURNAL_NUMBER_PROPERTY, num);
	}

	@Override
	public String getJournalNumber() {
		return (String)properties.get(IBiblioLegend.JOURNAL_NUMBER_PROPERTY);
	}

	@Override
	public void setPages(String pages) {
		properties.put(IBiblioLegend.PAGES_PROPERTY, pages);
	}

	@Override
	public String getPages() {
		return (String)properties.get(IBiblioLegend.PAGES_PROPERTY);
	}

	@Override
	public void setIsFederated(boolean t) {
		properties.put(ITopicQuestsOntology.IS_FEDERATED, new Boolean(t));
	}

	@Override
	public boolean getIsFederated() {
		Boolean o = (Boolean)properties.get(ITopicQuestsOntology.IS_FEDERATED);
		if (o != null)
			return o.booleanValue();
		return false;
	}

	@Override
	public void setIsLive(boolean t) {
		properties.put(ITopicQuestsOntology.IS_LIVE, new Boolean(t));
	}

	@Override
	public boolean getIsLive() {
		Boolean o = (Boolean)properties.get(ITopicQuestsOntology.IS_LIVE);
		if (o != null)
			return o.booleanValue();
		return true;
	}






}
