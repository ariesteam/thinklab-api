package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.knowledge.IAxiom;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.project.IProject;

public interface INamespaceDefinition extends ILanguageDefinition, INamespace {

	public void setId(String id);
	
	public void addAxiom(IAxiom axiom);
	
	public void setResourceUrl(String resourceUrl);
	
	public void setTimeStamp(long timestamp);
	
	public void addImportedNamespace(INamespaceDefinition namespace);
	
	public void addModelObject(IModelObjectDefinition modelObject);
	
	public void setProject(IProject project);
	
	public void setStorageKbox(String kboxUri);

	public void setTrainingKbox(String kboxUri);

	public void setLookupKbox(String kboxUri);
}
