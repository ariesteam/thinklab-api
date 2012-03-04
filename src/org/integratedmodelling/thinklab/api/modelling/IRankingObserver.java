package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.collections.Pair;

/**
 * Rankings are numerically ordered, linear quantification of a value that has no further
 * unit of measurement. The type returned by getStateType() will determine whether it's
 * a discrete or continuous ranking.
 *  
 * @author Ferd
 *
 */
public interface IRankingObserver extends IObserver {

	/**
	 * Get the range of the ranking. If no range is specified, return null. If only
	 * one boundary of the range is specified, return null for the unspecified one.
	 * 
	 * @return
	 */
	public Pair<Number, Number> getRange();
	
}
