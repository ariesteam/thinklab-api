package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.knowledge.IAxiom;
import org.integratedmodelling.thinklab.api.modelling.IExtent;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.project.IProject;

public interface INamespaceDefinition extends ILanguageDefinition, INamespace {

	public void setId(String id);
	
	public void addAxiom(IAxiom axiom);
	
	public void setResourceUrl(String resourceUrl);
	
	public void setTimeStamp(long timestamp);
	
	public void addImportedNamespace(INamespace namespace);
	
	public void addModelObject(IModelObjectDefinition modelObject);
	
	public void setProject(IProject project);
	
	public void setStorageKbox(String kboxUri);

	public void setTrainingKbox(String kboxUri);

	public void setLookupKbox(String kboxUri);
	
	public void setExpressionLanguage(String language);
	
	/**
	 * Add any extent outside of which the model should not be applied. If this
	 * is done more than once for an extent of the same type, the extents should be
	 * merged. Exceptions may result from the merging.
	 * 
	 * @param extent
	 */
	public void addCoveredExtent(IExtent extent);
}
