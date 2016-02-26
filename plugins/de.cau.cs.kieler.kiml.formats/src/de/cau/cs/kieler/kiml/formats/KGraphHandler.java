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

import org.eclipse.elk.core.klayoutdata.KLayoutDataPackage;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.GraphDataUtil;
import org.eclipse.elk.graph.KGraphPackage;
import org.eclipse.elk.graph.KNode;
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
public class KGraphHandler extends AbstractEmfHandler<KNode> {
    
    /** the KGraph format identifier. */
    public static final String FORMAT = "de.cau.cs.kieler.kgraph";

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize(final TransformationData<KNode, KNode> transData) {
        for (KNode graph : transData.getTargetGraphs()) {
            ElkUtil.persistDataElements(graph);
        }
        return super.serialize(transData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deserialize(final String serializedGraph,
            final TransformationData<KNode, KNode> transData) {
        
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
        if (!extensionMap.containsKey("kgraph")) {
            extensionMap.put("kgraph", new XMIResourceFactoryImpl());
        }
        if (!extensionMap.containsKey("kgx")) {
            extensionMap.put("kgx", new XMIResourceFactoryImpl());
        }
        
        EPackage.Registry registry = EPackage.Registry.INSTANCE;
        if (!registry.containsKey(KGraphPackage.eNS_URI)) {
            registry.put(KGraphPackage.eNS_URI, KGraphPackage.eINSTANCE);
        }
        if (!registry.containsKey(KLayoutDataPackage.eNS_URI)) {
            registry.put(KLayoutDataPackage.eNS_URI, KLayoutDataPackage.eINSTANCE);
        }
        
        return new ResourceSetImpl();
    }
    
    /** the identity transformer for KGraph. */
    private static final IGraphTransformer<KNode, KNode> TRANSFORMER
            = new IGraphTransformer<KNode, KNode>() {
        
        public void transform(final TransformationData<KNode, KNode> data) {
            KNode graph = data.getSourceGraph();        
            // Make sure all graph elements are configured according to specifications
            ElkUtil.validate(graph);
            // Forward the validated graph as layout graph
            data.getTargetGraphs().add(graph);
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
