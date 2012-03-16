package org.integratedmodelling.thinklab.api.plugin;

import java.io.File;
import java.util.List;

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
	
// CHECK disabling for the time being, as we don't need to know in the plugin - only the plugin manager should
// worry about that. It is necessary in IProject, 
//	/**
//	 * Get all the plugin we depend on.
//	 * 
//	 * @return
//	 */
//	public abstract List<IThinklabPlugin> getPrerequisites();

	/**
	 * Callback called upon initialization.
	 * 
	 * @throws ThinklabException
	 */
	public abstract void load() throws ThinklabException;

	/**
	 * Callback called when Thinklab shuts down. No provision for
	 * hot-swapping is expected.
	 * 
	 * @throws ThinklabException
	 */
	public abstract void unload() throws ThinklabException;
	
	/**
	 * Find a resource file or directory in the plugin. Extract it to a scratch
	 * area if necessary so it is a File that can be copied and 
	 * used locally. Expected to be read only. Return null if not found.
	 * 
	 * @param resource
	 * @return
	 */
	public File findResource(String resource);


}
