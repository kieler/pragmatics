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

import java.util.ListIterator;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A layout provider that sets fixed positions for all elements. Elements that have no position
 * option attached just stay where they are.
 *
 * @author msp
 */
public class FixedLayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Null Layout", 1);
        
        for (KNode node : layoutNode.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            KVector pos = nodeLayout.getProperty(LayoutOptions.POSITION);
            if (pos == null) {
                nodeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
            } else {
                nodeLayout.setXpos((float) pos.x);
                nodeLayout.setYpos((float) pos.y);
            }
            
            for (KPort port : node.getPorts()) {
                KShapeLayout portLayout = port.getData(KShapeLayout.class);
                pos = portLayout.getProperty(LayoutOptions.POSITION);
                if (pos == null) {
                    portLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                } else {
                    portLayout.setXpos((float) pos.x);
                    portLayout.setYpos((float) pos.y);
                }
            }
            
            for (KEdge edge : node.getOutgoingEdges()) {
                processEdge(edge);
            }
            for (KEdge edge : node.getIncomingEdges()) {
                if (edge.getSource().getParent() != layoutNode) {
                    processEdge(edge);
                }
            }
        }
        
        progressMonitor.done();
    }
    
    /**
     * Process an edge and its labels.
     * 
     * @param edge an edge
     */
    private void processEdge(final KEdge edge) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KVectorChain bendPoints = edgeLayout.getProperty(LayoutOptions.BEND_POINTS);
        if (bendPoints == null || bendPoints.size() < 2) {
            edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
        } else {
            KPoint sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            KVector firstPoint = bendPoints.getFirst();
            sourcePoint.setX((float) firstPoint.x);
            sourcePoint.setY((float) firstPoint.y);
            edgeLayout.setSourcePoint(sourcePoint);
            
            edgeLayout.getBendPoints().clear();
            ListIterator<KVector> pointIter = bendPoints.listIterator(1);
            while (pointIter.nextIndex() < bendPoints.size() - 1) {
                KPoint bendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                KVector nextPoint = pointIter.next();
                bendPoint.setX((float) nextPoint.x);
                bendPoint.setY((float) nextPoint.y);
                edgeLayout.getBendPoints().add(bendPoint);
            }
            
            KPoint targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            KVector lastPoint = bendPoints.getLast();
            targetPoint.setX((float) lastPoint.x);
            targetPoint.setY((float) lastPoint.y);
            edgeLayout.setTargetPoint(targetPoint);
        }
        
        for (KLabel label : edge.getLabels()) {
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            KVector pos = labelLayout.getProperty(LayoutOptions.POSITION);
            if (pos == null) {
                labelLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
            } else {
                labelLayout.setXpos((float) pos.x);
                labelLayout.setYpos((float) pos.y);
            }
        }
    }

}
