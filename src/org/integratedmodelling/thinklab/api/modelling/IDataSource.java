package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * Datasources are non-semantic datasets for one observable that are aware of what 
 * contexts mean, and are capable of recontextualizing to another context (or to complain
 * about that). Datasource implementations are responsible for all context transformations
 * in Thinklab.
 * 
 * @author  Ferd
 */
public interface IDataSource {
	
	/**
	 * All datasources must report what kind of value they are going to return. Core ontologies should
	 * be used for base types; if not one of those, return the most specific common concept between the
	 * (hopefully disjoint) state types.
	 * 
	 * @return
	 */
	public abstract IConcept getValueType();

	/**
	 * Return the n-th value for the context that was passed to contextualize().
	 * 
	 * @param index
	 * @return
	 */
	public abstract Object getValue(int index);

	/**
	 * Produce a datasource that fits the passed context. If we don't need transformations,
	 * return this. If we can't transform or don't understand the extents in the context,
	 * throw an exception.
	 * 
	 * This one must store enough context information to be able to respond properly to
	 * getValue(n).
	 * 
	 * @param context
	 * @return
	 * @throws ThinklabException if the transformation cannot be handled.
	 */
	public abstract IDataSource contextualize(IContext context) throws ThinklabException;
}
