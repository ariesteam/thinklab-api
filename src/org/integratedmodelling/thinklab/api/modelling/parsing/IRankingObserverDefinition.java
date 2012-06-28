package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IClassification;
import org.integratedmodelling.thinklab.api.modelling.IRankingObserver;

public interface IRankingObserverDefinition extends IObserverDefinition, IRankingObserver {

	public void setType(int type);
	
	public void setScale(Number from, Number to);

	public void setDiscretization(IClassification classification);
	
}
