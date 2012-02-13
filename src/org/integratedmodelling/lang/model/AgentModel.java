package org.integratedmodelling.lang.model;

public class AgentModel extends ModelObject {

	@Override
	public String getQualifiedName() {
		return getNamespace().getId() + "/" + getId();
	}

}
