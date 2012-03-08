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
package org.integratedmodelling.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.list.PolyList;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IKnowledge;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;
import org.integratedmodelling.thinklab.api.knowledge.factories.IKnowledgeManager;
import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * The semantics for a ISemanticObject. It contains all the information to
 * create a OWL individual for the object, plus the Thinklab extensions to
 * support class objects and extended literals. A relationship is also
 * Semantics, so all relationships can be obtained consistently.
 * 
 * Semantics can be built for an arbitrary Object by the knowledge manager as
 * long as it's been properly annotated (see @Literal, @Concept, and @Property)
 * and/or it implements IConceptualizable.
 * 
 * Semantics is implemented internally as a compact IList, so it can be printed
 * nicely and converted to XML, JSON or any host language data structures.
 * 
 * @author Ferdinando Villa
 */
public class Semantics {

	/*
	 * the semantic predicate is either a concept or a property.
	 */
	IKnowledge predicate;

	/*
	 * only filled if the predicate is a data property
	 */
	ISemanticObject target;
	/*
	 * filled if the predicate is an object property
	 */
	Semantics       targetSemantics;

	/*
	 * only filled if the predicate is a concept
	 */
	ArrayList<Semantics> relationships;

	/*
	 * the original representation
	 */
	IList list;

	/*
	 * we need this
	 */
	IKnowledgeManager _km = null;

	/**
	 * This will try to convert the passed targets according to what the
	 * predicates expect. So for example, concept and properties can be passed
	 * as strings, and string literals can be passed for data properties that
	 * expect complex objects, which will be parsed from the corresponding
	 * literals. Obvious conversions are also supported through
	 * parse(object.toString()). This way the annotation should support lists
	 * that come from textual input.
	 * 
	 * @param instanceList
	 * @param km
	 */
	public Semantics(IList instanceList, IKnowledgeManager km) {
		_km = km;
		list = instanceList;
		/*
		 * expects a concept. The constructor with the property is private
		 */
	}

	private Semantics(IProperty property, Object object) {
		
	}

	private void parse(IList iList) {
		// TODO Auto-generated method stub
		
	}


	public int getRelationshipsCount() {
		return getRelationships().size();
	}

	public int getRelationshipsCount(IProperty property)
			throws ThinklabException {
		return getRelationships(property).size();
	}

	public IList asList() {
		return list;
	}
	
	@Override
	public String toString() {
		return list.toString();
	}

	public IConcept getConcept() {
		return (IConcept)predicate;
	}

	public IProperty getProperty() {
		return (IProperty)predicate;
	}

	public Collection<Semantics> getRelationships() {

		ArrayList<Semantics> ret = new ArrayList<Semantics>();
		if (relationships == null) {
			parse(list);
		}
		ret.addAll(relationships);

		return ret;
	}

	// /**
	// * Return the target of the given represented relationship assuming it's a
	// concept or specifies one.
	// *
	// * @param relationship
	// * @return
	// * @throws ThinklabException
	// */
	// public IConcept getTargetConcept(String relationship) {
	//
	// IConcept ret = null;
	//
	// for (int i = 1; i < array.length; i++) {
	// if (array[i] instanceof IList) {
	// String s = ((IList)array[i]).first().toString();
	//
	// if (s.equals(relationship)) {
	//
	// Object o = ((PolyList)array[i]).second();
	//
	// if (o instanceof ISemanticLiteral) {
	// ret = ((ISemanticLiteral)o).getConcept();
	// } else if (o instanceof IList) {
	// /* instance specification */
	// ret = resolveToConcept(((IList)o).first());
	// } else {
	// ret = resolveToConcept(o);
	// }
	// }
	// }
	// }
	// return ret;
	// }
	//
	// private IConcept resolveToConcept(Object o) {
	//
	// IConcept ret = null;
	//
	// if (o instanceof IConcept) {
	// ret = (IConcept)o;
	// } else {
	// ret = _km.getConcept(o.toString());
	// }
	//
	// return ret;
	// }

	public Collection<Semantics> getRelationships(IProperty property)
			throws ThinklabException {

		ArrayList<Semantics> ret = new ArrayList<Semantics>();
		for (Semantics rel : getRelationships()) {
			if (rel.predicate.is(property))
				ret.add(rel);
		}
		return ret;
	}

	public Semantics getValue(IProperty property) throws ThinklabException {
		Collection<Semantics> r = getRelationships(property);
		return r.size() > 0 ? r.iterator().next().getTargetSemantics() : null;
	}

	private Semantics getTargetSemantics() {

		if (target != null) 
			return target.getSemantics();
		return targetSemantics;
	}

	public List<Semantics> getValues(IProperty property)
			throws ThinklabException {
		List<Semantics> ret = new ArrayList<Semantics>();
		for (Semantics r : getRelationships(property)) {
			ret.add(r.getTargetSemantics());
		}
		return ret;
	}

}
