package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * @author  Ferd
 */
public interface IDataSource {
	
	/**
	 * Tag interface to construct transformation objects that produce a datasource from
	 * another during context mediation. Such transformation may change the underlying
	 * representation of the data or subset/resample it to adapt to a different, compatible
	 * context. It may also turn an import specification into an actual DS.
	 * 
	 * @author Ferdinando Villa
	 *
	 */
	public static interface Transformation {
		
		public void initialize(IContext ctx);
		
		public IDataSource execute();
	}
	
	/**
	 * All datasources must report what kind of value they are going to return. Core ontologies should
	 * be used for base types; if not one of those, return the most specific common concept between the
	 * (hopefully disjoint) state types.
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
	 * This callback gets called before any value is extracted. It's a good place to load
	 * data, check the context and prepare for processing.
	 * 
	 * @param context
	 * @throws ThinklabException
	 */
	public abstract void preProcess(IContext context) throws ThinklabException;

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
