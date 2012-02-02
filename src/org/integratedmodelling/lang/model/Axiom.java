package org.integratedmodelling.lang.model;

import org.integratedmodelling.thinklab.api.knowledge.IAxiom;

/**
 * Just a holder for axiom information. 
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

	
	
	
}
