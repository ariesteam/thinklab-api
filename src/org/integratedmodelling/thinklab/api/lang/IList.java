package org.integratedmodelling.thinklab.api.lang;

import java.util.List;

/**
 * Open linked list, LISP-style. Immutable and general, not generic. Contains a 
 * simple mechanism to tag lists as being referenced in more than one place with a
 * retrievable ID, so that circular containment relationships can be handled.
 * 
 * @author Ferd
 *
 */
public interface IList extends Iterable<Object> {

	/**
	 *  isEmpty() tells whether the list is empty.
	 */
	public abstract boolean isEmpty();
	
	/**
	 * Return a new list with an object appended to it.
	 * 
	 * @param o
	 * @return
	 */
	public abstract IList append(Object o);

	/**
	 *  first() returns the first element of a non-empty Polylist.
	 * @exception NullPointerException Can't take first of an empty Polylist.
	 *
	 */
	public abstract Object first();

	/**
	 * Returns a nicely indented string representation of the list.
	 * 
	 * @return
	 */
	public abstract String prettyPrint();

	/**
	 *  rest() returns the rest of a non-empty Polylist.
	 * @exception NullPointerException Can't take rest of an empty Polylist.
	 */
	public abstract IList rest();

	/**
	 * cons returns a new Polylist given a First and this as a Rest
	 */
	public abstract IList cons(Object First);

	/**
	 *  return the length of this list
	 */
	public abstract int length();

	/**
	 *  reverse() returns the reverse of this
	 */
	public abstract IList reverse();

	/**
	 *  contains(A) tells whether A is a member of this
	 */
	public abstract boolean contains(Object A);

	/**
	 *  nth selects list item by index (0, 1, 2, ...).
	 *
	 */
	public abstract Object nth(long n);

	/**
	 * array() returns an array of the elements in list
	 */
	public abstract Object[] toArray();

	/**
	 * Return a List of the object in the list.
	 * 
	 * @return
	 */
	public abstract List<Object> toCollection();

	/**
	 * Check whether the list has been tagged as being referenced somewhere
	 * else. If this returns true, getReferenceId() can be used to retrieve
	 * the user-defined reference ID. Creation of referenced lists is left
	 * to implementations.
	 * 
	 * @return
	 */
	boolean isReference();

	/**
	 * If isReference() returns true, this can be used to retrieve a unique ID. How
	 * that is created depends on the implementation. 
	 *  
	 * @return
	 */
	long getReferenceId();

}