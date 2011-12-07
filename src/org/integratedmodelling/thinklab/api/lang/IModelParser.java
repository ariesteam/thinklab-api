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
	
	public abstract Namespace parse(InputStream input) throws ThinklabException;
}
