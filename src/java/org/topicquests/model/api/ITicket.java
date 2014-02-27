/**
 * 
 */
package org.topicquests.model.api;

import java.util.List;

/**
 * @author park
 *
 */
public interface ITicket {
	
	/**
	 * General purpose expansion capabilities
	 * @param key
	 * @param value
	 */
	void setProperty(String key, Object value);
	
	Object getProperty(String key);
	
	/**
	 * <code>locator</code> is the <em>primary</em> identifier
	 * for this user
	 * @param locator
	 */
	void setUserLocator(String locator);
	String getUserLocator();

	/**
	 * A user can have one or more, depending on system rules,
	 * <code>avatar</code> identities
	 * @param avatar
	 */
	void addAvatarLocator(String avatar);
	
	/**
	 * Return list of avatar identities
	 * @return does not return <code>null</code>
	 */
	List<String> listAvatars();
	
	/**
	 * Some groups or documents may have ACLs which grant
	 * a group identity
	 * @param groupLocator
	 */
	void addGroupLocator(String groupLocator);
	
	/**
	 * Return a list of group locators
	 * @return does not return <code>null</code>
	 */
	List<String> listGroupLocators();
	
	/**
	 * Will return <code>true</code> if <code>userlocator</code> is
	 * not equal to {@link ITopicQuestsOntology&GUEST_USER}
	 * @return
	 */
	boolean isAuthenticated();
	
	/**
	 * Will return <code>true</code> if this user is a has
	 * <code>groupLocator</code> in the member list
	 * @param groupLocator
	 * @return
	 */
	boolean isMember(String groupLocator);
}
