package org.integratedmodelling.lang.model;

/**
 * Captures name and arguments of a function
 * 
 * @author Ferd
 *
 */
public class Function {

	String functionId;
	String[] argumentNames;
	Object[] argumentValues;
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String[] getArgumentNames() {
		return argumentNames;
	}
	public void setArgumentNames(String[] argumentNames) {
		this.argumentNames = argumentNames;
	}
	public Object[] getArgumentValues() {
		return argumentValues;
	}
	public void setArgumentValues(Object[] argumentValues) {
		this.argumentValues = argumentValues;
	}
	
	
}
