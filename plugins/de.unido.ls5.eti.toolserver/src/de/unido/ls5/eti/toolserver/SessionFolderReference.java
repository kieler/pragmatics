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
 * Created on 19.01.2005
 */
package de.unido.ls5.eti.toolserver;

/**
 * This Parameter can be used in jETI tools to
 * provide the executed method with a path. Note that it would not 
 * make sense to set this parameter something other then "static", because
 * the actual value is ignored anyhow.
 * 
 * @author Stefan Naujokat
 */
public class SessionFolderReference extends FileReference {

	
	/**
	 * It is required by jETI convention that every 
	 * {@link de.unido.ls5.eti.common.Parameter} has a constructor that 
	 * accepts a String as only param. Here s is simply ignored, because
	 * we only need the path to the session folder.
	 * 
	 * @param s required by convention, but does nothing here.
	 */
	public SessionFolderReference(String s) {
		// explicitely do not forward s
		super();
	}
	
	/**
	 * retrieve the pathPrefix of this FileReference
	 * 
	 * @return the pathPrefix
	 */
	public String toString() {
		return pathPrefix;
	}
	
}
