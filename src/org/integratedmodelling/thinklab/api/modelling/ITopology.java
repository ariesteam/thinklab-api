package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;

public interface ITopology<T> extends ITopologicallyComparable<T> {

	public static final long INFINITE = -1L;
	
	/**
	 * Return the total number of distinct subdivisions in this topology. INFINITE is
	 * an option when applicable.
	 * 
	 * @return
	 */
	public long getMultiplicity();
	
	/**
	 * Return a topology which represents the intersection of this with the passed
	 * one. Thinklab expects that the intersected extent can be index-remapped to
	 * a sub-extent of this by simple offsetting. This means that subdivisions need 
	 * to be "in phase" between the two extents.
	 * 
	 * @param myExtent
	 * @return
	 * @throws ThinklabException 
	 */
	public T intersection(T other) throws ThinklabException;
	
	/**
	 * Return a topology which represents the union of this with the passed
	 * one. As in intersection(), the union extent must be in phase with
	 * the original one.
	 * 
	 * @param myExtent
	 * @return
	 * @throws ThinklabException 
	 */
	public T union(T other) throws ThinklabException;
	
}
