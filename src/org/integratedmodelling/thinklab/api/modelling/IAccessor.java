package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * Accessors do the actual work of modelling by producing resolved observations. As a default they operate 
 * state-by-state in a context, being passed each state in the sequence determined by the context's internal 
 * topologies. 
 * 
 * By using the interfaces ITransformingAccessor, the accessor will be assumed to need the fully computed
 * states of their dependencies over the whole context, and to be able to produce their states in one operation
 * instead of successive calls to getValue().
 * 
 * An accessor implementing IContextTransformingAccessor is expected to modify the topologies of the context,
 * therefore producing a completely new context that will no longer contain the original states except in
 * provenance records. An example would be spatial clustering producing different polygons from originally 
 * different others or grids.
 * 
 * Classes implementing IAccessor must be able to handle data provision from either a datasource (extraction) or
 * another accessor (mediation). As a special case, some accessor may have neither and just produce predefined
 * values or compute functions of the current context.
 * 
 * @author Ferdinando
 *
 */
public abstract interface IAccessor {

	/**
	 * Accessors of indirect observations are responsible for creating the result observation
	 * when they are contextualized.
	 * 
	 * @param size the number of states that the result observation will contain.
	 * @param context  the context that the state will represent. Its multiplicity equals the
	 *        passed size.
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IState createState(int size, IContext context) throws ThinklabException;
	
}