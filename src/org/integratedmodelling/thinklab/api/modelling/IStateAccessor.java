package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * A serial accessor computes states one context state at a time. Most standard accessors are
 * serial. Given the choice, serial accessors should be used as they ensure proper behavior
 * re: listening, contextualization and topology ordering.
 * 
 * If the accessor is a computing accessor, which can have named dependencies and
 * multiple output states, getValue(int) will be ignored and the getValue(String)
 * will be called instead, after process(int) is called.
 * 
 * @author Ferd
 *
 */
public interface IStateAccessor extends IAccessor {

	/**
	 * Return a type corresponding to the state. The first accessor will create the state if 
	 * necessary, but the observer should be able to know the type.
	 *  
	 * @return
	 */
	public abstract IConcept getStateType();
	
	
	public Object getValue(int contextIndex); 
}
