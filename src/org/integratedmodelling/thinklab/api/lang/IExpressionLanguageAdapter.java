package org.integratedmodelling.thinklab.api.lang;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;
import org.integratedmodelling.thinklab.api.modelling.parsing.IFunctionCall;

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
	 * Return an expression that will evaluate to true.
	 * 
	 * @return
	 */
	IExpression getTrueExpression();

	/**
	 * Create an expression that is aware of the call context pertaining to the
	 * passed domains (those are the DomainConcept of the extents over which the
	 * expression is evaluated).
	 * 
	 * @param expression
	 * @param domain
	 * @return
	 */
	IExpression getExpression(String expression, IConcept ... domain);

	/**
	 * Return an expression that will call the function as passed when evaluated.
	 * 
	 * @param processFunctionCall
	 * @return
	 */
	IExpression compileFunctionCall(IFunctionCall processFunctionCall);

	/**
	 * Return an expression that will return the passed object when evaluated.
	 * 
	 * @param processLiteral
	 * @return
	 */
	IExpression compileObject(Object processLiteral);

}
