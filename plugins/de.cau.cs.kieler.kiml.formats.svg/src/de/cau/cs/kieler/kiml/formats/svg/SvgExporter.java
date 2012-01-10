/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.svg;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Exporter for SVG format.
 *
 * @author msp
 */
public class SvgExporter implements IGraphTransformer<KNode, SVGGraphics2D> {

    /** namespace of the SVG format. */
    private static final String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
    
    /**
     * {@inheritDoc}
     */
    public void transform(TransformationData<KNode, SVGGraphics2D> data) {
        // create a DOM document for XML output
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(SVG_NAMESPACE, "svg", null);
        
        // create an instance of the SVG generator
        SVGGraphics2D graphics = new SVGGraphics2D(document);
        data.getTargetGraphs().add(graphics);
        
        // draw SVG graphics
        graphics.fillRect(50, 50, 200, 100);
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(TransformationData<KNode, SVGGraphics2D> data) {
        throw new UnsupportedOperationException("SVG layout transfer is not supported.");
    }

}
