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
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions

/**
 * Creates concrete KRendering information for Ptolemy diagram elements.
 * 
 * @author ckru
 * @author cds
 */
class PtolemyFigureProvider {
    
    /** Marking nodes. */
    @Inject extension AnnotationExtensions
    /** Extensions used during the transformation. To make things easier. And stuff. */
    @Inject extension MiscellaneousExtensions
    /** Color stuff. */
    @Inject extension KColorExtensions
    /** Rendering stuff. */
    @Inject extension KRenderingExtensions
    
    /** Rendering factory used to instantiate KRendering instances. */
    val renderingFactory = KRenderingFactory::eINSTANCE
    
    
    /**
     * Renders the given node in a default way, that is, as a simple rectangle.
     * 
     * @return the created rendering.
     */
    def KRendering createDefaultRendering(KNode node) {
        return renderingFactory.createKRectangle()
    }
    
    /**
     * Creates a rendering for a relation node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createRelationNodeRendering(KNode node) {
        renderingFactory.createKPolygon() => [poly |
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
     * Creates a rendering for a state node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createStateNodeRendering(KNode node) {
        val isFinal = node.hasAnnotation("isFinalState")
        val isInitial = node.hasAnnotation("isInitialState")
        val lineWidth = if (isInitial) 4 else 1
        
        // Create the outer circle (which may remain the only one)
        val outerCircle = renderingFactory.createKRoundedRectangle() => [rec |
            rec.cornerHeight = 30
            rec.cornerWidth = 30
            rec.setAreaPlacementData(
                createKPosition(LEFT, 0, 0, TOP, 0, 0),
                createKPosition(LEFT, 30, 0, TOP, 30, 0)
            )
            rec.lineWidth = lineWidth
        ]
        
        // If this is a final state, we need to add an inner circle as well
        if (isFinal) {
            val innerCircle = renderingFactory.createKRoundedRectangle() => [rec |
                rec.cornerHeight = 22
                rec.cornerWidth = 22
                rec.setAreaPlacementData(
                    createKPosition(LEFT, 3, 0, TOP, 3, 0),
                    createKPosition(LEFT, 27, 0, TOP, 27, 0)
                )
                rec.lineWidth = lineWidth
            ]
            outerCircle.children += innerCircle
        }
        
        return outerCircle
    }
    
    /**
     * Creates a rendering for a regular node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createRegularNodeRendering(KNode node) {
        return createDefaultRendering(node)
    }
    
}