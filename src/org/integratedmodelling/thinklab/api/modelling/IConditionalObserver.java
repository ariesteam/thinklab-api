package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.collections.Pair;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;

public interface IConditionalObserver extends IObserver {

	public List<Pair<IObserver,IExpression>> getObservers();
}
