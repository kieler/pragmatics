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
                    putTargetInGrid(targetPort, port);
                    break;
                }
            }

            //TODO calulate maximal space usage of branches and push first branch node up/down accordingly!

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
                            putTargetInGrid(targetPort, port);
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

    private void putTargetInGrid(final LPort targetPort, final LPort sourcePort) {
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
                targetRow.addNodeAtPosition(
                        target,
                        patterns.breachBreach(targetRow.getOccupiedPositions(),
                                sourceRow.getPosition(source)));
                break;
            case SWITCH_LEFT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    targetRow.addNodeAtPosition(target, patterns.breachSwitchLeftBranch(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case STRAIGHT:
                    targetRow.addNodeAtPosition(target, patterns.breachSwitchLeftStraight(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case STUMP:
                    targetRow.addNodeAtPosition(target, patterns.breachSwitchLeftStump(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                }
                break;
            case SWITCH_RIGHT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    targetRow.addNodeAtPosition(target, patterns.breachSwitchRightBranch(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case STRAIGHT:
                    targetRow.addNodeAtPosition(target, patterns.breachSwitchRightStraight(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case STUMP:
                    targetRow.addNodeAtPosition(target, patterns.breachSwitchRightStump(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                }
                break;
            }
            break;
        case SWITCH_LEFT:
            switch (sourcePort.getProperty(Properties.PORT_TYPE)) {
            case BRANCH:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    targetRow.addNodeAtPosition(target, patterns.switchLeftBranchBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftBranchSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftBranchSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftBranchSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftBranchSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftBranchSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftBranchSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    targetRow.addNodeAtPosition(target, patterns.switchLeftStraightBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStraightSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStraightSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStraightSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStraightSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStraightSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStraightSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    targetRow.addNodeAtPosition(target, patterns.switchLeftStumpBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStumpSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStumpSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStumpSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStumpSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStumpSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchLeftStumpSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
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
                    targetRow.addNodeAtPosition(target, patterns.switchRightBranchBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    targetRow.addNodeAtPosition(target, patterns.switchRightStraightBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightStraightSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightStraightSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightBranchSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    targetRow.addNodeAtPosition(target, patterns.switchRightStumpBreach(
                            targetRow.getOccupiedPositions(), sourceRow.getPosition(source)));
                    break;
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightStumpSwitchLeftBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightStumpSwitchLeftStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightStumpSwitchLeftStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightStumpSwitchRightBranch(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STRAIGHT:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightStumpSwitchRightStraight(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    case STUMP:
                        targetRow.addNodeAtPosition(
                                target,
                                patterns.switchRightStumpSwitchRightStump(
                                        targetRow.getOccupiedPositions(),
                                        sourceRow.getPosition(source)));
                        break;
                    }
                    break;
                }
                break;
            }
            break;
        }
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
