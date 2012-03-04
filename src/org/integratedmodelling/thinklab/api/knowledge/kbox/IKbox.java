package org.integratedmodelling.thinklab.api.knowledge.kbox;

import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.query.IQuery;

/**
 * A generalized persistent store for semantic objects. In order for the objects to be stored and retrieved, they need to be
 * conceptualizable, i.e. be able to be converted to/from a SemanticAnnotation. Queries are performed using 
 * IQuery objects. Implementations should provide a kbox type capable of handling the contexts in the core package, 
 * and have a mean of extension so that new contexts can be supported.
 * 
 * Much simpler than old "IKBox", scheduled to substitute it. Kboxes are created and deleted by the knowledge
 * manager based on a URI that should contain all access details for the underlying implementation, possibly
 * complemented by a properties file in the local configuration.
 * 
 * @author Ferd
 */
public interface IKbox {

	/**
	 * option to pass to query() as a flag (use | to combine more options)
	 *
	 * Do not follow object properties and only return the object with its
	 * literal and annotation properties.
	 */
	public static final int KBOX_LITERALS_ONLY = 1;

	/**
	 * option to pass to query() as a flag (use | to combine more options)
	 *
	 * Do not attempt to create objects from their annotations and just return
	 * SemanticAnnotation objects.
	 */
	public static final int KBOX_RETRIEVE_ANNOTATIONS = 2;
	
	/**
	 * option to pass to query() as a flag (use | to combine more options)
	 *
	 * Have query() return the IDs of matching objects (as Long values) instead
	 * of annotations or the objects themselves (default).
	 */
	public static final int KBOX_RETRIEVE_IDS = 4;
	
	
	/**
	 * Store object, return handle
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
	public abstract Object retrieve(long id) throws ThinklabException;
	
	/**
	 * Retrieve the object named by this id, or null if it's not there. Honor
	 * flags above or others defined by kbox implementation.
	 * 
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract Object retrieve(long id, int flags) throws ThinklabException;
	
	
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
	 * Query kbox. The list returned should be read-only and of course implement lazy access. This should
	 * be an equivalent of query(query, null), provided for simplicity of use.
	 * 
	 * @param query
	 * @return
	 * @throws ThinklabException
	 */
	public List<Object> query(IQuery query) throws ThinklabException;

	/**
	 * Query kbox. The list returned should be read-only and of course implement lazy access. This one
	 * should be the real workhorse and implement all the options defined above, plus any 
	 * implementation-specific ones.
	 * 
	 * @param query
	 * @return
	 * @throws ThinklabException
	 */
	public List<Object> query(IQuery query, int flags) throws ThinklabException;
	
	/**
	 * Kboxes must be able to identify themselves.
	 * 
	 * @return
	 */
	public abstract String getUri();
}
