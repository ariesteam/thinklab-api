package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.modelling.observation.IContext;

/**
 * A dataset is a persistent IContext. Should be capable of reconstructing the context it came from.
 * @author   Ferdinando Villa
 */
public interface IDataset {

	/**
	 * Set the context for the dataset. If there is one, ensure compatibility of extents and merge states from it.
	 * @param context
	 * @throws ThinklabException
	 * @uml.property  name="context"
	 */
	public abstract void setContext(IContext context) throws ThinklabException;
	
	/**
	 * Return the context we represent, creating it if we were loaded from persistent storage.
	 * @param context
	 * @return
	 * @throws ThinklabException
	 * @uml.property  name="context"
	 * @uml.associationEnd  
	 */
	public abstract IContext getContext();

	/**
	 * Ensure we can get GC's without losing data. We should know where. Return a 
	 * location we can be restored from.
	 * 
	 * @throws ThinklabException
	 */
	public abstract String persist() throws ThinklabException;
	
	/**
	 * Read the dataset from assigned storage.
	 * @throws ThinklabException
	 */
	public abstract void restore(String location) throws ThinklabException;
	
}
