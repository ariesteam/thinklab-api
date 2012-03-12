package org.integratedmodelling.thinklab.api.factories;

import java.io.File;
import java.util.List;

import org.integratedmodelling.thinklab.api.plugin.IThinklabPlugin;

public interface IPluginManager {

	public abstract void registerPluginPath(File path);
	
	/**
	 * Return all plugins in order of dependency.
	 * @return
	 */
	public abstract List<IThinklabPlugin> getPlugins();
}
