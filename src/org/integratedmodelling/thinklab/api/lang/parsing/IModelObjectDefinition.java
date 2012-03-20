package org.integratedmodelling.thinklab.api.lang.parsing;

public interface IModelObjectDefinition extends ILanguageDefinition {

	public void setNamespace(INamespaceDefinition namespace);
	
	public void setLineNumbers(int startLine, int endLine);
	
}
