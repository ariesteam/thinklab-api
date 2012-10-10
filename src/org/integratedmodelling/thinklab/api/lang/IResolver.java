package org.integratedmodelling.thinklab.api.lang;

import java.io.InputStream;
import java.util.Map;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.modelling.IModelObject;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.modelling.parsing.IConceptDefinition;
import org.integratedmodelling.thinklab.api.modelling.parsing.IFunctionCall;
import org.integratedmodelling.thinklab.api.modelling.parsing.ILanguageDefinition;
import org.integratedmodelling.thinklab.api.modelling.parsing.INamespaceDefinition;
import org.integratedmodelling.thinklab.api.modelling.parsing.IPropertyDefinition;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * A Resolver is used by anything that generates model objects to interface to the parser for
 * whatever language is implemented. Using a resolver is a way to make any language implementation
 * depend only on the API. 
 * 
 * @author Ferd
 *
 */
public interface IResolver {
	
	/*
	 * the following are keys for concepts that the upper ontology is expected to
	 * provide. Concept definitions will be automatically derived from these 
	 * according to the idiom used to declare them.
	 */
	public static final String OBJECT_CONCEPT = "object";
	public static final String PROCESS_CONCEPT = "process";
	public static final String QUALITY_CONCEPT = "quality";
	public static final String QUALITY_SPACE_CONCEPT = "class";
	public static final String THING_CONCEPT = "thing";
	public static final String ENERGY_CONCEPT = "energy";
	public static final String ENTROPY_CONCEPT = "entropy";
	public static final String LENGTH_CONCEPT = "length";
	public static final String MASS_CONCEPT = "mass";
	public static final String VOLUME_CONCEPT = "volume";
	public static final String WEIGHT_CONCEPT = "weight";
	public static final String MONETARY_VALUE_CONCEPT = "money";
	public static final String PREFERENCE_VALUE_CONCEPT = "priority";
	public static final String ACCELERATION_CONCEPT = "acceleration";
	public static final String AREA_CONCEPT = "area";
	public static final String DENSITY_CONCEPT = "density";
	public static final String ELECTRIC_POTENTIAL_CONCEPT = "electric-potential";
	public static final String CHARGE_CONCEPT = "charge";
	public static final String RESISTANCE_CONCEPT = "resistance";
	public static final String RESISTIVITY_CONCEPT = "resistivity";
	public static final String PRESSURE_CONCEPT = "pressure";
	public static final String SLOPE_CONCEPT = "slope";
	public static final String SPEED_CONCEPT = "speed";
	public static final String TEMPERATURE_CONCEPT = "temperature";
	public static final String VISCOSITY_CONCEPT = "viscosity";
	public static final String AGENT_CONCEPT = "agent";

	/**
	 * Return the core ontology concept corresponding to the passed key - chosen between 
	 * those above. These are used to define the semantics of specific observables using 
	 * language keywords instead of explicit derivations.
	 *  
	 * @param key
	 * @return
	 */
	public abstract IConcept getConceptFor(String key);
	
	/**
	 * This one returns a new "definable" object for the model class passed. This way each 
	 * implementation can use their own objects and the API remains clean.
	 * 
	 * @param cls
	 * @return
	 */
	public abstract ILanguageDefinition newLanguageObject(Class<?> cls);
	
	/**
	 * Override to fine-tune error management. If this throws an exception, the parser also will. If not, 
	 * the return value determines whether parsing continue (true) or not (false).
	 * 
	 * @param e
	 * @return
	 */
	public abstract boolean onException(Throwable e, int lineNumber);

	/**
	 * Override to fine-tune error management. Will stop parsing if false is returned.
	 * 
	 * @param warning
	 * @return
	 */
	public abstract boolean onWarning(String warning, int lineNumber);

	/**
	 * Override to fine-tune error management. Will stop parsing if false is returned.
	 * 
	 * @param info
	 * @return
	 */
	public abstract boolean onInfo(String info, int lineNumber);

	/**
	 * Callback invoked as soon as a namespace declaration is parsed. The resolver should contain
	 * the namespace it is being used for.
	 * 
	 * @param namespaceId
	 * @param resourceId
	 * @param namespace
	 */
	public abstract void onNamespaceDeclared();
	
	/**
	 * Callback invoked as soon as parsing of a namespace has been completed. The namespace will contain all
	 * model objects declared in it and the axioms collected from them. 
	 * 
	 * @param namespace
	 * @throws ThinklabException 
	 */
	public abstract void onNamespaceDefined();

	/**
	 * Callback invoked at every new model object at main level. Not all the namespace will be
	 * defined when this is called.
	 * 
	 * @param namespace
	 * @param ret
	 * @throws ThinklabException 
	 */
	public abstract void onModelObjectDefined(IModelObject ret);

	/**
	 * Called when an external concept name (with the :) is identified in a legal place in a model object. Should
	 * return a ConceptObject pointing to whatever definition of the import we need, which will be set in the
	 * model tree.
	 *  
	 * @param id
	 * @param namespace
	 * @param line 
	 * @return
	 */
	public abstract IConceptDefinition resolveExternalConcept(String id, int line);
	
	/**
	 * Called when an external property name (with the :) is identified in a legal place in a model object. Should
	 * return a PropertyObject pointing to whatever definition of the import we need, which will be set in the
	 * model tree.
	 *  
	 * @param id
	 * @param namespace
	 * @param line 
	 * @return
	 */
	public abstract IPropertyDefinition resolveExternalProperty(String id, int line);

	/**
	 * Check if a passed model object ID has been generated by generateId().
	 * 
	 * @param id
	 * @return
	 */
	public abstract boolean isGeneratedId(String id);

	/**
	 * Generate an ID for the passed model object. Only the type of the model object should be
	 * checked, as the remaining attributes may not be initialized when this is called. The ID
	 * should be recognizable by isGeneratedId().
	 * 
	 * @param o
	 * @return
	 */
	public abstract String generateId(IModelObject o);

	/**
	 * Return the last model object notified to the resolver. Used by interactive
	 * applications only. Should not return any object more than once.
	 * 
	 * @return
	 */
	public abstract IModelObject getLastProcessedObject();

	/**
	 * Return whether the resolver is being used in an interactive session. The parser may
	 * allow or disallow some statements in that case. 
	 * 
	 * @return
	 */
	boolean isInteractive();

	/**
	 * In an interactive session, an 'observe' command should be supported which calls
	 * this method for handling. The command is expected to contribute observations to
	 * the current context in the current session. It will not be called on a resolver
	 * whose isInteractive() returns false.
	 * 
	 * @param observable
	 * @param lineNumber
	 * @throws ThinklabException 
	 */
	public abstract void handleObserveStatement(
			Object observable,  boolean distribute, IPropertyDefinition property, int lineNumber);

	/**
	 * Get another resolver to handle an imported project. The lifetime of this new 
	 * resolver is local to ours.
	 * 
	 * @param p 
	 * 
	 * @return
	 */
	public abstract IResolver getImportResolver(IProject p);
	
	/**
	 * Return a resolver for parsing the passed namespace, part of the project this resolver
	 * was created for. This one must check that the 
	 * resource is OK, that the namespace ID and the resource ID match if necessary, 
	 * prepare to return a valid (empty) namespace when getNamespace() is called, and
	 * a valid InputStream for the resource when openStream() is called. Resource checks
	 * should be done here - if this exits successfully, openStream should not fail.
	 * 
	 * For now resource may be null - flagging an interactive namespace that will be
	 * defined incrementally without an input stream.
	 * 
	 * @param namespace
	 * @param resource
	 * @return
	 */
	public abstract IResolver getNamespaceResolver(String namespace, String resource);

	/**
	 * Return the INamespaceDefinition corresponding to the specs given when this resolver
	 * was created using getNamespaceResolver(). Must not be null. It will not be called on
	 * a resolver not returned by getNamespaceResolver().
	 * 
	 * @return
	 */
	public abstract INamespaceDefinition getNamespace();
	
	/**
	 * Return an open InputStream corresponding to the resource given at creation by
	 * getNamespaceResolver().  It will not be called on a resolver not returned by 
	 * getNamespaceResolver() or on a namespace whose isInteractive() returns true.
	 * 
	 * @return
	 */
	public abstract InputStream openStream();
	
	/**
	 * Return a previously defined namespace from the global namespace catalog, or null if
	 * not seen before. It is called to resolve imports, so all projects imported by the 
	 * currently parsing project should have been parsed when this is called.
	 * 
	 * @param id
	 * @param lineNumber the line where the namespace is called for, for error reporting.
	 * @return
	 */
	public abstract INamespace getNamespace(String id, int lineNumber);

	/**
	 * Define symbol with current namespace visibility. Won't be called on a resolver not
	 * returned by getNamespaceResolver().
	 * 
	 * @param id
	 * @param value
	 * @param lineNumber 
	 */
	public abstract void defineSymbol(String id, Object value, int lineNumber);
	
	/**
	 * Return the namespace-specific symbol table that is filled in by the parser using
	 * defineSymbol().  It will not be called on a resolver not returned by getNamespaceResolver().
	 * 
	 * @return
	 */
	public abstract Map<String, Object>  getSymbolTable();

	/**
	 * If the resolver is for a project, as it should be, return the project.
	 * Namespace resolvers are expected to return the project that the namespace
	 * belongs to.
	 * 
	 * @return
	 */
	public abstract IProject getProject();

	/**
	 * Pass a function call, warn or complain if it doesn't match a known prototype.
	 * 
	 * @param ret
	 * @return 
	 */
	public abstract boolean validateFunctionCall(IFunctionCall ret);
	
	/**
	 * Return a language adapter for the expression language that this
	 * namespace expects.
	 * 
	 * @return
	 */
	public abstract IExpressionLanguageAdapter getLanguageAdapter();

	
}