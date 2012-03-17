package org.integratedmodelling.thinklab.api.configuration;

import java.io.File;
import java.util.Properties;

/**
 * This interface tags objects that have configuration properties and
 * need workspace areas, such as sessions, project and plugins. Objects
 * implementing this interface can have installation directories and
 * must have workspaces, temp areas and scratch areas.
 * 
 * @author Ferd
 *
 */
public interface IConfiguration {

	// use these subspace names for extra consistency.
	public final static String SUBSPACE_CONFIG    = "config";
	public final static String SUBSPACE_KNOWLEDGE = "knowledge";
	public final static String SUBSPACE_SOURCE    = "src";
	public final static String SUBSPACE_PLUGINS   = "plugins";
	public final static String SUBSPACE_LIB       = "lib";
	public final static String SUBSPACE_PROJECTS  = "projects";

	/**
	 * Configurable objects should have properties that can be 
	 * persisted across invocations.
	 * 
	 * @return
	 */
	public Properties getProperties();

	/**
	 * Return a default workspace area where things of general use 
	 * may be written. A Workspace is a known place that should 
	 * contain things that are known to the user and understandable,
	 * such as project files. Use a ScratchArea for caches and 
	 * strangeness that users shouldn't see.
	 * 
	 * @return a valid directory. This should always return a 
	 * 	       valid, existing and writable path.
	 */
	public abstract File getWorkspace();
	
	/**
	 * Return a workspace area where things relevant to the given subspace
	 * may be written. A Workspace is a known place that should 
	 * contain things that are known to the user and understandable,
	 * such as project files. Use a ScratchArea for caches and 
	 * strangeness that users shouldn't see.
	 * 
	 * @return a valid directory. This should always return a 
	 * 	       valid, existing and writable path.
	 */
	public abstract File getWorkspace(String subspace);
	
	/**
	 * A scratch area is for places where stuff that's not going to
	 * be understandable to the user will get written. It should remain
	 * there across sessions.
	 * 
	 * @return a valid, writable scratch path.
	 */
	public abstract File getScratchArea();

	/**
	 * A scratch area is for places where stuff that's not going to
	 * be understandable to the user will get written. It should remain
	 * there across sessions. Use this one for specific subdirectories.
	 * 
	 * @return a valid, writable scratch path.
	 */
	public abstract File getScratchArea(String subArea);


	/**
	 * A temp area is like a scratch path but the object implementing
	 * this configuration should ensure that it's removed either at the
	 * end of a session or at the beginning of a new one, ideally at
	 * both points.
	 * 
	 * @param subArea
	 * @return
	 */
	public abstract File getTempArea(String subArea);

	
	/**
	 * The load path is the equivalent of an installation directory: a 
	 * read-only file space where resources may be. Resources implementing
	 * IConfiguration should always have a non-null load path. 
	 * 
	 * @return
	 */
	public abstract File getLoadPath();

	
	/**
	 * The load path is the equivalent of an installation directory: a 
	 * read-only file space where resources may be. This one should
	 * never create anything, but just return a File when the path
	 * exists, or null when it doesn't. 
	 * 
	 * @return
	 */
	public abstract File getLoadPath(String subArea);
	
}
