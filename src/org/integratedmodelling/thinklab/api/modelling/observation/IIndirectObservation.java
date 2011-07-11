package org.integratedmodelling.thinklab.api.modelling.observation;


public interface IIndirectObservation extends IObservation {
	
	/**
	 * During contextualization the current state of an indirect observation can be used as
	 * a dependency. This returns the formal name that the observation has been assigned. If none
	 * has been assigned (using a :as clause in the context statement), this should return 
	 * the lowercased concept name where any camelcase pattern has been replaced with a dash
	 * separator (i.e. MyConcept -> my-concept).
	 * 
	 * @return
	 */
	public String getFormalName();
	
	/**
	 * Return an appropriate accessor to use during contextualization to obtain data in this context.
	 * @return
	 */
	public abstract IAccessor getAccessor(IContext context);
		
}
