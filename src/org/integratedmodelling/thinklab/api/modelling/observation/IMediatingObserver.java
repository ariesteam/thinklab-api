package org.integratedmodelling.thinklab.api.modelling.observation;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.modelling.IObserver;

/**
 * @author  Ferd
 */
public interface IMediatingObserver extends IObserver {

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
	public abstract IAccessor getMediator(IObserver observer, IContext context)
		throws ThinklabException;
	
}
