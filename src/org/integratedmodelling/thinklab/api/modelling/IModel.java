package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * A Model, i.e. a query that uses observers to produce its results. It must have only one observable.
 */
public interface IModel extends IObservingObject {
	
	public IList getObservable();
	
	/**
	 * Return the observer(s) that made this observation and provides the
	 * full observation semantics for it.
	 * @return
	 */
	public IObserver getObserver();

	/**
	 * Return an observation contextualized to the passed context, or throw an
	 * exception if that's not possible. Recontextualization is the
	 * core of thinklab and is done based on extent-specific algorithms.
	 * 
	 * Contextualization must keep the observation structure consistent across
	 * dependencies and contingencies.
	 * 
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IObservation contextualize(IContext context) throws ThinklabException;


	
}