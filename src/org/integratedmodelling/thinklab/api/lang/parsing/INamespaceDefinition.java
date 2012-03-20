package org.integratedmodelling.thinklab.api.lang.parsing;

import org.integratedmodelling.thinklab.api.knowledge.IAxiom;

public interface INamespaceDefinition extends ILanguageDefinition {

	public void setId(String id);
	
	public void addAxiom(IAxiom axiom);
	
	public void setResourceUrl(String resourceUrl);
	
	public void setTimeStamp(long timestamp);
	
	public void addImportedNamespace(INamespaceDefinition namespace);
	
	public void addModelObject(IModelObjectDefinition modelObject);
}
