/**
 * IQuery.java
 * ----------------------------------------------------------------------------------
 * 
 * Copyright (C) 2008 www.integratedmodelling.org
 * Created: Jan 17, 2008
 *
 * ----------------------------------------------------------------------------------
 * This file is part of Thinklab.
 * 
 * Thinklab is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Thinklab is distributed in the hope that it will be useful,
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
 * @author    Ioannis N. Athanasiadis (ioannis@athanasiadis.info)
 * @date      Jan 17, 2008
 * @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v3
 * @link      http://www.integratedmodelling.org
 **/
package org.integratedmodelling.thinklab.api.knowledge.query;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.lang.LogicalConnector;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.lang.IParseable;

/**
 * A query's purpose is to generalize the semantic querying of objects. Queries operate on generic object,
 * which are annotated as necessary before use. 
 *  
 * @author Ferdinando Villa
 *
 */
public interface IQuery extends IParseable {
	
	/**
	 * Return true if the query is empty, meaning that it will select everything that's queriable. 
	 * 
	 * @return
	 */
	public abstract boolean isEmpty();

	/**
	 * Return a new query which is the logical connection of self with the passed one.
	 * 
	 * @param constraint
	 * @param intersection
	 * @return
	 */
	public abstract IQuery merge(IQuery constraint, LogicalConnector intersection) throws ThinklabException ;
	
	/**
	 * Return a list representation of this query that can produce an identical one.
	 * 
	 * @return
	 */
	public abstract IList asList();

	/**
	 * Restrict to the queries defined by the passed semantic operators. 
	 * 
	 * E.g. query.restrict(hasName,    new Equals("John"))
	 *      query.restrict(hasSibling, new Query(Person).restrict(hasName, new Equals("John"))
	 *      query.restrict(hasParent,  new Conforms(john))
	 *      
	 * @param property
	 * @param operator
	 * @return
	 */
	public abstract IQuery restrict(IProperty property, IOperator ... operator);
	
	/**
	 * Restrict the current constraint by properly merging in the passed connections using the passed
	 * mode. Don't even think about passing anything but AND and OR, although no check is made.
	 * @param connector LogicalConnector.INTERSECTION or UNION. Nothing else please.
	 * @param restrictions as many new restrictions as you want. NULLs are accepted and will be ignored for
	 *        convenience.
	 * @returns this, not a new constraint; it's done only to enable shorter idioms when creating
	 *    a constraint like new Constraint(..).restrict(...);
	 */
	public abstract IQuery restrict(LogicalConnector connector, IProperty property, IOperator ... restrictions);

//	/**
//	 * Return the restriction objects as a single restriction
//	 * @return
//	 */
//	public abstract getRestrictions();

	/**
	 * Validate passed object for conditions expressed in constraint. Object must have a
	 * semantic peer, i.e. be an IInstance, InstanceList, IConceptualizable or have an
	 * associated SemanticAdapter.
	 * 
	 * @param i a concept or instance to validate.
	 * @return true if match is positive.
	 * @throws ThinklabException 
	 */
	public abstract boolean match(Object i) throws ThinklabException;

}
