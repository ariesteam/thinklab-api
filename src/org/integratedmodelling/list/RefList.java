package org.integratedmodelling.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.integratedmodelling.common.HashableObject;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.lang.IRList;

public class RefList extends HashableObject implements IRList {

	Map<Integer, Object> _refs;
	IList               _list;
	
	// ref 0 is reserved to internalize self
	int _id = 1;
	
	private class Ref {
		int _n;
		Ref(int n) {
			_n = n;
		}
	}

	private RefList() {
		_list = PolyList.list();
		_refs = new HashMap<Integer, Object>();
	}
	
	private RefList(Object ... objects) {
		_refs = new HashMap<Integer, Object>();
		for (int i = 0; i < objects.length; i++)
			objects[i] = internalize(objects[i]);
		_list = PolyList.list(objects);
	}
	
	private RefList(Map<Integer, Object> refs, int id, IList list) {
		_refs = refs;
		_id = id;
		_list = list;
	}
	
	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	@Override
	public IList append(Object... o) {
		
		for (int i = 0; i < o.length; i++) {
			if (o[i] instanceof RefList)
				o[i] = internalize((IList)(o[i]));
		}
		return new RefList(_refs, _id, _list.append(o));
	}

	@Override
	public Object first() {
		return dereference(_list.first());
	}

	@Override
	public IList rest() {
		return new RefList(_refs, _id, _list.rest());
	}

	@Override
	public IList cons(Object o) {
		if (o instanceof RefList)
			o = internalize((RefList)o);
		return new RefList(_refs, _id, _list.cons(o));
	}

	@Override
	public int length() {
		return _list.length();
	}

	@Override
	public IList reverse() {
		return new RefList(_refs, _id, _list.reverse());
	}

	@Override
	public boolean contains(Object a) {
		return _list.contains(a);
	}

	@Override
	public Object nth(long n) {
		return dereference(_list.nth(n));
	}

	private Object dereference(Object o) {
		if (o instanceof Ref) {
			return ((Ref)o)._n == 0 ?
					this :
					_refs.get(((Ref)o)._n);
		} else if (o instanceof IList) {
			return new RefList(_refs, _id, (IList)o);
		}
		return o;
	}

	@Override
	public Object[] toArray() {
		Object[] ret = _list.toArray();
		for (int i = 0; i < ret.length; i++)
			ret[i] = dereference(ret[i]);
		return null;
	}

	@Override
	public List<Object> toCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Object> iterator() {

		return new Iterator<Object>() {
			
			int n = 0;
			
			@Override
			public boolean hasNext() {
				return n < length();
			}

			@Override
			public Object next() {
				return nth(n);
			}

			@Override
			public void remove() {
			}
		};
	}

	@Override
	public Object getForwardReference() {
		return new Ref(_id++);
	}

	@Override
	public void resolve(Object reference, Object list) {
		_refs.put(((Ref)reference)._n, internalize(list));
	}

	public IRList newList(Object... objects) {
		for (int i = 0; i < objects.length; i++) 
			objects[i] = internalize(objects[i]);
		return new RefList(_refs, _id, PolyList.list(objects));
	}


	public static IRList list(Object... objects) {
		return new RefList(objects);
	}

	/*
	 * if passed a reflist, turn it into another with our own
	 * reference map, translate all references in it, and return the result.
	 */
	private Object internalize(Object o) {
		if (o instanceof RefList) {
			return o.equals(this)? 
					new Ref(0) :
					internalizeInternal((RefList)o, new HashMap<Integer, Integer>());
		} else if (o instanceof IList) {
			Object[] oo = ((IList)o).toArray();
			for (int i = 0; i < oo.length; i++)
				oo[i] = internalize(oo[i]);
			return PolyList.list(oo);
		}
		return o;
	}

	/*
	 * winner of the "sick method name of the year" award
	 */
	RefList internalizeInternal(RefList rl, HashMap<Integer, Integer> rr) {

		Object[] io = new Object[rl.length()];
		int i = 0;
		for (Object obj : rl._list.toArray()) {
			if (obj instanceof Ref) {
				if (rr.containsKey(((Ref)obj)._n)) 
					io[i] = new Ref(rr.get(((Ref)obj)._n));
				else {
					Ref r = new Ref(_id++);
					rr.put(((Ref)obj)._n, r._n);
					_refs.put(r._n, rl._refs.get(((Ref)obj)._n));
					io[i] = r;
				}
			} else if (obj instanceof RefList) {
				io[i] = internalizeInternal((RefList)obj, rr);
			} else {
				io[i] = obj;
			}
			i++;
		}
		return new RefList(_refs, _id, PolyList.list(io));

	}
	
	/*
	 * printing
	 * @return
	 */
	
	public String asText() {
		return printObject(this, new HashSet<Integer>());
	}
	
	private String asTextInternal(String ret, Set<Integer> ids) {

		if (ids.contains(_id))
			return ret + (ret.isEmpty()? "" : " ") + "#"+ _id;
		
		ids.add(_id);

		ret +=  (ret.isEmpty()? "" : " ") + "#" + _id + "(";
		for (Object o : _list) {
			ret +=	((ret.isEmpty() || ret.endsWith("(")) ? "" : " ") + printObject(o, ids);
		}
		return ret + ")"; 
	}
	
	private String printObject(Object o, Set<Integer> ids) {
		
		String ret = "";
		if (o instanceof Ref) {
			ret += "#" + ((Ref)o)._n;
			if (ids.contains(((Ref)o)._n))
				return ret;
			
			ids.add(((Ref)o)._n);
			return ret + printObject(_refs.get(((Ref)o)._n), ids);
			
		} else if (o instanceof IList) {
			ret +=	(ret.isEmpty()? "(" : " (");
			for (Object oo : (o instanceof RefList ? ((RefList)o)._list.toArray() : ((IList)o).toArray())) {
				ret +=	(ret.isEmpty()? "" : " ") + printObject(oo, ids);
			}
			ret +=	(ret.isEmpty()? ")" : " )");
			return ret;
		} else {
			
			String sep = " ";
			String z = o == null ? "nil" : o.toString();
			z = Escape.forDoubleQuotedString(z, false);
			
			// double quote anything that is not a number, doesn't look like one, contains spaces
			// in its string representation, or is not a first element that looks like a semantic type.
			if (z.contains(" ") || !PolyList.validateNumber(o) && !(PolyList.validateType(z))) {
				z = "\"" + z + "\"";
			}

			ret += sep;
			ret += z;

		}
		return o.toString();
	}

	public String toString() {
		return asText();
	}
	
	@Override
	public String prettyPrint() {
		return prettyPrint(0);
	}
	
	public String prettyPrint(int indent) {
		return prettyPrintInternal(this, indent, 2, new HashSet<Integer>());
	}
	
	private  String prettyPrintInternal(Object obj, int indentLevel, int indentAmount, Set<Integer> seenIds) {

		String ret = "";
		String inds = "";
		
		for (int i = 0; i < indentLevel*indentAmount; i++)
			inds += ' ';

		if (obj == null)
			return "(nil)";

		ret += inds;
		
		if (obj instanceof Ref) {
			ret += "#" + ((Ref)obj)._n;
			if (seenIds.contains(((Ref)obj)._n))
				return ret;
			
			seenIds.add(((Ref)obj)._n);
			return ret + prettyPrintInternal((IList)(_refs.get(((Ref)obj)._n)), indentLevel+1, indentAmount, seenIds);
	
		} else if (obj instanceof IList) {
			
			IList lst = (IList)obj;
			ret += "(";
			for (Object o : (obj instanceof RefList? ((RefList)obj)._list.toArray() : lst.toArray())) {
				ret += prettyPrintInternal(o, indentLevel+1, indentAmount, seenIds);
			} 
			ret += ")";
			
		} else {
			
			boolean wrote = false;

			String sep = " ";
			String z = obj == null ? "nil" : obj.toString();
			z = Escape.forDoubleQuotedString(z, false);
			
			// double quote anything that is not a number, doesn't look like one, contains spaces
			// in its string representation, or is not a first element that looks like a semantic type.
			if (z.contains(" ") || !PolyList.validateNumber(obj) && !(PolyList.validateType(z))) {
				z = "\"" + z + "\"";
			}

			if (wrote) ret += sep;
			ret += z;
			wrote = true;
		}		
		
		return ret;
	}

}
