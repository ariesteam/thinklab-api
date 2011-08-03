package org.integratedmodelling.thinklab.api.lang;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IValue;

/**
 * Used in restrictions and capable of being rewritten in queries. A suitable
 * operator factory should be provided 
 * Largely TODO.
 * 
 * @author Ferdinando
 *
 */
public interface IOperator  {

	public static final String NO_OP = "nop";
	public static final String SUM = "+";
	public static final String MUL = "*";
	public static final String SUB = "-";
	public static final String DIV = "/";
	public static final String MOD = "%";
	
	public static final String AVG = "mean";
	public static final String STD = "std";
	public static final String CV  = "cv";
	public static final String VAR = "var";
	
	public static final String INTERSECT = "intersect";
	public static final String UNION = "union";

	public static final String AND = "and";
	public static final String OR = "or";
	public static final String NOT = "not";
	public static final String XOR = "xor";
	
	public abstract IValue eval(Object ... arg) throws ThinklabException;

	public abstract String getName();
	
	public abstract String getName(String language);
	
}
