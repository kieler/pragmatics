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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;

/**
 * .
 * 
 * @param <T>
 *            .
 * @author uru
 */
public class CGraphElement<T extends KGraphElement> {

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
    }

    public <S> S getProperty(final IProperty<S> prop) {
        KLayoutData layout = origin.getData(KLayoutData.class);
        return layout.getProperty(prop);
    }

}
