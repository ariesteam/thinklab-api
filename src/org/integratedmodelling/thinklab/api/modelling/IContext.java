//package org.integratedmodelling.thinklab.api.modelling;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.integratedmodelling.exceptions.ThinklabException;
//import org.integratedmodelling.thinklab.api.knowledge.IConcept;
//import org.integratedmodelling.thinklab.api.listeners.IListenable;
//
///**
// * Context are the result of observing an agent. As such, their observable is a concrete SemanticObject
// * and they can have other concepts observed in them to extend the agent's semantics with new knowledge.
// * 
// * Context must start with something (usually a spatial and/or temporal observation) and are bound by
// * the semantics of the observable in adding new observations. When the context is first used as the
// * base for a model session (by observing it), we make sure that all the functional properties of the
// * observable are bound to observations. We can later add more knowledge by observing other things
// * 'at' it, compatibly with the current semantics. 
// * 
// * Contexts are defined with the observe instruction that specifies a concept and a definition (often
// * just a name) for its instance.
// * 
// * @author ferdinando.villa
// */
//public interface IContext extends ITopology<IContext>, IModelObject, IListenable {
//
//	/**
//	 * Constant to be used in functions that take an extent index to extend the
//	 * result to the combined extents.
//	 */
//	public static final int ALL_EXTENTS = -1;
//	
//	/**
//	 * Return all the states that describe topology extents. The extents should be
//	 * ordered appropriately for execution.
//	 * 
//	 * @return
//	 */
//	public abstract List<IExtent> getExtents();
//
//	/**
//	 * The total number of states determined by the topologies in the context, i.e. the
//	 * size of the Cartesian product of the topologies.
//	 * 
//	 * @param concept
//	 * @return
//	 * @throws ThinklabException
//	 */
//	public abstract int getMultiplicity(IConcept concept)
//			throws ThinklabException;
//
//	/**
//	 * Return the extent for a specific topology observable type, or null if not
//	 * there.
//	 * 
//	 * @param observable
//	 * @return
//	 */
//	public abstract IExtent getExtent(IConcept observable);
//
//	/**
//	 * True if all the extent states correspondent to the passed state index are
//	 * defined in all dimensions (meaning there is a correspondent topology
//	 * subdivision for all topologies). If this is false for any extent, states using this context will
//	 * have a no-data value at that index.
//	 * 
//	 * @param index index of extent, or ALL_EXTENTS for all of them.
//	 * @return
//	 */
//	public abstract boolean isCovered(int index);
//	
//	/**
//	 * Contexts are hierarchically composable. This is what enables agent-based modeling
//	 * and many other things. The requirement is that the parent context contains the 
//	 * children completely, and that it has all the extent types of its children.
//	 * 
//	 * This one returns null for the top-level context that contains all the others.
//	 * 
//	 * @return
//	 */
//	public IContext getParentContext();
//
//	/**
//	 * Merge anything that is related to observation. If the passed object is an IExtent, the 
//	 * context should be recomputed and all observations mediated to the result. If it is a 
//	 * IState, it should be added to the context if compatible, mediating if necessary. If it
//	 * is another IContext, extents and states from it should be merged. If it is anything 
//	 * else, we should attempt to observe it in this context, and merge the results. 
//	 * 
//	 * @param observation
//	 */
//	public abstract void merge(Object object) throws ThinklabException;
//
//	
//	/**
//	 * Convenience method to get the temporal extent, if any, without having to know which
//	 * specific view of time we have.
//	 * 
//	 * @return
//	 */
//	public IExtent getTime();
//
//	/**
//	 * Convenience method to get the spatial extent, if any, without having to know which
//	 * specific view of space we have.
//	 * 
//	 * @return
//	 */
//	public IExtent getSpace();
//
//	/**
//	 * 
//	 * @return
//	 */
//	public abstract Collection<IState> getStates();
//
//	/**
//	 * Return a new context with the given extent collapsed to its total
//	 * extent (1 state only), thereby eliminating any multiplicity in the
//	 * distribution of states in that dimension. Each state should be 
//	 * adjusted to reflect that (IState has an aggregate()
//	 * function that will create the correspondent aggregated state.)
//	 * 
//	 * If the dimension is null, this function must aggregate all extents in their
//	 * sorted order, resulting in a one-state overall aggregate.
//	 * 
//	 * @param dimension
//	 * @return
//	 * @throws ThinklabException
//	 */
//	public abstract IContext collapse(IConcept dimension)
//			throws ThinklabException;
//
//	public abstract int hasEqualExtents(IContext second);
//	
//	/**
//	 * Use the passed objects to cover some or all of our extents. Return the
//	 * difference between the previous total coverage and the coverage after
//	 * merging in the extents.
//	 * 
//	 * @param context
//	 * @return
//	 */
//	public abstract double cover(ITopologicallyComparable<?>[] context);
//
//	/**
//	 * Get the total covered proportion in the passed extent, or in all extents (as a
//	 * product) if extent index is ALL_EXTENTS. This should be 0 unless cover() has
//	 * been called (one or more times) to provide covering extents.
//	 * 
//	 * @param extent
//	 * @return the extent as a ratio (0 to 1).
//	 */
//	public abstract double getCoverage(int extent);
//
//}
