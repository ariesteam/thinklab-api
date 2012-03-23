package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IModelObject;

public abstract interface IModelObjectDefinition extends ILanguageDefinition, IModelObject {

	public void setId(String id);
	
	public void setNamespace(INamespaceDefinition namespace);
	
	public void setMetadata(IMetadataDefinition metadata);
}
