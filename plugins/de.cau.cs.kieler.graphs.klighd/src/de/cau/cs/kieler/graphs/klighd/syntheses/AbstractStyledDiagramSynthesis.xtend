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
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.actions.FocusAndContextAction
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.HorizontalAlignment
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.VerticalAlignment
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLibraryExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.PositionReferenceX
import de.cau.cs.kieler.klighd.krendering.extensions.PositionReferenceY
import de.cau.cs.kieler.klighd.labels.AbstractKlighdLabelManager
import de.cau.cs.kieler.klighd.labels.ConditionLabelManager
import de.cau.cs.kieler.klighd.labels.IdentLabelManager
import de.cau.cs.kieler.klighd.labels.LabelPredicates
import de.cau.cs.kieler.klighd.labels.SoftWrappingLabelManager
import de.cau.cs.kieler.klighd.labels.TruncatingLabelManager
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import org.eclipse.elk.core.labels.LabelManagementOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.EdgeRouting
import org.eclipse.emf.ecore.EObject

/**
 * A base class for transformations that want to apply nice renderings to the diagrams they produce. The
 * currently supported renderings are boring, stylish, Hello Kitty, and Spongebob (or cheese, we're
 * actually not sure).
 * 
 * <p>When using this class, you will probably want to override {@link #getDisplayedSynthesisOptions()}.
 * Be sure to call the superclass method and include its return value in your returned list of
 * synthesis options. Next, when your transformation starts, call {@link #initRenderings(KNode)}. This
 * will initialize the rendering libraries according to the user's selection. Once your view model is
 * finished, call {@link #enrichRenderings(EObject)}. Feel free to override the protected
 * {@code enrichRendering(...)} methods to add things or to prevent things from being added.
 */
abstract class AbstractStyledDiagramSynthesis<T> extends AbstractDiagramSynthesis<T> {

    ///////////////////////////////////////////////////////////////////////////////
    // SYNTHESIS OPTIONS
    
    private static val STYLE_BORING = "Boring";
    private static val STYLE_STYLISH = "Stylish";
    private static val STYLE_HELLO_KITTY = "Hello Kitty";
    private static val STYLE_SPONGEBOB = "Spongebob";
    /** The style to be used when drawing the KGraphs. */
    private static val SynthesisOption STYLE = SynthesisOption::createChoiceOption(
        "Style",
        ImmutableList::of(STYLE_BORING, STYLE_STYLISH, STYLE_HELLO_KITTY, STYLE_SPONGEBOB),
        STYLE_STYLISH);
    
    private static val LABELS_NO_LABELS = "No Labels";
    private static val LABELS_TRUNCATE = "Truncate";
    private static val LABELS_SOFT_WORD_WRAP = "Soft Word Wrapping";
    private static val LABELS_FULL = "Full Labels";
    private static val SynthesisOption LABEL_SHORTENING_STRATEGY = SynthesisOption.createChoiceOption(
        "Label Shortening Strategy",
        ImmutableList::of(LABELS_NO_LABELS, LABELS_TRUNCATE, LABELS_SOFT_WORD_WRAP, LABELS_FULL),
        LABELS_FULL);

    /**
     * {@inheritDoc} 
     */
    override getDisplayedSynthesisOptions() {
        return ImmutableList::of(
            STYLE,
            LABEL_SHORTENING_STRATEGY
        )
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // VARIABLES
    
    @Inject extension KPolylineExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KColorExtensions
    @Inject extension KLibraryExtensions

    /** Rendering factory used to create KRendering model instances. */
    static KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE

    /** Default rendering for nodes. */
    private var KRendering defaultNodeRendering;

    /** Default rendering for polyline edges. */
    private var KRendering defaultPolylineRendering;

    /** Default rendering for spline edges. */
    private var KRendering defaultSplineRendering;
    

    ///////////////////////////////////////////////////////////////////////////////
    // RENDERING INITIALIZATION
    
    /**
     * Call this method to initialize all renderings before actually applying renderings
     * to your graph elements.
     */
    protected def initRenderings(KNode graph) {
        // Create a rendering library for reuse of renderings
        var library = graph.getData(typeof(KRenderingLibrary))
        if (library === null) {
            library = renderingFactory.createKRenderingLibrary
            graph.data += library
        }

        switch STYLE.objectValue {
            case STYLE_STYLISH: library.initStylishFactory
            case STYLE_HELLO_KITTY: library.initHelloKittyFactory
            case STYLE_SPONGEBOB: library.initSpongebobFactory
            default: library.initBoringFactory // boring 
        }
    }
    
    private def initEdgeRenderings(KRenderingLibrary library) {
        // Create a common rendering for polylines
        defaultPolylineRendering = renderingFactory.createKPolyline() => [
            it.id = "DefaultEdgeRendering"
            it.addHeadArrowDecorator
            it.addJunctionPointDecorator
        ];
        library.renderings += defaultPolylineRendering

        // Create a common rendering for splines
        defaultSplineRendering = renderingFactory.createKSpline => [
            it.id = "SplineEdgeRendering"
            it.addHeadArrowDecorator
        ];
        library.renderings += defaultSplineRendering
    }

    private def initBoringFactory(KRenderingLibrary library) {
        library.initEdgeRenderings
        defaultNodeRendering = renderingFactory.createKRectangle => [
            it.id = "DefaultNodeRendering"
        ]
        library.renderings += defaultNodeRendering
    }

    private def initStylishFactory(KRenderingLibrary library) {
        library.initEdgeRenderings
        defaultNodeRendering = renderingFactory.createKRoundedRectangle => [
            it.id = "DefaultNodeRendering"
            it.cornerWidth = 5
            it.cornerHeight = 5
            it.setBackgroundGradient("#EBF6FA".color, "#C4E3F3".color, 90)
        ]
        library.renderings += defaultNodeRendering
    }

    private def initHelloKittyFactory(KRenderingLibrary library) {
        library.initEdgeRenderings
        defaultNodeRendering = renderingFactory.createKEllipse => [
            it.id = "DefaultNodeRendering"
            it.setBackgroundGradient("#FFEEEE".color, "#FFBBBB".color, 90)
        ]
        library.renderings += defaultNodeRendering
    }

    private def initSpongebobFactory(KRenderingLibrary library) {
        library.initEdgeRenderings
        defaultNodeRendering = renderingFactory.createKRoundedRectangle => [
            it.id = "DefaultNodeRendering"
            it.cornerWidth = 5
            it.cornerHeight = 5
            it.setBackgroundColor(237,249,107)
            it.children += renderingFactory.createKEllipse => [
                            it.setPointPlacementData(PositionReferenceX.LEFT, 0, 0.1f, PositionReferenceY.TOP, 0, 0.1f,
                                HorizontalAlignment.LEFT, VerticalAlignment.TOP, 0, 0, 20, 20)
                            it.setBackgroundColor(255,255,255)
            ]
            it.children += renderingFactory.createKEllipse => [
                            it.setPointPlacementData(PositionReferenceX.RIGHT, 0, 0.2f, PositionReferenceY.TOP, 0, 0.15f,
                                HorizontalAlignment.LEFT, VerticalAlignment.TOP, 0, 0, 10, 10)
                            it.setBackgroundColor(255,255,255)
            ]
            it.children += renderingFactory.createKEllipse => [
                            it.setPointPlacementData(PositionReferenceX.LEFT, 0, 0.05f, PositionReferenceY.TOP, 0, 0.5f,
                                HorizontalAlignment.LEFT, VerticalAlignment.TOP, 0, 0, 10, 10)
                            it.setBackgroundColor(255,255,255)
            ]
            it.children += renderingFactory.createKEllipse => [
                            it.setPointPlacementData(PositionReferenceX.RIGHT, 0, 0.4f, PositionReferenceY.TOP, 0, 0.5f,
                                HorizontalAlignment.LEFT, VerticalAlignment.TOP, 0, 0, 10, 10)
                            it.setBackgroundColor(255,255,255)
            ]
            it.children += renderingFactory.createKEllipse => [
                            it.setPointPlacementData(PositionReferenceX.LEFT, 0, 0.15f, PositionReferenceY.BOTTOM, 0, 0.3f,
                                HorizontalAlignment.LEFT, VerticalAlignment.TOP, 0, 0, 23, 23)
                            it.setBackgroundColor(255,255,255)
            ]
            it.children += renderingFactory.createKEllipse => [
                            it.setPointPlacementData(PositionReferenceX.RIGHT, 0, 0.3f, PositionReferenceY.BOTTOM, 0, 0.4f,
                                HorizontalAlignment.LEFT, VerticalAlignment.TOP, 0, 0, 20, 20)
                            it.setBackgroundColor(255,255,255)
            ]
        ]
        library.renderings += defaultNodeRendering
    }


    ///////////////////////////////////////////////////////////////////////////////
    // RENDERINGS ENRICHMENT CENTER
    //   "Thank you for helping us help you help us all."
    
    /**
     * Recursively calls {@link #enrichRendering(EObject)} on this object and all of its children.
     * 
     * @param o object to enrich.
     */
    protected final def void enrichRenderings(EObject o) {
        // We cannot use a dispatch method here as we want subclasses to be able to override it. Overriding with
        // a dispatch method can lead to stack overflow
        if (o instanceof KNode) {
            enrichNodeRendering(o as KNode);
        } else if (o instanceof KPort) {
            enrichPortRendering(o as KPort);
        } else if (o instanceof KLabel) {
            enrichLabelRendering(o as KLabel);
        } else if (o instanceof KEdge) {
            enrichEdgeRendering(o as KEdge);
        }
        
        o.eContents.forEach[child | enrichRenderings(child)]
    }

    /**
     * Make sure the node has a valid size and a label (if defaults are switched on).
     * 
     * @param node the node whose rendering to enrich.
     */
    protected def void enrichNodeRendering(KNode node) {
        // add a rendering to the node
        val rendering = node.addRenderingRef(defaultNodeRendering)
        rendering.addSingleClickAction(FocusAndContextAction.ID)
    }

    /**
     * Make sure the port has a valid size and a label (if defaults are switched on).
     * 
     * @param port the port whose rendering to enrich.
     */
    protected def void enrichPortRendering(KPort port) {
    }

    /**
     * Possibly adds a proper rendering to the given edge. If no rendering is associated with the edge
     * yet, a default rendering with an arrowhead is added to it.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    protected def void enrichEdgeRendering(KEdge edge) {
        if (!edge.hasRendering) {
            val parent = if (KGraphUtil.isDescendant(edge.target, edge.source))
                    edge.source
                else
                    edge.source?.parent
            val routing = parent?.getProperty(CoreOptions::EDGE_ROUTING)
            edge.data += renderingFactory.createKRenderingRef => [
                if (routing !== null && routing == EdgeRouting::SPLINES) {
                    it.rendering = defaultSplineRendering
                } else {
                    it.rendering = defaultPolylineRendering
                }
                
                it.addSingleClickAction(FocusAndContextAction.ID)
            ]
        }
    }

    /**
     * Possibly adds a proper rendering to the given label. If no rendering is associated with the label
     * yet, a default text rendering with a predefined font size is added to it. Also, makes sure that
     * an edge label placement is set on the label if it belongs to an edge.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    protected def void enrichLabelRendering(KLabel label) {
        // We do this in any case, whether defaults are turned on or not
        if (!label.hasRendering) {
            renderingFactory.createKText() => [ text |
                label.data += text
                text.fontSize = KlighdConstants::DEFAULT_FONT_SIZE - 2
                text.addSingleClickAction(FocusAndContextAction.ID)
                
                // Port labels should have a smaller font size
                if (label.eContainer instanceof KPort) {
                    text.fontSize = KlighdConstants::DEFAULT_FONT_SIZE - 3
                }
            ]
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // LABEL MANAGEMENT
    
    /**
     * Configures the appropriate label manager for the given KGraph.
     * 
     * @param kgraph the graph to configure the label manager for.
     */
    protected def addLabelManager(KNode kgraph) {
        var labelManager = null as AbstractKlighdLabelManager;

        // Evaluate the label shortening property
        switch LABEL_SHORTENING_STRATEGY.objectValue {
            case LABELS_NO_LABELS: {
                labelManager = new ConditionLabelManager(
                    null,
                    LabelPredicates.matchNone,
                    true);
            }
            case LABELS_TRUNCATE: {
                labelManager = new ConditionLabelManager(
                    new TruncatingLabelManager(),
                    LabelPredicates.centerEdgeLabel,
                    false);
            }
            case LABELS_SOFT_WORD_WRAP: {
                labelManager = new ConditionLabelManager(
                    new SoftWrappingLabelManager(),
                    LabelPredicates.centerEdgeLabel,
                    false);
            }
            case LABELS_FULL: {
                labelManager = new IdentLabelManager()
            }
        }
        
        if (labelManager !== null) {
            kgraph.setLayoutOption(LabelManagementOptions.LABEL_MANAGER, labelManager)
        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    // UTILITY METHODS
    
    /**
     * Checks if the given graph element already has a rendering attached.
     * 
     * @param e the element to check.
     * @return {@code true} if the element already has a rendering attached, {@code false} otherwise.
     */
    protected def boolean hasRendering(KGraphElement e) {
        e.data.exists[it instanceof KRendering]
    }
}