package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.thinklab.api.IMetadataHolder;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IInstance;

public interface IObservation extends IMetadataHolder {

	/**
	 * Return the observable instance. Can't be null. If this observation is a
	 * mediator and doesn't have an observable, scan the mediation chain until
	 * one is found.
	 * 
	 * @return the observable for this observation
	 */
	public abstract IInstance getObservable();

	/**
	 * Get the class of the main observable. If this observation is a mediator
	 * and doesn't have an observable, scan the mediation chain until one is
	 * found.
	 * 
	 * @return
	 * @model
	 */
	public abstract IConcept getObservableClass();

	/**
	 * Return a collection of all observations on which this one depends except
	 * the extents.
	 * 
	 * @return
	 */
	public abstract Collection<IObservation> getDependencies();


	/**
	 * Return a collection of all extent observation that this one depends on.
	 * 
	 * @return
	 */
	public abstract Collection<IExtent> getExtents();

}
