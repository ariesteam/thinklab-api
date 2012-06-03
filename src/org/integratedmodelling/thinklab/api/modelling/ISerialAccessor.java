package org.integratedmodelling.thinklab.api.modelling;


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
	 * Compute or retrieve the value for the passed context index. Any dependencies have
	 * been passed as independent accessors using notifyDependency before this is called.
	 * 
	 * @return
	 */
	public Object getValue(int overallContextIndex);
}
