package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IConceptualizable;
import org.integratedmodelling.thinklab.api.knowledge.storage.IKBox;
import org.integratedmodelling.thinklab.api.runtime.ISession;

/**
 * The most high-level notion in Thinklab. Essentially a query that returns 
 * observations of a concept given a kbox and a session. It is nothing but a set
 * of axioms, and it should be serializable to an appropriately restricted
 * observation class; it is here represented as a Java class for practical
 * reasons (of efficiency, storage and cleanup of unneeded axioms); make it a
 * IConceptualizable to implement the behavior if needed, it's likely to be
 * unneeded overhead for now.
 * 
 * The Java side is usable as is but the whole model definition machinery is
 * meant to be used from Clojure, which provides an elegant and compact syntax for
 * model specification. See the examples/ folder in the plugin directory.
 * 
 * More docs will come or I'm not a real academic...
 * 
 * @author Ferdinando Villa
 * @date Jan 25th, 2008.
 * 
 */
public interface IModel extends IModelObject {
	
	
	/**
	 * Return the base observable concept
	 * 
	 * @return
	 */
	public abstract IConcept getObservableClass();
	

	/**
	 * 
	 * @param kbox
	 * @param session
	 * @param context
	 * @return
	 * @throws ThinklabException
	 */
	public IObservationIterator observe(IKBox kbox, ISession session, IContext context) throws ThinklabException;

	/**
	 * Train the model to match any specified output observation (in the :observed
	 * clause, if any). Not all models may be trainable. Returns a new trained model
	 * that has learned to reproduce the models observed on the passed kbox.
	 * 
	 * @param kbox
	 * @param session
	 * @param params
	 * @return
	 * @throws ThinklabException
	 */
	public IModel train(IKBox kbox, ISession session,  IContext context) throws ThinklabException;

	/**
	 * A scenario is a model modifier, containing alternative models for given observables.
	 * Applying the scenario substitutes any models of the same observables with those
	 * in the scenario, going as deep as needed in the dependency chain.
	 * 
	 * @param scenario
	 * @return
	 * @throws ThinklabException
	 */
	public IModel applyScenario(IScenario scenario) throws ThinklabException;

	/**
	 * Get the models that this one depend upon. Return an empty collection, not null, if
	 * none exist.
	 * @return
	 */
	public abstract Collection<IModel> getDependencies();
}