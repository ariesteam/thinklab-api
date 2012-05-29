package org.integratedmodelling.thinklab.api.modelling.parsing;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.modelling.IDataSource;
import org.integratedmodelling.thinklab.api.modelling.IExtent;
import org.integratedmodelling.thinklab.api.modelling.IModel;

public interface IModelDefinition extends IObservingObjectDefinition, IModel {

	public void addObserver(IObserverDefinition observer, IExpressionDefinition expression);
	
	public void setDataSource(IDataSource datasource);
	
	public void setDatasourceGeneratorFunction(IFunctionDefinition function);
	
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
