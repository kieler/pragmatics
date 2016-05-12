/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext.transformations

import com.google.common.base.Predicate
import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.actions.FocusAndContextAction
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLibraryExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.labels.AbstractKlighdLabelManager
import de.cau.cs.kieler.klighd.labels.ConditionLabelManager
import de.cau.cs.kieler.klighd.labels.IdentLabelManager
import de.cau.cs.kieler.klighd.labels.LabelPredicates
import de.cau.cs.kieler.klighd.labels.SoftWrappingLabelManager
import de.cau.cs.kieler.klighd.labels.TruncatingLabelManager
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.util.KlighdProperties
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import org.eclipse.elk.core.klayoutdata.KLayoutData
import org.eclipse.elk.core.labels.LabelManagementOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.EdgeLabelPlacement
import org.eclipse.elk.core.options.EdgeRouting
import org.eclipse.elk.core.util.ElkUtil
import org.eclipse.elk.core.util.GraphDataUtil
import org.eclipse.elk.graph.EMapPropertyHolder
import org.eclipse.elk.graph.KEdge
import org.eclipse.elk.graph.KGraphData
import org.eclipse.elk.graph.KGraphElement
import org.eclipse.elk.graph.KLabel
import org.eclipse.elk.graph.KNode
import org.eclipse.elk.graph.KPort
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.elk.graph.properties.Property
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import de.cau.cs.kieler.klighd.krendering.extensions.PositionReferenceX
import de.cau.cs.kieler.klighd.krendering.extensions.PositionReferenceY
import de.cau.cs.kieler.klighd.krendering.HorizontalAlignment
import de.cau.cs.kieler.klighd.krendering.VerticalAlignment

/**
 * Synthesizes a copy of the given {@code KNode} and adds default stuff.
 * 
 * <p>Default stuff, in this case, is the following:</p>
 * <ul>
 *   <li>If a node has no label, but an ID, a label with the ID as its text is added.</li>
 *   <li>If a node label is present, node label placement defaults to inner centered placement.</li>
 *   <li>Edges without attached {@code KRendering}s are provided with a {@code KPolyline} with
 *       an arrowhead at the end. (the default would be a simple polyline without arrowhead)</li>
 *   <li>Labels without attached {@code KRendering}s are provided with a default {@code KText}
 *       rendering that ensures that label sizes will be calculated correctly.</li>
 *   <li>Edge labels without edge label placement get a shiny new {@link EdgeLabelPlacement#CENTER}
 *       placement.</li>
 * </ul>
 * 
 * @author cds
 * @author uru
 */
class KGraphDiagramSynthesis extends AbstractDiagramSynthesis<KNode> {

    /**
     * ID this synthesis is registered with KLighD with.
     */
    public static val String TRANSFORMATION_ID =
        "de.cau.cs.kieler.klighd.xtext.transformations.KGraphDiagramSynthesis"

    // The next two definitions are used to load possibly persisted klighd information
    //  that is ignored by kiml, e.g. the expansion state of nodes
    private static val PREDICATE_IS_KGRAPHDATA = new Predicate<EMapPropertyHolder>() {
        override apply(EMapPropertyHolder input) {
            return input instanceof KGraphData;
        }
    }
    private static val KNOWN_PROPS = ImmutableList.of(KlighdProperties.EXPAND,
        KlighdProperties.EXPANDED_RENDERING, KlighdProperties.COLLAPSED_RENDERING,
        KlighdProperties.TOOLTIP, KlighdProperties.NOT_SELECTABLE);


    ///////////////////////////////////////////////////////////////////////////////
    // SYNTHESIS OPTIONS
    
    /**
     * Whether the model wants default defaults. This property is no layout option, not registered with
     * KIML, and has to be loaded explicitly by the 
     * {@link de.cau.cs.kieler.core.kgraph.text.KGraphResource KGraphResource}. 
     */
    private static final IProperty<Boolean> DEFAULTS_PROPERTY = new Property<Boolean>(
        "de.cau.cs.kieler.kgraphsynthesis.defaults", false)
    
    private static val DEFAULTS_AS_IN_MODEL = "As in Model"
    private static val DEFAULTS_ON = "On"
    private static val DEFAULTS_OFF = "Off"
    /**
     * Synthesis option specifying whether default values should be used. Default values are, eg, node
     * size if not specified and port ids as labels if no labels exist.
     */
    private static val SynthesisOption DEFAULTS = SynthesisOption::createChoiceOption("Default Values",
        ImmutableList::of(DEFAULTS_AS_IN_MODEL, DEFAULTS_ON, DEFAULTS_OFF), DEFAULTS_AS_IN_MODEL)
    
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
    override getDisplayedLayoutOptions() {
        return ImmutableList::of( 
            // example to specify external layout option (in this case one of klay layered)
            // remember to add the following import in the head of this class
            //   import static de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
            // specifyLayoutOption("de.cau.cs.kieler.klay.layered.edgeSpacingFactor", ImmutableList.of(0f,1f))
            // These values are annoying :)
            //specifyLayoutOption(LayoutOptions::PORT_CONSTRAINTS,
            //  ImmutableList::copyOf(PortConstraints::values)),
            //specifyLayoutOption(LayoutOptions::SPACING, ImmutableList::of(0, 255))
        )
    }

    /**
     * {@inheritDoc} 
     */
    override getDisplayedSynthesisOptions() {
        return ImmutableList::of(
            DEFAULTS,
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

    /** The value for the defaults property as specified in the model. */
    private boolean defaults = false

    /** Default rendering for nodes. */
    private var KRendering defaultNodeRendering;

    /** Default rendering for polyline edges. */
    private var KRendering defaultPolylineRendering;

    /** Default rendering for spline edges. */
    private var KRendering defaultSplineRendering;


    ///////////////////////////////////////////////////////////////////////////////
    // TRANSFORMATION
    
    /**
     * Transforms the given graph into an equivalent graph that may be enriched with additional
     * rendering information.
     * 
     * @param graph the graph to transform.
     * @return the possibly enriched graph.
     */
    override KNode transform(KNode graph) {
        usedContext.setProperty(KlighdSynthesisProperties.SUPPRESS_EDGE_ADJUSTMENT, true)

        // 3 lines are more or less copied from EcoreUtil.copy()
        val copier = new Copier()
        val KNode result = copier.copy(graph) as KNode
        copier.copyReferences()

        // Persistent entries of the original graph are already loaded in the KGraphResource
        //  but until now nobody knows about any persisted entries that originate from KLighD.
        // First, this might be the expansion state of nodes. Second, also KRendering elements
        //  may carry persisted entries that have to be parsed before we build the view model.
        GraphDataUtil.loadDataElements(result, PREDICATE_IS_KGRAPHDATA, KNOWN_PROPS)

        // Evaluate the defaults property
        try {
            defaults = graph.getData(typeof(KLayoutData)).getProperty(DEFAULTS_PROPERTY)
        } catch (ClassCastException cce) {
            // This is a 'special' property not known as layout option hence it is not type-checked,
            //  possibly yielding a class cast exception if neither 'true' nor 'false' are specified
            //  as value.
        }

        // Enable label management
        addLabelManager(result)
               
        // Create a rendering library for reuse of renderings
        var library = result.getData(typeof(KRenderingLibrary))
        if (library == null) {
            library = renderingFactory.createKRenderingLibrary
            result.data += library
        }

        switch STYLE.objectValue {
            case STYLE_STYLISH: library.initStylishFactory
            case STYLE_HELLO_KITTY: library.initHelloKittyFactory
            case STYLE_SPONGEBOB: library.initSpongebobFactory
            default: library.initBoringFactory // boring 
        }

        // Associate original objects with transformed objects
        for (entry : copier.entrySet()) {
            entry.value.associateWith(entry.key)
        }

        // Enrich the rendering
        recursivelyEnrichRendering(result)

        return result
    }
    
    /**
     * Configures the appropriate label manager for the given KGraph.
     * 
     * @param kgraph the graph to configure the label manager for.
     */
    def addLabelManager(KNode kgraph) {
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
        
        if (labelManager != null) {
            kgraph.setLayoutOption(LabelManagementOptions.LABEL_MANAGER, labelManager)
        }
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // RENDERING INITIALIZATION
    
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
    def private void recursivelyEnrichRendering(EObject o) {
        enrichRendering(o)
        o.eContents.forEach[child|recursivelyEnrichRendering(child)]
    }

    /**
     * Fallback metchod that does not add any rendering information to the given object.
     * 
     * @param o an EMF object.
     */
    def private dispatch void enrichRendering(EObject o) {
    }

    /**
     * Make sure the node has a valid size and a label (if defaults are switched on).
     * 
     * @param node the node whose rendering to enrich.
     */
    def private dispatch void enrichRendering(KNode node) {
        // If defaults are switched off, don't bother
        if (!defaultsEnabled()) {
            return;
        }

        ElkUtil.configureWithDefaultValues(node)

        // add a rendering to the node
        val rendering = node.addRenderingRef(defaultNodeRendering)
        rendering.addSingleClickAction(FocusAndContextAction.ID)
    }

    /**
     * Make sure the port has a valid size and a label (if defaults are switched on).
     * 
     * @param port the port whose rendering to enrich.
     */
    def private dispatch void enrichRendering(KPort port) {
        // If defaults are switched off, don't bother
        if (!defaultsEnabled()) {
            return;
        }

        ElkUtil.configureWithDefaultValues(port)
    }

    /**
     * Possibly adds a proper rendering to the given edge. If no rendering is associated with the edge
     * yet, a default rendering with an arrowhead is added to it.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    def private dispatch void enrichRendering(KEdge edge) {
        if (!edge.hasRendering) {
            val parent = if (ElkUtil.isDescendant(edge.target, edge.source))
                    edge.source
                else
                    edge.source?.parent
            val routing = parent?.getData(typeof(KLayoutData))?.getProperty(CoreOptions::EDGE_ROUTING)
            edge.data += renderingFactory.createKRenderingRef => [
                if (routing != null && routing == EdgeRouting::SPLINES) {
                    it.rendering = defaultSplineRendering
                } else {
                    it.rendering = defaultPolylineRendering
                }
                
                it.addSingleClickAction(FocusAndContextAction.ID)
            ]
        }

        // If defaults are switched off, don't bother
        if (!defaultsEnabled()) {
            return;
        }

        ElkUtil.configureWithDefaultValues(edge)
    }

    /**
     * Possibly adds a proper rendering to the given label. If no rendering is associated with the label
     * yet, a default text rendering with a predefined font size is added to it. Also, makes sure that
     * an edge label placement is set on the label if it belongs to an edge.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    def private dispatch void enrichRendering(KLabel label) {
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
    // UTILITY METHODS
    
    /**
     * Checks if the given graph element already has a rendering attached.
     * 
     * @param e the element to check.
     * @return {@code true} if the element already has a rendering attached, {@code false} otherwise.
     */
    def private boolean hasRendering(KGraphElement e) {
        e.data.exists[it instanceof KRendering]
    }

    /**
     * Returns whether or not default stuff should be added or not.
     * 
     * @return {@code true} if default stuff should be added, {@code false} otherwise.
     */
    def private boolean defaultsEnabled() {
        return DEFAULTS.objectValue == DEFAULTS_ON
            || (DEFAULTS.objectValue == DEFAULTS_AS_IN_MODEL && defaults);
    }
}
