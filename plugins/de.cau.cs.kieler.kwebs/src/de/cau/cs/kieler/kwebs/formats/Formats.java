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

/**
 * Utility class defining the supported formats for the layout service.
 *
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
public final class Formats {

    /**
     * Private constructor.
     */
    private Formats() {
    }

    /** Constant defining the KGraph model in XMI serialization. */
    public static final String FORMAT_KGRAPH_XMI
        = "de.cau.cs.kieler.format.kgraph.xmi"; //$NON-NLS-1$

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
        return format.equals(FORMAT_KGRAPH_XMI);
    }

}
