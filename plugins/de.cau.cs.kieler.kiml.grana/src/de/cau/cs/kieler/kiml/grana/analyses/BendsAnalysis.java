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
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * A graph analysis that counts the number of bendpoints.
 * 
 * @author mri
 */
public class BendsAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(KNode parentNode, Map<String, Object> results,
            IKielerProgressMonitor progressMonitor) throws KielerException {
        progressMonitor.begin("Number of Bends analysis", 1);

        Integer numberOfBends = 0;
        List<KNode> nodes = new LinkedList<KNode>();
        nodes.add(parentNode);
        while (nodes.size() > 0) {
            // pop first element
            KNode node = nodes.remove(0);
            for (KEdge edge : node.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
                numberOfBends += edgeLayout.getBendPoints().size();
            }
            for (KNode childNode : node.getChildren()) {
                nodes.add(childNode);
            }
        }

        progressMonitor.done();
        return numberOfBends;
    }

}
