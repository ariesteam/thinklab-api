package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;


/**
 * Accessors must implement this one in order to be allowed to mediate another.
 * 
 * @author Ferd
 *
 */
public interface IMediatingAccessor extends IAccessor {

	/**
	 * Receive an accessor to mediate and ensure it is compatible with our semantics.
	 * 
	 * @param accessor
	 * @throws ThinklabException
	 */
	public abstract void addMediatedAccessor(IAccessor accessor) throws ThinklabException;
	
}
