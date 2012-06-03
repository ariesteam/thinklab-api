package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * A State is a stateful observation that represents its observable in its context using indirect 
 * information, i.e. "data". It extends ISerialAccessor and may be used as such when a model is
 * contextualized in a context that already contains a state for a required observable.
 * 
 * @author  Ferdinando
 */
public interface IState extends IObservation, ISerialAccessor {

	/**
	 * This will return an array of the appropriate type without any further allocation. It's terrifying to use in Java, but just fine for dynamically typed embedded languages. 
	 * @return
	 * @uml.property  name="rawData"
	 */
	public Object getRawData();

	/**
	 * Should endeavor to return doubles as long as it's not entirely meaningless. Many 
	 * procedures will require doubles and the more are supported, the more can be done
	 * with all scientific plugins.
	 * 
	 * @return
	 */
	public double[] getDataAsDoubles() throws ThinklabException;

	/**
	 * get a single double for the given index.
	 */
	public double getDoubleValue(int index) throws ThinklabException;
	
	/**
	 * Return the total number of states.
	 * @return
	 */
	public int getValueCount();

	/**
	 * Return a state with the given context dimension collapsed to one, and
	 * the data appropriately aggregated. Return self if the dimension is not
	 * in the context and throw an exception if data along that dimension cannot
	 * be aggregated. It's expected to handle the metadata appropriately, e.g. 
	 * modify the units if necessary.
	 * 
	 * @param concept
	 * @return
	 * @throws ThinklabException
	 */
	public IState aggregate(IConcept concept) throws ThinklabException;

	/**
	 * True if the state has more than one value over any spatial 
	 * dimension. 
	 * 
	 * @return
	 */
	public abstract boolean isSpatiallyDistributed();
	
	/**
	 * True if the state has more than one value over any temporal
	 * dimension.
	 * 
	 * @return
	 */
	public abstract boolean isTemporallyDistributed();
	
}
