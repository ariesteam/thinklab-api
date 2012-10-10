package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.knowledge.IExpression;
import org.integratedmodelling.thinklab.api.modelling.IConditionalObserver;


public interface IConditionalObserverDefinition extends IObserverDefinition, IConditionalObserver {
	
	public void addObserver(IExpression condition, IObserverDefinition observer);
	
}
