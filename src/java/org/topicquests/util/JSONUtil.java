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
package org.topicquests.util;

import java.util.*;
import org.json.simple.JSONObject;

/**
 * @author park
 *
 */
public class JSONUtil {

	/**
	 * Convert <code>map</code> to a {@link JSONObject}
	 * @param map
	 * @return
	 */
	public static JSONObject map2JSONObject(Map<Object,Object> map) {
		JSONObject result = new JSONObject();
		Iterator<Object> itr = map.keySet().iterator();
		Object key;
		while (itr.hasNext()) {
			key = itr.next();
			result.put(key, map.get(key));
		}
		
		return result;
	}
}
