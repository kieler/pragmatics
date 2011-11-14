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
package de.cau.cs.kieler.kiml.formats.gml;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.formats.gml.gml.Element;
import de.cau.cs.kieler.kiml.formats.gml.gml.GmlFactory;
import de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.service.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;

/**
 * A transformer for GML.
 *
 * @author msp
 */
public class GmlHandler extends AbstractEmfHandler<GmlModel> {
    
    /** the injector for creation of resources. */
    private static Injector injector;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileExtension() {
        return "gml";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceSet createResourceSet() {
        if (injector == null) {
            injector = new GMLStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        return injector.getInstance(XtextResourceSet.class);
    }
    
    private GmlImporter importer = new GmlImporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<GmlModel, KNode> getImporter() {
        return importer;
    }
    
    private GmlExporter exporter = new GmlExporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, GmlModel> getExporter() {
        return exporter;
    }
    
    /**
     * Create a GML point for a KPoint.
     * 
     * @param point a KPoint
     * @return a GML element with the coordinates of the given point
     */
    public static Element createPoint(final KPoint point) {
        Element p = GmlFactory.eINSTANCE.createElement();
        p.setKey("point");
        Element x = GmlFactory.eINSTANCE.createElement();
        x.setKey("x");
        x.setValue(Float.toString(point.getX()));
        p.getElements().add(x);
        Element y = GmlFactory.eINSTANCE.createElement();
        y.setKey("y");
        y.setValue(Float.toString(point.getY()));
        p.getElements().add(y);
        return p;
    }

}
