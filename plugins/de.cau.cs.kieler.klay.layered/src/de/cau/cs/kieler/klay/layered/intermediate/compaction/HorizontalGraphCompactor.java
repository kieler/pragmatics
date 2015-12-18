/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.compaction.oned.CGroup;
import de.cau.cs.kieler.klay.layered.compaction.oned.CNode;
import de.cau.cs.kieler.klay.layered.compaction.oned.ISpacingsHandler;
import de.cau.cs.kieler.klay.layered.compaction.oned.OneDimensionalCompactor;
import de.cau.cs.kieler.klay.layered.compaction.oned.OneDimensionalCompactor.ICompactionAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.networksimplex.NEdge;
import de.cau.cs.kieler.klay.layered.networksimplex.NGraph;
import de.cau.cs.kieler.klay.layered.networksimplex.NNode;
import de.cau.cs.kieler.klay.layered.networksimplex.NetworkSimplex;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.properties.Spacings;

/**
 * This processor applies additional compaction to an already routed graph and can be executed after
 * {@link de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter OrthogonalEdgeRouter}.
 * Therefore nodes and vertical segments of edges are repositioned in the specified direction where
 * the position is minimal considering the desired spacing between elements.
 * 
 * <p>
 * Since the locking functionality in {@link CLNode} and {@link CLEdge} relies on the direction of
 * incoming and outgoing edges, this processor is required to be executed before the
 * {@link de.cau.cs.kieler.klay.layered.intermediate.ReversedEdgeRestorer ReversedEdgeRestorer}.
 * </p>
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>The edges are routed orthogonally</dd>
 * <dt>Postcondition:</dt>
 * <dd>Nodes and edges are positioned compact without colliding.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 5.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>After {@link de.cau.cs.kieler.klay.layered.intermediate.LabelDummyRemover LabelDummyRemover}</dd>
 * <dd>Before {@link de.cau.cs.kieler.klay.layered.intermediate.ReversedEdgeRestorer
 * ReversedEdgeRestorer}</dd>
 * </dl>
 * 
 * @author dag
 */
public class HorizontalGraphCompactor implements ILayoutProcessor {

    private LGraph lGraph;
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        GraphCompactionStrategy strategy = layeredGraph.getProperty(Properties.POST_COMPACTION);
        if (strategy == GraphCompactionStrategy.NONE) {
            return;
        }
        progressMonitor.begin("Horizontal Compaction", 1);
        
        this.lGraph = layeredGraph;
        
        // the layered graph is transformed into a CGraph that is passed to OneDimensionalCompactor
        LGraphToCGraphTransformer transformer = new LGraphToCGraphTransformer();
        OneDimensionalCompactor odc =
                new OneDimensionalCompactor(transformer.transform(layeredGraph));
        
        odc.setSpacingsHandler(SPACINGS_HANDLER);
        
        switch (strategy) {
        case LEFT:
            odc.compact()
            .drawHitboxes("d:/tmp/cc/1.svg");
            break;
            
        case RIGHT:
            odc.changeDirection(Direction.RIGHT)
               .compact();
            break;
            
        case LEFT_RIGHT_CONSTRAINT_LOCKING:
            // the default locking strategy locks CNodes if they are not constrained
            odc.compact()
               .changeDirection(Direction.RIGHT)
               .applyLockingStrategy()
               .compact();
            break;
            
        case LEFT_RIGHT_CONNECTION_LOCKING:
            // compacting left, locking all CNodes that have fewer connections to the right,
            // then compacting right to shorten unnecessary long edges
            odc.compact()
               .changeDirection(Direction.RIGHT)
               .setLockingStrategy((n, d) -> !n.lock.get(d))
               .applyLockingStrategy()
               .compact();
            break;
         
        case EDGE_LENGTH:
            
            odc.setCompactionAlgorithm(NETWORK_SIMPLEX_COMPACTION)
               .compact()
               .drawHitboxes("d:/tmp/cc/2.svg");
            
            break;

        default:
            // nobody should get here
            break;
        }
        
        // since changeDirection may transform hitboxes, the final direction has to be LEFT again
        odc.finish();
        
        // applying the compacted positions to the LGraph and updating its size and offset
        transformer.applyLayout();
        
        progressMonitor.done();
    }
    
    /**
     * An implementation of a {@link ISpacingsHandler} that is able to cope with the special
     * requirements of {@link LGraph}s. For instance, there are special cases for the spacing
     * between {@link CLEdge}s as opposed to {@link CLNode}s.
     */
    private final ISpacingsHandler<CNode> SPACINGS_HANDLER = new ISpacingsHandler<CNode>() {
        
        /**
         * {@inheritDoc}
         */
        @Override
        public double getHorizontalSpacing(final CNode cNode1, final CNode cNode2) {
            
            // joining north/south segments that belong to the same edge 
            // by setting their spacing to 0
            if (cNode1.parentNode != null
                    && cNode2.parentNode != null
                    && (cNode1 instanceof CLEdge && cNode2 instanceof CLEdge)
                    // this might seem quite expensive but in most cases the sets contain only
                    // one element
                    && !Sets.intersection(((CLEdge) cNode2).originalLEdges,
                            ((CLEdge) cNode2).originalLEdges).isEmpty()) {
                return 0;
            }
            
            
            // at this point we know that the passed cnodes are either a CLNode or a CLEdge
            
            LNode node1 = null;
            if (cNode1 instanceof CLNode) {
                node1 = ((CLNode) cNode1).getlNode();
            }
            
            LNode node2 = null;
            if (cNode2 instanceof CLNode) {
                node2 = ((CLNode) cNode2).getlNode();
            } 
            
            
            if ((node1 != null && node1.getType() == NodeType.EXTERNAL_PORT)
                    || (node2 != null && node2.getType() == NodeType.EXTERNAL_PORT)) {
                return 0;
            }
            
            Spacings spacings = lGraph.getProperty(InternalProperties.SPACINGS);
            
            return spacings.getHorizontalSpacing(
                    node1 != null ? node1.getType() : NodeType.LONG_EDGE, 
                    node2 != null ? node2.getType() : NodeType.LONG_EDGE);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public double getVerticalSpacing(final CNode cNode1, final CNode cNode2) {

            // joining north/south segments that belong to the same edge 
            // by setting their spacing to 0
            if (cNode1.parentNode != null
                    && cNode2.parentNode != null
                    && (cNode1 instanceof CLEdge && cNode2 instanceof CLEdge)
                    // this might seem quite expensive but in most cases the sets contain only
                    // one element
                    && !Sets.intersection(((CLEdge) cNode2).originalLEdges,
                            ((CLEdge) cNode2).originalLEdges).isEmpty()) {
                return 0;
            }

            // TODO really min?
            return Math.min(cNode1.getVerticalSpacing(), cNode2.getVerticalSpacing());
        }
        
    };
    
    
    /**
     * Compaction algorithm based on the network simplex algorithm presented by Gansner et al. in
     * the context of layering.
     * 
     * @see NetworkSimplex
     */
    private static final ICompactionAlgorithm NETWORK_SIMPLEX_COMPACTION =
            compactor -> {
                // based on gansner
                
                NNode[] nNodes = new NNode[compactor.cGraph.cGroups.size()];
                
                NGraph networkSimplexGraph = new NGraph();

                // TODO add imaginary source and sink
                
                // create a node in the network simplex graph for each group
                int index = 0;
                for (CGroup cGroup: compactor.cGraph.cGroups) {
                    
                    cGroup.id = index;
                    NNode nNode = NNode.of()
                      .id(index)
                      .origin(cGroup)
                      .create(networkSimplexGraph);
                    nNodes[index] = nNode;
                    
                    index++;
                }
                
                // #2 transform constraint graph edges
                for (CNode cNode : compactor.cGraph.cNodes) {
                    
                    for (CNode incNode : cNode.constraints) {
                        
                        // we require all group offsets to be positive
                        // relative to the left most element of the group
                        
                        double spacing;
                        if (compactor.direction.isHorizontal()) {
                            spacing = compactor.spacingsHandler.getHorizontalSpacing(cNode, incNode);
                        } else {
                            spacing = compactor.spacingsHandler.getVerticalSpacing(cNode, incNode);
                        }
                        
                        // TODO handle vertical segments correctly here!
                        System.out.println(cNode + " " + incNode + " spacing: " + spacing);
                        
                        // we want to compact to the left 
                        // and the constraint edges look as follows: 
                        //   cNode <-- incNode
                        double delta = cNode.cGroupOffset.x 
                                       + cNode.hitbox.width
                                       + spacing
                                       + incNode.cGroupOffset.x;
                        
                        System.out.println("Distance2 between " + cNode + " " + incNode + " " + delta);
                        
                        // network simplex requires an integer for delta
                        delta = Math.ceil(delta);
                        
                        NEdge.of()
                          .delta((int) delta)
                          .weight(1) // no weight here, it only preserves separations
                          .source(nNodes[cNode.cGroup.id])
                          .target(nNodes[incNode.cGroup.id])
                          .create();
                        
                    }
                    
                }
               
                // #3 add the original graph's edges for edge length minimization
                // TODO how to get them ;)?
                Map<LNode, CNode> lNodeMap = Maps.newHashMap();
                for (CNode cNode : compactor.cGraph.cNodes) {
                    if (cNode instanceof CLNode) {
                        LNode lNode = ((CLNode) cNode).getlNode();
                        lNodeMap.put(lNode, cNode);
                    }
                }
                
                for (CNode cNode : compactor.cGraph.cNodes) {
                    if (cNode instanceof CLNode) {
                        LNode lNode = ((CLNode) cNode).getlNode();
                        for (LEdge lEdge : lNode.getOutgoingEdges()) {
                            
                            if (lEdge.isSelfLoop() ) {
                                continue;
                            }
                            
                            if (PortSide.SIDES_NORTH_SOUTH.contains(lEdge.getSource().getSide())
                                    || PortSide.SIDES_NORTH_SOUTH.contains(lEdge.getTarget().getSide())) {
                                continue;
                            }
                            
                            CNode target = lNodeMap.get(lEdge.getTarget().getNode());
                            System.out.println("Adding edge: " + target);
                            
                            NEdge.of()
                                .delta(1)
                                .weight(100)
                                .source(nNodes[cNode.cGroup.id])
                                .target(nNodes[target.cGroup.id])
                                .create();
                            
                        }
                    }
                }
                
                // network simplex does not allow disconnected graphs
                // so we make it connected!
                List<NNode> sources = Lists.newLinkedList();
                for (NNode n : networkSimplexGraph.nodes) {
                    if (n.getIncomingEdges().isEmpty()) {
                        sources.add(n);
                    }
                }
                if (sources.size() > 1) {
                    NNode dummySource = NNode.of()
                            .id(index++)
                            .create(networkSimplexGraph);
                    
                   for (NNode src : sources) {
                       NEdge.of()
                           .delta(1)
                           .weight(0)
                           .source(dummySource)
                           .target(src)
                           .create();
                   }
                }
                
                // #4 execute network simplex
                NetworkSimplex.forGraph(networkSimplexGraph)
                              .execute(new BasicProgressMonitor());
                
                
                // #5 apply positions
                for (CNode cNode : compactor.cGraph.cNodes) {
                    cNode.startPos = nNodes[cNode.cGroup.id].layer + cNode.cGroupOffset.x;
                    cNode.applyPosition();
                }  
            };
    
}
