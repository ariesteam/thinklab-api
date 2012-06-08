package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * Accessors implementing this one are serial accessors that can use dependencies and
 * produce more than an output state. The invocation logics for their methods is 
 * more complex - first the dependencies and expected outputs are notified once per
 * observable; then at each context state, all the dependency values are set with 
 * setValue, then process() is called, after which all the output values are extracted
 * with getValue(). Each of these will use the keys notified beforehand.
 * 
 * @author Ferd
 *
 */
public interface IComputingAccessor extends ISerialAccessor {

	/**
	 * This method is called once per dependency before any values are extracted, passing
	 * the key that will be available for get() when values are extracted.
	 * 
	 * TODO this should probably move to ISerialAccessor. ITransformingAccessor should
	 * have a similar method but taking a IState instead of an accessor.
	 * 
	 * TODO we need a separate notifyMediated for IMediatingAccessor.
	 * 
	 * @param key the formal name of the parameter that will be passed to the 
	 * @param accessor the accessor that will be used to get the dependency.
	 */
	public abstract void notifyDependency(ISemanticObject<?> observable, String key);

	/**
	 * Called at initialization to inform the accessor that it's expected to produce
	 * states for the passed observable, and make them accessible by passing the
	 * given key string to getValue().
	 * 
	 * @param observable
	 * @param key
	 */
	public abstract void notifyExpectedOutput(ISemanticObject<?> observable, String key);
	
	/**
	 * Compute anything the accessor computes over the expected context (which will
	 * be one state of the overall context our observer may have passed us)
	 * 
	 * After this is called, the appropriate getValue will
	 * be called to retrieve the output(s).
	 * 
	 * @return
	 */
	public void process(int stateIndex) throws ThinklabException;
	
	/**
	 * Pass the current value of a dependency, which will be made available for process()
	 * to use.
	 * 
	 * @param inputKey
	 * @param value
	 */
	public void setValue(String inputKey, Object value);
	
	/**
	 * Compute or retrieve the value for the passed context index. Any dependencies have
	 * been passed as independent accessors using notifyDependency before this is called.
	 * 
	 * @return
	 */
	public Object getValue(String outputKey);
}
