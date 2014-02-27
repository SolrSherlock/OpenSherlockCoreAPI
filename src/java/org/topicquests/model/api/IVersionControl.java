/**
 * 
 */
package org.topicquests.model.api;

import org.topicquests.model.api.node.IAddressableInformationResource;

/**
 * @author park
 * <p>Version Control is required to support {@link IAddressableInformationResource} objects.</p>
 * <p>In practice, this means:<br />
 * <li>Storing {@link IAddressableInformationResource} objects which are <em>not</em> the current
 * version in such a way that they can be accessed through version fetches</li>
 * <li>Maintaining the <em>current</em> version in the document to which it belongs</em>
 * <li>Maintaining version correctness for <em>transcluded</em> airs</li>
 * </p>
 */
public interface IVersionControl {
	
	

}
