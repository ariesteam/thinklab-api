package org.integratedmodelling.thinklab.api.modelling;

/**
 * An extent is capable of returning one of these on request. Used
 * to build
 * @author ferdinando.villa
 *
 */
public interface ISchedule {

	/**
	 * Merge in another schedule and return an object capable of
	 * telling us whether the next() state for this schedule has
	 * fully covered a state for the merged one, and if not how much
	 * of a full state is covered.
	 * 
	 * @param externalSchedule
	 * @return
	 */
	abstract public IScheduleMapper merge(ISchedule externalSchedule);

	/**
	 * Return the ID of the next state for the merged schedule or -1 if
	 * there is no next;
	 * 
	 * @return
	 */
	abstract long next();

	/**
	 * Return the ID of the next state for the merged schedule or -1 if
	 * there is no next;
	 * 
	 * @return
	 */
	abstract long previous();
	
	/**
	 * get the total number of events in this schedule. Return UNLIMITED if
	 * unlimited - this can only happen as the consequence of ONE unlimited
	 * schedules in a group.
	 * @return
	 */
	abstract long getEventCount();
	
}
