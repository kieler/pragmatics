/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * Add constraint edges between partitions. </br>
 * In partitioned graphs, each node has a assigned partition. For every node holds, that every other node
 * with a smaller partition is placed left to it. This is accomplished by adding partition constraint
 * edges. For each node, we add an edge to every node in the next greater partition. These edges get a
 * high priority assigned such that during cycle breaking, none of these constraint edges gets reversed.
 * During layering, the constraint edges assure the aforementioned condition.
 * The constraint edges are removed later by the {@link PartitionPostprocessor}.
 *
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>an unlayered graph.</dd>
 *   <dt>Postcondition:</dt>
 *     <dd>unlayered graph with partition constraint edges.</dd>
 *   <dt>Slots:</dt>
 *     <dd>Before phase 1.</dd>
 *   <dt>Same-slot dependencies:</dt>
 *     <dd>None.</dd>
 * </dl>
 *
 * @author csp
 * @see PartitionPostprocessor
 */
public class PartitionPreprocessor implements ILayoutProcessor {

    /** The priority to set on added constraint edges. */
    private static final int PARTITION_CONSTRAINT_EDGE_PRIORITY = 20;

    /** The nodes of the currently processed graph, sorted into partitions. */
    private List<List<LNode>> partitions;

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final LGraph lGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Adding partition constraint edges", 1);
        partitions = Lists.newArrayList();
        // Sort nodes into partitions.
        for (LNode node : lGraph.getLayerlessNodes()) {
            Integer index = node.getProperty(LayoutOptions.PARTITION);
            // We assume every node has a partition set.
            assert index != null : "Missing partition property at " + node.toString();
            retrievePartition(index).add(node);
        }

        // Add edges from each node to every node in the next greater partition.
        for (int i = 0; i < partitions.size() - 1; i++) {
            for (LNode node : partitions.get(i)) {
                LPort sourcePort = new LPort();
                sourcePort.setNode(node);
                sourcePort.setSide(PortSide.EAST);
                sourcePort.setProperty(InternalProperties.PARTITION_DUMMY, true);
                
                for (LNode otherNode : partitions.get(i + 1)) {
                    LPort targetPort = new LPort();
                    targetPort.setNode(otherNode);
                    targetPort.setSide(PortSide.WEST);
                    targetPort.setProperty(InternalProperties.PARTITION_DUMMY, true);
                    
                    LEdge edge = new LEdge();
                    edge.setProperty(InternalProperties.PARTITION_DUMMY, true);
                    edge.setProperty(LayoutOptions.PRIORITY, PARTITION_CONSTRAINT_EDGE_PRIORITY);
                    edge.setSource(sourcePort);
                    edge.setTarget(targetPort);
                }
            }
        }
        partitions = null;
        monitor.done();
    }
    
    /**
     * Retrieve the partition with the given index. If doesn't exist yet, the list of partitions is
     * extended sufficiently.
     * 
     * @param index
     *            the index of the partition to retrieve.
     * @return the existing or newly created partition.
     */
    private List<LNode> retrievePartition(final int index) {
        while (index >= partitions.size()) {
            partitions.add(new LinkedList<LNode>());
        }
        return partitions.get(index);
    }

}
