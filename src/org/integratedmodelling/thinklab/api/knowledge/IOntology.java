/**
 * IOntology.java
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

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * Ontologies are not first-class objects in Thinklab. This interface isn't deprecated at the moment, but
 * it may go away any time. All retrieval of concepts should be done through INamespace.
 */
public interface IOntology  extends IResource {
    
	/**
	 * Iterate over all concepts
	 * @return an iterator over all the concepts contained in the ontology. 
	 */
	public abstract Collection<IConcept> getConcepts();

	/**
	 * Iterate over all properties
	 * @return an iterator over all the properties contained in the ontology. 
	 */
	public abstract Collection<IProperty> getProperties();

	/**
	 * Return a concept, or null if not found.
	 * @param ID the concept's ID
	 * @return the concept or null
	 */
	public abstract IConcept getConcept(String ID);

	/**
	 * Return a property, or null if not found.
	 * @param ID the property's ID
	 * @return the property or null
	 */
	public abstract IProperty getProperty(String ID);

	/**
	 */
	public String getURI();

	/**
	 * Write the ontology to the passed physical location.
	 * 
	 * @param uri
	 * @throws ThinklabException 
	 */
	public boolean write(String uri) throws ThinklabException;

	
	/**
	 * Define the ontology from a collection of axioms. Must work incrementally.
	 * @param axioms
	 * @throws ThinklabException
	 */
	public void define(Collection<IAxiom> axioms) throws ThinklabException;

	/**
	 * Return the number of (named, useful) concepts, hopefully quickly. 
	 * 
	 * @return
	 */
	public abstract int getConceptCount();

	/**
	 * Return the number of (named, useful) properties, hopefully quickly.
	 * 
	 * @return
	 */
	public abstract int getPropertyCount();
}