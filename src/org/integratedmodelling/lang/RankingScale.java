package org.integratedmodelling.lang;

import org.integratedmodelling.collections.Pair;

/**
 * A simple object that allows numeric ranking scales to be defined and in some cases
 * mediated.
 * 
 * @author Ferd
 *
 */
public class RankingScale {

	Number _from = null;
	Number _to = null;
	
	boolean _isInteger = false;
	boolean _isBounded = false;
	
	/**
	 * Unbounded ranking, basically a no-op to filter values through.
	 */
	public RankingScale() {
	}
	
	/**
	 * Full specification - can be unbounded, partially bounded of fully bounded.
	 * 
	 * @param from
	 * @param to
	 */
	public RankingScale(Number from, Number to) {
		// TODO Auto-generated constructor stub
		_from = from;
		_to = to;
		_isInteger =  ((from instanceof Integer || from instanceof Long) &&
			(to instanceof Integer || to instanceof Long));
		_isBounded =
				_from != null && _to != null &&
				!checkInfinity(_from) && !checkInfinity(_to);
	}

	private boolean checkInfinity(Number n) {

		if (n instanceof Double && (Double.isInfinite(n.doubleValue()) || Double.isNaN(n.doubleValue())))
			return true;
		
		if (n instanceof Float && (Float.isInfinite(n.floatValue()) || Float.isNaN(n.floatValue())))
			return true;
				
		return false;
	}

	public Pair<Number, Number> getRange() {
		return new Pair<Number, Number>(_from, _to);
	}

	/**
	 * Convert passed value in passed scale to our own scale and number 
	 * representation. If anyone is unbounded or the passed scale is
	 * null, shut up and just return the value as passed.
	 * 
	 * @param d
	 * @param scale
	 * @return
	 */
	public Number convert(Number d, RankingScale scale) {
		
		if (scale != null && _isBounded && scale._isBounded) {
			
			double conversion = 
					(_to.doubleValue() - _from.doubleValue()) /
					(scale._to.doubleValue() - scale._from.doubleValue());
			d = _from.doubleValue() + (d.doubleValue() * conversion);
			if (_isInteger) {
				d = new Integer((int) Math.rint(d.doubleValue()));
			}
		}
		
		return d;
	}
	
	public boolean isBounded() {
		return _isBounded;
	}
	
	public boolean isInteger() {
		return _isInteger;
	}
	
}
