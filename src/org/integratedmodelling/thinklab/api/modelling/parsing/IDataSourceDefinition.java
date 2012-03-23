package org.integratedmodelling.thinklab.api.modelling.parsing;

import java.util.Map;

import org.integratedmodelling.thinklab.api.knowledge.IExpression;

public interface IDataSourceDefinition {

	public void setType(String type);
	
	/**
	 * If the datasource is produced by an expression and we know it at
	 * compile time, pass it.
	 * 
	 * @param expression
	 */
	public void setResolvedExpression(IExpression expression);

	/**
	 * If the datasource is produced by an expression, pass the parameters
	 * we found.
	 * 
	 * @param parameters
	 */
	public void setParameters(Map<String, Object> parameters);
	
}
