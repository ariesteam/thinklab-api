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
	 * A special object to put in a list to reference another. Enables us to
	 * construct linearized serializations of graphs. If a list member is
	 * a IList.Ref, we can ask it to return the corresponding IList, with
	 * the understanding that we will run into trouble if we process it
	 * recursively without counting references.
	 * 
	 * Refs are always fully hashable and comparable, and can be 
	 * serialized and parsed from list representations.
	 * 
	 * @author Ferd
	 *
	 */
	public interface Ref {
		
		/**
		 * Get the list this Ref refs to.
		 * @return
		 */
		public abstract IList get();
	}


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
	 * Get a new Ref for the passed list, and store it along
	 * with us so that it can be returned. The same Ref can
	 * be reused. Don't use newReference for a self-reference:
	 * use newReference() for that.
	 * 
	 * @param object
	 * @return
	 */
	public Ref newReference(IList object);
	
	/**
	 * Get a new self-reference.
	 * 
	 * @return
	 */
	public Ref newReference();
	
}