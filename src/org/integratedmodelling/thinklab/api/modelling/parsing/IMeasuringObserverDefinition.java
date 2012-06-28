package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IClassification;
import org.integratedmodelling.thinklab.api.modelling.IMeasuringObserver;

public interface IMeasuringObserverDefinition extends IObserverDefinition, IMeasuringObserver {

	public void setUnit(IUnitDefinition unit);

	public void setDiscretization(IClassification classification);
	
}
