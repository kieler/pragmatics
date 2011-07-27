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
 * This is an example certificate which can be used
 * to run a tool x times
 * put the class file in ./certificates/ to get it loaded
 * on server startup
 * 
 * @author David Karla
 *
 */
public class EtiCertificateFree implements EtiCertificate{

	public String getDescription() {
		return "For free use of a tool.";
	}

	public String getName() {
		return "Free certificate";
	}

	public HashMap<String, EtiParameter> getParameters() {		
		return new HashMap<String, EtiParameter>();
	}

	public boolean isValid() {
		return true;
	}

	public void use() {
		// do nothing here
	}

}
