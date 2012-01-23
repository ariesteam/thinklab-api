package org.integratedmodelling.thinklab.api.lang;

import java.io.PrintStream;
import java.util.HashMap;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.lang.model.ModelObject;
import org.integratedmodelling.lang.model.Namespace;

public interface ICompilationContext {

	public abstract void setId(String namespace);

	public abstract org.integratedmodelling.lang.model.Namespace getNamespace();

	public abstract void setDefaultNamespace();

	public abstract HashMap<String, ModelObject> getSymbolTable();

	/**
	 * Override to fine-tune error management.
	 * 
	 * @param e
	 * @return
	 */
	public abstract boolean onException(Throwable e);

	/**
	 * Override to fine-tune error management.
	 * 
	 * @param warning
	 * @return
	 */
	public abstract boolean onWarning(String warning);

	/**
	 * Override to fine-tune error management.
	 * @param info
	 * @return
	 */
	public abstract boolean onInfo(String info);

	public abstract void dump(PrintStream out);

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