package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;
import org.integratedmodelling.thinklab.api.modelling.IContext;

public interface IContextDefinition extends IModelObjectDefinition, IContext {

	public void addModel(IModelDefinition observation);
	
	/**
	 * Instead of an actual observation, we may need to pass a function definition to
	 * be resolved. If capable of validating right away, do so and throw an exception
	 * if function is undefined or doesn't return an observation. 
	 * 
	 * @param function
	 */
	public void addObservationGeneratorFunction(IFunctionCall function) throws ThinklabValidationException;

	/**
	 * A context is an instance of an observation so it contains a finished observable. If this
	 * has functional properties that were not observed at the time of use, they will be 
	 * observed when the context is observed.
	 * 
	 * @param agent
	 */
	public void setObservable(ISemanticObject<?> agent);
	
}
