package org.integratedmodelling.thinklab.api.factories;

import java.io.File;
import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.lang.IResolver;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * The server side of project management. Projects define namespaces, containing knowledge - either
 * ontologies or model objects, plus any support resources necessary.
 * 
 * @author Ferd
 *
 */
public interface IProjectManager {
	
	/**
	 * Return a specific project if registered, or null if not found. Do not throw an 
	 * exception. The project may be loaded or unloaded.
	 * 
	 * @param projectId
	 * @return
	 */
	public IProject getProject(String projectId);
	
	/**
	 * Return all the projects registered with the manager. They may or may not be
	 * loaded.
	 * 
	 * @return
	 */
	public Collection<IProject> getProjects();
	
	/**
	 * This loads every project in order of dependency, minimizing unnecessary unloading.
	 * 
	 * @throws ThinklabException
	 */
	public void loadAllProjects() throws ThinklabException;
	
	/**
	 * Deploy a project contained in a given archive or directory.
	 * 
	 * @param pluginId
	 * @param resourceId
	 * @return
	 * @throws ThinklabException
	 */
	public IProject deployProject(String pluginId, String resourceId) throws ThinklabException;

	/**
	 * Synonymous of unload followed by unregister.
	 * 
	 * @param projectId
	 * @throws ThinklabException
	 */
	public void undeployProject(String projectId) throws ThinklabException;

	/**
	 * Register a project contained in the passed directory. If the directory does not
	 * contain a project, throw an exception. This should rebuild a dependency tree and
	 * if 
	 * 
	 * @param projectDir
	 * @return
	 * @throws ThinklabException 
	 */
	public String[] registerProject(File ... projectDir) throws ThinklabException;
	
	/**
	 * Load a project, ensuring all its prerequisites are also loaded. This should work as
	 * refresh() as well, but not throw an exception if the project isn't loaded.
	 * @param projectId
	 * @return 
	 * @throws ThinklabException
	 */
	public IProject loadProject(String projectId) throws ThinklabException;

	/**
	 * Unregister the project, unloading it (and those that depend on it) if loaded. After this
	 * is called, load(id) will throw an exception.
	 * 
	 * @param projectId
	 */
	public void unregisterProject(String projectId);
	
	/**
	 * Refresh the named project from its disk representation, unloading if it was
	 * loaded, and all those that depend on it.
	 *  
	 * @param projectId
	 */
	public void refreshProject(String projectId) throws ThinklabException;
	
	/**
	 * The project manager should not have a watched project directory by default - projects
	 * directories can be registered individually. If this is given, it should register all
	 * the projects in it.
	 *  
	 * @param projectDirectory
	 * @throws ThinklabException 
	 */
	public void registerProjectDirectory(File projectDirectory) throws ThinklabException;
	
	/**
	 * Return a resolver to load the resources in a project.
	 * 
	 * @param project
	 * @return
	 */
	public IResolver getResolver(IProject project);

	/**
	 * Unload the project. Should count references to the project and leave its definitions
	 * in the knowledge base unless no other loaded projects reference it.
	 * 
	 * @param projectId
	 * @throws ThinklabException
	 */
	void unloadProject(String projectId) throws ThinklabException;


}
