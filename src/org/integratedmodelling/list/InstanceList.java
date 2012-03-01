/**
 * InstanceList.java
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
package org.integratedmodelling.list;

import java.util.ArrayList;
import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IInstanceImplementation;
import org.integratedmodelling.thinklab.api.knowledge.IValue;
import org.integratedmodelling.thinklab.api.knowledge.factories.IKnowledgeManager;
import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * An object that wraps an instance definition list and provides access methods and 
 * associated object that mimic the IInstance methods. Allows inspecting an instance
 * structure from its list representation without having to create the list. OPAL 
 * provides a styled translator to XML.
 * 
 * @author Ferdinando Villa
 *
 */
public class InstanceList {

	Object[] array = null;
	IKnowledgeManager _km = null;
	
	public InstanceList(IList list, IKnowledgeManager km) {
		array = list.array();
		_km = km;
	}

	public String getLocalName() {

		String s = array[0].toString();
		String[] ss = s.split("#");
		
		return ss.length == 2 ? ss[1] : "";
	}

	public IList asList() {
		return PolyList.fromArray(array);
	}
	
	public IConcept getDirectType() {
		String s = array[0].toString();
		String[] ss = s.split("#");		
		return _km.getConcept(ss[0]);
	}

	public String getId() throws ThinklabException {
		String s = array[0].toString();
		String[] ss = s.split("#");		
		return ss.length > 1 ? ss[1] : null;
	}

	
	public String getLabel() {
		
		String ret = null;
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] instanceof IList && 
					((IList)array[i]).first().toString().equals("rdfs:label") ) {
				ret = ((PolyList)array[i]).second().toString();
				break;
			}	
		}
		return ret;
	}

	public IInstanceImplementation getImplementation() {

		IInstanceImplementation ret = null;
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] instanceof IList && 
					((IList)array[i]).first().toString().equals("#") ) {
				ret = (IInstanceImplementation) ((PolyList)array[i]).second();
				break;
			}	
		}
		return ret;
		
	}
	
	public String getDescription() {
		String ret = null;
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] instanceof IList && 
					((IList)array[i]).first().toString().equals("rdfs:comment") ) {
				ret = ((PolyList)array[i]).second().toString();
				break;
			}	
		}
		return ret;
	}

	public Collection<RelationshipList> getRelationships() {
		
		ArrayList<RelationshipList> ret = new ArrayList<RelationshipList>();
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] instanceof IList) {
				String s = ((IList)array[i]).first().toString();
				
				if (!(s.equals("rdsf:label") || 
					  s.equals("rdfs.comment"))) {
					ret.add(new RelationshipList((PolyList)array[i], _km));
				}
				
			}
		}
		return ret;
	}

	public boolean hasLiteralContent() {

		for (int i = 1; i < array.length; i++)
			if (!(array[i] instanceof IList))
				return true;
		return false;
	}
	
	public Object getLiteralContent() {

		for (int i = 1; i < array.length; i++)
			if (!(array[i] instanceof IList))
				return array[i];
		return null;

	}

	/**
	 * Return the target of the given represented relationship assuming it's a concept or specifies one.
	 * 
	 * @param relationship
	 * @return
	 * @throws ThinklabException
	 */
	public IConcept getTargetConcept(String relationship)  {

		IConcept ret = null;
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] instanceof IList) {
				String s = ((IList)array[i]).first().toString();
				
				if (s.equals(relationship)) {
					
					Object o = ((PolyList)array[i]).second();
					
					if (o instanceof IValue) {
						ret = ((IValue)o).getConcept();
					} else if (o instanceof IList) {
						/* instance specification */
						ret = resolveToConcept(((IList)o).first());
					} else {
						ret = resolveToConcept(o);
					}
				}
			}
		}
		return ret;
	}

	private IConcept resolveToConcept(Object o) {

		IConcept ret = null;

		if (o instanceof IConcept) {
			ret = (IConcept)o;
		} else {
			ret = _km.getConcept(o.toString());
		}
		
		return ret;
	}

	public Collection<RelationshipList> getRelationships(String property) throws ThinklabException {

		ArrayList<RelationshipList> ret = new ArrayList<RelationshipList>();
		
		for (RelationshipList rel : getRelationships()) {
			if (rel.property.is(property))
				ret.add(rel);
		}
		return ret;
	}

	public IValue getValue(String property) {
		// TODO Auto-generated method stub
		return null;
	}

}
