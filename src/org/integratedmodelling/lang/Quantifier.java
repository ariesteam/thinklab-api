/**
 * Quantifier.java
 * ----------------------------------------------------------------------------------
 * 
 * Copyright (C) 2008 www.integratedmodelling.org
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
 * @copyright 2008 www.integratedmodelling.org
 * @author    Ferdinando Villa (fvilla@uvm.edu)
 * @author    Ioannis N. Athanasiadis (ioannis@athanasiadis.info)
 * @date      Jan 17, 2008
 * @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v3
 * @link      http://www.integratedmodelling.org
 **/
package org.integratedmodelling.lang;

import org.integratedmodelling.exceptions.ThinklabValidationException;
import org.integratedmodelling.thinklab.api.lang.IParseable;

public class Quantifier implements IParseable {

	static public final int ERROR = -2;
    static public final int ANY = 0;
    static public final int ALL = 1;
    static public final int EXACT = 2;
    static public final int RANGE = 3;
    static public final int NONE = 4;
    static public final int INFINITE = -1;
    
    /**
     * Create the specified quantifier.
     * @return
     */
    static public Quantifier ANY() { return new Quantifier(ANY); }
    
    /**
     * Create the specified quantifier.
     * @return
     */
    static public Quantifier ALL() { return new Quantifier(ALL); }
    
    /**
     * Create the specified quantifier.
     * @return
     */
    static public Quantifier NONE() { return new Quantifier(NONE); }
    
    /**
     * Create the specified quantifier.
     * @return
     */
    static public Quantifier EXACTLY(int n) { 
    	Quantifier q = new Quantifier(EXACT); q.min = q.max = n; return q; 
    	}
    
    /**
     * Create the specified quantifier.
     * @return
     */
    static public Quantifier RANGE(int min, int max) {
    	Quantifier q = new Quantifier(RANGE); q.min = min; q.max = max; return q;
    }

    public int type = ERROR;
    
    // INFINITE == unbounded
    public int min = 0;
    public int max = 0;
    
    public Quantifier(int type) {
    	this.type = type;
    }
    
    public int getExactValue() {
    	return min;
    }
    
    public int getMinValue() {
    	return min;
    }
    
    public int getMaxValue() {
    	return max;
    }
    
    public Quantifier(String s) throws ThinklabValidationException {
    	parse(s);
    }
    
    /**
     * Compares the type of a quantifier but not the actual ranges if any.
     * @return
     */
    public boolean is(int quantifierType) {
    	return type == quantifierType;
    }
    
    public static Quantifier parseQuantifier(String s) throws ThinklabValidationException {

    	Quantifier q = null;
    	try {
    		q = new Quantifier(s);
    	} catch (Exception e) {
    		throw new ThinklabValidationException(s);
    	}
    	if (q.type == ERROR)
    		throw new ThinklabValidationException(s);
    	return q;
    }
    
    public static boolean isQuantifier(String s) {
        boolean ret = false;
        
        try {
            ret = parseQuantifier(s) != null;
        } catch (ThinklabValidationException e) {
        } 
        return ret;
    }
    
    public String toString() {

        String ret = null;

        switch (type)
          {
          case ANY:
            ret = "any"; 
            break;
          case ALL:
            ret = "all"; 
            break;
          case EXACT:
            ret = (min + max) == 0 ?  "none" : Integer.toString(min);
            break;
          case RANGE:
            ret = 
            	(min < 0? "" : Integer.toString(min)) + 
            	":" + 
            	(max < 0 ? "" : Integer.toString(max));
            break;
          case NONE:
        	ret = "none";
        	break;
          }
        
        return ret;
    }

	public boolean isMaxUnbound() {
		return max < 0;
	}

	public boolean isMinUnbound() {
		return min < 0;
	}

	@Override
	public void parse(String s) throws ThinklabValidationException {

        if (s.toLowerCase().equals("any")) {
            type = ANY;            
        } else if (s.toLowerCase().equals("all")) {
            type = ALL;
        } else if (s.toLowerCase().equals("none")) {
            type = NONE;
            min = max = 0;
        } else if (s.startsWith(":")) {
            type = RANGE;
            max = Integer.parseInt(s.substring(1));
            min = INFINITE;
            if (max == 0)
            	type = NONE;
        } else if (s.endsWith(":")) {
            type = RANGE;
            min = Integer.parseInt(s.substring(0, s.length()-1));
            max = INFINITE;
        } else if (s.contains(":")) {
            type = RANGE;
            String[] ss = s.split(":");
            min = Integer.parseInt(ss[0]);
            max = Integer.parseInt(ss[1]);
            if (min == 0 && max == 0)
            	type = NONE;
        } else {
        	type = EXACT;
        	min = max = Integer.parseInt(s);
        	if (min == 0 && max == 0)
        		type = NONE;
        }
	}

	@Override
	public String asText() {
		return toString();
	}

	
	public boolean match(int matches) {

		if (type == EXACT) {
			return matches == min;
		} else if (type == NONE) { 
			return matches == 0;
		} else if (type == ANY) {
			return matches > 0;
		} else if (type == RANGE) {
			
			if (min < 0) {
				return matches <= max;
			} else if (max < 0) {
				return matches >= min;
			} else {
				return matches <= max && matches >= min;
			}
		}
		return false;
	}

}
