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
import de.cau.cs.kieler.formats.kgraph.KGraphExporter
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
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
    
    extension KGraphExporter exporter = new KGraphExporter
    
    /*
     * Synthesis options specifying whether several default values should be used or not. Default values are, eg, node
     * size if not specified and port ids as labels if no labels exist.
     */
    public static val SynthesisOption DEFAULT_NODE_SIZES = SynthesisOption::createCheckOption("Node Sizes", false)
    public static val SynthesisOption DEFAULT_NODE_LABELS = SynthesisOption::createCheckOption("Node Labels", false)
    public static val SynthesisOption DEFAULT_PORT_SIZES = SynthesisOption::createCheckOption("Port Sizes", false)
    public static val SynthesisOption DEFAULT_PORT_LABELS = SynthesisOption::createCheckOption("Port Labels", false)
    public static val SynthesisOption DEFAULT_EDGE_DIRECTIONS = SynthesisOption::createCheckOption("Edge Directions", true)
    
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
                .build();
    }
    
    override transform(ElkNode elkGraph) {
        // Transform everything (don't use 'elkGraph.transform' :))
        val KNode result = exporter.transform(elkGraph)

        // Enable label management
        addLabelManager(result)
        
        // Initialize the rendering code
        initRenderings(result);

        // Enrich the rendering
        enrichRenderings(result)

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
    
    
    
}