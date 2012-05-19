package org.integratedmodelling.thinklab.api.project;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.plugin.IThinklabPlugin;

/**
 * Thinklab projects are plug-in packages that provide Thinklab resources such as models, annotations etc. 
 * This interface is shared by the client and server side. Projects at the server side will need more
 * methods to manage their lifetime in Thinklab.
 * @author  Ferd
 */
public interface IProject extends IThinklabPlugin {
	
	public static final String THINKLAB_META_INF = "META-INF";
	public static final String THINKLAB_PROPERTIES_FILE = "thinklab.properties";
	
	// properties for META-INF/thinklab.properties file
	public static final String SOURCE_FOLDER_PROPERTY = "thinklab.source.folder";
	public static final String ONTOLOGY_NAMESPACE_PREFIX_PROPERTY = "thinklab.ontology.prefix";
	public static final String PREREQUISITES_PROPERTY = "thinklab.prerequisites";
	public static final String STORAGE_KBOX_PROPERTY = "thinklab.storage.kbox";
	public static final String LOOKUP_KBOX_PROPERTY = "thinklab.lookup.kbox";
			
	/**
	 * Return all the namespaces defined in the project.
	 * 
	 * @return
	 */
	public Collection<INamespace> getNamespaces();
	
	/**
	 * Source folders are scanned at startup and monitored during development. The location of
	 * source folders is in the SOURCE_FOLDER_PROPERTY of the thinklab properties. There is
	 * one source folder per project.
	 * 
	 * @return
	 */
	public File getSourceDirectory();
	
	/**
	 * Any ontologies in the source folder must have a URL that matches the project's 
	 * ontology namespace prefix (from properties, defaulting to http://www.integratedmodelling.org/ns)
	 * followed by the path corresponding to the ontology namespace and file name.
	 * 
	 * @return
	 */
	public String getOntologyNamespacePrefix();

	/**
	 * Add a dependency on another project and save all the related info in manifest and
	 * properties. If reload == true, reload the project and its
	 * dependencies.
	 * 
	 * @param plugin
	 * @param reload
	 * @throws ThinklabException
	 */
	public void addDependency(String plugin, boolean reload) throws ThinklabException;

	/**
	 * Find the resource file that defines the passed namespace. If not
	 * found, return null.
	 * 
	 * @param namespace
	 * @return
	 */
	public File findResourceForNamespace(String namespace, String extension);
	
	/**
	 * Get all projects we depend on.
	 * 
	 * @return
	 */
	public abstract List<IProject> getPrerequisites();
	
}
