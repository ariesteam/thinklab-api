package org.integratedmodelling.thinklab.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Tag classes with this annotation to tell Thinklab to associate a particular concept with a 
 * Java class. Instances of this object are automatically conceptualizable by the knowledge
 * manager to a SemanticAnnotation. Any instances of the concept created from ontologies or the
 * API will automatically be associated to a new object of the type annotated.
 * 
 * Fields annotated with @Property will drive creation and conceptualization of linked 
 * beans. 
 * 
 * @author Ferd
 * @see org.integratedmodelling.thinklab.api.annotations.Property
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Concept {
	public String value(); 
}


