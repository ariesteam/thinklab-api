/**
 * IConceptualizable.java
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
package org.integratedmodelling.thinklab.api.knowledge;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.lang.IList;


/**
 * A way to control conceptualization and definition of semantic objects that does not involve
 * annotation with @Concept and @Property.
 * 
 * Essentially IConceptualizable is the semantic version of IParseable.
 * 
 * @author Ferdinando Villa, Ecoinformatics Collaboratory, UVM
 *
 */
public interface IConceptualizable {
	
	/**
	 * Conceptualize to a list. 
	 * 
	 * @return
	 * @throws ThinklabException
	 */
	IList conceptualize() throws ThinklabException;
	
	/**
	 * A IConceptualizable must have an empty constructor and be capable of fully
	 * defining itself through a concept list of the kind returned by conceptualize().
	 * 
	 * @param conceptualization
	 * @throws ThinklabException
	 */
	void define(IList conceptualization) throws ThinklabException;
}
