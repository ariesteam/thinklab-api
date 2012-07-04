package org.integratedmodelling.thinklab.api.lang;

import java.util.List;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * Prototypes define callable functions and their parameters. In Thinklab it works for commands and
 * language functions so far, both of which can return one of these. A prototype implementation should
 * be able to be validated against a call.
 * 
 * @author Ferd
 *
 */
public interface IPrototype {

	/**
	 * 
	 * @return
	 */
	public String getId();
	
	/**
	 * 
	 * @return
	 */
	public IConcept getReturnType();
	
	/**
	 * 
	 * @return
	 */
	public List<String> getMandatoryArgumentNames();
	
	/**
	 * 
	 * @return
	 */
	public List<String> getOptionalArgumentNames();
	
	/**
	 * 
	 * @param argumentName
	 * @return
	 */
	public IConcept getArgumentType(String argumentName);
	
	/**
	 * 
	 * @return
	 */
	public String getDescription();
}
