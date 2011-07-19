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

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.Platform;

/**
 * Utilities for access to plugin resources.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class Io {

    /** Default buffer size for io operations. */
    private static final int BUFFER_SIZE
        = 10240;
    
    /**
     * Creates an input stream to a bundled resource.
     * 
     * @param pluginid
     *            id of the plugin the resource is bundled with
     * @param resource 
     *            path to the resource within this plugin
     * @return an input stream to the resource
     * @throws NoSuchResourceException
     *             if the specified resource could not be found
     */
    public static InputStream getResourceStream(final String pluginid, final String resource) {
        try {
            return Platform.getBundle(pluginid).getResource(resource).openStream();
        } catch (Exception e) {
            throw new NoSuchResourceException(e);
        }
    }

    /**
     * Reads from a stream with fixed size.
     * 
     * @param stream
     *            the stream to be read
     * @return String with stream contents
     * 
     * @throws IOException 
     */
    public static String readStream(final InputStream stream) throws IOException {       
        StringBuffer data = new StringBuffer();
        byte[] buffer = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = stream.read(buffer)) > 0) {
            data.append(new String(buffer, 0, read));
        }
        return data.toString();    
    }

    /**
     * Private constructor.
     */
    private Io() {        
    }

}
