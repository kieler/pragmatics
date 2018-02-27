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
package de.cau.cs.kieler.graphs.klighd.syntheses

import com.google.common.base.Predicate
import com.google.common.collect.ImmutableList
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.util.KlighdProperties
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import org.eclipse.elk.core.options.EdgeLabelPlacement
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.elk.graph.properties.Property
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
class KGraphDiagramSynthesis extends AbstractStyledDiagramSynthesis<KNode> {

    /**
     * ID this synthesis is registered with KLighD with.
     */
    public static val String TRANSFORMATION_ID =
        "de.cau.cs.kieler.graphs.klighd.syntheses.KGraphDiagramSynthesis"

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
     * {@link de.cau.cs.kieler.kgraph.text.KGraphResource KGraphResource}. 
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

    /**
     * Synthesis option to allow edge adjustment by default
     */
    private static val SynthesisOption SUPPRESSEDGEADJUSTMENT = SynthesisOption::createCheckOption(
        "Suppress Edge Adjustement", true)

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
        return new ImmutableList.Builder()
                .add(DEFAULTS)
                .add(SUPPRESSEDGEADJUSTMENT)
                .addAll(super.displayedSynthesisOptions)
                .build();
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // VARIABLES
    
    /** The value for the defaults property as specified in the model. */
    private boolean defaults = false


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
        usedContext.setProperty(KlighdSynthesisProperties.SUPPRESS_EDGE_ADJUSTMENT, SUPPRESSEDGEADJUSTMENT.booleanValue)

        // 3 lines are more or less copied from EcoreUtil.copy()
        val copier = new Copier()
        val KNode result = copier.copy(graph) as KNode
        copier.copyReferences()

        // Persistent entries of the original graph are already loaded in the KGraphResource
        //  but until now nobody knows about any persisted entries that originate from KLighD.
        // First, this might be the expansion state of nodes. Second, also KRendering elements
        //  may carry persisted entries that have to be parsed before we build the view model.
        KGraphDataUtil.loadDataElements(result, PREDICATE_IS_KGRAPHDATA, KNOWN_PROPS)

        // Evaluate the defaults property
        try {
            defaults = graph.getProperty(DEFAULTS_PROPERTY)
        } catch (ClassCastException cce) {
            // This is a 'special' property not known as layout option hence it is not type-checked,
            //  possibly yielding a class cast exception if neither 'true' nor 'false' are specified
            //  as value.
        }

        // Enable label management
        addLabelManager(result)
        
        // Initialize the rendering code
        initRenderings(result);

        // Associate original objects with transformed objects
        for (entry : copier.entrySet()) {
            entry.value.associateWith(entry.key)
        }

        // Enrich the rendering
        enrichRenderings(result)

        return result
    }


    ///////////////////////////////////////////////////////////////////////////////
    // RENDERINGS ENRICHMENT CENTER
    //   "Thank you for helping us help you help us all."
    
    /**
     * Make sure the node has a valid size and a label (if defaults are switched on).
     * 
     * @param node the node whose rendering to enrich.
     */
    protected def override void enrichNodeRendering(KNode node) {
        // If defaults are switched off, don't bother
        if (!defaultsEnabled()) {
            return;
        } else {
            super.enrichNodeRendering(node);
            KGraphUtil.configureWithDefaultValues(node);
        }

    }

    /**
     * Make sure the port has a valid size and a label (if defaults are switched on).
     * 
     * @param port the port whose rendering to enrich.
     */
    protected def override void enrichPortRendering(KPort port) {
        // If defaults are switched off, don't bother
        if (!defaultsEnabled()) {
            return;
        } else {
            super.enrichPortRendering(port);
            KGraphUtil.configureWithDefaultValues(port);
        }
    }

    /**
     * Possibly adds a proper rendering to the given edge. If no rendering is associated with the edge
     * yet, a default rendering with an arrowhead is added to it.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    protected def override void enrichEdgeRendering(KEdge edge) {
        // We always want a rendering for edges, apparently
        super.enrichEdgeRendering(edge);

        // If defaults are switched off, don't bother
        if (defaultsEnabled()) {
            KGraphUtil.configureWithDefaultValues(edge);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    // UTILITY METHODS

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
