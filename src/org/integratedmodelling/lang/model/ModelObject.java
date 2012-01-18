package org.integratedmodelling.lang.model;

import java.io.PrintWriter;

public class ModelObject extends LanguageElement {
	
	Metadata metadata;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public void dump(PrintWriter out) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
