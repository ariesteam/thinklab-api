package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;

public interface IMediatingObserver extends IObserver {

	/**
	 * Called when mediation is implied by the model structure. Should return null if
	 * no mediation is necessary and the passed observer's accessor can be used directly, or
	 * throw an exception if the observer is incompatible. Otherwise, return an accessor which 
	 * will be chained to the passed observer's at contextualization.
	 * 
	 * @param observer
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IAccessor getMediator(IObserver observer) throws ThinklabException;
	
}
