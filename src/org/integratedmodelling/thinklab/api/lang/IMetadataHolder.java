package org.integratedmodelling.thinklab.api.lang;

import org.integratedmodelling.thinklab.api.metadata.IMetadata;

/**
 * An object capable of producing metadata.
 * 
 * @author  Ferd
 */
public interface IMetadataHolder  {

	public abstract IMetadata getMetadata();
}
