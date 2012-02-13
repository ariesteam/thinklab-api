package org.integratedmodelling.lang.model;

public class PropertyObject extends ModelObject {

	@Override
	public String getQualifiedName() {
		return getNamespace().getId() + ":" + getId();
	}


}
