package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.lang.IMetadataHolder;
import org.integratedmodelling.thinklab.api.lang.INamespaceQualified;
import org.integratedmodelling.thinklab.api.lang.parsing.ILanguageDefinition;

/**
 * @author Ferdinando
 *
 */
public interface IModelObject extends ILanguageDefinition, INamespaceQualified, IMetadataHolder {

	public abstract String getId();

}
