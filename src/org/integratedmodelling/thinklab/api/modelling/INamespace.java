package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.thinklab.api.knowledge.IOntology;

/**
 * Describes an existing model namespace. All namespaces in thinklab have an associated ontology. Namespaces may contain models, contexts, scenarios and annotations. TODO namespaces could work hierarchically and return their child namespaces, too. Not sure that's a good thing or not.
 * @author  Ferd
 */
public interface INamespace {

	/**
	 * @uml.property  name="namespace"
	 */
	public abstract String getNamespace();
	
	/**
	 * @uml.property  name="ontology"
	 * @uml.associationEnd  
	 */
	public abstract IOntology getOntology();
	
	public abstract Collection<IModelObject> getModelObjects();
	
	public abstract long getLastModification();
	
}
