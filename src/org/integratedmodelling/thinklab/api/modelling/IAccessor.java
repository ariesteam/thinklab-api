package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.exceptions.ThinklabException;

/**
 * 
 * Classes implementing IAccessor must be able to handle data provision from either a datasource (extraction) or
 * another accessor (mediation). As a special case, some accessor may have neither and just produce predefined
 * values or compute functions of the current context.
 * 
 * @author Ferdinando
 *
 */
public interface IAccessor {

	public void setDatasource(IDataSource datasource);
	
	public void setMediatedAccessor(IAccessor accessor);
	
	/**
	 * Accessors of indirect observations are responsible for creating the result observation
	 * when they are contextualized.
	 * 
	 * @param size the number of states that the result observation will contain.
	 * @param context  the context that the state will represent. Its multiplicity equals the
	 *        passed size.
	 * @return
	 * @throws ThinklabException
	 */
	public abstract IState createState(int size, IContext context) throws ThinklabException;
	
	/**
	 * Compute or retrieve the value for the passed context index. If that requires parameters, the
	 * current values are in the context.
	 * 
	 * @return
	 */
	public Object getValue(int overallContextIndex, IContext context);

	/**
	 * returning true means that the value returned by getValue() does not change 
	 * and is known before any computation starts. Of course it also means
	 * that registers can safely be null in any call to getValue(). Compilers will
	 * inline the value and discard the accessor, exposing the value register so that
	 * compilation can be run again with different values.
	 * 
	 * @return
	 */
	public boolean isConstant();

}