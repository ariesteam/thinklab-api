package org.integratedmodelling.thinklab.api.plugin;

import java.io.File;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.configuration.IConfiguration;

/**
 * Simple interface for a Thinklab plugin. Should be easy to implement on top of
 * any plugin infrastructure, simple or complex, or even without one. Thinklab
 * plugins do not expect to be hot-swappable or anything fancy. 
 * 
 * @author Ferd
 *
 */
public interface IThinklabPlugin extends IConfiguration {
	
	/**
	 * Plugins have a name.
	 * 
	 * @return
	 */
	public String getId();
	
//	/**
//	 * Make all resources available. Before loading, ensure that anything that depends
//	 * on us is also loaded.
//	 * 
//	 * @throws ThinklabException
//	 */
//	protected abstract void load() throws ThinklabException;

//	/**
//	 * Unload and make all the resources unavailable. Also unload anything that depends on
//	 * it. No provision for
//	 * hot-swapping is expected.
//	 * 
//	 * @throws ThinklabException
//	 */
//	public abstract void unload() throws ThinklabException;
//	
	/**
	 * Find a resource file or directory in the plugin. Extract it to a scratch
	 * area if necessary so it is a File that can be copied and 
	 * used locally. Expected to be read only. Return null if not found.
	 * 
	 * @param resource
	 * @return
	 */
	public File findResource(String resource);


//	/**
//	 * True if unload() can be called.
//	 * 
//	 * @return
//	 */
//	public boolean isLoaded();
	
}
