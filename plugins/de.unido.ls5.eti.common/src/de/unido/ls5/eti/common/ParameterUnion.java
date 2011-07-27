/**
 * Java Electronic Tool Integration - jETI
 * Copyright (C) 2004-2011 Chair for Programming Systems, TU Dortmund
 *
 * This file is part of jETI.
 *
 * jETI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * jETI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with jETI. If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 23.12.2004
 */
package de.unido.ls5.eti.common;

import java.util.List;
import java.util.Vector;

/**
 * 
 * @author Stefan Naujokat
 *
 */
public class ParameterUnion implements ParameterElement {
	
	private List<ParameterElement> unionElements;
	
	
	public ParameterUnion () {
		unionElements = new Vector<ParameterElement>();
	}
	
	
	public void addEtiParameter(Parameter p) {
		unionElements.add(p);
	}
	
	public List<ParameterElement> getUnionElements() {
		return unionElements;
	}

	public boolean isContainer() {
		return true;
	}


	public List<ParameterElement> getElements() {
		return unionElements;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ParameterUnion)) {
			return false;
		}
		else {
			ParameterUnion p = (ParameterUnion) obj;
			
			if ( 
				unionElements.equals(p.unionElements)
			)
				return true;
			else {
				return false;
			}
			
		}
	}
	
}
