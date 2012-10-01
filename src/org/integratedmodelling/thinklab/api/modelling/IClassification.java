package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

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
		RANGE_DISCRETIZATION
	}
		
	/**
	 * This must be known after initialization. The classification should use hints coming from
	 * the concept, the observer type etc. to determine the type. There is also a type hint that
	 * can be explicitly submitted in the IClassificationDefinition interface.
	 * 
	 * @return
	 */
	public Type getType();
	
	/**
	 * Return the root concept for the classification. All concepts that can be attributed
	 * to the classifiers should be direct, disjoint children of it.
	 * 
	 * @return
	 */
	public IConcept getConceptSpace();
	
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

	/**
	 * Return true if one of the possible rank concepts corresponds to a logical
	 * zero - e.g. NoHousehold or WetlandAbsent.
	 * 
	 * @return
	 */
	public boolean hasZeroRank();

	/**
	 * Return true if the classes in this classification are supposed to mean just
	 * that - disjoint classes not representing a numeric range or a logical ranking.
	 * 
	 * @return
	 */
	public boolean isCategorical();

	/**
	 * Return true if all the intervals are contiguous and the extreme intervals have
	 * finite boundaries.
	 * 
	 * @return
	 */
	public boolean isContiguousAndFinite();

	/**
	 * Check with another classification and ensure they do exactly the same things. 
	 * Could be "equals"- FIXME check if that creates any problem.
	 * 
	 * @param _classification
	 * @return
	 */
	public boolean isIdentical(IClassification _classification);

}
