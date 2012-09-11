package org.integratedmodelling.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.integratedmodelling.thinklab.api.knowledge.IAxiom;

/**
 * Just a holder for axiom information. Because axioms are basically just syntax, we provide an
 * implementation here so you don't need to.
 * 
 * @author Ferd
 *
 */
public class Axiom implements IAxiom {

	private String _type;
	private Object[] _args;

	//
	// Public static creation members
	//
	
	static public Axiom ClassAssertion(String conceptId) {
		return new Axiom(CLASS_ASSERTION, conceptId);
	}
	
	static public Axiom AnnotationAssertion(String targetConcept, String annotationProperty, String value) {
		return new Axiom(ANNOTATION_ASSERTION, targetConcept, annotationProperty, value);
	}
	
	static public Axiom SubClass(String concept, String ancestor) {
		return new Axiom(SUBCLASS_OF, concept, ancestor);
	}
	
	public static Axiom ObjectPropertyAssertion(String string) {
		return new Axiom(OBJECT_PROPERTY_ASSERTION, string);
	}

	public static Axiom DataPropertyAssertion(String string) {
		return new Axiom(DATA_PROPERTY_ASSERTION, string);
	}
	
	public static IAxiom ObjectPropertyRange(String property, String concept) {
		return new Axiom(OBJECT_PROPERTY_RANGE, property, concept);
	}

	public static IAxiom DataPropertyRange(String property, String concept) {
		return new Axiom(DATA_PROPERTY_RANGE, property, concept);
	}

	public static IAxiom ObjectPropertyDomain(String property, String concept) {
		return new Axiom(OBJECT_PROPERTY_DOMAIN, property, concept);
	}

	public static IAxiom DataPropertyDomain(String property, String concept) {
		return new Axiom(DATA_PROPERTY_DOMAIN, property, concept);
	}
	
	public static IAxiom FunctionalDataProperty(String id) {
		return new Axiom(FUNCTIONAL_DATA_PROPERTY, id);
	}
	
	public static IAxiom FunctionalObjectProperty(String id) {
		return new Axiom(FUNCTIONAL_OBJECT_PROPERTY, id);
	}

	public static IAxiom AnnotationPropertyAssertion(String id) {
		/*
		 * TODO check: is this what we need to say?
		 */
		return new Axiom(DATA_PROPERTY_ASSERTION, id);
	}
	
	public static Axiom DisjointClasses(String[] concepts) {
		return new Axiom(DISJOINT_CLASSES, (Object[])concepts);
	}

	public Axiom(String type, Object ... args) {
		_type = type;
		_args = args;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Axiom) {
			return toString().equals(arg0.toString());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		String ret = "<" + _type;
		for (Object o : _args) {
			ret += "," + o.toString();
		}
		return ret + ">";
	}

	@Override
	public boolean is(String classAssertion) {
		return _type.equals(classAssertion);
	}

	@Override
	public Object getArgument(int index) {
		return _args[index];
	}

	@Override
	public Iterator<Object> iterator() {
		return Arrays.asList(_args).iterator();
	}




}
