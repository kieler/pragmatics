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
 * This abstract class is a template for the certificate parameters 
 * which can be used. It just has methods to set/get name, description
 * and the current value of a parameter.
 * 
 * @author David Karla
 *
 */

public abstract class EtiParameter {
	
	// These types are available
	public final static int INTEGER = 0;
	public final static int BOOLEAN = 1;
	public final static int STRING = 2;
	public final static int DATE = 3;
	
	private String name;
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name + ":" + this.description + ":" + this.getValue();
	}
	
	public abstract String getValue();
	
	public abstract void setValue(String value);
	
	public abstract int getType ();

}
