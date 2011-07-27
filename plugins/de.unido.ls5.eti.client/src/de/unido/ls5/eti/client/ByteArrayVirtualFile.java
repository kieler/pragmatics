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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is an implementation of {@link VirtualFile} that uses a byte array
 * to store the data.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 */
public class ByteArrayVirtualFile implements VirtualFile {

    private static final int BUFFER_SIZE = 32768;
	private byte [] bytes;
    private String filename;
    
    /**
     * Initializes this VirtualFile by copying the data from inputStream
     * and storing it in an internal byte array.
     *  
     * @param inputStream the input stream where the data is read from
     * @param filename the virtual filename
     * @throws IOException if something during copying of the data goes wrong.
     */
    public ByteArrayVirtualFile (InputStream inputStream, String filename) throws IOException {
        this.filename = filename;        
        
        byte[] buffer = new byte[BUFFER_SIZE];
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        int readBytes = 0;
        while ((readBytes = inputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
        	bos.write(buffer, 0, readBytes);
        }
        bos.flush();
        this.bytes = bos.toByteArray();
        bos.close();
        inputStream.close();
    }
    
    /**
     * Retrieves an input stream to read the internal byte array
     * @return the input streamto read the internal byte array
     */
    public InputStream getInputStream() {
    	return new ByteArrayInputStream(bytes);
    }
    
    /**
     * @see VirtualFile#getFilename()
     */
    public String getFilename() {
        return filename;
    }
    
    /**
     * Produces a debug String that contains the virtual filename and the
     * number of bytes stored.
     * 
     * @return the debug string
     */
    public String toString() {
    	return filename + " (" + String.valueOf(bytes.length) + " bytes)";
    }
    
}
