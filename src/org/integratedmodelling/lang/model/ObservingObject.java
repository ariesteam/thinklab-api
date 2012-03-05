package org.integratedmodelling.lang.model;

import java.util.ArrayList;

import org.integratedmodelling.collections.Triple;
import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * Models and Observers. They both have observables, which are complicated enough to handle
 * to deserve being handled once.
 * 
 * @author Ferd
 *
 */
public abstract class ObservingObject extends ModelObject {
	
	ArrayList<Triple<Model, String, Boolean>> _dependencies = 
			new ArrayList<Triple<Model,String, Boolean>>();
	
	ArrayList<IList> observables = new ArrayList<IList>();

	public void addObservable(IList instance) {
		observables.add(instance);
	}

	public String getObservableConcept() {
		return observables.get(0).first().toString();
	}

	public IList getObservable() {
		return observables.get(0);
	}

	/**
	 * The dependencies of a Model - used to select the contingencies if "when" statements
	 * are given.
	 * 
	 * @param cmodel
	 * @param formalName the 'named or :as' specification, null if not given.
	 */
	protected void addDependency(Model cmodel, String formalName, boolean required) {
		_dependencies.add(new Triple<Model, String, Boolean>(cmodel, formalName, required));
	}
	

	@Override
	public String getQualifiedName() {
		return getNamespace().getId() + "/" + getId();
	}

}
