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

import java.net.URI;

/**
 * 
 * This class handles the creation of {@link EtiConnection}s. It instanciates 
 * protocol specific connection objects. Which protocol shall be used is infered
 * from the server URI.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class EtiConnectionFactory {
	
	/** 
	 * Instanciates an EtiConnection for a given URI. The 'scheme' part of the
	 * URI must be either http (for an EtiConnectionSoap) or sepp (for an
	 * EtiConnectionSepp)
	 * 
	 * @param uri
	 * 		The target endpoint for the EtiConnection
	 * @return
	 * 		a protocol specific instance of {@link EtiConnection} 
	 * @throws EtiLocalException
	 * 		if the specified uri specifies a protocol which has no jETI implementation.
	 */
	public static EtiConnection createConnection(URI uri) throws EtiLocalException {
		
		//FIXME: Service Provider Pattern
		if (uri.getScheme().equals("http")) {
			throw new EtiLocalException("SOAP connections currently not supported");
			//return new EtiConnectionSoap(uri);
		}
		else if (uri.getScheme().equals("eti")) {
			return new EtiConnectionSepp(uri);
		}
		else {
			throw new EtiLocalException("No jETI client implementation for URI scheme '" + uri.getScheme() + "' was found");
		}
	}
	
	
	/** 
	 * Instanciates a new EtiConnection for an already existing Session. This is
	 * for example used by the forward function.
	 * 
	 * @param uri The target endpoint for the EtiConnection
	 * 
	 * @see EtiConnectionFactory#createConnection(URI)
	 * 
	 * @param sessionId
	 * 	The initial value for the connection's sessionId
	 * @return  a protocol specific instance of {@link EtiConnection} 
	 * 
	 * @throws EtiLocalException 
	 */
	public static EtiConnection createConnection(URI uri, String sessionId) throws EtiLocalException {
		EtiConnection connection = createConnection(uri);
		connection.setSession(sessionId);
		return connection;
	}

}
