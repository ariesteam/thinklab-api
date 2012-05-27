package org.integratedmodelling.thinklab.api.modelling.parsing;

import java.util.Map;

import org.integratedmodelling.thinklab.api.annotations.Concept;

public interface IFunctionDefinition extends ILanguageDefinition {

	public void set(String id, Map<String,Object> parameters);
	
	public String getId();
	
	public Map<String,Object> getParameters();
	
}
