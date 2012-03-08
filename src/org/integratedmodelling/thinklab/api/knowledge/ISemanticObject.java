package org.integratedmodelling.thinklab.api.knowledge;

import java.util.List;

import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.lang.Semantics;

public interface ISemanticObject {

	/**
	 * Return the bare semantics of this object, from which another identical object can be
	 * instantiated by the knowledge manager.
	 * 
	 * @return
	 * @see IKnowledgeManager.instantiate()
	 */
	public abstract Semantics getSemantics();
	
	/**
	 * Return the bare Java object that was annotated through this object. In most cases the
	 * semantics can be reconstructed by the knowledge manager, although it may be more general
	 * than what we have here.
	 * 
	 * TBC could be called demote() - stripping the object of semantics. Don't know what is best.
	 * 
	 * @return
	 * @see IKnowledgeManager.conceptualize()
	 */
	public abstract Object getObject();
	
	/**
	 * Return the concept that this object directly incarnates. We assume it's one, i.e. semantic objects
	 * are not created directly from intersections, unions and the like. 
	 * 
	 * @return
	 */
	public IConcept getDirectType();
	
	/**
	 * Use whatever reasoning strategy is supported in the implementation to check whether our direct type
	 * is the passed one.
	 * 
	 * @param concept
	 * @return
	 */
	public boolean is(IConcept concept);
	
	/**
	 * Shorthand for applying a default conformance operator between us and another object. It should
	 * resolve to checking whether the objects classify to the same object in the implemented
	 * ontology infrastructure. The meaning of conformance can be defined by using the full conformance
	 * API. 
	 * 
	 * @param object
	 * @return
	 */
	public boolean is(ISemanticObject object);

	/**
	 * Return the target value of the passed relationship, or null if it doesn't exist in this object.
	 * 
	 * @param property
	 * @return
	 */
	public abstract ISemanticObject get(IProperty property);
	
	/**
	 * Return all the values of the passed relationship, or an empty list if it doesn't exist
	 * in this object.
	 * 
	 * Unless this object has been through OWL/RDF, the order of the relationships is preserved.
	 * 
	 * @param property
	 * @return
	 */
	public abstract List<ISemanticObject> getAll(IProperty property);
	
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
	 * TODO add optional semantic validation - which may use the reasoner in implementations 
	 */
	public abstract void validate() throws ThinklabValidationException;
	
	/*
	 * -----------------------------------------------------------------------------------------
	 * responding yes to isLiteral() or isConcept() gives us the right to call these. Not very elegant, but
	 * the tradeoff is with the ugliness of the code that will use it.
	 * -----------------------------------------------------------------------------------------
	 */
	
	public boolean asBoolean();
	
	public int asInteger();
	
	public double asDouble();
	
	public float asFloat();
	
	public String asString();
	
}
