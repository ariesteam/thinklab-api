package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.thinklab.api.lang.IParseable;

public interface IUnit extends IParseable {

	/**
	 * Convert the given value from the passed unit to the unit we
	 * represent.
	 * 
	 * @param value
	 * @param unit
	 * @return
	 */
	public abstract double convert(double value, IUnit unit);

	public abstract boolean isRate();

	public abstract IUnit getTimeExtentUnit();

	public abstract boolean isLengthDensity();

	public abstract IUnit getLengthExtentUnit();

	public abstract boolean isArealDensity();

	/**
	 * If the unit represents an areal density, return the area term with 
	 * inverted exponents - e.g. if we are something per square meter, return
	 * square meters. If not an areal density, return null.
	 * 
	 * @return
	 */
	public abstract IUnit getArealExtentUnit();

	public abstract boolean isVolumeDensity();

	public abstract IUnit getVolumeExtentUnit();

	public abstract boolean isUnitless();
}
