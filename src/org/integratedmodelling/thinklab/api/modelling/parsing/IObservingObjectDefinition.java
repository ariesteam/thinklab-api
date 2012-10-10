package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;
import org.integratedmodelling.thinklab.api.lang.IList;

public abstract interface IObservingObjectDefinition extends IModelObjectDefinition {

	/**
	 * Add an observable as the semantic representation. Should be instantiated to a semantic
	 * object at initialization, not immediately after setting because the semantics inferred
	 * from the model may not be yet complete.
	 * 
	 * @param semantics list that can be passed to instantiate() to create the actual observable.
	 * @param formalName an optional name for an observable, which may be used to 
	 * 	      label a state in a result dataset or to refer to states of the observable
	 * 	      in previously computed contexts. May be null and will often be.
	 */
	public void addObservable(IList semantics, String formalName);
	
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
	 * @param isOptional
	 * @param distribute 
	 * @param whereCondition 
	 * @param whenCondition 
	 * @param contextModel 
	 */
	public void addDependency(
			Object model, 
			String formalName, 
			IPropertyDefinition property, 
			boolean isOptional, 
			boolean distribute, 
			IModelDefinition contextModel, 
			Object whereCondition);

	/**
	 * Accessors are not created directly in the language, but referred to through
	 * an external function definition which may have parameters.
	 * 
	 * @param function
	 */
	public void setAccessorGeneratorFunction(IFunctionCall function);

	public void addAction(IConcept domain, String subject,
			IExpression action, IExpression condition,
			boolean negated);
	
}
