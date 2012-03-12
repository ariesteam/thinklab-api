/**
 * IConcept.java
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
import org.integratedmodelling.thinklab.api.knowledge.query.IQuery;

/**
 * The main interface that a concept must support. Note that many of the methods require that some sort of reasoner is connected, and in Jena the choice of reasoner will change the results. This is not optional in the IMA: implementations that do not provide these functionalities as expected are considered incomplete, and the results of all ops on concepts should be the same for the same ontologies regardless of the implementation choices.
 * @author  Ferdinando Villa
 */
public interface IConcept extends IKnowledge {

    /**
     * Return a collection of all the direct parent classes.
     * @return
     */
	public abstract Collection<IConcept> getParents();

    /**
     * Return a collection of all direct and indirect parent classes. Should use a reasoner if
     * installed, and only follow transitive superclass relationships if not.
     * @return
     */
	public abstract Collection<IConcept> getSemanticClosure();
	
    /**
     * Return a collection of all direct subclasses.
     * @return all child concepts.
     */
	public abstract Collection<IConcept> getChildren();

	/**
	 * Return all properties that are  defined as having this in their domain
	 * subproperties.
	 * @return
	 */
	public abstract Collection<IProperty> getProperties();
	
	/**
	 * It includes all properties inherited by its superclasses, or having undefined domains
	 * @return
	 */
	public abstract Collection<IProperty> getAllProperties();
	
	/**
	 * Return the range of the passed property in the context of this concept, considering
	 * restrictions.
	 * @return
	 * @throws ThinklabException 
	 */
	public abstract Collection<IConcept> getPropertyRange(IProperty property) throws ThinklabException;

//    /**
//	 * Return a constraint with all the restrictions defined for this type and its supertypes. The type in the constraint is the concept itself. The minimal restriction selects the concept itself, so a restriction is never null.
//	 * @return
//	 * @throws ThinklabException
//	 * @uml.property  name="restrictions"
//	 * @uml.associationEnd  
//	 */
//    public abstract IQuery getRestrictions() throws ThinklabException;

    /**
     * The notion of an abstract concept is important where we create instances that have implementations. 
     * @return true if concept is abstract (no instances can be created).
     */
    public abstract boolean isAbstract();

    /**
	 * Return the (only) parent class, or throw an exception if there's more than one parent.
	 * @uml.property  name="parent"
	 * @uml.associationEnd  
	 */
	public abstract IConcept getParent() throws ThinklabException;


	/** get the number of properties for this type */
	public abstract int getPropertiesCount(String property);

	/**
	 * Get the minimum cardinality of the passed property in the domain of this
	 * concept.
	 * @param property
	 */
	public abstract int getMinCardinality(IProperty property);

	/**
	 * Get the maximum cardinality of the passed property in the domain of this
	 * concept.
	 * @param property
	 */
	public abstract int getMaxCardinality(IProperty property);

	/**
	 * 
	 * @return
	 */
	public abstract Collection<IProperty> getAnnotationProperties();

	public abstract IConcept getLeastGeneralCommonConcept(IConcept c);
	
	/**
	 * @uml.property  name="definition"
	 * @uml.associationEnd  
	 */
	public abstract IQuery getDefinition();
	
	
}