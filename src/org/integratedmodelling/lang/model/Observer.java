package org.integratedmodelling.lang.model;

import java.util.HashMap;

public class Observer extends ObservingObject {

	/**
	 * Set the intended accessor (optional) with any parameters needed. If no parameters were
	 * passed, parms will be null.
	 * 
	 * @param id
	 * @param parms
	 */
	public void setAccessor(String id, HashMap<String, Object> parms) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * The dependencies of an Observer. This one just publishes super.addDependency. 
	 * 
	 * @param cmodel
	 * @param formalName the 'named or :as' specification, null if not given.
	 */
	public void addDependency(Model cmodel, String formalName, boolean required) {
		super.addDependency(cmodel, formalName, required);
	}
}
