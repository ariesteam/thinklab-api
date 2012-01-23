package org.integratedmodelling.thinklab.api.lang;

import java.io.InputStream;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.lang.model.Namespace;

/**
 * Something that can turn an input stream into the javabeans representing a
 * namespace. Interpretation of those is left to the compiler.
 * 
 * @author Ferd
 *
 */
public interface IModelParser {
	
	/**
	 * Main entry point into the parser. It should normally not be called as the resolveNamespace() below is capable
	 * of creating a namespace from an ID, but it can be used for code passed as a string or for operation outside of
	 * a module.
	 * 
	 * @param input
	 * @return
	 * @throws ThinklabException
	 */
	public abstract Namespace parse(InputStream input, ICompilationContext context) throws ThinklabException;
	

}
