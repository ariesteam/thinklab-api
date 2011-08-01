package org.integratedmodelling.thinklab.api.lang;

import org.integratedmodelling.exceptions.ThinklabException;

// TODO: Auto-generated Javadoc
/**
 * Anything that adopts IParseable must have an empty public constructor and can be 
 * fully defined by parsing a string value.
 * 
 * @author Ferdinando
 */
public interface IParseable {
	
	/**
	 * Parses the.
	 *
	 * @param string the string
	 * @throws ThinklabException the thinklab exception
	 */
	public void parse(String string) throws ThinklabException;

}
