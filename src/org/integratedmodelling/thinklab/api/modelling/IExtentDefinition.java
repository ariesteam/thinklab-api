package org.integratedmodelling.thinklab.api.modelling;

/**
 * 
 * @author ferdinando.villa
 *
 */
public interface IExtentDefinition {
	
	/**
	 * 
	 * @param extentDefinition
	 */
	void define(IExtentDefinition extentDefinition);
	
	/**
	 * 
	 * @return
	 */
	IExtent getExtent();
	
	/**
	 * 
	 * @return
	 */
	boolean isResolved();

}
