package org.integratedmodelling.thinklab.api.modelling.parsing;


public interface IConditionalObserverDefinition extends IObserverDefinition {
	
	public void addObserver(IExpressionDefinition condition, IObserverDefinition observer);
	
}
