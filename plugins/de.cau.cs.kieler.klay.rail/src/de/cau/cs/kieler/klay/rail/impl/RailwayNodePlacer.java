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
package de.cau.cs.kieler.klay.rail.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;
import de.cau.cs.kieler.klay.rail.IPatterns;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.graph.RailRow;
import de.cau.cs.kieler.klay.rail.options.NodeType;
import de.cau.cs.kieler.klay.rail.options.PortType;

/**
 * Node placer for railway layout.
 * 
 * @author jjc
 */
public class RailwayNodePlacer extends AbstractAlgorithm implements INodePlacer {

    private List<LNode> known = new LinkedList<LNode>();
    private Stack<LNode> nextNodes = new Stack<LNode>();

    private IPatterns patterns = new SimplePatternsImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
    }

    /**
     * {@inheritDoc}
     */
    public void placeNodes(final LayeredGraph layeredGraph) {
        getMonitor().begin("Linear segments node placement", 1);

        // determine starting position for straight-first DFS
        LNode walker = null;
        known = new LinkedList<LNode>();
        nextNodes = new Stack<LNode>();
        findStart: for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.ENTRY_POINT)) {
                    walker = node;
                    break findStart;
                }
            }
        }
        if (walker == null) {
            throw new IllegalArgumentException("No entry point found!");
        }
        if (walker.getLayer() instanceof RailLayer) {
            ((RailLayer) walker.getLayer()).getRowList().addNodeAtPosition(walker, 0);
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }

        // start straight first DFS
        nextNodes.push(walker);
        known.add(walker);
        List<LNode> trunk = new LinkedList<LNode>();
        boolean stillTrunk = true;

        while (!nextNodes.isEmpty()) {
            walker = nextNodes.pop();
            boolean found = false;
            if (allNeighborsKnown(walker)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                LPort targetPort = port.getEdges().get(0).getTarget();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !known.contains(target)) {
                    found = true;
                    known.add(target);
                    if (!allNeighborsKnown(walker)) {
                        nextNodes.push(walker);
                    }
                    if (stillTrunk) {
                        trunk.add(walker);
                    }
                    nextNodes.push(target);
                    putTargetInGrid(target, getPositionForNode(targetPort, port));
                    break;
                }
            }

            // TODO calulate maximal space usage of branches and push first branch node up/down
            // accordingly!

            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    LPort targetPort = port.getEdges().get(0).getTarget();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !known.contains(target)) {
                        stillTrunk = false;
                        if (!known.contains(target)) {
                            known.add(target);
                            if (!allNeighborsKnown(walker)) {
                                nextNodes.push(walker);
                            }
                            nextNodes.push(target);
                            putTargetInGrid(target, getPositionForNode(targetPort, port));
                        }
                    }
                }
            }

        }

        // get minimal position for offset

        // apply grid to graph
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                gridToAbsolutePosition((RailLayer) layer, getMinimalPosition(layeredGraph));
            } else {
                throw new IllegalArgumentException("Only works with RailLayers!");
            }
        }
        getMonitor().done();
    }

    private int getPositionForNode(final LPort targetPort, final LPort sourcePort) {
        RailRow targetRow;
        RailRow sourceRow;
        LNode source = sourcePort.getNode();
        LNode target = targetPort.getNode();
        if (target.getLayer() instanceof RailLayer && source.getLayer() instanceof RailLayer) {
            targetRow = ((RailLayer) target.getLayer()).getRowList();
            sourceRow = ((RailLayer) source.getLayer()).getRowList();
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }
        switch (source.getProperty(Properties.NODE_TYPE)) {
        case BREACH_OR_CLOSE:
            switch (target.getProperty(Properties.NODE_TYPE)) {
            case BREACH_OR_CLOSE:
                return patterns.breachBreach(targetRow.getOccupiedPositions(),
                        sourceRow.getPosition(source));
            case SWITCH_LEFT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    return patterns.breachSwitchLeftBranch(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case STRAIGHT:
                    return patterns.breachSwitchLeftStraight(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case STUMP:
                    return patterns.breachSwitchLeftStump(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                }
                break;
            case SWITCH_RIGHT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    return patterns.breachSwitchRightBranch(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case STRAIGHT:
                    return patterns.breachSwitchRightStraight(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case STUMP:
                    return patterns.breachSwitchRightStump(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                }
                break;
            }
            break;
        case SWITCH_LEFT:
            switch (sourcePort.getProperty(Properties.PORT_TYPE)) {
            case BRANCH:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftBranchBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftBranchSwitchLeftBranch(targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchLeftBranchSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchLeftBranchSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftBranchSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchLeftBranchSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchLeftBranchSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftStraightBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStraightSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchLeftStraightSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchLeftStraightSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStraightSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchLeftStraightSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchLeftStraightSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftStumpBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStumpSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchLeftStumpSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchLeftStumpSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStumpSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchLeftStumpSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchLeftStumpSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                }
                break;
            }
            break;
        case SWITCH_RIGHT:
            switch (sourcePort.getProperty(Properties.PORT_TYPE)) {
            case BRANCH:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightBranchBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightBranchSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchRightBranchSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightBranchSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchRightBranchSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightStraightBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStraightSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchRightBranchSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStraightSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchRightBranchSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightStumpBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source));
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStumpSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchRightStumpSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchRightStumpSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStumpSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STRAIGHT:
                        return patterns.switchRightStumpSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    case STUMP:
                        return patterns.switchRightStumpSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source));
                    }
                    break;
                }
                break;
            }
            break;
        }
        return 0;
    }

    private void putTargetInGrid(final LNode target, final int position) {
        RailRow targetRow;
        if (target.getLayer() instanceof RailLayer) {
            targetRow = ((RailLayer) target.getLayer()).getRowList();
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }
        targetRow.addNodeAtPosition(target, position);
    }

    private int getMinimalPosition(final LayeredGraph graph) {
        int result = Integer.MAX_VALUE;
        for (Layer layer : graph.getLayers()) {
            if (layer instanceof RailLayer) {
                int i = ((RailLayer) layer).getRowList().getMinimalPosition();
                if (i < result) {
                    result = i;
                }
            } else {
                // TODO: maybe validate once, gets annoying
            }
        }
        return result;
    }

    private void gridToAbsolutePosition(final RailLayer layer, final int minPos) {
        float spacing = layer.getGraph().getProperty(Properties.OBJ_SPACING);
        float borspacing = layer.getGraph().getProperty(Properties.BOR_SPACING);
        double value = borspacing;
        for (LNode node : layer.getRowList().getNodesOrderedByPosition()) {
            int position = layer.getRowList().getPosition(node);
            int offset = (-1) * minPos;
            value = offset * (node.getSize().y + spacing) + position * (node.getSize().y + spacing);
            node.getPos().y = value;
            System.out.println("Setting y of " + node.toString() + " to " + value);
            System.out.println("Position was " + position);
        }
    }

    private boolean allNeighborsKnown(final LNode node) {
        boolean result = true;
        for (LPort port : node.getPorts()) {
            result &= known.contains(port.getEdges().get(0).getTarget().getNode());
        }
        return result;
    }

}
