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
package de.unido.ls5.eti.toolserver;

import de.unido.ls5.eti.sps.apis.ErrorCodeConstants;
import de.unido.ls5.eti.sps.apis.EtiServerException;

/**
 * This interface can be implemented by classes that are meant to be
 * parameters to jETI tools. Every Parameter will be checked wether
 * it implements this interface. If it does, the {@link #validateClassArgument(String)}
 * method will be called with the String that is configured as classArgument
 * in the tool descriptor for that Parameter.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public interface ValidatableParameter {
	
	/**
	 * Perform internal validation check on this parameter. If
	 * validation failes, throw a {@link ClassArgumentValidationFailedException}
	 * that explains why
	 * 
	 * @param s The String to validate against. May be configured in tool
	 * descriptor (tools.xml)
	 * 
	 * @throws EtiServerException if validation fails. The error code of this
	 * Exception should be {@link ErrorCodeConstants#CLASS_ARG_VERIFY_FAILED}
	 */
	public void validateClassArgument(String s) throws EtiServerException;

}
