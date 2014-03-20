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

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import org.adaptagrams.AlignmentConstraint;
import org.adaptagrams.Dim;
import org.adaptagrams.SeparationConstraint;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.properties.CycleTreatment;
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
        progressMonitor.begin("Cola Direction Constraints", 1);

        float spacing = graph.getProperty(LayoutOptions.SPACING);

        Set<Set<CNode>> sccs = ColaUtil.findStronglyConnectedComponents(graph);
        Map<CNode, Set<CNode>> nodeSccMap = Maps.newHashMap();
        for (Set<CNode> scc : sccs) {
            for (CNode n : scc) {
                nodeSccMap.put(n, scc);
            }
        }

        CycleTreatment cycleTreatment = graph.getProperty(ColaProperties.CYCLE_TREATMENT);

        // calculate FAS edges
        Set<CEdge> fasEdges = Sets.newHashSet();
        switch (cycleTreatment) {
        case MFAS_SCC:
            for (Set<CNode> scc : sccs) {
                fasEdges.addAll(ColaUtil.findMinimalFAS(Lists.newLinkedList(scc)));
            }
            break;

        case MFAS_GLOBAL:
            fasEdges.addAll(ColaUtil.findMinimalFAS(graph.getChildren()));
            break;

        case ALIGN_SCC:
            // create alignment for scc
            for (Set<CNode> scc : sccs) {
                if (scc.size() == 1) {
                    continue;
                }
                AlignmentConstraint ac = new AlignmentConstraint(Dim.XDIM);
                System.out.println("Generated " + ac);
                for (CNode cn : scc) {
                    ac.addShape(cn.cIndex, 0);
                }
                graph.constraints.add(ac);
            }
            break;

        default:
            // nothing to do
        }

        System.out.println("FAS: " + fasEdges);

        // TODO only left to right atm
        for (CNode n : graph.getChildren()) {

            for (CEdge e : n.getOutgoingEdges()) {

                if (EnumSet.of(CycleTreatment.NONE, CycleTreatment.ALIGN_SCC).contains(
                        cycleTreatment)) {
                    // don't create constraints if the nodes are in the same scc
                    if (nodeSccMap.get(e.getSource()).contains(e.getTarget())) {
                        continue;
                    }
                } else if (EnumSet.of(CycleTreatment.MFAS_SCC, CycleTreatment.MFAS_GLOBAL)
                        .contains(cycleTreatment)) {
                    // don't create constraints for nodes in the FAS
                    if (fasEdges.contains(e)) {
                        System.out.println("Excluding: " + e.getProperty(ColaProperties.ORIGIN));
                        continue;
                    }
                }

                Margins srcMargins = e.getSource().getProperty(LayoutOptions.MARGINS);
                Margins tgtMargins = e.getTarget().getProperty(LayoutOptions.MARGINS);

                // separation has to go from mid to mid
                double widthSeparation =
                        (e.getSource().getSize().x + srcMargins.right + srcMargins.left) / 2f
                                + (e.getTarget().getSize().x + tgtMargins.left + tgtMargins.right)
                                / 2f;
                SeparationConstraint sc =
                        new SeparationConstraint(Dim.XDIM, e.getSource().cIndex,
                                e.getTarget().cIndex, widthSeparation + spacing);

                System.out.println("Generated " + sc + " for " + e);

                graph.constraints.add(sc);
            }
        }

        progressMonitor.done();
    }

}
