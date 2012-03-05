package org.integratedmodelling.lang.model;

import java.util.HashMap;

import org.integratedmodelling.thinklab.api.knowledge.IExpression;

public class Observer extends ObservingObject {

	Observer _mediated = null;
	String accessorId = null;
	HashMap<String, Object> accessorParameters = null;
	
	/**
	 * Add one observer with an optional conditional expression to contextualize the model to use. Creation
	 * of conditional observers if more than one observer is added or there are conditions is
	 * handled transparently.
	 * 
	 * @param observer
	 * @param expression
	 */
	public void addMediated(Observer observer, IExpression expression) {
		
		if (_mediated == null && expression == null) {
			_mediated = observer;
		} else {
			if (_mediated == null) {
				_mediated = new ConditionalObserver();
			} else if (	!(_mediated instanceof ConditionalObserver)) {
				ConditionalObserver obs = new ConditionalObserver();
				obs.addObserver(_mediated, null);
				_mediated = obs;
			}
			((ConditionalObserver)_mediated).addObserver(observer, expression);
		}
	}
	
	public Observer getMediated() {
		return _mediated;
	}
	
	/**
	 * Set the intended accessor (optional) with any parameters needed. If no parameters were
	 * passed, parms will be null.
	 * 
	 * @param id
	 * @param parms
	 */
	public void setAccessor(String id, HashMap<String, Object> parms) {
		this.accessorId	 = id;
		this.accessorParameters = parms;
	}
	
	/**
	 * The dependencies of an Observer. This one just publishes super.addDependency. 
	 * 
	 * @param cmodel
	 * @param formalName the 'named or :as' specification, null if not given.
	 */
	public void addDependency(Model cmodel, String formalName, boolean required) {
		super.addDependency(cmodel, formalName, required);
	}
}
