package org.integratedmodelling.thinklab.api.factories;

import java.io.File;
import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * The server side of project management.
 * 
 * @author Ferd
 *
 */
public interface IProjectManager {
	
	public IProject getProject(String projectId);
	
	public Collection<IProject> getProjects();
	
	public IProject deployProject(String resourceId) throws ThinklabException;

	public void undeployProject(String projectId) throws ThinklabException;

	public void addProjectDirectory(File projectDirectory);
}
