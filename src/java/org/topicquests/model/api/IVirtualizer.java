/**
 * 
 */
package org.topicquests.model.api;


import org.topicquests.common.api.IResult;
import org.topicquests.model.api.node.INode;

/**
 * @author park
 *
 */
public interface IVirtualizer {

	/**
	 * <p>Create a <em>virtual node</code> based on the given nodes.</p>
	 * <p>This allows different node creation patterns according to the
	 * specifics of the node types.</p>
	 * @param primary
	 * @param merge
	 * @param userLocator
	 * @return
	 */
	IResult createVirtualNode(INode primary, INode merge, String userLocator);
}
