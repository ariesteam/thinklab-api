package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.modelling.IClassifyingObserver;


public abstract interface IClassifyingObserverDefinition extends IObserverDefinition, IClassifyingObserver {
	
	public abstract void setConceptSpace(IConcept concept);
}
