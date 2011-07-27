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
 * This is a specialised {@link de.unido.ls5.eti.client.EtiRemoteException} 
 * where the message text automatically strips the leading class name
 * contained in the FaultString of AxisFault
 * 
 * @author Stefan Naujokat
 *
 */
public class EtiAxisException extends EtiRemoteException {

	private static final long serialVersionUID = 7532208033780252418L;

	/**
	 * @see EtiRemoteException#EtiRemoteException(String) 
	 * @param message the message. it is passed to super constructor
	 * with everything after the first colon
	 */
	public EtiAxisException(String message) {
		super(message.substring(message.indexOf(':')+2));
	}

	/**
	 * @see EtiRemoteException#EtiRemoteException(String) 
	 * @param message the message. it is passed to super constructor
	 * with everything after the first colon
	 */
	public EtiAxisException(String message, Throwable cause) {
		super(message.substring(message.indexOf(':')+2), cause);
	}
	
}
