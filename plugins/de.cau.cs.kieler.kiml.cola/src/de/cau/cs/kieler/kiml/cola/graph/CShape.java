/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.cola.graph;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * .
 * 
 * @param <T>
 * 
 * @author uru
 */
public class CShape<T extends KGraphElement> extends CGraphElement<T> {

    private static final long serialVersionUID = 7543591108386925637L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    protected KShapeLayout layout;

    public CShape(final CGraph graph, final T element) {
        super(graph, element);
        layout = element.getData(KShapeLayout.class);
    }

    public KVector getPos() {
        return new KVector(layout.getXpos(), layout.getYpos());
    }

    public KVector getSize() {
        return new KVector(layout.getWidth(), layout.getHeight());
    }

}
