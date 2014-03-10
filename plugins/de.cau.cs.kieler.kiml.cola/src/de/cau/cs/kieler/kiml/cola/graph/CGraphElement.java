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
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;

/**
 * .
 * 
 * @param <T>
 *            .
 * @author uru
 */
public class CGraphElement<T extends KGraphElement> extends MapPropertyHolder {

    /**
     * 
     */
    private static final long serialVersionUID = -8909829166918218344L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    /**
     * Index of this element within an adaptagrams array. E.g., the index of a node in the nodes
     * array.
     */
    public int cIndex;

    public final T origin;

    public final CGraph graph;

    public CGraphElement(final CGraph graph, final T element) {
        this.graph = graph;
        this.origin = element;

        // copy the properties from the KGraph element to the CGraph element
        KLayoutData layoutData = element.getData(KLayoutData.class);
        if (layoutData != null) {
            copyProperties(layoutData);
        }
    }

}
