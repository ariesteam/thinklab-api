package org.integratedmodelling.thinklab.api.modelling;

/**
 * Accessors implementing this one will be passed the accessors for all the dependencies in
 * the models that use them, so they can use them to compute their states.
 * 
 * @author Ferd
 *
 */
public interface IComputingAccessor {

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
	public abstract void notifyDependency(String key, IAccessor accessor);

}
