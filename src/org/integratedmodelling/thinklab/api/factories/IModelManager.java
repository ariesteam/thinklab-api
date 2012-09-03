package org.integratedmodelling.thinklab.api.factories;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabIOException;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.modelling.IContext;
import org.integratedmodelling.thinklab.api.modelling.IModel;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
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

	/**
	 * Create an observation of an object. In order to use this one, the object is first 
	 * annotated to a ISemanticObject, which must not be a literal because literals can only
	 * be contingencies in other ISubjects.
	 * 
	 * @param object
	 * @return
	 * @throws ThinklabException
	 */
	public ISubject observe(Object observable) throws ThinklabException;

	/**
	 * Observe an object whose observation will be set as a contingency of the passed
	 * ISubject, linked to it by a relationship incarnating the passed property. If a 
	 * property is not passed, a specific 'hasXXX' one will be added to the namespace
	 * of the subject and used.
     *
	 * The observable decides whether the target of the new contingency is a IState or
	 * a ISubject. The property must match the data/object nature of the observable if
	 * it is passed.
	 * 
	 * The distribute parameter only applies when the observable determines an object
	 * property linking to ISubject. If so, distribute=true will create relationships
	 * to one ISubject per state of the abstract extents observed for the observable;
	 * otherwise only one ISubject with the same extents will be created.
	 * 
	 * @param object
	 * @param context
	 * @param property
	 * @param distribute
	 * @return
	 * @throws ThinklabException
	 */
	public ISubject observe(Object observable, ISubject context, 
							IProperty property, boolean distribute) 
					throws ThinklabException;
	
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
	public abstract IContext getCoverage(IModel model);

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