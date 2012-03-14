package org.integratedmodelling.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabRuntimeException;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.lang.IParseable;
import org.integratedmodelling.thinklab.api.lang.IReferenceList;

public class ReferenceList implements IReferenceList, IParseable {
	
	/*
	 * used for validation of references
	 */
	long _listId = ThreadId.get();
	
	/*
	 * Increasing IDs are thread-private so we don't need to synchronize.
	 */
	 public static class ThreadId {
	     // Atomic integer containing the next thread ID to be assigned
	     private static final AtomicLong nextId = new AtomicLong(0);

	     // Thread local variable containing each thread's ID
	     private static final ThreadLocal<Long> threadId =
	         new ThreadLocal<Long>() {
	             @Override protected Long initialValue() {
	                 return nextId.getAndIncrement();
	         }   
	     };

	     // Returns the current thread's unique ID, assigning it if necessary
	     public static long get() {
	         return threadId.get();
	     }		     
	 }

	 // the main list we represent.
	IList   _list;
	HashMap<Long, IList> _refs;
	
	private HashMap<Long, IList> _refs() {
		if (_refs == null)
			_refs = new HashMap<Long, IList>();
		return _refs;
	}
	
	class Ref implements Reference  {
		
		long _id = ThreadId.get();
		long _ls = _listId;
		
		@Override
		public IList getList() {
			return _refs.get(_id);
		}

		@Override
		public boolean equals(Object arg0) {
			return arg0 instanceof Ref && ((Ref)arg0)._id == _id;
		}

		@Override
		public int hashCode() {
			return new Long(_id).hashCode();
		}
		
		public String toString() {
			return "#" + _id;
		}
	}

	private ReferenceList(HashMap<Long, IList> refs, Object[] objects) {
		this(objects);
		_refs = refs;
	}
	
	private ReferenceList(long id, HashMap<Long, IList> refs, IList list) {
		_list = list;
		_refs = refs;
		_listId = id;
	}
	
	public ReferenceList(Object[] objects) {
		_list = PolyList.list(objects);
	}


	public ReferenceList() {
		_list = new PolyList();
	}

	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	@Override
	public IList append(Object ... o) {
		_list = _list.append(o);
		return this;
	}

	@Override
	public Object first() {
		return _list.first();
	}

	@Override
	public String prettyPrint() {
		// TODO add reference info
		return _list.prettyPrint();
	}

	@Override
	public IList rest() {
		return new ReferenceList(_listId, _refs, _list.rest());
	}

	@Override
	public IList cons(Object first) {
		return new ReferenceList(_listId, _refs, _list.cons(first));
	}

	@Override
	public int length() {
		return _list.length();
	}

	@Override
	public IList reverse() {
		return new ReferenceList(_listId, _refs, _list.reverse());
	}

	@Override
	public boolean contains(Object object) {
		return _list.contains(object);
	}

	@Override
	public Object nth(long n) {
		return _list.nth(n);
	}

	@Override
	public Object[] toArray() {
		return _list.toArray();
	}

	@Override
	public List<Object> toCollection() {
		return _list.toCollection();
	}

	@Override
	public Iterator<Object> iterator() {
		return _list.iterator();
	}

	@Override
	public Reference getForwardReference() {
		return new Ref();
	}

	@Override
	public IList resolveReference(Reference ref, IList list) {
		if (((Ref)ref)._ls != _listId)
			throw new ThinklabRuntimeException(
					"internal error: foreign reference in reference list");
		_refs().put(((Ref)ref)._id, list);
		return this;
	}

	@Override
	public IList getList(Reference ref) {
		if (((Ref)ref)._ls != _listId)
			throw new ThinklabRuntimeException(
					"internal error: foreign reference in reference list");
		return _refs().get(((Ref)ref)._id);
	}

	@Override
	public IReferenceList list(Object... objects) {
		ReferenceList ret = new ReferenceList(_refs, objects);
		ret._listId = _listId;
		return ret;
	}

	@Override
	public void parse(String string) throws ThinklabException {

		string = string.trim();
		if (string.startsWith("("))
			_list = PolyList.parse(string);
		else {
			
			/*
			 * chop final "}";
			 */
			
			/*
			 * read first list
			 */
			
			/*
			 * loop reading #n tokens and lists
			 * alternatively until EOI
			 */
		}
	}

	@Override
	public String asText() {

		if (_refs == null) {
			return ((PolyList)_list).toString();
		}
		
		String ret = "{";
		ret += _list.toString();
		for (long ref : _refs.keySet()) {
			ret += " #" + ref + " " + _refs.get(ref);
		}
		ret += "}";
		
		return ret;
	}
	
	public String toString() {
		return asText();
	}
	
}
