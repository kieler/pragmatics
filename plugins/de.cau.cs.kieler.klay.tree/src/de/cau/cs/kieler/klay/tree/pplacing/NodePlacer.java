/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.pplacing;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * @author sor
 * @author sgu
 * 
 */
public class NodePlacer implements ILayoutPhase {

    Comparator<TNode> comparator = new Comparator<TNode>() {
        public int compare(TNode t1, TNode t2) {
            if (t1.getLabel().length() < t2.getLabel().length()) {
                return -1;
            } else {
                if (t1.getLabel().length() > t2.getLabel().length()) {
                    return 1;
                } else {
                    return t1.getLabel().compareTo(t2.getLabel());
                }
            }
        }
    };

    private final TreeMap<TNode, Double> prelim = new TreeMap<TNode, Double>(comparator);
    private final TreeMap<TNode, Double> modifier = new TreeMap<TNode, Double>(comparator);
    private TGraph tGraph = null;
    private double spacing = 20f;
    
    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            null, EnumSet.of(LayoutProcessorStrategy.ROOT_PROC),
            EnumSet.of(LayoutProcessorStrategy.NEIGHBORS_PROC),
            EnumSet.of(LayoutProcessorStrategy.COORDINATE_PROC));

    private void setPrelim(TNode tNode, double value) {
        prelim.put(tNode, value);
    }

    private double getPrelim(TNode tNode) {
        if (tNode != null)
            return prelim.get(tNode);
        else
            return 0;
    }

    private void setModifier(TNode tNode, double value) {
        modifier.put(tNode, value);
    }

    private double getModifier(TNode tNode) {
        if (tNode != null)
            return modifier.get(tNode);
        else
            return 0;
    }

    public void firstWalk(TNode tNode, TNode leftNeighbour,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor place nodes - first walk", 2);
        // initial Prelim value of all leaves should be 0
        if (tGraph.isLeaf(tNode)) {
            setPrelim(tNode, 0);
            setModifier(tNode, 0);

            TNode tmp = leftNeighbour;
            // if a node is a leaf and has a leftNeighbour modify Prelim
            if (tmp != null) {
                double calc = getPrelim(tmp) + spacing + meanNodeSize(tmp, tNode).x;
                // the value of Prelim is: Prelim of left neighbour + silbingSeparation + mean node
                // size
                setPrelim(tNode, calc);
            }
        }
        // in case tNode is not a leaf
        else {
            // call this method recursively for every child of tNode
            TNode prev = null;
            for (TNode start : tNode.getChildren()) {
                firstWalk(start, prev, progressMonitor);
                prev = start;
            }
            // calculate midPoint
            double midPoint = (getPrelim(tGraph.getFirstChild(tNode)) + getPrelim(tGraph
                    .getLastChild(tNode))) / 2;
            // check existence of a leftNeighbour
            TNode tmp = leftNeighbour;
            if (tmp != null) {
                // Prelim is Prelim of leftNeighbour + meanNodeSize + silbingSeparation
                setPrelim(tNode, getPrelim(tmp) + meanNodeSize(tmp, tNode).x + spacing);
                // Modifier is Prelim - midPoint
                setModifier(tNode, getPrelim(tNode) - midPoint);
            }
            // no leftNeighbour, so modifier is 0 and prelim is just midPoint
            else {
                setPrelim(tNode, midPoint);
            }
        }
        progressMonitor.done();
    }

    // recursive method, that adds modifier of ancestors to nodes
    public void secondWalk(TNode tNode, LinkedList<TNode> currentLevel,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Processor place nodes - second walk", 3);
        if (!tGraph.isLeaf(tNode)) {
            double xCoor = getPrelim(tNode);
            for (TNode tmp : tNode.getChildren()) {
                secondWalk(tmp, tmp.getChildren(), progressMonitor.subTask(3.0f));
                xCoor += getModifier(tmp);
            }
            tNode.setProperty(Properties.XCOOR, (int) Math.round(xCoor));
        }
        progressMonitor.done();
    }

    /**
     * @param leftChild
     * @param tNode
     * @return
     */
    private KVector meanNodeSize(TNode leftNode, TNode rightNode) {
        KVector nodeSize = new KVector();
        double nodeWidth = 0;
        double nodeHeight = 0;
        nodeWidth = (leftNode.getSize().x + rightNode.getSize().x) / 2;
        nodeHeight = (leftNode.getSize().y + rightNode.getSize().y) / 2;
        nodeSize.x = nodeWidth;
        nodeSize.y = nodeHeight;
        return nodeSize;
    }

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {
        // TODO Auto-generated method stub

        progressMonitor.begin("Processor order nodes", 2);

        prelim.clear();
        modifier.clear();

        this.tGraph = tGraph;

        spacing = tGraph.getProperty(Properties.SPACING);

        LinkedList<TNode> roots = new LinkedList<TNode>();
        for (TNode tNode : tGraph.getNodes()) {
            if (tNode.getProperty(Properties.ROOT))
                roots.add(tNode);
        }
        TNode root = roots.getFirst();
        firstWalk2(root, progressMonitor.subTask(2.0f));

        Set<Entry<TNode, Double>> modSet = modifier.entrySet();
        System.out.println("Modmap");
        for (Entry<TNode, Double> entry : modSet) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        secondWalk(root, roots, progressMonitor.subTask(3.0f));

        System.out.println("X COOR");

        Set<Entry<TNode, Double>> prelimSet = prelim.entrySet();

        for (Entry<TNode, Double> entry : prelimSet) {
            System.out
                    .println(entry.getKey() + ": " + entry.getKey().getProperty(Properties.XCOOR));
        }

        // for (Entry<TNode, Double> entry : prelimSet) {
        // KVector pos = entry.getKey().getPosition();
        // pos.x = entry.getValue();
        // }
        progressMonitor.done();

    }

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(TGraph tGraph) {
        // TODO Auto-generated method stub
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    public void setPrevNodeAtLevel(TNode tNode, LinkedList<TNode> currentLevel) {
        if (currentLevel.getFirst() != tNode) {
            int i = 0;
            while (currentLevel.get(i) != tNode) {
                currentLevel.get(i + 1);
            }
            currentLevel.set(i - 1, tNode);
        }
    }

    public TNode getPrevNodeAtLevel(TNode tNode, LinkedList<TNode> currentLevel) {
        if (tNode == currentLevel.getFirst()) {
            return null;
        } else {
            int i = 0;
            while (currentLevel.get(i) != tNode) {
                currentLevel.get(i + 1);
            }
            int prev = i - 1;
            return currentLevel.get(prev);
        }
    }

    public void firstWalk2(TNode tNode, final IKielerProgressMonitor progressMonitor) {
        setModifier(tNode, 0);
        System.out.println("Vor Blatttest!");
        if (tGraph.isLeaf(tNode)) {
            System.out.println("In Blatttest!");
            TNode lN = tNode.getLeftNeighbour();
            if (lN != null) {
                System.out.println("Im  2. if");
                double calc = getPrelim(lN) + spacing + meanNodeSize(lN, tNode).x;
                System.out.println("Calc berechnet: " + calc);
                setPrelim(tNode, calc);
            } else {
                setPrelim(tNode, 0);
            }
        } else {
            TNode leftMost = tNode.getChildren().get(0);
            TNode rightMost = tNode.getChildren().get(tNode.getChildren().size() - 1);
            // currentLevel erhoehen?
            for (TNode child : tNode.getChildren()) {
                firstWalk2(child, progressMonitor.subTask(2.0f));
            }
            double midPoint = (getPrelim(rightMost) + getPrelim(leftMost)) / 2;
            System.out.println("Midpoint: " + midPoint);
            if (tNode.getLeftNeighbour() != null) {
                System.out.println("DRIN!");
                setPrelim(
                        tNode,
                        getPrelim(tNode.getLeftNeighbour()) + spacing
                                + meanNodeSize(tNode.getLeftNeighbour(), tNode).x);
                setModifier(tNode, getPrelim(tNode) - midPoint);
            } else {
                System.out.println("Letzter Test!");
                setPrelim(tNode, midPoint);
            }
        }
    }

}
