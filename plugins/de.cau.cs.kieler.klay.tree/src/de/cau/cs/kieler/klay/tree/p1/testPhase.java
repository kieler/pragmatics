package de.cau.cs.kieler.klay.tree.p1;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TGraphBuilder;
import de.cau.cs.kieler.klay.tree.graph.TNode;

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

    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Mr.T", 1);

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
        int occupiedLenght = 0;

        ArrayList<TNode> currentLevel = new ArrayList<TNode>();
        ArrayList<TNode> nextLevel = new ArrayList<TNode>();

        nextLevel.addAll(tempRoot.getChildren());

        while (!nextLevel.isEmpty()) {
            currentLevel = (ArrayList<TNode>) nextLevel.clone();
            nextLevel.clear();
            depth++;
            for (TNode tNode : currentLevel) {
                childFan = tNode.getProperty(Properties.FAN);
                occupiedLenght += childFan;
                tNode.getPosition().x = occupiedLenght + childFan / 2;
                tNode.getPosition().y = depth;
                nextLevel.addAll(tNode.getChildren());
            }
        }
        // TODO return graph and implement fan data
    }

}
