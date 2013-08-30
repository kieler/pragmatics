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

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.PortConstraints
import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
import java.util.Collection
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil$Copier

/**
 * Synthesizes a copy of the given {@code KNode} and enriches it with a selection of default renderings
 * where no explicit renderings are specified. To be more precise, the following renderings are added
 * if necessary:
 * <ul>
 *   <li>Edges without attached {@code KRendering}s are provided with a {@code KPolyline} with
 *       an arrowhead at the end. (the default would be a simple polyline without arrowhead)</li>
 *   <li>Labels without attached {@code KRendering}s are provided with a default {@code KText}
 *       rendering that ensures that label sizes will be calculated correctly.</li>
 * </ul>
 * 
 * @author cds
 */
class KGraphDiagramSynthesis extends AbstractDiagramSynthesis<KNode> {
    /**
     * ID this synthesis is registered with KLighD with.
     */
    public static String TRANSFORMATION_ID =
         "de.cau.cs.kieler.klighd.xtext.transformations.KGraphDiagramSynthesis"
    
    /**
     * Rendering factory used to create KRendering model instances.
     */
    static KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
    
    @Inject
    extension KEdgeExtensions
    
    @Inject
    extension KPolylineExtensions
    
    override getRecommendedLayoutOptions() {
        return ImmutableMap::<IProperty<?>, Collection<?>>of(
            LayoutOptions::PORT_CONSTRAINTS, ImmutableList::copyOf(PortConstraints::values),
            LayoutOptions::SPACING, ImmutableList::of(0, 255)
        );
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
        
        // Associate original objects with transformed objects and provide default renderings
        for (entry : copier.entrySet()) {
            entry.value.putToLookUpWith(entry.key)
            entry.value.enrichRendering
        }
        
        return result
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // ENRICHING RENDERINGS
    
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
     * Fallback metchod that does not add any rendering information to the given object.
     * 
     * @param o an EMF object.
     */
    def private dispatch void enrichRendering(EObject o) {
    }
    
    /**
     * Possibly adds a proper rendering to the given edge. If no rendering is associated with the edge
     * yet, a default rendering with an arrowhead is added to it.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    def private dispatch void enrichRendering(KEdge edge) {
        if (!edge.hasRendering) {
            edge.addPolyline.addArrowDecorator
        }
    }
    
    /**
     * Possibly adds a proper rendering to the given label. If no rendering is associated with the label
     * yet, a default text rendering with a predefined font size is added to it.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    def private dispatch void enrichRendering(KLabel label) {
        if (!label.hasRendering) {
            renderingFactory.createKText() => [
                label.data += it
            ]
        }
    }
    
}