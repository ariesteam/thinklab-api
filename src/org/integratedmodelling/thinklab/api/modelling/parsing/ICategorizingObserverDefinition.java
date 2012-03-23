package org.integratedmodelling.thinklab.api.modelling.parsing;

import java.util.Set;

import org.integratedmodelling.thinklab.api.modelling.ICategorizingObserver;

public abstract interface ICategorizingObserverDefinition extends IObserverDefinition, ICategorizingObserver {
	
	public abstract void setDictionary(Set<String> dictionary);
	
}
