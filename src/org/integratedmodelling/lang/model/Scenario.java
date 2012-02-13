package org.integratedmodelling.lang.model;

public class Scenario extends ModelObject {

	@Override
	public String getQualifiedName() {
		return getNamespace().getId() + "/" + getId();
	}

}
