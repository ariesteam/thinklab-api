package org.integratedmodelling.thinklab.api.modelling.factories;

import java.io.File;
import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabIOException;
import org.integratedmodelling.thinklab.api.knowledge.storage.IKBox;
import org.integratedmodelling.thinklab.api.modelling.IAgentModel;
import org.integratedmodelling.thinklab.api.modelling.IAnnotation;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.IModelObject;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
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
public interface IModelManager {

	public abstract IAnnotation getAnnotation(String s);

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
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IContext run(IModel model, IKBox kbox, ISession session, IContext context)
			throws ThinklabException;

	/**
	 * Load all model objects defined in the given file, adding them to the model map.
	 *  
	 * @param resourceId
	 * @return the namespace defined
	 * @throws ThinklabIOException 
	 */
	public abstract INamespace loadFile(final String resourceId)
			throws ThinklabException;
	
	/**
	 * Load all model objects defined in the given source directory, 
	 * adding each found resource to the model map.
	 *  
	 * @param resourceId
	 * @return the namespaces defined
	 * @throws ThinklabIOException 
	 */
	public abstract Collection<INamespace> load(final File sourceDirectory)
			throws ThinklabException;

}