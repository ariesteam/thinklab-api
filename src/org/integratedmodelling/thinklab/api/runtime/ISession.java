/**
 * ISession.java
 * ----------------------------------------------------------------------------------
 * 
 * Copyright (C) 2008 www.integratedmodelling.org
 * Created: Jan 17, 2008
 *
 * ----------------------------------------------------------------------------------
 * This file is part of Thinklab.
 * 
 * Thinklab is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Thinklab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the software; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * ----------------------------------------------------------------------------------
 * 
 * @copyright 2008 www.integratedmodelling.org
 * @author    Ferdinando Villa (fvilla@uvm.edu)
 * @author    Ioannis N. Athanasiadis (ioannis@athanasiadis.info)
 * @date      Jan 17, 2008
 * @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v3
 * @link      http://www.integratedmodelling.org
 **/
package org.integratedmodelling.thinklab.api.runtime;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.query.IConformance;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.listeners.IListenable;


/**
 * A Session is a temporary concept space that contains all instances that are created during a user session. A session should be an ontology or contain one, but its identity as a IOntology is not mandated. Sessions are normally memory-based but should be able to persist themselves. All operations on the same session are synchronous so there's no need to worry about concurrency.
 * @author  Ferdinando Villa
 */
public interface ISession extends IListenable {
	
	// standard variable names for notification levels
	public static final String DEBUG = "session.debug";
	public static final String INFO = "session.info";
	public static final String COMMAND = "session.command";
	
	/**
	 * Each session has a unique ID assigned by the Knowledge manager. 
	 * @return  the session's ID.
	 */
	public abstract String getSessionID();
	
	/**
	 * A session must have properties that users and plugins can set. This method must return a valid properties object.
	 * @return  the session's properties.
	 */
	public abstract Properties getSessionProperties();

    /**
     * <p>Write all current contents of ontology on passed ontology file.</p>
     * <p><b>NOTE:</b> this will remove all non-validated instances, rendering all relative objects meaningless and their use
     * dangerous. This may change.</p>
     * @param file
     * @throws ThinklabException
     */
    public abstract void write(String file) throws ThinklabException;
    
	
	/**
	 * Sessions must be capable of creating temporary concepts from a list specification. These
	 * concepts can only restrict "global" ones by specifying owl:hasValue restrictions. The list
	 * syntax is very similar to the one used for instances.
	 * 
	 * @param list 
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IConcept createConcept(IList list) throws ThinklabException;


	/**
	 * Delete the named object.
	 * @param name name of object
	 * @throws ThinklabException 
	 */
	public abstract void deleteObject(String name) throws ThinklabException;
	
	/**
	 * Return the user model for the session. If the session is not interactive, the user model may be null.
	 * @return
	 */
	public abstract IUserModel getUserModel();

	/**
	 * Get the input stream if the user model has it, or return null.
	 * @return
	 */
	public abstract InputStream getInputStream(); 
	
	/**
	 * get the output stream if the user model defines one, otherwise return null.
	 */
	public PrintStream getOutputStream();
	

	/**
	 * Print a string wherever is appropriate, or ignore if not appropriate. Do not
	 * raise errors.
	 * @param s
	 */
	public void print(String s);
	
	/**
	 * 
	 * @param varname
	 * @param value
	 */
	public abstract void pushVariable(String varname, Object value);
	
	/**
	 * 
	 * @param varname
	 * @return
	 * @throws ThinklabInappropriateOperationException 
	 */
	public abstract Object popVariable(String varname) throws ThinklabValidationException;
	
	/**
	 * 
	 * @param varname
	 * @return
	 */
	public abstract Object getVariable(String varname);

	/**
	 * Return a unique directory name for the session's workspace, in case applications request it. The directory  should be a simple identifier.
	 * @return
	 */
	public abstract String getSessionWorkspace();
	
	/**
	 * Based on the user preferences or rights, a conformance policy for object matching in
	 * queries, models and observations may be specified. If not,
	 * return a default conformance policy. Must never return null.
	 * 
	 * @return
	 */
	public abstract IConformance getConformancePolicy();

}
