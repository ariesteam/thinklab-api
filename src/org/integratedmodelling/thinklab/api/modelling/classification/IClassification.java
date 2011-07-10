package org.integratedmodelling.thinklab.api.modelling.classification;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IConceptualizable;

public interface IClassification extends IConceptualizable {

	enum Type {
		UNORDERED,
		BOOLEAN_RANKING,
		ORDERED_RANKING,
		ORDERED_RANGE_MAPPING
	}
	
	public void initialize(IConcept type, Type typeHint) throws ThinklabValidationException;
	
	public IConcept classify(Object o);
	
	public Collection<IClassifier> getClassifiers();
	
	public int getRank(IConcept concept);
	
	public double[] getNumericRange(IConcept concept);

	public double[] getDistributionBreakpoints();

}
