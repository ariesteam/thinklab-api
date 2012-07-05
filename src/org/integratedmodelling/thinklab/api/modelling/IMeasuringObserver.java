package org.integratedmodelling.thinklab.api.modelling;


public interface IMeasuringObserver extends IObserver {

	public abstract IUnit getUnit();

	/**
	 * If states are expressed as discrete distribution, we have a classification that
	 * defines the discretization.
	 * 
	 * @return
	 */
	public abstract IClassification getDiscretization();
}
