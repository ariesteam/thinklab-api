package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IClassification;
import org.integratedmodelling.thinklab.api.modelling.IClassifier;
import org.integratedmodelling.thinklab.api.modelling.IObserver;

/**
 * Augments IClassification with setting methods for parsers and languages to use.
 * 
 * @author Ferd
 *
 */
public interface IClassificationDefinition extends ILanguageDefinition, IClassification {

	/**
	 * Notify the observer that is using this classification. This helps us decide
	 * what role the classification will have and validate the classifiers
	 * accordingly. If the observer is a mediator, the mediated observer should
	 * be already defined when this is called.
	 * 
	 * @param observer
	 */
	public void notifyObserver(IObserver observer);
	
	/**
	 * Set the main concept space. It may not be called at all (TODO check
	 * if that is still the case).
	 * 
	 * @param concept
	 * @param typeHint
	 */
	public void setConceptSpace(IConceptDefinition concept, Type typeHint);
	
	/**
	 * Add a classifier. Called in order of definition.
	 * 
	 * @param concept
	 * @param classifier
	 */
	public void addClassifier(IConceptDefinition concept, IClassifier classifier);
	
	/**
	 * If we can determine from outside the classification what the type could be, 
	 * we can submit that here.
	 * 
	 * @param type
	 */
	public void setTypeHint(Type type);
	
	/**
	 * Called as last thing in the definition process, when all other definitions 
	 * and notifications have happened.
	 * 
	 */
	public void initialize();

}
