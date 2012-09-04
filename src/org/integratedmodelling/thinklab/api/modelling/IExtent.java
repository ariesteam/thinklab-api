/**
 * IExtent.java
 * ----------------------------------------------------------------------------------
 * 
 * Copyright (C) 2008 www.integratedmodelling.org
 * Created: Jan 17, 2008
 *
 * ----------------------------------------------------------------------------------
 * This file is part of ThinklabCoreSciencePlugin.
 * 
 * ThinklabCoreSciencePlugin is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * ThinklabCoreSciencePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the software; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * ----------------------------------------------------------------------------------
 * 
 * @copyright 2008 www.integratedmodelling.org
 * @author    Ferdinando Villa (fvilla@uvm.edu)
 * @date      Jan 17, 2008
 * @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v3
 * @link      http://www.integratedmodelling.org
 **/
package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;

/**
 * An Extent describes the topology of the observable
 * it's linked to. Conceptual models are capable of producing unions or intersections
 * of extents.
 * 
 * Extents must be conceptualizable and the result of conceptualizing
 * them must be an observation describing the extent. They are also expected
 * to implement equals() and hash() in a fast way (e.g. by using signatures).
 * 
 * @author Ferdinando Villa
 *
 */
public abstract interface IExtent extends IState, ITopology<IExtent> {

	/**
	 * Return the main concept for the topological class represented by this extent. It should
	 * be the same concept for all the different kinds of extents representing the same domain,
	 * i.e. geospace:Space, which should be an ancestor to the observable of this
	 * extent. It will be used to ensure that no two extents of the same domain concept
	 * appear in a context. The context implementation is expected to try to merge extents
	 * that share the same domain concept even if their observables are not the same.
	 * 
	 * @return
	 */
	public abstract IConcept getDomainConcept();
	
	/**
	 * Return the property that should link this extent to a ISubject. It should be
	 * a data property.
	 * 
	 * @return
	 */
	public abstract IProperty getDomainProperty();

	
	/**
	 * Collapse the multiplicity and return the extent that represents
	 * the full extent of our topology in one single state. This extent may
	 * not necessarily be of the same class.
	 * 
	 * @return a new extent with getValueCount() == 1.
	 */
	public IExtent collapse();
	
	/**
	 * Return the n-th state of the ordered topology as a new extent with one
	 * state.
	 * 
	 * @param granule 
	 * @return a new extent with getValueCount() == 1.
	 */
	public IExtent getExtent(int stateIndex);
	
	/**
	 * True if the i-th state of the topology correspond to a concrete subdivision where
	 * observations can be made. Determines the status of "data" vs. "no-data"
	 * for the state of an observation defined over this extent.
	 * 
	 * @param granule
	 * @return whether there is an observable world at the given location.
	 */
	public abstract boolean isCovered(int stateIndex);

	/**
	 * Return a list of location references to use in expressions and
	 * their relative offsets in the dimension described by this extent.
	 * For example, a time grid extent will return "previous, -1" so that
	 * the previous state can be referenced in expressions (with syntax
	 * dependent on the language: e.g. in the Clojure accessor, for state
	 * "altitude", the var will be :altitude/previous). Space grids should
	 * return things like n,s,e,w, etc. with offsets determined by the
	 * specific configuration.
	 * 
	 * The list of locators depends on the specific location, so the overall
	 * index in the dimension is passed.
	 * 
	 * If no locators can be defined, a null should be returned.
	 * @return
	 */
	public abstract Collection<Pair<String,Integer>> getStateLocators(int index);

	/**
	 * Should return whether the coverage of the domain is discontinuous to the
	 * point of breaking the internal rules of the represented topologies. E.g.,
	 * if we represent continuous uninterrupted space, discontinuities will break
	 * neighborhood relationships that models may to count on. This may come
	 * as a result of intersecting partial extents. If discontinuity is not an
	 * issue for this domain, just return false.
	 * 
	 * @return
	 */
	public boolean isDiscontinuous() throws ThinklabException;

	/**
	 * Return an extent that is capable of representing the passed one through
	 * the "lens" of our semantics. Return null if the operation is legal but
	 * it results in no context, throw an exception if we don't know what to
	 * do with the passed context.
	 * 
	 * @param extent
	 * @return
	 * @throws ThinklabException
	 */
	public IExtent force(IExtent extent) throws ThinklabException;
	
	/**
	 * Pass a topologically comparable object and return how much of this
	 * extent it covers. If it's not of a compatible type, do not raise errors
	 * and return 0.
	 *  
	 * @param obj
	 * @return the coverage proportion as a double [0..1]
	 */
	public double getCoverage(ITopologicallyComparable<?> obj);


}