package org.integratedmodelling.thinklab.api.modelling.factories;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabIOException;
import org.integratedmodelling.thinklab.api.knowledge.IOntology;
import org.integratedmodelling.thinklab.api.knowledge.storage.IKBox;
import org.integratedmodelling.thinklab.api.listeners.IContextualizationListener;
import org.integratedmodelling.thinklab.api.modelling.IAgentModel;
import org.integratedmodelling.thinklab.api.modelling.IAnnotation;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.IModelObject;
import org.integratedmodelling.thinklab.api.modelling.IScenario;
import org.integratedmodelling.thinklab.api.modelling.observation.IContext;
import org.integratedmodelling.thinklab.api.runtime.ISession;

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
public interface IModelFactory {

	public abstract IAnnotation retrieveAnnotation(String s);

	public abstract IAnnotation requireAnnotation(String s)
			throws ThinklabException;

	public abstract IModel retrieveModel(String s);

	public abstract IModel requireModel(String s) throws ThinklabException;
	
	public abstract IAgentModel retrieveAgentModel(String s);

	public abstract IAgentModel requireAgentModel(String s) throws ThinklabException;

	public abstract IScenario retrieveScenario(String s);

	public abstract IScenario requireScenario(String s) throws ThinklabException;

	public abstract IContext retrieveContext(String s);

	public abstract IContext requireContext(String s) throws ThinklabException;

	public abstract void releaseNamespace(String namespace);

	public String getSource(String object) throws ThinklabException;
	
	public Collection<IModelObject> getDependencies(String object) 
		throws ThinklabException;
	
	public long getNamespaceLastModification(String ns);
	
	public IOntology getNamespaceOntology(String ns);

	public Collection<String> getNamespaces();
	
	public Collection<IModelObject> listNamespace(String ns);

	public IModelObject getModelObject(String name) throws ThinklabException;
	
	/**
	 * Return a context that describes the coverage of a particular
	 * model in a given kbox along all applicable dimensions. 
	 * It will be the union of the contexts of
	 * all the realized models found in that kbox. 
	 *
	 * @param model
	 * @return
	 */
	public abstract IContext getCoverage(IModel model, IKBox kbox, ISession session);

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
	 * Query the model on the kbox, create the observation correspondent to the 0 match,
	 * contextualize it and return the resulting context.
	 * 
	 * @param model
	 * @param kbox
	 * @param session
	 * @param listeners
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IContext run(IModel model, IKBox kbox, ISession session,
			Collection<IContextualizationListener> listeners, IContext context)
			throws ThinklabException;

	/**
	 * Load all model objects defined in the given file, adding them to the model map.
	 *  
	 * @param resourceId
	 * @return the namespace defined
	 * @throws ThinklabIOException 
	 */
	public abstract String loadFile(final String resourceId)
			throws ThinklabException;

}