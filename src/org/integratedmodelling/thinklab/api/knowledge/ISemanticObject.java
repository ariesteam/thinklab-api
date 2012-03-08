package org.integratedmodelling.thinklab.api.knowledge;

import org.integratedmodelling.lang.SemanticAnnotation;

public interface ISemanticObject {

	public abstract SemanticAnnotation getSemantics();
	
	public abstract Object getObject();
	
	/*
	 * TODO add all other IInstance methods
	 */
	
	/*
	 * TODO add optional semantic validation - which may use the reasoner in implementations 
	 */
}
