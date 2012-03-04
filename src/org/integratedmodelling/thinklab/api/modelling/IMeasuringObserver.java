package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.modelling.units.IUnit;

public interface IMeasuringObserver extends IObserver {

	public abstract IUnit getUnit();
}
