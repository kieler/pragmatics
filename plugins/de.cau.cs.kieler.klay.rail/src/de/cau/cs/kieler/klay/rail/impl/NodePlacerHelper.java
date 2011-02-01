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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.rail.IPatterns;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.graph.RailRow;
import de.cau.cs.kieler.klay.rail.options.NodeType;
import de.cau.cs.kieler.klay.rail.options.PortType;

public class NodePlacerHelper {
    
    private IPatterns patterns;
    
    private HashMap<LNode, Integer> placement;
    
    public NodePlacerHelper(IPatterns pat) {
        patterns = pat;
    }
    
    public boolean allNeighborsKnown(final LNode node, final List<LNode> known) {
        boolean result = true;
        for (LPort port : node.getPorts()) {
            result &= known.contains(port.getEdges().get(0).getTarget().getNode());
        }
        return result;
    }

    
    public int getConflictDistanceLeft(final LNode first, final int layerCount) {
        placement = new HashMap<LNode, Integer>();
        List<Integer> maxNewPosByLayer = getMaxNewPosByLayer(first, layerCount);
        List<Integer> minPosByLayer = getMinPosByLayer(first.getLayer().getGraph());
        int result = 0;
        for (int i = 0; i < maxNewPosByLayer.size(); i++) {
            if (maxNewPosByLayer.get(i) >= minPosByLayer.get(i)) {
                int conflict = maxNewPosByLayer.get(i) - minPosByLayer.get(i);
                if (conflict > result) {
                    result = conflict;
                }
            }
        }
        System.out.println("Found a left conflict distance of " + result);
        return result;
    }

    public int getConflictDistanceRight(final LNode first, final int layerCount) {
        placement = new HashMap<LNode, Integer>();
        List<Integer> minNewPosByLayer = getMinNewPosByLayer(first, layerCount);
        List<Integer> maxPosByLayer = getMaxPosByLayer(first.getLayer().getGraph());
        int result = 0;
        for (int i = 0; i < minNewPosByLayer.size(); i++) {
            if (minNewPosByLayer.get(i) <= maxPosByLayer.get(i)) {
                int conflict = maxPosByLayer.get(i) - minNewPosByLayer.get(i);
                if (conflict > result) {
                    result = conflict;
                }
            }
        }
        System.out.println("Found a right conflict distance of " + result);
        return result;
    }

    private List<Integer> getMinPosByLayer(final LayeredGraph layeredGraph) {
        List<Integer> result = new ArrayList<Integer>(layeredGraph.getLayers().size());
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                result.add(((RailLayer) layer).getRowList().getMinimalPosition());
            }
        }
        return result;
    }

    private List<Integer> getMaxPosByLayer(final LayeredGraph layeredGraph) {
        List<Integer> result = new ArrayList<Integer>(layeredGraph.getLayers().size());
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                result.add(((RailLayer) layer).getRowList().getMaximalPosition());
            }
        }
        return result;
    }

    private List<Integer> getMinNewPosByLayer(final LNode first, final int layerCount) {
        List<Integer> result = new LinkedList<Integer>();
        List<LNode> knownNodes = new LinkedList<LNode>();
        Stack<LNode> next = new Stack<LNode>();
        for (int i = 0; i < layerCount; i++) {
            result.add(Integer.MAX_VALUE);
        }
        LNode walker = first;

        next.push(walker);
        knownNodes.add(walker);
        if (!placement.containsKey(walker)) {
            placement.put(walker, 0);
        }

        while (!next.isEmpty()) {
            walker = next.pop();
            boolean found = false;
            if (allNeighborsKnown(walker, knownNodes)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                LPort targetPort = port.getEdges().get(0).getTarget();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !knownNodes.contains(target)) {
                    found = true;
                    knownNodes.add(target);
                    if (!allNeighborsKnown(walker, knownNodes)) {
                        next.push(walker);
                    }
                    next.push(target);
                    int layerNo = target.getLayer().getIndex();
                    int newPos = getPositionForNode(targetPort, port, placement.get(walker));
                    placement.put(target, newPos);
                    if (result.get(layerNo) > newPos) {
                        result.set(layerNo, newPos);
                    }
                    break;
                }
            }

            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    LPort targetPort = port.getEdges().get(0).getTarget();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !knownNodes.contains(target)) {
                        if (!knownNodes.contains(target)) {
                            knownNodes.add(target);
                            if (!allNeighborsKnown(walker, knownNodes)) {
                                next.push(walker);
                            }
                            next.push(target);
                            int layerNo = target.getLayer().getIndex();
                            int newPos = getPositionForNode(targetPort, port, placement.get(walker));
                            int offset = 0;
                            if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_LEFT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset -= getConflictDistanceLeft(target, layerCount);
                                }
                            } else if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_RIGHT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset += getConflictDistanceRight(target, layerCount);
                                }
                            }
                            placement.put(target, newPos + offset);
                            placement.put(target, newPos);
                            if (result.get(layerNo) > newPos) {
                                result.set(layerNo, newPos);
                            }
                        }
                    }
                }
            }

        }

        return result;
    }

    private List<Integer> getMaxNewPosByLayer(final LNode first, final int layerCount) {
        List<Integer> result = new LinkedList<Integer>();
        List<LNode> knownNodes = new LinkedList<LNode>();
        Stack<LNode> next = new Stack<LNode>();
        for (int i = 0; i < layerCount; i++) {
            result.add(0);
        }
        LNode walker = first;

        next.push(walker);
        knownNodes.add(walker);
        if (!placement.containsKey(walker)) {
            placement.put(walker, 0);
        }

        while (!next.isEmpty()) {
            walker = next.pop();
            boolean found = false;
            if (allNeighborsKnown(walker, knownNodes)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                LPort targetPort = port.getEdges().get(0).getTarget();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !knownNodes.contains(target)) {
                    found = true;
                    knownNodes.add(target);
                    if (!allNeighborsKnown(walker, knownNodes)) {
                        next.push(walker);
                    }
                    next.push(target);
                    int layerNo = target.getLayer().getIndex();
                    int newPos = getPositionForNode(targetPort, port, placement.get(walker));
                    placement.put(target, newPos);
                    if (result.get(layerNo) < newPos) {
                        result.set(layerNo, newPos);
                    }
                    break;
                }
            }

            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    LPort targetPort = port.getEdges().get(0).getTarget();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !knownNodes.contains(target)) {
                        if (!knownNodes.contains(target)) {
                            knownNodes.add(target);
                            if (!allNeighborsKnown(walker, knownNodes)) {
                                next.push(walker);
                            }
                            next.push(target);
                            int layerNo = target.getLayer().getIndex();
                            int newPos = getPositionForNode(targetPort, port, placement.get(walker));
                            int offset = 0;
                            if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_LEFT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset -= getConflictDistanceLeft(target, layerCount);
                                }
                            } else if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_RIGHT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset += getConflictDistanceRight(target, layerCount);
                                }
                            }
                            placement.put(target, newPos + offset);
                            if (result.get(layerNo) < newPos) {
                                result.set(layerNo, newPos);
                            }
                        }
                    }
                }
            }

        }
        return result;
    }
    
    public int getPositionForNode(final LPort targetPort, final LPort sourcePort) {
        return getPositionForNode(targetPort, sourcePort, Integer.MIN_VALUE);
    }

    public int getPositionForNode(final LPort targetPort, final LPort sourcePort,
            final int sourcePosition) {
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
        int sourcePos = sourcePosition;
        if (sourcePos == Integer.MIN_VALUE) {
            sourcePos = sourceRow.getPosition(source);
        }
        switch (source.getProperty(Properties.NODE_TYPE)) {
        case BREACH_OR_CLOSE:
            switch (target.getProperty(Properties.NODE_TYPE)) {
            case BREACH_OR_CLOSE:
                return patterns.breachBreach(targetRow.getOccupiedPositions(), sourcePos);
            case SWITCH_LEFT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    return patterns.breachSwitchLeftBranch(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STRAIGHT:
                    return patterns.breachSwitchLeftStraight(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STUMP:
                    return patterns.breachSwitchLeftStump(targetRow.getOccupiedPositions(),
                            sourcePos);
                }
                break;
            case SWITCH_RIGHT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    return patterns.breachSwitchRightBranch(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STRAIGHT:
                    return patterns.breachSwitchRightStraight(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STUMP:
                    return patterns.breachSwitchRightStump(targetRow.getOccupiedPositions(),
                            sourcePos);
                }
                break;
            }
            break;
        case SWITCH_LEFT:
            switch (sourcePort.getProperty(Properties.PORT_TYPE)) {
            case BRANCH:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftBranchBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftBranchSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftBranchSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftStraightBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStraightSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStraightSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStraightSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStraightSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStraightSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStraightSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftStumpBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStumpSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStumpSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStumpSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStumpSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStumpSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStumpSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
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
                    return patterns.switchRightBranchBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightBranchSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightBranchSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightStraightBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStraightSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStraightSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightStumpBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStumpSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightStumpSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightStumpSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStumpSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightStumpSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightStumpSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            }
            break;
        }
        return 0;
    }
}
