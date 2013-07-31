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
package de.cau.cs.kieler.klay.tree.util;

import java.util.Comparator;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.klay.tree.graph.TEdge;

/**
 * Sorts a list of TEdges by the given property of the targets of the edges.
 * 
 * @author sor
 * @author sgu
 */
public class SortTEdgeTargetProperty implements Comparator<TEdge> {
    private IProperty<Integer> property;

    public SortTEdgeTargetProperty(IProperty<Integer> property) {
        this.property = property;
    }

    public int compare(TEdge t1, TEdge t2) {
        return t2.getTarget().getProperty(property) - t1.getTarget().getProperty(property);
    }
}
