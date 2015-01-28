/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.gml;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.formats.gml.xtext.GmlExporter;
import de.cau.cs.kieler.kiml.formats.gml.xtext.GmlImporter;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;

/**
 * @author uru
 */
public class GmlFormatHandler implements IGraphFormatHandler<GMLModel> {

    /**
     * {@inheritDoc}
     */
    public void deserialize(String serializedGraph, TransformationData<GMLModel, KNode> transData) {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(TransformationData<KNode, GMLModel> transData) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Creates a GML Point for a KPoint.
     * 
     * @param parent the parent collection, the GML Point is attached to
     * @param point a KPoint
     * @return a GML Element with the coordinates of the given point
     */
    public static Element createPoint(final Element parent, final KPoint point) {
        CollectionElement p = new CollectionElement(parent, "point"); 
        NumberElement x = new NumberElement(p, "x", point.getX());
        p.getElements().add(x);
        NumberElement y = new NumberElement(p, "y", point.getY());
        p.getElements().add(y);
        return p;
    }

    private GmlImporter importer = new GmlImporter();
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<GMLModel, KNode> getImporter() {
        return importer;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, GMLModel> getExporter() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
