package org.integratedmodelling.lang.model;

public class ConceptObject extends ModelObject {

	@Override
	public String getQualifiedName() {
		return getNamespace().getId() + ":" + getId();
	}

}
