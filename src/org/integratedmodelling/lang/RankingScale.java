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

	Number _lowerBound = null;
	Number _upperBound = null;
	
	boolean _integerScale = false;
	boolean _bounded = false;
	
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
		_lowerBound = from;
		_upperBound = to;
		_integerScale =  ((from instanceof Integer || from instanceof Long) &&
			(to instanceof Integer || to instanceof Long));
		_bounded =
				_lowerBound != null && _upperBound != null &&
				!checkInfinity(_lowerBound) && !checkInfinity(_upperBound);
	}

	private boolean checkInfinity(Number n) {

		if (n instanceof Double && (Double.isInfinite(n.doubleValue()) || Double.isNaN(n.doubleValue())))
			return true;
		
		if (n instanceof Float && (Float.isInfinite(n.floatValue()) || Float.isNaN(n.floatValue())))
			return true;
				
		return false;
	}

	public Pair<Number, Number> getRange() {
		return new Pair<Number, Number>(_lowerBound, _upperBound);
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
		
		if (scale != null && _bounded && scale._bounded) {
			
			double conversion = 
					(_upperBound.doubleValue() - _lowerBound.doubleValue()) /
					(scale._upperBound.doubleValue() - scale._lowerBound.doubleValue());
			d = _lowerBound.doubleValue() + (d.doubleValue() * conversion);
			if (_integerScale) {
				d = new Integer((int) Math.rint(d.doubleValue()));
			}
		}
		
		return d;
	}
	
	public boolean isBounded() {
		return _bounded;
	}
	
	public boolean isInteger() {
		return _integerScale;
	}
	
}
