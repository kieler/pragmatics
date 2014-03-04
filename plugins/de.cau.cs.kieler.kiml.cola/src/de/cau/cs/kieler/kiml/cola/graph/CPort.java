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

import org.adaptagrams.ColaEdge;
import org.adaptagrams.Dim;
import org.adaptagrams.Rectangle;
import org.adaptagrams.SeparationConstraint;

import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * @author uru
 *
 */
public class CPort {

    public final int cIndex;
    public final int cEdgeIndex;
    public final Rectangle rect;
    
    public final double idealDummyEdgeLength;
    
    /**
     * 
     */
    public CPort(final CGraph graph, final KPort p, CNode parentNode) {
        
        KShapeLayout portLayout = p.getData(KShapeLayout.class);

        rect =
                new Rectangle(0, 0 + portLayout.getWidth(), 0, 0 + portLayout.getHeight());
        cIndex = graph.nodeIndex++;
        
        graph.nodes.add(rect);

        // connect by edge
        ColaEdge dummyEdge = new ColaEdge(parentNode.cIndex, cIndex);
        cEdgeIndex = graph.edgeIndex++;
        graph.edges.add(dummyEdge);

        KShapeLayout layout = parentNode.origin.getData(KShapeLayout.class);
        
        // constraints refer to the center of a node
        double halfX = layout.getWidth() / 2f - portLayout.getWidth() / 2f;
        double halfY = layout.getHeight() / 2f - portLayout.getHeight() / 2f;

        // generate sep constrs
        SeparationConstraint scX =
                new SeparationConstraint(Dim.XDIM, parentNode.cIndex, cIndex,
                        portLayout.getXpos() - halfX, true);
        graph.constraints.add(scX);
        SeparationConstraint scY =
                new SeparationConstraint(Dim.YDIM, parentNode.cIndex, cIndex,
                        portLayout.getYpos() - halfY, true);
        graph.constraints.add(scY);

        // calculate the fixed distance of the dummy to the center
        KVector center = new KVector(layout.getWidth() / 2f, layout.getHeight() / 2f);
        KVector portPos =
                new KVector(portLayout.getXpos() - halfX, portLayout.getYpos() - halfY);

        idealDummyEdgeLength = KVector.distance(center, portPos) + 10;
    }
    
}
