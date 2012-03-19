package org.integratedmodelling.thinklab.api.lang;

/**
 * @author  Ferd
 */
public interface INamespaceQualified {
	

	
	/**
	 * The namespace, which should be in the form of a dot-separated path
	 * @return
	 */
	public abstract String getNamespace();	
	
	/**
	 * The fully qualified name of the object. Must return getNamespace() + "/" + getId()
	 * @return
	 */
	public abstract String getName();
}
