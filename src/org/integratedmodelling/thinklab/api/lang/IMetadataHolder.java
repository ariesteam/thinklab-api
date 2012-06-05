package org.integratedmodelling.thinklab.api.lang;

import org.integratedmodelling.thinklab.api.metadata.IMetadata;

/**
 * An object capable of producing metadata, exposing some convenient 
 * functions to retrieve fields.
 * 
 * @author  Ferd
 */
public interface IMetadataHolder  {

	/**
	 * Return the metadata. Must NEVER be null - at worst return empty metadata.
	 * 
	 * @return
	 */
	public abstract IMetadata getMetadata();
	

}
