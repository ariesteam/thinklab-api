package org.integratedmodelling.thinklab.api.lang;

/**
 * A ReferenceList is a list that allows setting, resolving and 
 * returning forward references to other lists. This way it's capable of representing
 * cyclic graph structures with ease. It should serialize and parse properly,
 * most likely to something quite different from a normal s-expression.
 * 
 * The drawback is that it should be assumed to have lost any functional
 * idiom compared to IList: the result of all list operation may be the SAME list 
 * it's called on. So if you add another list to it, you should use 
 * ITS list(...) constructor instead of PolyList.list(...) to create it, to
 * ensure that all references are preserved.
 * 
 * @author Ferd
 *
 */
public interface IReferenceList extends IList {

	public static interface Reference {
		public IList getList();
	}
	
	public Reference getForwardReference();
	
	public IList resolveReference(Reference ref, IList list);
	
	public IList getList(Reference ref);
	
	public IReferenceList list(Object ... objects);
	
}
