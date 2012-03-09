package org.integratedmodelling.thinklab.api.knowledge;

import java.util.Collection;
import java.util.List;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * The actual semantic annotation for an object. It contains all the semantics of a Java object,
 * it can be created by the knowledge manager by conceptualizing one, and it can be fed to
 * KnowledgeManager.instantiate() to create a new object that reflects it. All the semantic
 * functions in Thinklab use the semantics to do their job. A ISemanticObject contains both
 * the object and its semantics.
 * 
 * @author Ferd
 * @see ISemanticObject
 * @see ISemanticLiteral
 * @see IKnowledgeManager.conceptualize()
 * @see IKnowledgeManager.instantiate()
 * @see IKnowledgeManager.annotate()
 */
public interface ISemantics {

	public abstract int getRelationshipsCount();

	public abstract int getRelationshipsCount(IProperty property)
			throws ThinklabException;

	public abstract String toString();

	public abstract IConcept getConcept();

	public abstract IProperty getProperty();

	public abstract boolean isLiteral();

	public abstract Collection<ISemantics> getRelationships();

	public abstract Collection<ISemantics> getRelationships(IProperty property)
			throws ThinklabException;

	public abstract ISemantics getValue(IProperty property)
			throws ThinklabException;

	public abstract ISemantics getTargetSemantics();

	public abstract Object getTargetLiteral();

	public abstract ISemanticObject getTarget();

	public abstract List<ISemantics> getValues(IProperty property)
			throws ThinklabException;
	
	public abstract IList asList();

}