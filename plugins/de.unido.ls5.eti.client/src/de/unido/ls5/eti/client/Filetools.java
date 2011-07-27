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
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * Helper Methods to handle files and streams
 * 
 * Will probably removed in future and replaced by usage of commons-io
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class Filetools {
     
    private static final int BUFFER_SIZE = 32768;
    
    private static Logger logger = Logger.getLogger(Filetools.class);
    
    /**
     * Writes the contents of an input stream into a file
     * 
     * @param is the input stream to read from
     * @param file the file to write to
     * @throws Exception if something goes wrong
     */
    public static void streamToFile(InputStream is, File file) throws Exception {
    	FileOutputStream fos = new FileOutputStream(file);
    	int readBytes = 0;
    	byte[] buffer = new byte[BUFFER_SIZE];
    	while ((readBytes = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
    		fos.write(buffer, 0, readBytes);
    		logger.debug("wrote " + readBytes + " bytes to " + file.getAbsolutePath());
    	}
    	fos.flush();
    	fos.close();
    	is.close();
    }
    
    
    
}
