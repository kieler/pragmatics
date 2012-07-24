/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.importer.util;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A utility class that provides functionality that can be accessed by xtend
 * transformations.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public final class XtendUtil {
    /**
     * A private constructor to make the class not instantiable.
     */
    private XtendUtil() {
        // do nothing
    }

    /**
     * Returns the shape layout for the given node.
     * 
     * @param node
     *            the node
     * @return the shape layout
     */
    public static KShapeLayout getShapeLayout(final KNode node) {
        KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        return shapeLayout;
    }

    /**
     * Returns the edge layout for the given edge.
     * 
     * @param edge
     *            the edge
     * @return the edge layout
     */
    public static KEdgeLayout getEdgeLayout(final KEdge edge) {
        KEdgeLayout shapeLayout = edge.getData(KEdgeLayout.class);
        return shapeLayout;
    }
}
