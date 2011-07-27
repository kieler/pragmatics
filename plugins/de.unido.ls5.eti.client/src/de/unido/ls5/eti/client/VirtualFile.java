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

import java.io.IOException;
import java.io.InputStream;

/**
 * This interface is used by every jETI related API to represent the
 * tool's input/output files. It hides the fact how the data of this
 * file is stored and simply provides the {@link InputStream} to
 * read it. An implementation could for example store the data in
 * a real {@link java.io.File} object or in a byte array.
 * 
 * @author naujokat
 *
 */
public interface VirtualFile {

	/**
	 * Provides the InputStream where the data of the represented file
	 * can be read from.
	 * 
	 * @return the input stream to the represented file
	 * 
	 * @throws IOException
	 * 	may be thrown if the underlying implementation uses some I/O to
	 * 	read the data from.
	 */
	public abstract InputStream getInputStream() throws IOException;

	/**
	 * As the implementation is independent from real files, it must have
	 * a file name that is handled separately.
	 * 
	 * This filename is used by jETI to identify the file's name within
	 * the jETI-file-context.
	 * 
	 * @return
	 * 	The name of this VirtualFile
	 */
	public abstract String getFilename();

	/**
	 * The implementing class is forced to override {@link Object#toString()}
	 * to produce some usable debug data, such as size, path, ...
	 * @return
	 * 	A String representation of the file containing debug information
	 */
	public abstract String toString();

}