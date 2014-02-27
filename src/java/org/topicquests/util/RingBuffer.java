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

import org.topicquests.model.api.node.INode;
/**
 * @author park
 *
 */
public class RingBuffer {
	private ArrayList<INode> _buffer;
	private int maxsize = 0;
	private int last = 0;
	/**
	 * 
	 */
	public RingBuffer(int size) {
		_buffer = new ArrayList<INode>(size);
		maxsize = size;
	}

	public void add(INode n) {
		synchronized(_buffer) {
			if (!_buffer.contains(n)) {
				if (last > maxsize) {
					last--;
					_buffer.remove(maxsize-1);
				}
				last++;
				_buffer.add(0,n);
			}
		}
	}
	
	public List<INode> getBuffer() {
		synchronized(_buffer) {
			return (List<INode>)_buffer.clone();
		}
	}
	
	public int length() {
		return last;
	}
}
