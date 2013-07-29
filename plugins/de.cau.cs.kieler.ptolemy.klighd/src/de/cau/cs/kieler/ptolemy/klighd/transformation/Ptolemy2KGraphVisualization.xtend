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
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.krendering.KAreaPlacementData
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.Trigger
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
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
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement

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
    /** Access to labels. */
    @Inject extension LabelExtensions
    /** Access to marked nodes. */
    @Inject extension MarkerExtensions
    /** Extensions used during the transformation. To make things easier. And stuff. */
    @Inject extension MiscellaneousExtensions
    /** Extensions for creating edge renderings. */
    @Inject extension KEdgeExtensions
    /** Utility class that provides renderings. */
    @Inject extension KRenderingExtensions
    /** Utility class that provides renderings. */
    @Inject extension KRenderingFigureProvider
    
    /** Rendering factory used to create stuff. */
    val renderingFactory = KRenderingFactory::eINSTANCE
    /** alpha value of the background of expanded compound nodes. */
    var compoundNodeAlpha = 10
    
    
    /**
     * Annotates the given KGraph with the information necessary to render it as a Ptolemy model.
     * 
     * @param kGraph the KGraph created from a Ptolemy model.
     * @param compoundNodeAlpha alpha value of the background of expanded compound nodes. A higher value
     *                          translates to more intense backgrounds. INTENSE!
     */
    def void visualize(KNode kGraph, int compoundNodeAlpha) {
        this.compoundNodeAlpha = compoundNodeAlpha
        
        // Set the layout lagorithm for the graph
        kGraph.setLayoutAlgorithm()
        
        // Recurse into subnodes
        visualizeRecursively(kGraph)
    }
    
    /**
     * Annotates the children of the given node with the information necessary to render them and
     * calls itself recursively with each of the nodes.
     * 
     * @param node the node to visualize.
     * @param firstLevel {@code true} if the given node is the root of the graph. Used to auto-expand
     *                   compound nodes on the first level.
     */
    def private void visualizeRecursively(KNode node) {
        // Visualize child nodes
        for (child : node.children) {
            // Add child node rendering
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
            } else if (child.markedAsComment) {
                // We have a comment node
                child.addCommentNodeRendering()
            } else if (child.markedAsParameterNode) {
                // We have a parameter node that displays model parameters
                child.addParameterNodeRendering()
            } else if (child.markedAsState) {
                // We have a state machine state
                child.addStateNodeRendering()
            } else if (child.markedAsConstActor) {
                // We have a const actor whose rendering is a bit special
                child.addConstNodeRendering()
            } else {
                // We have a regular node
                child.addRegularNodeRendering()
            }
            
            // Add label rendering
            child.addLabelRendering()
            
            // Add tool tip
            child.addToolTip()
            
            // Add port rendering
            for (port : child.ports) {
                port.addPortRendering()
                port.addLabelRendering()
                port.addToolTip()
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
     */
    def private void addCompoundNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(KlighdProperties::EXPAND, false)
        layout.setProperty(LayoutOptions::NODE_LABEL_PLACEMENT, EnumSet::of(
            NodeLabelPlacement::OUTSIDE, NodeLabelPlacement::H_LEFT, NodeLabelPlacement::V_TOP))
        layout.setProperty(LayoutOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
        layout.setProperty(LayoutOptions::SIZE_CONSTRAINT, SizeConstraint::fixed)
        
        node.setLayoutAlgorithm()
        
        // Create the rendering for this node
        val KRendering expandedRendering = createExpandedCompoundNodeRendering(node, compoundNodeAlpha)
        expandedRendering.setProperty(KlighdProperties::EXPANDED_RENDERING, true)
        expandedRendering.addAction(Trigger::DOUBLECLICK, KlighdConstants::ACTION_COLLAPSE_EXPAND)
        node.data += expandedRendering
        
        // Add a rendering for the collapsed version of this node
        val KRendering collapsedRendering = createRegularNodeRendering(node)
        collapsedRendering.setProperty(KlighdProperties::COLLAPSED_RENDERING, true)
        collapsedRendering.addAction(Trigger::DOUBLECLICK, KlighdConstants::ACTION_COLLAPSE_EXPAND)
        node.data += collapsedRendering
        
        layout.setLayoutSize(collapsedRendering)
    }
    
    /**
     * Renders the given node as a relation node. Also removes the relation's labels.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addRelationNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        
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
        layout.setProperty(LayoutOptions::NODE_LABEL_PLACEMENT, EnumSet::of(
            NodeLabelPlacement::OUTSIDE, NodeLabelPlacement::H_LEFT, NodeLabelPlacement::V_TOP))
        layout.setProperty(LayoutOptions::PRIORITY, 1000)
        
        // Create the rendering
        val rendering = createDirectorNodeRendering(node)
        node.data += rendering
        
        // Set size
        layout.setLayoutSize(rendering)
    }
    
    /**
     * Renders the given node as a comment node.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addCommentNodeRendering(KNode node) {
        // Create the rendering
        val rendering = createCommentNodeRendering(node)
        node.data += rendering
        
        // The rendering of all the comments edges is also special
        for (edge : node.incidentEdges) {
            val edgeRendering = createCommentEdgeRendering(edge)
            edge.data += edgeRendering
        }
    }
    
    /**
     * Renders the given node as a parameter node.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addParameterNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(LayoutOptions::PRIORITY, 800)
        
        // Create the rendering
        val rendering = createParameterNodeRendering(node)
        node.data += rendering
    }
    
    /**
     * Renders the given node as a state machine state.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addStateNodeRendering(KNode node) {
        // Create the rendering
        val rendering = createStateNodeRendering(node)
        node.data += rendering
    }
    
    /**
     * Renders the given node as a Const node, with the constant displayed in it.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addConstNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
        layout.setProperty(LayoutOptions::NODE_LABEL_PLACEMENT, EnumSet::of(
            NodeLabelPlacement::OUTSIDE, NodeLabelPlacement::H_LEFT, NodeLabelPlacement::V_TOP))
        layout.setProperty(LayoutOptions::PORT_LABEL_PLACEMENT, PortLabelPlacement::OUTSIDE)
        layout.setProperty(LayoutOptions::PORT_CONSTRAINTS, PortConstraints::FIXED_SIDE)
        
        // Create the rendering
        val rendering = createValueDisplayingNodeRendering(node,
            node.getAnnotationValue("value") ?: "")
        node.data += rendering
    }
    
    /**
     * Renders the given node just like it would be rendered in Ptolemy, if possible.
     * 
     * @param node the node to attach the rendering information to.
     */
    def private void addRegularNodeRendering(KNode node) {
        val layout = node.layout as KShapeLayout
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
        
        // Remove the port's label and put it into the tool tip text
        if (port.name.length > 0) {
            rendering.setProperty(KlighdProperties::TOOLTIP, "Port: " + port.name)
            port.labels.clear()
        }
        
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
        if (edge.source.markedAsState || edge.target.markedAsState) {
            // If the edge has state transition annotations, we need to visualize those
            val labelText = new StringBuffer()
            
            val annotation = edge.getAnnotationValue(ANNOTATION_ANNOTATION)
            if (!annotation.nullOrEmpty) {
                labelText.append("\n" + annotation)
            }
            
            val guardExpression = edge.getAnnotationValue(ANNOTATION_GUARD_EXPRESSION)
            if (!guardExpression.nullOrEmpty) {
                labelText.append("\nGuard: " + guardExpression)
            }
            
            val outputActions = edge.getAnnotationValue(ANNOTATION_OUTPUT_ACTIONS)
            if (!outputActions.nullOrEmpty) {
                labelText.append("\nOutput: " + outputActions)
            }
            
            val setActions = edge.getAnnotationValue(ANNOTATION_SET_ACTIONS)
            if (!setActions.nullOrEmpty) {
                labelText.append("\nSet: " + setActions)
            }
            
            // Actually set the label text if we found anything worthwhile and also set the edge
            // label placement accordingly
            if (labelText.length > 0) {
                edge.name = labelText.substring(1)
                
                val layout = edge.labels.get(0).layout
                layout.setProperty(LayoutOptions::EDGE_LABEL_PLACEMENT, EdgeLabelPlacement::CENTER)
            }
            
            // Now finally add an edge rendering, which in turn depends on additional stuff...
            val rendering = createTransitionRendering(edge)
            edge.data += rendering
        } else {
            // We have a regular edge
            edge.addRoundedBendsPolyline(5f, 2f)
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
            // Add empty text rendering
            val ktext = renderingFactory.createKText()
            label.data += ktext
            
            // Make the text of edge labels 
            if (element instanceof KEdge) {
                ktext.fontSize = KlighdConstants::DEFAULT_FONT_SIZE - 2
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Tool Tips
    
    /**
     * Generates a tool tip for the given element based on its properties.
     * 
     * @param element the element to generate the tooltip for.
     */
    def private void addToolTip(KGraphElement element) {
        val toolTip = new StringBuffer("\n" + element.KRendering.getProperty(KlighdProperties::TOOLTIP))
        if (toolTip.length == 1) {
            toolTip.deleteCharAt(0)
        }
        
        // Look for properties that don't start with an underscore (these are the ones we want the
        // user to see)
        for (property : element.annotations) {
            if (!property.name.startsWith("_")) {
                toolTip.append("\n").append(property.name)
                
                if (property.value != null && property.value.length() > 0) {
                    toolTip.append(": ").append(property.value)
                }
            }
        }
        
        // If we have found something, display them as tooltip
        if (toolTip.length > 0) {
            element.KRendering.setProperty(KlighdProperties::TOOLTIP, toolTip.substring(1))
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
    
    /**
     * Sets the layout algorithm of the given node depending on which kind of diagram the node hosts.
     * 
     * @param node the node to set the layout algorithm information on.
     */
    def private void setLayoutAlgorithm(KNode node) {
        val layout = node.layout
        // Check if this is a state machine
        if (node.markedAsStateMachineContainer) {
            layout.setProperty(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.graphviz.dot")
            layout.setProperty(LayoutOptions::DIRECTION, Direction::RIGHT)
        } else {
            layout.setProperty(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.klay.layered")
            layout.setProperty(LayoutOptions::EDGE_ROUTING, EdgeRouting::ORTHOGONAL)
        }
    }
}
