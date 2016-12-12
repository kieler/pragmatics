/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.svg;

import java.awt.Dimension;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.graph.ElkNode;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;

/**
 * Exporter for SVG format.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class SvgExporter implements IGraphTransformer<ElkNode, SVGGraphics2D> {

    /** namespace of the SVG format. */
    private static final String SVG_NAMESPACE = "http://www.w3.org/2000/svg";

    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<ElkNode, SVGGraphics2D> data) {
        // create a DOM document for XML output
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(SVG_NAMESPACE, "svg", null);

        // create an instance of the SVG generator
        SVGGraphics2D graphics = new SVGGraphics2D(document);
        data.getTargetGraphs().add(graphics);
        
        // create and execute an instance of the KAwtRenderer
        KAwtRenderer renderer = new KAwtRenderer(graphics, data);
        KVector size = renderer.renderGraph(data.getSourceGraph());
        
        // set the bounding box for the SVG canvas
        graphics.setSVGCanvasSize(new Dimension((int) size.x + 1, (int) size.y + 1));
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<ElkNode, SVGGraphics2D> data) {
        throw new UnsupportedOperationException("SVG layout transfer is not supported.");
    }

}
