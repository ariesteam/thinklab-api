package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.metadata.IMetadata;

public interface IMetadataDefinition extends ILanguageDefinition, IMetadata {

	/**
	 * Just like in the underlying hash, but will silently ignore a call where
	 * any of the parameter is null.
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value);
	
}
