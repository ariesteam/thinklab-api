package org.integratedmodelling.thinklab.api.modelling.observation;

import org.integratedmodelling.thinklab.api.modelling.IObserver;

/**
 * Indirect observation that is capable of merging the
 * states of multiple contingent observation. 
 * 
 * @author ferdinando.villa
 *
 */
public interface IMergingObserver extends IObserver {

	/**
	 * Return a collection of all observations that may contribute to define the 
	 * states of this one, accumulating states over the union of their extents.
	 * 
	 * @return
	 */
	public abstract IObservation[] getContingencies();
}
