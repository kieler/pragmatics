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
package de.cau.cs.kieler.klay.tree.p1;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;

/**
 * TODO: Document this class.
 * 
 * @author sor
 * @author sgu
 */
public class testPhase implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = 
            new IntermediateProcessingConfiguration(
                    IntermediateProcessingConfiguration.BEFORE_PHASE_1,
                    EnumSet.of(LayoutProcessorStrategy.TEST_PROCESSOR));

    /** default value for spacing between nodes. */
    private static final float DEFAULT_SPACING = 15.0f;
    
    /** default value for size of nodes. */
    private static final float DEFAULT_SIZE = 50.0f;

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final TGraph graph) {
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    @SuppressWarnings("unchecked")
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Mr.Test", 1);

        // TODO structure add root or potential roots
        List<TNode> root = new ArrayList<TNode>();
        for (TNode tNode : tGraph.getNodes()) {
            if (tNode.getProperty(Properties.ROOT))
                root.add(tNode);
        }

        TNode tempRoot;

        Iterator<TNode> iterator = root.iterator();
        if (iterator.hasNext()) {
            tempRoot = iterator.next();
            for (; iterator.hasNext();) {
                TNode tNode = iterator.next();
                tNode.setParent(tempRoot);
                tempRoot.addChild(tNode);
            }
        } else {
            tempRoot = root.isEmpty() ? null : root.get(0); // SEND ERROR
        }

        // TODO Structure add property for SPACING and BORDER_SPACING
        int depth = 0;
        // TODO find a use for tempRoot.getProperty(Properties.DEPTH)

        int parentFan = tempRoot.getProperty(Properties.FAN);
        
        tempRoot.getPosition().x = parentFan * DEFAULT_SIZE / 2;
        tempRoot.getPosition().y = 0;

        int childFan;
        int occupiedSpace = 0;

        ArrayList<TNode> currentLevel = new ArrayList<TNode>();
        ArrayList<TNode> nextLevel = new ArrayList<TNode>();

        nextLevel.addAll(tempRoot.getChildren());

        // TODO treat size
        while (!nextLevel.isEmpty()) {
            currentLevel = (ArrayList<TNode>) nextLevel.clone();
            nextLevel.clear();
            depth += DEFAULT_SIZE;
            for (TNode tNode : currentLevel) {
                childFan = tNode.getProperty(Properties.FAN);
                tNode.getPosition().x = occupiedSpace + childFan * DEFAULT_SIZE / 2;
                tNode.getPosition().y = depth;
                System.out.println("x: " + tNode.getPosition().x);
                occupiedSpace += childFan < 1 ? 1 * DEFAULT_SIZE : childFan* DEFAULT_SIZE;
                System.out.println("o: " + occupiedSpace);

                nextLevel.addAll(tNode.getChildren());
                
                for (TEdge outgoingEdge : tNode.getOutgoingEdges()) {
                    double sourcePos = outgoingEdge.getSource().getPosition().y;
                    double targetPos = outgoingEdge.getTarget().getPosition().y;
                    
                    outgoingEdge.getBendPoints().add(sourcePos, targetPos);
                }
            }
            occupiedSpace = 0;
        }
        
        // TODO return graph and implement fan data
        progressMonitor.done();
    }

}
