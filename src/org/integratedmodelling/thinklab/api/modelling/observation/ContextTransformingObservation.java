package org.integratedmodelling.thinklab.api.modelling.observation;

import org.integratedmodelling.exceptions.ThinklabException;

public interface ContextTransformingObservation extends ITransformingObservation {

	/**
	 * Return the context that this observation will have once transformed in the 
	 * passed context. Should not actually perform the transformation if at all possible.
	 * If that is necessary in order to know the context, it should store the result and
	 * return when transform() is called. The preferred way is to compute the transformation 
	 * when transform() is called, so that context extraction can be done quickly.
	 * 
	 * @return
	 * @throws ThinklabException
	 */
	public IContext getTransformedContext(IContext context)
		throws ThinklabException;
}