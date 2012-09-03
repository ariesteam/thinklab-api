package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * Returned by functions such as "shape" or "space" to allow incremental definition
 * of mediated extents using different functions for the same domain. E.g. a 'shape'
 * result can be merged with a 'grid(resolution='100m') to create a constrained 
 * grid.
 * 
 * @author ferdinando.villa
 *
 */
public interface IExtentDefinition {
	
	/**
	 * Return the root domain concept for the extent that will be defined, so that
	 * independent definitions for the same domain can be merged together.
	 * 
	 * @return
	 */
	public abstract IConcept getDomainConcept();
	
	/**
	 * 
	 * @param extentDefinition
	 */
	public abstract void merge(IExtentDefinition extentDefinition);
	
	/**
	 * 
	 * @return
	 */
	public abstract IExtent getExtent();
	
	/**
	 * 
	 * @return
	 */
	public abstract boolean isResolved();

}
