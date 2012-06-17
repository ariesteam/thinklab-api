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

	public enum Type {
		BINARY_CODING,
		NUMERIC_ENCODING,
		RANKING
	};
	
	public abstract Type getType();
	
	public abstract RankingScale getScale();
	
}
