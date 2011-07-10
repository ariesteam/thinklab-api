package org.integratedmodelling.thinklab.api.knowledge.factories;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.knowledge.IValue;

public interface IKnowledgeFactory {

	public abstract IConcept requireConcept(String id)
			throws ThinklabException;

	public abstract IProperty requireProperty(String id)
			throws ThinklabException;
	
	public abstract IProperty retrieveProperty(String prop);

	public abstract IConcept retrieveConcept(String prop);



	public abstract IConcept getLeastGeneralCommonConcept(String semanticType, String otherConcept)
			throws ThinklabValidationException;

	/**
	 * Return the least general ancestor of both concepts passed, or null if there is none.
	 * @param concept1
	 * @param c
	 * @return
	 */
	public abstract IConcept getLeastGeneralCommonConcept(IConcept concept1, IConcept c);

	/**
	 * Return the least general common concept in a collection of concept, or null if there
	 * is none.
	 * @param cc
	 * @return
	 */
	public abstract IConcept getLeastGeneralCommonConcept(Collection<IConcept> cc);

	/**
	 * Find the concept manager that can validate a literal of the passed concept and validate the passed
	 * string into the proper literal.
	 * @param c A concept to validate to
	 * @param literal a literal representing an instance of that concept
	 * @return a Value containing the concept
	 * @throws ThinklabValidationException
	 * @throws ThinklabException 
	 */
	public abstract IValue validateLiteral(IConcept c, String literal) throws ThinklabException;
}
