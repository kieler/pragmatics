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
package de.cau.cs.kieler.kiml.util;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A layout provider that does nothing, except declaring that the graph was not layouted.
 *
 * @author msp
 */
public class NullLayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Null Layout", 1);
        
        // invalidate all nodes, ports, edges and labels of the graph
        for (KNode node : layoutNode.getChildren()) {
            node.getData(KShapeLayout.class).setProperty(LayoutOptions.NO_LAYOUT, true);
            for (KPort port : node.getPorts()) {
                port.getData(KShapeLayout.class).setProperty(LayoutOptions.NO_LAYOUT, true);
            }
            for (KEdge edge : node.getOutgoingEdges()) {
                invalidateEdge(edge);
            }
            for (KEdge edge : node.getIncomingEdges()) {
                invalidateEdge(edge);
            }
        }
        
        progressMonitor.done();
    }
    
    /**
     * Invalidate an edge and its labels.
     * 
     * @param edge an edge
     */
    private void invalidateEdge(final KEdge edge) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        if (!edgeLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
            edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
            for (KLabel label : edge.getLabels()) {
                label.getData(KShapeLayout.class).setProperty(LayoutOptions.NO_LAYOUT, true);
            }
        }
    }

}
