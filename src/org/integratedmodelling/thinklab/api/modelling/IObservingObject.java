package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.collections.Triple;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * Anything that observes anything must implement this interface, normally
 * through another that extends it.
 * 
 * @author Ferd
 *
 */
public abstract interface IObservingObject extends IModelObject {

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
	 * Return for each model we depend on: the model itself, the formal name
	 * that identifies it in this model, and whether it's a required dependency
	 * or not.
	 * 
	 * @return
	 */
	public List<Triple<IModel,String,Boolean>> getDependencies();
}
