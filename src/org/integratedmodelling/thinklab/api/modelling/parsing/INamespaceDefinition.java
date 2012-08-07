package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.knowledge.IAxiom;
import org.integratedmodelling.thinklab.api.knowledge.IOntology;
import org.integratedmodelling.thinklab.api.modelling.IExtent;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.project.IProject;

public interface INamespaceDefinition extends ILanguageDefinition, INamespace {

	/**
	 * Set the namespace ID. Only for resolvers and such.
	 * 
	 * @param id
	 */
	public void setId(String id);
	
	/**
	 * Add an axiom for the namespace's tbox.
	 * 
	 * @param axiom
	 */
	public void addAxiom(IAxiom axiom);
	
	/**
	 * Set the resource name that this namespace was created from.
	 * 
	 * @param resourceUrl
	 */
	public void setResourceUrl(String resourceUrl);
	
	/**
	 * Timestamp of last modification - tied to the resource or creation date if
	 * ephemeral.
	 * 
	 * @param timestamp
	 */
	public void setTimeStamp(long timestamp);
	
	/**
	 * Notification of imported namespace.
	 * 
	 * @param namespace
	 */
	public void addImportedNamespace(INamespace namespace);
	
	/**
	 * Notification of model object read within this namespace.
	 * 
	 * @param modelObject
	 */
	public void addModelObject(IModelObjectDefinition modelObject);
	
	/**
	 * Set project we belong to. 
	 * 
	 * @param project
	 */
	public void setProject(IProject project);

	
	/**
	 * Set from namespace header specs if a language was defined. The language used for 
	 * expressions is a namespace-scoped definition, i.e., no mixing of expression 
	 * languages within the same namespace.
	 * 
	 * 
	 * @param language
	 */
	public void setExpressionLanguage(String language);
	
	/**
	 * Add any extent outside of which the model should not be applied. If this
	 * is done more than once for an extent of the same type, the extents should be
	 * merged. Exceptions may result from the merging.
	 * 
	 * @param extent
	 */
	public void addCoveredExtent(IExtent extent);

	/**
	 * Passed if the namespace provides knowledge that is specific to a particular
	 * kind of agent.
	 * 
	 * @param agentConcept
	 */
	public void setAgentConcept(IConceptDefinition agentConcept);
	
	/**
	 * Add an error to the namespace. Used by the parser and resolver. Line number should be
	 * 1-based, and 0 if the error doesn't come from a specific statement. Error code is
	 * implementation-dependent and if not used, anything may be passed.
	 * 
	 * @param errorCode
	 * @param errorMessage
	 * @param lineNumber
	 */
	public void addError(int errorCode, String errorMessage, int lineNumber);
	
	/**
	 * Add a warning to the namespace. Line number as in {@link addError()}.
	 * @param warning
	 * @param lineNumber
	 */
	public void addWarning(String warning, int lineNumber);

	/**
	 * Add a namespace ID that will be used to resolve dependencies. Default when none
	 * is added is to use all visible namespaces.
	 * 
	 * @param tns
	 */
	public void addLookupNamespace(String tns);

	/**
	 * Add a namespace ID that will be used to find evidence for training. Default is
	 * to use all visible namespaces, which is bad. TODO may want to make it mandatory
	 * for training to happen.
	 *
	 * @param tns
	 */
	public void addTrainingNamespace(String tns);

	/**
	 * Set the ontology manually. Done when the ontology is created externally to the
	 * namespace.
	 * 
	 * @param iOntology
	 */
	public void setOntology(IOntology iOntology);
	
}
