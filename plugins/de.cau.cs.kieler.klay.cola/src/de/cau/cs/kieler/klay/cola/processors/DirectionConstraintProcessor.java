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
package de.cau.cs.kieler.klay.cola.processors;

import java.util.Map;
import java.util.Set;

import org.adaptagrams.Dim;
import org.adaptagrams.SeparationConstraint;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.util.ColaUtil;

/**
 * Adds direction constraints to the graph.
 * 
 * @author uru
 */
public class DirectionConstraintProcessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final CGraph graph, final IKielerProgressMonitor progressMonitor) {

        float spacing = graph.getProperty(LayoutOptions.SPACING);

        Set<Set<KNode>> sccs = ColaUtil.findStronglyConnectedComponents(graph.origin);
        Map<KNode, Set<KNode>> nodeSccMap = Maps.newHashMap();
        for (Set<KNode> scc : sccs) {
            for (KNode n : scc) {
                nodeSccMap.put(n, scc);
            }
        }

        // TODO only left to right atm
        for (CNode n : graph.getChildren()) {

            for (CEdge e : n.getOutgoingEdges()) {

                // dont create constraints if the nodes are in the same scc
                if (nodeSccMap.get(e.getSource().origin).contains(e.getTarget().origin)) {
                    continue;
                }

                Margins srcMargins = e.getSource().getProperty(LayoutOptions.MARGINS);
                Margins tgtMargins = e.getTarget().getProperty(LayoutOptions.MARGINS);

                // separation has to go from mid to mid
                double widthSeparation =
                        (e.getSource().getSize().x / 2f + srcMargins.right + srcMargins.left)
                                + (e.getTarget().getSize().x / 2f + tgtMargins.left + tgtMargins.right);
                SeparationConstraint sc =
                        new SeparationConstraint(Dim.XDIM, e.getSource().cIndex,
                                e.getTarget().cIndex, widthSeparation + spacing);

                // System.out.println("Spacing: " + spacing);

                graph.constraints.add(sc);
            }
        }

    }

}
