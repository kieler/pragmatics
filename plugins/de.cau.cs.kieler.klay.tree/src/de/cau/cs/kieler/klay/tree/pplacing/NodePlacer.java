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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
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
    
    private final Map<TNode, Double> prelim = new HashMap<TNode, Double>();
    private final Map<TNode, Double> modifier = new HashMap<TNode, Double>();
    private TGraph tGraph = null;
    private double silbingSeparation = 10.0;
    
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
            if(tmp != null) {
                double calc = getPrelim(tmp) + silbingSeparation + 
                        meanNodeSize(tmp, tNode).x;
                // the value of Prelim is: Prelim of left neighbour + silbingSeparation + mean node size
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
            double midPoint = (getPrelim(tGraph.getFirstChild(tNode)) +
                    getPrelim(tGraph.getLastChild(tNode)))/2;
            // check existence of a leftNeighbour
            TNode tmp = leftNeighbour;
            if (tmp != null) {
                // Prelim is Prelim of leftNeighbour + meanNodeSize + silbingSeparation
                setPrelim(tNode, getPrelim(tmp) + meanNodeSize(tmp, tNode).x + silbingSeparation);
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
    public void secondWalk(TNode tNode, double modifier, LinkedList<TNode> currentLevel, 
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Processor place nodes - second walk", 3);
        if (!tGraph.isLeaf(tNode)) {
            for (TNode tmp : tNode.getChildren()) {
                secondWalk(tmp, modifier + getModifier(tmp), tmp.getChildren(), 
                        progressMonitor.subTask(3.0f));
            }
        }
        progressMonitor.done();
    }

    /**
     * @param leftChild
     * @param tNode
     * @return
     */
    private KVector meanNodeSize(TNode leftNode, TNode rightNode) {
        // TODO Auto-generated method stub
        KVector nodeSize = null;
        double nodeWidth = 0;
        double nodeHeight = 0;
        nodeWidth = (leftNode.getSize().x + rightNode.getSize().x)/2;
        nodeHeight = (leftNode.getSize().y + rightNode.getSize().y)/2;
        nodeSize.x = nodeWidth;
        nodeSize.y = nodeHeight;
        return nodeSize;
    }
    
    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = 
            new IntermediateProcessingConfiguration(
            IntermediateProcessingConfiguration.BEFORE_PHASE_2,
            EnumSet.of(LayoutProcessorStrategy.TEST_PROCESSOR));

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {
        // TODO Auto-generated method stub
        
        progressMonitor.begin("Processor order nodes", 2);
        
        this.tGraph = tGraph;
        LinkedList<TNode> roots = new LinkedList<TNode>();
        for (TNode tNode : tGraph.getNodes()) {
            if (tNode.getProperty(Properties.ROOT))
                roots.add(tNode);
        }
        TNode root = roots.getFirst();
        firstWalk2(root, roots, progressMonitor.subTask(2.0f));
        secondWalk(root, 0, roots, progressMonitor.subTask(3.0f));
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
                currentLevel.get(i+1);
            }
            currentLevel.set(i-1, tNode);
        }
    }
    
    
    public TNode getPrevNodeAtLevel(TNode tNode, LinkedList<TNode> currentLevel) {
        if (tNode == currentLevel.getFirst()) {
            return null;
        }
        else {
            int i = 0;
            while (currentLevel.get(i) != tNode) {
                currentLevel.get(i+1);
            }
            int prev = i-1;
            return currentLevel.get(prev);
        }
    }
    
    public void firstWalk2(TNode tNode, LinkedList<TNode> currentLevel, 
            final IKielerProgressMonitor progressMonitor) {
        tNode.setLeftNeighbour(getPrevNodeAtLevel(tNode, currentLevel));
        setPrevNodeAtLevel(tNode, currentLevel);
        setModifier(tNode, 0);
        System.out.println("Vor Blatttest!");
        if (tGraph.isLeaf(tNode)) {
            System.out.println("In Blatttest!");
            if (tNode.getLeftNeighbour() != null) {
                System.out.println("Im  2. if");
                double calc = getPrelim(tNode.getLeftNeighbour()) + 
                        silbingSeparation + meanNodeSize(tNode.getLeftNeighbour(), tNode).x;
                System.out.println("Calc berechnet: "+calc);
                setPrelim(tNode, calc);  
            }
            else {
                setPrelim(tNode, 0);
            }
        }
        else {
            TNode leftMost = tNode.getChildren().get(0);
            TNode rightMost = tNode.getChildren().get(0);
            // currentLevel erhoehen?
            firstWalk2(leftMost, currentLevel, progressMonitor.subTask(2.0f));
            while (rightMost.getRightNeighbour() != null) {
                rightMost.setRightNeighbour(rightMost);
                // currentLevel erhoehen?
                firstWalk2(rightMost, currentLevel, progressMonitor.subTask(2.0f));
            }
            double midPoint = (getPrelim(rightMost) + getPrelim(leftMost)) / 2;
            System.out.println("Midpoint: " + midPoint);
            if (tNode.getLeftNeighbour() != null) {
                System.out.println("DRIN!");
                setPrelim(tNode, getPrelim(tNode.getLeftNeighbour()) + silbingSeparation
                        + meanNodeSize(tNode.getLeftNeighbour(), tNode).x);
                setModifier(tNode, getPrelim(tNode)-midPoint);
            }
            else {
                System.out.println("Letzter Test!");
                setPrelim(tNode, midPoint);
            }
        }
        Set prelimSet = prelim.entrySet();
        Set modSet = modifier.entrySet();
        Iterator i1 = prelimSet.iterator();
        Iterator i2 = modSet.iterator();
        
        while(i1.hasNext()) {
            Map.Entry me = (Map.Entry)i1.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println("Modmap");
        while(i2.hasNext()) {
            Map.Entry me = (Map.Entry)i2.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
            }
            System.out.println(); 
        
        
        
    }
    
    

}
