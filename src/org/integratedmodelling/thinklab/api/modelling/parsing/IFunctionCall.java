package org.integratedmodelling.thinklab.api.modelling.parsing;

import java.util.Map;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * A function call. Must be able to resolve the function and return the result object when requested.
 * Validation of this object happens in the resolver.
 * 
 * @author Ferd
 *
 */
public interface IFunctionCall extends ILanguageDefinition {

	/**
	 * name given to a single parameter passed by itself, outside of a named list.
	 */
	String DEFAULT_PARAMETER_NAME = "_default";


	public void set(String id, Map<String,Object> parameters);
	
	public String getId();
	
	public Map<String,Object> getParameters();
	
	public void setProject(IProject project);
	

	/**
	 * Use the model manager or whatever strategy is appropriate to resolve the function call
	 * to an actual callable object, then call it and return the result. This is never called before
	 * the resolver has been given a chance to validate the call, so the function should exist and
	 * the parameters should be appropriate by the time call() is invoked.
	 * 
	 * @param functionId
	 * @param parameterNames
	 * @return
	 */
	public Object call() throws ThinklabException;
}
