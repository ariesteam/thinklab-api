package org.integratedmodelling.thinklab.api.modelling;


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

	public Object getValue(int contextIndex); 
}
