package org.integratedmodelling.thinklab.api.lang.parsing;

import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.modelling.IObservation;
import org.integratedmodelling.thinklab.api.modelling.IObserver;

public interface IObservationDefinition extends IModelObjectDefinition, IObservation {

	public void setObservable(IList semantics);
	
	public void setObserver(IObserver observer);
	
	public void setDataSource(IDataSourceDefinition datasource);
}
