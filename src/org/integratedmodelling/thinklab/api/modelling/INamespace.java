package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * @author  Ferd
 */
public interface INamespace {

	/**
	 * The name of the namespace - full path if dot-separated.
	 * @return
	 */
	public abstract String getId();
		
	/**
	 * Time of creation of the underlying resource if any, time when the object definition 
	 * finished otherwise.
	 * 
	 * @return
	 */
	public abstract long getTimeStamp();
	
	/**
	 * A namespace is the endorsed specification of any ontology in Thinklab (IOntology is only
	 * for bridging to the underlying knowledge infrastructure). Because individuals are dealt with
	 * at the Thinklab/Java level using ISemanticObject, only concepts and properties are of interest.
	 * 
	 * @param s the requested concept ID.
	 * @return a concept or null if not defined.
	 */
	public abstract IConcept getConcept(String s);
	
	/**
	 * A namespace is the endorsed specification of any ontology in Thinklab (IOntology is only
	 * for bridging to the underlying knowledge infrastructure). Because individuals are dealt with
	 * at the Thinklab/Java level using ISemanticObject, only concepts and properties are of interest.
	 * 
	 * @param s the requested property ID.
	 * @return a property or null if not defined.
	 */
	public abstract IProperty getProperty(String s);
	
	/**
	 * Return all objects that were explicitly created in the language statements that 
	 * produced this namespace. Implicit concepts/properties and imported objects will not be
	 * returned. 
	 *  
	 * @return
	 */
	public abstract List<IModelObject> getModelObjects();
	
	/**
	 * Get a model object by name. Will not return implicit concepts/properties and anonymous
	 * objects.
	 * 
	 * @param mod
	 * @return
	 */
	public IModelObject getModelObject(String mod);

	/**
	 * Return the project that this namespace was created from. Currently it can be null but
	 * shouldn't - external import units should be projects and interactive sessions should 
	 * operate within a specialized project.
	 * 
	 * @return
	 */
	public IProject getProject();

	public abstract String getResourceUrl();

	/**
	 * @deprecated - should disappear as we use namespaces now
	 * @return
	 */
	public abstract String getStorageKbox();
	
	/**
	 * @deprecated - should return a list of namespaces instead, or null to mean "all namespaces"
	 * @return
	 */
	public abstract String getTrainingKbox();
	
	/**
	 * @deprecated - should return a list of namespaces instead, or null to mean "all namespaces"
	 * @return
	 */
	public abstract String getLookupKbox();

	/**
	 * Return the plug-in language that is used in this namespace to parse expressions.
	 * @return
	 */
	public abstract String getExpressionLanguage();
	
	/**
	 * If a model was given a specific coverage in any extent, either directly or through
	 * a namespace-wide specification, return the context that
	 * expresses that coverage. If no coverage has been specified, return an empty
	 * context.
	 * 
	 * @return
	 */
	public abstract IContext getCoverage();

	/**
	 * Return true if the namespace has any errors that will prevent the use of its
	 * model objects.
	 * 
	 * @return
	 */
	public abstract boolean hasErrors();
}
