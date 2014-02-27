/**
 * 
 */
package org.topicquests.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author park
 * Based on code http://www.careercup.com/question?id=17230678
 */
public class ConcurrentLRUCache<String, Object> {
	private final int maxSize;
	private ConcurrentHashMap<String, Object> map;
	private ConcurrentLinkedQueue<String> queue;

	/**
	 * 
	 */
	public ConcurrentLRUCache(final int maxSize) {
	    this.maxSize = maxSize;
	    map = new ConcurrentHashMap<String, Object>(maxSize);
	    queue = new ConcurrentLinkedQueue<String>();
	}

	/**
	 * @param key - may not be null!
	 * @param value - may not be null!
	 * @return can return null
	 */
	public Object put(final String key, final Object value) {
		Object result = null;
	    if (map.containsKey(key)) {
	        queue.remove(key); // remove the key from the FIFO queue
	    }
	 
	    while (queue.size() >= maxSize) {
	        String oldestKey = queue.poll();
	        if (null != oldestKey) {
	            result = map.remove(oldestKey);
	        }
	    }
	    queue.add(key);
	    map.put(key, value);
	    return result;
	}
	 
	/**
	 * @param key - may not be null!
	 * @return the value associated to the given key or null
	 */
	public Object get(final String key) {
	    return map.get(key);
	}
	
	public Object remove(final String key) {
		Object result = map.remove(key);
		queue.remove(key);
		return result;
	}

}
