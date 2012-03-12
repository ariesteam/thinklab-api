package org.integratedmodelling.thinklab.api.plugin;

import java.io.File;
import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.configuration.IConfiguration;

public interface IThinklabPlugin extends IConfiguration {
	
	public String getId();
	
	public abstract List<IThinklabPlugin> getPrerequisites();

	public abstract void load() throws ThinklabException;

	public abstract void unload() throws ThinklabException;
	
	/**
	 * Find a resource file in the project. Return null if not found.
	 * 
	 * @param resource
	 * @return
	 */
	public File findResource(String resource);



}
