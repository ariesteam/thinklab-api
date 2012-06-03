package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;


/**
 * Accessors must implement this one in order to be allowed to mediate another.
 * 
 * @author Ferd
 *
 */
public interface IMediatingAccessor  {

	/**
	 * Receive an accessor to mediate and ensure it is compatible with our semantics. Note that
	 * if the accessor comes from a datasource, it may only be needed for initialization so
	 * it may only be capable of computing initial states.
	 * 
	 * @param accessor
	 * @throws ThinklabException
	 */
	public abstract void addMediatedAccessor(IAccessor accessor) throws ThinklabException;
	
}
