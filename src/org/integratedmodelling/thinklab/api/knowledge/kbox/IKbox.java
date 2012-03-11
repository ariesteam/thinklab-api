package org.integratedmodelling.thinklab.api.knowledge.kbox;

import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;
import org.integratedmodelling.thinklab.api.knowledge.query.IQuery;

/**
 * K-box or Knowledge Box is the only place where semantic objects get stored permanently. Being an instance
 * store, it's expected to scale to large amounts of object efficiently and to possibly give up some 
 * non-crucial reasoning for efficiency, like most instance stores do.
 * 
 * A generalized persistent store for semantic objects. In order for the objects to be stored and retrieved, they need to be
 * conceptualizable, i.e. be able to be converted to/from a ISemanticObject. Queries are performed using 
 * IQuery objects.
 * 
 * Implementations should provide at least one default kbox implementation, so that a user can just do
 * 
 * IKbox kbox = <KnowledgeManager>.requireKbox(anyName)
 * 
 * and expect it to work.
 * 
 * @author Ferd
 */
public interface IKbox {

	/**
	 * Retrieve all first-class objects in kbox (those that have been explicitly stored with a 
	 * call to store(object)). 

	 * The list returned should be read-only and implement lazy access. 
	 * 
	 * @param query
	 * @return
	 * @throws ThinklabException
	 */
	public List<ISemanticObject> retrieveAll() throws ThinklabException;

	/**
	 * Query kbox. The list returned should be read-only and of course implement lazy access. 
	 * 
	 * @param query
	 * @return
	 * @throws ThinklabException
	 */
	public List<ISemanticObject> query(IQuery query) throws ThinklabException;

	/**
	 * Store object, return handle. Any object can be passed, as long as it can be
	 * annotated.
	 * 
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract long store(Object o) throws ThinklabException;
	
	/**
	 * Retrieve the object named by this id, or null if it's not there.
	 * Shorthand for retrieve(id, flags);
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract ISemanticObject retrieve(long id) throws ThinklabException;	
	
	/**
	 * Remove object identified by handle
	 * 
	 * @param handle
	 * @throws ThinklabException
	 */
	public abstract void remove(long handle) throws ThinklabException;

	/**
	 * Remove all objects matching the query.
	 * 
	 * @param query
	 */
	public abstract void removeAll(IQuery query);

	/**
	 * Remove everything in kbox.
	 * 
	 * @throws ThinklabException
	 */
	public abstract void clear() throws ThinklabException;
	
	/**
	 * Kboxes must be able to identify themselves.
	 * 
	 * @return
	 */
	public abstract String getUri();
}
