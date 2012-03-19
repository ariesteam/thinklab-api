package org.integratedmodelling.thinklab.api.knowledge;

/**
 * A semantic object that wraps a literal, so that we can ask for conversions and demotion to
 * POD types.
 * 
 * @author Ferd
 *
 * @param <T>
 */
public interface ISemanticLiteral<T> extends ISemanticObject<T> {

	public double asDouble();
	
	public boolean asBoolean();
	
	public int asInteger();
	
	public long asLong();
	
	public float asFloat();
	
	public String asString();
}
