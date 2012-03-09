package org.integratedmodelling.thinklab.api.knowledge.factories;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;
import org.integratedmodelling.thinklab.api.knowledge.ISemantics;
import org.integratedmodelling.thinklab.api.knowledge.kbox.IKbox;

public interface IKnowledgeManager {

	/**
	 * Find and return the named concept. If not found, return null.
	 * @param prop
	 * @return
	 */
	public abstract IConcept getConcept(String concept);

	/**
	 * Find and return the passed property. If not found, return null.
	 * 
	 * @param prop
	 * @return
	 */
	public abstract IProperty getProperty(String concept);
	
	/**
	 * Parse a literal into a semantic object of the passed concept. The concept must have 
	 * been used to annotate a class that implements IParseable.
	 * 
	 * @param c A concept to validate to
	 * @param literal a literal representing an instance of that concept
	 * @return a Value containing the concept
	 * @throws ThinklabValidationException
	 * @throws ThinklabException 
	 */
	public abstract ISemanticObject parse(String literal, IConcept c) throws ThinklabException;
	
	/**
	 * Return an annotated ISemanticObject for the passed Java object, or throw an exception if no
	 * annotation is possible. Should normally use conceptualize() to obtain the semantics.
	 * 
	 * @param object
	 * @return
	 * @throws ThinklabException
	 * literals to instances.
	 */
	public abstract ISemanticObject annotate(Object object) throws ThinklabException;
	
	
	/**
	 * Create a kbox with the named uri, using the implementation assigned to the
	 * URI protocol.
	 * 
	 * @param uri
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IKbox createKbox(String uri) throws ThinklabException;
	
	/**
	 * Drop the kbox indicated by the uri.
	 * 
	 * @param uri
	 * @throws ThinklabException
	 */
	public abstract void dropKbox(String uri) throws ThinklabException;
	
	/**
	 * Get the kbox with the named URI, creating it if it does not exist.
	 * @param uri
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IKbox requireKbox(String uri) throws ThinklabException;

	/**
	 * Return the least general common concept in a collection of concept, or null if there
	 * is none.
	 * @param cc
	 * @return
	 */
	public abstract IConcept getLeastGeneralCommonConcept(IConcept ... cc);

	/**
	 * Create a semantic annotation from the passed object. This will succeed if the
	 * object and its fields have semantic tags (@Concept, @Property, @Literal) and/or 
	 * is an IConceptualizable.
	 * 
	 * @param i
	 * @return
	 * @throws ThinklabException
	 */
	public abstract ISemantics conceptualize(Object object) throws ThinklabException;
	
	/**
	 * Reifies an annotation by producing the object it describes, if any. In order for an
	 * object to be created, annotation tags must have specified the classes to associate to
	 * concepts, properties and literals.
	 * 
	 * @param a
	 * @return
	 * @throws ThinklabException
	 */
	public Object instantiate(ISemantics a) throws ThinklabException;
}
