package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

public interface IComputingAccessor extends IAccessor {
	
	/**
	 * Notifies that the observation whose state we must provide access to depends on another
	 * observation of this observable, and that the observable will be available as the given
	 * type under a given name. IComputingAccessor must redefine getValue to call the superclass
	 * getValue if necessary, and translate indexed concept state values in the context to the
	 * passed formal names.
	 * 
	 * If the obs doesn't know what to do with the observable, it should throw an exception here.
	 * @param o 
	 * @param formalName the id under which this dependency will be known to the internal representation
	 *        of the computing function.
	 *  
	 * @throws ThinklabException 
	 */
	public void notifyDependency(IObservation o, IConcept observable, String formalName) throws ThinklabException;
		
}
