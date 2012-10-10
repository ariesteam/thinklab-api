package org.integratedmodelling.thinklab.api.modelling.parsing;

public interface IUnitDefinition {
	
	public void setExpression(String expression);
	
	/**
	 * Return a parseable string representation of the unit. Could simply be
	 * toString() but this enforces it.
	 * 
	 * @return
	 */
	public abstract String getStringExpression();

}
