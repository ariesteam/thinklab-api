package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * A dataset is a persistent IContext. Should be capable of reconstructing the context it came from.
 * @author Ferdinando Villa
 */
public interface IDataset {

	/**
	 * Set the context for the dataset. This is assumed to be an initialization operation, done once and
	 * with destructive effects if done more than once.
	 * 
	 * @param context
	 * @throws ThinklabException
	 */
	public abstract void setContext(ISubject context) throws ThinklabException;
	
	/**
	 * Return the context we represent, creating it if we were loaded from persistent storage.
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public abstract ISubject getContext();

	/**
	 * Ensure we can get GC's without losing data. We should know where. Return a 
	 * location URI it can be restored from. Should be capable of taking null for a
	 * location, creating its own storage in a default area.
	 * 
	 * @throws ThinklabException
	 */
	public abstract String persist(String location) throws ThinklabException;
	
	/**
	 * Read the dataset from assigned storage.
	 * @throws ThinklabException
	 */
	public abstract void restore(String location) throws ThinklabException;
	
}
