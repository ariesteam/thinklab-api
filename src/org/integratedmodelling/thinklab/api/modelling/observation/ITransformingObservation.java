package org.integratedmodelling.thinklab.api.modelling.observation;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.runtime.ISession;

public interface ITransformingObservation extends IObservation {

	
	/**
	 * Transform the observation and return the list definition of the new IObservation that takes its
	 * place in the compiler.
	 * 
	 * @return
	 */
	public IContext transform(IContext inputContext, ISession session, IContext context) throws ThinklabException;

	/**
	 * Return the class of the transformed observation
	 * @return
	 */
	public IConcept getTransformedObservationClass();
	
}
