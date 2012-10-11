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
	 * Specific extents may be mentioned in action specifications to trigger actions
	 * on transition. We set function calls so they can be created at initialization and
	 * not at parsing. This one must return the domain concept of the extent that the
	 * function adds - which may require to call it, according to implementation. Avoid
	 * that if possible.
	 * 
	 * @param extentGenerator
	 * @return the domain concept for the extent. If this returns null, the extent and
	 * 		   all the corresponding actions will be ignored.
	 */
	public IConcept addExtentConstraintFunction(IFunctionCall extentGenerator);
	
	/**
	 * Accessors are not created directly in the language, but referred to through
	 * an external function definition which may have parameters.
	 * 
	 * @param function
	 */
	public void setAccessorGeneratorFunction(IFunctionCall function);

	/**
	 * Add an action, consisting of a "what to do" and a "when to do it" parts. Both
	 * are IExpressions, defined with appropriate receivers and arguments so that they
	 * do their thing without further intervention.
	 * 
	 * @param target the string key of the object that this action will change, if any. May be 
	 *        null for side-effect actions.
	 * @param type one of the numeric constants in IObservingObject, to indicate which action type we target
	 * @param action what to do - never null.
	 * @param condition when to do it - returns boolean and may be null.
	 * @param domains the domains of the events that trigger this action. Null means
	 * 	      initialization.
	 */
	public void addAction(String target, int type, IExpression action, IExpression condition, IConcept[] domains);
	
}
