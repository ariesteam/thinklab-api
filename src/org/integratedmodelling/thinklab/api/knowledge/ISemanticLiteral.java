/**
 * IValue.java
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



/**
 * These should be associated to datatype URIs using the @Literal annotation.
 * 
 * @author  Ferd
 * @deprecated to be substituted by SemanticObject which also substitutes IInstance.
 */
public interface ISemanticLiteral {

	/**
	 * Called just after an empty constructor by the annotation manager. Must
	 * be able to define everything from the literal it's being passed, which
	 * will be of the class defined in the @Literal annotation.
	 * 
	 * @param o
	 */
	public abstract void wrap(Object o);
	
	/**
	 * Return the concept expressed in the value.
	 * @return
	 */
	public abstract IConcept getConcept();
	
	public abstract boolean isNumber();

	public abstract boolean isText();

	public abstract boolean isBoolean();

//	public abstract boolean isObject();

	/**
	 * Check if value can be represented by a plain old data type literal. Note
	 * that the concept can be any concept derived from the base concepts
	 * installed for Text, Number, or Boolean.
	 * 
	 * @return true if POD type.
	 */
	public abstract boolean isPODType();
	
	/**
	 * Return the literal we're wrapping stripped of its semantics, with the most appropriate type.
	 * @return
	 */
	public abstract Object demote();

//	public abstract SemanticAnnotation asObject();

	public abstract boolean asBoolean();

	public abstract double asDouble();

	public abstract int asInteger();

	public abstract String asText();

	public abstract long asLong();

	//	public abstract boolean isLiteral();

//	public abstract boolean isClass();

	
}