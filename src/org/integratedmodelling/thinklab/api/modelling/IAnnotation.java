package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

/**
 * Semantic annotations are essentially models whose result observation is
 * entirely specified, i.e. they must be instances of IState. Conceptualized to
 * instances and stored in kboxes, such observations are used to contextually
 * resolve open observables in models. Any model-specific implementation (e.g.
 * measurement, classification etc) can be stored with the annotation, allowing
 * multiple implementations of the same datasources.
 * 
 * An annotation specification is usually isomorphic to a model, but is used to
 * produce producing one or more IState. If >1 states are produced by the
 * annotation, they will be stored as a dataset and turned automatically into a
 * merged observation when the observables match an unresolved reference.
 * Compared to a model definition, the definition of an annotation should accept
 * annotation-specific parameters and should not have dependencies or code that
 * depends on a context. Also, any extent specification that pertains to the
 * state must be explicit.
 * 
 * @author Ferdinando
 * 
 */
public interface IAnnotation extends IModelObject {

	public Collection<IState> getStates();

}
