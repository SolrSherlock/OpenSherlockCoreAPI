/**
 * 
 */
package org.topicquests.model.api.node;


/**
 * @author park
 * <p>An <code>IAddressableInformationResource</code (<em>AIR</em>) is an 
 * <em>individually addressable information resources</em> such as a paragraph,
 * a blog post, whatever is going to have a <em>purple number</em> identifier which
 * allows it to be individually addressable.</p>
 * <p>An AIR will allow for a <em>version control system</em> to be employed to maintain
 * current versions in any core topic view, but grant access to prior versions as REST URLs.</p>
 * <p><em>Transclusion</em> makes an AIR quite complex. Any time this AIR is transcluded
 * into another view, presumably owned by a different subject proxy, then it has the opportunity
 * to take a new <em>purple number</em> for that view.</p>
 * <p>In theory, the proxy ({@link INode} which owns each AIR is responsible for the
 * purple number assigned to each AIR it displays.</p>
 */
public interface IAddressableInformationResource extends INode {

	/**
	 * Since an <em>AIR</em> is just an {@link INode}, the <em>subject</em>
	 * is the same as a <em>label</em>
	 * @param text
	 * @param language
	 */
	void setSubject(String text, String language);
	
	String getSubject(String language);
	
	/**
	 * <p>Since an <em>AIR</em> is just an {@link INode}, the <em>body</em>
	 * is the same as a <em>details</em> field. But, an <em>AIR</em> can place
	 * <em>versions on their values, meaning we can implement <em>version control</em>
	 * </p>
	 * @param text
	 * @param language
	 */
	void setBody(String text, String language);
	
	/**
	 * Links this AIR with a host proxy, whether original or transclude
	 * @param hostProxyLocator
	 */
	void addHostProxyLocator(String hostProxyLocator);
	
	/**
	 * Applies when AIRs are organized in a <em>tree structure</em> within
	 * a <em>host</em> proxy
	 * @param parentLocator
	 * @param hostProxyLocator can be <code>null</code> in which case the AIR is its own host
	 */
	void setParentNodeLocator(String parentLocator, String hostProxyLocator);
	
	/**
	 * 
	 * @param hostProxyLocator can be <code>null</code> in which case the AIR is its own host
	 * @return
	 */
	String getParentNodeLocator(String hostProxyLocator);
	
	String getBody(String language);
	
	void setVersion(int version);
	
	int getVersion();
	
	void setPurpleNumber(String purpleNumber, String hostProxyLocator);
	
	String getPurpleNumber(String hostProxyLocator);
	

}
