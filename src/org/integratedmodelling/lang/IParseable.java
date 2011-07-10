package org.integratedmodelling.lang;

import org.integratedmodelling.exceptions.ThinklabValidationException;

/**
 * Anything that adopts IParseable must have an empty public constructor and can be 
 * fully defined by parsing a string value.
 * 
 * @author Ferdinando
 *
 */
public interface IParseable {
	
	public void parse(String string) throws ThinklabValidationException;

}
