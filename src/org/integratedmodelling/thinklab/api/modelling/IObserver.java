package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;
import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * A model producing observations of a given type (through its subclasses). 
 */
public abstract interface IObserver extends IModelObject {
	
	/**
	 * Return an appropriate accessor chain to use during contextualization to obtain data in this context. Each
	 * accessor will use whatever is left on the stack by the previous one.
	 * 
	 * @return
	 */
	public abstract List<IAccessor> getAccessors(IContext context);
	
	/**
	 * Return a type corresponding to the state. The first accessor will create the state if 
	 * necessary, but the observer should be able to know the type.
	 *  
	 * @return
	 */
	public abstract IConcept getStateType();


	/**
	 * The observe() operation is essentially a semantic query for an observation of an observable in a 
	 * context. Because this can produce a set of possible results, each observation in the resulting
	 * collection should represent a unique and complete solution. Thinklab will expect the collection to
	 * be sorted using some criterion of decreasing relevance, so that the first observation in the list will
	 * be the most likely to be useful. Implementations should also make the assumption that extracting each
	 * observation from the collection will be a potentially very expensive operation, so they should minimize
	 * iteration and expect a lazy behavior for the iterator.
	 * 
	 * The context of each observation extracted will give access to all states in the observation structure
	 * represented, and can be used to create any IDataset for communication or storage.
	 * 
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public List<IObservation> observe(IContext context) throws ThinklabException;

	/**
	 * Train the model to match any output state that can be
	 * observed in the kbox/context. Details of how to choose or subset the output states are
	 * left to the implementation. Not all observer will be trainable - if not trainable,
	 * this one should return the same model (with the option of logging a warning).
	 * 
	 * It should never modify the observer it's called on - it would be a const if there
	 * was such a thing in Java. 
	 * @param params
	 * 
	 * @return a new trained model that has learned to reproduce the output states 
	 * 		   observed on the passed kbox.
	 * @throws ThinklabException
	 */
	public IObserver train(IContext context) throws ThinklabException;

	/**
	 * A scenario is a model modifier, containing alternative models for given observables.
	 * Applying the scenario substitutes any models of the same observables with those
	 * in the scenario, going as deep as needed in the dependency chain.
	 * 
	 * @param scenario
	 * @return
	 * @throws ThinklabException
	 */
	public IObserver applyScenario(IScenario scenario) throws ThinklabException;

	/**
	 * Get the models that this one directly depends upon. Return an empty 
	 * collection, not null, if none exist - it should always be legal to iterate
	 * over the return value.
	 * 
	 * @return
	 */
	public abstract Collection<IModel> getDependencies();
}