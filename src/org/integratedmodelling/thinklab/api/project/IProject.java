package org.integratedmodelling.thinklab.api.project;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

import org.integratedmodelling.thinklab.api.modelling.INamespace;

/**
 * Thinklab projects are plug-in packages that provide Thinklab resources such as models, annotations etc. This interface is shared by the client and server side. Projects at the server side will need more methods to manage their lifetime in Thinklab.
 * @author  Ferd
 */
public interface IProject {
	
	// properties for META-INF/thinklab.properties file
	public static final String SOURCE_FOLDER_PROPERTY = "thinklab.source.folder";
	public static final String ONTOLOGY_NAMESPACE_PREFIX_PROPERTY = "thinklab.ontology.prefix";
	
	public String getId();

	public Properties getProperties();
		
	/**
	 * Return all the namespaces defined in the project.
	 * 
	 * @return
	 */
	public Collection<INamespace> getNamespaces();
	
	/**
	 * Source folders are scanned at startup and monitored during development. The location of
	 * source folders is in the SOURCE_FOLDER_PROPERTY of the thinklab properties.
	 * @return
	 */
	public Collection<File> getSourceFolders();
	
	/**
	 * Any ontologies in the source folder must have a URL that matches the project's 
	 * ontology namespace prefix (from properties, defaulting to http://www.integratedmodelling.org/ns)
	 * followed by the path corresponding to the ontology namespace and file name.
	 * 
	 * @return
	 */
	public String getOntologyNamespacePrefix();
	
	

}
