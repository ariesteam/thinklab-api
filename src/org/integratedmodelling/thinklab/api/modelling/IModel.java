package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;


/**
 * A Model, i.e. a query that uses observers to produce its results.
 */
public interface IModel extends IModelObject {
	
	/**
	 * Return the observable. Never null. 
	 * @return
	 */
	public abstract ISemanticObject<?> getObservable();
	
	/**
	 * Return the observer(s) that made this observation and provides the
	 * full observation semantics for it.
	 * @return
	 */
	public IObserver getObserver();

	/**
	 * Return a collection of all observations on which this one depends except
	 * the extents. These will be a subset of those in the context and this method
	 * is just convenience to quickly extract the direct dependencies from it.
	 * 
	 * @return
	 */
	public abstract Collection<IObservation> getDependencies();

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