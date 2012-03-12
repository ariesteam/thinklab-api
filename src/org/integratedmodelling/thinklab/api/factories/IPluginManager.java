package org.integratedmodelling.thinklab.api.factories;

import java.io.File;
import java.util.List;

import org.integratedmodelling.thinklab.api.plugin.IPluginLifecycleListener;
import org.integratedmodelling.thinklab.api.plugin.IThinklabPlugin;

public interface IPluginManager {

	public abstract void registerPluginPath(File path);
	
	public abstract void addPluginLifecycleListener(IPluginLifecycleListener listener);
	
	/**
	 * Return all plugins in order of dependency.
	 * @return
	 */
	public abstract List<IThinklabPlugin> getPlugins();
}
