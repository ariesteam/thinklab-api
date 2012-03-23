package org.integratedmodelling.thinklab.api.modelling.parsing;

/**
 * Includes all the methods that language objects need to implement in order to be 
 * defined by a parser. These will be implemented by classes implementing the corresponding
 * immutable interfaces, so we don't have to put setters in the modeling API.
 * 
 * Objects of this kind should be created by the IResolver passed to the parsers, and
 * implementations will know what to do with them to turn them into "active" model
 * objects.
 * 
 * @author Ferd
 *
 */
public abstract interface ILanguageDefinition {
	
	public void setLineNumbers(int startLine, int endLine);
	
	public abstract int getFirstLineNumber();

	public abstract int getLastLineNumber();

}
