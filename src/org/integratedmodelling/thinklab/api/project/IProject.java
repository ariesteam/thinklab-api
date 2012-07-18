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
	
	// properties for META-INF/thinklab.properties file. May also have metadata
	// according to IMetadata fields.
	public static final String SOURCE_FOLDER_PROPERTY = "thinklab.source.folder";
	public static final String ONTOLOGY_NAMESPACE_PREFIX_PROPERTY = "thinklab.ontology.prefix";
	public static final String PREREQUISITES_PROPERTY = "thinklab.prerequisites";
	
	/**
	 * TODO remove this - these should become namespaces and the obvious namespace to store into
	 * is the native one.
	 * @deprecated
	 */
	public static final String STORAGE_NAMESPACES_PROPERTY = "thinklab.storage.kbox";
	
	/*
	 * TODO this becomes a LIST of namespaces
	 */
	public static final String LOOKUP_NAMESPACES_PROPERTY = "thinklab.lookup.kbox";
	/*
	 * TODO this becomes a LIST of namespaces
	 */
	public static final String TRAINING_NAMESPACES_PROPERTY = "thinklab.training.kbox";
			
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
	 * @return the path of the source directory, relative to the project workspace.
	 */
	public String getSourceDirectory();
	
	/**
	 * Any ontologies in the source folder must have a URL that matches the project's 
	 * ontology namespace prefix (from properties, defaulting to http://www.integratedmodelling.org/ns)
	 * followed by the path corresponding to the ontology namespace and file name.
	 * 
	 * @return
	 */
	public String getOntologyNamespacePrefix();

	/**
	 * Find the resource file that defines the passed namespace. If not
	 * found, return null.
	 * 
	 * @param namespace
	 * @return
	 */
	public File findResourceForNamespace(String namespace, String extension);
	
	/**
	 * Get all projects we depend on. These should be ordered in load order.
	 * 
	 * @return
	 * @throws ThinklabException 
	 */
	public abstract List<IProject> getPrerequisites() throws ThinklabException;
	
	/**
	 * Return when this was last modified, so that we can load efficiently.
	 * 
	 * @return
	 */
	public long getLastModificationTime();
	
}
