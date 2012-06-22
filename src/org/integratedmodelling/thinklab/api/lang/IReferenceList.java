package org.integratedmodelling.thinklab.api.lang;

/**
 * A ReferenceList is a list that allows setting, creating, resolving and 
 * returning forward references to other lists. This way it becomes capable of representing
 * cyclic graph structures with ease. It should serialize and print properly,
 * most likely to something quite different from a normal s-expression.
 * 
 * The drawback is that it should be assumed to have lost any functional
 * behavior compared to IList: the result of any list operation is the SAME list 
 * it's called on. It is meant to be API-compatible with other ILists, with 
 * assignment operators and everything behaving properly.
 * 
 * To create the first instance of a ReferenceList, use either new ReferenceList()
 * or ReferenceList.list(...). All modification functions modify the list to avoid
 * losing references. E.g., append returns the same list with things added to it.
 * The assign(IList) function returns the same list after setting its contents to
 * those of the passed list.
 * 
 * The ReferenceList must implement hashCode and equals() properly so that
 * it can be used in a reference set by any recursive algorithm, preventing 
 * infinite loops.
 * 
 * @author Ferd
 *
 */
public interface IReferenceList extends IList {

	/**
	 * Get a reference to this list. Do not resolve() it for any
	 * reason.
	 * 
	 * @return
	 */
	public IReferenceList getReference();
	
	/**
	 * Get a reference list that shares this list's references and will be
	 * resolved later by calling resolve() on it. Not resolving it will
	 * cause a runtime unresolved reference exception when the object
	 * is used.
	 * 
	 * @return
	 */
	public IReferenceList getForwardReference();

	/**
	 * Resolve a previously generated forward reference by passing the
	 * list it refers to.
	 * 
	 * @param list
	 * @return
	 */
	public IList resolve(IList list);
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public IReferenceList assign(IList list);

	/**
	 * Each reference list has an ID that is guaranteed equal for all 
	 * references to the same object and different for all references to
	 * different objects. Exposed because it comes in handy to implement hashCode and equals
	 * for objects created from a ref list.
	 * 
	 * @return
	 */
	long getId();
	
}
