package org.integratedmodelling.thinklab.api.knowledge;

import org.integratedmodelling.thinklab.api.knowledge.query.IQuery;

/**
 * Defines semantically comparable objects.
 * 
 * An IComparable is something capable of producing a query to select
 * objects that compare to it in the way expressed by a particular
 * concept.
 * 
 * @author Ferd
 *
 */
public interface IComparable {

	public abstract IQuery getQuery(IConcept comparison, Object comparable);
}
