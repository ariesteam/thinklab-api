package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IContext;

public interface IContextDefinition extends IModelObjectDefinition, IContext {

	public void addObservation(IObservationDefinition observation);
	
	/**
	 * Instead of an actual observation, we may need to pass a function definition to
	 * be resolved later.
	 * 
	 * @param function
	 */
	public void addObservationGeneratorFunction(IFunctionDefinition function);
	
}
