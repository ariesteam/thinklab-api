package org.integratedmodelling.thinklab.api.knowledge;

import java.util.Map;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.project.IProject;

public interface IExpression {
	
	/**
	 * Simple execution interface for expressions. A new expression is generated per each call
	 * to the corresponding language statement, so it can store local data about its call context.
	 * 
	 * @param parameters
	 * @return
	 * @throws ThinklabException TODO
	 */
    public abstract Object eval(Map<String,Object> parameters) throws ThinklabException;

    /**
     * If an expression has been created within a project, we pass the project context so
     * that relative paths and other project-specific information can be resolved.
     * 
     * @param project
     */
	public abstract void setProjectContext(IProject project);
    
}


