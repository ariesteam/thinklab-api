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
package org.integratedmodelling.thinklab.api.modelling.observation;

import java.util.Collection;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.lang.IOperator;
import org.integratedmodelling.lang.PhysicalNature;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.query.IQuery;

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
public abstract interface IExtent extends IState, ITopologicallyComparable {

	/**
	 * One of these is set into AggregationParameters to properly return the
	 * aggregation multiplier for each topology granule.
	 * 
	 * @author ferdinando.villa
	 *
	 */
	public static interface Aggregator {
		public abstract double getAggregationFactor(int granule);
	}
	
	/**
	 * Returned by getAggregationParameters
	 * @author ferdinando.villa
	 *
	 */
	public static interface AggregationParameters {
		
		public Aggregator     aggregator = null;
		public IUnit          aggregatedUnit = null;
		public PhysicalNature aggregatedNature = null;
		public String         aggregationOperator = IOperator.AVG;
		public String         uncertaintyOperator = IOperator.CV; 
	}
	
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
	public IExtent getExtent(int granule);
	
	/**
	 * True if the i-th granule correspond to a concrete subdivision where
	 * things can be observed. Determines the status of data or no-data
	 * for any state defined over this extent.
	 * 
	 * @param granule
	 * @return whether there is an observable world at the given location.
	 */
	public abstract boolean isCovered(int granule);

	/**
	 * Return a semantic query that will match observations that are in the passed
	 * relationship with this extent. 
	 */
	public abstract IQuery getConstraint(IOperator operator) throws ThinklabException;

	/**
	 * Return the transformation, if any, that will be necessary to operate on a 
	 * datasource that conforms to us so that it matches the passed extent. If 
	 * no transformation is necessary, return an identity transformation.
	 * 
	 * @param mainObservable the observable for the main observation that owns the extent
	 * 		  (what the states mean)
	 * @param extent the extent we must adapt the datasource to
	 * @return a transformation to be passed to the datasource
	 * @throws ThinklabException 
	 */
	public IDataSource.Transformation getDatasourceTransformation(
			IConcept mainObservable, IExtent extent) throws ThinklabException;

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
	 * Return an extent which represents the intersection of this with the passed
	 * one. NOTE: it is expected that the intersected extent can be index-remapped to
	 * a sub-extent of this. Meaning that subdivisions etc. need to be in sync.
	 * 
	 * @param myExtent
	 * @return
	 */
	public IExtent intersection(IExtent extent) throws ThinklabException;

	/**
	 * Return an extent which represents the union of this with the passed
	 * one. Same note as intersect(): the union extent must be usable together with
	 * this through an index remap operation.
	 * 
	 * @param myExtent
	 * @return
	 */
	public IExtent union(IExtent extent) throws ThinklabException;

	/**
	 * Return an extent that is capable of representing the passed one 
	 * exactly. If the passed one is of the same class, it can just return
	 * the passed one, but it's provided to give the extent a chance of 
	 * adjustments or of raising errors when complex matches are done. 
	 * 
	 * @param extent
	 * @return
	 * @throws ThinklabException
	 */
	public IExtent force(IExtent extent) throws ThinklabException;

	/**
	 * Return a descriptor of how aggregation should be performed in this
	 * extent for a value of the passed type. If a unit is associated, it
	 * must be capable of creating the unit of the aggregated concept, which
	 * will eliminate the dimension we represent if the concept is an
	 * extensive one.
	 * 
	 * @param concept
	 * @param unit
	 * @return
	 * @throws ThinklabException 
	 */
	public abstract AggregationParameters getAggregationParameters(IConcept concept, IUnit unit) throws ThinklabException;
	
}