package org.integratedmodelling.lang.model;

public class Context extends ModelObject {

	@Override
	public String getQualifiedName() {
		return getNamespace().getId() + "/" + getId();
	}

}
