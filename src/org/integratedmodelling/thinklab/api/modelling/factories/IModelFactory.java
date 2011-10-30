package org.integratedmodelling.thinklab.api.modelling.factories;

import java.util.Map;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.lang.SemanticType;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.IModelObject;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.modelling.units.IUnit;

/**
 * Internal interface used by interpreters to create model objects.
 * 
 * @author Ferd
 */
public interface IModelFactory {

	// keys for model generator map. Can also be others for different models.
	public final static String K_REQUIRED = "required";
	public final static String K_KEEP = "keep";
	public final static String K_UNIT = "unit";
	public final static String K_RANGE = "range";
	public final static String K_STATETYPE = "statetype";
	public final static String K_CONTEXTMODEL = "contextmodel";
	public final static String K_STATEEXPRESSION = "stateexpression";
	public final static String K_UPDATEEXPRESSION = "updateexpression";
	public final static String K_MOVEEXPRESSION = "moveexpression";
	public final static String K_UPDATERATE = "updaterate";
	public final static String K_MOVERATE = "moverate";
	public final static String K_CLASSIFICATION = "classification";
	public final static String K_IMPORT = "import";
	public final static String K_FORMALNAME = "formalname";
	public final static String K_OBSERVABLE = "observable"; // InstanceList

	// the next two are in sync
	public final static String K_DEPENDENCIES = "dependencies"; // List<IModel>
	public final static String K_DEPENDENCYNAMES = "depnames"; // List<String>
	public final static String K_MANDATORYDEPENDENCY = "mandatory"; // List<Boolean>

	// the next two are in sync
	public final static String K_CONDITIONALS = "conditionals"; // List<IExpression>
	public final static String K_DEFINITION = "definition"; // List<IModel>

	// the next two are in sync
	public final static String K_MCONDITIONALS = "mconditionals"; // List<IExpression>
	public final static String K_MEDIATED = "mediated"; // List<IModel>

	
	public abstract INamespace createNamespace(String namespace,
			String ontologyId, IList ontology);

	/**
	 * This one creates a model of the passed semantic type as defined in the
	 * passed map (using the K_keywords above).
	 * 
	 * @param namespace
	 * @param modelType
	 * @param definition
	 * @return
	 */
	public abstract IModel createModel(INamespace namespace,
			SemanticType modelType, Map<String, Object> definition) throws ThinklabException;

	/**
	 * A default namespace is used when no namespace is specified. While it
	 * should be enforced that each model file declares a namespace, interactive
	 * sessions may not do so. All model objects must live in namespaces, so
	 * this must never return null. If a user session is active, the default
	 * namespace should be linked to it and the namespace ontology should be the
	 * session's ontology. TODO see how to pass it without confusing the API for
	 * the language interpreter.
	 * 
	 * @return the default namespace.
	 * @uml.property name="defaultNamespace"
	 * @uml.associationEnd
	 */
	public abstract INamespace getDefaultNamespace();

	/**
	 * Register the given model object with the given name in given namespace,
	 * so that it can be found by the model manager.
	 * 
	 * @param ret
	 */
	public abstract void register(IModelObject ret, String id,
			INamespace namespace);

	
	/**
	 * Copy the model object passed into another one. Used to convert independent
	 * implementations into one another. Typical use is to take the syntactic
	 * constructs built by the language parser and convert them into models for
	 * the target implementation. This gives the language parser a way to build
	 * conformant objects that can be reused for actual work without devising
	 * extremely complex constructors.
	 * 
	 * Must perform a deep copy and return completely functional objects.
	 * 
	 * @param o the object to copy
	 * @param namespace if not null, use this namespace for the resulting objects instead
	 * 	      of the original one.
	 * @return a new object in the target implementation for this factory.
	 */
	public abstract IModelObject clone(IModelObject o, INamespace namespace);
	
	/**
	 * Gateway to whatever unit parser we use. 
	 * 
	 * @param unit
	 * @return
	 * @throws ThinklabValidationException
	 */
	public abstract IUnit parseUnit(String unit) throws ThinklabValidationException;
}
