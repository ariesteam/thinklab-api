package org.integratedmodelling.lang.model;

import java.util.Map;

import org.integratedmodelling.thinklab.api.knowledge.IExpression;

/**
 * An observation pairs a datasource with an observer. The datasource may provide
 * additional context for it even if a context hasn't been specified.
 * 
 * @author Ferd
 *
 */
public class Observation extends ModelObject {

	/*
	 * the observer. It may be null, in which case there must be a function that
	 * should generate a full observation.
	 * 
	 */
	Observer observer;
	
	/*
	 * if not null, the datasource will be the result of evaluating the
	 * expression with the given parameters. Alternative to inlineState.
	 * 
	 * NOTE: if the observer is null, the function is expected to
	 * generate an observation instead of a datasource.
	 */
	IExpression dataSourceGenerator;
	
	/*
	 * if dataSourceGenerator isn't null, this should contain the
	 * parameters to the expression that generates a datasource.
	 */
	Map<String, Object> dataSourceGeneratorParameters;
	/*
	 * if not null, this is the state specified in the host language. Typically
	 * a number or other POD, but the details depend on the implementation.
	 * alternative to dataSourceGenerator.
	 */
	Object      inlineState;
	
	public void setDataSourceGenerator(IExpression expr, Map<String, Object> parameters) {
		this.dataSourceGenerator = expr;
		this.dataSourceGeneratorParameters = parameters;
	}
	
	public void setInlineState(Object state) {
		this.inlineState = state;
	}
	
	@Override
	public String getQualifiedName() {
		return getNamespace().getId() + "/" + getId();
	}
	
	public void setObserver(Observer o) {
		this.observer = o;
	}
	
}
