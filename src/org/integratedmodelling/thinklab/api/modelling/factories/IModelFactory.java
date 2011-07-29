package org.integratedmodelling.thinklab.api.modelling.factories;

import java.util.Map;

import org.integratedmodelling.list.Polylist;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.IModelObject;
import org.integratedmodelling.thinklab.api.modelling.INamespace;

/**
 * Internal interface used by interpreters to create model objects.
 * @author  Ferd
 */
public interface IModelFactory {

	// Concepts for the different model types.
	public final IConcept C_MODEL = null;
	
	// keys for model generator map. Can also be others for different models.
	public final static String K_DEPENDENCIES = "dependencies";
	public final static String K_REQUIRED = "dependencies";
	public final static String K_KEEP = "dependencies";
	public final static String K_UNIT = "dependencies";
	public final static String K_RANGE = "dependencies";
	public final static String K_STATETYPE = "dependencies";
	public final static String K_CONTEXTMODEL = "dependencies";
	public final static String K_STATEEXPRESSION = "dependencies";
	public final static String K_UPDATEEXPRESSION = "dependencies";
	public final static String K_MOVEEXPRESSION = "dependencies";
	public final static String K_UPDATERATE = "dependencies";
	public final static String K_MOVERATE = "dependencies";
	public final static String K_CLASSIFICATION = "classification";
	public final static String K_IMPORT = "classification";
	public final static String K_FORMALNAME = "classification";
	// the next two are in sync
	public final static String K_CONDITIONALS = "classification";
	public final static String K_DEFINITION = "classification";

	public abstract INamespace createNamespace(String namespace, Polylist ontology);

	
	public abstract IModel createModel(INamespace namespace, IConcept modelType, Map<String, Object> definition);

	/**
	 * A default namespace is used when no namespace is specified. While it should be  enforced that each model file declares a namespace, interactive sessions may not do so. All model objects must live in namespaces, so this must never return null. If a user session is active, the default namespace should be linked to it and the namespace ontology should be the session's ontology. TODO see how to pass it without confusing the API for the language interpreter.
	 * @return   the default namespace.
	 * @uml.property  name="defaultNamespace"
	 * @uml.associationEnd  
	 */
	public abstract INamespace getDefaultNamespace();

	/**
	 * Register the given model object with the given name in given namespace, so that
	 * it can be found by the model manager.
	 * 
	 * @param ret
	 */
	public abstract void register(IModelObject ret, String id, INamespace namespace);

}
