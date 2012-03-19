package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.thinklab.api.knowledge.IOntology;
import org.integratedmodelling.thinklab.api.lang.ILanguageObject;

/**
 * Describes an existing model namespace. All namespaces in thinklab have an associated ontology, although they may be in a one to many relationship 
 * with it. Namespaces may contain models, contexts, scenarios and annotations. 
 * 
 * TODO namespaces could work hierarchically and return their child namespaces, too. Not sure that's a good thing or not.
 * 
 * @author  Ferd
 */
public interface INamespace extends ILanguageObject {

	/**
	 * The ID that distinguished the object in its namespace
	 * @return
	 */
	public abstract String getId();

	@Deprecated
	public abstract String getNamespace();
	
	public abstract IOntology getOntology();
	
	public abstract Collection<IModelObject> getModelObjects();
	
	public abstract long getLastModification();
	
}
