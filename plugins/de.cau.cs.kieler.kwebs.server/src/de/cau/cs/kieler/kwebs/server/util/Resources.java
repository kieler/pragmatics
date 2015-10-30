/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.Platform;

/**
 * Utilities for accessing resources such as files or bundled resources.
 *
 * @kieler.design 2011-08-02 reviewed by ckru, mri, msp
 * @author swe
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
        writeFile(path, data.getBytes());    
    }

    /**
     * Writes a file from a string.
     * 
     * @param path
     *            path to the file
     * @param data
     *            byte array file contents
     * @throws IOException 
     */
    public static void writeFile(final String path, final byte[] data)
        throws IOException { 
        writeFile(path, new ByteArrayInputStream(data));    
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
     * Reads data from a path specified by {@code resource}. If not successful the data
     * is read from the plug-in specified by {@code pluginid}.
     *  
     * @param pluginid
     *            the plug-in id for reading from a plug-in
     * @param resource
     *            the resource path specifying either the file or the resource identifier
     * @return a byte array containing the read data or {@code null} if neither a matching
     *         file or a matching resource could be found
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
     * Returns an input stream on a file specified by {@code resource}. If the file does not
     * exist an input stream to the bundled resource from the plug-in specified by {@code pluginid}
     * is created.
     *  
     * @param pluginid
     *            the plug-in id for reading from a plug-in
     * @param resource
     *            the resource path specifying either the file or the resource identifier
     * @return an input stream on the resource or {@code null} if neither a matching
     *         file or a matching resource could be found
     */
    @SuppressWarnings("resource")
    public static InputStream getFileOrPluginResourceStream(final String pluginid, 
        final String resource) {
        InputStream result = null;
        try {
            result = new FileInputStream(new File(resource));
        } catch (Exception e) {
            //Ignore exception on file access and look in plug-in for resource
        }
        if (result == null) {
            try {
                result = getResourceStream(pluginid, resource);
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
    public static URL getResourceURL(final String pluginid, final String resource) throws IOException {
        return Platform.getBundle(pluginid).getResource(resource);                
    }

    /**
     * Read the version of this plug-in.
     * 
     * @param pluginId the plugin from which the version is to be returned.
     * @return the version of this plug-in
     */
    public static String getPluginVersion(final String pluginId) {
        String version = "<unknown>";
        //CHECKSTYLEOFF EmptyBlock
        try {
            version = Platform.getBundle(pluginId).getVersion().toString();
        } catch (Exception e) {
            // Hmmm... Not sure what to do in this case
        }
        //CHECKSTYLEON EmptyBlock
        return version;
    }
    
    /**
     * Private Constructor. Utility class must not
     * be instantiated.
     */
    private Resources() {
    }

}
