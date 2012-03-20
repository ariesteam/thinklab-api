package org.integratedmodelling.thinklab.api.runtime;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * Implements the model of the user during a Thinklab session. Only one user model should exist per session, and it can be retrieved from it; it may also be null for non-interactive sessions. Application tasks and interface components will check the lineage of the user model in order to enable or disable operations.
 * @author  Ferdinando
 */
public interface IUserModel {

	/**
	 * Sessions may have an input and an output stream associated in case they can interact with the user through them.
	 * @return
	 * @uml.property  name="inputStream"
	 */
	public abstract InputStream getInputStream();
	
	/**
	 * Sessions may have an input and an output stream associated in case they can interact with the user through them.
	 * @return
	 * @uml.property  name="outputStream"
	 */
	public abstract PrintStream getOutputStream();
	
	/*
	 * initialize, passing the session that this user works in
	 */
	public abstract void initialize(ISession session);

	/*
	 * set properties for user
	 */
	/**
	 * @param uprop
	 * @uml.property  name="properties"
	 */
	public abstract void setProperties(Properties uprop);

	/*
	 * get properties for user. Should never return null.
	 */
	/**
	 * @uml.property  name="properties"
	 */
	public abstract Properties getProperties();

	/**
	 * if a user is logged in, this method must return a valid object that describes it. We use the instance 
	 * for authentication (checking subsumption by roles).
	 * 
	 * @return
	 * @throws ThinklabException
	 * @uml.property  name="userInstance"
	 * @uml.associationEnd  
	 */
	public abstract ISemanticObject<?> getUser() throws ThinklabException;
}
