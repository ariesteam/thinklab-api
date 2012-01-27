package org.integratedmodelling.thinklab.api.lang;

import java.io.InputStream;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.lang.model.Namespace;

public interface IResolver {
	
	/**
	 * Override to fine-tune error management. If this throws an exception, the parser also will. If not, 
	 * the return value determines whether parsing continue (true) or not (false).
	 * 
	 * @param e
	 * @return
	 */
	public abstract boolean onException(Throwable e, int lineNumber) throws ThinklabException;

	/**
	 * Override to fine-tune error management. Will stop parsing if false is returned.
	 * 
	 * @param warning
	 * @return
	 */
	public abstract boolean onWarning(String warning, int lineNumber);

	/**
	 * Override to fine-tune error management. Will stop parsing if false is returned.
	 * 
	 * @param info
	 * @return
	 */
	public abstract boolean onInfo(String info, int lineNumber);

	/**
	 * Resolve a namespace (optionally further specified by an import URI) to an input stream. Either the namespace id or
	 * the reference may be null, i.e. it must be prepared to produce an inputstream based on a namespace id only (e.g. 
	 * resolving across dependent plugin classpaths) and/or based on an external reference URI; if both are present, 
	 * it should ensure that they're compatible name-wise. 
	 *
	 * @param namespace. May be null if only the reference is passed.
	 * @param reference where to look for the namespace to load if not known. May be null.
	 * @return a valid input stream which will be read and closed by the parser.
	 * @throws ThinklabException
	 */
	public abstract InputStream resolveNamespace(String namespace, String reference) throws ThinklabException;
	
	/**
	 * Callback invoked as soon as a namespace declaration is parsed. Passed back the parameters that were
	 * used in namespace resolution (see resolveNamespace for docs) both of which may be null. Should ensure that 
	 * the namespace declaration is appropriate for the resources. Any imports will be parsed before this is called.
	 * 
	 * @param namespaceId
	 * @param resourceId
	 * @param namespace
	 */
	public abstract void onNamespaceDeclared(String namespaceId, String resourceId, Namespace namespace);
	
	/**
	 * Callback invoked as soon as parsing of a namespace has been completed. The namespace will contain all
	 * model objects declared in it and the axioms collected from them. 
	 * 
	 * @param namespace
	 */
	public abstract void onNamespaceDefined(Namespace namespace);
	
}