package org.integratedmodelling.thinklab.api.modelling;


/**
 * Accessors give access to the raw values of observations and are used to process their data or objects.
 * 
 * IStateAccessors operate serially in a context, being passed each state in the sequence determined by the context's internal 
 * topologies. State models always have a default accessor and may be chained to a user-provided one for further processing.
 * 
 * ISubjectAccessors can be attached to subject models and when present, receive the fully computed
 * state of the subject over the whole context. They do not exist by default, but user accessors may be
 * defined to provide specific computations. Any complex model being wrapped semantically is essentially
 * a ISubjectAccessor as an entry point. 
 * 
 * For now IAccessor is a tag interface only, the relevant methods are in IStateAccessor and ISubjectAccessor.
 * 
 * @author Ferdinando
 *
 */
public abstract interface IAccessor {	
	

}