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
	public abstract Namespace parse(InputStream input) throws ThinklabException;
	
	/**
	 * The parser should remember all the namespaces that are parsed and be able to resolve them after parsing
	 * (for example as the result of an import statement). Also, if the namespace has been seen before it should
	 * try to load it from any obvious location before failing. Loading may be based on the physical location of a
	 * current repository and the namespace path, or on any externally provided reference (such as a URL) that 
	 * is passed as a second parameter.
	 *
	 * @param namespace 
	 * @param reference where to look for the namespace to load if not known. May be null.
	 * @return
	 * @throws ThinklabException
	 */
	public abstract Namespace resolveNamespace(String namespace, String reference) throws ThinklabException;
}
