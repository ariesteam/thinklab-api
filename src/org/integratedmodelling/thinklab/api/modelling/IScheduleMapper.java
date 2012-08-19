package org.integratedmodelling.thinklab.api.modelling;

/**
 * Remembers the relationship between a target schedule and a global one
 * that the target has been merged with, and tells us whether a state for
 * the global schedule covered a new state of the target and by how much.
 * 
 * @author ferdinando.villa
 *
 */
public interface IScheduleMapper {

	/**
	 * Advance the target schedule to the passed state for the global
	 * one, and return 1 if a new state was covered, 0 if nothing changed
	 * or there is no coverage at that point, and 0 < n < 1 if there was
	 * partial coverage.
	 * 
	 * @param state
	 * @return
	 */
	double advanceAndGetCoverage(long state);
	
}
