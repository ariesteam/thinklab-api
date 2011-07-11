package org.integratedmodelling.thinklab.api.modelling.observation;

import org.integratedmodelling.exceptions.ThinklabException;

public interface MediatingObservation extends IIndirectObservation {

	/**
	 * If this observation is acting as a mediator for another, return it. If
	 * it's a mediator, the datasource should be ignored and the observable may
	 * be null.
	 * 
	 * @return
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
