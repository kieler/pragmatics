/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.intermediate;

import java.util.List;
import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * TODO Document this class
 * 
 * @author sor
 * @author sgu
 * 
 */
public class TreeingProcessor implements ILayoutProcessor {

    /**
     * TODO Document
     */
    private List<TEdge> edges = new LinkedList<TEdge>();

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        edges.clear();
        edges = tGraph.getProperty(Properties.REMOVABLE_EDGES);

        for (TEdge e : edges) {
            TNode src = e.getSource();
            TNode target = e.getTarget();
            tGraph.addEdge(src, target);
        }
    }
}
