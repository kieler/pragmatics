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
package de.unido.ls5.eti.client;

/**
 * Exception that indicates that something went wrong in the local context
 * of a jETI execution.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class EtiLocalException extends Exception {

	private static final long serialVersionUID = 7929252302976641474L;

	/**
	 * Instanciate a jETI Exception
	 */
	public EtiLocalException() {
		super();
	}

	/**
	 * Instanciate a jETI Exception with given message.
	 * @param message the given message
	 */
	public EtiLocalException(String message) {
		super(message);
	}

	/**
	 * Instanciate a jETI Exception with given message and causing throwable.
	 * @param message the given message
	 * @param cause the causing throwable
	 */
	public EtiLocalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instanciate a jETI Exception with causing throwable.
	 * @param cause the causing throwable
	 */
	public EtiLocalException(Throwable cause) {
		super(cause);
	}

}
