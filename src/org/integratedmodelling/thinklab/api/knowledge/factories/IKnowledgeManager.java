package org.integratedmodelling.thinklab.api.knowledge.factories;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.knowledge.IValue;
import org.integratedmodelling.thinklab.api.knowledge.kbox.IKbox;

public interface IKnowledgeManager {
	
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
	 * 
	 * @param prop
	 * @return
	 */
	public abstract IProperty getProperty(String prop);

	/**
	 * 
	 * @param prop
	 * @return
	 */
	public abstract IConcept getConcept(String prop);
	
	/**
	 * Return the concept associated to a class.
	 * 
	 * @param cls
	 * @return
	 */
	public abstract IConcept getConceptForClass(Class<?> cls);

	/**
	 * Return the Java class that serves as a peer for a concept.
	 * 
	 * @param cls
	 * @return
	 */
	public abstract Class<?> getClassForConcept(IConcept cls);
	
	/**
	 * Return the least general common concept in a collection of concept, or null if there
	 * is none.
	 * @param cc
	 * @return
	 */
	public abstract IConcept getLeastGeneralCommonConcept(IConcept ... cc);

	/**
	 * Find the concept manager that can validate a literal of the passed concept and validate the passed
	 * string into the proper literal.
	 * 
	 * @param c A concept to validate to
	 * @param literal a literal representing an instance of that concept
	 * @return a Value containing the concept
	 * @throws ThinklabValidationException
	 * @throws ThinklabException 
	 */
	public abstract IValue validateLiteral(IConcept c, String literal) throws ThinklabException;
	
}
