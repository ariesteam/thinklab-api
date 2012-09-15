package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;

/**
 * Anything that observes anything must implement this interface, normally
 * through another that extends it.
 * 
 * @author Ferd
 *
 */
public abstract interface IObservingObject extends IModelObject {

	public static interface IDependency {
		
		/**
		 * The object that we need to observe to satisfy this dependency.
		 * @return
		 */
		public Object getObservable();
		
		/**
		 * The name that the state of this object will be known as to the
		 * embedding model. Should never be null.
		 * 
		 * @return
		 */
		public String getFormalName();
		
		/**
		 * Whether we can do without this dependency or not.
		 * @return
		 */
		public boolean isOptional();
		
		/**
		 * The property that expresses the counterpart of this dependency in
		 * the conceptual model for the observable of the embedding model. May
		 * be null. If not, the dependency resolution strategy must honor
		 * the restrictions of the property in the context of each matched
		 * observable for the embedding model.
		 * 
		 * @return
		 */
		public IProperty getProperty();

		/**
		 * If true, the dependency should be for an agent that is distributed over the domain
		 * returned by running the model returned by getDistributionContext() in the context
		 * of observation.
		 * @return
		 */
		boolean isDistributed();
		
		/**
		 * Return whatever model was passed to contextualize the dependency (the 'at'
		 * part of the specification).
		 * 
		 * @return
		 */
		IModel getContextModel();
		
		/**
		 * The object, if any, which 
		 * @return
		 */
		Object getWhereCondition();
		
	}
	
	/**
	 * Dependency information using the structure above.
	 * 
	 * @return
	 */
	public List<IDependency> getDependencies();

	/**
	 * Given the observable for a dependency and that of an extent implemented, return
	 * whether the model has any actions that define the state of that dependency at 
	 * domain transitions for that domain concept. This will tell the API whether the
	 * dependency needs to be supplied in full or we only need to supply enough state 
	 * to initialize it.
	 *  
	 * @param observable
	 * @param domainConcept
	 * @return
	 */
	boolean hasActionsFor(IConcept observable, IConcept domainConcept);
}
