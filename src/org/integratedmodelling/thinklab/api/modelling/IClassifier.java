package org.integratedmodelling.thinklab.api.modelling;


public interface IClassifier  {

	public boolean classify(Object o);

	boolean isUniversal();

	boolean isNil();

	boolean isInterval();
	
	void negate();
}
