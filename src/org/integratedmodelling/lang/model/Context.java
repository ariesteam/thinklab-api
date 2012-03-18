package org.integratedmodelling.lang.model;

import java.util.ArrayList;
import java.util.List;

public class Context extends ModelObject {

	ArrayList<Observation> observations = new ArrayList<Observation>();
	
	@Override
	public String getQualifiedName() {
		return getNamespace().getId() + "/" + getId();
	}

	public void addObservation(Observation o) {
		observations.add(o);
	}
	
	public List<Observation> getObservations() {
		return observations;
	}
}
