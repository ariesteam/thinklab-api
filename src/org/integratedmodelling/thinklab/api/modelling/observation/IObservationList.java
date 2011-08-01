package org.integratedmodelling.thinklab.api.modelling.observation;

import java.util.List;


/**
 * An IObservationIterator is returned by the observe() operation on a IModel.
 * It must be capable of producing each matching observation for an observation
 * of the model, and it is assumed that if applicable, the best fitting
 * observation will be at the top so that the first call to next() will produce
 * the best way to observe the model. The iterator must also be capable of
 * producing the total number of observation, and must behave in a lazy way so
 * that no observation (except possibly the first) is actually produced until
 * the call to next() is given. In an observation tree, what makes
 * size() return > 1 is multiple matches between unresolved observables
 * and states in the kbox. The iterator in the top observation must give access
 * to the combinatory set of all possible observations.
 * 
 * 
 * 
 * @author Ferdinando
 * 
 */
public interface IObservationList extends List<IObservation> {

}