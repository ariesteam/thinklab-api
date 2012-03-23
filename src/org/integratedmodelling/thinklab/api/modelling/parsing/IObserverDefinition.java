package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IObserver;

public abstract interface IObserverDefinition extends IObservingObjectDefinition, IObserver {

	/**
	 * Accessors are not created directly in the language, but referred to through
	 * an external function definition which may have parameters.
	 * 
	 * @param function
	 */
	public void setAccessorGeneratorFunction(IFunctionDefinition function);
	
	public void addMediatedObserver(IObserverDefinition observer, IExpressionDefinition condition);
	
}
