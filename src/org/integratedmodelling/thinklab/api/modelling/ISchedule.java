package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * An ISchedule generates all context transitions implied by the set of IExtent seen by 
 * an ISubject, in the order determined by the nature of the extents and their mutual
 * arrangement. 
 * 
 * @author ferdinando.villa
 *
 */
public interface ISchedule {

	public static final long UNLIMITED = -1;
	
	/**
	 * Return all the states that describe topology extents. The extents should be
	 * ordered appropriately for execution.
	 * 
	 * @return
	 */
	public abstract List<IExtent> getExtents();

	/**
	 * Return the extent for a specific topology observable type, or null if not
	 * there.
	 * 
	 * @param observable
	 * @return
	 */
	public abstract IExtent getExtent(IConcept observable);

	/**
	 * True if all the extent states correspondent to the passed state index are
	 * defined in all dimensions (meaning there is a correspondent topology
	 * subdivision for all topologies). If this is false for any extent, states using this context will
	 * have a no-data value at that index.
	 * 
	 * TODO move to mapper
	 * 
	 * @param index index of extent, or ALL_EXTENTS for all of them.
	 * @return
	 */
	public abstract boolean isCovered(int index);
		
	/**
	 * Convenience method to get the temporal extent, if any, without having to know which
	 * specific view of time we have.
	 * 
	 * @return
	 */
	public IExtent getTime();

	/**
	 * Convenience method to get the spatial extent, if any, without having to know which
	 * specific view of space we have.
	 * 
	 * @return
	 */
	public IExtent getSpace();


	public abstract int hasEqualExtents(IContext second);
	
	/**
	 * Use the passed objects to cover some or all of our extents. Return the
	 * difference between the previous total coverage and the coverage after
	 * merging in the extents.
	 * 
	 * TODO move to mapper
	 * 
	 * @param context
	 * @return
	 */
	public abstract double cover(ITopologicallyComparable<?>[] schedule);
	
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
	 * 
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
