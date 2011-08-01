package org.integratedmodelling.thinklab.api.modelling.classification;

import org.integratedmodelling.thinklab.api.lang.IParseable;

public interface IClassifier extends IParseable {

	public boolean classify(Object o);

	boolean isUniversal();

	boolean isNil();

	boolean isInterval();
}
