package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.modelling.IDataSource;
import org.integratedmodelling.thinklab.api.modelling.IExtent;
import org.integratedmodelling.thinklab.api.modelling.IModel;

public interface IModelDefinition extends IObservingObjectDefinition, IModel {

	public void addObserver(IObserverDefinition observer, IExpressionDefinition expression);
		
	public void setDataSource(IDataSource datasource);
	
	/**
	 * Function passed in the observable list. May return a datasource or be a shorthand
	 * to create an ugly instance of something, e.g. uncertainty for a concept. Interpretation
	 * and validation are left to the model's initialization method.
	 * 
	 * @param function a function returning something to observe. Should be validated for
	 * 		  appropriateness in the models' initialize() method.
	 * 
	 * @param formalName an optional name for an observable, which may be used to 
	 * 	      label a state in a result dataset or to refer to states of the observable
	 * 	      in previously computed contexts. May be null and will often be.
	 */
	public void setObservableFunction(IFunctionCall function, String formalName);
	
	public void setInlineState(Object state);
	
	/**
	 * Add any extent outside of which the model should not be applied. If this
	 * is done more than once for an extent of the same type, the extents should be
	 * merged. Exceptions may result from the merging.
	 * 
	 * @param extent
	 */
	public void addCoveredExtent(IExtent extent) throws ThinklabException;

}
