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

import java.util.List;

import com.google.common.collect.Lists;

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

    /** The KGraph model in XMI serialization. */
    public static final String FORMAT_KGRAPH_XMI
        = "de.cau.cs.kieler.kgraph";
    /** The KGraph model in compressed XMI serialization. */
    public static final String FORMAT_KGRAPH_XMI_COMPRESSED
        = "de.cau.cs.kieler.kgraph.gz";
    /** The OGML format. */
    public static final String FORMAT_OGML
        = "net.ogdf.ogml";
    /** The GraphML format. */
    public static final String FORMAT_GRAPHML
        = "org.graphdrawing.graphml";
    /** The Graphviz Dot format. */
    public static final String FORMAT_DOT
        = "org.graphviz.dot";
    /** The Matrix Market format. */
    public static final String FORMAT_MATRIX
        = "gov.nist.math.matrix";

    /** The list of supported formats. */
    private static final List<String> SUPPORTED_FORMATS
        = Lists.newArrayList(
            FORMAT_KGRAPH_XMI, FORMAT_KGRAPH_XMI_COMPRESSED, FORMAT_OGML, FORMAT_GRAPHML, FORMAT_DOT,
            FORMAT_MATRIX
        );
    
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
