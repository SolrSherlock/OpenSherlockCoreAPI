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
package org.topicquests.common;

import java.util.*;

/**
 * @author park
 *
 */
public class Utils {

	/**
	 * 
	 */
	public Utils() {}

	
	/**
	 * Perform a Set Union on two lists (no duplicates)
	 * @param a
	 * @param b
	 * @return does not return <code>null</code>
	 */
	public List<String> setUnionString(List<String>a, List<String>b) {
		List<String> result = new ArrayList<String>();
		if (a == null && b != null)
			result.addAll(b);
		else if (a != null && b == null)
			result.addAll(a);
		else {
			if (a.size() > b.size()) {
				result.addAll(a);
				doSetUnionStringList(b,result);
			} else if (a.size() < b.size()) {
				result.addAll(b);
				doSetUnionStringList(a,result);
			} else {
				result.addAll(b);
				doSetUnionStringList(a,result);
			}
		}
		return result;
	}
	
	void doSetUnionStringList(List<String>source, List<String>target) {
		Iterator<String>itr = source.iterator();
		String x;
		while (itr.hasNext()) {
			x = itr.next();
			if (!target.contains(x))
				target.add(x);
		}
	}
}
