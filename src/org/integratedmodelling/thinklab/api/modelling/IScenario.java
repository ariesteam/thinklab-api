package org.integratedmodelling.thinklab.api.modelling;


/**
 * Scenarios are collections of two possible objects:
 * 
 * 	1. Models that are substituted to other models of compatible observables
 *     before contextualization;
 *  2. Transformations that are inserted in the chain of mediation after the
 *     existing model for a particular observable has computed each state.
 * 
 * @author Ferd
 *
 */
public interface IScenario extends IModelObject {

	public void merge(IScenario scenario);
	
}
