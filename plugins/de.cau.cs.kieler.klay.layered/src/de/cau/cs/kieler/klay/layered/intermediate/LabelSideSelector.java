/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LLabel.LSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.EdgeLabelSideSelectionStrategy;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 *
 * @author jjc
 */
public class LabelSideSelector extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * The constructor for this processor.
     * 
     * @param strategy The side placement strategy given by a layout option.
     */
    public LabelSideSelector() {
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        EdgeLabelSideSelectionStrategy mode = layeredGraph.getProperty(Properties.EDGE_LABEL_SIDE);
        getMonitor().begin("Label side selection (" + mode + ")", 1);
        List<LNode> nodes = new LinkedList<LNode>();
        for (Layer layer : layeredGraph.getLayers()) {
            nodes.addAll(layer.getNodes());
        }
        
        switch (mode) {
        case ALWAYS_UP:
            alwaysUp(nodes);
            break;
        case ALWAYS_DOWN:
            alwaysDown(nodes);
            break;
        case DIRECTION_UP:
            directionUp(nodes);
            break;
        case DIRECTION_DOWN:
            directionDown(nodes);
            break;
        case SMART:
            smart(nodes);
            break;
        }
        
        getMonitor().done();
    }
    
    private void alwaysUp(final List<LNode> nodes) {
        for (LNode node : nodes) {
            for (LEdge edge : node.getOutgoingEdges()) {
                for (LLabel label : edge.getLabels()) {
                    label.setSide(LSide.UP);
                }
            }
        }
    }
    
    private void alwaysDown(final List<LNode> nodes) {
        for (LNode node : nodes) {
            for (LEdge edge : node.getOutgoingEdges()) {
                for (LLabel label : edge.getLabels()) {
                    label.setSide(LSide.DOWN);
                }
            }
        }
    }

    private void directionUp(final List<LNode> nodes) {
        for (LNode node : nodes) {
            for (LEdge edge : node.getOutgoingEdges()) {
                LSide side = LSide.UP;
                if (edge.getSource().getNode().getLayer().getIndex()
                        <= edge.getTarget().getNode().getLayer().getIndex()) {
                    side = LSide.UP;
                } else {
                    side = LSide.DOWN;
                }
                for (LLabel label : edge.getLabels()) {
                    label.setSide(side);
                }
            }
        }
    }

    private void directionDown(final List<LNode> nodes) {
        for (LNode node : nodes) {
            for (LEdge edge : node.getOutgoingEdges()) {
                LSide side = LSide.UP;
                if (edge.getSource().getNode().getLayer().getIndex()
                        <= edge.getTarget().getNode().getLayer().getIndex()) {
                    side = LSide.DOWN;
                } else {
                    side = LSide.UP;
                }
                for (LLabel label : edge.getLabels()) {
                    label.setSide(side);
                }
            }
        }
    }
    
    private void smart(final List<LNode> nodes) {
        
    }

}
