package org.integratedmodelling.thinklab.api.lang;

import org.integratedmodelling.thinklab.api.knowledge.IExpression;

/**
 * Objects of this class are used to turn language instructions into expressions that
 * can be compiled and run. The IResolver will return one of these for the namespace
 * it's handling; language specifications in the namespace definition may be used to
 * select specific languages, but there should be a default that works with all
 * namespaces.
 * 
 * @author Ferd
 *
 */
public interface IExpressionLanguageAdapter {

	/**
	 * Return an expression that evaluates to true.
	 * 
	 * @return
	 */
	IExpression getTrueExpression();

}
