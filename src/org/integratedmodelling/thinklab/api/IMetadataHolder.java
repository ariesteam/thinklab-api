package org.integratedmodelling.thinklab.api;

import org.integratedmodelling.thinklab.api.modelling.metadata.IMetadata;

/**
 * @author  Ferd
 */
public interface IMetadataHolder  {

	/**
	 * @uml.property  name="metadata"
	 * @uml.associationEnd  
	 */
	public abstract IMetadata getMetadata();
}
