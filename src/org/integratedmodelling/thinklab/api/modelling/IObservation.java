package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;


/**
 * An observation is a fully specified and resolved scientific statement about an observable. It usually exists
 * in a context, which is the world view contingent to this observation. Once an observable has been observed
 * in a context, the same observable is "resolved", i.e. there cannot be two observations of the same observable
 * in the same context.
 * 
 * All dependencies of an IObservation must be also resolved in order to preserve the semantics of
 * an observation, and aligned with the given context. Dependencies must appear in the context as well,
 * the getDependencies() method is preserved to allow structural analysis of the observation's lineage.
 * 
 * By itself this interface represents a direct observation (identification), which doesn't use a state to represent its
 * observable - the observable is its own state. As such, it does not need an IObserver to provide specific semantics
 * for the observation process. Observations that have a IObserver also have "data" and are instances of
 * the derived IState which holds all data access methods. 
 * 
 * An observation without data is essentially a resolved model with no further info.
 * 
 * @author  Ferd
 * @see IState
 */
public interface IObservation extends IModelObject {
	
	public abstract ISemanticObject<?> getObservable();
	
	public abstract IDataSource getDataSource();
	
	public abstract IObserver getObserver();
	
	/**
	 * Observations are always part of a context, and must be able to return the context they are part of. The
	 * context will contain states for all observations in the structure that this represents.
	 * 
	 * The returned context should contain this observation as well. In rare cases it will contain JUST this
	 * observation: semantically, these are "universals" - statements of the existence of something, or 
	 * universal constants. 
	 * 
	 */
	public abstract IContext getContext();
}
