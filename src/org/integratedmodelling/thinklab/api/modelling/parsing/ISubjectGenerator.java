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

	ISubject createSubject();

	void setObservable(IList odef);

	void addObservationGeneratorFunction(IFunctionCall ff);

	/**
	 * Force use of a specific model to choose when observing a given property, which should
	 * be functional. 
	 * 
	 * @param property
	 * @param observer
	 */
	void addModelDependency(IPropertyDefinition property, IModel observer);
	
}
