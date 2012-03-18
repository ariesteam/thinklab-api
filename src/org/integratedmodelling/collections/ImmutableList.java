package org.integratedmodelling.collections;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Utility class to implement a read-only object list, such as those used in kbox query
 * results. Just implements the interface for all modifying methods and make them throw
 * and UnsupportedOperationException, so that only the relevant methods need to be
 * implemented.
 * 
 * @author Ferd
 *
 */
public abstract class ImmutableList<T> implements List<T> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean add(T arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("cannot modify read-only list");
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public void add(int arg0, T arg1) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public int indexOf(Object arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public int lastIndexOf(Object arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public T remove(int arg0) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public T set(int arg0, T arg1) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		throw new UnsupportedOperationException("cannot modify read-only list");	
	}
}
