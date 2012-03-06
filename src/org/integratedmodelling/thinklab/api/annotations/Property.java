package org.integratedmodelling.thinklab.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Associate a field with the property that will be used to conceptualize that object and to 
 * translate semantic annotations into it. Collections and arrays are automatically linked to
 * multiple properties. 
 * 
 * @author Ferd
 * @see org.integratedmodelling.thinklab.api.annotations.Concept
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
	public String value(); 
}


