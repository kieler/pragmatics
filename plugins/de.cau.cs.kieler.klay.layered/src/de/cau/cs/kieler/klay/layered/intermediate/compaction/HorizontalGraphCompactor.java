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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.intermediate.LabelDummyRemover;
import de.cau.cs.kieler.klay.layered.intermediate.ReversedEdgeRestorer;

/**
 * This processor applies additional compaction to an already routed graph and can be
 * executed after {@link OrthogonalEdgeRouter}. Therefore nodes and vertical segments of edges are
 * repositioned in the specified direction where the position is minimal considering the
 * desired spacing between elements.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>The edges are routed orthogonally</dd>
 * <dt>Postcondition:</dt>
 * <dd>Nodes and edges are positioned compact without colliding.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 5.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>After {@link LabelDummyRemover}</dd>
 * <dd>Before {@link ReversedEdgeRestorer}</dd>
 * </dl>
 * 
 * @author dag
 */
public class HorizontalGraphCompactor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Compacting", 1);
        
        OneDimensionalCompactor odc =
                new OneDimensionalCompactor(new LGraphToCGraphTransformer().transform(layeredGraph));
        
        // compacting left, locking all nodes that don't have any edge pointing to the right,
        // then compacting right to shorten unnecessary long edges
        odc.changeDirection(Direction.LEFT)
           .drawHitboxes("/home/dag/cgraphdebug/test_" + System.nanoTime() + "DLEFT" + ".svg")
           .compact()
           .drawHitboxes("/home/dag/cgraphdebug/test_" + System.nanoTime() + "LEFT_COMPACT" + ".svg")
           .setLockingStrategy((n, d) -> n.reposition = !n.lock.get(d))
           .changeDirection(Direction.RIGHT)
           .drawHitboxes("/home/dag/cgraphdebug/test_" + System.nanoTime() + "DRIGHT" + ".svg")
           .compact()
           .drawHitboxes("/home/dag/cgraphdebug/test_" + System.nanoTime() + "RIGHT_COMPACT" + ".svg")
//           .setLockingStrategy((n, d) -> n.reposition = !n.lock.get(d))
//           .changeDirection(Direction.LEFT)
//           .drawHitboxes("/home/dag/cgraphdebug/test_" + System.nanoTime() + "DLEFT2" + ".svg")
//           .compact()
//           .drawHitboxes("/home/dag/cgraphdebug/test_" + System.nanoTime() + "LEFT_COMPACT2" + ".svg")
           .changeDirection(Direction.LEFT)
           .drawHitboxes("/home/dag/cgraphdebug/test_" + System.nanoTime() + "FINAL_LEFT" + ".svg")
           .applyToGraph();
        
        progressMonitor.done();
    }
}
