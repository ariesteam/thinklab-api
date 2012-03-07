package org.integratedmodelling.thinklab.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Associate a Java class with an object that can be represented as the target of
 * a data property. In translation OWL->Java, the class is used when the associated 
 * datatype is found. In Java->OWL, the object itself (if built-in) or its string
 * value (must be IParseable) are used to fill in the target property value.
 * 
 * Implementations should provide annotations for all common
 * XSD types as well as the desired custom ones. 
 * 
 * @author Ferd
 * @see org.integratedmodelling.thinklab.api.annotations.Concept
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Literal {
	/**
	 * Datatype should be a full XSD URI for built-ins, or the usual thinklab syntax (e.g. geospace:point) for
	 * user-defined datatypes.
	 * 
	 * @return
	 */
	public String datatype();
	
	/**
	 * Tells the system to use the annotated class to create ILiterals for objects of the
	 * passed class.
	 * 
	 * @return
	 */
	public Class<?> javaClass();
}
