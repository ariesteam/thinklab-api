package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IDataSource;
import org.integratedmodelling.thinklab.api.modelling.IModel;

public interface IModelDefinition extends IObservingObjectDefinition, IModel {

	public void addObserver(IObserverDefinition observer, IExpressionDefinition expression);
	
	public void setDataSource(IDataSource datasource);
	
	public void setDatasourceGeneratorFunction(IFunctionDefinition function);
	
	public void setInlineState(Object state);

	/**
	 * This should be called after setting the observable (if any) or the other subjects (datasource
	 * or inline state) and the observer(s). After this is called, getObservable() is assumed to return a
	 * proper non-null value.
	 */
	public void defineObservable();
}
