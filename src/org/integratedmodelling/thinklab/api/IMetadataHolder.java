package org.integratedmodelling.thinklab.api;

import org.integratedmodelling.thinklab.api.knowledge.IConceptualizable;
import org.integratedmodelling.thinklab.api.modelling.metadata.IMetadata;

public interface IMetadataHolder extends IConceptualizable {

	public abstract IMetadata getMetadata();
}
