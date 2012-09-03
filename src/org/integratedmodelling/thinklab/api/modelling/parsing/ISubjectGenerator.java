package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.ISubject;

/**
 * Holds the declarations corresponding to an "observe" definition, which
 * specifies an ISubject (the only observation that can be stated at the
 * statement level, because an IState cannot be specified outside of
 * an ISubject). Will generate a corresponding ISubject on demand.
 * 
 * @author Ferd
 *
 */
public interface ISubjectGenerator extends IModelObjectDefinition {

	/**
	 * Create an instance of the subject defined by this model object. This may
	 * involve building a model for it as an observable, with any dependencies
	 * implied by its functional properties. Called when the subject is observed.
	 * 
	 * @return
	 */
	ISubject observe();

	/**
	 * Set the definition of the observable that specifies the semantic object. It
	 * may be partial - functional properties will be observed automatically.
	 * 
	 * @param odef
	 */
	void setObservable(IList odef);

	/**
	 * Add a function that will be run when createSubject() is called to provide
	 * pre-observed state.
	 * 
	 * @param ff
	 */
	void addObservationGeneratorFunction(IFunctionCall ff);

	/**
	 * Force use of a specific model to choose when observing a given property, which should
	 * be functional. 
	 * 
	 * @param property
	 * @param observer
	 * @param propagate whether the model should create more than one object (if propagate == true,
	 * 	  the property must be an object property.
	 */
	void addModelDependency(IPropertyDefinition property, IModel observer, boolean propagate);
	
}
