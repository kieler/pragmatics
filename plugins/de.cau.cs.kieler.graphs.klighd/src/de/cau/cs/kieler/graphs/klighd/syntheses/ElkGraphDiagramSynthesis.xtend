/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.graphs.klighd.syntheses

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.formats.kgraph.KGraphExporter
import de.cau.cs.kieler.graphs.klighd.syntheses.InlineEdgeLabelStyleModifier.Visibility
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.actions.FocusAndContextAction
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KPolygon
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.EdgeLabelPlacement
import org.eclipse.elk.graph.ElkEdge
import org.eclipse.elk.graph.ElkGraphElement
import org.eclipse.elk.graph.ElkLabel
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.ElkPort

/**
 * Turns an ELK graph into a diagram KLighD knows how to display.
 * 
 * <p>Note: The coordinate transformtion here is not correct yet, at least for edges.</p>
 */
class ElkGraphDiagramSynthesis extends AbstractStyledDiagramSynthesis<ElkNode> {
    
    ///////////////////////////////////////////////////////////////////////////////
    // SYNTHESIS OPTIONS
    
    /* Synthesis options specifying whether several default values should be used or not. Default values are, eg, node
     * size if not specified and port ids as labels if no labels exist.
     */
    public static val SynthesisOption DEFAULT_NODE_SIZES = SynthesisOption.createCheckOption("Node Sizes", false)
    public static val SynthesisOption DEFAULT_NODE_LABELS = SynthesisOption.createCheckOption("Node Labels", false)
    public static val SynthesisOption DEFAULT_PORT_SIZES = SynthesisOption.createCheckOption("Port Sizes", false)
    public static val SynthesisOption DEFAULT_PORT_LABELS = SynthesisOption.createCheckOption("Port Labels", false)
    public static val SynthesisOption DEFAULT_EDGE_DIRECTIONS = SynthesisOption.createCheckOption("Edge Directions", true)
    
    // An advanced category for... well... advanced options and experimental things
    public static val SynthesisOption ADVANCED_CATEGORY = SynthesisOption.createCategory("Danger Zone", false)
    
    public static val SynthesisOption INLINE_LABELS_SEPARATOR = {
        val option = SynthesisOption.createSeparator("Edge Labels");
        option.category = ADVANCED_CATEGORY;
        option;
    }
    
    public static val INLINE_LABELS_OFF = "Oh God, no!";
    public static val INLINE_LABELS_SIMPLE = "Simple";
    public static val INLINE_LABELS_LINES = "Lines";
    public static val INLINE_LABELS_BRACKETS = "Brackets";
    public static val INLINE_LABELS_RECTS = "Rectangles";
    public static val SynthesisOption INLINE_LABELS = {
        // We're using a block expression here because we need to set the category on the option
        val option = SynthesisOption.createChoiceOption(
            "Display Inline",
            ImmutableList::of(INLINE_LABELS_OFF, INLINE_LABELS_SIMPLE, INLINE_LABELS_LINES, INLINE_LABELS_BRACKETS,
                              INLINE_LABELS_RECTS),
            INLINE_LABELS_OFF);
        option.category = ADVANCED_CATEGORY;
        option;
    };
    
    public static val SynthesisOption INLINE_LABELS_EDGE_VISIBLE = {
        val option = SynthesisOption.createCheckOption("Let Edge Shine Through", true);
        option.category = ADVANCED_CATEGORY;
        option;
    };
    
    public static val SynthesisOption INLINE_LABELS_ARROWS = {
        val option = SynthesisOption.createCheckOption("Direction Hints", false);
        option.category = ADVANCED_CATEGORY;
        option;
    };
    
    public static val SynthesisOption INLINE_LABELS_BAD_DESIGN_MODE = {
        val option = SynthesisOption.createCheckOption("Bad Design Mode", false);
        option.category = ADVANCED_CATEGORY;
        option;
    };
    
    
    /**
     * {@inheritDoc} 
     */
    override getDisplayedSynthesisOptions() {
        return new ImmutableList.Builder()
                .addAll(super.displayedSynthesisOptions)
                .add(SynthesisOption.createSeparator("Default Values"))
                .add(DEFAULT_NODE_SIZES)
                .add(DEFAULT_NODE_LABELS)
                .add(DEFAULT_PORT_SIZES)
                .add(DEFAULT_PORT_LABELS)
                .add(DEFAULT_EDGE_DIRECTIONS)
                .add(ADVANCED_CATEGORY)
                .add(INLINE_LABELS_SEPARATOR)
                .add(INLINE_LABELS)
                .add(INLINE_LABELS_EDGE_VISIBLE)
                .add(INLINE_LABELS_ARROWS)
                .add(INLINE_LABELS_BAD_DESIGN_MODE)
                .build();
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // TRANSFORMATION
    
    override transform(ElkNode elkGraph) {
        // Transform everything (don't use 'elkGraph.transform' :))
        val KNode result = exporter.transform(elkGraph)

        // Enable label management
        addLabelManager(result);
        
        // Initialize the rendering code
        initRenderings(result);

        // Enrich the rendering
        enrichRenderings(result);

        // Associate original objects with transformed objects
        //  note that the 'transformX' methods are contributed by the 
        //  KGraphExporter extension and internally hold a mapping
        elkGraph.eAllContents.filter(ElkGraphElement).forEach[ e |
            switch e {
                ElkNode: e.transformNode?.associateWith(e)
                ElkPort: e.transformPort?.associateWith(e)
                ElkEdge: e.transformEdge?.associateWith(e)
                ElkLabel: e.transformLabel?.associateWith(e)
            }
        ] 

        return result
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // RENDERING THINGS
    
    // Extensions and rendering stuff
    extension KGraphExporter exporter = new KGraphExporter
    extension KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
    
    @Inject extension KPolylineExtensions
    @Inject extension KRenderingExtensions
    
    /**
     * Make sure the node has a valid size and a label (if defaults are switched on).
     * 
     * @param node the node whose rendering to enrich.
     */
    protected def override void enrichNodeRendering(KNode node) {
        super.enrichNodeRendering(node)
        if (DEFAULT_NODE_LABELS.booleanValue)
            KGraphUtil.configureWithDefaultLabel(node)
        if (DEFAULT_NODE_SIZES.booleanValue)
            KGraphUtil.configurWithDefaultSize(node)
    }

    /**
     * Make sure the port has a valid size and a label (if defaults are switched on).
     * 
     * @param port the port whose rendering to enrich.
     */
    protected def override void enrichPortRendering(KPort port) {
        super.enrichPortRendering(port)
        if (DEFAULT_PORT_LABELS.booleanValue)
            KGraphUtil.configureWithDefaultValues(port)
        if (DEFAULT_PORT_SIZES.booleanValue)
            KGraphUtil.configurWithDefaultSize(port)
    }

    /**
     * Possibly adds a proper rendering to the given edge. If no rendering is associated with the edge
     * yet, a default rendering with an arrowhead is added to it.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    protected def override void enrichEdgeRendering(KEdge edge) {
        super.enrichEdgeRendering(edge, DEFAULT_EDGE_DIRECTIONS.booleanValue);
        KGraphUtil.configureWithDefaultValues(edge);
    }
    
    /**
     * Adds a slightly more complex rendering to center edge labels, if that is necessary. Also sets layout properties.
     */
    protected def override void enrichLabelRendering(KLabel label) {
        if (isInlineLabel(label)) {
            // LET'S DO THIS!!!
            addInlineLabelRendering(label)
        } else {
            super.enrichLabelRendering(label);
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // INLINE LABEL RENDERING
    
    /**
     * Checks whether we need to create a special rendering for the label. That is the case if it is a center edge
     * label, inline labels are active, and the label doesn't already have a rendering.
     */
    private def boolean isInlineLabel(KLabel label) {
        // Already has rendering
        if (label.hasRendering()) {
            return false;
        }
        
        // Inline labels are active
        if (INLINE_LABELS.objectValue == INLINE_LABELS_OFF) {
            return false;
        }
        
        // Edge label
        if (!(label.parent instanceof KEdge)) {
            return false;
        }
        
        // Center edge label
        val placement = label.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);
        return placement == EdgeLabelPlacement.CENTER || placement == EdgeLabelPlacement.UNDEFINED;
    }
    
    /**
     * Adds inline edge label rendering things to the given label.
     */
    private def void addInlineLabelRendering(KLabel label) {
        label.setProperty(CoreOptions.EDGE_LABELS_INLINE, true);
        
        // The background rectangle is always there
        val backgroundRectangle = addBackgroundRectangle(label);
        addDecoration(backgroundRectangle);
        addArrows(backgroundRectangle);
        addLabelRendering(backgroundRectangle);
        
        label.data += backgroundRectangle;
    }
    
    /**
     * Creates the background container rectangle for inline edge labels.
     */
    private def KContainerRendering addBackgroundRectangle(KLabel label) {
        // Retrieve settings
        val border = INLINE_LABELS.objectValue.equals(INLINE_LABELS_RECTS);
        val translucent = INLINE_LABELS_EDGE_VISIBLE.booleanValue;
        val badDesign = INLINE_LABELS_BAD_DESIGN_MODE.booleanValue;
        
        return createKRectangle() => [ rect |
            // The rectangle's border
            if (border) {
                if (badDesign) {
                    rect.setForegroundColor(0, 0, 255);
                } else {
                    rect.setForegroundColor(100, 100, 100);
                }
            } else {
                rect.foregroundInvisible = true;
            }
            
            // The rectangle's background
            if (badDesign) {
                rect.setBackgroundColor(255, 255, 0,
                    if (translucent) 220 else 255
                );
            } else {
                rect.setBackgroundColor(255, 255, 255,
                    if (translucent) 220 else 255
                );
            }
        ];
    }
    
    /**
     * Adds a text rendering to the given container which will display the label text.
     */
    private def void addLabelRendering(KContainerRendering container) {
        // We need a bit of space between the text and the top / bottom container border with certain rendering styles
        val verticalPadding = INLINE_LABELS.objectValue.equals(INLINE_LABELS_RECTS);
        
        container.children += createKText() => [ text |
            text.fontSize = KlighdConstants::DEFAULT_FONT_SIZE - 2
            text.addSingleClickAction(FocusAndContextAction.ID)
            
            text.setAreaPlacementData(
//                createKPosition(LEFT, 2, 0, TOP, if (verticalPadding) 2 else 0, 0),
//                createKPosition(RIGHT, 2, 0, BOTTOM, if (verticalPadding) 3 else 0, 0)
                createKPosition(LEFT, 2, 0, TOP, 2, 0),
                createKPosition(RIGHT, 2, 0, BOTTOM, 3, 0)
            )
        ]
    }
    
    /**
     * Renders a label such that it is surrounded by lines or brackets to its left and right sides.
     */
    private def void addDecoration(KContainerRendering container) {
        // Retrieve settings
        val lines = INLINE_LABELS.objectValue.equals(INLINE_LABELS_LINES);
        val brackets = INLINE_LABELS.objectValue.equals(INLINE_LABELS_BRACKETS);
        
        if (lines || brackets) {
            // Left line / bracket
            container.children += createKPolyline() => [ line |
                setupVisibility(line, Visibility.SEGMENT_HORIZONTAL)
                line.setForegroundColor(100, 100, 100);
                
                if (brackets) line.addKPosition(RIGHT, 0, 0, TOP, 0, 0);
                line.addKPosition(LEFT, 0, 0, TOP, 0, 0);
                line.addKPosition(LEFT, 0, 0, BOTTOM, 0, 0);
                if (brackets) line.addKPosition(RIGHT, 0, 0, BOTTOM, 0, 0);
                
                line.setAreaPlacementData(
                    createKPosition(LEFT, 0, 0, TOP, 0, 0),
                    createKPosition(LEFT, 3, 0, BOTTOM, 0, 0)
                )
            ]
            
            // Right line / bracket
            container.children += createKPolyline() => [ line |
                setupVisibility(line, Visibility.SEGMENT_HORIZONTAL)
                line.setForegroundColor(100, 100, 100);
                
                if (brackets) line.addKPosition(LEFT, 0, 0, TOP, 0, 0);
                line.addKPosition(RIGHT, 0, 0, TOP, 0, 0);
                line.addKPosition(RIGHT, 0, 0, BOTTOM, 0, 0);
                if (brackets) line.addKPosition(LEFT, 0, 0, BOTTOM, 0, 0);
                
                line.setAreaPlacementData(
                    createKPosition(RIGHT, 3, 0, TOP, 0, 0),
                    createKPosition(RIGHT, 0, 0, BOTTOM, 0, 0)
                )
            ]
            
            // Top line / bracket
            container.children += createKPolyline() => [ line |
                setupVisibility(line, Visibility.SEGMENT_VERTICAL)
                line.setForegroundColor(100, 100, 100);
                
                if (brackets) line.addKPosition(LEFT, 0, 0, BOTTOM, 0, 0);
                line.addKPosition(LEFT, 0, 0, TOP, 0, 0);
                line.addKPosition(RIGHT, 0, 0, TOP, 0, 0);
                if (brackets) line.addKPosition(RIGHT, 0, 0, BOTTOM, 0, 0);
                
                line.setAreaPlacementData(
                    createKPosition(LEFT, 0, 0, TOP, 0, 0),
                    createKPosition(RIGHT, 0, 0, TOP, 3, 0)
                )
            ]
            
            // Bottom line / bracket
            container.children += createKPolyline() => [ line |
                setupVisibility(line, Visibility.SEGMENT_VERTICAL)
                line.setForegroundColor(100, 100, 100);
                
                if (brackets) line.addKPosition(LEFT, 0, 0, TOP, 0, 0);
                line.addKPosition(LEFT, 0, 0, BOTTOM, 0, 0);
                line.addKPosition(RIGHT, 0, 0, BOTTOM, 0, 0);
                if (brackets) line.addKPosition(RIGHT, 0, 0, TOP, 0, 0);
                
                line.setAreaPlacementData(
                    createKPosition(LEFT, 0, 0, BOTTOM, 3, 0),
                    createKPosition(RIGHT, 0, 0, BOTTOM, 0, 0)
                )
            ]
        }
    }
    
    /**
     * Adds directional arrows to the container which can be used to display directional hints.
     */
    private def void addArrows(KContainerRendering container) {
        // Retrieve settings
        val arrows = INLINE_LABELS_ARROWS.booleanValue;
        
        if (arrows) {
            // Left arrow
            container.children += createKPolygon() => [ arrow |
                setupArrowColors(arrow);
                setupVisibility(arrow, Visibility.DIRECTION_LEFT)
                
                arrow.addKPosition(RIGHT, 0, 0, TOP, -4, 0.5f)
                     .addKPosition(RIGHT, 0, 0, TOP, 4, 0.5f)
                     .addKPosition(LEFT, 0, 0, TOP, 0, 0.5f)
                     .addKPosition(RIGHT, 0, 0, TOP, -4, 0.5f);
                
                arrow.setAreaPlacementData(
                    createKPosition(LEFT, -4, 0, TOP, 0, 0),
                    createKPosition(LEFT, 0, 0, BOTTOM, 0, 0)
                )
            ]
            
            // Right arrow
            container.children += createKPolygon() => [ arrow |
                setupArrowColors(arrow);
                setupVisibility(arrow, Visibility.DIRECTION_RIGHT)
                
                arrow.addKPosition(LEFT, 0, 0, TOP, -4, 0.5f)
                     .addKPosition(LEFT, 0, 0, TOP, 4, 0.5f)
                     .addKPosition(RIGHT, 0, 0, TOP, 0, 0.5f)
                     .addKPosition(LEFT, 0, 0, TOP, -4, 0.5f);
                
                arrow.setAreaPlacementData(
                    createKPosition(RIGHT, 0, 0, TOP, 0, 0),
                    createKPosition(RIGHT, -4, 0, BOTTOM, 0, 0)
                )
            ]
            
            // Up arrow
            container.children += createKPolygon() => [ arrow |
                setupArrowColors(arrow);
                setupVisibility(arrow, Visibility.DIRECTION_UP)
                
                arrow.addKPosition(LEFT, -4, 0.5f, BOTTOM, 0, 0)
                     .addKPosition(LEFT, 4, 0.5f, BOTTOM, 0, 0)
                     .addKPosition(RIGHT, 0, 0.5f, TOP, 0, 0)
                     .addKPosition(LEFT, -4, 0.5f, BOTTOM, 0, 0)
                
                arrow.setAreaPlacementData(
                    createKPosition(LEFT, 0, 0, TOP, -4, 0),
                    createKPosition(RIGHT, 0, 0, TOP, 0, 0)
                )
            ]
            
            // Down arrow
            container.children += createKPolygon() => [ arrow |
                setupArrowColors(arrow);
                setupVisibility(arrow, Visibility.DIRECTION_DOWN)
                
                arrow.addKPosition(LEFT, -4, 0.5f, TOP, 0, 0)
                     .addKPosition(LEFT, 4, 0.5f, TOP, 0, 0)
                     .addKPosition(RIGHT, 0, 0.5f, BOTTOM, 0, 0)
                     .addKPosition(LEFT, -4, 0.5f, TOP, 0, 0)
                
                arrow.setAreaPlacementData(
                    createKPosition(LEFT, 0, 0, BOTTOM, 0, 0),
                    createKPosition(RIGHT, 0, 0, BOTTOM, -4, 0)
                )
            ]
        }
    }
    
    /**
     * Setup setting for arrows.
     */
    private def void setupArrowColors(KPolygon arrow) {
        val badDesign = INLINE_LABELS_BAD_DESIGN_MODE.booleanValue;
        
        if (badDesign) {
            arrow.setBackgroundColor(0, 0, 255);
            arrow.setForegroundColor(0, 0, 255);
        } else {
            arrow.setBackgroundColor(100, 100, 100);
            arrow.setForegroundColor(100, 100, 100);
        }
    }
    
    private def setupVisibility(KRendering rendering, Visibility visibility) {
        rendering.invisible = false;
        rendering.invisible.setProperty(InlineEdgeLabelStyleModifier.STYLE_VISIBILITY, visibility);
        rendering.invisible.modifierId = InlineEdgeLabelStyleModifier.ID;
    }
    
}