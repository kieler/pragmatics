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
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
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
import de.cau.cs.kieler.kiml.options.PortLabelPlacement
import de.cau.cs.kieler.kiml.options.PortSide
import de.cau.cs.kieler.kiml.options.SizeConstraint
import de.cau.cs.kieler.klay.layered.properties.Properties
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.util.KlighdProperties
import java.util.EnumSet

import static de.cau.cs.kieler.ptolemy.klighd.transformation.TransformationConstants.*

/**
 * Enriches a KGraph model freshly transformed from a Ptolemy2 model with the KRendering information
 * necessary to properly display the model. This is the final step of the Ptolemy model import process.
 * 
 * @author cds
 */
@ViewSynthesisShared
class Ptolemy2KGraphVisualization {
    
    /** Access to annotations. */
    @Inject extension AnnotationExtensions
    /** Access to marked nodes. */
    @Inject extension MarkerExtensions
    /** Extensions used during the transformation. To make things easier. And stuff. */
    @Inject extension MiscellaneousExtensions
    /** Extensions for creating edge renderings. */
    @Inject extension KEdgeExtensions
    /** Extensions for creating polylines. */
    @Inject extension KPolylineExtensions
    /** Utility class that provides renderings. */
    @Inject extension KRenderingExtensions
    /** Utility class that provides renderings. */
    @Inject extension KRenderingFigureProvider
    
    val renderingFactory = KRenderingFactory::eINSTANCE
    
    
    /**
     * Annotates the given KGraph with the information necessary to render it as a Ptolemy model.
     * 
     * @param kGraph the KGraph created from a Ptolemy model.
     */
    def void visualize(KNode kGraph) {
        // The first level is definitely a compound node
        kGraph.addCompoundNodeRendering(true)
        
        // Recurse into subnodes
        visualizeRecursively(kGraph, true)
    }
    
    /**
     * Annotates the children of the given node with the information necessary to render them and
     * calls itself recursively with each of the nodes.
     * 
     * @param node the node to visualize.
     * @param firstLevel {@code true} if the given node is the root of the graph. Used to auto-expand
     *                   compound nodes on the first level.
     */
    def private void visualizeRecursively(KNode node, boolean firstLevel) {
        // Visualize child nodes
        for (child : node.children) {
            // Add child node rendering
            if (!child.children.empty) {
                // We have a compound node
                child.addCompoundNodeRendering(firstLevel)
                visualizeRecursively(child, false)
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
            
            // Add label rendering
            child.addLabelRendering()
            
            // Add port rendering
            for (port : child.ports) {
                port.addPortRendering()
                port.addLabelRendering()
            }
            
            // Add edge rendering
            for (edge : child.outgoingEdges) {
                edge.addEdgeRendering()
                edge.addLabelRendering()
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Rendering of Nodes
    
    /**
     * Renders the given node as a compound node.
     * 
     * @param node the node to attach the rendering information to.
     * @param expand {@code true} if the node should initially be expanded.
     */
    def private void addCompoundNodeRendering(KNode node, boolean expand) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(KlighdProperties::KLIGHD_SELECTION_UNPICKABLE, true)
        layout.setProperty(KlighdProperties::EXPAND, expand)
        layout.setProperty(LayoutOptions::NODE_LABEL_PLACEMENT, EnumSet::of(
            NodeLabelPlacement::OUTSIDE, NodeLabelPlacement::H_LEFT, NodeLabelPlacement::V_TOP))
        layout.setProperty(LayoutOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
        
        // Check if this is a state machine
        if (node.markedAsStateMachineContainer) {
            layout.setProperty(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.graphviz.dot")
            layout.setProperty(LayoutOptions::DIRECTION, Direction::RIGHT)
        } else {
            layout.setProperty(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.klay.layered")
            layout.setProperty(LayoutOptions::EDGE_ROUTING, EdgeRouting::ORTHOGONAL)
        }
        
        // Create the rendering for this node
        val KRendering expandedRendering = createDefaultRendering(node)
        expandedRendering.setProperty(KlighdProperties::EXPANDED_RENDERING, true)
        expandedRendering.addAction(Trigger::DOUBLECLICK, KlighdConstants::ACTION_COLLAPSE_EXPAND)
        node.data += expandedRendering
        
        // Add a rendering for the collapsed version of this node
        val KRendering collapsedRendering = createRegularNodeRendering(node)
        collapsedRendering.setProperty(KlighdProperties::COLLAPSED_RENDERING, true)
        collapsedRendering.addAction(Trigger::DOUBLECLICK, KlighdConstants::ACTION_COLLAPSE_EXPAND)
        node.data += collapsedRendering
        
        layout.setLayoutSize(expandedRendering)
    }
    
    /**
     * Renders the given node as a relation node. Also removes the relation's labels.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addRelationNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(KlighdProperties::KLIGHD_SELECTION_UNPICKABLE, true)
        
        // Remove the relation's labels
        node.labels.clear()
        
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
        layout.setProperty(KlighdProperties::KLIGHD_SELECTION_UNPICKABLE, true)
        layout.setProperty(LayoutOptions::NODE_LABEL_PLACEMENT, EnumSet::of(
            NodeLabelPlacement::OUTSIDE, NodeLabelPlacement::H_LEFT, NodeLabelPlacement::V_TOP))
        
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
        layout.setProperty(KlighdProperties::KLIGHD_SELECTION_UNPICKABLE, true)
        layout.setProperty(LayoutOptions::NODE_LABEL_PLACEMENT, EnumSet::of(
            NodeLabelPlacement::OUTSIDE, NodeLabelPlacement::H_LEFT, NodeLabelPlacement::V_TOP))
        layout.setProperty(LayoutOptions::PORT_LABEL_PLACEMENT, PortLabelPlacement::OUTSIDE)
        
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
        layout.setProperty(KlighdProperties::KLIGHD_SELECTION_UNPICKABLE, true)
        layout.setProperty(LayoutOptions::NODE_LABEL_PLACEMENT, EnumSet::of(
            NodeLabelPlacement::OUTSIDE, NodeLabelPlacement::H_LEFT, NodeLabelPlacement::V_TOP))
        layout.setProperty(LayoutOptions::PORT_LABEL_PLACEMENT, PortLabelPlacement::OUTSIDE)
        layout.setProperty(LayoutOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
        
        // Some kinds of nodes require special treatment
        var KRendering rendering = switch node.getAnnotationValue(ANNOTATION_PTOLEMY_CLASS) {
            case "ptolemy.actor.lib.Accumulator" : createAccumulatorNodeRendering(node)
            default : createRegularNodeRendering(node)
        }
        node.data += rendering
        
        // Calculate layout size.
        layout.setLayoutSize(rendering)
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
        layout.setProperty(KlighdProperties::KLIGHD_SELECTION_UNPICKABLE, true)
        
        // Remove the port's labels
        // TODO: Instead of doing this, we should think about changing their appearance
        port.labels.clear()
        
        // Find the port type
        val inputPort = port.markedAsInputPort
        val outputPort = port.markedAsOutputPort
        
        // Find the port side
        val ptolemySpecifiedPortSide = port.getAnnotationValue("_cardinal")
        var portSide = PortSide::UNDEFINED
        
        // If Ptolemy specifies a port side, use that
        try {
            portSide = PortSide::valueOf(ptolemySpecifiedPortSide)
        } catch (Exception e) {
            // Happens if there is no port side or the specification doesn't match our expectations
        }
        
        // If the port side is still undefined, infer it from the port type
        if (portSide == PortSide::UNDEFINED) {
            if (inputPort && outputPort) {
                portSide = PortSide::SOUTH
            } else if (inputPort) {
                portSide = PortSide::WEST
            } else {
                portSide = PortSide::EAST
            }
        }
        
        layout.setProperty(LayoutOptions::PORT_SIDE, portSide)
        
        // Set port properties depending on the port side
        switch portSide {
            case PortSide::NORTH: {
                layout.setProperty(LayoutOptions::OFFSET, 0f)
            }
            case PortSide::SOUTH: {
                layout.setProperty(LayoutOptions::OFFSET, 0f)
            }
            case PortSide::EAST: {
                layout.setProperty(LayoutOptions::OFFSET, 0f)
                layout.setProperty(Properties::PORT_ANCHOR, new KVector(7, 3.5))
            }
            case PortSide::WEST: {
                layout.setProperty(LayoutOptions::OFFSET, 0f)
                layout.setProperty(Properties::PORT_ANCHOR, new KVector(0, 3.5))
            }
        }
        
        // Add rendering
        val rendering = createPortRendering(port)
        port.data += rendering
        
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
        layout.setProperty(KlighdProperties::KLIGHD_SELECTION_UNPICKABLE, true)
        
        if (edge.source.markedAsState || edge.target.markedAsState) {
            // We have an edge in a state machine
            edge.addSpline(1.6f).addArrowDecorator()
        } else {
            // We have a regular edge
            edge.addRoundedBendsPolyline(5f, 1.6f)
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Rendering of Labels
    
    /**
     * Attaches rendering information to all labels of the given element.
     * 
     * @param elemtent the element.
     */
    def private void addLabelRendering(KLabeledGraphElement element) {
        for (label : element.labels) {
            val layout = label.layout as KShapeLayout
            layout.setProperty(KlighdProperties::KLIGHD_SELECTION_UNPICKABLE, true)
            
            // Add empty text rendering
            label.data += renderingFactory.createKText()
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
            layout.setProperty(KlighdProperties::MINIMAL_NODE_SIZE, new KVector(60, 40))
            layout.setProperty(LayoutOptions::SIZE_CONSTRAINT, EnumSet::of(SizeConstraint::MINIMUM_SIZE))
        }
    }
}