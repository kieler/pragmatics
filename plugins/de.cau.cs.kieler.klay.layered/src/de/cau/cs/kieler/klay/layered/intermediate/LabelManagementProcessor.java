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
package de.cau.cs.kieler.klay.layered.intermediate;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.labels.ILabelSizeModifier;
import de.cau.cs.kieler.kiml.labels.LabelLayoutOptions;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

/**
 * Invokes a potentially registered label size modifier on center edge label dummy nodes.
 * 
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>The graph is layered.</dd>
 *     <dd>Label dummy nodes are placed in their final layers.</dd>
 *   <dt>Postcondition:</dt>
 *     <dd>Possible lable text modifications.</dd>
 *   <dt>Slots:</dt>
 *     <dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt>
 *     <dd>{@link LabelDummySwitcher}</dd>
 * </dl>
 * 
 * @author cds
 */
public final class LabelManagementProcessor implements ILayoutProcessor {

    /** Predicate that checks for center labels. */
    private static final Predicate<LLabel> CENTER_LABEL_PREDICATE = new Predicate<LLabel>() {
        public boolean apply(final LLabel label) {
            return label.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)
                    == EdgeLabelPlacement.CENTER;
        }
    };
    
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Label management", 1);
        
        // This processor should actually not be run in the first place if there is no label size
        // modifier set on the graph, but let's be sure anyway
        ILabelSizeModifier labelSizeModifier =
                layeredGraph.getProperty(LabelLayoutOptions.LABEL_SIZE_MODIFIER);
        if (labelSizeModifier != null) {
            double labelSpacing = layeredGraph.getProperty(LayoutOptions.LABEL_SPACING);
            
            // Iterate over all layers and call our nifty code
            for (Layer layer : layeredGraph) {
                manageLabels(layer, labelSizeModifier, labelSpacing);
            }
        }
        
        monitor.done();
    }
    
    
    /**
     * Iterates over the nodes labels and invokes the label size modifier on label dummy nodes. The
     * target width for labels is the width of the widest non-dummy node.
     * 
     * @param layer
     *            the layer whose labels to manage.
     * @param labelSizeModifier
     *            the label size modifier used.
     * @param labelSpacing
     *            spacing between labels and other objects.
     */
    private void manageLabels(final Layer layer, final ILabelSizeModifier labelSizeModifier,
            final double labelSpacing) {
        
        assert labelSizeModifier != null : "labelSizeModifier is null";
        
        double maxWidth = findMaxNonDummyNodeWidth(layer);
        
        // Apply the maximum width to all label dummy nodes
        for (LNode labelDummy : layer) {
            if (labelDummy.getProperty(InternalProperties.NODE_TYPE) == NodeType.LABEL) {
                final KVector newDummySize = new KVector(0.0, 0.0); 
                
                Iterable<LLabel> labels = findLabels(labelDummy);
                for (LLabel label : labels) {
                    // If the label has an origin, call the label size modifier
                    Object origin = label.getProperty(InternalProperties.ORIGIN);
                    if (origin != null) {
                        KVector newSize = labelSizeModifier.resizeLabelToWidth(origin, maxWidth);
                        label.getSize().x = newSize.x;
                        label.getSize().y = newSize.y;
                    }
                    
                    // Adjust dummy height
                    newDummySize.x = Math.max(newDummySize.x, label.getSize().x);
                    newDummySize.y += label.getSize().y + labelSpacing;
                }
                
                // Subtract the most recent label spacing
                if (newDummySize.y > 0.0) {
                    newDummySize.y -= labelSpacing;
                }
                
                // Apply new dummy node size (we don't bother with the ports here since they will be
                // meddled with later anyway)
                labelDummy.getSize().x = newDummySize.x;
                labelDummy.getSize().y = newDummySize.y;
            }
        }
    }

    /**
     * Finds the maximum width of non-dummy nodes in the given layer.
     * 
     * @param layer
     *            the layer to iterate over.
     * @return the maximum width of non-dummy nodes. If there are none, {@code 0.0} is returned.
     */
    private double findMaxNonDummyNodeWidth(final Layer layer) {
        double maxWidth = 0.0;
        
        for (LNode node : layer) {
            if (node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORMAL) {
                maxWidth = Math.max(maxWidth, node.getSize().x);
            }
        }
        
        return maxWidth;
    }
    
    /**
     * Retrieves the labels represented by the given label dummy node.
     * 
     * @param labelDummy
     *            label dummy node.
     * @return an iterable over the labels the given dummy node represents.
     */
    private Iterable<LLabel> findLabels(final LNode labelDummy) {
        final LEdge edge = (LEdge) labelDummy.getProperty(InternalProperties.ORIGIN);
        assert edge != null : "label dummy's original edge is null";
        
        // Return the first center label we find
        return Iterables.filter(edge.getLabels(), CENTER_LABEL_PREDICATE);
    }
    
}
