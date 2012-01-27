package org.integratedmodelling.lang.model;

import java.util.ArrayList;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.thinklab.api.lang.IList;

public class Model extends ObservingObject {
	
	ArrayList<Pair<Observer, Expression>> _observers = 
			new ArrayList<Pair<Observer,Expression>>();
	
	ArrayList<Model> contextModels = new ArrayList<Model>();

	String observableConcept;
	
	/**
	 * Add one observer with an optional conditional expression to contextualize the model to use.
	 * 
	 * @param observer
	 * @param expression
	 */
	public void addConditional(Observer observer, Expression expression) {
		_observers.add(new Pair<Observer, Expression>(observer, expression));
	}

	public void addObservable(ConceptObject concept) {

		/*
		 * TODO set the observable properly.
		 */
		
		observableConcept = concept.getNamespace() + ":" + concept.getId();
	}

	public void addObservable(IList instance) {
		
		/*
		 * TODO set the observable properly
		 */
		
		observableConcept = instance.first().toString();
	}

	/**
	 * The dependencies of a Model - used to select the contingencies if "when" statements
	 * are given. This one just rewraps addDependency to straighten the semantics out. 
	 * 
	 * @param cmodel
	 * @param formalName the 'named or :as' specification, null if not given.
	 */
	public void addContextModel(Model cmodel, String formalName, boolean required) {
		addDependency(cmodel, formalName, required);
	}

	public String getObservableConcept() {
		return observableConcept;
	}

}
