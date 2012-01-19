package org.integratedmodelling.lang.model;

import java.io.PrintStream;

public class ModelObject extends LanguageElement {
	
	Metadata metadata;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public void dump(PrintStream out) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
