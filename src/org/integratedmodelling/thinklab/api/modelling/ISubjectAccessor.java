package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * @author  Ferd
 */
public interface ISubjectAccessor extends IAccessor {

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
	public abstract void notifyDependency(IState state);

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
	 * be the whole context our observer may have passed us, and is passed again here
	 * for simplicity).
	 * 
	 * After this is called, the appropriate getState will
	 * be called to retrieve the output(s).
	 * 
	 * @return
	 */
	public ISubject process(ISubject context) throws ThinklabException;
	
	/**
	 * Return the computed state for the context passed to process().
	 * 
	 * @param outputKey
	 * @return
	 */
	public IState getState(String outputKey);
}
