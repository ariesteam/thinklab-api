package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.runtime.ISession;

/**
 * @author  Ferd
 */
public interface ITransformingAccessor extends IAccessor {

	
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
	 * @uml.property  name="transformedObservationClass"
	 * @uml.associationEnd  
	 */
	public IConcept getTransformedObservationClass();
	
}
