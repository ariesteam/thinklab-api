package org.integratedmodelling.thinklab.api.modelling.observation;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

public interface IDataSource {
	
	/**
	 * Tag interface to construct transformation objects that produce a datasource from
	 * another during context mediation. Such transformation may change the underlying
	 * representation of the data or subset/resample it to adapt to a different, compatible
	 * context.
	 * 
	 * @author Ferdinando Villa
	 *
	 */
	public static interface Transformation {
		
	}
	
	/**
	 * All datasources must report to the conceptual model what kind of
	 * value they are going to return.
	 * 
	 * @return
	 */
	public abstract IConcept getValueType();

	/**
	 * 
	 * @param index
	 * @return
	 */
	public abstract Object getValue(int index);
	
	/**
	 * This callback gets called before any process() command is called. It's a good place to load
	 * data, check the context and prepare for processing.
	 * 
	 * @param context
	 * @throws ThinklabException
	 */
	public abstract void preProcess(IContext context) throws ThinklabException;

	/**
	 * This callback gets called after any process() command is called but before the first
	 * values are extracted. It's a good place to load data after transformations. Datasource
	 * must be ready to serve data in the given context after this is called.
	 * 
	 * @param context
	 * @throws ThinklabException
	 */
	public abstract void postProcess(IContext context) throws ThinklabException;

	/**
	 * Process the passed transformation created by the extents and
	 * return the transformed datasource.
	 * 
	 * @param transformation
	 * @return
	 * @throws ThinklabException if the transformation cannot be handled.
	 */
	public abstract IDataSource transform(Transformation transformation)
		throws ThinklabException;
}
