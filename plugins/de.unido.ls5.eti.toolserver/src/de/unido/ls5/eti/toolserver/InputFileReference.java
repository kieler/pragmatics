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

import java.io.File;

import de.unido.ls5.eti.sps.apis.ErrorCodeConstants;
import de.unido.ls5.eti.sps.apis.EtiServerException;

/**
 * This class should be used as parameter to jETI tools for input files
 * to that tool.
 * 
 * By naming convention (classname.contains("InputFile")) this is recognized
 * by the sibcreator as an input file 
 * ({@code see de.metaframe.jabc.sib.ETI#getInputFiles()}).
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 */
public class InputFileReference extends FileReference implements ValidatableParameter {

	/**
	 * Create a File Reference with s as relative part below session folder
	 * @param s the relative part of this file reference
	 * 
	 * @see FileReference#FileReference(String)
	 */
	public InputFileReference(String s) {
		super(s);
	}
	
	/**
	 * This method performs two checks to validate the parameter:
	 * 
	 * 1) a simple "correct file extension" check where classArgument is supposed
	 * to be this file extension. if the represented file ends with this extension,
	 * the validation succeeds.
	 * 
	 * 2) check wether the file exists: as this is going to be an input file
	 * to the executed tool, it would not make much sense if the file is not
	 * present.
	 * 
	 * @param classArgument expected to be the required file extension. if it 
	 * is null or "", the extension check will be omitted.
	 * 
	 * @throws EtiServerException if the validation fails. The
	 * message tells why. The error code will be {@link ErrorCodeConstants#CLASS_ARG_VERIFY_FAILED}
	 * 
	 */
	public void validateClassArgument(String classArgument) throws EtiServerException {
		
		// file extension check.
		if (classArgument != null && !classArgument.equals("") && !getFilename().endsWith(classArgument))
			throw new EtiServerException("File '"+ getFilename() + "' failed classargument verification. file extension '" + classArgument + "' is required", ErrorCodeConstants.CLASS_ARG_VERIFY_FAILED);
		
		// file existance check
		if (!new File(this.toString()).exists())
			throw new EtiServerException("File '" +getFilename() + "' failed classargument verification. file does not exist. perhabs it was not properly uploaded/generated before?", ErrorCodeConstants.CLASS_ARG_VERIFY_FAILED);
		
		// if we reached this point, all checks succeeded
		return;
	}
	
}
