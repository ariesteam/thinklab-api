package org.integratedmodelling.thinklab.api.lang;

import org.integratedmodelling.thinklab.api.modelling.INamespace;

/**
 * @author  Ferd
 */
public interface INamespaceQualified {
	
	/**
	 * The namespace, which should be in the form of a dot-separated path
	 * @return
	 */
	public abstract INamespace getNamespace();	
	
	/**
	 * The fully qualified name of the object. Must return getNamespace() + "." + getId()
	 * @return
	 */
	public abstract String getName();
}
