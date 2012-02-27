package org.integratedmodelling.thinklab.api.knowledge;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.query.IQuery;
import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * Semantic adapters can be linked to classes using the @SemanticAdapter annotation to provide
 * serialization to/from semantic lists for objects. It's an annotation-based version of 
 * IConceptualizable.
 * 
 * @author Ferd
 *
 */
public interface ISemanticAdapter {
	
	/**
	 * Conceptualize an object to a semantic statement (which can be turned into an IInstance).
	 * 
	 * @param target
	 * @return
	 * @throws ThinklabException
	 */
	public IList conceptualize(Object target) throws ThinklabException;
	
	/**
	 * Create a new object of the target class from a conceptualization list.
	 * 
	 * @param conceptualization
	 * @return
	 * @throws ThinklabException
	 */
	public Object create(IList conceptualization) throws ThinklabException;

	/**
	 * Create a semantic query that selects an object like the target.
	 * 
	 * @param target
	 * @return
	 * @throws ThinklabException
	 */
	public IQuery query(Object target) throws ThinklabException;
	
}
