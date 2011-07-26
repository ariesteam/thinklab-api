package org.integratedmodelling.thinklab.api.modelling.factories;

import org.integratedmodelling.list.Polylist;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.INamespace;

/**
 * Internal interface used by interpreters to create model objects.
 * @author  Ferd
 */
public interface IModelFactory {

	public abstract INamespace createNamespace(String namespace, Polylist ontology);

	public abstract IModel createModel(String namespace, Polylist ontology);

	/**
	 * A default namespace is used when no namespace is specified. While it should be  enforced that each model file declares a namespace, interactive sessions may not do so. All model objects must live in namespaces, so this must never return null. If a user session is active, the default namespace should be linked to it and the namespace ontology should be the session's ontology. TODO see how to pass it without confusing the API for the language interpreter.
	 * @return   the default namespace.
	 * @uml.property  name="defaultNamespace"
	 * @uml.associationEnd  
	 */
	public abstract INamespace getDefaultNamespace();

}
