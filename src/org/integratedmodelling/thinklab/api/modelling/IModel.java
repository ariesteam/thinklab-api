package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * A Model, i.e. a query that uses observers to produce its result observation. It 
 * must have only one observable.
 */
public interface IModel extends IObservingObject {
	
	/**
	 * Return the single observable as a semantic object.
	 * 
	 * @return
	 */
	public ISemanticObject<?> getObservable();
	
	/**
	 * Return the observer that made this observation and provides the
	 * full observation semantics for it. There is always one observer, which
	 * may be a IConditionalObserver switching to others according to context.
	 * 
	 * @return
	 */
	public IObserver getObserver();

	/**
	 * Return an observation contextualized to the passed context, or throw an
	 * exception if that's not possible. Recontextualization is the
	 * core of thinklab and is done based on extent-specific algorithms.
	 * 
	 * For conventional modelers: the "output data" are the IStates contained in
	 * the observation's context, accessed through getContext(). This context will
	 * also contain any states and extents from the passed one, harmonized and
	 * transformed as required by the accessors in the contextualization chain.
	 * 
	 * Contextualization keeps the observation structure consistent across
	 * dependencies and contingencies.
	 * 
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IObservation observe(IContext context) throws ThinklabException;

	/**
	 * If a model was given a specific coverage in any extent, either directly or through
	 * a namespace-wide specification, return the context that
	 * expresses that coverage. If no coverage has been specified, return an empty
	 * context.
	 * 
	 * @return
	 */
	public abstract IContext getCoverage();
	
}