package org.integratedmodelling.thinklab.api.knowledge.factories;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.knowledge.IValue;

public interface IKnowledgeManager {
	
	public abstract IProperty getProperty(String prop);

	public abstract IConcept getConcept(String prop);

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
