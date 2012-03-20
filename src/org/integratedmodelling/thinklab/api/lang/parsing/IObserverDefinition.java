package org.integratedmodelling.thinklab.api.lang.parsing;

import java.util.Map;

import org.integratedmodelling.thinklab.api.modelling.IObserver;

public abstract interface IObserverDefinition extends IObservingObjectDefinition, IObserver {

	public void setAccessor(String accessorType, Map<String, Object> parameters);
	
	public void addMediatedObserver(IObserverDefinition observer, IExpressionDefinition condition);
	
}
