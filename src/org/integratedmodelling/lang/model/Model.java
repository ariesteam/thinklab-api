package org.integratedmodelling.lang.model;

import java.util.ArrayList;

import org.integratedmodelling.thinklab.api.knowledge.IExpression;

public class Model extends ObservingObject {
	
	Observer _observer = null;
	ArrayList<Model> contextModels = new ArrayList<Model>();
	
	/**
	 * Add one observer with an optional conditional expression to contextualize the model to use. Creation
	 * of conditional observers if more than one observer is added or there are conditions is
	 * handled transparently.
	 * 
	 * @param observer
	 * @param expression
	 */
	public void addConditional(Observer observer, IExpression expression) {
		
		if (_observer == null && expression == null) {
			_observer = observer;
		} else {
			if (_observer == null) {
				_observer = new ConditionalObserver();
			} else if (	!(_observer instanceof ConditionalObserver)) {
				ConditionalObserver obs = new ConditionalObserver();
				obs.addObserver(_observer, null);
				_observer = obs;
			}
			((ConditionalObserver)_observer).addObserver(observer, expression);
		}
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
	public Observer getObserver() {
		return _observer;
	}



}
