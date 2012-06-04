package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.lang.IList;

public abstract interface IObservingObjectDefinition extends IModelObjectDefinition {

	public void addObservable(IList semantics);
	
	/**
	 * A dependency can be added for any object - the implementation should ensure that
	 * there is a resolution strategy of the object to a model that can observe it. 
	 * 
	 * Thinklab will support models and other semantic objects, seen as observables.
	 * 
	 * @param model
	 * @param formalName
	 * @param isRequired
	 */
	public void addDependency(Object model, String formalName, boolean isRequired);
}
