package org.integratedmodelling.thinklab.api.knowledge.kbox;

import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;
import org.integratedmodelling.thinklab.api.knowledge.query.IQuery;
import org.integratedmodelling.thinklab.api.metadata.IMetadata;

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
	 * Metadata key for queries that want sorting of the results. Make the query implementation be
	 * a IMetadataHolder and set this field to the property that identifies the sort field.
	 * 
	 * TODO move to implementation
	 */
	public static final String SORT_FIELD = "___sort_field";

	/**
	 * Retrieve all first-class objects in kbox (those that have been explicitly stored with a 
	 * call to store(object)). It's a convenience method that should return the result of 
	 * an empty query, but may be able to use a more efficient strategy according to host platform.

	 * The list returned should be read-only and implement lazy access. 
	 * 
	 * @param query
	 * @return
	 * @throws ThinklabException
	 */
	public List<ISemanticObject<?>> retrieveAll() throws ThinklabException;

	/**
	 * Query kbox. The list returned should be read-only and of course implement lazy access. 
	 * Sorting, grouping or any other query option should be specified within the query
	 * object, using metadata or other strategy.
	 * 
	 * @param query
	 * @return
	 * @throws ThinklabException
	 */
	public List<ISemanticObject<?>> query(IQuery query) throws ThinklabException;

	
	/**
	 * Perform the same query as query() but only return the IDs of matching objects.
	 * This is provided so that metadata can be inspected before extracting potentially
	 * large or complex objects.
	 * 
	 * @param query
	 * @return
	 * @throws ThinklabException
	 */
	public List<Long> queryIDs(IQuery query) throws ThinklabException;
	
	/**
	 * Store object, return handle. Any object can be passed, as long as it can be
	 * annotated. Return an ID that can be passed to retrieve() to reconstruct the
	 * object.
	 * 
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract long store(Object o) throws ThinklabException;
	
	/**
	 * Retrieve the object pointed to by this id, or null if it's not there.
	 * 
	 * @param o
	 * @return
	 * @throws ThinklabException
	 */
	public abstract ISemanticObject<?> retrieve(long id) throws ThinklabException;	
	
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
	 * @throws ThinklabException 
	 */
	public abstract void removeAll(IQuery query) throws ThinklabException;

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
	
	/**
	 * This should be called once upon kbox instantiation, and leave the kbox
	 * in operational conditions. It should be smart about repeated calls to
	 * it.
	 */
	public abstract void open();
	
	/**
	 * This should always be called after a set of operations on the kbox, no matter 
	 * whether you know it's needed or not.
	 */
	public abstract void close();

	/**
	 * Kboxes have the option of storing metadata with each object they store. They can
	 * either be the metadata of the actual object if it's a IMetadataHolder, or
	 * other metadata such as timestamp of storage etc. This function should retrieve
	 * the metadata for the object identified by the given handle, and return an empty
	 * IMetadata object if no metadata exist or the kbox does not support metadata.
	 * 
	 * @param handle
	 * @return
	 */
	public IMetadata getObjectMetadata(long handle);
}
