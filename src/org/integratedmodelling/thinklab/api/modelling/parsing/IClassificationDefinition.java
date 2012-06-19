package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IClassification;
import org.integratedmodelling.thinklab.api.modelling.IClassifier;

public interface IClassificationDefinition extends ILanguageDefinition, IClassification {

	public void setConceptSpace(IConceptDefinition concept, Type typeHint);
	
	public void addClassifier(IConceptDefinition concept, IClassifier classifier);
	
	public void initialize();

}
