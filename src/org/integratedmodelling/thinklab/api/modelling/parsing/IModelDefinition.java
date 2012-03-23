package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IModel;

public interface IModelDefinition extends IObservingObjectDefinition, IModel {

	public void addObserver(IObserverDefinition observer, IExpressionDefinition expression);
	
}
