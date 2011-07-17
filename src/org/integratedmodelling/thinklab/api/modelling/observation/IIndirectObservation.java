package org.integratedmodelling.thinklab.api.modelling.observation;


public interface IIndirectObservation extends IObservation {
	
	/**
	 * Return an appropriate accessor to use during contextualization to obtain data in this context.
	 * @return
	 */
	public abstract IAccessor getAccessor(IContext context);
		
}
