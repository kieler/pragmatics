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
package de.cau.cs.kieler.klay.codaflow.processors;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import org.adaptagrams.AlignmentConstraint;
import org.adaptagrams.Dim;
import org.adaptagrams.SeparationConstraint;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.adaptagrams.cgraph.CEdge;
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.codaflow.algs.EadesMFASHeuristic;
import de.cau.cs.kieler.klay.codaflow.algs.TrajansAlgorithm;
import de.cau.cs.kieler.klay.codaflow.properties.CodaflowProperties;
import de.cau.cs.kieler.klay.codaflow.properties.CycleTreatment;
import de.cau.cs.kieler.klay.codaflow.properties.HorizontalAlignment;

/**
 * Adds direction constraints to the graph. In other words, {@link SeparationConstraint}s are
 * created between nodes that are connected by an edge.
 * 
 * <h1>Avoiding Contradicting Constraints</h1> 
 * In case the graph contains cycles, contradicting constraints would be introduced for nodes 
 * that are part of a cycle. The way we handle this issue is determined by the value of the
 * {@link CodaflowProperties#CYCLE_TREATMENT} property.
 * property. 
 * 
 * <p>
 * <dl>
 *  <dt>{@link CycleTreatment#NONE}</dt>
 *  <dd>Contradicting constraints are created and cola is responsible to reject constraints.</dd>
 *  <dt>{@link CycleTreatment#IGNORE_SCCS}</dt>
 *  <dd>
 *      No separation constraints are created for nodes that are contained in the same strongly
 *      connected component.
 *  </dd> 
 *  <dt>{@link CycleTreatment#MFAS_GLOBAL}</dt>
 *  <dd>
 *      We calculate the 'minimal feedback arc set' using a heuristic by Eades et.al. and do not
 *      create {@link SeparationConstraint}s for edges that are part of the MFAS.
 *  </dd>
 *  <dt>...</dt>
 * </dl>
 * </p>
 * 
 * 
 * FIXME 
 * Known Problems:
 *  - if ports have different widths, the separation is not calculated properly
 *  - RIGHT and CENTER are not supported yet
 *  - ports are not included in the margin, thus ALWAYS added to the spacing
 *  
 * @author uru
 */
public class FlowConstraintProcessor implements ILayoutProcessor {

    /** The graph to handle. */
    private CGraph graph;
    /** The user-specified treatment of cycles. */
    private CycleTreatment cycleTreatment;
    /** The distance of a port dummy to the parent node. */
    private float portBreath = 0f;
    
    // Note that the following collections might be uninitialized based an
    // the current value of cycleTreatment.
    /** A set possibly containing all strongly connected cycles of the graph. */
    private Set<Set<CNode>> sccs = Sets.newHashSet();
    /** A map that maps nodes to the strongly connected component they are part of. */
    private Map<CNode, Set<CNode>> nodeSccMap = Maps.newHashMap();
    /** A set containing all edges that are part of the minimal feedback arc set of the graph. */
    private Set<CEdge> fasEdges = Sets.newHashSet();
    
    /**
     * {@inheritDoc}
     */
    public void process(final CGraph theGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Flow Constraints Generation", 1);

        this.graph = theGraph;
        
        if (!graph.getProperty(CodaflowProperties.EMPHASIZE_DIRECTION)) {
            progressMonitor.done();
            return;
        }

        if (theGraph.getProperty(CodaflowProperties.PORT_DUMMIES)) {
            // when we use dummy ports, we let them breathe a bit from its parent node
            portBreath = theGraph.getProperty(CodaflowProperties.PORT_DUMMY_BREATHE);
        }
        cycleTreatment = theGraph.getProperty(CodaflowProperties.CYCLE_TREATMENT);

        // initialize some internal information we require to determine whether to exclude specific
        // edges or not.
        initializeInternally();

        // generate the actual separation constraints between connected nodes
        generateSeparationConstraints();
        
        if (cycleTreatment == CycleTreatment.ALIGN_SCC) {
            // create an alignment constraints for all strongly connected components
            // that contain more than one node
            alignSCCs();
        }

        progressMonitor.done();
    }

    /**
     * Depending on the value of {@code DirectionConstraintProcessor#cycleTreatment}, initializes
     * internal maps.
     */
    private void initializeInternally() {
        
        // clear the collections
        sccs.clear();
        nodeSccMap.clear();
        fasEdges.clear();
        
        // if a specific cycle treatment is requested, we require knowledge 
        if (cycleTreatment == CycleTreatment.NONE) {
            return;
        }

        // calculate all strongly connected components
        if (EnumSet.of(CycleTreatment.IGNORE_SCCS, CycleTreatment.ALIGN_SCC).contains(cycleTreatment)) {
            sccs.addAll(TrajansAlgorithm.getStronglyConnectedComponents(graph));
            // for every node remember which SCC it belongs to
            for (Set<CNode> scc : sccs) {
                for (CNode n : scc) {
                    nodeSccMap.put(n, scc);
                }
            }
        }
        
        // calculate feedback arc sets edges
        switch (cycleTreatment) {
        case MFAS_SCC:
            for (Set<CNode> scc : sccs) {
                fasEdges.addAll(EadesMFASHeuristic.execute(Lists.newArrayList(scc)));
            }
            break;

        case MFAS_GLOBAL:
            fasEdges.addAll(EadesMFASHeuristic.execute(graph.getChildren()));
            break;

        default:
            // nothing to do
        }
    }

    /**
     * Generates the {@link SeparationConstraint}s based on the user-specified
     * {@link HorizontalAlignment}.
     */
    private void generateSeparationConstraints() {
        HorizontalAlignment vAlignment = graph.getProperty(CodaflowProperties.ALIGN_NODES);
        switch (vAlignment) {
        case LEFT:
            leftAlign();
            break;

        case CENTER:
            throw new UnsupportedOperationException("CenterAlignment is not supported yet.");

        case RIGHT:
            throw new UnsupportedOperationException("RightAlignment is not supported yet.");

        default: // NONE
            withoutAlign();
            break;
        }
    }
    
    /**
     * Align nodes wrt their left boundary.
     * 
     * The left margin of the target node is added to the spacing as it is not considered for the
     * alignment.
     */
    private void leftAlign() {
        for (CNode tgt : graph.getChildren()) {

            // for every node with incoming edges, find the widest of the incoming edges
            double maxWidth = Double.MIN_VALUE;
            for (CEdge e : tgt.getIncomingEdges()) {
                if (excludeEdge(e)) {
                    continue;
                }
                maxWidth = Math.max(maxWidth, e.getSource().getRectSizeRaw().x);
            }

            // now create proper separation constraints
            for (CEdge e : tgt.getIncomingEdges()) {

                if (excludeEdge(e)) {
                    continue;
                }

                CNode src = e.getSource();

                double d = tgt.getRectSizeRaw().x / 2f 
                        + actualSpacing(e)
                        + tgt.getMargins().left
                        + maxWidth / 2f
                        + (maxWidth / 2f - src.getRectSizeRaw().x / 2f);

                // consider left margin of the src node
                d += src.getMargins().left; 

                createSeparationConstraint(src.cIndex, e.getTarget().cIndex, d);
            }
        }
    }
    
    /**
     * Just generate the separation constraints without any additional considerations.
     */
    private void withoutAlign() {
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                if (excludeEdge(e)) {
                    continue;
                }

                CNode src = e.getSource();
                CNode tgt = e.getTarget();

                // separation has to go from mid to mid
                double d = src.getRectSizeRaw().x / 2f
                        + actualSpacing(e) 
                        + tgt.getRectSizeRaw().x / 2f;

                createSeparationConstraint(src.cIndex, tgt.cIndex, d);
            }
        }
    }
    
    /**
     * Minimal distance between connected nodes.
     */
    private double actualSpacing(final CEdge e) {
        // we have to assure a minimal distance such that nodes do not overlap
        return portBreath;
    }
    
    /**
     * Generates an <b>in</b>equality constraint with the given values.
     */
    private void createSeparationConstraint(final int left, final int right, final double gap) {
        SeparationConstraint sc = new SeparationConstraint(Dim.XDIM, left, right, gap);
        graph.constraints.add(sc);
    }
    
    /**
     * Generates alignment constraints for nodes within the same strongly connected component.
     */
    private void alignSCCs() {
        // create alignment for scc
        for (Set<CNode> scc : sccs) {
            if (scc.size() == 1) {
                continue;
            }
            AlignmentConstraint ac = new AlignmentConstraint(Dim.XDIM);
            for (CNode cn : scc) {
                ac.addShape(cn.cIndex, 0);
            }
            graph.constraints.add(ac);
        }
    }
    
    /**
     * @return {@code true} if no {@link SeparationConstraint} should be create for the passed edge.
     */
    private boolean excludeEdge(final CEdge e) {
        if (cycleTreatment == CycleTreatment.NONE) {
            return false;
        } else if (EnumSet.of(CycleTreatment.IGNORE_SCCS, CycleTreatment.ALIGN_SCC).contains(
                cycleTreatment)) {
            // don't create constraints if the nodes are in the same scc
            if (nodeSccMap.get(e.getSource()).contains(e.getTarget())) {
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
