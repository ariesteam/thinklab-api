package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * Datasources abstract generators of states for a given context.
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
	 * Return the n-th value for the context that was passed to notifyTargetContext() and 
	 * transform().
	 * 
	 * @param index
	 * @return
	 */
	public abstract Object getValue(int index);
	
	/**
	 * This callback gets called before any value is extracted. The same context will later
	 * be passed to transform() which expects the datasource to conform to it. It's a good 
	 * place to check context compatibility, load data and prepare for processing. 
	 * 
	 * @param context
	 * @throws ThinklabException
	 */
	public abstract void notifyTargetContext(IContext context) throws ThinklabException;

	/**
	 * Process the passed transformation created by the extents and
	 * return the transformed datasource. If no transformation is necessary,
	 * return this. The context is the same that was previously passed to
	 * the single invocation of notifyTargetContext, where any compatibility
	 * checking must have been done.
	 * 
	 * @param context
	 * @return
	 * @throws ThinklabException if the transformation cannot be handled.
	 */
	public abstract IDataSource transform(IContext context) throws ThinklabException;
}
