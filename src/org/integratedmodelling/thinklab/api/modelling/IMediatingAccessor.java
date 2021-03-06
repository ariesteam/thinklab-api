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
	 * @throws ThinklabException if the accessor is incompatible
	 */
	public abstract void notifyMediatedAccessor(IAccessor accessor) throws ThinklabException;
	
	/**
	 * Run the required mediation step by reinterpreting the passed object, which
	 * holds the semantics given in notifyMediatedAccessor, according to our own
	 * semantics.
	 * 
	 * @param object
	 * @return
	 * @throws ThinklabException
	 */
	public Object mediate(Object object) throws ThinklabException;
}
