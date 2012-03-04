package org.integratedmodelling.thinklab.api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Tag ISemanticAdapter classes with this annotation to tell Thinklab to associate a particular class of
 * objects with it. 
 * 
 * @author Ferd
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SemanticAdapter {
	public String classId(); 
}


