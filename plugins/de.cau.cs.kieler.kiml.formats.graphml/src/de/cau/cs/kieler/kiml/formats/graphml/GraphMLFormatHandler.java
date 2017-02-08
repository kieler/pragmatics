/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.graphml;

import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.graphdrawing.graphml.DocumentRoot;
import org.graphdrawing.graphml.GraphMLPackage;
import org.graphdrawing.graphml.util.GraphMLResourceFactoryImpl;

import de.cau.cs.kieler.kiml.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;

/**
 * A transformer for GraphML.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class GraphMLFormatHandler extends AbstractEmfHandler<DocumentRoot> {

    /**
     * Creates a resource set ready to be used with the GraphML meta model.
     *
     * @return a resource set
     */
    protected ResourceSet createResourceSet() {
        ResourceSet resourceset = new ResourceSetImpl();
        resourceset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            Resource.Factory.Registry.DEFAULT_EXTENSION,
            new GraphMLResourceFactoryImpl()
        );
        resourceset.getPackageRegistry().put(
            GraphMLPackage.eNS_URI,
            GraphMLPackage.eINSTANCE
        );
        return resourceset;
    }
    
    private GraphMLImporter importer = new GraphMLImporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<DocumentRoot, ElkNode> getImporter() {
        return importer;
    }
    
    private GraphMLExporter exporter = new GraphMLExporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, DocumentRoot> getExporter() {
        return exporter;
    }

}
