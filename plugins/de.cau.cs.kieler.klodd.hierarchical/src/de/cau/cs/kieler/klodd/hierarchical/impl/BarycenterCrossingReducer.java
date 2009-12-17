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

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.klodd.hierarchical.modules.ISingleLayerCrossingReducer;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerElement;

/**
 * Implementation of the barycenter method for the 2-layer crossing reduction
 * problem.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class BarycenterCrossingReducer extends AbstractAlgorithm implements ISingleLayerCrossingReducer {

    /**
     * number of surrounding ranks to consider for elements with undefined
     * abstract rank.
     */
    private static final int RANK_AVG_RADIUS = 1;

    /**
     * {@inheritDoc}
     */
    public void reduceCrossings(final Layer layer, final boolean forward) {
        getMonitor().begin("Barycenter method (layer " + layer.getRank()
                + (forward ? ", forward)" : ", backwards)"), 1);

        double[] abstractRanks = new double[layer.getElements().size()];
        int elemIndex = 0;
        for (LayerElement element : layer.getElements()) {
            PortConstraints portConstraints = element.getPortConstraints();
            if (portConstraints == PortConstraints.FIXED_SIDE
                    || portConstraints == PortConstraints.FREE_PORTS) {
                // ports are not fixed, find an order for the ports
                Map<KPort, List<Integer>> portRanks = element.getConnectionRanksByPort(forward);
                Map<KPort, Double> abstractPortRanks = new HashMap<KPort, Double>();
                List<KPort> ports = new LinkedList<KPort>(portRanks.keySet());
                double sum = 0.0;
                ListIterator<KPort> portsIter = ports.listIterator();
                while (portsIter.hasNext()) {
                    KPort port = portsIter.next();
                    List<Integer> rankList = portRanks.get(port);
                    double barycenter = calcBarycenter(rankList);
                    if (barycenter < 0.0) {
                        portsIter.remove();
                    } else {
                        sum += barycenter;
                        abstractPortRanks.put(port, Double.valueOf(barycenter));
                    }
                }
                if (ports.isEmpty()) {
                    // elements with no connections should stay where they are
                    abstractRanks[elemIndex] = -1.0;
                } else {
                    element.sortPorts(abstractPortRanks, !forward);
                    abstractRanks[elemIndex] = sum / ports.size();
                }
            } else {
                // the ports of the current element are fixed,
                // or there are no port constraints
                List<Integer> rankList = element.getConnectionRanks(forward);
                abstractRanks[elemIndex] = calcBarycenter(rankList);
            }
            elemIndex++;
        }
        sortAbstract(layer, abstractRanks);

        getMonitor().done();
    }

    /**
     * {@inheritDoc}
     */
    public void reduceCrossings(final Layer layer) {
        getMonitor().begin("Barycenter method", 1);

        double[] abstractRanks = new double[layer.getElements().size()];
        int elemIndex = 0;
        for (LayerElement element : layer.getElements()) {
            PortConstraints portConstraints = element.getPortConstraints();
            if (portConstraints == PortConstraints.FIXED_SIDE
                    || portConstraints == PortConstraints.FREE_PORTS) {
                // ports are not fixed, find an order for the ports
                Map<KPort, List<Integer>> forwardRanks = element.getConnectionRanksByPort(true);
                Map<KPort, List<Integer>> backwardsRanks = element.getConnectionRanksByPort(false);
                Map<KPort, Double> abstractForward = new HashMap<KPort, Double>();
                Map<KPort, Double> abstractBackwards = new HashMap<KPort, Double>();
                List<KPort> ports = new LinkedList<KPort>(forwardRanks.keySet());
                double sum = 0.0;
                ListIterator<KPort> portsIter = ports.listIterator();
                while (portsIter.hasNext()) {
                    KPort port = portsIter.next();
                    double bary1 = calcBarycenter(forwardRanks.get(port));
                    double bary2 = calcBarycenter(backwardsRanks.get(port));
                    double barycenter = mergeBarycenters(bary1, bary2);
                    if (barycenter < 0.0) {
                        portsIter.remove();
                    } else {
                        sum += barycenter;
                        if (bary1 >= 0.0) {
                            abstractForward.put(port, Double.valueOf(bary1));
                        }
                        if (bary2 >= 0.0) {
                            abstractBackwards.put(port, Double.valueOf(bary2));
                        }
                    }
                }
                if (ports.isEmpty()) {
                    // elements with no connections should stay where they are
                    abstractRanks[elemIndex] = -1.0;
                } else {
                    element.sortPorts(abstractBackwards, abstractForward);
                    abstractRanks[elemIndex] = sum / ports.size();
                }
            } else {
                // the ports of the current element are fixed,
                // or there are no port constraints
                double bary1 = calcBarycenter(element.getConnectionRanks(true));
                double bary2 = calcBarycenter(element.getConnectionRanks(false));
                abstractRanks[elemIndex] = mergeBarycenters(bary1, bary2);
            }
            elemIndex++;
        }
        sortAbstract(layer, abstractRanks);

        getMonitor().done();
    }

    /**
     * Calculates the barycenter of a list of ranks.
     * 
     * @param ranks list of ranks
     * @return barycenter value
     */
    private double calcBarycenter(final List<Integer> ranks) {
        if (ranks.isEmpty()) {
            return -1.0;
        } else {
            int rankSum = 0;
            for (int rank : ranks) {
                rankSum += rank;
            }
            return (double) rankSum / ranks.size();
        }
    }

    /**
     * Merges two barycenter values, considering special cases.
     * 
     * @param b1 first value
     * @param b2 second value
     * @return merged barycenter value
     */
    private double mergeBarycenters(final double b1, final double b2) {
        if (b1 < 0.0 && b2 < 0.0) {
            return -1.0;
        } else if (b1 < 0.0) {
            return b2;
        } else if (b2 < 0.0) {
            return b1;
        } else {
            return (b1 + b2) / 2;
        }
    }

    /**
     * Sorts the elements in the given layer and assigns them new rank values
     * based on a map of abstract ranks. The algorithm tries to put elements
     * with abstract rank smaller than zero near their previous position.
     * 
     * @param layer layer to sort
     * @param abstractRanks array of abstract ranks used as base for sorting
     */
    private void sortAbstract(final Layer layer, final double[] abstractRanks) {
        List<LayerElement> elements = layer.getElements();
        // determine placements for elements with no abstract rank
        final double[] filteredRanks = new double[elements.size()];
        int index = 0;
        double lastRank = 0.0;
        for (LayerElement element : elements) {
            double arank = abstractRanks[index];
            if (arank < 0.0) {
                int definedRanks = 0;
                double sum = 0.0;
                for (int i = index - RANK_AVG_RADIUS; i <= index + RANK_AVG_RADIUS; i++) {
                    if (i >= 0 && i < abstractRanks.length && abstractRanks[i] >= 0.0) {
                        sum += abstractRanks[i];
                        definedRanks++;
                    }
                }
                if (definedRanks > 0) {
                    filteredRanks[index] = sum / definedRanks;
                } else {
                    filteredRanks[index] = lastRank;
                }
            } else {
                filteredRanks[index] = arank;
                lastRank = arank;
            }
            element.setRank(index++);
        }

        // sort all elements by the filtered ranks
        Collections.sort(elements, new Comparator<LayerElement>() {
            public int compare(final LayerElement elem1, final LayerElement elem2) {
                return Double.compare(filteredRanks[elem1.getRank()], filteredRanks[elem2.getRank()]);
            }
        });

        // calculate concrete rank values
        layer.calcElemRanks();
    }

}
