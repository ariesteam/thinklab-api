package org.integratedmodelling.common;

/**
 * Anything implementing ISerializable will return a bean made only of POD objects, to be serialized
 * using any adopted serialization framework.
 * 
 * @author Ferd
 *
 */
public interface ISerializable {

	public Object getSerializableBean();
}
