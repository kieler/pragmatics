/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
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
 * Layerwise edge placer implementation that orders routing slots using
 * topological numbering.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class ToponumLayerwiseEdgePlacer extends AbstractAlgorithm implements ILayerwiseEdgePlacer {

    /** value returned by comparator if the 'greater than' relation is strong. */
    private static final int STRONGLY_GREATER = 2;
    /** value returned by comparator if the 'less than' relation is strong. */
    private static final int STRONGLY_LESS = -2;
    
    /**
     * Routing slot used for sorting.
     */
    private class TopoRoutingSlot extends RoutingSlot implements Comparable<TopoRoutingSlot> {

        /** positions of line segments going to the preceding layer. */
        private List<Float> sourcePosis = new LinkedList<Float>();
        /** positions of line segments going to the next layer. */
        private List<Float> targetPosis = new LinkedList<Float>();
        /**
         * list of outgoing links in the slot ordering graph; the second entry
         * indicates whether this is a strong link.
         */
        private List<Pair<TopoRoutingSlot, Boolean>> outgoing
                = new LinkedList<Pair<TopoRoutingSlot, Boolean>>();
        /** number of incoming links in the slot ordering graph. */
        private int incomingCount = 0;
        /** visit marker for cycle breaking. */
        private int visit = -1;

        /**
         * Compares two routing slots. Returns a number greater than zero if
         * this slot should be put behind the other slot, or a number less than
         * zero if this slot should be put in front of the other slot. If the
         * relation is a strong one (there are conflicts between edges), the
         * result is -2 or 2, else it is -1, 0, or 1.
         * 
         * @param o the other routing slot to compare with this one
         * @return a number between -2 and 2 (included)
         */
        public int compareTo(final TopoRoutingSlot other) {
            // compare number of conflicts for both variants
            int conflicts1 = countConflicts(this.targetPosis, other.sourcePosis);
            int conflicts2 = countConflicts(other.targetPosis, this.sourcePosis);
            if (conflicts1 > conflicts2) {
                return STRONGLY_GREATER;
            } else if (conflicts1 < conflicts2) {
                return STRONGLY_LESS;
            } else {
                // compare number of crossings for both variants
                int crossings1 = countCrossings(this.targetPosis, other.getStart(), other.getEnd())
                        + countCrossings(other.sourcePosis, this.getStart(), this.getEnd());
                int crossings2 = countCrossings(other.targetPosis, this.getStart(), this.getEnd())
                        + countCrossings(this.sourcePosis, other.getStart(), other.getEnd());
                return crossings1 > crossings2 ? 1 : (crossings1 < crossings2 ? -1 : 0);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object other) {
            if (other instanceof TopoRoutingSlot) {
                return this.compareTo((TopoRoutingSlot) other) == 0;
            } else {
                return false;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return sourcePosis.hashCode() ^ targetPosis.hashCode() ^ Float.valueOf(getStart()).hashCode()
                    ^ Float.valueOf(getEnd()).hashCode();
        }
    }

    /** factor for the minimal distance value for edge spacing. */
    private static final float EDGE_DIST_FACT = 0.25f;

    /** map of layer elements to their corresponding routing slots. */
    private Map<Object, RoutingSlot> slotMap = new LinkedHashMap<Object, RoutingSlot>();
    /** minimal distance for edge spacing. */
    private float edgeSpacing;
    /** next dfs number for cycle breaking. */
    private int nextDfs;

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
        this.edgeSpacing = minDist * EDGE_DIST_FACT;

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
                float startPos = Math.min(sourcePos, targetPos);
                float endPos = Math.max(sourcePos, targetPos);

                // get routing slot and insert connection area
                TopoRoutingSlot slot = (TopoRoutingSlot) slotMap.get(key);
                if (slot == null) {
                    slot = new TopoRoutingSlot();
                    slot.setStart(startPos);
                    slot.setEnd(endPos);
                    slot.sourcePosis.add(Float.valueOf(sourcePos));
                    slot.targetPosis.add(Float.valueOf(targetPos));
                    slotMap.put(key, slot);
                } else {
                    slot.setStart(Math.min(slot.getStart(), startPos));
                    slot.setEnd(Math.max(slot.getEnd(), endPos));
                    insertSorted(slot.sourcePosis, sourcePos);
                    insertSorted(slot.targetPosis, targetPos);
                }
            }
        }

        // create links for the slot ordering graph
        List<RoutingSlot> slots = new LinkedList<RoutingSlot>(slotMap.values());
        ListIterator<RoutingSlot> slotIter1 = slots.listIterator();
        while (slotIter1.hasNext()) {
            TopoRoutingSlot slot1 = (TopoRoutingSlot) slotIter1.next();
            ListIterator<RoutingSlot> slotIter2 = slots.listIterator(slotIter1.nextIndex());
            while (slotIter2.hasNext()) {
                TopoRoutingSlot slot2 = (TopoRoutingSlot) slotIter2.next();
                int rel = slot1.compareTo(slot2);
                if (rel < 0) {
                    slot1.outgoing.add(new Pair<TopoRoutingSlot, Boolean>(slot2, Boolean
                            .valueOf(rel == STRONGLY_LESS)));
                    slot2.incomingCount++;
                } else if (rel > 0) {
                    slot2.outgoing.add(new Pair<TopoRoutingSlot, Boolean>(slot1, Boolean
                            .valueOf(rel == STRONGLY_GREATER)));
                    slot1.incomingCount++;
                }
            }
        }

        // break cycles in the slot ordering graph
        LinkedList<TopoRoutingSlot> sources = new LinkedList<TopoRoutingSlot>();
        nextDfs = 1;
        for (RoutingSlot slot : slots) {
            TopoRoutingSlot topoSlot = (TopoRoutingSlot) slot;
            breakCycles(topoSlot);
            if (topoSlot.incomingCount == 0) {
                sources.add(topoSlot);
            }
        }

        // assign ranks to all routing slots
        int slotRanks = 0;
        List<List<TopoRoutingSlot>> routingLayers = new LinkedList<List<TopoRoutingSlot>>();
        while (!sources.isEmpty()) {
            TopoRoutingSlot source = sources.removeFirst();
            // find a proper rank for the current slot
            int rank = routingLayers.size();
            ListIterator<List<TopoRoutingSlot>> routingLayerIter = routingLayers
                    .listIterator(routingLayers.size());
            List<TopoRoutingSlot> lastLayer = null;
            while (routingLayerIter.hasPrevious()) {
                List<TopoRoutingSlot> routingLayer = routingLayerIter.previous();
                boolean feasible = true;
                for (RoutingSlot layerSlot : routingLayer) {
                    if (source.getStart() < layerSlot.getEnd() + edgeSpacing
                            && source.getEnd() > layerSlot.getStart() - edgeSpacing) {
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
                source.setRank(rank);
                lastLayer.add(source);
            } else {
                source.setRank(routingLayers.size());
                List<TopoRoutingSlot> routingLayer = new LinkedList<TopoRoutingSlot>();
                routingLayer.add(source);
                routingLayers.add(routingLayer);
                slotRanks++;
            }
            // update all outgoing links
            for (Pair<TopoRoutingSlot, Boolean> outgoingSlot : source.outgoing) {
                outgoingSlot.getFirst().incomingCount--;
                if (outgoingSlot.getFirst().incomingCount == 0) {
                    sources.addLast(outgoingSlot.getFirst());
                }
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

    /**
     * Inserts a given value into a sorted list.
     * 
     * @param list sorted list
     * @param value value to insert
     */
    private void insertSorted(final List<Float> list, final float value) {
        ListIterator<Float> listIter = list.listIterator();
        while (listIter.hasNext()) {
            float next = listIter.next().floatValue();
            if (next == value) {
                return;
            } else if (next > value) {
                listIter.previous();
                break;
            }
        }
        listIter.add(Float.valueOf(value));
    }

    /**
     * Counts the number of crossings for a given list of positions.
     * 
     * @param posis sorted list of positions
     * @param start start of the critical area
     * @param end end of the critical area
     * @return number of positions in the critical area
     */
    private int countCrossings(final List<Float> posis, final float start, final float end) {
        int crossings = 0;
        for (float pos : posis) {
            if (pos > end) {
                break;
            } else if (pos >= start) {
                crossings++;
            }
        }
        return crossings;
    }

    /**
     * Counts the number of conflicts for the given lists of positions.
     * 
     * @param posis1 sorted list of positions
     * @param posis2 sorted list of positions
     * @return number of positions that overlap
     */
    private int countConflicts(final List<Float> posis1, final List<Float> posis2) {
        int conflicts = 0;
        if (!posis1.isEmpty() && !posis2.isEmpty()) {
            Iterator<Float> iter1 = posis1.iterator();
            Iterator<Float> iter2 = posis2.iterator();
            float pos1 = iter1.next();
            float pos2 = iter2.next();
            boolean hasMore = true;
            do {
                if (pos1 > pos2 - edgeSpacing && pos1 < pos2 + edgeSpacing) {
                    conflicts++;
                }
                if (pos1 <= pos2 && iter1.hasNext()) {
                    pos1 = iter1.next();
                } else if (pos2 <= pos1 && iter2.hasNext()) {
                    pos2 = iter2.next();
                } else {
                    hasMore = false;
                }
            } while (hasMore);
        }
        return conflicts;
    }

    /**
     * Breaks cycles starting with the given slot. A DFS is performed, and back
     * edges are removed if they are weak links. Otherwise a weak link is
     * fetched and removed from the cycle.
     * 
     * @param slot starting slot
     * @return the highest DFS number of a slot found through a back edge, if
     *         that cycle could not be broken yet, or 0 otherwise
     */
    private int breakCycles(final TopoRoutingSlot slot) {
        int result = 0;
        if (slot.visit < 0) {
            slot.visit = nextDfs++;
            ListIterator<Pair<TopoRoutingSlot, Boolean>> slotIter = slot.outgoing.listIterator();
            while (slotIter.hasNext()) {
                Pair<TopoRoutingSlot, Boolean> outgoingSlot = slotIter.next();
                if (outgoingSlot.getFirst().visit > 0) {
                    if (outgoingSlot.getSecond().booleanValue()) {
                        // found a cycle through a strong link, update result
                        result = Math.max(result, outgoingSlot.getFirst().visit);
                    } else {
                        // found a cycle through a weak link, break it
                        slotIter.remove();
                        outgoingSlot.getFirst().incomingCount--;
                    }
                } else {
                    // follow the link
                    int outgoingRes = breakCycles(outgoingSlot.getFirst());
                    if (outgoingRes > 0) {
                        if (outgoingRes == slot.visit || !outgoingSlot.getSecond().booleanValue()) {
                            // the link must be removed to break a cycle
                            slotIter.remove();
                            outgoingSlot.getFirst().incomingCount--;
                        } else {
                            // the cycle can't be broken here, update result
                            result = Math.max(result, outgoingRes);
                        }
                    }
                }
            }
            slot.visit = 0;
            // temporarily store the result in the slot rank field
            slot.setRank(result);
        } else {
            result = slot.getRank();
        }
        return result;
    }

}
