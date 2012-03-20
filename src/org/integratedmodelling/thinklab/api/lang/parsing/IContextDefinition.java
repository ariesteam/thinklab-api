package org.integratedmodelling.thinklab.api.lang.parsing;

import org.integratedmodelling.thinklab.api.modelling.IContext;

public interface IContextDefinition extends IModelObjectDefinition, IContext {

	public void addObservation(IObservationDefinition observation);
	
}
