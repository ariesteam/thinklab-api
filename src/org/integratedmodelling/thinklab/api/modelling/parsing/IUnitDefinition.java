package org.integratedmodelling.thinklab.api.modelling.parsing;

public interface IUnitDefinition extends IExpressionDefinition {
	
	/**
	 * Return a parseable string representation of the unit. Could simply be
	 * toString() but this enforces it.
	 * 
	 * @return
	 */
	public abstract String getStringExpression();

}
