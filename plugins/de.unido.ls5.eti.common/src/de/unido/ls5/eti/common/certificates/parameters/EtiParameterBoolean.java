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
package de.unido.ls5.eti.common.certificates.parameters;

/**
 * @author David Karla
 */
public class EtiParameterBoolean extends EtiParameter{

	private boolean value = false;
	
	@Override
	public String getValue() {
		return Boolean.toString(this.value);
	}

	@Override
	public void setValue(String value) {
		this.value = Boolean.parseBoolean(value);		
	}
	
	public boolean getBooleanValue () {
		return this.value;
	}
	
	public void setBooleanValue (boolean value){
		this.value = value;
	}

	@Override
	public int getType() {
		return EtiParameter.BOOLEAN;
	}

}
