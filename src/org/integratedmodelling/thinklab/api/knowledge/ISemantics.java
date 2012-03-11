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
 * FIXIT keeping the Object and Relationship semantics in one object is confusing. We should
 * separate them and let implementations join them in one class if they so desire.
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

	/**
	 * Return whether this object represents a literal relationship, i.e. 
	 * we can get an object using getTargetSemantics().getLiteral()
	 * @return
	 */
	public abstract boolean isLiteral();

	public abstract Collection<ISemantics> getRelationships();

	public abstract Collection<ISemantics> getRelationships(IProperty property)
			throws ThinklabException;

	public abstract ISemantics getValue(IProperty property)
			throws ThinklabException;

	public abstract ISemantics getTargetSemantics();

	public abstract Object getLiteral();

	public abstract ISemanticObject getTarget();

	public abstract List<ISemantics> getValues(IProperty property)
			throws ThinklabException;

	/**
	 * FIXME put this one in a separate IListSerializable or something that
	 * classes have the freedom to implement. Then we can write adapters to
	 * JSON, XML etc. 
	 * 
	 * (IListAdapter -> fromList(), toList(), print(outputstream), read(inputstream)
	 * 
	 * @return
	 */
	public abstract IList asList();

}