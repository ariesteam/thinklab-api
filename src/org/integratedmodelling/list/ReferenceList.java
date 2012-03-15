package org.integratedmodelling.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabRuntimeException;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.lang.IParseable;
import org.integratedmodelling.thinklab.api.lang.IReferenceList;

public class ReferenceList implements IReferenceList, IParseable {
	
	long _id = ThreadId.get();
	
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
	         return nextId.getAndIncrement();
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
	
	private IList _list() {
		IList ret = null;
		if (_refs != null && _refs.containsKey(_id))
			ret = _refs.get(_id);
		else {
			ret = _list;
		}
		if (ret == null)
			throw new ThinklabRuntimeException("unresolved reference in list");
		return ret;
	}

	
	public static ReferenceList list(Object ... objs) {
		return new ReferenceList((Object[])objs);
	}
	
	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof ReferenceList && ((ReferenceList)arg0)._id == _id;
	}
	
	@Override
	public int hashCode() {
		return new Long(_id).hashCode();
	}

	@Override
	public IList resolve(IList list) {
		_refs().put(_id, list);
		if (list instanceof ReferenceList) {
			((ReferenceList)list)._id = _id;
		}
		return this;
	}

	private ReferenceList(HashMap<Long, IList> refs, Object[] objects) {
		this(objects);
		_refs = refs;
	}
	
	private ReferenceList(long id, HashMap<Long, IList> refs, IList list) {
		_list = list;
		_refs = refs;
		_id = id;
	}
	
	private ReferenceList(Object[] objects) {
		_list = objects == null ? null : PolyList.list(objects);
	}


	public ReferenceList() {
		_list = null;
	}

	@Override
	public boolean isEmpty() {
		return _list().isEmpty();
	}

	@Override
	public ReferenceList append(Object ... o) {
		_list = _list().append(o);
		return this;
	}

	@Override
	public Object first() {
		return _list().first();
	}

	@Override
	public String prettyPrint() {
		// this will never be pretty anyway
		return asText();
	}

	@Override
	public ReferenceList rest() {
		return new ReferenceList(_id, _refs, _list().rest());
	}

	@Override
	public ReferenceList cons(Object first) {
		return new ReferenceList(_id, _refs, _list().cons(first));
	}

	@Override
	public int length() {
		return _list().length();
	}

	@Override
	public ReferenceList reverse() {
		return new ReferenceList(_id, _refs, _list().reverse());
	}

	@Override
	public boolean contains(Object object) {
		return _list().contains(object);
	}

	@Override
	public Object nth(long n) {
		return _list().nth(n);
	}

	@Override
	public Object[] toArray() {
		return _list().toArray();
	}

	@Override
	public List<Object> toCollection() {
		return _list().toCollection();
	}

	@Override
	public Iterator<Object> iterator() {
		return _list() == null ? new PolyList().iterator() : _list().iterator();
	}

	@Override
	public ReferenceList getForwardReference() {
		return new ReferenceList(_refs, (Object[])null);
	}

	@Override
	public ReferenceList newList(Object... objects) {
		return new ReferenceList(_refs, objects);
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
		return asTextInternal("", new HashSet<Long>());
	}
	
	private String asTextInternal(String ret, Set<Long> ids) {

		if (ids.contains(_id))
			return ret + (ret.isEmpty()? "" : " ") + "#"+ _id;

		ids.add(_id);

		if (_refs == null || _list instanceof PolyList)  {
			return ret + (ret.isEmpty()? "" : " ") + printObject(_list,ids);
		}
		
		ret +=  (ret.isEmpty()? "(" : " (");
		for (Object o : _list()) {
			ret +=	(ret.isEmpty()? "" : " ") + printObject(o, ids);
		}
		return ret + ")#" + _id; 
		
	}
	
	private String printObject(Object o, Set<Long> ids) {
		
		String ret = "";
		if (o instanceof ReferenceList) {
			return ((ReferenceList)o).asTextInternal("", ids);
		} else if (o instanceof PolyList) {
			ret +=	(ret.isEmpty()? "(" : " (");
			for (Object oo : (PolyList)o) {
				ret +=	(ret.isEmpty()? "" : " ") + printObject(oo, ids);
			}
			ret +=	(ret.isEmpty()? ")" : " )");
			return ret;
		}
		return o.toString();
	}

	public String toString() {
		return asText();
	}
	
}
