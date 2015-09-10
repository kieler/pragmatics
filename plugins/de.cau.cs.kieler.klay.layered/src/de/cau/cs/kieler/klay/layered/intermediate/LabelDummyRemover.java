/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.nodespacing.LabelSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * <p>Processor that removes the inserted center label dummies and places the labels on their
 * position.</p>
 * 
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>a layered graph<dd>
 *     <dd>nodes are placed</dd>
 *     <dd>edges are routed</dd>
 *     <dd>center labels are represented by and attached to center label dummy nodes.</dd>
 *   <dt>Postcondition:</dt>
 *     <dd>labels are placed</dd>
 *     <dd>there are no dummy nodes of type
 *       {@link de.cau.cs.kieler.klay.layered.properties.NodeType#LABEL}.</dd>
 *     <dd>center labels are attached to their original edges again.</dd>
 *   <dt>Slots:</dt>
 *     <dd>After phase 5.</dd>
 *   <dt>Same-slot dependencies:</dt>
 *     <dd>{@link HierarchicalPortOrthogonalEdgeRouter}</dd>
 * </dl>
 *
 * @author jjc
 * @kieler.rating yellow proposed cds
 */
public final class LabelDummyRemover implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Label dummy removal", 1);
        
        double labelSpacing = layeredGraph.getProperty(LayoutOptions.LABEL_SPACING).doubleValue();
        Direction layoutDirection = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        
        for (Layer layer : layeredGraph.getLayers()) {
            // An iterator is necessary for traversing nodes, since
            // dummy nodes might be removed
            ListIterator<LNode> nodeIterator = layer.getNodes().listIterator();
            
            while (nodeIterator.hasNext()) {
                LNode node = nodeIterator.next();
                
                if (node.getType() == NodeType.LABEL) {
                    // First, place labels on position of dummy node 
                    LEdge originEdge = (LEdge) node.getProperty(InternalProperties.ORIGIN);
                    double thickness = originEdge.getProperty(LayoutOptions.THICKNESS).doubleValue();
                    
                    KVector currLabelPos = new KVector(node.getPosition());
                    
                    // If the labels are to be placed below their edge, we need to move the first label's
                    // position down a bit to respect the label spacing
                    if (node.getProperty(InternalProperties.LABEL_SIDE) == LabelSide.BELOW) {
                        currLabelPos.y += thickness + labelSpacing;
                    }
                    
                    // Calculate the space available for the placement of labels
                    KVector labelSpace = new KVector(
                            node.getSize().x,
                            node.getSize().y - thickness - labelSpacing);
                    
                    // Place labels
                    List<LLabel> representedLabels =
                            node.getProperty(InternalProperties.REPRESENTED_LABELS);
                    
                    if (layoutDirection.isVertical()) {
                        placeLabelsForVerticalLayout(representedLabels, currLabelPos, labelSpacing,
                                labelSpace,
                                node.getProperty(InternalProperties.LABEL_SIDE) != LabelSide.ABOVE);
                    } else {
                        placeLabelsForHorizontalLayout(representedLabels, currLabelPos, labelSpacing,
                                labelSpace);
                    }
                    
                    // Add represented labels back to the original edge
                    originEdge.getLabels().addAll(representedLabels);
                    
                    // Join the edges without adding unnecessary bend points
                    LongEdgeJoiner.joinAt(node, false);
                    
                    // Remove the node
                    nodeIterator.remove();
                }
            }
        }
        monitor.done();
    }

    private void placeLabelsForHorizontalLayout(final List<LLabel> labels, final KVector labelPos,
            final double labelSpacing, final KVector labelSpace) {
        
        for (LLabel label : labels) {
            label.getPosition().x = labelPos.x + (labelSpace.x - label.getSize().x) / 2.0;
            label.getPosition().y = labelPos.y;
            
            labelPos.y += label.getSize().y + labelSpacing;
        }
    }

    private void placeLabelsForVerticalLayout(final List<LLabel> labels, final KVector labelPos,
            final double labelSpacing, final KVector labelSpace, final boolean leftAligned) {
        
        for (LLabel label : labels) {
            label.getPosition().x = labelPos.x;
            label.getPosition().y = leftAligned
                    ? labelPos.y
                    : labelPos.y + labelSpace.y - label.getSize().y;
            
            labelPos.x += label.getSize().x + labelSpacing;
        }
    }

}
