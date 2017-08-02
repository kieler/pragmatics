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
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.actions.FocusAndContextAction
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.labels.inline.DirectionalArrowsDecorator
import de.cau.cs.kieler.klighd.labels.inline.InlineLabelConfigurator
import de.cau.cs.kieler.klighd.labels.inline.LinesDecorator
import de.cau.cs.kieler.klighd.labels.inline.RectangleDecorator
import java.awt.Color
import org.eclipse.elk.graph.ElkEdge
import org.eclipse.elk.graph.ElkGraphElement
import org.eclipse.elk.graph.ElkLabel
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.ElkPort
import de.cau.cs.kieler.klighd.labels.inline.InlineLabelConfigurator.LayoutMode

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
    
    public static val COLOR_MODE_SOLID = "Solid backgrounds";
    public static val COLOR_MODE_TRANSLUCENT = "Translucent backgrounds";
    public static val COLOR_MODE_BAD_DESIGN = "Bad Design Mode";
    public static val SynthesisOption COLOR_MODE = {
        // We're using a block expression here because we need to set the category on the option
        val option = SynthesisOption.createChoiceOption(
            "Color Mode",
            ImmutableList::of(COLOR_MODE_SOLID, COLOR_MODE_TRANSLUCENT, COLOR_MODE_BAD_DESIGN),
            COLOR_MODE_TRANSLUCENT);
        option.category = ADVANCED_CATEGORY;
        option;
    };
    
    public static val SynthesisOption INLINE_LABELS_ARROWS = {
        val option = SynthesisOption.createCheckOption("Direction Hints", false);
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
                .add(COLOR_MODE)
                .add(INLINE_LABELS_ARROWS)
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
        
        // Inline label rendering
        configureInlinelabels(result);

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
            super.enrichLabelRendering(label);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // INLINE LABEL RENDERING
    
    /**
     * Sets up inline labels for the graph.
     */
    private def void configureInlinelabels(KNode graph) {
        // Check if inline labels are active in the first place
        if (INLINE_LABELS.objectValue.equals(INLINE_LABELS_OFF)) {
            return;
        }
        
        // Colors to be used
        var Color background = null;
        var Color foreground = null;
        
        switch COLOR_MODE.objectValue {
            case COLOR_MODE_SOLID: {
                background = new Color(255, 255, 255, 255);
                foreground = new Color(100, 100, 100, 255);
            }
            
            case COLOR_MODE_TRANSLUCENT: {
                background = new Color(255, 255, 255, 220)
                foreground = new Color(100, 100, 100, 255);
            }
            
            case COLOR_MODE_BAD_DESIGN: {
                background = new Color(255, 255, 0, 255)
                foreground = new Color(0, 0, 255, 255);
            }
        }
        
        // Setup the configurator
        val configurator = InlineLabelConfigurator.create()
            .withLayoutMode(LayoutMode.HORIZONTAL)
            .withLabelTextRenderingProvider([ container, label | createTextRendering(container, label) ]);
        
        val backgroundProvider = RectangleDecorator.create().withBackground(background);
        configurator.addDecoratorRenderingProvider(backgroundProvider);
        
        switch INLINE_LABELS.objectValue {
            case INLINE_LABELS_BRACKETS:
                configurator.addDecoratorRenderingProvider(
                    LinesDecorator.create()
                        .withColor(foreground)
                        .withBrackets(true))
            case INLINE_LABELS_LINES:
                configurator.addDecoratorRenderingProvider(
                    LinesDecorator.create()
                        .withColor(foreground)
                        .withBrackets(false))
            case INLINE_LABELS_RECTS:
                backgroundProvider.withBorder(foreground)
        }
        
        if (INLINE_LABELS_ARROWS.booleanValue) {
            configurator.addDecoratorRenderingProvider(
                DirectionalArrowsDecorator.create()
                    .withColor(foreground))
        }
        
        // Magic
        configurator.applyToAll(graph, true);
    }
    
    /**
     * Creates a simple label rendering. We cannot use enrichRendering(KLabel) here because at this point
     * the label already has renderings and that method thus wouldn't do anything.
     */
    private def KRendering createTextRendering(KContainerRendering container, KLabel label) {
        val rendering = createKText() => [ text |
            text.fontSize = KlighdConstants::DEFAULT_FONT_SIZE - 2
            text.addSingleClickAction(FocusAndContextAction.ID)
        ];
        
        container.children += rendering;
        return rendering;
    }
    
}