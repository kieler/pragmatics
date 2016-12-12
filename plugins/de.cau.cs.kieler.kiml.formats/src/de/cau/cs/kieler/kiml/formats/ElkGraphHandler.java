/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.formats;

import java.util.Map;

import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.GraphDataUtil;
import org.eclipse.elk.graph.ElkGraphPackage;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * Transformer for the KGraph model and XMI serialization.
 *
 * @author swe
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class ElkGraphHandler extends AbstractEmfHandler<ElkNode> {
    
    /** the KGraph format identifier. */
    public static final String FORMAT = "de.cau.cs.kieler.kgraph";

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize(final TransformationData<ElkNode, ElkNode> transData) {
        for (ElkNode graph : transData.getTargetGraphs()) {
            ElkUtil.persistDataElements(graph);
        }
        return super.serialize(transData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deserialize(final String serializedGraph,
            final TransformationData<ElkNode, ElkNode> transData) {
        
        super.deserialize(serializedGraph, transData);
        if (transData.getSourceGraph() != null) {
            // load layout options from their serialized form
            GraphDataUtil.loadDataElements(transData.getSourceGraph(), true);
        }
    }

    /**
     * {@inheritDoc}
     */
    protected ResourceSet createResourceSet() {
        Map<String, Object> extensionMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        if (!extensionMap.containsKey("elkg")) {
            extensionMap.put("elkg", new XMIResourceFactoryImpl());
        }
        
        EPackage.Registry registry = EPackage.Registry.INSTANCE;
        if (!registry.containsKey(ElkGraphPackage.eNS_URI)) {
            registry.put(ElkGraphPackage.eNS_URI, ElkGraphPackage.eINSTANCE);
        }
        
        return new ResourceSetImpl();
    }
    
    /** the identity transformer for KGraph. */
    private static final IGraphTransformer<ElkNode, ElkNode> TRANSFORMER
            = new IGraphTransformer<ElkNode, ElkNode>() {
        
        public void transform(final TransformationData<ElkNode, ElkNode> data) {
            data.getTargetGraphs().add(data.getSourceGraph());
        }
        
        public void transferLayout(final TransformationData<ElkNode, ElkNode> data) {
            // nothing to do
        }
    };

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, ElkNode> getImporter() {
        return TRANSFORMER;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, ElkNode> getExporter() {
        return TRANSFORMER;
    }

}
