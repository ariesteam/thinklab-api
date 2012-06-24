package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.lang.RankingScale;

/**
 * Rankings are numerically ordered, linear quantification of a value that has no further
 * unit of measurement. The type returned by getStateType() will determine whether it's
 * a discrete or continuous ranking.
 *  
 * @author Ferd
 *
 */
public interface IRankingObserver extends IObserver {

	static final int BINARY_CODING = 2;
	static final int NUMERIC_ENCODING = 1;
	static final int RANKING = 0;
	
	public abstract int getType();
	
	public abstract RankingScale getScale();
	
}
