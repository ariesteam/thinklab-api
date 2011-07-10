package org.integratedmodelling.thinklab.api.modelling;

/**
 * An IAgentModel is defined in the specification language as the model for a thinklab agent. It is to
 * a IAgent what a IModel is to a IObservation. Agents can contain one or more models (their view of
 * the world) and will use them to observe the world at each change, building their context (the observed
 * world). 
 * 
 * @author Ferdinando
 *
 */
public interface IAgentModel extends IModelObject {

}
