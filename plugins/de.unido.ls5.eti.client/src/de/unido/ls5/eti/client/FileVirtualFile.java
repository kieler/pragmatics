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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is an implementation of {@link VirtualFile} that uses a real
 * file object as means to store the data.
 * 
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class FileVirtualFile implements VirtualFile {

	private File file;
	private String filename;

	/**
	 * Retrieves the virtual filename of this virtual file
	 * 
	 * @return the virtual filename
	 * 
	 * @see VirtualFile#getFilename()
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @see VirtualFile#getInputStream()
	 * 
	 * @return a {@link FileInputStream} to the underlying file object.
	 */
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(file);
	}

	/**
	 * Instanciate this VirtualFile with a given File and a virtual filename.
	 * 
	 * @param file the given file
	 * @param filename the virtual filename
	 * @throws FileNotFoundException file does not exist 
	 */
	public FileVirtualFile(File file, String filename) throws FileNotFoundException {
		if (!file.exists()) {
			throw new FileNotFoundException("FileVirtualFile cannot point to a non-existing file: " + file.getAbsolutePath());
		}
		this.file = file;
		this.filename = filename;
	}
	
	/**
	 * Retrieve a debug output String for this virtual file containing
	 * the virtual filename and the absolute path of the underlying file
	 * object
	 * 
	 * @return the debug string of this virtual file
	 */
    public String toString() {
    	return filename + " (" + file.getAbsolutePath() + ")";
    }

}
