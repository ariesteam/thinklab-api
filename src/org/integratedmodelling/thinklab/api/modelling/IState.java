package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticLiteral;
import org.integratedmodelling.thinklab.api.lang.IMetadataHolder;

/**
 * An IState is the result of observing a quality. IStates only exists in the context of an ISubject
 * and are the target of relationships defined by data properties. As such an IState classifies as
 * a literal semantic object.
 * 
 * Because ISubjects are physical objects and may exist in time/space or other abstract regions,
 * the IState is a complex literal that has as many states as the cartesian product of all the
 * IExtents owned by the ISubject that contains it. The demote() operation will therefore return
 * collections or atomic objects according to the extents adopted. Specialized access methods
 * return the event index of the ISchedule that represents the ISubject's view of the extents.
 * 
 * IState extends ISerialAccessor so that it can be used as such when observing something
 * in the context of a ISubject that already contains the IState for a required observable.
 * 
 * @author  Ferdinando
 */
public interface IState extends ISemanticLiteral<Object>, IStateAccessor, IMetadataHolder {

	/**
	 * Should endeavor to return doubles as long as it's not meaningless. Many 
	 * procedures will require doubles and the more are supported, the more can be done
	 * in processing and visualization plugins.
	 * 
	 * @return
	 */
	public double[] getDataAsDoubles() throws ThinklabException;

	/**
	 * get a single double for the given index.
	 */
	public double getDoubleValue(int index) throws ThinklabException;
	
	/**
	 * Return the total number of values determined by the extents owned by
	 * the owning ISubject.
	 * 
	 * @return
	 */
	public int getValueCount();

	/**
	 * True if the owning ISubject has an observation of space with more than
	 * one state value. 
	 * 
	 * @return
	 */
	public abstract boolean isSpatiallyDistributed();
	
	/**
	 * True if the owning ISubject has an observation of time with more than
	 * one state value. 
	 * 
	 * @return
	 */
	public abstract boolean isTemporallyDistributed();
	
	/**
	 * States are created by observers and will store them to provide a
	 * link to the observation semantics.
	 * 
	 * @return
	 */
	public abstract IObserver getObserver();
	
}
