package org.integratedmodelling.thinklab.api.knowledge.kbox;

import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.query.IQuery;

/**
 * A persistent store for semantic objects. In order for the objects to be stored, they need to be
 * instances or conceptualizables. Queries are performed using IQuery objects. Implementations should
 * provide a kbox type capable of handling the contexts in the core package, and have a mean of extension
 * so that new context can be supported.
 * 
 * Much simpler than old "IKBox", scheduled to substitute it. Kboxes are created and deleted by the knowledge
 * manager.
 * 
 * @author Ferd
 *
 */
public interface IKbox {

	/**
	 * Store object, return handle
	 * 
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract int store(Object o) throws ThinklabException;
	
	/**
	 * Remove object identified by handle
	 * 
	 * @param handle
	 * @throws ThinklabException
	 */
	public abstract void remove(int handle) throws ThinklabException;
	
	/**
	 * Remove everything in kbox.
	 * 
	 * @throws ThinklabException
	 */
	public abstract void clear() throws ThinklabException;
	
	/**
	 * Query kbox. The list returned should be read-only and of course implement lazy access.
	 * 
	 * @param query
	 * @return
	 * @throws ThinklabException
	 */
	public List<Object> query(IQuery query) throws ThinklabException;
}
