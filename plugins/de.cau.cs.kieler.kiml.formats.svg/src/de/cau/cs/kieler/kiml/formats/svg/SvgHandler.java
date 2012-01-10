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

import java.io.StringWriter;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.service.formats.TransformationException;

/**
 * Transformation handler for SVG format.
 *
 * @author msp
 */
public class SvgHandler implements ITransformationHandler<SVGGraphics2D> {

    /**
     * {@inheritDoc}
     */
    public void deserialize(String serializedGraph, TransformationData<SVGGraphics2D, KNode> transData) {
        throw new UnsupportedOperationException("SVG parsing is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(SVGGraphics2D graphics) {
        StringWriter writer = new StringWriter();
        try {
            graphics.stream(writer, true);
        } catch (SVGGraphics2DIOException e) {
            throw new TransformationException(e);
        }
        return writer.toString();
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<SVGGraphics2D, KNode> getImporter() {
        throw new UnsupportedOperationException("SVG import is not supported.");
    }
    
    private SvgExporter exporter = new SvgExporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, SVGGraphics2D> getExporter() {
        return exporter;
    }

}
