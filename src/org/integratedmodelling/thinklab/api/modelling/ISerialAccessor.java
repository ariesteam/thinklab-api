package org.integratedmodelling.thinklab.api.modelling;

import java.util.Map;

/**
 * A serial accessor computes states one context state at a time. Most standard accessors are
 * serial. Given the choice, serial accessors should be used as they ensure proper behavior
 * re: listening, contextualization and topology ordering.
 * 
 * @author Ferd
 *
 */
public interface ISerialAccessor extends IAccessor {
	
	/**
	 * Compute or retrieve the value for the passed context index. If that requires parameters, the
	 * current values are passed as the context with definition-dependent string IDs.
	 * 
	 * @return
	 */
	public Object getValue(int overallContextIndex, Map<String, Object> context);
}
