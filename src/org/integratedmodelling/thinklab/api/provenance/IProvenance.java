package org.integratedmodelling.thinklab.api.provenance;

/**
 * Objects implementing this interface allow API users to trace their provenance, intended as 
 * lineage from one other object through a transformation. The transformation contains the
 * implementing instance.
 * 
 * @author Ferd
 *
 */
public interface IProvenance<Toriginal> {
	
	/**
	 * Return the transformation that created us, or null.
	 * 
	 * @return
	 */
	public ITransformation<Toriginal, IProvenance<Toriginal>> getTransformation();
	
}
