package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.modelling.IContext;

public interface IContextDefinition extends IModelObjectDefinition, IContext {

	public void addObservation(IObservationDefinition observation);
	
	/**
	 * Instead of an actual observation, we may need to pass a function definition to
	 * be resolved. If capable of validating right away, do so and throw an exception
	 * if function is undefined or doesn't return an observation. 
	 * 
	 * @param function
	 */
	public void addObservationGeneratorFunction(IFunctionDefinition function) throws ThinklabValidationException;
	
}
