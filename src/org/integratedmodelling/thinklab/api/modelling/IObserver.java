package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IInstance;
import org.integratedmodelling.thinklab.api.knowledge.storage.IKBox;
import org.integratedmodelling.thinklab.api.runtime.ISession;

/**
 * A model producing observations of a given type (through its subclasses). 
 */
public abstract interface IObserver extends IModelObject {
	
	/**
	 * Return an appropriate accessor to use during contextualization to obtain data in this context.
	 * @return
	 */
	public abstract IAccessor getAccessor(IContext context);
	
	/**
	 * Return the observable. Never null. 
	 * @return
	 */
	public abstract IInstance getObservable();

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
	 * @param kbox
	 * @param session
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public Collection<IObservation> observe(IContext context, IKBox kbox, ISession session) throws ThinklabException;

	/**
	 * Train the model to match any output state that can be
	 * observed in the kbox/context. Details of how to choose or subset the output states are
	 * left to the implementation. Not all observer will be trainable - if not trainable,
	 * this one should return the same model (with the option of logging a warning).
	 * 
	 * It should never modify the observer it's called on - it would be a const if there
	 * was such a thing in Java. 
	 * 
	 * @param kbox
	 * @param session
	 * @param params
	 * @return a new trained model that has learned to reproduce the output states 
	 * 		   observed on the passed kbox.
	 * @throws ThinklabException
	 */
	public IObserver train(IContext context, IKBox kbox, ISession session) throws ThinklabException;

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