package org.integratedmodelling.thinklab.api.runtime;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabResourceNotFoundException;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;

public interface IInterpreterManager {

	public abstract IInterpreter getInterpreter(IExpression algorithm)
			throws ThinklabResourceNotFoundException;

	public abstract IInterpreter newInterpreter(String language)
			throws ThinklabException;

	/**
	 * Retrieve interpreter for given algorithm, using interpreter registry and class
	 * of algorithm. The same interpreter may be returned for the same session, as it is
	 * a Thinklab requirement that operations in the same session are synchronized.
	 * The IValue containing the algorithm as a string. May have been
	 * validated or not. The specific IConcept linked to the string will be used to
	 * select the interpreter.
	 * @param algorithm 
	 * @param session
	 * @return
	 */
	public abstract IInterpreter getInterpreter(IExpression algorithm,
			ISession session) throws ThinklabResourceNotFoundException;

}