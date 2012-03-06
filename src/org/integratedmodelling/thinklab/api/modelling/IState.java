package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * A State is a stateful observation that represents its observable in its context using indirect 
 * information, i.e. "data". 
 * 
 * @author  Ferdinando
 */
public interface IState extends IObservation {
	
	/**
	 * Return the unmodified object at given offset. Most times it will be a duplicate of
	 * super.getValue(offset, parameters) but should not make any modification. If data are 
	 * unknown (nodata), return null.
	 * 
	 * @param previousOffset
	 * @return
	 */
	public Object getValue(int offset);

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
	public double[] getDataAsDoubles() throws ThinklabValidationException;

	/**
	 * get a single double for the given index.
	 */
	public double getDoubleValue(int index) throws ThinklabValidationException;
	
	/**
	 * Return the total number of states.
	 * @return
	 */
	public int getValueCount();

	/**
	 * Return the class of what our contents observe.
	 * @return
	 * @uml.property  name="observableClass"
	 * @uml.associationEnd  
	 */
	public IConcept getObservableClass();

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
