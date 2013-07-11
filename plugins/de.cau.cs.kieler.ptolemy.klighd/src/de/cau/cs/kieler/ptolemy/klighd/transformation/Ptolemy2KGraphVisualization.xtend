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
package de.cau.cs.kieler.ptolemy.klighd.transformation

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.krendering.KAction
import de.cau.cs.kieler.core.krendering.KAreaPlacementData
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.Trigger
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.ViewSynthesisShared
import de.cau.cs.kieler.core.math.KVector
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.Direction
import de.cau.cs.kieler.kiml.options.EdgeRouting
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.NodeLabelPlacement
import de.cau.cs.kieler.kiml.options.PortConstraints
import de.cau.cs.kieler.kiml.options.SizeConstraint
import de.cau.cs.kieler.klighd.KlighdConstants
import java.util.EnumSet

/**
 * Enriches a KGraph model freshly transformed from a Ptolemy2 model with the KRendering information
 * necessary to properly display the model. This is the final step of the Ptolemy model import process.
 * 
 * @author cds
 */
@ViewSynthesisShared
class Ptolemy2KGraphVisualization {
    
    /** Access to marked nodes. */
    @Inject extension MarkerExtensions
    /** Extensions used during the transformation. To make things easier. And stuff. */
    @Inject extension MiscellaneousExtensions
    /** Extensions for creating edge renderings. */
    @Inject extension KEdgeExtensions
    /** Extensions for creating polylines. */
    @Inject extension KPolylineExtensions
    /** Extensions for creating renderings. */
    @Inject extension KRenderingExtensions
    /** Utility class that provides renderings. */
    @Inject extension PtolemyFigureProvider
    
    
    /**
     * Annotates the given KGraph with the information necessary to render it as a Ptolemy model.
     * 
     * @param kGraph the KGraph created from a Ptolemy model.
     */
    def void visualize(KNode kGraph) {
        // The first level is definitely a compound node
        addCompoundNodeRendering(kGraph)
        
        // Recurse into subnodes
        visualizeRecursively(kGraph)
    }
    
    /**
     * Annotates the children of the given node with the information necessary to render them and
     * calls itself recursively with each of the nodes.
     * 
     * @param node the node to visualize.
     */
    def private void visualizeRecursively(KNode node) {
        // Visualize ports
        for (port : node.ports) {
            port.addPortRendering()
        }
        
        // Visualize outgoing edges
        for (edge : node.outgoingEdges) {
            edge.addEdgeRendering()
        }
        
        // Visualize child nodes
        for (child : node.children) {
            // Check which kind of a node we have
            if (!child.children.empty) {
                // We have a compound node
                child.addCompoundNodeRendering()
                visualizeRecursively(child)
            } else if (child.markedAsHypernode) {
                // We have a hypernode (a relation node, in Ptolemy speak)
                child.addRelationNodeRendering()
            } else if (child.markedAsDirector) {
                // We have a director node
                child.addDirectorNodeRendering()
            } else if (child.markedAsState) {
                // We have a state machine state
                child.addStateNodeRendering()
            } else {
                // We have a regular node
                child.addRegularNodeRendering()
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Rendering of Nodes
    
    /**
     * Renders the given node as a compound node.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addCompoundNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(KlighdConstants::KLIGHD_SELECTION_UNPICKABLE, true)
        
        // Check if this is a state machine
        if (node.markedAsStateMachineContainer) {
            layout.setProperty(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.graphviz.dot")
            layout.setProperty(LayoutOptions::DIRECTION, Direction::RIGHT)
        } else {
            layout.setProperty(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.klay.layered")
            layout.setProperty(LayoutOptions::EDGE_ROUTING, EdgeRouting::ORTHOGONAL)
            layout.setProperty(LayoutOptions::NODE_LABEL_PLACEMENT,
                NodeLabelPlacement::outsideBottomCenter)
        }
        
        // Create the rendering for this node
        val KRendering expandedRendering = createDefaultRendering(node)
        expandedRendering.setProperty(KlighdConstants::EXPANDED_RENDERING, true)
        node.data += expandedRendering
        
        val KAction collapseAction = KRenderingFactory::eINSTANCE.createKAction()
        collapseAction.setTrigger(Trigger::DOUBLECLICK)
        collapseAction.setId(KlighdConstants::ACTION_COLLAPSE_EXPAND)
        expandedRendering.actions.add(collapseAction)
        
        // Add a rendering for the collapsed version of this node
        // TODO: Retrieve the collapsed rendering
        val KRendering collapsedRendering = node.addEllipse();
        collapsedRendering.setProperty(KlighdConstants::COLLAPSED_RENDERING, true)
        
        val KAction expandAction = KRenderingFactory::eINSTANCE.createKAction()
        expandAction.setTrigger(Trigger::DOUBLECLICK)
        expandAction.setId(KlighdConstants::ACTION_COLLAPSE_EXPAND)
        collapsedRendering.actions.add(expandAction)
        
        layout.setLayoutSize(expandedRendering)
    }
    
    /**
     * Renders the given node as a relation node.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addRelationNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(KlighdConstants::KLIGHD_SELECTION_UNPICKABLE, true)
        
        // Create the rendering
        node.data += createRelationNodeRendering(node)
        
        // Set size
        layout.height = 10
        layout.width = 10
    }
    
    /**
     * Renders the given node as a director.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addDirectorNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(KlighdConstants::KLIGHD_SELECTION_UNPICKABLE, true)
        
        // Create the rendering
        val rendering = createDirectorNodeRendering(node)
        node.data += rendering
        
        // Set size
        layout.setLayoutSize(rendering)
    }
    
    /**
     * Renders the given node as a state machine state.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addStateNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(KlighdConstants::KLIGHD_SELECTION_UNPICKABLE, true)
        
        // Create the rendering
        val rendering = createStateNodeRendering(node)
        node.data += rendering
        
        // Set size
        layout.setLayoutSize(rendering)
    }
    
    /**
     * Renders the given node just like it would be rendered in Ptolemy, if possible.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addRegularNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(KlighdConstants::KLIGHD_SELECTION_UNPICKABLE, true)
        layout.setProperty(LayoutOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
        
        // Create the rendering
        val rendering = createRegularNodeRendering(node)
        node.data += rendering
        
        
        // TODO: Calculate layout size.
        layout.setLayoutSize(null)
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Rendering of Ports
    
    /**
     * Adds KRendering information to the given port.
     * 
     * @param port the port to add rendering information to.
     */
    def private void addPortRendering(KPort port) {
        val layout = port.layout as KShapeLayout
        layout.setProperty(KlighdConstants::KLIGHD_SELECTION_UNPICKABLE, true)
        
        
        
        // Add size information
        layout.width = 8
        layout.height = 8
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Rendering of Edges
    
    /**
     * Adds KRendering information to the given edge.
     * 
     * @param edge the edge to add rendering information to.
     */
    def private void addEdgeRendering(KEdge edge) {
        val layout = edge.layout
        layout.setProperty(KlighdConstants::KLIGHD_SELECTION_UNPICKABLE, true)
        
        if (edge.source.markedAsState || edge.target.markedAsState) {
            // We have an edge in a state machine
            edge.addSpline(1.6f).addArrowDecorator()
        } else {
            // We have a regular edge
            edge.addRoundedBendsPolyline(5f, 1.6f)
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Utility Methods
    
    /**
     * Sets the layout size depending on the information in the given rendering information.
     * 
     * @param layout the layout object to set size information on.
     * @param rendering the rendering to infer the size information from. May be {@code null}, in which
     *                  case a default size is assumed.
     */
    def private void setLayoutSize(KShapeLayout layout, KRendering rendering) {
        if (rendering == null) {
            // If we have no rendering in the first place, fix the size
            layout.height = 50
            layout.width = 50
        } else if (rendering.placementData != null
            && rendering.placementData instanceof KAreaPlacementData) {
            
            // We have concrete placement data to infer the size from
            val placementData = rendering.placementData as KAreaPlacementData
            
            layout.height = placementData.bottomRight.y.absolute
            layout.width = placementData.bottomRight.x.absolute
        } else {
            // Use a default minimum size
            layout.setProperty(KlighdConstants::MINIMAL_NODE_SIZE, new KVector(60, 40))
            layout.setProperty(LayoutOptions::SIZE_CONSTRAINT, EnumSet::of(SizeConstraint::MINIMUM_SIZE))
        }
    }
}