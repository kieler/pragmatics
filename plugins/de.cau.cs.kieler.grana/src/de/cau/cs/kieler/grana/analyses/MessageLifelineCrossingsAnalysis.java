/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.elk.core.UnsupportedGraphException;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisFailed;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Computes the number of crossings between messages and lifelines in sequence diagrams.
 * 
 * @author cds
 */
public class MessageLifelineCrossingsAnalysis implements IAnalysis {
    
    /**
     * Identifier of the edge crossings analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.messageLifelineCrossings";
    
    /** Holder class for results. */
    private static class MessageLifelineCrossingsResult {
        private int messages = 0;
        private int minCrossings = 0;
        private int maxCrossings = 0;
        private int sumCrossings = 0;
        private double averageCrossings = 0;
    }
    
    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Message Lifeline Crossings analysis", 1);
        
        // Find the interaction node, if any
        ElkNode interactionNode = findInteractionNode(parentNode);
        if (interactionNode == null) {
            progressMonitor.done();
            return new AnalysisFailed(
                    AnalysisFailed.Type.Configuration,
                    new UnsupportedGraphException(
                            "This does not appear to be a sequence diagram."));
        }
        
        MessageLifelineCrossingsResult results;
        
        // Start by turning all lifelines into their bounding box rectangles
        List<Rectangle2D> llRects = computeLifelineRectangles(interactionNode);
        if (llRects.isEmpty()) {
            results = new MessageLifelineCrossingsResult();
        } else {
            // Count them crossings!
            results = countCrossings(interactionNode, llRects);
        }

        progressMonitor.done();
        return new Object[] {
                llRects.size(),
                results.messages,
                results.minCrossings,
                results.averageCrossings,
                results.maxCrossings,
                results.sumCrossings };
    }
    
    /**
     * Finds an returns the node that represents the surrounding interaction, if any.
     */
    private ElkNode findInteractionNode(final ElkNode parentNode) {
        // The graph is supposed to have only one node
        if (parentNode.getChildren().size() != 1) {
            return null;
        }
        
        ElkNode child = parentNode.getChildren().get(0);
        
        return isInteraction(child)
                ? child
                : null;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Lifeline Gathering
    
    /**
     * Returns a list of rectangles that describe the bounding boxes of all lifelines.
     */
    private List<Rectangle2D> computeLifelineRectangles(final ElkNode interactionNode) {
        return interactionNode.getChildren().stream()
            .filter(node -> isLifeline(node))
            .map(node -> toBoundingBox(node))
            .collect(Collectors.toList());
    }
    
    /**
     * Returns a rectangle that describes the bounding box of the given node, which is assumed to
     * represent a lifeline.
     */
    private Rectangle2D toBoundingBox(final ElkNode node) {
        return new Rectangle2D.Double(
                node.getX(),
                node.getY(),
                node.getWidth(),
                node.getHeight());
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Cross Counting
    
    /**
     * Counts crossings between all messages and all lifelines. A message's source and target
     * lifeline do not contribute to the number of crossings.
     */
    private MessageLifelineCrossingsResult countCrossings(final ElkNode interactionNode,
            final List<Rectangle2D> llRects) {
        
        MessageLifelineCrossingsResult results = new MessageLifelineCrossingsResult();
        
        // Iterate over all edges and only care about those which actually represent messages
        for (ElkEdge edge : interactionNode.getContainedEdges()) {
            if (!isMessage(edge)) {
                continue;
            }
            
            int currCrossings = countCrossings(edge, llRects);
            
            results.minCrossings = Math.min(results.minCrossings, currCrossings);
            results.maxCrossings = Math.max(results.maxCrossings, currCrossings);
            results.sumCrossings += currCrossings;
            results.messages++;
        }
        
        results.averageCrossings = results.sumCrossings / (double) results.messages;
        
        return results;
    }
    
    /**
     * Counts crossings between the given edge and lifelines.
     */
    private int countCrossings(final ElkEdge edge, final List<Rectangle2D> llRects) {
        // Turn the edge into a line so we can use AWT code to determine intersections
        ElkEdgeSection edgeSection = ElkGraphUtil.firstEdgeSection(edge, false, false);
        Line2D edgeLine = new Line2D.Double(
                edgeSection.getStartX(), edgeSection.getStartY(),
                edgeSection.getEndX(), edgeSection.getEndY());
        
        // Iterate over the rectangles. We count a crossing if the line intersects a rectangle and
        // does not end in it
        int crossings = 0;
        for (Rectangle2D llRect : llRects) {
            if (llRect.intersectsLine(edgeLine)) {
                // If none of the end points are in the rectangle, count a crossing
                if (!llRect.contains(edgeLine.getX1(), edgeLine.getY1())
                        && !llRect.contains(edgeLine.getX2(), edgeLine.getY2())) {
                    
                    crossings++;
                }
            }
        }
        
        return crossings;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    // Utilities

    /**
     * Checks whether the given edge represents a message. This is the case if both of its end
     * points are lifelines.
     */
    private boolean isMessage(final ElkEdge edge) {
        // We only support simple edges
        if (edge.isHyperedge()) {
            return false;
        }
        
        return isLifeline(ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0)))
                && isLifeline(ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0)));
    }
    
    /**
     * Checks whether the given node represents an interaction.
     */
    private boolean isInteraction(final ElkNode node) {
        return nodeTypeEquals(node, "SURROUNDING_INTERACTION");
    }
    
    /**
     * Checks whether the given node represents a lifeline.
     */
    private boolean isLifeline(final ElkNode node) {
        return nodeTypeEquals(node, "LIFELINE");
    }
    
    /**
     * A copy of the node type property. We cannot use the NodeType enumeration here without
     * introducing a dependency to ELK Sequence which we don't want, so this is a... well, hack to
     * get around that problem.
     */
    private static final IProperty<Object> NODE_TYPE = new Property<>(
            "org.eclipse.elk.alg.sequence.type.node");
    
    /**
     * Checks whether the given node's type matches the expectation.
     */
    private boolean nodeTypeEquals(final ElkNode node, final String expected) {
        Object nodeType = node.getProperty(NODE_TYPE);
        return nodeType != null
                ? nodeType.toString().equals(expected)
                : false;
    }
    
}
