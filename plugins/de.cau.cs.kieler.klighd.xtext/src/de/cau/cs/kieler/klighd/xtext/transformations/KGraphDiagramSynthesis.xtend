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
package de.cau.cs.kieler.klighd.xtext.transformations

import com.google.common.base.Predicate
import com.google.common.base.Strings
import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.EMapPropertyHolder
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KGraphData
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KRenderingLibrary
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KLibraryExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.core.properties.Property
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement
import de.cau.cs.kieler.kiml.options.EdgeRouting
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.NodeLabelPlacement
import de.cau.cs.kieler.kiml.options.SizeConstraint
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.util.KlighdProperties
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil.Copier

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
    public static String TRANSFORMATION_ID =
         "de.cau.cs.kieler.klighd.xtext.transformations.KGraphDiagramSynthesis"
    
    /**
     * Whether the model wants default defaults. This property is no layout option, not registered with
     * KIML, and has to be loaded explicitly by the 
     * {@link de.cau.cs.kieler.core.kgraph.text.KGraphResource KGraphResource}. 
     */
    private static final IProperty<Boolean> DEFAULTS_PROPERTY = 
        new Property<Boolean>("de.cau.cs.kieler.kgraphsynthesis.defaults", false)
    
    private static val DEFAULTS_AS_IN_MODEL = "As in Model"
    private static val DEFAULTS_ON = "On"
    private static val DEFAULTS_OFF = "Off"
    
    /**
     * Synthesis option specifying whether default values should be used. Default values are, eg, node
     * size if not specified and port ids as labels if no labels exist.
     */
    private static val SynthesisOption DEFAULTS = SynthesisOption::createChoiceOption(
        "Default Values", 
        ImmutableList::of(DEFAULTS_AS_IN_MODEL, DEFAULTS_ON, DEFAULTS_OFF), DEFAULTS_AS_IN_MODEL)
        
   private static val SynthesisOption STYLE = SynthesisOption::createChoiceOption(
        "Style", 
        ImmutableList::of("Boring", "Stylish", "Hello Kitty"), "Boring")
    
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
    
    
    // The next two definitions are used to load possibly persisted klighd information
    //  that is ignored by kiml, e.g. the expansion state of nodes
    private static val PREDICATE_IS_KGRAPHDATA  =  new Predicate<EMapPropertyHolder>() {
        override apply(EMapPropertyHolder input) {
            return input instanceof KGraphData;
        }
    }
    private static val KNOWN_PROPS = ImmutableList.of(KlighdProperties.EXPAND, KlighdProperties.EXPANDED_RENDERING, 
        KlighdProperties.COLLAPSED_RENDERING, KlighdProperties.TOOLTIP)
    
    
    /**
     * {@inheritDoc} 
     */
    override getDisplayedLayoutOptions() {
        return ImmutableList::of(
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
            DEFAULTS, STYLE
        )
    }

    /**
     * Transforms the given graph into an equivalent graph that may be enriched with additional
     * rendering information.
     * 
     * @param graph the graph to transform.
     * @return the possibly enriched graph.
     */
    override KNode transform(KNode graph) {
        // 3 lines are more or less copied from EcoreUtil.copy()
        val copier = new Copier()
        val KNode result = copier.copy(graph) as KNode
        copier.copyReferences()

        // Persistent entries of the original graph are already loaded in the KGraphResource
        //  but until now nobody knows about any persisted entries that originate from KLighD.
        // First, this might be the expansion state of nodes. Second, also KRendering elements
        //  may carry persisted entries that have to be parsed before we build the view model.
        KimlUtil.loadDataElements(result, PREDICATE_IS_KGRAPHDATA, KNOWN_PROPS)
         
        // Evaluate the defaults property
        try {
            defaults = graph.getData(typeof(KLayoutData)).getProperty(DEFAULTS_PROPERTY)
        } catch (ClassCastException cce) {
            // This is a 'special' property not known as layout option hence it is not type-checked,
            //  possibly yielding a class cast exception if neither 'true' nor 'false' are specified
            //  as value.
        }
        
        // Create a rendering library for reuse of renderings
        var library = result.getData(typeof(KRenderingLibrary))
        if (library == null) {
            library = renderingFactory.createKRenderingLibrary
            result.data += library
        }
        
        switch STYLE.objectValue {
            case "Stylish": library.initStylishFactory
            case "Hello Kitty": library.initHelloKittyFactory
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
        o.eContents.forEach[child | recursivelyEnrichRendering(child)]
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
        
        // Try to add a label
        ensureLabel(node)
        
        // Make sure the node has a size if the size constraints are fixed
        val layoutData = node.getData(typeof(KShapeLayout))
        if (layoutData != null) {
            val sizeConstraint = layoutData.getProperty(LayoutOptions::SIZE_CONSTRAINT)
            
            if (layoutData.width == 0.0f && layoutData.height == 0.0f
                && sizeConstraint.equals(SizeConstraint.fixed)) {
                
                layoutData.width = 80
                layoutData.height = 80
            }
        }
        
        // add a rendering to the node
        node.addRenderingRef(defaultNodeRendering) 
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
        
        // Try to add a label
        ensureLabel(port)
        
        // Make sure the port has a size
        val layoutData = port.getData(typeof(KShapeLayout))
        if (layoutData != null) {
            if (layoutData.width == 0.0f && layoutData.height == 0.0f) {
                layoutData.width = 5
                layoutData.height = 5
            }
        }
    }
    
    /**
     * Possibly adds a proper rendering to the given edge. If no rendering is associated with the edge
     * yet, a default rendering with an arrowhead is added to it.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    def private dispatch void enrichRendering(KEdge edge) {
        if (!edge.hasRendering) {
            val parent = 
                if (KimlUtil.isDescendant(edge.target, edge.source))
                    edge.source
                else
                    edge.source?.parent
            val routing = parent?.getData(typeof(KLayoutData))?.getProperty(LayoutOptions::EDGE_ROUTING)
            edge.data += renderingFactory.createKRenderingRef => [
                if (routing != null && routing == EdgeRouting::SPLINES) {
                    it.rendering = defaultSplineRendering
                } else {
                    it.rendering = defaultPolylineRendering
                }
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
    def private dispatch void enrichRendering(KLabel label) {
        // We do this in any case, whether defaults are turned on or not
        if (!label.hasRendering) {
            renderingFactory.createKText() => [text |
                label.data += text
                text.fontSize = KlighdConstants::DEFAULT_FONT_SIZE - 2
                
                // Port labels should have a smaller font size
                if (label.eContainer instanceof KPort) {
                    text.fontSize = KlighdConstants::DEFAULT_FONT_SIZE - 3
                }
            ]
        }
        
        // The rest adds layout options and is dependent on whether defaults are switched on or not
        if (!defaultsEnabled()) {
            return;
        }
        
        if (label.eContainer instanceof KEdge) {
            // Make sure that edge labels have an edge label placement
            val layoutData = label.getData(typeof(KLayoutData))
            if (layoutData != null) {
                val edgeLabelPlacement = layoutData.getProperty(LayoutOptions::EDGE_LABEL_PLACEMENT)
                if (edgeLabelPlacement == EdgeLabelPlacement::UNDEFINED) {
                    layoutData.setProperty(
                        LayoutOptions::EDGE_LABEL_PLACEMENT, EdgeLabelPlacement::CENTER)
                }
            }
        } else if (label.eContainer instanceof KNode) {
            // Make sure that nodes have a proper node label placement
            val node = label.eContainer as KNode
            val layoutData = node.getData(typeof(KLayoutData))
            if (layoutData != null) {
                val nodeLabelPlacement = layoutData.getProperty(LayoutOptions::NODE_LABEL_PLACEMENT)
                if (nodeLabelPlacement.equals(NodeLabelPlacement.fixed())) {
                    layoutData.setProperty(
                        LayoutOptions::NODE_LABEL_PLACEMENT, NodeLabelPlacement.insideCenter())
                }
            }
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
     * Ensures that the given element has a label, if possible. If it doesn't, a label is added with its
     * identifier. If there is no identifier, no label is added.
     * 
     * @param e the element to add the label to.
     */
    def private void ensureLabel(KLabeledGraphElement e) {
        // If defaults are switched off, don't bother
        if (!defaultsEnabled()) {
            return;
        }
        
        // We're only interested in elements that don't have a label yet
        if (e.labels.empty) {
            // Find the element's ID
            val identifier = e.getData(typeof(KIdentifier))
            if (identifier != null && !Strings.isNullOrEmpty(identifier.id)) {
                // Add a node label
                val label = KimlUtil.createInitializedLabel(e)
                label.text = identifier.id
            }
        }
    }
    
    /**
     * Returns whether or not default stuff should be added or not.
     * 
     * @return {@code true} if default stuff should be added, {@code false} otherwise.
     */
    def private boolean defaultsEnabled() {
        return DEFAULTS.objectValue == DEFAULTS_ON
            || (DEFAULTS.objectValue == DEFAULTS_AS_IN_MODEL && defaults)
    }
}
