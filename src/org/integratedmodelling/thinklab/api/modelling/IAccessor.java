package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * Accessors give access to the raw values of observations. The IAccessor interface should not be implemented
 * directly but only through one of its subinterfaces. 
 * 
 * ISerialAccessors operate state-by-state in a context, being passed each state in the sequence determined by the context's internal 
 * topologies, or in "parallel" producing states in a one-shot manner. 
 * 
 * ITransformingAccessors will receive the fully computed
 * states of their dependencies over the whole context, and produce their states in one operation
 * instead of successive calls to getValue(). 
 * 
 * An accessor implementing IContextTransformingAccessor is expected to modify the topologies of the context,
 * therefore producing a completely new context that will no longer contain the original states except in
 * provenance records. An example would be spatial clustering producing different polygons from originally 
 * different others or grids.
 * 
 * Classes implementing IAccessor may also be able to handle data extraction from an unchecked datasource or
 * mediate the output of another accessor to convert to a specified value semantics. This ability is signaled
 * by implementing the additional interface IMediatingAccessor.
 * 
 * @author Ferdinando
 *
 */
public abstract interface IAccessor {	
	
	/**
	 * Return a type corresponding to the state. The first accessor will create the state if 
	 * necessary, but the observer should be able to know the type.
	 *  
	 * @return
	 */
	public abstract IConcept getStateType();
	
	
}