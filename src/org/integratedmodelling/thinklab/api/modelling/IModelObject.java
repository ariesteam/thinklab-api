package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.lang.IMetadataHolder;
import org.integratedmodelling.thinklab.api.lang.INamespaceQualified;

/**
 * @author Ferdinando
 *
 */
public interface IModelObject extends INamespaceQualified, IMetadataHolder {

	public abstract String getId();

	public abstract int getFirstLineNumber();

	public abstract int getLastLineNumber();
}
