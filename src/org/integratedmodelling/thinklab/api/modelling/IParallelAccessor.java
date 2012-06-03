package org.integratedmodelling.thinklab.api.modelling;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * @author  Ferd
 */
public interface IParallelAccessor extends IAccessor, IComputingAccessor {

	/**
	 * This kind of accessor can be asked to compute more than one state that must be returned to
	 * the context (and from process()). The expected observables for these states will be
	 * passed using this callback before process() is called.
	 * 
	 * @param observable
	 */
	public void notifyExpectedObservable(ISemanticObject<?> observable);
	
	/**
	 * Compute anything the accessor computes, and return all outputs in a list. Expected to also insert 
	 * the outputs in the context, which should be have been to the accessor's constructor by the observer that
	 * creates it.
	 * 
	 * @return
	 */
	public Collection<IState> process() throws ThinklabException;
	
}
