/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.ogml;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.cau.cs.kieler.formats.AbstractEmfHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import net.ogdf.ogml.DocumentRoot;
import net.ogdf.ogml.OgmlFactory;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.PointType;
import net.ogdf.ogml.util.OgmlResourceFactoryImpl;

/**
 * Transformer for OGML.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class OgmlFormatHandler extends AbstractEmfHandler<DocumentRoot> {

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
    public IGraphTransformer<DocumentRoot, ElkNode> getImporter() {
        return importer;
    }
    
    private OgmlExporter exporter = new OgmlExporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, DocumentRoot> getExporter() {
        return exporter;
    }
    
    /**
     * Create an OGML point from two coordinates.
     * 
     * @param x x coordinate.
     * @param y y coodinate.
     * @param offset offset to be added to coordinates
     * @return an OGML point
     */
    public static PointType createPoint(final double x, final double y, final KVector offset) {
        PointType ogmlPoint = OgmlFactory.eINSTANCE.createPointType();
        ogmlPoint.setX(x + offset.x);
        ogmlPoint.setY(y + offset.y);
        return ogmlPoint;
    }

}
