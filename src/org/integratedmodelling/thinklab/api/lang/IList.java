package org.integratedmodelling.thinklab.api.lang;

import java.util.List;

/**
 * Open linked list, LISP-style. Immutable and general, not generic. Supports
 * the obvious basic operations.
 * 
 * Not meant to contain itself or lists that refer back to itself. I.e., it's
 * suboptimal and laborious to build a graph representation with this one. If
 * that's what you want, look at IReferenceList.
 * 
 * @author Ferd
 * @see IReferenceList
 */
public interface IList extends Iterable<Object> {

	/**
	 *  isEmpty() tells whether the list is empty.
	 */
	public abstract boolean isEmpty();
	
	/**
	 * Return a new list with the passed objects appended to it.
	 * 
	 * @param o
	 * @return
	 */
	public abstract IList append(Object ... o);

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
	
}