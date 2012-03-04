package org.integratedmodelling.lang.model;

import java.util.ArrayList;
import java.util.List;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;

public class ConditionalObserver extends Observer {

	ArrayList<Pair<Observer,IExpression>> _observers;
	
	public void addObserver(Observer observer, IExpression expression) {
		_observers.add(new Pair<Observer, IExpression>(observer,expression));
	}
	
	public List<Pair<Observer,IExpression>> getObservers() {
		return _observers;
	}
}
