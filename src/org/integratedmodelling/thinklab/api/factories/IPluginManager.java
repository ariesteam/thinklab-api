package org.integratedmodelling.thinklab.api.factories;

import java.io.File;
import java.util.List;

import org.integratedmodelling.thinklab.api.plugin.IPluginLifecycleListener;
import org.integratedmodelling.thinklab.api.plugin.IThinklabPlugin;

/**
 * Handles all thinklab plugins. Meant to be lightweight. Note that projects are independent from plugins, which
 * should be reserved for system knowledge and program extension.
 * 
 * @author Ferd
 *
 */
public interface IPluginManager {

	public abstract void registerPluginPath(File path);
	
	public abstract void addPluginLifecycleListener(IPluginLifecycleListener listener);
	
	/**
	 * Return all plugins in order of dependency.
	 * @return
	 */
	public abstract List<IThinklabPlugin> getPlugins();
}
