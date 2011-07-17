package org.integratedmodelling.thinklab.api.project;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * The server side of project management.
 * 
 * @author Ferd
 *
 */
public interface IProjectManager {
	
	public IProject getProject(String projectId);
	
	public Collection<IProject> getActiveProjects();
	
	public IProject deployProject(String resourceId) throws ThinklabException;

	public void undeployProject(String projectId) throws ThinklabException;

}
