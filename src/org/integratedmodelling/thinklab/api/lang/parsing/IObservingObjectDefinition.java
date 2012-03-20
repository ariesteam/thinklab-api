package org.integratedmodelling.thinklab.api.lang.parsing;

import org.integratedmodelling.thinklab.api.lang.IList;

public abstract interface IObservingObjectDefinition extends IModelObjectDefinition {

	public void addObservable(IList semantics);
	
	public void addDependency(IModelDefinition model, String formalName, boolean isRequired);
}
