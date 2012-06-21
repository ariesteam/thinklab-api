package org.integratedmodelling.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Derive objects from this to implement a threadsafe ID assignment that is guaranteed to
 * work in a hash and support equality checking.
 * 
 * Note: equals() will only return true in case of identity, i.e. the two objects are the
 * same object. So don't use it for anything that is treated like a literal.
 * 
 * @author Ferd
 *
 */
public class HashableObject {
	
    private static final AtomicLong nextId = new AtomicLong(0);
    private static final ThreadLocal<Long> threadId =
        new ThreadLocal<Long>() {
            @Override protected Long initialValue() {
                return nextId.getAndIncrement();
        }   
    };

    // Returns the current thread's unique ID, assigning it if necessary
    private static long nextId() {
        Long id = threadId.get();
        threadId.set(new Long(id.longValue() + 1l));
        return id;
    }		     
	
	protected Long __id = nextId();
	
	public int hashCode() {
		return __id.hashCode();
	}
	
	public boolean equals(Object o) {
		return o instanceof HashableObject && __id == ((HashableObject)o).__id;
	}
}
