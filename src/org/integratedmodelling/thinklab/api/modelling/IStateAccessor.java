package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

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
public interface IStateAccessor extends IAccessor {

	/**
	 * The name that the accessor will know as the "main" object in the
	 * computation. This can be used in expressions or to retrieve the
	 * value in trivial accessors such as mediators. It will be set by
	 * the compiler to the name that best reflects the object (e.g. the
	 * ID of the main observable or the model that computes it).
	 * 
	 * Should return something valid even if setSelfID() hasn't been
	 * called.
	 */
	public abstract String getSelfID();
	
	/**
	 * Set the ID of self so that getSelfID() will return it. Sometimes
	 * it feels like Java interfaces should allow non-static data members
	 * and spare us all this.
	 * 
	 * @param id
	 */
	public abstract void setSelfID(String id);
	
	/**
	 * This method is called once per dependency before any values are extracted, passing
	 * the key that will be available for get() when values are extracted.
	 * 
	 * @param key the formal name of the parameter that will be passed to the 
	 * @param accessor the accessor that will be used to get the dependency.
	 * @throws ThinklabException 
	 */
	public abstract void notifyDependency(IConcept observable, IObserver observer, String key) throws ThinklabException;

	/**
	 * Called at initialization to inform the accessor that it's expected to produce
	 * states for the passed observable, and make them accessible by passing the
	 * given key string to getValue().
	 * 
	 * @param observable
	 * @param key
	 */
	public abstract void notifyExpectedOutput(IConcept observable, String key) throws ThinklabException;
	
	/**
	 * Compute anything the accessor computes over the expected context (which will
	 * be one state of the overall context our observer may have passed us)
	 * 
	 * After this is called, the appropriate getValue will
	 * be called to retrieve the output(s).
	 * 
	 * NOTE: this may be called more than once with the same observable and 
	 * different names. It must be capable of handling that correctly.
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
