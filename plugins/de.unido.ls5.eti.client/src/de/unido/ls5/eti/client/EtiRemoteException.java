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
 * Exception that indicates that something went wrong during the remote
 * execution of a tool.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class EtiRemoteException extends Exception {

	private static final long serialVersionUID = -8691599228044753584L;
	private int errorId = -1;

	/**
	 * Instanciate a jETI Exception
	 */
	public EtiRemoteException() {
		super();
	}

	/**
	 * Instanciate a jETI Exception with given message.
	 * @param message the given message
	 */
	public EtiRemoteException(String message) {
		super(message);
	}

	/**
	 * Instanciate a jETI Exception with given message and causing throwable.
	 * @param message the given message
	 * @param cause the causing throwable
	 */
	public EtiRemoteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instanciate a jETI Exception with causing throwable.
	 * @param cause the causing throwable
	 */
	public EtiRemoteException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instanciate a jETI Exception with a given error ID.
	 * @param errorId the ID of the error
	 */
	public EtiRemoteException(int errorId) {
		super();
		this.errorId = errorId;
	}

	/**
	 * Instanciate a jETI Exception with given message and error ID.
	 * @param message the given message
	 * @param errorId the ID of the error
	 */
	public EtiRemoteException(String message, int errorId) {
		super(message);
		this.errorId = errorId;
	}

	/**
	 * Instanciate a jETI Exception with given message, causing throwable and error ID.
	 * @param message the given message
	 * @param cause the causing throwable
	 * @param errorId the ID of the error
	 */
	public EtiRemoteException(String message, Throwable cause, int errorId) {
		super(message, cause);
		this.errorId = errorId;
	}

	/**
	 * Instanciate a jETI Exception with causing throwable and error ID.
	 * @param cause the causing throwable
	 * @param errorId the ID of the error
	 */
	public EtiRemoteException(Throwable cause, int errorId) {
		super(cause);
		this.errorId = errorId;
	}

	/**
	 * Retrieve the ID of the error. refer to external documentation
	 * for specific error codes.
	 *		 
	 * @return the id of the error or \code{-1}, if no error code has been provided.
	 */
	public int getErrorId() {
		return errorId;
	}


}
