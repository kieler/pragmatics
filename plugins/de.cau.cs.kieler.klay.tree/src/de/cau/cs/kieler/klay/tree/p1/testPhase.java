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
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            IntermediateProcessingConfiguration.BEFORE_PHASE_1,
            EnumSet.of(LayoutProcessorStrategy.TEST_PROCESSOR));

    /** default value for spacing between nodes. */
    private static final float DEFAULT_SPACING = 15.0f;

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
            if (tNode.getParent() == null)
                root.add(tNode);
        }

        TNode tempRoot;

        Iterator<TNode> iterator = root.iterator();
        if (iterator.hasNext()) {
            iterator.next();
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

        tempRoot.getPosition().x = parentFan / 2;
        tempRoot.getPosition().y = 0;

        int childFan;
        int occupiedSpace = 0;

        ArrayList<TNode> currentLevel = new ArrayList<TNode>();
        ArrayList<TNode> nextLevel = new ArrayList<TNode>();

        nextLevel.addAll(tempRoot.getChildren());

        while (!nextLevel.isEmpty()) {
            currentLevel = (ArrayList<TNode>) nextLevel.clone();
            nextLevel.clear();
            depth++;
            for (TNode tNode : currentLevel) {
                childFan = tNode.getProperty(Properties.FAN);
                occupiedSpace += childFan;
                tNode.getPosition().x = occupiedSpace + childFan / 2;
                tNode.getPosition().y = depth;
                nextLevel.addAll(tNode.getChildren());
            }
            occupiedSpace = 0;
        }
        // TODO return graph and implement fan data
        progressMonitor.done();
    }

}
