package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * A Model, i.e. a query that uses observers to produce its result observation. It 
 * must have at least one observable. If it has more, they must have been given
 * observation semantics through other models in the same namespace.
 * 
 * Models may be unresolved (i.e. they leave their observable specified only at a
 * semantic level) or resolved (they specify an actual datasource and the observation
 * semantics for it). 
 * 
 */
public interface IModel extends IObservingObject {
	
	/**
	 * Return the semantics of all observables we are observing. The first
	 * in the list is the actual observable and must exist; the others are
	 * expected side-effects of observing the first, which must correspond
	 * to models in the same namespace.
	 * 
	 * @return
	 */
	public List<ISemanticObject<?>> getObservables();
	
	/**
	 * Return the datasource, if any. If we have a datasource, the observer 
	 * defines its observable through its observed "endpoint" - either an
	 * observable object or a model.
	 *  
	 * @return
	 */
	public IDataSource getDatasource();
	
	/**
	 * Return the observer that made this observation and provides the
	 * full observation semantics for it. Data models have one observer, which
	 * may be a IConditionalObserver switching to others according to context. Object
	 * models (agent models) have no observer.
	 * 
	 * @return
	 */
	public IObserver getObserver();

	/**
	 * If a model was given a specific coverage in any extent, either directly or through
	 * a namespace-wide specification, return the context that
	 * expresses that coverage. If no coverage has been specified, return an empty
	 * context.
	 * 
	 * @return
	 */
	public abstract IScale getCoverage();

	
}