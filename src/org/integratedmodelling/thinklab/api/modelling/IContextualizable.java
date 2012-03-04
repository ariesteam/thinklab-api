package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.thinklab.api.knowledge.IProperty;

public interface IContextualizable<T> {

	public abstract IProperty getContextProperty();
	
	public abstract List<T> getContextObjects();
}
