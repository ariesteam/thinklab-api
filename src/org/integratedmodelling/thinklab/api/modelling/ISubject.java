package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;
import org.integratedmodelling.thinklab.api.lang.IMetadataHolder;

/**
 * ISubject is the equivalent of an individual in the Thinklab modeling platform. It 
 * represents an observed object, i.e. the individual that results from an observation
 * (either built in the language using an 'observe' statement, or returned by thinklab's 
 * observe operation). At the most general level, the ISubject is the "agent" of most
 * modeling framework, and may have behavior specified as actions to be linked to any
 * context transition.
 * 
 * A ISubject is a semantic object whose literal properties point to IState and whose
 * object properties point to other ISubjects. Being the result of observation it will
 * normally be located in time/space extents (themselves IStates), and methods are provided
 * to facilitate introspection of extents, other states, and other subjects that belong
 * to the subject.
 * 
 * Observing anything in Thinklab produces an ISubject. Any functional properties in
 * the semantics of the annotated object that is observed will be resolved against 
 * the knowledge base to ensure that the ISubject's semantics is valid.
 * 
 * An ISubject is the result of observation, therefore it must be valid during its 
 * full lifetime. It is not possible to create a partially specified ISubject.
 * 
 * Because Thinklab commits to OWL's open world assumption, it is possible to observe
 * things using a ISubject as the context. The result of the observation will be an
 * ISubject with enriched semantics. The observe(Object, ISubject) operation in the
 * model manager does that.
 * 
 * TODO add introspection methods to establish if it's agentive in any domain.
 * 
 * @author Ferd
 *
 */
public interface ISubject extends ISemanticObject<Object>, IMetadataHolder {
	
	/**
	 * Return all properties that point to IState (the data properties of 
	 * the semantic object that have been observed). getRelationships() can
	 * be used to access the states.
	 * 
	 * @return
	 */
	public Collection<IProperty> getStateProperties();
	
	/**
	 * Return all properties that point to ISubject (the object properties of 
	 * the semantic object that have been observed). getRelationships() can
	 * be used to access the states.
	 * 
	 * @return
	 */
	public Collection<IProperty> getSubjectProperties();

	/**
	 * Return all properties that point to IExtent (data properties of 
	 * the semantic object that have been observed pointing to abstract 
	 * regions). getRelationships() can be used to access the states.
	 * 
	 * @return
	 */
	public Collection<IProperty> getExtentProperties();
	
	/**
	 * Return the scale seen by this subject, merging all the extents declared
	 * for the subject in the observation context.
	 * 
	 * @return
	 */
	public IScale getScale();
	
	/**
	 * Observe the passed observable according to our point of view and return
	 * a new ISubject where the resulting state has been made part of the 
	 * object.
	 * 
	 * @param observable
	 * @return
	 */
	public ISubject observe(Object observable);
}
