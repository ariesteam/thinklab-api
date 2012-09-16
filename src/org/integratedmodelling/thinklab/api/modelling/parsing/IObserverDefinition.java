package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IObserver;

public abstract interface IObserverDefinition extends IObservingObjectDefinition, IObserver {


	public void addMediatedObserver(IObserverDefinition observer, IExpressionDefinition condition);
	
}
