package org.integratedmodelling.thinklab.api.lang.parsing;


public interface IConditionalObserverDefinition extends IObserverDefinition {
	
	public void addObserver(IExpressionDefinition condition, IObserverDefinition observer);
	
}
