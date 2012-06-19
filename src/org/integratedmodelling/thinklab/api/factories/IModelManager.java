package org.integratedmodelling.thinklab.api.factories;

import java.io.File;
import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabIOException;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;
import org.integratedmodelling.thinklab.api.modelling.IAgentModel;
import org.integratedmodelling.thinklab.api.modelling.IContext;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.IModelObject;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.modelling.IObservation;
import org.integratedmodelling.thinklab.api.modelling.IScenario;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * The model factory contains the register of all model objects and namespaces. It's capable of
 * reconstructing dependency structure and source code of all objects. It can load model definitions
 * from resources and release objects on a namespace basis.
 * 
 * The paradigm "one resource, one namespace" is mandatory and enforced.
 * 
 * @author Ferdinando
 *
 */
public interface IModelManager {

	
	/**
	 * The key function in the modeling system is observing something in a context. The passed
	 * object may be a model (which is going to be contextualized and run) or something else,
	 * for which Thinklab will try to build the appropriate model for the context. If successful,
	 * an observation of the object will be returned, whose context will link to all other 
	 * contingent observations made in the process.
	 * 
	 * @param object
	 * @param context
	 * @return
	 */
	public IObservation observe(Object object, IContext context) throws ThinklabException;
	
	public abstract IModel getModel(String s);
	
	public abstract IAgentModel getAgentModel(String s);

	public abstract IScenario getScenario(String s);

	public abstract IContext getContext(String s);

	public abstract INamespace getNamespace(String ns);

	public abstract void releaseNamespace(String namespace);

	public abstract IModelObject getModelObject(String object);

	public abstract String getSource(String object);
	
	public Collection<IModelObject> getDependencies(String object);
	
	public Collection<INamespace> getNamespaces();
		
	/**
	 * Return a context that describes the coverage of a particular
	 * model in a given kbox along all applicable dimensions. 
	 * It will be the union of the contexts of
	 * all the realized models found in that kbox. 
	 *
	 * @param model
	 * @return
	 */
	public abstract IContext getCoverage(IModel model);

	/**
	 * Get all scenarios that apply to the passed model.
	 * 
	 * @param model
	 * @return
	 * @throws ThinklabException 
	 */
	public abstract Collection<IScenario> getApplicableScenarios(IModel model,
			IContext context, boolean isPublic) throws ThinklabException;

	/**
	 * Load all model objects defined in the given file, adding them to the model map.
	 *  
	 * @param resourceId
	 * @param namespaceId 
	 * @param the project to look up resource and imports (may be null)
	 * @return the namespace defined
	 * @throws ThinklabIOException 
	 */
	public abstract INamespace loadFile(final String resourceId, final String namespaceId, final IProject project)
			throws ThinklabException;

	
	/**
	 * Load all model objects defined in the project's source directories, 
	 * adding each found resource to the model map.
	 *  
	 * @param resourceId
	 * @return the namespaces defined
	 * @throws ThinklabIOException 
	 */
	public abstract Collection<INamespace> load(final IProject project)
			throws ThinklabException;

	/**
	 * Syntactic functions are used throughout the system to encapsulate creation of various
	 * elements, like predefined observations or datasources. They must resolve to expressions
	 * before they are used. This function returns an expression for the given function and
	 * parameter names, or null.
	 * 
	 * @param functionId
	 * @param parameterNames
	 * @return
	 */
	public IExpression resolveFunction(String functionId, Collection<String> parameterNames);

	/**
	 * Load a whole source directory recursively, attributing namespaces based on 
	 * source file and subdirectory names. Return all the namespaces defined.
	 * 
	 * @param sourcedir
	 * @return
	 * @throws ThinklabException
	 */
	Collection<INamespace> loadSourceDirectory(File sourcedir, IProject project) throws ThinklabException;

}