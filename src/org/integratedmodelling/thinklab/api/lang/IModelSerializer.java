package org.integratedmodelling.thinklab.api.lang;

import java.io.File;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.modelling.INamespace;

/**
 * If a IModelParser is also a IModelSerializer, its translation services may be offered in an
 * "import" dialog in a GUI. Translation means that a namespace parsed from a supported language can be written to the
 * "official" Thinklab language, which may be implementation defined but in the existing implementations
 * is assumed to be TQL.
 * 
 * @author Ferd
 *
 */
public interface IModelSerializer {

	/**
	 * Write the namespace to the given output file.
	 * 
	 * @param namespace
	 * @param outputFile
	 * @throws ThinklabException
	 */
	public abstract void writeNamespace(INamespace namespace, File outputFile) throws ThinklabException;
}
