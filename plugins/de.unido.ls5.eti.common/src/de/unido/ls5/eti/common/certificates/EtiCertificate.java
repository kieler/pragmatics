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
package de.unido.ls5.eti.common.certificates;

import java.util.HashMap;
import de.unido.ls5.eti.common.certificates.parameters.EtiParameter;

/**
 * Use this interface when you rite your own certificate classes.
 * Store the class file in folder ./certificates.
 * All files in this folder (which implement this interface) are
 * loaded at server startup
 * 
 * @author David Karla
 *
 */

public interface EtiCertificate {
	
	public String getName();
	public String getDescription();
	
	/**
	 * This method is used to check a certificate.
	 * E.g.: If a tool is free this method shoudl always return true. 
	 * @return tre if valid otherwise false
	 */
	public boolean isValid();
	
	/**
	 * This method is used when a tool is run.
	 * Example: You have a certificate which has an integer parameter
	 * which stores how many times the tool can be run before the
	 * certificate is invalid. In this case this method should 
	 * decrement the integer value and the isValid()-Methode
	 * should check if the integer value is > 0. 
	 *
	 */
	public void use();
	
	/**
	 * To access the parameter values of a certificate use this method.
	 * @return the parameters of this certificate
	 */
	public HashMap<String, EtiParameter> getParameters();
}
