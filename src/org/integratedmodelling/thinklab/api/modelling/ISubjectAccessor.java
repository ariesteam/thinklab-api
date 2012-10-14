package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * ISubjectAccessors can be attached to subject models and when present, receive the fully computed
 * state of the subject over the whole context. They do not exist by default, but user accessors may be
 * defined to provide specific computations. Any complex model being wrapped semantically is essentially
 * a ISubjectAccessor as an entry point. 
 * 
 * SubjectAccessors can modify the context for the object they handle and do pretty much what they
 * want, compatibly with the semantics. So they can also handle context transformations such as
 * GIS operations.
 * 
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
	 * @deprecated slated for removal - shouldn't be here
	 */
	public abstract void notifyDependency(IState state);

	/**
	 * Called at initialization to inform the accessor that it's expected to produce
	 * states for the passed observable, and make them accessible by passing the
	 * given key string to getValue().
	 * 
	 * @param observable
	 * @param key
	 * @deprecated slated for removal - shouldn't be here unless we use it to notify 
	 * 	           functional properties from the semantics that are not in the dependencies.
	 */
	public abstract void notifyExpectedOutput(ISemanticObject<?> observable, String key);
	
	/**
	 * Called once after the subject is created and placed in its context, but before any states or
	 * subjects are created (except those that were predefined). If not the root
	 * subject, the father context and the property that links us to it are also passed. It is 
	 * acceptable at this point to modify the scale according to the semantics - e.g. add boundaries
	 * for a watershed within the predefined grid, etc.
	 * 
	 * @param subject the subject we are handling.
	 * @param context the father object (may be null)
	 * @param property the property linking us to the father object (null if the latter is null)
	 * @throws ThinklabException
	 */
	public abstract void preinitialize(ISubject subject, ISubject context, IProperty property) throws ThinklabException;
	
	
	/**
	 * This one is called after all the data are created and initialized but before any subjects are 
	 * created. It is not ok to modify the context in this one, but data states may be inspected and
	 * values modified if necessary, as long as the context remains the same. 
	 * 
	 * @param subject the subject we are handling.
	 * @param context the father object (may be null)
	 * @param property the property linking us to the father object (null if the latter is null)
	 */
	public abstract void initialize(ISubject subject, ISubject context, IProperty property) throws ThinklabException;

	/**
	 * This one is called after all the data are created and initialized and all subjects are 
	 * created. Data states may be inspected and modified if necessary, subjects may be modified or
	 * deleted.  
	 * 
	 * @param subject the subject we are handling.
	 * @param context the father object (may be null)
	 * @param property the property linking us to the father object (null if the latter is null)
	 */
	public abstract void postinitialize(ISubject subject, ISubject context, IProperty property) throws ThinklabException;

	/**
	 * Compute anything the accessor computes over the expected context (which will
	 * be the whole context our observer may have passed us, and is passed again here
	 * for simplicity). This is only called if the context of observation includes some
	 * observation of time, after all the initialization sequence.
	 * 
	 * After this is called, getState be called as needed to retrieve the output(s) using the keys 
	 * previously notified through notifyExpectedOutput().
	 * 
	 * @param subject the subject we are handling.
	 * @param context the father object (may be null)
	 * @param property the property linking us to the father object (null if the latter is null)

	 * @return
	 */
	public ISubject process(ISubject subject, ISubject context, IProperty property) throws ThinklabException;
	
	/**
	 * Return the computed state for the context passed to process().
	 * 
	 * @param outputKey
	 * @return
	 */
	public IState getState(String outputKey);
}
