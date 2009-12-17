/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.hierarchical.modules.ILayerwiseEdgePlacer;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerConnection;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerElement;
import de.cau.cs.kieler.klodd.hierarchical.structures.RoutingSlot;

/**
 * Layerwise edge placer implementation that sorts the edges and gives them slot
 * ranks according to the result.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class SortingLayerwiseEdgePlacer extends AbstractAlgorithm implements ILayerwiseEdgePlacer {

    /**
     * Routing slots used for sorting.
     */
    private static class SortableRoutingSlot extends RoutingSlot {
        private boolean outgoingAtStart = false, outgoingAtEnd = false;
    }

    /** minimal distance of two edges to make them feasible in the same routing layer. */
    private static final float EDGE_DIST = 2.0f;

    /** map of layer elements to their corresponding routing slots. */
    private Map<Object, RoutingSlot> slotMap = new LinkedHashMap<Object, RoutingSlot>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        slotMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    public int placeEdges(final Layer layer, final float minDist) {
        getMonitor().begin("Edge routing (layer " + layer.getRank() + ")", 1);
        LayoutDirection layoutDirection = layer.getLayeredGraph().getLayoutDirection();

        // determine number of outgoing connections for each port
        Map<Object, Integer> outgoing = new HashMap<Object, Integer>();
        for (LayerElement element : layer.getElements()) {
            // count outgoing connections
            for (LayerConnection connection : element.getOutgoingConnections()) {
                Object key = connection.getSourcePort();
                if (key == null) {
                    key = element;
                }
                Integer value = outgoing.get(key);
                if (value == null) {
                    outgoing.put(key, Integer.valueOf(1));
                } else {
                    outgoing.put(key, Integer.valueOf(value.intValue() + 1));
                }
            }
        }

        // create routing slots for each port
        for (LayerElement element : layer.getElements()) {
            for (LayerConnection connection : element.getOutgoingConnections()) {
                // choose source or target port for routing
                Object key = connection.getSourcePort();
                if (key == null) {
                    key = element;
                }
                Integer sourceValue = outgoing.get(key);
                if (sourceValue == null || sourceValue.intValue() <= 1) {
                    key = connection.getTargetPort();
                    if (key == null) {
                        key = connection.getTargetElement();
                    }
                }

                // determine source and target positions
                float sourcePos = connection.calcSourcePos(minDist);
                float targetPos = connection.calcTargetPos(minDist);
                if (layer.getRank() == 0) {
                    PortSide placement = LayoutOptions.getPortSide(KimlLayoutUtil
                            .getShapeLayout(connection.getSourcePort()));
                    if (layoutDirection == LayoutDirection.DOWN) {
                        if (placement == PortSide.WEST) {
                            sourcePos = 0.0f;
                        } else if (placement == PortSide.EAST) {
                            sourcePos = layer.getCrosswiseDim();
                        } else if (placement == PortSide.SOUTH) {
                            sourcePos = layer.getCrosswiseDim();
                        }
                    } else {
                        if (placement == PortSide.NORTH) {
                            sourcePos = 0.0f;
                        } else if (placement == PortSide.SOUTH) {
                            sourcePos = layer.getCrosswiseDim();
                        } else if (placement == PortSide.EAST) {
                            sourcePos = layer.getCrosswiseDim();
                        }
                    }
                } else if (layer.getHeight() == 1) {
                    PortSide placement = LayoutOptions.getPortSide(KimlLayoutUtil
                            .getShapeLayout(connection.getTargetPort()));
                    if (layoutDirection == LayoutDirection.DOWN) {
                        if (placement == PortSide.WEST) {
                            targetPos = 0.0f;
                        } else if (placement == PortSide.EAST) {
                            targetPos = layer.getCrosswiseDim();
                        } else if (placement == PortSide.NORTH) {
                            targetPos = layer.getCrosswiseDim();
                        }
                    } else {
                        if (placement == PortSide.NORTH) {
                            targetPos = 0.0f;
                        } else if (placement == PortSide.SOUTH) {
                            targetPos = layer.getCrosswiseDim();
                        } else if (placement == PortSide.WEST) {
                            targetPos = layer.getCrosswiseDim();
                        }
                    }
                }
                float startPos = Math.min(sourcePos, targetPos) - EDGE_DIST;
                float endPos = Math.max(sourcePos, targetPos) + EDGE_DIST;

                // get routing slot and insert connection area
                SortableRoutingSlot slot = (SortableRoutingSlot) slotMap.get(key);
                if (slot == null) {
                    slot = new SortableRoutingSlot();
                    if (targetPos <= sourcePos) {
                        slot.outgoingAtStart = true;
                    }
                    if (targetPos >= sourcePos) {
                        slot.outgoingAtEnd = true;
                    }
                    slot.setStart(startPos);
                    slot.setEnd(endPos);
                    slotMap.put(key, slot);
                } else {
                    if (startPos < slot.getStart()) {
                        if (targetPos <= sourcePos) {
                            slot.outgoingAtStart = true;
                        } else {
                            slot.outgoingAtStart = false;
                        }
                    }
                    if (endPos > slot.getEnd()) {
                        if (targetPos >= sourcePos) {
                            slot.outgoingAtEnd = true;
                        } else {
                            slot.outgoingAtEnd = false;
                        }
                    }
                    slot.setStart(Math.min(slot.getStart(), startPos));
                    slot.setEnd(Math.max(slot.getEnd(), endPos));
                }
            }
        }

        // sort all routing slots
        List<List<RoutingSlot>> routingLayers = new LinkedList<List<RoutingSlot>>();
        RoutingSlot[] sortedSlots = slotMap.values().toArray(new RoutingSlot[0]);
        Arrays.sort(sortedSlots, new Comparator<RoutingSlot>() {
            public int compare(final RoutingSlot s1, final RoutingSlot s2) {
                assert s1 instanceof SortableRoutingSlot && s2 instanceof SortableRoutingSlot;
                SortableRoutingSlot slot1 = (SortableRoutingSlot) s1;
                SortableRoutingSlot slot2 = (SortableRoutingSlot) s2;
                if (slot1.outgoingAtStart && !slot2.outgoingAtStart
                        && slot1.getStart() == slot2.getStart()) {
                    return 1;
                } else if (slot2.outgoingAtStart && !slot1.outgoingAtStart
                        && slot1.getStart() == slot2.getStart()) {
                    return -1;
                } else if (slot1.outgoingAtEnd && !slot2.outgoingAtEnd
                        && slot1.getEnd() == slot2.getEnd()) {
                    return 1;
                } else if (slot2.outgoingAtEnd && !slot1.outgoingAtEnd
                        && slot1.getEnd() == slot2.getEnd()) {
                    return -1;
                } else if (slot1.outgoingAtStart && slot1.getStart() > slot2.getStart()) {
                    return 1;
                } else if (slot2.outgoingAtStart && slot2.getStart() > slot1.getStart()) {
                    return -1;
                } else if (slot1.outgoingAtEnd && slot1.getEnd() < slot2.getEnd()) {
                    return 1;
                } else if (slot2.outgoingAtEnd && slot2.getEnd() < slot1.getEnd()) {
                    return -1;
                } else if (!slot1.outgoingAtStart && slot1.getStart() > slot2.getStart()) {
                    return -1;
                } else if (!slot2.outgoingAtStart && slot2.getStart() > slot1.getStart()) {
                    return 1;
                } else if (!slot1.outgoingAtEnd && slot1.getEnd() < slot2.getEnd()) {
                    return -1;
                } else if (!slot2.outgoingAtEnd && slot2.getEnd() < slot1.getEnd()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        // assign ranks to each routing slot
        int slotRanks = 0;
        for (RoutingSlot slot : sortedSlots) {
            int rank = routingLayers.size();
            ListIterator<List<RoutingSlot>> routingLayerIter = routingLayers.listIterator(routingLayers
                    .size());
            List<RoutingSlot> lastLayer = null;
            while (routingLayerIter.hasPrevious()) {
                List<RoutingSlot> routingLayer = routingLayerIter.previous();
                boolean feasible = true;
                for (RoutingSlot layerSlot : routingLayer) {
                    if (slot.getStart() < layerSlot.getEnd() && slot.getEnd() > layerSlot.getStart()) {
                        feasible = false;
                        break;
                    }
                }
                if (!feasible) {
                    break;
                }
                lastLayer = routingLayer;
                rank--;
            }
            if (lastLayer != null) {
                slot.setRank(rank);
                lastLayer.add(slot);
            } else {
                slot.setRank(routingLayers.size());
                List<RoutingSlot> routingLayer = new LinkedList<RoutingSlot>();
                routingLayer.add(slot);
                routingLayers.add(routingLayer);
                slotRanks++;
            }
        }

        getMonitor().done();
        return slotRanks;
    }

    /**
     * {@inheritDoc}
     */
    public Map<Object, RoutingSlot> getSlotMap() {
        return slotMap;
    }

}
