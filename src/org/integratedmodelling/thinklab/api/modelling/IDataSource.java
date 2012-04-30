package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * Datasources are non-semantic datasets for one observable that are aware of what 
 * contexts mean, and are capable of contextualizing to a context (or to complain
 * about it) by producing an accessor. 
 * 
 * @author  Ferd
 */
public interface IDataSource {
	
	/**
	 * Produce an accessor that extracts the data for a given context.If we don't understand 
	 * the extents in the context, throw an exception.
	 * 
	 * This one must store enough context information to allow the accessor to respond properly to
	 * getValue(n). Whatever happened should be recorded in the provenance records.
	 * 
	 * @param context
	 * @return
	 * @throws ThinklabException if the transformation cannot be handled.
	 */
	public abstract IAccessor contextualize(IContext context) throws ThinklabException;
}
