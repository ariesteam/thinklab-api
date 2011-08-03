package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IInstance;
import org.integratedmodelling.thinklab.api.knowledge.storage.IKBox;
import org.integratedmodelling.thinklab.api.modelling.observation.IContext;
import org.integratedmodelling.thinklab.api.modelling.observation.IObservationList;
import org.integratedmodelling.thinklab.api.runtime.ISession;

/**
 * A Model
 */
public interface IModel extends IModelObject {
	
	/**
	 * Return the observable
	 * @return
	 */
	public abstract IInstance getObservable();

	/**
	 * 
	 * @param kbox
	 * @param session
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public IObservationList observe(IContext context, IKBox kbox, ISession session) throws ThinklabException;

	/**
	 * Train the model to match any output state that can be
	 * observed in the kbox/context. Details of how to choose or subset the output states are
	 * left to the implementation. Not all models will be trainable - if not trainable,
	 * this one should return the same model (with the option of logging a warning).
	 * 
	 * It should never modify the model it's called on - would be a const if there
	 * was such a thing in Java. 
	 * 
	 * @param kbox
	 * @param session
	 * @param params
	 * @return a new trained model that has learned to reproduce the output states 
	 * 		   observed on the passed kbox.
	 * @throws ThinklabException
	 */
	public IModel train(IContext context, IKBox kbox, ISession session) throws ThinklabException;

	/**
	 * A scenario is a model modifier, containing alternative models for given observables.
	 * Applying the scenario substitutes any models of the same observables with those
	 * in the scenario, going as deep as needed in the dependency chain.
	 * 
	 * @param scenario
	 * @return
	 * @throws ThinklabException
	 */
	public IModel applyScenario(IScenario scenario) throws ThinklabException;

	/**
	 * Get the models that this one directly depends upon. Return an empty 
	 * collection, not null, if none exist - it should always be legal to iterate
	 * over the return value.
	 * 
	 * @return
	 */
	public abstract Collection<IModel> getDependencies();
}