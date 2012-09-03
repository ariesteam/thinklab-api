package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.listeners.IListenable;

/**
 * An ObservationContext is an immutable list of the IExtents seen by a ISubject, which can be
 * compared with others topologically. Contexts generate events that are merged in ISchedules
 * for contextualization.
 * 
 * A IScale must be properly hashable and comparable in order to be added/removed to/from ISchedule 
 * and used to understand the transitions caused by IEvents.
 * 
 * @author Ferd
 * TODO change back to IContext after cleanup is done.
 */
public interface IScale extends Iterable<IExtent>, ITopology<IExtent>, IListenable {

	public final static long INFINITE = -1L;

	public IExtent getSpace();

	public IExtent getTime();
}
