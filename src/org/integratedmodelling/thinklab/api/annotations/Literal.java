package org.integratedmodelling.thinklab.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Associate a class with an object that can be represented as the target of
 * a data property. In translation OWL->Java, the class is used when the associated 
 * datatype is found. Implementations should provide annotations for all common
 * XSD types as well as the desired custom ones. 
 * 
 * @author Ferd
 * @see org.integratedmodelling.thinklab.api.annotations.Concept
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Literal {
	public String value();
}
