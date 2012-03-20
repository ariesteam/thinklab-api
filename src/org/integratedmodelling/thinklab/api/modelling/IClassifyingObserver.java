package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;

public interface IClassifyingObserver extends IObserver {

	public abstract IConcept getConceptSpace();
}
