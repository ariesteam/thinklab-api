package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.collections.Triple;

/**
 * Anything that observes anything must implement this interface, normally
 * through another that extends it.
 * 
 * @author Ferd
 *
 */
public abstract interface IObservingObject extends IModelObject {

	/**
	 * Return for each model we depend on: the model itself, the formal name
	 * that identifies it in this model, and whether it's a required dependency
	 * or not.
	 * 
	 * @return
	 */
	public List<Triple<IModel,String,Boolean>> getDependencies();
}
