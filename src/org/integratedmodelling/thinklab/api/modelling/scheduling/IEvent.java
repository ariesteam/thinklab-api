package org.integratedmodelling.thinklab.api.modelling.scheduling;

import org.integratedmodelling.thinklab.api.modelling.IScale;

/**
 * An IEvent results from advancing a schedule. IEvents are specific to a merged schedule and 
 * are be able to return what transitions, if any, have happened in each of the extents owned by the schedule 
 * relative to any IObservationContexts that were merged into it.
 * 
 * @author Ferd
 *
 */
public interface IEvent {

	public abstract ITransition getTransition(IScale context);
}
