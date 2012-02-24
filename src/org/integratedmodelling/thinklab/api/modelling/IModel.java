package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;


/**
 * A Model, i.e. a query that uses observers to produce its results.
 */
public interface IModel extends IObserver {
	
	public Pair<IObserver, IExpression> getObservers();

}