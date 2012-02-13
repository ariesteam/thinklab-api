package org.integratedmodelling.lang.model;

import java.util.ArrayList;
import java.util.List;

import org.integratedmodelling.collections.Pair;

public class Model extends ObservingObject {
	
	ArrayList<Pair<Observer, Expression>> _observers = 
			new ArrayList<Pair<Observer,Expression>>();
	
	ArrayList<Model> contextModels = new ArrayList<Model>();
	
	/**
	 * Add one observer with an optional conditional expression to contextualize the model to use.
	 * 
	 * @param observer
	 * @param expression
	 */
	public void addConditional(Observer observer, Expression expression) {
		_observers.add(new Pair<Observer, Expression>(observer, expression));
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

	/**
	 * Return all observers with their conditionals
	 * 
	 * @return
	 */
	public List<Pair<Observer, Expression>> getObservers() {
		return _observers;
	}


}
