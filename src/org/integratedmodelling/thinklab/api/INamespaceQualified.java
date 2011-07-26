package org.integratedmodelling.thinklab.api;

/**
 * @author  Ferd
 */
public interface INamespaceQualified {
	
	/**
	 * The ID that distinguished the object in its namespace
	 * @return
	 * @uml.property  name="id"
	 */
	public abstract String getId();
	
	/**
	 * The namespace, which should be in the form of a dot-separated path
	 * @return
	 * @uml.property  name="namespace"
	 */
	public abstract String getNamespace();	
	
	/**
	 * The fully qualified name of the object. Must return getNamespace() + "/" + getId()
	 * @return
	 * @uml.property  name="name"
	 */
	public abstract String getName();
}
