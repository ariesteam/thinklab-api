package org.integratedmodelling.thinklab.api.provenance;

/**
 * A transformation is a record of the process that transformed an object into another. It does not
 * have to be able to run the process, but it should be indexable properly so that we can lookup
 * any transformation between any objects and retrieve their results if we find them.
 * 
 * @author Ferd
 *
 * @param <T1>
 * @param <T2>
 */
public interface ITransformation<Torig, Tfinal> {

	/**
	 * Return the original object.
	 * 
	 * @return
	 */
	public Torig getOriginalObject();
	
	/**
	 * Return the result of the transformation.
	 * 
	 * @return
	 */
	public Tfinal getTransformedObject();
	
}
