package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.listeners.IListenable;

/**
 * A Context represents the observed world during a modelling session. It
 * contains a set of observations with explicit states (State) that belong to
 * the same observation session. Contexts are populated with observations by
 * running models in them. A context is usually started by observing a topology
 * of reference (space and/or time), i.e. adding Extents to it. When a model is
 * run in a context, it creates States for that context and adds them to it. Any
 * requirement of the model that is already in the context is taken from it even
 * if there is a model for it, and all States in the same context have a
 * representation compatible with the set of Extents contained. The context has
 * specialized methods to operate on states and extents. The modeling language
 * defines contexts as named templates that build a Context for an observation
 * structure. Such structures are contexts "primed" with temporal/spatial
 * observations and/or global observations for parameters.
 * 
 * @author ferdinando.villa
 */
public interface IContext extends ITopology<IContext>, IModelObject, IListenable {

	/**
	 * Return all the states that describe topology extents.
	 * 
	 * @return
	 */
	public abstract Collection<IExtent> getExtents();

	/**
	 * 
	 * @param concept
	 * @return
	 * @throws ThinklabException
	 */
	public abstract int getMultiplicity(IConcept concept)
			throws ThinklabException;

	/**
	 * Return the extent for a specific topology observable, or null if not
	 * there.
	 * 
	 * @param observable
	 * @return
	 */
	public abstract IExtent getExtent(IConcept observable);

	/**
	 * True if all the extent states correspondent to the passed index are
	 * defined in all dimensions (meaning there is a correspondent topology
	 * subdivision). If this is false for any extent, states using this context will
	 * have a no-data value at that index.
	 * 
	 * @param index
	 * @return
	 */
	public abstract boolean isCovered(int index);

	/**
	 * Return the extent for a specific topology observable, or null if not
	 * there.
	 * 
	 * @param observable
	 * @return
	 */
	public abstract IState getState(IConcept observable);

	/**
	 * Merge in an observation: if there is already one for that observable, mediate it through
	 * the extents we have; otherwise add it in.
	 * 
	 * @param observation
	 */
	public abstract void merge(IObservation observation) throws ThinklabException;

	/**
	 * Merge all the observations in the passed context
	 * 
	 * @param observation
	 */
	public abstract void merge(IContext context) throws ThinklabException;

	
	/**
	 * @return
	 */
	public IExtent getTime();

	/**
	 * @return
	 */
	public IExtent getSpace();

	/**
	 * 
	 * @return
	 */
	public abstract Collection<IState> getStates();


	/**
	 * Return a new context with the given dimension collapsed to its total
	 * extent (1 state only), thereby eliminating any multiplicity in the
	 * distribution of states in that dimension. IState has an aggregate()
	 * function that will create the correspondent aggregated state.
	 * 
	 * If dimension is null, this function must aggregate all extents in their
	 * sorted order, resulting in a one-state overall aggregate.
	 * 
	 * @param dimension
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IContext collapse(IConcept dimension)
			throws ThinklabException;

	/**
	 * Return a context mapper that translates linear state coordinates to the
	 * equivalent ones for the context defined by the extents of the passed
	 * observation.
	 * 
	 * The passed observation must have been harmonized by the contextualizer
	 * already, i.e. its context must have the same multiplicity or multiplicity
	 * 1 along any common dimension and have no multiple \ dimensions than this
	 * doesn't have.
	 * 
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IContextMapper mapContext(IObservation observation)
			throws ThinklabException;

}
