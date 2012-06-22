package org.integratedmodelling.common;


/**
 * Derive objects from this to implement a global ID assignment that is guaranteed to
 * work in a hash and support equality checking. Works across threads - the thread-local
 * version is ThreadHashableObject.
 * 
 * Note: equals() will only return true in case of identity, i.e. the two objects are the
 * same object. So don't use it for anything that is treated like a literal.
 * 
 * @author Ferd
 *
 */
public class HashableObject {
	
	static long __ID = 0L;
	
    // Returns the current thread's unique ID, assigning it if necessary
    private synchronized static long nextId() {
    	return __ID ++;
    }		     
	
	protected Long __id = nextId();
	
	public int hashCode() {
		return __id.hashCode();
	}
	
	public boolean equals(Object o) {
		return o instanceof HashableObject && __id == ((HashableObject)o).__id;
	}
}
