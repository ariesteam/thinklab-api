package org.integratedmodelling.thinklab.api.modelling.compiler;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.modelling.IContext;
import org.integratedmodelling.thinklab.api.modelling.IObservation;

public interface ICompiler {

	public IContextualizer compile(IObservation observation, IContext context, int index) throws ThinklabException;
}
