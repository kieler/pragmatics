/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.core.kgraph.text.ui;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Provider;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KStyleHolder;
import de.cau.cs.kieler.core.krendering.KStyleRef;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.service.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Xtext based handler for the KGraph format.
 *
 * @author msp
 */
public class KGraphTextHandler extends AbstractEmfHandler<KNode> {
    
    /** The KGraph Text format identifier. */
    public static final String FORMAT = "de.cau.cs.kieler.kgraph.text";
    
    /**
     * Check all cross-references of the given graph and generate identifiers where necessary.
     * 
     * @param node the parent node of the graph
     */
    public static void generateMissingIdentifiers(final KNode node) {
        checkRenderings(node);
        for (KLabel label : node.getLabels()) {
            checkRenderings(label);
        }
        for (KEdge edge : node.getOutgoingEdges()) {
            checkIdentifier(edge.getTarget());
            checkIdentifier(edge.getSourcePort());
            checkIdentifier(edge.getTargetPort());
            checkRenderings(edge);
            for (KLabel label : edge.getLabels()) {
                checkRenderings(label);
            }
        }
        for (KPort port : node.getPorts()) {
            checkRenderings(port);
            for (KLabel label : port.getLabels()) {
                checkRenderings(label);
            }
        }
        for (KNode child : node.getChildren()) {
            generateMissingIdentifiers(child);
        }
    }
    
    /**
     * Check rendering and style references and create identifiers if necessary.
     * 
     * @param graphElement a graph element
     */
    private static void checkRenderings(final KGraphElement graphElement) {
        for (KGraphData graphData : graphElement.getData()) {
            if (graphData instanceof KRendering) {
                checkRenderings((KRendering) graphData);
            } else if (graphData instanceof KRenderingLibrary) {
                for (KStyleHolder styleHolder : ((KRenderingLibrary) graphData).getRenderings()) {
                    checkRenderings(styleHolder);
                }
            }
        }
    }
    
    /**
     * Check rendering and style references and create identifiers if necessary.
     * 
     * @param styleHolder a style holder
     */
    private static void checkRenderings(final KStyleHolder styleHolder) {
        if (styleHolder instanceof KRenderingRef) {
            KRendering target = ((KRenderingRef) styleHolder).getRendering();
            if (target != null && (target.getId() == null || target.getId().length() == 0)) {
                target.setId("R" + Integer.toHexString(target.hashCode()));
            }
        }
        for (KStyle style : styleHolder.getStyles()) {
            if (style instanceof KStyleRef) {
                KStyleHolder target = ((KStyleRef) style).getStyleHolder();
                if (target != null && (target.getId() == null || target.getId().length() == 0)) {
                    target.setId("R" + Integer.toHexString(target.hashCode()));
                }
            }
        }
        if (styleHolder instanceof KContainerRendering) {
            for (KRendering containedRendering : ((KContainerRendering) styleHolder).getChildren()) {
                checkRenderings(containedRendering);
            }
        }
    }
    
    /**
     * Check the identifier of a graph element and create it if necessary.
     * 
     * @param graphElement a graph element
     */
    private static void checkIdentifier(final KGraphElement graphElement) {
        if (graphElement != null) {
            KIdentifier identifier = graphElement.getData(KIdentifier.class);
            if (identifier == null) {
                identifier = KLayoutDataFactory.eINSTANCE.createKIdentifier();
                graphElement.getData().add(identifier);
            }
            if (identifier.getId() == null || identifier.getId().length() == 0) {
                identifier.setId("E" + Integer.toHexString(graphElement.hashCode()));
            }
        }
    }
    
    /** the resource set provider injected by the KGraphExecutableExtensionFactory. */
    @Inject private Provider<XtextResourceSet> resourceSetProvider;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceSet createResourceSet() {
        return resourceSetProvider.get();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize(final TransformationData<KNode, KNode> transData) {
        for (KNode graph : transData.getTargetGraphs()) {
            generateMissingIdentifiers(graph);
        }
        return super.serialize(transData);
    }
    
    /** the identity transformer for KGraph. */
    private static final IGraphTransformer<KNode, KNode> TRANSFORMER
            = new IGraphTransformer<KNode, KNode>() {
        
        public void transform(final TransformationData<KNode, KNode> data) {
            data.getTargetGraphs().add(data.getSourceGraph());
        }
        
        public void transferLayout(final TransformationData<KNode, KNode> data) {
            // nothing to do
        }
    };

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, KNode> getImporter() {
        return TRANSFORMER;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, KNode> getExporter() {
        return TRANSFORMER;
    }

}
