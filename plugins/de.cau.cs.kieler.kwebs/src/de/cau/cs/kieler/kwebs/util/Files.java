/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utilities for file access.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class Files {

    /** Default buffer size for io operations. */
    private static final int BUFFER_SIZE
        = 10240;
    
    /**
     * Reads a file.
     * 
     * @param path
     *            path to the file
     * @return String with file contents
     * 
     * @throws IOException 
     */
    public static String readFile(final String path) throws IOException {       
        return Io.readStream(new FileInputStream(path));  
    }

    /**
     * Writes a file from a string.
     * 
     * @param path
     *            path to the file
     * @param data
     *            String with file contents
     * @throws IOException 
     */
    public static void writeFile(final String path, final String data)
        throws IOException { 
        writeFile(path, new ByteArrayInputStream(data.getBytes()));    
    }

    /**
     * Writes a file from an input stream.
     * 
     * @param path
     *            path to the file
     * @param stream
     *            stream with file contents
     * @throws IOException 
     */
    public static void writeFile(final String path, final InputStream stream)
        throws IOException {
        File dir = new File(path).getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fOut = new FileOutputStream(path);
        byte[] buffer = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = stream.read(buffer)) > 0) {
            fOut.write(buffer, 0, read);
        }
        fOut.flush();
        fOut.close();    
    }

    /**
     * Private Constructor. Util class must not
     * be instantiated.
     */
    private Files() {
    }

}
