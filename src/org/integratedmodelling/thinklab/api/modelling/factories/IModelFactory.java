package org.integratedmodelling.thinklab.api.modelling.factories;

import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.lang.model.Namespace;
import org.integratedmodelling.thinklab.api.modelling.IModelObject;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.modelling.units.IUnit;

/**
 * Internal interface used by interpreters to create model objects.
 * 
 * @author Ferd
 */
public interface IModelFactory {

	/**
	 * The main entry point to translate the result of parsing any language into usable model
	 * objects. The namespace passes is a bean defined in the API. It may already exist if we are
	 * allowing snippets of code from user input or embedded code, so be aware of that. All rules
	 * about namespace duplication have already been dealt with in the parser, so the input is
	 * OK.
	 * 
	 * This will be called for each incremental change in the namespaces and must result in the
	 * appropriate updates to the model map.
	 * 
	 * @param namespace
	 * @return
	 */
	public abstract void processNamespace(Namespace namespace);

	/**
	 * Copy the model object passed into another one. Used to convert independent
	 * implementations into one another. Typical use is to take the syntactic
	 * constructs built by the language parser and convert them into models for
	 * the target implementation. This gives the language parser a way to build
	 * conformant objects that can be reused for actual work without devising
	 * extremely complex constructors.
	 * 
	 * Must perform a deep copy and return completely functional objects.
	 * 
	 * @param o the object to copy
	 * @param namespace if not null, use this namespace for the resulting objects instead
	 * 	      of the original one.
	 * @return a new object in the target implementation for this factory.
	 */
	public abstract IModelObject clone(IModelObject o, INamespace namespace);
	
	/**
	 * Gateway to whatever unit parser we use. 
	 * 
	 * @param unit
	 * @return
	 * @throws ThinklabValidationException
	 */
	public abstract IUnit parseUnit(String unit) throws ThinklabValidationException;
}
