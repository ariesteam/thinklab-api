/**
 * Copyright 2011 The ARIES Consortium (http://www.ariesonline.org) and
 * www.integratedmodelling.org. 

   This file is part of Thinklab.

   Thinklab is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published
   by the Free Software Foundation, either version 3 of the License,
   or (at your option) any later version.

   Thinklab is distributed in the hope that it will be useful, but
   WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Thinklab.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.integratedmodelling.collections;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.exceptions.ThinklabRuntimeException;
import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.lang.IParseable;

/**
 * A numeric interval parsed from conventional syntax (e.g. "[12 34)" ). Can be
 * set to be open or close on each end and check if a number is contained in it.
 * 
 * @author Ferdinando Villa
 *
 */
public class NumericInterval implements IParseable {

	double lowerBound = 0.0;
	double upperBound = 0.0;
	boolean lowerOpen = false;
	boolean upperOpen = false;
	boolean lowerUndefined = true;
	boolean upperUndefined = true;
	
	public NumericInterval(String intvs) throws ThinklabException {
		parse(intvs);
	}

	public NumericInterval(Double left, Double right, boolean leftOpen, boolean rightOpen) {

		if (!( lowerUndefined = (left == null))) 
			lowerBound = left;
		if (!( upperUndefined = (right == null))) 
			upperBound = right;

		lowerOpen = leftOpen;
		upperOpen = rightOpen;
	}
	
	@Override
	public void parse(String s) throws ThinklabException {

		StreamTokenizer scanner = new StreamTokenizer(new StringReader(s));
		int token = 0;
		double high = 0.0, low = 0.0;
		int nnums = 0;
		boolean lowdef = false, highdef = false;
		
		while (true) {

			try {
				token = scanner.nextToken();
			} catch (IOException e) {
				throw new ThinklabValidationException("invalid interval syntax: " + s);
			}

			if (token == StreamTokenizer.TT_NUMBER) {
			
				if (nnums > 0) {
					high = scanner.nval;
				} else {
					low = scanner.nval;
				}
				nnums ++;
				
			} else if (token == StreamTokenizer.TT_EOF || token == StreamTokenizer.TT_EOL) {
				break;
			} else  if (token == '(') {
				if (nnums > 0) 
					throw new ThinklabValidationException("invalid interval syntax: " + s);
				lowdef = true;
				lowerOpen = true;
			} else  if (token == '[') {
				if (nnums > 0) 
					throw new ThinklabValidationException("invalid interval syntax: " + s);
				lowdef = true;
				lowerOpen = false;
			} else  if (token == ')') {
				if (nnums == 0) 
					throw new ThinklabValidationException("invalid interval syntax: " + s);
				highdef = true;
				upperOpen = true;
			} else  if (token == ']') {
				if (nnums == 0) 
					throw new ThinklabValidationException("invalid interval syntax: " + s);
				highdef = true;
				upperOpen = false;
			} else  if (token == ',') {
				/* accept and move on */
			} else {
				throw new ThinklabValidationException("invalid interval syntax: " + s);
			}			
		}
		
		/*
		 * all read, assemble interval info
		 */
		if (lowdef && highdef && nnums == 2) {
			lowerUndefined = upperUndefined = false;
			lowerBound = low;
			upperBound = high;
		} else if (lowdef && !highdef && nnums == 1) {
			lowerUndefined = false;
			lowerBound = low;
		} else if (highdef && !lowdef && nnums == 1) {
			upperUndefined = false;
			upperBound = low;
		} else {
			throw new ThinklabValidationException("invalid interval syntax: " + s);
		}
	}

	public int compare(NumericInterval i) {
		
		if (lowerUndefined == i.lowerUndefined &&
				lowerOpen == i.lowerOpen &&
				upperUndefined == i.upperUndefined &&
				upperOpen == i.upperOpen &&
				lowerBound == i.lowerBound &&
				upperBound == i.upperBound)
			return 0;
		
		if (this.upperBound <= i.lowerBound)
			return -1;

		if (this.lowerBound >= i.upperBound)
			return 1;
		
		throw new ThinklabRuntimeException("error: trying to sort overlapping numeric intervals");
		
	}
	
	public boolean isRightInfinite() {
		return upperUndefined;
	}

	public boolean isLeftInfinite() {
		return lowerUndefined;
	}

	/**
	 * true if the upper boundary is closed, i.e. includes the limit
	 * @return
	 */
	public boolean isRightBounded() {
		return !upperOpen;
	}

	/**
	 * true if the lower boundary is closed, i.e. includes the limit
	 * @return
	 */
	public boolean isLeftBounded() {
		return !lowerOpen;
	}
	
	public double getMinimumValue() {
		return lowerBound;
	}

	public double getMaximumValue() {
		return upperBound;
	}

	public boolean contains(double d) {

		if (lowerUndefined)
			return (upperOpen ? d < upperBound : d <= upperBound);
		else if (upperUndefined)
			return (lowerOpen ? d > lowerBound : d >= lowerBound);
		else 
			return
				(upperOpen ? d < upperBound : d <= upperBound) &&
				(lowerOpen ? d > lowerBound : d >= lowerBound);	
	}

	@Override
	public String toString() {
	
		String ret = "";
		
		if (!lowerUndefined) {
			ret += lowerOpen ? "(" : "[";
			ret += lowerBound;
		}
		if (!upperUndefined) {
			if (!lowerUndefined) 
				ret += " ";
			ret += upperBound;
			ret += upperOpen ? ")" : "]";
		}
		
		return ret;
	}

	@Override
	public String asText() {
		return toString();
	}

}
