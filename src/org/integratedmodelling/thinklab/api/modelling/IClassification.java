package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;

/**
 * 
 * @author Ferd
 *
 */
public interface IClassification  {

	/**
	 * @author   Ferd
	 */
	public enum Type {
		UNORDERED,
		BOOLEAN_RANKING,
		ORDERED_RANKING,
		ORDERED_RANGE_MAPPING
	}
	
	public void initialize(IConcept type, Type typeHint) throws ThinklabValidationException;
	
	/**
	 * Return the concept that the passed object classifies to.
	 * 
	 * @param o
	 * @return
	 */
	public IConcept classify(Object o);
	
	/**
	 * Return all the classifiers that define the classification. If the classifiers have
	 * an intrinsic ordering, this function should return them in an order that reflects it.
	 * @return
	 */
	public List<IClassifier> getClassifiers();
	
	/**
	 * Return the list of concepts in the classification in the order corresponding
	 * to the ranks returned by getRank().
	 * 
	 * @return
	 */
	public List<IConcept> getConceptOrder();
	
	/**
	 * A Classification must be capable of converting a concept into its numeric
	 * rank. This should be done properly according to the concept, the model it
	 * (may) come from, the kind of classification represented, and any configuration
	 * meant to impose a specific order.
	 * 
	 * @see Type
	 * @param concept
	 * @return
	 */
	public int getRank(IConcept concept);
	
	/**
	 * This should return a two-element array only when the classification
	 * discretizes a numeric range. Any of the doubles may be infinity and
	 * need to be checked for that before use. If the classification does
	 * not represent a numeric range, this function must return null.
	 * 
	 * @param concept
	 * @return
	 */
	public double[] getNumericRange(IConcept concept);

	/**
	 * If the classification discretizes a numeric range, this must return
	 * the breakpoints of the range, i.e. an array of n+1 elements (n = number
	 * of concepts) where each interval {r[n], r[n+1]} corresponds to the numeric range
	 * of concept n. 
	 * 
	 * @return
	 */
	public double[] getDistributionBreakpoints();

}
