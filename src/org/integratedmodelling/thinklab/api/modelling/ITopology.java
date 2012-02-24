package org.integratedmodelling.thinklab.api.modelling;

public interface ITopology<T> extends ITopologicallyComparable<T> {

	/**
	 * Return the total number of distinct subdivisions in this topology. 
	 * 
	 * @return
	 */
	public int getMultiplicity();
	
	/**
	 * Return a topology which represents the intersection of this with the passed
	 * one. Thinklab expects that the intersected extent can be index-remapped to
	 * a sub-extent of this by simple offsetting. This means that subdivisions need 
	 * to be "in phase" between the two extents.
	 * 
	 * @param myExtent
	 * @return
	 */
	public T intersection(T other);
	
	/**
	 * Return a topology which represents the union of this with the passed
	 * one. As in intersection(), the union extent must be in phase with
	 * the original one.
	 * 
	 * @param myExtent
	 * @return
	 */
	public T union(T other);
	
}
