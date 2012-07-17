package org.integratedmodelling.thinklab.api.factories;

import java.io.File;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * A project factory is a project manager that can also create, delete and modify projects on
 * the filesystem. 
 * <p>
 * The general usage pattern should be 
 * 
 * <code>
 * if (projectManager instanceof IProjectFactory) {
 * 	// do factory things
 * }
 * </code>
 * 
 * @author Ferd
 *
 */
public interface IProjectFactory extends IProjectManager {

	/**
	 * Create project files in the passed path, which should be the actual project path, not
	 * a directory that contains it. 
	 * 
	 * @param projectPath project path; doesn't have to exist.
	 * @param prerequisites array of project IDs the new project will depend on. May be null.
	 * @return a newly created and registered (but not loaded) project.
	 */
	public abstract IProject createProject(File projectPath, String[] prerequisites) throws ThinklabException;
	
	/**
	 * Delete the passed project from the filesystem. If loaded unload it first; then unregister it.
	 * 
	 * @param projectId
	 * @throws ThinklabException
	 */
	public abstract void deleteProject(String projectId) throws ThinklabException;
	
	/**
	 * Create an archive file suitable for remote deployment and backup in temporary storage 
	 * for the passed project, which must be registered.
	 * 
	 * @return
	 * @throws ThinklabException
	 */
	public File archiveProject(String projectId) throws ThinklabException;
}
