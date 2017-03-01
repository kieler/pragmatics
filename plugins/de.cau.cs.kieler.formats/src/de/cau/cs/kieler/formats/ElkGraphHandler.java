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

package de.cau.cs.kieler.formats;

import java.util.Map;

import org.eclipse.elk.core.util.persistence.ElkGraphResourceFactory;
import org.eclipse.elk.graph.ElkGraphPackage;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * Transformer for the KGraph model and XMI serialization.
 *
 * @author swe
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class ElkGraphHandler extends AbstractEmfHandler<ElkNode> {
    
    /** the ELK graph format identifier. */
    public static final String FORMAT = "org.eclipse.elk.graph";

    /**
     * {@inheritDoc}
     */
    protected ResourceSet createResourceSet() {
        Map<String, Object> extensionMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        if (!extensionMap.containsKey("elkg")) {
            extensionMap.put("elkg", new ElkGraphResourceFactory());
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
