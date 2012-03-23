package org.integratedmodelling.thinklab.api.modelling.parsing;

import java.util.Map;

public interface IFunctionDefinition extends ILanguageDefinition {

	public void set(String id, Map<String,Object> parameters);
	
}
