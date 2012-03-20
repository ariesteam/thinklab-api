package org.integratedmodelling.thinklab.api.lang.parsing;

import org.integratedmodelling.thinklab.api.modelling.IModelObject;

public abstract interface IModelObjectDefinition extends ILanguageDefinition, IModelObject {

	public void setId(String id);
	
	public void setNamespace(INamespaceDefinition namespace);
	
	public void setLineNumbers(int startLine, int endLine);
	
	public void setMetadata(IMetadataDefinition metadata);
}