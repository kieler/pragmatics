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
import de.cau.cs.kieler.core.krendering.KContainerRendering
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.LineStyle
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.PortSide
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.AnnotationExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.LabelExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MarkerExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MiscellaneousExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.util.GraphicsUtils

import static de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties.*
import static de.cau.cs.kieler.ptolemy.klighd.transformation.util.TransformationConstants.*

/**
 * Creates concrete KRendering information for Ptolemy diagram elements.
 * 
 * @author ckru
 * @author cds
 */
class KRenderingFigureProvider {
    
    /** Accessing annotations. */
    @Inject extension AnnotationExtensions
    /** Handling labels. */
    @Inject extension LabelExtensions
    /** Marking nodes. */
    @Inject extension MarkerExtensions
    /** Extensions used during the transformation. To make things easier. And stuff. */
    @Inject extension MiscellaneousExtensions
    /** Create KRenderings from Ptolemy figures. */
    @Inject extension PtolemyFigureInterface
    /** Color stuff. */
    @Inject extension KColorExtensions
    /** Rendering stuff. */
    @Inject extension KRenderingExtensions
    /** Rendering stuff. */
    @Inject extension KPolylineExtensions
    
    /** Rendering factory used to instantiate KRendering instances. */
    val renderingFactory = KRenderingFactory::eINSTANCE
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Node Renderings
    
    
    /**
     * Renders the given node in a default way, that is, as a simple rectangle.
     * 
     * @return the created rendering.
     */
    def KRendering createDefaultRendering(KNode node) {
        val rendering = renderingFactory.createKRectangle() => [rect |
            rect.setBackgroundColor(255, 255, 255)
        ]
        
        return rendering
    }
    
    /**
     * Creates a rendering for an expanded compound node.
     * 
     * @param node the node to create the rendering information for.
     * @param alpha the alpha value of the compound node background.
     * @return the rendering.
     */
    def KRendering createExpandedCompoundNodeRendering(KNode node, int alpha) {
        // This is the code for representing expanded compound nodes as rounded rectangles with
        // progressively darker backgrounds whose color depends on whether the expanded node is
        // a regular node or whether it displays a state refinement
        val bgColor = if (node.markedAsState) {
            renderingFactory.createKColor() => [col |
                col.red = 11
                col.green = 188
                col.blue = 11
            ]
        } else {
            renderingFactory.createKColor() => [col |
                col.red = 16
                col.green = 78
                col.blue = 139
            ]
        }
        
        val rendering = renderingFactory.createKRoundedRectangle() => [rect |
            rect.cornerHeight = 15
            rect.cornerWidth = 15
            rect.setLineWidth(0)
            rect.styles += renderingFactory.createKBackground() => [bg |
                bg.alpha = alpha
                bg.color = bgColor
            ]
        ]

        // This in turn is the code for representing expanded compound nodes as regular rectangles
        // with drop shadows
//        val rendering = renderingFactory.createKRectangle() => [rect |
//            rect.styles += renderingFactory.createKBackground() => [bg |
//                bg.alpha = alpha
//                bg.color = renderingFactory.createKColor() => [col |
//                    col.red = 16
//                    col.green = 78
//                    col.blue = 139
//                ]
//            ]
//            rect.setShadow(GraphicsUtils::lookupColor("darkgrey"))
//        ]
        
        return rendering
    }
    
    /**
     * Creates a rendering for a relation node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createRelationNodeRendering(KNode node) {
        return renderingFactory.createKPolygon() => [poly |
            poly.points += createKPosition(5, 0)
            poly.points += createKPosition(10, 5)
            poly.points += createKPosition(5, 10)
            poly.points += createKPosition(0, 5)
            poly.points += createKPosition(5, 0)
            poly.setBackgroundColor(0, 0, 0)
        ]
    }
    
    /**
     * Creates a rendering for a director node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createDirectorNodeRendering(KNode node) {
        return renderingFactory.createKRectangle() => [rec |
            rec.background = "green".color
            rec.foreground = "black".color
            rec.setAreaPlacementData(
                createKPosition(LEFT, 0, 0, TOP, 0, 0),
                createKPosition(LEFT, 100, 0, TOP, 30, 0)
            )
        ]
    }
    
    /**
     * Creates a rendering for a comment node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createCommentNodeRendering(KNode node) {
        val rectangle = renderingFactory.createKRectangle() => [rec |
            rec.background = renderingFactory.createKColor() => [col |
                col.red = 255
                col.green = 255
                col.blue = 204
            ]
            rec.setLineWidth(0)
        ]
        
        // Add the comment's text
        rectangle.children += renderingFactory.createKText() => [text |
            text.text = node.layout.getProperty(COMMENT_TEXT)
            text.setSurroundingSpace(5, 0)
            text.setFontSize(KlighdConstants::DEFAULT_FONT_SIZE - 2)
        ]
        
        return rectangle
    }
    
    /**
     * Creates a rendering for a parameter node. Parameter nodes display model parameters in a grid-like
     * fashion.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createParameterNodeRendering(KNode node) {
        // Create the surrounding container rendering with a three-column grid placement
        val rectangle = renderingFactory.createKRectangle() => [rec |
            rec.foregroundInvisible = true
            rec.childPlacement = renderingFactory.createKGridPlacement() => [grid |
                grid.numColumns = 3
            ]
        ]
        
        // Find the parameters that should be displayed
        val parameters = node.layout.getProperty(PT_PARAMETERS)
        
        // Visualize each parameter
        for (parameter : parameters) {
            val circle = renderingFactory.createKEllipse() => [ell |
                ell.background = GraphicsUtils::lookupColor("blue")
                ell.setGridPlacementData(
                    15,
                    15,
                    createKPosition(LEFT, -4, 0.5f, TOP, -4, 0.5f),
                    createKPosition(RIGHT, -4, 0.5f, BOTTOM, -4, 0.5f))
                ell.lineWidth = 1
            ]
            rectangle.children += circle
            
            val nameText = renderingFactory.createKText()  => [name |
                name.text = parameter.first + ":"
                name.horizontalAlignment = H_LEFT
                name.setFontSize(KlighdConstants::DEFAULT_FONT_SIZE - 2)
                name.setGridPlacementData(
                    0,
                    // We need to specify a minimum height to work around a grid placement bug that
                    // would cause the cell not to be high enough for the label
                    25,
                    createKPosition(LEFT, 0, 0, TOP, 3, 0),
                    createKPosition(RIGHT, 5, 0, BOTTOM, 3, 0))
            ]
            rectangle.children += nameText
            
            val valueText = renderingFactory.createKText()  => [value |
                // We shorten the text to 150 characters. This could definitely be done in a more
                // intelligent way (with label management techniques, perhaps), but this is good enough
                // for the moment
                value.text = if (parameter.second.length > 150) {
                        parameter.second.substring(0, 150) + "..."
                    } else {
                        parameter.second
                    }
                value.horizontalAlignment = H_LEFT
                value.setFontSize(KlighdConstants::DEFAULT_FONT_SIZE - 2)
                value.setGridPlacementData(
                    0,
                    // We need to specify a minimum height to work around a grid placement bug that
                    // would cause the cell not to be high enough for the label
                    25,
                    createKPosition(LEFT, 5, 0, TOP, 3, 0),
                    createKPosition(RIGHT, 5, 0, BOTTOM, 3, 0))
            ]
            rectangle.children += valueText
        }
        
        return rectangle
    }
    
    /**
     * Creates a rendering for an edge that attaches a comment node to a commented node.
     * 
     * @param edge the edge to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createCommentEdgeRendering(KEdge edge) {
        val polyline = renderingFactory.createKPolyline() => [line |
            line.lineStyle = LineStyle::DASH
            line.lineWidth = 1
            line.foreground = GraphicsUtils::lookupColor("grey")
        ]
        
        return polyline
    }
    
    /**
     * Creates a rendering for a state node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createStateNodeRendering(KNode node) {
        val isFinal = node.getAnnotationBooleanValue("isFinalState")
        val isInitial = node.getAnnotationBooleanValue("isInitialState")
        val lineWidth = if (isInitial) 3 else 1
        val initialFinalInset = if (isInitial) 4 else 3
        
        // The background color depends on whether the state has a refinement
        val bgColor = if (node.markedAsHavingRefinement) {
            renderingFactory.createKColor() => [col |
                col.red = 204
                col.green = 255
                col.blue = 204
            ]
        } else {
            GraphicsUtils::lookupColor("white")
        }
        
        // Reset the regular label and replace it with a KText element; since we're using GraphViz dot
        // to layout state machines, the label wouldn't be placed properly anyway
        val label = renderingFactory.createKText() => [text |
            text.text = node.name
            text.setAreaPlacementData(
                createKPosition(LEFT, 14, 0, TOP, 8, 0),
                createKPosition(RIGHT, 14, 0, BOTTOM, 8, 0)
            )
        ]
        node.name = ""
        
        // Create the outer circle (which may remain the only one)
        val outerCircle = renderingFactory.createKRoundedRectangle() => [rec |
            rec.cornerHeight = 30
            rec.cornerWidth = 15
            rec.setAreaPlacementData(
                createKPosition(LEFT, 0, 0, TOP, 0, 0),
                createKPosition(RIGHT, 0, 0, BOTTOM, 0, 0)
            )
            rec.lineWidth = lineWidth
            rec.background = bgColor
        ]
        
        // If this is a final state, we need to add an inner circle as well
        if (isFinal) {
            val innerCircle = renderingFactory.createKRoundedRectangle() => [rec |
                rec.cornerHeight = 22
                rec.cornerWidth = 12
                rec.setAreaPlacementData(
                    createKPosition(LEFT, initialFinalInset, 0, TOP, initialFinalInset, 0),
                    createKPosition(RIGHT, initialFinalInset, 0, BOTTOM, initialFinalInset, 0)
                )
                rec.lineWidth = lineWidth
            ]
            innerCircle.children += label
            outerCircle.children += innerCircle
        } else {
            outerCircle.children += label
        }
        
        return outerCircle
    }
    
    /**
     * Creates a rendering for a node that displays a value.
     * 
     * @param node the node to create the rendering information for.
     * @param value the value the node should display.
     * @return the rendering.
     */
    def KRendering createValueDisplayingNodeRendering(KNode node, String value) {
        val nodeRendering = createDefaultRendering(node) as KContainerRendering
        
        // Add a text field to the default rendering
        nodeRendering.children += renderingFactory.createKText() => [text |
            text.text = value
            text.setSurroundingSpace(5, 0)
            text.setForeground(GraphicsUtils::lookupColor("darkgrey"))
        ]
        
        return nodeRendering
    }
    
    /**
     * Creates a rendering for a node that represents a modal model port.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createModalModelPortRendering(KNode node) {
        val input = node.markedAsInputPort
        val output = node.markedAsOutputPort
        val multiport = node.hasAnnotation("multiport")
        val polygon = renderingFactory.createKPolygon()
        
        if (multiport) {
            polygon.setBackgroundColor(255, 255, 255)
            
            if (input && !output) {
                polygon.points += createKPosition(0, 5)
                polygon.points += createKPosition(5, 5)
                polygon.points += createKPosition(5, 0)
                polygon.points += createKPosition(10, 5)
                polygon.points += createKPosition(10, 0)
                polygon.points += createKPosition(20, 10)
                polygon.points += createKPosition(10, 20)
                polygon.points += createKPosition(10, 15)
                polygon.points += createKPosition(5, 20)
                polygon.points += createKPosition(5, 15)
                polygon.points += createKPosition(0, 15)
                polygon.points += createKPosition(0, 5)
            } else if (!input && output) {
                polygon.points += createKPosition(0, 0)
                polygon.points += createKPosition(5, 5)
                polygon.points += createKPosition(5, 0)
                polygon.points += createKPosition(10, 5)
                polygon.points += createKPosition(20, 5)
                polygon.points += createKPosition(20, 15)
                polygon.points += createKPosition(10, 15)
                polygon.points += createKPosition(5, 20)
                polygon.points += createKPosition(5, 15)
                polygon.points += createKPosition(0, 20)
                polygon.points += createKPosition(0, 0)
            } else if (input && output) {
                polygon.points += createKPosition(0, 5)
                polygon.points += createKPosition(5, 5)
                polygon.points += createKPosition(5, 0)
                polygon.points += createKPosition(10, 5)
                polygon.points += createKPosition(10, 0)
                polygon.points += createKPosition(15, 5)
                polygon.points += createKPosition(20, 5)
                polygon.points += createKPosition(20, 15)
                polygon.points += createKPosition(15, 15)
                polygon.points += createKPosition(10, 20)
                polygon.points += createKPosition(10, 15)
                polygon.points += createKPosition(5, 20)
                polygon.points += createKPosition(5, 15)
                polygon.points += createKPosition(0, 15)
                polygon.points += createKPosition(0, 5)
            } else {
                polygon.points += createKPosition(0, 5)
                polygon.points += createKPosition(20, 5)
                polygon.points += createKPosition(20, 15)
                polygon.points += createKPosition(0, 15)
                polygon.points += createKPosition(0, 5)
            }
        } else {
            polygon.setBackgroundColor(0, 0, 0)
            
            if (input && !output) {
                polygon.points += createKPosition(10, 0)
                polygon.points += createKPosition(20, 10)
                polygon.points += createKPosition(10, 20)
                polygon.points += createKPosition(10, 15)
                polygon.points += createKPosition(0, 15)
                polygon.points += createKPosition(0, 5)
                polygon.points += createKPosition(10, 5)
                polygon.points += createKPosition(10, 0)
            } else if (!input && output) {
                polygon.points += createKPosition(0, 0)
                polygon.points += createKPosition(5, 5)
                polygon.points += createKPosition(20, 5)
                polygon.points += createKPosition(20, 15)
                polygon.points += createKPosition(5, 15)
                polygon.points += createKPosition(0, 20)
                polygon.points += createKPosition(0, 0)
            } else if (input && output) {
                polygon.points += createKPosition(0, 5)
                polygon.points += createKPosition(7, 5)
                polygon.points += createKPosition(7, 0)
                polygon.points += createKPosition(12, 5)
                polygon.points += createKPosition(20, 5)
                polygon.points += createKPosition(20, 15)
                polygon.points += createKPosition(12, 15)
                polygon.points += createKPosition(7, 20)
                polygon.points += createKPosition(7, 15)
                polygon.points += createKPosition(0, 15)
                polygon.points += createKPosition(0, 5)
            } else {
                polygon.points += createKPosition(0, 5)
                polygon.points += createKPosition(20, 5)
                polygon.points += createKPosition(20, 15)
                polygon.points += createKPosition(0, 15)
                polygon.points += createKPosition(0, 5)
            }
        }
        
        return polygon
    }
    
    /**
     * Creates a rendering for a regular node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createRegularNodeRendering(KNode node) {
        val ptRendering = createPtolemyFigureRendering(node.getAnnotationValue(ANNOTATION_PTOLEMY_CLASS))
        return ptRendering ?: createDefaultRendering(node)
    }
    
    /**
     * Creates a rendering for an accumulator node. This needs to be a separate case because the SVG
     * description for accumulator nodes in Ptolemy is broken.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createAccumulatorNodeRendering(KNode node) {
        val accumulatorSvg = "<svg height=\"41\" width=\"41\" >"
                + "<rect height=\"40\" style=\"fill:white;stroke:black;stroke-width:1\" "
                + "width=\"40\" x=\"0.0\" y=\"0.0\" />"
                + "<path d=\"m 29.126953,6.2304687 0,3.0410157 -13.833984,0 8.789062,9.3515626 "
                + "-8.789062,10.335937 14.554687,0 0,3.041016 -18.246093,0 "
                + "0,-3.550781 8.419921,-9.826172 -8.419921,-8.9648439 0,-3.4277344 z\" />"
                + "</svg>"
        return GraphicsUtils::createFigureFromSvg(accumulatorSvg)
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Edge Renderings
    
    /**
     * Creates a rendering for an edge that represents a transition in a state machine.
     */
    def KRendering createTransitionRendering(KEdge edge) {
        val rendering = renderingFactory.createKSpline() => [spline |
            spline.lineWidth = 1.6f
            
            // Special rendering options for transition types
            if (edge.getAnnotationBooleanValue(ANNOTATION_NONDETERMINISTIC_TRANSITION)) {
                spline.foreground = GraphicsUtils::lookupColor("red")
            }
            
            if (edge.getAnnotationBooleanValue(ANNOTATION_DEFAULT_TRANSITION)) {
                spline.lineStyle = LineStyle::DASH
            }
        ]
        rendering.addArrowDecorator()
        
        return rendering
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Port Renderings
    
    /** Width / height of a port. */
    private val PORT_SIZE = 7f
    
    /** Half the width / height of a port. */
    private val PORT_SIZE_HALF = 3.5f
    
    
    /**
     * Creates a rendering for a port.
     * 
     * @param port the port to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createPortRendering(KPort port) {
        val layout = port.getData(typeof(KShapeLayout))
        
        // Determine the port color
        val portFillColor = if (port.hasAnnotation(IS_PARAMETER_PORT)) {
            // Parameter ports are gray
            GraphicsUtils::lookupColor("gray")
        } else if (port.hasAnnotation(IS_IO_PORT) && port.hasAnnotation(IS_MULTIPORT)) {
            // IO Multiports are white
            GraphicsUtils::lookupColor("white")
        } else {
            // All other ports are black
            GraphicsUtils::lookupColor("black")
        }
        
        // Create the triangle (depending on the port size)
        val polygon = port.addPolygon()
        switch layout.getProperty(LayoutOptions::PORT_SIDE) {
            case PortSide::NORTH: {
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, 0, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE, 0, TOP, 0, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE_HALF, 0, TOP, PORT_SIZE, 0)
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, 0, 0)
            }
            case PortSide::SOUTH: {
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, PORT_SIZE, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE, 0, TOP, PORT_SIZE, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE_HALF, 0, TOP, 0, 0)
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, PORT_SIZE, 0)
            }
            default: {
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, PORT_SIZE, 0)
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, 0, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE, 0, TOP, PORT_SIZE_HALF, 0)
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, PORT_SIZE, 0)
            }
        }
        
        // Set color properties
        polygon.setBackground(portFillColor)
        polygon.setForeground(GraphicsUtils::lookupColor("black"))
        
        return polygon
    }
    
}
