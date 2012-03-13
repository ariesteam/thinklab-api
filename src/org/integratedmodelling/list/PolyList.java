/**
 * Polylist.java
 * ----------------------------------------------------------------------------------
 * 
 * Copyright (C) 2008 Robert Keller and www.integratedmodelling.org 
 * Created: Jan 17, 2008
 *
 * ----------------------------------------------------------------------------------
 * This file is part of Thinklab.
 * 
 * Thinklab is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Thinklab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the software; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * ----------------------------------------------------------------------------------
 * 
 * @copyright 2008 Robert Keller and www.integratedmodelling.org
 * @author    Robert Keller (original, polya package)
 * @author    Ferdinando Villa (fvilla@uvm.edu)
 * @author    Ioannis N. Athanasiadis (ioannis@athanasiadis.info)
 * @date      Jan 17, 2008
 * @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v3
 * @link      http://www.integratedmodelling.org
 **/

package org.integratedmodelling.list;

import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabIOException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.lang.IList;

/**   
 <pre>
 Polylist is the basic unit for constructing an open linked list of Objects.
 A Polylist can be either :
 empty, or
 non-empty, in which case it consists of:
 a Object as the first thing in the list
 a rest which is a Polylist.
 A Polylist can also be a Incremental, which may be converted into an
 ordinary Polylist by applying methods such as isEmpty(), first(), and
 rest(). 
 </pre>
 */
public class PolyList implements IList {
	/**
	 *  nil is the empty-list constant
	 */
	public static final PolyList nil = new PolyList();
	private Long _referenceId;
	private ConsCell ptr;

	/*
	 * simple functor that transforms tokens into objects when lists are read from strings.
	 */
	public interface TokenTransformer {
		public abstract Object transformString(String string);
		public abstract Object transformDouble(String string);
		public abstract Object transformQuote();
	}
	
	
	/**
	 *  construct empty Polylist
	 */
	public PolyList() {
		ptr = null;
	}

	/**
	 *  construct non-empty Polylist from a First and Rest
	 */
	PolyList(Object First, IList Rest) {
		ptr = new ConsCell(First, Rest);
	}

	/**
	 *  construct non-empty Polylist from a First and a Seed for the Rest
	 */
	PolyList(Object First, Seed Rest) {
		ptr = new ConsCell(First, Rest);
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return ptr == null;
	}

	/**
	 *  nonEmpty() tells whether the Polylist is non-empty.
	 */
	public boolean nonEmpty() {
		return ptr != null;
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#first()
	 */
	@Override
	public Object first() {
		return ptr.first();
	}

	/**
	 *  setFirst() sets the first of a list to an object
	 * @exception NullPointerException Can't take first of an empty Polylist.
	 *
	 */
	public void setFirst(Object ob) {
		ptr.setFirst(ob);
	}
	
	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#prettyPrint()
	 */
	@Override
	public String prettyPrint() {
		return prettyPrint(this);
	}
	
	public static String prettyPrint(IList list) {
		return prettyPrintInternal(list, 0, 2);
	}

	public static String prettyPrint(IList list, int indent) {
		return prettyPrintInternal(list, indent, 2);
	}
	
	private static boolean validateNumber(Object s) {
		
		if (s == null)
			return false;
		
		if (s instanceof Number)
			return true;
		
		try {
			Double.parseDouble(s.toString());
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private static boolean validateType(Object s) {
	
		if (s == null)
			return false;
		String[] ss = s.toString().split(":");
		if (ss.length != 2 || ss[0].trim().equals("") || ss[1].trim().equals(""))
			return false;
		return true;
	}
	
	private static String prettyPrintInternal(IList list, int indentLevel, int indentAmount) {

		String ret = "";
		String inds = "";
		
		for (int i = 0; i < indentLevel*indentAmount; i++)
			inds += ' ';

		if (list == null)
			return "(nil)";
		
		boolean wrote = false;

		ret += inds + "(";
		
		int i = 0;
		for (Object o : list.array()) {
			
			if (o instanceof IList) {
				ret += "\n" + prettyPrintInternal((IList)o, indentLevel+1, indentAmount);
			} else {
				String sep = " ";
				String z = o == null ? "nil" : o.toString();
				z = Escape.forDoubleQuotedString(z, false);
				
				// double quote anything that is not a number, doesn't look like one, contains spaces
				// in its string representation, or is not a first element that looks like a semantic type.
				if (z.contains(" ") || !validateNumber(o) && !(i == 0 && validateType(z))) {
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

	/**
	 * TBC an utility function that should either be generalized or moved to another class. If
	 * passed object is a list and the string equivalent of its first element equals to the passed string,
	 * the second object in the list is returned. Otherwise null is returned. Note that there is no
	 * way to distinguish when the element is there and the second is null, or the element is not there
	 * at all.
	 * @param o
	 * @param s
	 * @return
	 */
	public static Object declares(Object o, String what) {

		Object ret = null;

		if (o.getClass() == PolyList.class && ((PolyList) o).nonEmpty()
				&& ((IList) o).first().getClass() == String.class
				&& ((IList) o).first().toString().equals(what)) {
			ret = ((PolyList) o).second();
		}

		return ret;
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#rest()
	 */

	@Override
	public IList rest() {
		return ptr.rest();
	}

	/**
	 *  toString() converts Polylist to string, e.g. for printing
	 */

	public String toString() {

		StringBuffer buff = new StringBuffer();

		buff.append("(");

		// See if this is an incremental list; if so, show ...

		if (this instanceof Incremental && !((Incremental) this).grown()) {
			buff.append("...");
		}

		else if (nonEmpty()) {

			buff.append(first());
			IList L = rest();

			// print the rest of the items

			for (;;) {
				if (L instanceof Incremental && !((Incremental) L).grown()) {
					buff.append(" ...");
					break;
				}
				if (L.isEmpty())
					break;
				buff.append(" ");
				buff.append(L.first().toString());
				L = L.rest();
			}
		}
		buff.append(")");
		return buff.toString();
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#cons(java.lang.Object)
	 */

	@Override
	public IList cons(Object First) {
		return new PolyList(First, this);
	}

	/**
	 *  static cons returns a new Polylist given a First and a Rest.
	 */

	public static IList cons(Object First, IList Rest) {
		return ((IList)Rest).cons(First);
	}

	/**
	 *  This variant of cons takes a Seed instead of a Polylist as rest, so
	 *  the list can be grown incrementally.
	 */

	public static IList cons(Object First, Seed Rest) {
		return new PolyList(First, Rest);
	}

	/**
	 *  PolylistFromEnum makes a Polylist out of any Enumeration.
	 */

	public static IList PolylistFromEnum(java.util.Enumeration<?> e) {
		if (e.hasMoreElements())
			return cons(e.nextElement(), PolylistFromEnum(e));
		else
			return nil;
	}

	/**
	 *  return a list of no elements
	 */

	public static IList list() {
		return nil;
	}

	public static IList list(Object ... objs) {
		return fromArray(objs);
	}

	/**
	 * Create a referenced list.
	 * 
	 * When two lists contain each other, they need a mechanism for identifying the
	 * circular reference at creation time. The reference field is only instantiated
	 * from the user using this function, but can be inspected and is used in 
	 * printing and all recursive functions. The first time a referenced list is
	 * seen, it's printed normally with a #id in front; the second time, only
	 * (#id) is printed. Software using lists with a potential for self-reference
	 * should honor this field.
	 */
	static public PolyList referencedList(long id, Object[] ref) {
		PolyList ret = (PolyList) PolyList.list(ref);
		ret._referenceId = id;
		return ret;
	}

	public static IList listNotNull(Object ... objs) {
		return fromArrayNotNull(objs);
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#length()
	 */

	@Override
	public int length() {
		int len = 0;
		for (Enumeration<?> e = elements(); e.hasMoreElements(); e.nextElement()) {
			len++;
		}
		return len;
	}

	/**
	 *  elements() returns a PolylistEnum object, which implements the
	 *  interface java.util.Enumeration.
	 */

	public PolylistEnum elements() {
		return new PolylistEnum(this);
	}

	/**
	 *  first(L) returns the first element of its argument.
	 * @exception NullPointerException Can't take first of empty List.
	 */

	static public Object first(IList L) {
		return L.first();
	}

	/**
	 *  rest(L) returns the rest of its argument.
	 * @exception NullPointerException Can't take rest of empty Polylist.
	 */

	static public IList rest(IList L) {
		return L.rest();
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#reverse()
	 */

	@Override
	public IList reverse() {
		IList rev = nil;
		for (Enumeration<?> e = elements(); e.hasMoreElements();) {
			rev = cons(e.nextElement(), rev);
		}
		return rev;
	}

//	/**
//	 *  append(M) returns a Polylist consisting of the elements of this
//	 *  followed by those of M.
//	 */
//	public IList append(IList M) {
//		if (isEmpty())
//			return M;
//		else
//			return cons(first(), rest().append(M));
//	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#member(java.lang.Object)
	 */

	@Override
	public boolean member(Object A) {
		for (Enumeration<?> e = elements(); e.hasMoreElements();)
			if (Arith.equal(e.nextElement(), A))
				return true;
		return false;
	}

	/**
	 *  range(M, N) returns a Polylist of the form (M M+1 .... N)
	 */

	public static IList range(long M, long N) {
		if (M > N)
			return nil;
		else
			return cons(new Long(M), range(M + 1, N));
	}

	/**
	 *  range(M, N, S) returns a Polylist of the form (M M+S .... N)
	 */

	public static IList range(long M, long N, long S) {
		if (S >= 0)
			return rangeUp(M, N, S);
		else
			return rangeDown(M, N, S);
	}

	/**
	 *  rangeUp(M, N, S) is an auxiliary function for range
	 */

	static IList rangeUp(long M, long N, long S) {
		if (M > N)
			return nil;
		else
			return cons(new Long(M), rangeUp(M + S, N, S));
	}

	/**
	 *  rangeDown(M, N, S) is auxiliary function for range
	 */

	static IList rangeDown(long M, long N, long S) {
		if (M < N)
			return nil;
		else
			return cons(new Long(M), range(M + S, N, S));
	}

	/**
	 *  second selects the second element of a Polylist.
	 * @exception NullPointerException Can't take second of Polylist.
	 */

	public Object second() {
		return rest().first();
	}

	/**
	 *  third selects the third element of a Polylist.
	 * @exception NullPointerException Can't take third of Polylist.
	 */

	public Object third() {
		return rest().rest().first();
	}

	/**
	 *  fourth selects the fourth element of a Polylist.
	 * @exception NullPointerException Can't take fourth of Polylist.
	 */

	public Object fourth() {
		return rest().rest().rest().first();
	}

	/**
	 *  fifth selects the fifth element of a Polylist.
	 * @exception NullPointerException Can't take fifth of Polylist
	 */

	public Object fifth() {
		return rest().rest().rest().rest().first();
	}

	/**
	 *  sixth selects the sixth element of a Polylist.
	 * @exception NullPointerException Can't take sixth of Polylist
	 */

	public Object sixth() {
		return rest().rest().rest().rest().rest().first();
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#nth(long)
	 */

	@Override
	public Object nth(long n) {
		IList L = this;
		while (n-- > 0)
			L = L.rest();
		return L.first();
	}

//	/**
//	 *   prefix creates the length-n prefix of a Polylist.
//	 */
//
//	public IList prefix(long n) {
//		if (n <= 0 || isEmpty())
//			return nil;
//		else
//			return cons(first(), rest().prefix(n - 1));
//	}
//
//	/**
//	 *   coprefix creates the Polylist with all but the length-n prefix of 
//	 *   a Polylist
//	 */
//
//	public IList coprefix(long n) {
//		if (n <= 0 || isEmpty())
//			return this;
//		else
//			return rest().coprefix(n - 1);
//	}

	/**
	 *  equals(L, M) tells whether Polylists L and M are equal
	 */

	public static boolean equals(PolyList L, PolyList M) {
		Enumeration<?> e = L.elements();
		Enumeration<?> f = M.elements();

		while (e.hasMoreElements() && f.hasMoreElements()) {
			if (!Arith.equal(e.nextElement(), f.nextElement()))
				return false;
		}
		return !(e.hasMoreElements() || f.hasMoreElements());
	}

	/**
	 *  equals(M) tells whether this Polylist is equal to some other Object
	 */

	public boolean equals(Object M) {
		if (M instanceof IList)
			return equals(this, (PolyList) M);
		else
			return false;
	}

	
	/**
	 * Read the next list from the passed inputstream, stop when list is complete.
	 * 
	 * @param input
	 * @return a new list, or null if the stream finishes without a list. 
	 * @throws MalformedListException 
	 * @throws IOException 
	 */
	public static IList read(InputStream input) throws ThinklabException {
		
		String s = "";
		
		int c;
		try {
			c = input.read();
		} catch (IOException e) {
			throw new ThinklabIOException(e);
		}
		int pcount = 0;
		boolean inString = false;
		
		while (c != -1) {
		
			s = s + (char)c;
			
			if (c == '"') {
				inString = !inString;
			} else if (!inString) {
				if (c == '(')
					pcount ++;
				else if (c == ')') {
					pcount --;
					if (pcount == 0)
						break;
				}
			}
			try {
				c = input.read();
			} catch (IOException e) {
				throw new ThinklabIOException(e);
			}
		}
		
		if (s.trim().startsWith("("))
			return PolyList.parse(s);
		
		return null;
	}
	
	
	/**
	 * Create a list from a string. Quoted strings are handled properly. Numbers are
	 * stored in the list as doubles; all other objects are stored as strings.
	 * @param s
	 * @return
	 * @throws MalformedListException
	 */
	public static IList parse(String s) throws ThinklabException {

		final class DummyFunctor implements TokenTransformer {

			public Object transformDouble(String string) {
				return string;
		}

			public Object transformString(String string) {
				return string;
			}

			@Override
			public Object transformQuote() {
				return "'";
			}
			
		}

		return parseWithFunctor(s, new DummyFunctor());
	}
	
	/**
	 * Create a list from a string, but replace every token starting with $ with the
	 * correspondent value from the passed map (the key should not contain the $ sign).
	 * @param t
	 * @param vmap
	 * @return
	 */
	public static IList parseWithTemplate(String s, Hashtable<String, Object> vmap) throws ThinklabException {

		final class SubstitutionFunctor implements TokenTransformer {

			private Hashtable<String,Object> map = null;

			public SubstitutionFunctor(Hashtable<String, Object> vmap) {
				this.map = vmap ;
			}

			public Object transformDouble(String string) {
				return string;
			}

			public Object transformString(String string) {

				Object ret = string;
				
				if (ret == null)
					return ret;
				
				/* replace whole string with object if we have perfect match */
				if (string.startsWith("$") && map.containsKey(string.substring(1))) {
					ret = map.get(string.substring(1));
				} else if (string.contains("$")) {
					/* replace in-string tokens with their string representation */
					for (String k : map.keySet()) {
						string = 
							string.replaceAll(
								Pattern.quote("$") + k, 
								Matcher.quoteReplacement(map.get(k).toString()));
					}
					ret = string;
				}
				return ret;
			}
			
			@Override
			public Object transformQuote() {
				return "'";
			}

		}

		return parseWithFunctor(s, new SubstitutionFunctor(vmap));
	}
	
	/**
	 * Parses a list from a string. Generates a nested polylist of String objects or other Polylists.
	 * It's trivial to add one that recognizes Integers and Doubles as such, or even other types, but I'm not sure it would be useful. On the
	 * other hand, a parameterized one that parses as one specified type (e.g. integer) would probably be useful in the future.
	 * @param s a string representing a list; e.g. (a b (c d e) (19 22))
	 * @param functor a functor that handles the transformation between token and stored object.
	 * @return the generated list.
	 */
	public static IList parseWithFunctor(String s, TokenTransformer functor) throws ThinklabException {

		StreamTokenizer scanner = new StreamTokenizer(new StringReader(s));

		scanner.wordChars(':', ':');
		scanner.wordChars('/', '/');
		scanner.wordChars('\\', '\\');
		scanner.wordChars('~', '~');
		scanner.wordChars('%', '%');
		scanner.wordChars('&', '&');
		scanner.wordChars('?', '?');
		scanner.wordChars('_', '_');
		scanner.wordChars('#', '#');
		scanner.wordChars('$', '$');
		scanner.wordChars('*', '*');		
		scanner.ordinaryChar('\'');

		int token = 0;
		try {
			token = scanner.nextToken();
		} catch (IOException e) {
			throw new ThinklabValidationException(s);
		}

		if (token != '(')
			throw new ThinklabValidationException(s);

		return parseStringWithFunctor(scanner, functor);
	}

	static IList parseStringWithFunctor(StreamTokenizer scanner, TokenTransformer functor)
	throws ThinklabValidationException {

		IList ret = new PolyList();

		int token = StreamTokenizer.TT_EOF;
		Object tok = "";

		while (true) {

			try {
				token = scanner.nextToken();
			} catch (IOException e) {
				throw new ThinklabValidationException(e);
			}

			if (token == StreamTokenizer.TT_NUMBER)
				tok = functor.transformDouble(new Double(scanner.nval).toString());
			else if (token == StreamTokenizer.TT_WORD)
				tok = functor.transformString(scanner.sval);
			else if (token == '\'')
				tok = functor.transformQuote();
			else if (token == StreamTokenizer.TT_EOF
					|| token == StreamTokenizer.TT_EOL) {
				throw new ThinklabValidationException();
			} else {
				if (token != '(' && token != ')')
					// TBC a quoted string gets here, but what is returned is not TT_WORD, and no other fields
					// are there. Docs suck, to boot. 
					tok = functor.transformString(scanner.sval);
			}

			if (token == '(')
				// economical, it's not
				ret = (IList)ret.append(list(parseStringWithFunctor(scanner, functor)));
			else if (token == ')')
				break;
			else {
				ret = (IList)ret.append(list(functor.transformString(tok == null ? ("" + (char)token) : tok.toString())));
			}
		}

		return ret;

	}


	/**
	 *  analysis produces a string analyzing objects, especially Polylists
	 */

	public static String analysis(Object Ob) {
		return analysis(Ob, 0);
	}

	/**
	 *  produce an analysis of this Polylist
	 */

	String analysis() {
		return analysis(0);
	}

	/**
	 *  produce an analysis of this Polylist, indenting N spaces
	 */

	String analysis(int N) {
		if (isEmpty())
			return spaces(N) + "The empty Polylist\n";
		StringBuffer buff = new StringBuffer();
		buff.append(spaces(N));
		int len = length();
		buff.append("A Polylist consisting of " + len + " element"
				+ (len > 1 ? "s" : "") + ": \n");

		for (Enumeration<?> e = elements(); e.hasMoreElements();) {
			buff.append(analysis(e.nextElement(), N + 1));
		}
		return buff.toString();
	}

	/**
	 *  produce an analysis of the first argument, indenting N spaces
	 */

	static String analysis(Object Ob, int N) {
		if (Ob instanceof IList)
			return ((PolyList) Ob).analysis(N);
		else
			return spaces(N) + Ob.toString() + " (class "
					+ Ob.getClass().getName() + ")\n";
	}

	/**
	 * Indent N spaces.
	 */

	static String spaces(int N) {
		StringBuffer buff = new StringBuffer();
		while (N > 0) {
			buff.append("  ");
			N--;
		}
		return buff.toString();
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#array()
	 */

	@Override
	public Object[] array() {
		Object[] result = new Object[length()];
		int i = 0;
		for (Enumeration<?> e = elements(); e.hasMoreElements();) {
			result[i++] = e.nextElement();
		}
		return result;
	}

	/**
	 * PolylistFromArray makes a list out of an array of objects. If an object
	 * is a collection, all of its objects are added one by one.
	 */
	public static IList fromArray(Object array[]) {
		IList result = nil;
		for (int i = array.length - 1; i >= 0; i--) {
			result = cons(array[i], result);
		}
		return result;
	}
	
	/**
	 * PolylistFromArrayNotNull makes a list out of an array of objects where
	 * any nulls are not included in the list
	 */
	public static IList fromArrayNotNull(Object array[]) {
		IList result = nil;
		for (int i = array.length - 1; i >= 0; i--)
			if (array[i] != null)
				result = cons(array[i], result);
		return result;
	}
	
	
	/**
	 * PolylistFromArray makes a list out of an array of objects
	 */
	public static IList fromCollection(Collection<Object> collection) {
		IList result = nil;
		List<Object> array = null;
		if (collection instanceof List) {
			array = (List<Object>)collection;
		} else {
			array = new ArrayList<Object>();
			for (Object o : collection)
				array.add(o);
		}
		for (int i = array.size() - 1; i >= 0; i--)
			result = cons(array.get(i), result);
		return result;
	}

	/**
	 * explode(String S) converts a string into a Polylist of Character
	 */

	public static IList explode(String S) {
		IList result = nil;
		for (int i = S.length() - 1; i >= 0; i--)
			result = cons(new Character(S.charAt(i)), result);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#implode()
	 */

	@Override
	public String implode() {
		StringBuffer buff = new StringBuffer();
		for (Enumeration<?> e = elements(); e.hasMoreElements();) {
			buff.append(e.nextElement().toString());
		}
		return buff.toString();
	}

	/**
	 * map maps an object of class Function1 over a Polylist returning a 
	 * Polylist
	 */
	public boolean hasMemberOfClass(Class<?> cls) {
		boolean ret = false;
		for (Object o : array())
			if (ret = (o.getClass().equals(cls)))
				break;
		return ret;
	}

	/* (non-Javadoc)
	 * @see org.integratedmodelling.list.IList#toArrayList()
	 */
	@Override
	public List<Object> toCollection() {
		
		ArrayList<Object> ret = new ArrayList<Object>();

		for (Enumeration<?> e = elements(); e.hasMoreElements();) {
			ret.add(e.nextElement());
		}
		return ret;
	}
	
	/**
	 * Return a new list like this with the passed object appended at the end of the list.
	 * 
	 * @param o
	 * @return
	 */
	public IList append(Object o) {
		List<Object> ls = toCollection();
		ls.add(o);
		return PolyList.fromCollection(ls);
	}
		
	public boolean isReference() {
		return _referenceId != null;
	}
	
	public long getReferenceId() {
		return _referenceId;
	}
	
	public void setReference(long l) {
		_referenceId = l;
	}

	
//	public XML.XmlNode createXmlNode() {
//		XmlNode n = new XmlNode(first().toString());
//		for (Object o : rest().array()) {
//			if (o instanceof Polylist)
//				n.add(((Polylist)o).createXmlNode());
//			else 
//				n.text(o.toString());
//		}
//		return n;
//	}
	
//	/**
//	 * Return a nice XML document that can be written to file or shown.
//	 * 
//	 * @return
//	 * @throws ThinklabException
//	 */
//	public XMLDocument asXml() throws ThinklabException {
//		return XML.document(createXmlNode());
//	}

} // class Polylist
