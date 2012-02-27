package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

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

	public static final String MEDIATED_KEY = "this";
	
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
	
	/**
	 * This method offers access to the current state of any dependency declared for the
	 * observer that created it. If the observer is a mediator, the value to mediate is
	 * indexed by the keyword MEDIATED_KEY.
	 * 
	 * @param key
	 * @return the value in the current context. It can ALWAYS be null so that must be
	 * 	       handled gracefully. According to the type of accessor, the value may be
	 * 	       a single object (ISerialAccessor) or a collection.
	 * 
	 * @throws ThinklabException
	 */
	public abstract Object get(String key) throws ThinklabException;
	
	/**
	 * This method is called once per dependency before any values are extracted, passing
	 * the key that will be available for get() when values are extracted. If MEDIATED_KEY 
	 * is passed, the accessor is a mediator.
	 * 
	 * @param key the formal name of the parameter that will be passed to the 
	 * @param concept the concept incarnated by the value, whose meaning changes
	 *        from case to case.
	 */
	public abstract void notifyDependencyKey(String key, IConcept concept);
}