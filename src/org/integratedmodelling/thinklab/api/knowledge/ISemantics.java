//package org.integratedmodelling.thinklab.api.knowledge;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.integratedmodelling.exceptions.ThinklabException;
//import org.integratedmodelling.thinklab.api.lang.IList;
//
///**
// * The actual semantic annotation for an object. It contains all the semantics of a Java object,
// * it can be created by the knowledge manager by conceptualizing one, and it can be fed to
// * KnowledgeManager.instantiate() to create a new object that reflects it. All the semantic
// * functions in Thinklab use the semantics to do their job. A ISemanticObject contains both
// * the object and its semantics.
// *
// * Can be tagged as a reference the same way a IList can, to facilitate handling of
// * circular references. Printing functions are cognizant of that, but any recursive
// * processing should keep a list of referenceIds seen to avoid infinite recursion.
// *
// * The class merges the semantics of objects and relationships for ease of processing. It
// * can be confusing but the alternative has shown to be much worse.
// * 
// * @author Ferd
// * @see ISemanticObject
// * @see ISemanticLiteral
// * @see IKnowledgeManager.conceptualize()
// * @see IKnowledgeManager.instantiate()
// * @see IKnowledgeManager.annotate()
// */
//public interface ISemantics {
//
//
//	/**
//	 * true if this semantics represents an object. All main Thinklab functions that
//	 * return semantics will return something for which isObject() is true.
//	 * 
//	 * @return
//	 */
//	public abstract boolean isObject();
//	
//	/**
//	 * true if this object represents a relationship. Such objects are normally only
//	 * returned by a call to getRelationships([property]) on object semantics.
//	 * @return
//	 */
//	public abstract boolean isRelationship();
//	
//	/**
//	 * Should only be called if isObject() returns true. In general a processing
//	 * functions knows anyway, because processing always start from an object
//	 * semantics.
//	 * 
//	 * @return
//	 */
//	public abstract IConcept getConcept();
//
//	/**
//	 * Should only be called if isRelationship() returns true.
//	 * @return
//	 */
//	public abstract IProperty getProperty();
//
//	/**
//	 * Return whether this object represents a literal relationship, i.e. 
//	 * we can get a bare literal object using getTargetSemantics().getLiteral().
//	 * 
//	 * Should only be called if isRelationship() returns true.
//	 * 
//	 * @return
//	 */
//	public abstract boolean isLiteralRelationship();
//
//	/**
//	 * Get the total number of relationships. Should only be called if
//	 * isObject() returns true.
//	 * 
//	 * @return
//	 */
//	public abstract int getRelationshipsCount();
//
//	/**
//	 * Get the total number of relationships of the passed type. Should only be called if
//	 * isObject() returns true.
//	 * 
//	 * @return
//	 */
//	public abstract int getRelationshipsCount(IProperty property)
//			throws ThinklabException;
//
//	/**
//	 * Get the semantics of all relationships. Should only be called if
//	 * isObject() returns true.
//	 * 
//	 * @return
//	 */
//	public abstract Collection<ISemantics> getRelationships();
//
//	/**
//	 * Get the semantics for all relationships of the passed type.
//	 * 
//	 * Should only be called if isObject() returns true.
//	 * @return
//	 */
//	public abstract Collection<ISemantics> getRelationships(IProperty property)
//			throws ThinklabException;
//
//	/**
//	 * Should only be called if isObject() returns true.
//	 * @return
//	 */
//	public abstract ISemantics getValue(IProperty property)
//			throws ThinklabException;
//
//	/**
//	 * Should only be called if isRelationship() returns true.
//	 * @return
//	 */
//	public abstract ISemantics getTargetSemantics();
//
//	/**
//	 * Get the literal object we contain, or null if the semantics
//	 * we represent is not that of a literal.
//	 * 
//	 * Should only be called if isObject() returns true.
//	 * @return
//	 */
//	public abstract Object getLiteral();
//
//	/**
//	 * Should only be called if isRelationship() returns true.
//	 * @return
//	 */
//	public abstract ISemanticObject getTarget();
//
//	/**
//	 * Should only be called if isObject() returns true.
//	 * @return
//	 */
//	public abstract List<ISemantics> getValues(IProperty property)
//			throws ThinklabException;
//
//	public abstract boolean isReference();
//	
//	public abstract long getReferenceId();
//	
//	/**
//	 * FIXME put this one in a separate IListSerializable or something that
//	 * classes have the freedom to implement. Then we can write adapters to
//	 * JSON, XML etc. 
//	 * 
//	 * (IListAdapter -> fromList(), toList(), print(outputstream), read(inputstream)
//	 * 
//	 * @return
//	 */
//	public abstract IList asList();
//
//}