/**
 * Incremental.java
 * ----------------------------------------------------------------------------------
 * 
 * Copyright (C) 2008 Robert Keller and www.integratedmodelling.org
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
 * @copyright 2008 Robert Keller and www.integratedmodelling.org
 * @author    Robert Keller (original, polya package)
 * @author    Ferdinando Villa (fvilla@uvm.edu)
 * @author    Ioannis N. Athanasiadis (ioannis@athanasiadis.info)
 * @date      Jan 17, 2008
 * @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v3
 * @link      http://www.integratedmodelling.org
 **/
// author:  Robert Keller
// purpose: Class Incremental of poly package

package org.integratedmodelling.list;

import org.integratedmodelling.thinklab.api.lang.IList;

public class Incremental extends PolyList
  {
  Object value;

  public Object first()
    {
    ensureGrown();
    return ((IList)value).first();
    }

  public IList rest()
    {
    ensureGrown();
    return ((IList)value).rest();
    }

  public boolean isEmpty()
    {
    ensureGrown();
    return ((IList)value).isEmpty();
    }

  public boolean nonEmpty()
    {
    ensureGrown();
    return ((PolyList)value).nonEmpty();
    }

  public String toString()
    {
    if( value instanceof Growable )
      return "...";
    else
      return ((IList)value).toString();
    }

  public Incremental(Growable growable)
    {
    value = growable;
    }

  public void ensureGrown()
    {
    while( value instanceof Growable )
      {
      value = ((Growable)value).grow();
      }
    }

  // use with caution!

  public boolean grown()
    {
    return !(value instanceof Growable);
    }

  public IList getList()
    {
    ensureGrown();
    return (IList)value;
    }
  }
