package org.integratedmodelling.thinklab.api.knowledge;

import java.util.Map;

import org.integratedmodelling.thinklab.api.lang.IParseable;

public interface IExpression extends IParseable {

    public abstract Object eval(Map<String,Object> parameters);
    
}


