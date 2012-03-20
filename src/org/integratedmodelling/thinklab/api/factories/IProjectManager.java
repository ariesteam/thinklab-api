package org.integratedmodelling.thinklab.api.factories;

import java.io.File;
import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * The server side of project management. Projects define namespaces, containing knowledge - either
 * ontologies or model objects, plus any support resources necessary.
 * 
 * @author Ferd
 *
 */
public interface IProjectManager {
	
	public IProject getProject(String projectId);
	
	public Collection<IProject> getProjects();
	
	public IProject deployProject(String pluginId, String resourceId) throws ThinklabException;

	public void undeployProject(String projectId) throws ThinklabException;

	public void addProjectDirectory(File projectDirectory);
}
