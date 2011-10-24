package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;


/**
 * A Model
 */
public interface IModel extends IObserver {
	
	public Pair<IObserver, IExpression> getObservers();

}