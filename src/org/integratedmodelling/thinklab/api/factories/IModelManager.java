package org.integratedmodelling.thinklab.api.factories;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabIOException;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.modelling.IScale;
import org.integratedmodelling.thinklab.api.modelling.IScenario;
import org.integratedmodelling.thinklab.api.modelling.ISubject;
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

//	/**
//	 * Create an observation of an object. In order to use this one, the object is first 
//	 * annotated to a ISemanticObject. The object must annotate to an endurant or perdurant
//	 * in order to produce an ISubject as an observation - it cannot annotate to a quality
//	 * as that needs a ISubject as a context.
//	 * 
//	 * Because an ISubject is normally distributed in time/space, this method will only
//	 * work when the observable is already an ISubject or when the model chosen is constrained
//	 * to a fully specified scale. The normal way to instantiate an ISubject is by using a
//	 * declared ISubjectGenerator.
//	 * 
//	 * A IModel or an ISubjectGenerator can be passed as a delegate to an observable. In that case, the observable
//	 * of the model will be used to provide the ISubject's semantics, and the model itself
//	 * will be used to observe it.
//	 * 
//	 * @param object
//	 * @return
//	 * @throws ThinklabException
//	 */
//	public ISubject observe(Object observable) throws ThinklabException;
//	
	
	public abstract INamespace getNamespace(String ns);

	public abstract void releaseNamespace(String namespace);

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
	public abstract IScale getCoverage(IModel model);

	/**
	 * Get all scenarios that apply to the passed model.
	 * 
	 * @param model
	 * @return
	 * @throws ThinklabException 
	 */
	public abstract Collection<IScenario> getScenarios(IModel model, ISubject context) throws ThinklabException;

	/**
	 * Load all model objects defined in the given file, adding them to the model map.
	 * TODO shouldn't be here? Or not name the NS? Accept a directory?
	 * 
	 * @param resourceId
	 * @param namespaceId 
	 * @param the project to look up resource and imports (may be null)
	 * @return the namespace defined
	 * @throws ThinklabIOException 
	 */
	public abstract INamespace loadFile(final String resourceId, final String namespaceId, final IProject project)
			throws ThinklabException;


}