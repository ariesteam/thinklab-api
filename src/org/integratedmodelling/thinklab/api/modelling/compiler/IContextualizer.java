package org.integratedmodelling.thinklab.api.modelling.compiler;

import org.integratedmodelling.thinklab.api.modelling.IContext;

/**
 * This should be created with an observation chosen between the 
 * results of Model.observe() and the context of observation for the
 * latter. It takes care of mediating the contexts and computing all
 * states.
 * 
 * @author Ferd
 *
 */
public interface IContextualizer {

	public IContext run();
}
