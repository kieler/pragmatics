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

/**
 *
 * @author jjc
 */
public class LabelSideSelector extends AbstractAlgorithm implements ILayoutProcessor {

    /** The side selection methods for labels. */
    public enum Mode {
        /** Labels are always placed above their edges. */
        ALWAYS_UP,
        /** Labels are always placed below their edges. */
        ALWAYS_DOWN,
        /** Labels are always placed above their edges, with respect to the edge's direction. */
        DIRECTION_UP,
        /** Labels are always placed below their edges, with respect to the edge's direction. */
        DIRECTION_DOWN,
        /** A heuristic is used to determine the side. */
        SMART;
    }
    
    /** The selected side mode of the processor. */
    private final Mode mode;
    
    /**
     * The constructor of this processor, expecting a side placement mode.
     * 
     * @param mode The mode to use
     */
    public LabelSideSelector(final Mode mode) {
        this.mode = mode;
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
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
        
    }

    private void directionDown(final List<LNode> nodes) {
        
    }
    
    private void smart(final List<LNode> nodes) {
        
    }

}
