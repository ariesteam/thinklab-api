package org.integratedmodelling.thinklab.api.knowledge;

import java.util.List;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.exceptions.ThinklabCircularDependencyException;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.modelling.INamespace;

/**
 * The result of semantic annotation in Thinklab. Objects can be turned into SemanticObjects by the
 * annotate() operation of the knowledge manager. Being a semantic object implies to have both
 * the original Java Object nature and a semantic annotation, expressed as a IList (most likely
 * a IReferenceList), that expresses the object semantics and can recreate another 
 * (semantically) identical SemanticObject.
 * 
 * In order for the magic of semantic annotation and instantiation to happen, there must be
 * semantic support (ontologies) for the meaning of the object and enough annotation at the
 * Java side to link concepts and properties to the ontologies. This can happen in a few 
 * ways:
 * 
 * 1. By tagging the Java class with @Concept, which establishes the mapping of the class to
 *    an instance of that concept, and either using @Property to link fields to properties or 
 *    letting the conventions for field naming automatically link fields to properties;
 *    
 * 2. By tagging the Java class with @Concept and implementing the IConceptualizable
 *    interface;
 *    
 * 3. By manually registering the class to a concept in the knowledge manager, and
 *    proceeding as in (1) or (2) to link fields to properties.
 *    
 * @author Ferd
 *
 */
public interface ISemanticObject<T extends Object> {
	
	/**
	 * All semantic objects live in a namespace - if they haven't been explicitly 
	 * declared as such, their namespace is the namespace of the concept they
	 * incarnate.
	 * 
	 * @return
	 */
	public INamespace getNamespace();
	
	/**
	 * Return the bare semantics of this object, from which another identical object can be
	 * instantiated by the knowledge manager.
	 * 
	 * @return
	 * @see IKnowledgeManager.instantiate()
	 */
	public abstract IList getSemantics();
	
	/**
	 * Return the bare Java object that was annotated through this object. In most cases the
	 * semantics can be reconstructed by the knowledge manager, although it may be more general
	 * than what we have here.
	 * 
	 * For all practical purposes, this object should be consider immutable - except that Java doesn't
	 * allow that. Modifying the object may put it out of sync with its semantics, so make sure
	 * you know what you're doing.
	 * 
	 * @return
	 * @see IKnowledgeManager.conceptualize()
	 */
	public abstract T demote();
	
	/**
	 * Return the concept that this object directly incarnates. We assume it's one, i.e. semantic objects
	 * are not created directly from intersections, unions and the like. 
	 * 
	 * @return
	 */
	public IConcept getDirectType();
	
	/**
	 * Use whatever reasoning strategy is supported in the implementation to check whether our direct type
	 * is the passed one. Any object should be admitted without error, but only sensible ones should have
	 * a chance of resulting in a true return value. Normally implementations will check concepts, ISemantics, ISemanticObject
	 * and Strings, if wanted, after converting them to concepts.
	 * 
	 * If the other object is a ISemanticObject, the function should check whether the objects classify 
	 * to the same object in the implemented ontology infrastructure. The meaning of conformance can be 
	 * defined by using a default conformance operator. 
	 * 
	 * @param concept
	 * @return
	 */
	public boolean is(Object other);

	/**
	 * Return the total amount of objects that are in any relationship with this one.
	 * 
	 * @return
	 */
	public abstract int getRelationshipsCount();

	/**
	 * Return the total amount of objects that are in the named relationship with this one.
	 * 
	 * @param _subject
	 * @return
	 */
	public abstract int getRelationshipsCount(IProperty property);
	
	/**
	 * Return the target value of the passed property, assuming there is just one, or null 
	 * if it doesn't exist in this object. If there is more than one value, throw a 
	 * runtime exception.
	 * 
	 * @param property
	 * @return
	 */
	public abstract ISemanticObject<?> get(IProperty property);

	/**
	 * Return all the relationships for this object.
	 * 
	 * @return
	 */
	public abstract List<Pair<IProperty, ISemanticObject<?>>> getRelationships();

	/**
	 * Return all the objects that this object is in the named relationship with.
	 * 
	 * @param property
	 * @return
	 */
	public abstract List<ISemanticObject<?>> getRelationships(IProperty property);

	
	/**
	 * Return whether this object represents a Java Plain Old Literal, such as a Double or a String, 
	 * or any other user-defined class that was annotated with the @Literal annotation and has a 
	 * corresponding datatype.
	 * 
	 * @return
	 */
	public boolean isLiteral();
	
	/**
	 * Return whether this object represents bare semantics - i.e. a concept without a corresponding
	 * Java object. Relationships that link to these are "classification properties", transparently
	 * handled as objects for compliance with OWL-DL.
	 * 
	 * @return
	 */
	public boolean isConcept();
	
	/**
	 * Shorthand for (!isLiteral() && !isConcept()) - if true, this object contains non-trivial
	 * object semantics, which should correspond to a non-null Java peer unless semantic mappings
	 * have not been defined for its concept.
	 * 
	 * @return
	 */
	public boolean isObject();

	/*
	 * -----------------------------------------------------------------------------------------
	 * Introspection methods to investigate the nature of the semantics
	 * -----------------------------------------------------------------------------------------
	 */

	/**
	 * Return whether the graph created by this object's relationships has circular dependencies.
	 * 
	 * @return
	 */
	public boolean isCyclic();
	
	/**
	 * Apply any semantic validation defined by the host infrastructure and return whether
	 * the object is semantically consistent.
	 * 
	 * @return
	 */
	public boolean isValid();
		
	/**
	 * Follow the path to other objects identified by the passed property and return
	 * the topological sorting of all objects encountered. If the graph is cyclic, throw
	 * an exception. Ignores relationships to literal objects of course.
	 *  
	 * @return
	 */
	public List<ISemanticObject<?>> getSortedRelationships(IProperty property) 
			throws ThinklabCircularDependencyException;
	

}
