package org.integratedmodelling.thinklab.api.modelling.observation;

public interface ITopology<T> extends ITopologicallyComparable<T> {

	public int getMultiplicity();
	
	public T intersection(T other);
	
	public T union(T other);
	
}
