package org.integratedmodelling.thinklab.api.modelling.observation;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * A topological object can be compared with topological operators to another of a 
 * compatible class.
 * 
 * TODO implement remaining contract from SFS or other
 * 
 * @author Ferdinando Villa
 *
 */
public interface ITopologicallyComparable<T> {
	
	/**
	 * 
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract boolean contains(T o) throws ThinklabException;
	
	/**
	 * 
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract boolean overlaps(T o) throws ThinklabException;
	
	/**
	 * 
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract boolean intersects(T o) throws ThinklabException;

}
