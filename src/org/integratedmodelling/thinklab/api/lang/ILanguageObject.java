package org.integratedmodelling.thinklab.api.lang;

import org.integratedmodelling.lang.model.LanguageElement;

/**
 * Objects tagged with this have been created by a language specification, so they can
 * return the API bean that the language was translated into. In some specific cases 
 * (e.g. implicit objects) the bean returned may be null.
 * 
 * @author Ferd
 *
 */
public interface ILanguageObject {

	/**
	 * Return the language API bean that created this object, or null if the
	 * object was added as a result of other reasoning.
	 * 
	 * @return
	 */
	public abstract LanguageElement getLanguageElement();
	
}
