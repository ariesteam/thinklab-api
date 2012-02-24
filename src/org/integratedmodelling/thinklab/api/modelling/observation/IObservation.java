package org.integratedmodelling.thinklab.api.modelling.observation;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.IMetadataHolder;
import org.integratedmodelling.thinklab.api.knowledge.IInstance;

/**
 * By itself this represents a direct observation (identification), which doesn't use a state to represent its
 * observable. As such, it does not need an observer either. Observations that have a datasource are instances of IState.
 * 
 * @author  Ferd
 */
public interface IObservation extends IMetadataHolder {

	/**
	 * Return the observable instance. Can't be null. If this observation is a mediator and doesn't have an observable, scan the mediation chain until one is found.
	 * @return   the observable for this observation
	 * @uml.property  name="observable"
	 * @uml.associationEnd  
	 */
	public abstract IInstance getObservable();

	/**
	 * Return a collection of all observations on which this one depends except
	 * the extents.
	 * 
	 * @return
	 */
	public abstract Collection<IObservation> getDependencies();

	/**
	 * Return another observation contextualized to the passed context, or throw an
	 * exception if that's not possible. Recontextualization is the
	 * core of thinklab and is done based on extent-specific algorithms.
	 * 
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IObservation contextualize(IContext context) throws ThinklabException;

	/**
	 * Observations are always part of a context, and must be able to return the context they are part of.
	 */
	public abstract IContext getContext();
}
