package org.integratedmodelling.lang.model;

import java.util.ArrayList;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.thinklab.api.lang.IList;

public class Model extends ObservingObject {
	
	ArrayList<Pair<Observer, Expression>> _observers = 
			new ArrayList<Pair<Observer,Expression>>();

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
		// TODO Auto-generated method stub
		
	}

	public void addObservable(IList instance) {
		// TODO Auto-generated method stub
		
	}

}
