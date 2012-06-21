package org.integratedmodelling.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.integratedmodelling.exceptions.ThinklabRuntimeException;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.lang.IReferenceList;

public class ReferenceList implements IReferenceList {
	
//    private static final AtomicLong nextId = new AtomicLong(0);
//    private static final ThreadLocal<Long> threadId =
//        new ThreadLocal<Long>() {
//            @Override protected Long initialValue() {
//                return nextId.getAndIncrement();
//        }   
//    };

	static long ___ID;

    // Returns the current thread's unique ID, assigning it if necessary
    private static synchronized long nextId() {
//        Long id = threadId.get();
//        threadId.set(new Long(id.longValue() + 1l));
//        return id;
    	return ___ID ++;
    }		     
	
    /*
     * IDs will be unique to threads, so all we need to do to merge references
     * from different lists is merge the ref tables.
     */
	long _id;
	
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
		ret = ret instanceof ReferenceList ? ((ReferenceList)ret)._list : ret;
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
		IList l = (IList) internalize(list);
		_refs().put(_id, l);
		if (list instanceof ReferenceList) {
			((ReferenceList)list)._id = _id;
			
			if (((ReferenceList)list)._list == null)
				System.out.println("PORCODIO");
		}
		return this;
	}

	private ReferenceList(HashMap<Long, IList> refs, Object[] objects) {
		this(objects);
		_refs = refs;
		_id = nextId();
	}
	
	private ReferenceList(long id, HashMap<Long, IList> refs, IList list) {
		_list = list;
		_refs = refs;
		_id = id;
	}
	
	private ReferenceList(Object[] objects) {
		_id = nextId();
		_list = objects == null ? null : PolyList.list(objects);
	}


	public ReferenceList() {
		_id = nextId();
		_list = null;
	}

	@Override
	public boolean isEmpty() {
		return _list().isEmpty();
	}

	@Override
	public ReferenceList append(Object ... o) {
		IList lst = _list();
		for (Object oo : o)
			lst = lst.append(internalize(oo));
		_list = lst;
		return this;
	}

	@Override
	public Object first() {
		return _list().first();
	}

	@Override
	public ReferenceList rest() {
		return new ReferenceList(_id, _refs, _list().rest());
	}

	@Override
	public ReferenceList cons(Object first) {
		return new ReferenceList(_id, _refs, _list().cons(internalize(first)));
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

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] instanceof ReferenceList) {
				objects[i] = internalize(objects[i]);
			}
		}
		return new ReferenceList(_refs, objects);
	}
	
	public Object internalize(Object orig) {
		if (orig instanceof ReferenceList) {
			ReferenceList rl = (ReferenceList)orig;
			if (_refs != null && rl._refs != null)
				_refs.putAll(((ReferenceList)orig)._refs);
			else if (_refs == null)
				_refs = rl._refs;
		}
		return orig;
	}

	public String asText() {
		return asTextInternal("", new HashSet<Long>());
	}
	
	private String asTextInternal(String ret, Set<Long> ids) {

		if (ids.contains(_id))
			return ret + (ret.isEmpty()? "" : " ") + "#"+ _id;
		
		ids.add(_id);

		ret +=  (ret.isEmpty()? "" : " ") + "#" + _id + "(";
		for (Object o : _list()) {
			ret +=	((ret.isEmpty() || ret.endsWith("(")) ? "" : " ") + printObject(o, ids);
		}
		return ret + ")"; 
		
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
	
	@Override
	public String prettyPrint() {
		return prettyPrint(this);
	}
	
	public static String prettyPrint(IList list) {
		return prettyPrintInternal(list, 0, 2, new HashSet<Long>());
	}

	public static String prettyPrint(IList list, int indent) {
		return prettyPrintInternal(list, indent, 2, new HashSet<Long>());
	}
	
	private static String prettyPrintInternal(
			IList list, int indentLevel, int indentAmount,
			Set<Long> seenIds) {

		String ret = "";
		String inds = "";
		
		for (int i = 0; i < indentLevel*indentAmount; i++)
			inds += ' ';

		if (list == null)
			return "(nil)";
		
		boolean wrote = false;

		ret += inds;
		
		if (list instanceof ReferenceList) {
			ret += "#" + ((ReferenceList)list)._id;
			if (seenIds.contains(((ReferenceList)list)._id))
				return ret;
			seenIds.add(((ReferenceList)list)._id);
		}
		
		ret += "(";
		
		int i = 0;
		for (Object o : list.toArray()) {
			if (o instanceof ReferenceList && seenIds.contains(((ReferenceList)o)._id)) {
				if (wrote) ret += " ";
				ret += "#" + ((ReferenceList)o)._id;
			} else if (o instanceof IList) {
				ret += "\n" + prettyPrintInternal((IList)o, indentLevel+1, indentAmount, seenIds);
			} else {
				String sep = " ";
				String z = o == null ? "nil" : o.toString();
				z = Escape.forDoubleQuotedString(z, false);
				
				// double quote anything that is not a number, doesn't look like one, contains spaces
				// in its string representation, or is not a first element that looks like a semantic type.
				if (z.contains(" ") || !PolyList.validateNumber(o) && !(i == 0 && PolyList.validateType(z))) {
					z = "\"" + z + "\"";
				}

				if (wrote) ret += sep;
				ret += z;
				wrote = true;
			}
			i++;
		}
		
		ret += ")";
		
		return ret;
	}

	public static IReferenceList fromCollection(ArrayList<Object> rl) {
		return list(rl.toArray());
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public IReferenceList getReference() {
		return new ReferenceList(_id, _refs, null);
	}
	
}
