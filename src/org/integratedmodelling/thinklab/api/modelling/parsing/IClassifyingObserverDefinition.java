package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IClassification;
import org.integratedmodelling.thinklab.api.modelling.IClassifyingObserver;


public abstract interface IClassifyingObserverDefinition extends IObserverDefinition, IClassifyingObserver {
	
	public abstract void setClassification(IClassification classification);
}
