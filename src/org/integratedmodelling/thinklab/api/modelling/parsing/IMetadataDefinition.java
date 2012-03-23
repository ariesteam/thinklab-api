package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.metadata.IMetadata;

public interface IMetadataDefinition extends ILanguageDefinition, IMetadata {

	public void put(String key, Object value);
	
}
