/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.ogml;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import net.ogdf.ogml.DocumentRoot;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.util.OgmlResourceFactoryImpl;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;

/**
 * Transformer for OGML.
 *
 * @author msp
 */
public class OgmlHandler extends AbstractEmfHandler<DocumentRoot> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileExtension() {
        return "ogml";
    }

    /**
     * Creates a resource set ready to be used with the GraphML meta model.
     *
     * @return a resource set
     */
    protected ResourceSet createResourceSet() {
        ResourceSet resourceset = new ResourceSetImpl();
        resourceset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            Resource.Factory.Registry.DEFAULT_EXTENSION,
            new OgmlResourceFactoryImpl()
        );
        resourceset.getPackageRegistry().put(
            OgmlPackage.eNS_URI,
            OgmlPackage.eINSTANCE
        );
        return resourceset;
    }

    private OgmlImporter importer = new OgmlImporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<DocumentRoot, KNode> getImporter() {
        return importer;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, DocumentRoot> getExporter() {
        throw new UnsupportedOperationException("OGML export is not supported yet.");
    }

}
