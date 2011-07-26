package org.integratedmodelling.thinklab.api.knowledge;

import java.util.Map;

import org.integratedmodelling.lang.IParseable;

public interface IExpression extends IValue, IParseable {

    public abstract Object eval(Map<String,Object> parameters);
    
	
}


