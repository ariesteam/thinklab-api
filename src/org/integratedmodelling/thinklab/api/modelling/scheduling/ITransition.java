package org.integratedmodelling.thinklab.api.modelling.scheduling;

/**
 * A transition is a (small, fast) object that is generated by confronting a schedule event with
 * an IObservationContext. It should be able to tell quickly if the transition is relevant to the
 * context, and if relevant whether that relevance is partial or full (all extents covered). If 
 * relevance is partial, it should be able to return the proportion of coverage of each extent 
 * in the context it's generated for.
 * 
 * @author Ferd
 *
 */
public interface ITransition {

	public enum Relevance {
		PARTIAL,
		FULL
	}
	
	/**
	 * Return how the event that generated this transition is relevant to the context of
	 * interest, i.e. whether extents in the context has been covered partially or
	 * fully.
	 * 
	 * @return
	 */
	public Relevance getRelevance();
	
	/**
	 * Returns the percentage of coverage of each extent determined by the last transition 
	 * event in the context it's called for, expressed as an array of doubles (0 to 1) indexed
	 * by the extents in the context.
	 * 
	 * This should only be called when getRelevance() returns PARTIAL or FULL. If FULL,
	 * it should trivially return an array of 1.0s. If PARTIAL, at least one of the 
	 * coverages will be < 1.
	 * 
	 * @return
	 */
	public double[] getCoverage();
}
