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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.cola.algs.EadesMFASHeuristic;
import de.cau.cs.kieler.klay.cola.algs.TrajansAlgorithm;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.properties.CycleTreatment;
import de.cau.cs.kieler.klay.cola.properties.HorizontalAlignment;

/**
 * Adds direction constraints to the graph.
 * 
 * @author uru
 */
public class DirectionConstraintProcessor implements ILayoutProcessor {

    private CycleTreatment cycleTreatment;

    private Set<Set<CNode>> sccs;
    private Map<CNode, Set<CNode>> nodeSccMap;

    private Set<CEdge> fasEdges;

    
    /**
     * {@inheritDoc}
     */
    public void process(final CGraph graph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Cola Direction Constraints", 1);

        float spacing = graph.getProperty(LayoutOptions.SPACING);
        cycleTreatment = graph.getProperty(ColaProperties.CYCLE_TREATMENT);

        sccs = TrajansAlgorithm.getStronglyConnectedComponents(graph);
        nodeSccMap = Maps.newHashMap();
        for (Set<CNode> scc : sccs) {
            for (CNode n : scc) {
                nodeSccMap.put(n, scc);
            }
        }

        // calculate FAS edges
        fasEdges = Sets.newHashSet();
        switch (cycleTreatment) {
        case MFAS_SCC:
            for (Set<CNode> scc : sccs) {
                fasEdges.addAll(EadesMFASHeuristic.execute(Lists.newArrayList(scc)));
            }
            break;

        case MFAS_GLOBAL:
            fasEdges.addAll(EadesMFASHeuristic.execute(graph.getChildren()));
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

        HorizontalAlignment vAlignment = graph.getProperty(ColaProperties.ALIGN_NODES);

        switch (vAlignment) {

        /*
         * Align nodes wrt their left boundary.
         * 
         * The left margin of the target node is added to the spacing as it is not 
         * considered for the alignment.
         * 
         */
        case LEFT:

            for (CNode n : graph.getChildren()) {

                // for every node with incoming edges, find the widest of the incoming edges
                double maxWidth = Double.MIN_VALUE;
                for (CEdge e : n.getIncomingEdges()) {
                    KVector size = e.getSource().getRectSizeRaw();
                    if (maxWidth < size.x) {
                        maxWidth = size.x;
                    }
                }

                // now create proper separation constraints
                for (CEdge e : n.getIncomingEdges()) {

                    if (excludeEdge(e)) {
                        continue;
                    }

                    CNode src = e.getSource();

                    double d = n.getRectSizeRaw().x / 2f 
                            + spacing
                            + n.getMargins().left
                            + maxWidth / 2f
                            + (maxWidth / 2f - src.getRectSizeRaw().x / 2f);

                    // consider left margin of the src node
                    d += src.getMargins().left;

                    SeparationConstraint sc =
                            new SeparationConstraint(Dim.XDIM, e.getSource().cIndex,
                                    e.getTarget().cIndex, d);
                    graph.constraints.add(sc);
                }
            }
            
            break;

        /*
         * Align vertically stacked nodes wrt to their center point
         * 
         * TODO do we want to consider margins for alignment or not!?!
         * 
         */
        case CENTER:

            // TODO only left to right atm
            for (CNode n : graph.getChildren()) {

                // for every node with incoming edges, find the widest of the incoming edges
                double maxWidth = Double.MIN_VALUE;
                for (CEdge e : n.getIncomingEdges()) {
                    final KVector size = e.getSource().getRectSizeRaw();
                    if (maxWidth < size.x) {
                        maxWidth = size.x;
                    }
                }

                // one pass for incoming edges
                for (CEdge e : n.getIncomingEdges()) {

                    if (excludeEdge(e)) {
                        continue;
                    }

                    CNode src = e.getSource();
                    CNode tgt = e.getTarget();

                    // separation has to go from mid to mid
                    double d = tgt.getRectSizeRaw().x / 2f + spacing + maxWidth / 2f;

                    SeparationConstraint sc =
                            new SeparationConstraint(Dim.XDIM, e.getSource().cIndex,
                                    e.getTarget().cIndex, d);

                    graph.constraints.add(sc);
                }
                
                // for every node with incoming edges, find the widest of the outgoing edges
                maxWidth = Double.MIN_VALUE;
                for (CEdge e : n.getOutgoingEdges()) {
                    final KVector size = e.getTarget().getRectSizeRaw();
                    if (maxWidth < size.x) {
                        maxWidth = size.x;
                    }
                }

                // another pass for outgoing edges
                for (CEdge e : n.getOutgoingEdges()) {

                    if (excludeEdge(e)) {
                        continue;
                    }

                    CNode src = e.getSource();
                    CNode tgt = e.getTarget();

                    // separation has to go from mid to mid
                    double d = src.getRectSizeRaw().x / 2f + spacing + maxWidth / 2f;

                    SeparationConstraint sc =
                            new SeparationConstraint(Dim.XDIM, e.getSource().cIndex,
                                    e.getTarget().cIndex, d);

                    graph.constraints.add(sc);
                }
            }
            break;

        case RIGHT:

            for (CNode n : graph.getChildren()) {

                // for every node with incoming edges, find the widest of the incoming edges
                double maxWidth = Double.MIN_VALUE;
                for (CEdge e : n.getOutgoingEdges()) {
                    final KVector size = e.getTarget().getRectSizeRaw();
                    if (maxWidth < size.x) {
                        maxWidth = size.x;
                    }
                }

                for (CEdge e : n.getOutgoingEdges()) {

                    if (excludeEdge(e)) {
                        continue;
                    }

                    CNode src = e.getSource();
                    CNode tgt = e.getTarget();

                    // separation has to go from mid to mid
                    double d = src.getRectSizeRaw().x / 2f 
                            + spacing        
                            + maxWidth / 2f
                            - src.getMargins().right
                            + (maxWidth / 2f - tgt.getRectSizeRaw().x / 2f);

                    // consider right margin of the tgt node
                    d += tgt.getMargins().right;
                    
                    SeparationConstraint sc =
                            new SeparationConstraint(Dim.XDIM, e.getSource().cIndex,
                                    e.getTarget().cIndex, d
                            // FIXME the question is where to consider ports for spacing
                            // we add dummy nodes, hence dont need them in the margin,
                            // however, during separation calculation we need them!
                            );

                    graph.constraints.add(sc);
                }
            }
                
            break;

        default:
            // nada

        }

        progressMonitor.done();
    }

    /**
     * @return true if no {@link SeparationConstraint} should be create for the passed edge.
     */
    private boolean excludeEdge(final CEdge e) {
        if (EnumSet.of(CycleTreatment.NONE, CycleTreatment.ALIGN_SCC).contains(cycleTreatment)) {
            // don't create constraints if the nodes are in the same scc
            if (nodeSccMap.get(e.getSource()).contains(e.getTarget())) {
                System.out.println("Ignoring edge scc " + e);
                return true;
            }
        } else if (EnumSet.of(CycleTreatment.MFAS_SCC, CycleTreatment.MFAS_GLOBAL).contains(
                cycleTreatment)) {
            // don't create constraints for nodes in the FAS
            if (fasEdges.contains(e)) {
                return true;
            }
        }
        return false;
    }

}
