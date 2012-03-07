/**
 * RelationshipList.java
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

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabRuntimeException;
import org.integratedmodelling.list.PolyList;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticLiteral;
import org.integratedmodelling.thinklab.api.knowledge.factories.IKnowledgeManager;

public class RelationshipAnnotation {

	IProperty property;
	ISemanticLiteral value;
	SemanticAnnotation object;
	IKnowledgeManager _km;
	
	public RelationshipAnnotation(PolyList polylist, IKnowledgeManager km)  {
		
		_km = km;
		Object o = polylist.first();
		if (o instanceof IProperty)
			property = (IProperty)o;
		else
			property = _km.getProperty(o.toString());
		
		try {
			value = _km.annotateLiteral(polylist.second());
		} catch (ThinklabException e) {
			throw new ThinklabRuntimeException(e);
		}
		
	}

	public SemanticAnnotation getObject() {
		return object;
	}
	
	public IProperty getProperty() {
		return property;
	}

	public boolean isObject() {
		return object != null;
	}

	public boolean isLiteral() {
		return value != null;
	}

	public ISemanticLiteral getValue() {
		return value;
	}

	public boolean isClassification() {
		return property.isClassification();
	}
	
	public IConcept getConcept() {
		return value.getConcept();
	}
	

}
