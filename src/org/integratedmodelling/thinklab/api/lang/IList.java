package org.integratedmodelling.thinklab.api.lang;

import java.util.List;

/**
 * 
 * @author Ferd
 *
 */
public interface IList {

	/**
	 *  isEmpty() tells whether the list is empty.
	 */
	public abstract boolean isEmpty();
	
	public abstract IList append(Object o);

	/**
	 *  first() returns the first element of a non-empty Polylist.
	 * @exception NullPointerException Can't take first of an empty Polylist.
	 *
	 */
	public abstract Object first();

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
	 *  reverse(L) returns the reverse of this
	 */

	public abstract IList reverse();

	/**
	 *  member(A, L) tells whether A is a member of this
	 */

	public abstract boolean member(Object A);

	/**
	 *  nth selects list item by index (0, 1, 2, ...).
	 * @exception NullPointerException Can't select from an empty Polylist.
	 */

	public abstract Object nth(long n);

	/**
	 * array() returns an array of elements in list
	 */

	public abstract Object[] array();

	/**
	 * implode() creates a String from a Polylist of items
	 */

	public abstract String implode();

	public abstract List<Object> toCollection();

}