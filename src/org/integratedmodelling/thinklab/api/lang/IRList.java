package org.integratedmodelling.thinklab.api.lang;

/**
 * A ReferenceList is a list that allows setting, creating, resolving and 
 * returning forward references to other lists. This way it becomes capable of representing
 * cyclic graph structures with ease. It should serialize and parse properly,
 * most likely to something quite different from a normal s-expression.
 * 
 * The drawback is that it should be assumed to have lost any functional
 * behavior compared to IList: the result of all list operation may be the SAME list 
 * it's called on. So if you add another list to it, you should use 
 * ITS newList(...) constructor instead of PolyList.list(...) to create it, to
 * ensure that all references are preserved. It should still be usable
 * the same way as other ILists, with assignment operators and everything
 * behaving properly.
 * 
 * To create the first instance of a ReferenceList, use either new ReferenceList()
 * or ReferenceList.list(...).
 * 
 * @author Ferd
 *
 */
public interface IRList extends IList {

	/**
	 * Get a reference object that shares this list's references and will be
	 * resolved later by calling resolve() on it. Not resolving it will
	 * cause a runtime unresolved reference exception when the object
	 * is used.
	 * 
	 * @return
	 */
	public Object getForwardReference();

	/**
	 * Resolve a previously generated forward reference by passing the
	 * object it refers to.
	 * 
	 * @param list
	 * @return
	 */
	public void resolve(Object reference, Object list);	

	
}
