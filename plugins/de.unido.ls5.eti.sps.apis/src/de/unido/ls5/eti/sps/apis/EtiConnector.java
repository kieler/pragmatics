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
 * Created on 04.10.2005
 *
 */
package de.unido.ls5.eti.sps.apis;

import java.util.Properties;

/**
 * 
 * Interface that all connectors must implement. Provides start and stop
 * methods. How the connector receives its configuration is not regulated.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public interface EtiConnector {

	/**
	 * Start the connector.
	 * 
	 * @param props
	 * 	the properties of the ETI toolserver
	 * @return
	 * 	the URI this connector listens to or null, if the connector startup failed
	 */
    public String start(Properties props);
    
	/**
	 * Stop the connector.
	 */
    public void stop();
    
}
