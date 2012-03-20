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
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * A query's purpose is to generalize the semantic querying of objects. Queries operate on generic object,
 * which are annotated as necessary before use. 
 *  
 * @author Ferdinando Villa
 *
 */
public interface IQuery {
		
	/**
	 * Return a list representation of this query that can produce an identical one.
	 * 
	 * @return
	 */
	public abstract IList asList();

	/**
	 * Restrict to the queries defined by the passed queries. Note: can modify the query. Whether
	 * this is implemented functionally depends on the implementation.
	 * 
	 * E.g. 
	 *  
	 	   // select john (it actually returns a list - just pretend)
	       ISemanticObject john = 
	       		kbox.query(Query.select(PERSON).restrict(hasName, new Equality("John")));

    	   // select all of john's siblings
	       Query.select(PERSON).restrict(hasSibling, new Equality(john));

	       // select anyone who has at least one parent "like" john - same number of
	       // children and parents and whatever else the semantic of PERSON means.
	       Query.select(PERSON).restrict(hasParent,  new Conformance(john));
	       
	 * @param property the property to restrict
	 * @param query the queries to restrict it with. If no query is passed, the simple
	 *        existence of relationships of type property should be checked.
	 * @return
	 */
	public abstract IQuery restrict(IProperty property, IQuery ... query);
	
	/**
	 * Validate passed object for conditions expressed in the query. The object
	 * is conceptualized if necessary.
	 * 
	 * @param i a semantic object to validate.
	 * @return true if match is positive.
	 * @throws ThinklabException 
	 */
	public abstract boolean match(Object i) throws ThinklabException;

}
