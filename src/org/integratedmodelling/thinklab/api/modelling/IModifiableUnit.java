package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabValidationException;

/**
 * Modifiable units admit a modifier after @ to localize them somewhere in time or space. For now
 * the only ones are currencies, which admit a time after the modifier so we can convert say
 * USD@2003 into EUR@2005.
 * 
 * @author Ferd
 *
 */
public interface IModifiableUnit extends IUnit {

	public abstract void validateModifier(String modifier) 
		throws ThinklabValidationException;
	
	public abstract double convert(IModifiableUnit to, String modFrom, String modTo)
		throws ThinklabValidationException;
	
}
