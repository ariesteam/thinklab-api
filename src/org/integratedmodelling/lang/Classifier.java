/**
 * Copyright 2011 The ARIES Consortium (http://www.ariesonline.org) and
 * www.integratedmodelling.org. 

   This file is part of Thinklab.

   Thinklab is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published
   by the Free Software Foundation, either version 3 of the License,
   or (at your option) any later version.

   Thinklab is distributed in the hope that it will be useful, but
   WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Thinklab.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.integratedmodelling.lang;

import java.util.ArrayList;
import java.util.HashMap;

import org.integratedmodelling.collections.NumericInterval;
import org.integratedmodelling.exceptions.ThinklabRuntimeException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;
import org.integratedmodelling.thinklab.api.modelling.IClassifier;

/**
 * A classifier of objects for general use.
 */
public class Classifier implements IClassifier {

	ArrayList<Classifier> classifierMatches = null;
	Double numberMatch = null;
	NumericInterval intervalMatch = null;
	IConcept conceptMatch = null;
	String stringMatch = null;
	boolean negated = false;
	private IExpression expressionMatch = null;
	
	/*
	 * if true, this is an :otherwise classifier, that needs to be known
	 * by the classification
	 */
	private boolean catchAll = false;
	
	/* if true, this is a classifier specifically meant to reclassify nil/null;
	 * normally, nil does not reclassify unless there is one of these in a 
	 * classification.
	 */
	private boolean nullMatch = false;
	
	public Classifier() {
	}

	@Override
	public boolean isUniversal() {
		return catchAll;
	}
	
	/**
	 * TODO support negation for everything except catchall
	 */
	@Override
	public boolean classify(Object o) {

		if (catchAll && o != null && !(o instanceof Double && Double.isNaN((Double)o))) {
			return true;
		}

		if (o == null)
			return negated ?
					!nullMatch : nullMatch;
		
		if (numberMatch != null) {
			
			return negated?
					!numberMatch.equals(asNumber(o)) :
					numberMatch.equals(asNumber(o));
			
		} else if (classifierMatches != null) {
			
			for (Classifier cl : classifierMatches) {
				if (cl.classify(o))
					return true;
			}
			
		} else if (intervalMatch != null) {
			
			Double d = asNumber(o);
			if (d != null)
				return negated?
						!intervalMatch.contains(d) :
						intervalMatch.contains(d);
			
		} else if (conceptMatch != null) {

			return negated ?
					!asConcept(o).is(conceptMatch) :
					asConcept(o).is(conceptMatch);

		} else if (stringMatch != null) { 

			return negated ?
					!stringMatch.equals(o.toString()) :
					stringMatch.equals(o.toString());

		} else if (expressionMatch != null) {
		
			try {
				/*
				 * TODO find an elegant way to communicate external
				 * parameter maps, and set :self = o in it.
				 */
				HashMap<String, Object> parms = new HashMap<String, Object>();
				parms.put("self", o);
				return negated ?
						!(Boolean)expressionMatch.eval(parms) :
						(Boolean)expressionMatch.eval(parms);

			} catch (Exception e) {
				throw new ThinklabRuntimeException(e);
			}
		}
		
		return false;
	}

	private IConcept asConcept(Object o) {
		
		if (o instanceof IConcept)
			return (IConcept)o;
		
		/*
		 * TODO no provision for parsing from string
		 */
		
		return null;
	}

	private Double asNumber(Object o) {

		Double ret = null;
		if (o instanceof Integer) {
			ret = (double)((Integer)o);
		} else if (o instanceof Double) {
			ret = (Double)o;
		} else if (o instanceof Float) {
			ret = (double)((Float)o);
		} else if (o instanceof Long) {
			ret = (double)((Long)o);
		} 
		return ret;
	}
	
	public void addClassifier(Classifier c) {
		if (classifierMatches == null)
			classifierMatches = new ArrayList<Classifier>();
		classifierMatches.add(c);
	}

	public void setConcept(IConcept c) {
			conceptMatch = c;
	}

	public void setInterval(NumericInterval interval) {
		this.intervalMatch = interval;
	}

	public void setNumber(Object classifier) {
		numberMatch = asNumber(classifier);
	}
	
	public String toString() {
		String ret = null;
		if (classifierMatches != null) {
			ret = "mul:";
			for (Classifier c : classifierMatches) {
				ret += "[" + c + "]";
			}
		} else if (numberMatch != null) {
			ret = "num:" + numberMatch;
		} else if (intervalMatch != null) {
			ret = "int:" + intervalMatch;
		} else if (conceptMatch != null) {
			ret = "con:" + conceptMatch;
		} else if (stringMatch != null) {
			ret = "str:" + stringMatch;
		} else if (catchAll) {
			ret = "tru:true";
		} else if (nullMatch) {
			ret = "nil:true";
		}
		return ret;
	}

	public void setCatchAll() {
		this.catchAll = true;
	}

	public void setString(String classifier) {
		this.stringMatch = classifier;
	}

	public void setNil() {
		this.nullMatch = true;
	}

	@Override
	public boolean isInterval() {
		return intervalMatch != null;
	}

	public NumericInterval getInterval() {
		return intervalMatch;
	}

	@Override
	public boolean isNil() {
		return this.nullMatch;
	}

	public void setExpression(IExpression e) {
		this.expressionMatch = e;
	}
	
	public static Classifier NumberMatcher(Number n) {
		Classifier ret = new Classifier();
		ret.numberMatch = n.doubleValue();
		return ret;
	}
	
	public static Classifier RangeMatcher(NumericInterval interval) {
		Classifier ret = new Classifier();
		ret.intervalMatch = interval;
		return ret;
	}
	
	public static Classifier ConceptMatcher(IConcept concept) {
		Classifier ret = new Classifier();
		ret.conceptMatch = concept;
		return ret;
	}
	
	public static Classifier Multiple(Classifier ... classifiers) {		
		Classifier ret = new Classifier();
		for (Classifier c : classifiers)
			ret.addClassifier(c);
		return ret;
	}
	
	public static Classifier StringMatcher(String string) {
		Classifier ret = new Classifier();
		ret.stringMatch = string;
		return ret;
	}
	
	public static Classifier Universal() {
		Classifier ret = new Classifier();
		ret.catchAll = true;
		return ret;
	}
	
	public static Classifier NullMatcher() {
		Classifier ret = new Classifier();
		ret.nullMatch = true;
		return ret;
	}

	@Override
	public void negate() {
		negated = true;
	}
	
// may be useful (or not) but requires a knowledge manager for parsing concept names.
//	public void parse(String s) throws ThinklabException {
//		
//		String selector = s.substring(0,4);
//		String def = s.substring(4);
//		
//		if (selector.equals("num:")) {
//			number = Double.parseDouble(def);
//		} else if (selector.equals("int:")) {
//			interval = new NumericInterval(def);
//		} else if (selector.equals("con:")) {
//			concept = Thinklab.c(def);
//		} else if (selector.equals("mul:")) {
//			parseMultiple(def);
//		} else if (selector.equals("str:")) {
//			string = def;
//		} else if (selector.equals("tru:")) {
//			catchAll = true;
//		} else if (selector.equals("nil:")) {
//			isNil = true;
//		}
//	}
//	
//	private void parseMultiple(String def) throws ThinklabException {
//		
//		/*
//		 * first character must be a square bracket; read up until matching closing bracket
//		 */
//		if (def.charAt(0) != '[') {
//			throw new ThinklabValidationException("syntax error in multiple classifier: classifiers must appear in square brackets");
//		}
//		
//		int level = 0;
//		int len = def.length();
//
//		StringBuffer buf = new StringBuffer(len);
//		for (int i = 0; i < len; i++) {
//			char c = def.charAt(i);
//			if (c == '[') {
//				if (level > 0) {
//					buf.append(c);
//				}
//				level++;
//			} else if (c == ']') {
//				level--;
//				if (level == 0) {
//					addClassifier(new Classifier(buf.toString()));
//					buf = new StringBuffer(len);
//				} else {
//					buf.append(c);
//				}
//			} else {
//				buf.append(c);
//			}
//		}
//	}

}
