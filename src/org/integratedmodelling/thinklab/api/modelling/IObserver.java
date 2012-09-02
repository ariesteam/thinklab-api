package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * A model producing data observations of a given type (through its subclasses). 
 */
public abstract interface IObserver extends IObservingObject {
	
	/**
	 * Observers do not properly have observables: they either
	 * mediate another observer or link to another model through
	 * its observable. This function returns the link object, either
	 * an observer or a general semantic object that describes the
	 * observable. At the end of a mediation chain there is always
	 * an observable. 
	 * 
	 * @return the object observed by this observable. Use instanceof
	 * to check whether it's another observer or not. If not, it's a
	 * open observable for which compatible resolving models should be
	 * found in the model base. It will never return null.
	 */
	public abstract Object getObservedObject();
	
	/**
	 * Return the accessor that will compute states for this observer.
	 * 
	 * @return
	 */
	public abstract IAccessor getAccessor(IContext context);
	
	/**
	 * Observers are responsible for creating the empty result observation
	 * when they are contextualized.
	 * @param observable The observable we need the state for. Some observers
	 * 	      handle more than one observable.
	 * @param context  the context that the state will represent. The state must
	 *        be able to accommodate context.getMultiplicity() elements.
	 * 
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IState createState(ISemanticObject<?> observable, ISubject context) throws ThinklabException;
		

	/**
	 * Return the sub-context of the passed one that this observer isn't capable
	 * of computing without external input. If no external input is necessary,
	 * return null. If all the context is unresolved, return the passed context.
	 * 
	 * Normally the only case when the return value will be not null and different
	 * from the totalContext is when one extent in the context supports a notion of
	 * "initial" (i.e., time) and the observer is capable of inferring the remaining
	 * states when initial values are provided.
	 * 
	 * @param totalContext
	 * @return
	 */
	public abstract List<IExtent> getUnresolvedContext(ISubject totalContext);

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
	 * Get the observer we are mediating if any.
	 * 
	 * @return
	 */
	public abstract IObserver getMediatedObserver();


}