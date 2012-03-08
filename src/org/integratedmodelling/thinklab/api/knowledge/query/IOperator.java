package org.integratedmodelling.thinklab.api.knowledge.query;


/**
 * An IRestriction creates a semantic query that will select semantic objects in a specific
 * relationship with the arguments it's been initialized with. Semantically it can be called a
 * Restriction.
 *  
 * @author Ferdinando
 *
 */
public interface IOperator  {
	
	public IQuery getQuery();
	
}
