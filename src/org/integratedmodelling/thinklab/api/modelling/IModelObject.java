package org.integratedmodelling.thinklab.api.modelling;

import java.util.Set;

import org.integratedmodelling.thinklab.api.ILanguageObject;
import org.integratedmodelling.thinklab.api.IMetadataHolder;
import org.integratedmodelling.thinklab.api.INamespaceQualified;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * Tag interface for any object that can be stored in the model map. Currently that
 * means agents, models, contexts and scenarios. Hopefully it will stop at this, meaning
 * the whole thing makes sense.
 * 
 * @author Ferdinando
 *
 */
public interface IModelObject extends ILanguageObject, INamespaceQualified, IMetadataHolder {

	/**
	 * Return the set of all concepts observed in this model object
	 * @return
	 */
	public Set<ISemanticObject> getObservables();
}
