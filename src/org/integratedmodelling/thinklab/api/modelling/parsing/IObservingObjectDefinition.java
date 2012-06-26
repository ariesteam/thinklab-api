package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.lang.IList;

public abstract interface IObservingObjectDefinition extends IModelObjectDefinition {

	public void addObservable(IList semantics);
	
	/**
	 * Required before the concepts are actually generated, right after addObservable
	 * has been called, so that naming defaults can be used.
	 * 
	 * @return
	 */
	public String getObservableConceptName();
	
	/**
	 * A dependency can be added for any object - the implementation should ensure that
	 * there is a resolution strategy of the object to a model that can observe it. 
	 * 
	 * Thinklab will support models and other semantic objects, seen as observables.
	 * 
	 * @param model
	 * @param formalName
	 * @param property
	 * @param isRequired
	 */
	public void addDependency(Object model, String formalName, IPropertyDefinition property, boolean isOptional);
}
