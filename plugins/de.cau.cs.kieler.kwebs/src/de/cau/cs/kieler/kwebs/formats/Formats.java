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

package de.cau.cs.kieler.kwebs.formats;

import java.util.LinkedList;
import java.util.List;

/**
 * Utility class defining the supported formats for the layout service. A format
 * is a combination of the meta model of a a graph and the used serialization. 
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *     
 * @author swe
 */
public final class Formats {

    /** Constant defining the KGraph model in XMI serialization. */
    public static final String FORMAT_KGRAPH_XMI
        = "de.cau.cs.kieler.format.kgraph.xmi";

    /** The list of supported formats. */
    private static final List<String> SUPPORTED_FORMATS
        = new LinkedList<String>();
    
    /** Initialize the list of supported formats. */
    static {
        SUPPORTED_FORMATS.add(FORMAT_KGRAPH_XMI);
    }
    
    /**
     * Tests whether a given format identifier is supported
     * by the layout service.
     *
     * @param format
     *            The format identifier to be tested
     *
     * @return boolean
     *             Whether the given format is supported or not
     */
    public static boolean isSupportedFormat(final String format) {
        return SUPPORTED_FORMATS.contains(format);
    }

    /**
     * Private constructor since this utility class shall not be instantiated.
     */
    private Formats() {
    }

}
