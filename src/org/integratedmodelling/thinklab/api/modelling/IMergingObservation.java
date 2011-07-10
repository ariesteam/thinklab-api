package org.integratedmodelling.thinklab.api.modelling;

/**
 * Tag interface to distinguish observations that are capable of merging the
 * states of their dependencies.
 * 
 * @author ferdinando.villa
 *
 */
public interface IMergingObservation extends IIndirectObservation {

	/**
	 * Return a collection of all observations that may contribute to define the 
	 * states of this one, accumulating states over the union of their extents.
	 * 
	 * @return
	 */
	public abstract IObservation[] getContingencies();
}
