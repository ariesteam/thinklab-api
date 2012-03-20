package org.integratedmodelling.thinklab.api.lang.parsing;

import org.integratedmodelling.thinklab.api.modelling.IMeasuringObserver;

public interface IMeasuringObserverDefinition extends IObserverDefinition, IMeasuringObserver {

	public void setUnit(IUnitDefinition unit);
	
}
