package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.IMetadataHolder;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;
import org.integratedmodelling.thinklab.api.knowledge.IInstance;

/**
 * An observation is a fully specified and resolved scientific statement about an observable. It usually exists
 * in a context, which is the world view contingent to this observation. Once an observable has been observed
 * in a context, the same observable is "resolved", i.e. there cannot be two observations of the same observable
 * in the same context.
 * 
 * By itself this interface represents a direct observation (identification), which doesn't use a state to represent its
 * observable - the observable is its own state. As such, it does not need an IObserver to provide specific semantics
 * for the observation process. Observations that have a IObserver also have "data" and are instances of
 * the derived IState.
 * 
 * @author  Ferd
 * @see IState
 */
public interface IObservation extends IMetadataHolder {

	/**
	 * Return the observable. Never null. 
	 * @return
	 */
	public abstract IInstance getObservable();
	
	/**
	 * Return the observer(s) that made this observation and provides the
	 * full observation semantics for it.
	 * @return
	 */
	public Pair<IObserver, IExpression> getObservers();

	/**
	 * Return a collection of all observations on which this one depends except
	 * the extents. These will be a subset of those in the context and this method
	 * is just convenience to quickly extract the direct dependencies from it.
	 * 
	 * @return
	 */
	public abstract Collection<IObservation> getDependencies();

	/**
	 * Return another observation contextualized to the passed context, or throw an
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

	/**
	 * Observations are always part of a context, and must be able to return the context they are part of. If the
	 * observation is "absolute", e.g. pi or some other constant, and has no dependencies or extents, this 
	 * function should return an empty context and not null.
	 */
	public abstract IContext getContext();
}
