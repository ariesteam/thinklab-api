package org.integratedmodelling.thinklab.api.modelling.observation;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * @author  Ferd
 */
public interface IMediatingObservation extends IIndirectObservation {

	/**
	 * If this observation is acting as a mediator for another, return it. If it's a mediator, the datasource should be ignored and the observable may be null.
	 * @return
	 * @uml.property  name="mediatedObservation"
	 * @uml.associationEnd  
	 */
	public IObservation getMediatedObservation();
	
	/**
	 * 
	 * @param observation
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IAccessor getMediator(IIndirectObservation observation, IContext context)
		throws ThinklabException;
	
}
