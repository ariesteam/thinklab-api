package org.integratedmodelling.thinklab.api.modelling.scheduling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.modelling.IScale;


/**
 * An ISchedule generates a sequence of transition events implied by the set of IExtent seen by 
 * any ISubject under its jurisdiction, in the order determined by the nature of the extents and their mutual
 * arrangement. ISubjects are notified to the schedule through their IObservationContext.
 * 
 * Schedules must be dynamic, meaning that extents can be added or removed dynamically.
 * 
 * @author ferdinando.villa
 *
 */
public interface ISchedule extends Iterable<IEvent> {
	
	/**
	 * Merge a scale into this schedule. 
	 * 
	 * @param context
	 * @throws ThinklabException
	 */
	public abstract void merge(ISchedule schedule) throws ThinklabException;
	
	/**
	 * Remove a previously merge scale from this schedule.
	 * 
	 * @param context
	 */
	public abstract void remove(IScale context);
	
}
