/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

/**
 * A singleton factory for creating ogdf layouters by name.
 * 
 * @author mri
 */
public final class OgdfLayouterFactory {

    /** The singleton instance of OgdfLayouterFactory. */
    private static OgdfLayouterFactory eINSTANCE = new OgdfLayouterFactory();

    /**
     * Returns the singleton instance of OgdfLayouterFactory.
     * 
     * @return the singleton instance
     */
    public static OgdfLayouterFactory getInstance() {
        return eINSTANCE;
    }

    /** Definition of available layout algorithms. */
    private enum LayoutAlgorithm {
        SUGIYAMA, UMLPLANARIZATION
    }

    /**
     * Creates the required layouter defined by name.
     * 
     * @param name
     *            the name of the layouter
     * @return the layouter if name is a valid layouter name else null
     */
    public OgdfLayouter createLayouter(final String name) {
        switch (LayoutAlgorithm.valueOf(name)) {
        case SUGIYAMA:
            return new SugiyamaLayouter();
        case UMLPLANARIZATION:
            return new PlanarizationLayouter();
        default:
            return null;
        }
    }
}
