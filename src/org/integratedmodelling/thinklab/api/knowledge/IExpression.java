package org.integratedmodelling.thinklab.api.knowledge;

import java.util.Map;

public interface IExpression {

	/**
	 * Expressions must be able to identify the language they're implemented in, so that they
	 * can be connected to an interpreter if necessary.
	 *
	 * @return
	 */
	public abstract String getLanguage();
	
	/**
	 * Simple execution interface for expressions.
	 * 
	 * @param parameters
	 * @return
	 */
    public abstract Object eval(Map<String,Object> parameters);
    
}


