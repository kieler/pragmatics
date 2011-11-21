/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.labeling;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A simple layout provider that distributes edge labels along their edge. Highly
 * experimental stuff!
 * 
 * @author cds
 */
public class LabelLayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Label layout", 1);
        
        // Iterate over all the outgoing edges we can find
        for (KNode node : parentNode.getChildren()) {
            for (KEdge edge : node.getOutgoingEdges()) {
                distributeLabels(edge);
            }
        }
        
        progressMonitor.done();
    }

    /**
     * Computes a label placement for the labels of the given edge.
     * 
     * @param edge the edge whose labels to distribute.
     */
    private void distributeLabels(final KEdge edge) {
        /* This method implements an extremely naive first approach to placing labels. One of
         * the drawbacks is that this assumes that edges don't have any bend points. Much room
         * for improvement. Left as an exercise to the reader... ;)
         */
        
        // Check if there are any labels at all
        int labelCount = edge.getLabels().size();
        if (labelCount == 0) {
            return;
        }
        
        // Calculate the spacing between two label anchors on the edge
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KPoint edgeSource = edgeLayout.getSourcePoint();
        KPoint edgeTarget = edgeLayout.getTargetPoint();
        
        float deltaX = (edgeTarget.getX() - edgeSource.getX()) / (labelCount + 1);
        float deltaY = (edgeTarget.getY() - edgeSource.getY()) / (labelCount + 1);
        
        KVector currentLabelPos = new KVector(edgeSource.getX() + deltaX, edgeSource.getY() + deltaY);
        
        // Iterate through the labels
        for (KLabel label : edge.getLabels()) {
            KShapeLayout shapeLayout = label.getData(KShapeLayout.class);
            shapeLayout.setXpos((float) currentLabelPos.x);
            shapeLayout.setYpos((float) currentLabelPos.y);
            
            // Advance the current label position
            currentLabelPos.translate(deltaX, deltaY);
        }
    }

}
