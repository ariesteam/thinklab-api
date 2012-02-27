package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;
import org.integratedmodelling.thinklab.api.knowledge.IInstance;


/**
 * A Model, i.e. a query that uses observers to produce its results.
 */
public interface IModel extends IObserver {
	
	/**
	 * Return the observable. Never null. 
	 * @return
	 */
	public abstract IInstance getObservable();
	


}