package org.integratedmodelling.thinklab.api.lang.parsing;

import org.integratedmodelling.thinklab.api.modelling.IModel;

public interface IModelDefinition extends IObservingObjectDefinition, IModel {

	public void setObserver(IObserverDefinition observer);
	
}
