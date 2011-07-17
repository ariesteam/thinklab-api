package org.integratedmodelling.thinklab.api.project;

import java.util.Properties;

/**
 * Thinklab projects are plug-in packages that provide Thinklab resources such as models, annotations etc.
 * 
 * This interface is shared by the client and server side. Projects at the server side will need more
 * methods to manage their lifetime in Thinklab.
 * 
 * @author Ferd
 *
 */
public interface IProject {
	
	public String getId();

	public Properties getProperties();

}