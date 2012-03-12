package org.integratedmodelling.thinklab.api.knowledge.query;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * IQuery objects tagged with this one can operate on literals (we like to call
 * those "semantic operators").
 * 
 * Specific subclasses of Query capable of handling literals should be able to
 * return a concept and the necessary arguments so that an external object can
 * rewrite the query for a host infrastructure and perform it over another 
 * object or collection of objects. The concept identifies the type of comparison
 * requested (e.g. Equals or Intersects), and the objects, if any, define what to 
 * compare it to. These are passed to a KboxTypeAdapter to rewrite queries on
 * specific host infrastructures.
 * 
 * @author Ferd
 *
 */
public interface IOperator {
	
	/**
	 * Because a LiteralQuery may also operate on objects, we need a way
	 * to know if a specific instance has been configured for a literal
	 * or not. Think of something like Equals(Object). We want these
	 * polymorphic for usage reasons. So Equals(12) will answer true and
	 * Equals(john) will answer false.
	 * 
	 * @return
	 */
	public abstract boolean isLiteral();

	/**
	 * First element is the concept identifying the operation (in 
	 * thinklab.owl there are many subclasses of Comparison that 
	 * work). The second is the array of the parameters, i.e. what
	 * we compare our subject to.
	 * 
	 * @return
	 */
	public abstract Pair<IConcept, Object[]> getQueryParameters();
}
