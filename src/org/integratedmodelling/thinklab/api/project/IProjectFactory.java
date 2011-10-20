package org.integratedmodelling.thinklab.api.project;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * The client side of project management. The factory manages physical projects on
 * disk. They need to be deployed by a ProjectManager in order to exist in thinklab.
 * 
 * @author Ferd
 *
 */
public interface IProjectFactory {

	public IProject createProject(String id) throws ThinklabException;

	public void deleteProject(String id) throws ThinklabException;

	public IProject getProject(String id);
	
	public Collection<IProject> getProjects();
	
}
