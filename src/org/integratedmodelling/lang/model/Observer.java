package org.integratedmodelling.lang.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.integratedmodelling.collections.Pair;

public class Observer extends ObservingObject {

	ArrayList<Pair<Observer, Expression>> _mediated = 
			new ArrayList<Pair<Observer,Expression>>();
	
	/**
	 * Add one mediated observer with an optional conditional expression to contextualize the choice of
	 * mediated to the dependencies.
	 * 
	 * @param observer
	 * @param expression
	 */
	public void addMediated(Observer observer, Expression expression) {
		_mediated.add(new Pair<Observer, Expression>(observer, expression));
	}


	
	/**
	 * Set the intended accessor (optional) with any parameters needed. If no parameters were
	 * passed, parms will be null.
	 * 
	 * @param id
	 * @param parms
	 */
	public void setAccessor(String id, HashMap<String, Object> parms) {
		// TODO Auto-generated method stub
		
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
