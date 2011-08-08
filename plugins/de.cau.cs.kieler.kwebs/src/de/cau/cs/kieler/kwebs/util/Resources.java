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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.Platform;

/**
 * Utilities for accessing resources such as files or bundled resources.
 *
 * @kieler.rating  2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *     
 * @author  swe
 */
public final class Resources {

    /** Default buffer size for i/o operations. */
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
    public static String readFileAsString(final String path) throws IOException {       
        return readStreamAsString(new FileInputStream(path));  
    }

    /**
     * Reads a file.
     * 
     * @param path
     *            path to the file
     * @return byte array with file contents
     * 
     * @throws IOException 
     */
    public static byte[] readFileAsByteArray(final String path) throws IOException {       
        return readStreamAsByteArray(new FileInputStream(path));  
    }

    /**
     * Writes a file from a string.
     * 
     * @param path
     *            path to the file
     * @param data
     *            string with file contents
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
        if (dir != null && !dir.exists()) {
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
     * Reads from a stream with fixed size.
     * 
     * @param stream
     *            the stream to be read
     * @return string with stream contents
     * 
     * @throws IOException 
     */
    public static String readStreamAsString(final InputStream stream) throws IOException {       
        StringBuffer data = new StringBuffer();
        byte[] buffer = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = stream.read(buffer)) > 0) {
            data.append(new String(buffer, 0, read));
        }
        return data.toString();    
    }

    /**
     * Reads from a stream with fixed size.
     * 
     * @param stream
     *            the stream to be read
     * @return string with stream contents
     * 
     * @throws IOException 
     */
    public static byte[] readStreamAsByteArray(final InputStream stream) throws IOException {
        byte[] result = null;
        int read = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((read = stream.read(buffer)) > 0) {
            out.write(buffer, 0, read);
        }
        out.flush();
        result = out.toByteArray();
        out.close();
        return result;
    }

    /** 
     * @param pluginid
     * @param resource
     * @return
     */
    public static byte[] readFileOrPluginResourceAsByteArray(final String pluginid, 
        final String resource) {
        byte[] result = null;    
        try {
            result = readFileAsByteArray(resource);
        } catch (Exception e) {
            //Ignore exception on file access and look in plug-in for resource
        }
        if (result == null) {
            try {
                result = readStreamAsByteArray(getResourceStream(pluginid, resource));
            } catch (Exception e) {
                // Ignore since no logging possible due to the fact that this utility
                // class is used on both server and client side.
            }
        }
        return result;
    }
    
    /**
     * Creates an input stream to a bundled resource.
     * 
     * @param pluginid
     *            id of the plug-in the resource is bundled with
     * @param resource 
     *            path to the resource within this plug-in
     * @return an input stream to the resource
     * @throws IOException
     *             if the specified resource could not be found
     */
    public static InputStream getResourceStream(final String pluginid, final String resource)
        throws IOException {
        return Platform.getBundle(pluginid).getResource(resource).openStream();                
    }

    /**
     * Private Constructor. Utility class must not
     * be instantiated.
     */
    private Resources() {
    }

}
