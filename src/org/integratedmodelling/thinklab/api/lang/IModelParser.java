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
	 * Parse an input stream in a given namespace. Must not specify a namespace programmatically.
	 * The result is a clone of the input namespace, but contains only those model objects and 
	 * axioms that were declared in the input stream - i.e. a delta compared to the existing
	 * namespace (or all of it if it didn't exist originally).
	 * 
	 * @param input source language to be parsed into model objects to add to the namespace. It should not
	 *        be legal to declare a namespace in it.
	 * @param namespace a namespace ID that may identify a known namespace or not.
	 * @return a namespace containing whatever NEW model objects and axioms have been parsed from the input.
	 */
	public abstract Namespace parseInNamespace(InputStream input, String namespace, IResolver resolver) throws ThinklabException;
	

	/**
	 * Main entry point into the parser. Turns a resource into a Namespace bean, from which point on it's the job
	 * of the implementation to use it. A IResolver is used to connect resource names to input streams, find namespace
	 * sources across dependency chains, and handling errors and messages fed to it by the parser.
	 * 
	 * @param resource should identify a resolvable URI that the passed resolver can handle (second parameter in resolve).
	 * @param context a resolver to connect uris and namespace ids to input streams 
	 * @return a finished namespace bean to be turned into usable model objects by the implementation.
	 * @throws ThinklabException
	 */
	public abstract Namespace parse(String resource, IResolver resolver) throws ThinklabException;
	

}
