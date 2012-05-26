package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.thinklab.api.modelling.IDataSource;
import org.integratedmodelling.thinklab.api.modelling.IModel;

public interface IModelDefinition extends IObservingObjectDefinition, IModel {

	public void addObserver(IObserverDefinition observer, IExpressionDefinition expression);
	
	public void setDataSource(IDataSource datasource);
	
	public void setDatasourceGeneratorFunction(IFunctionDefinition function);
	
	public void setInlineState(Object state);

}
