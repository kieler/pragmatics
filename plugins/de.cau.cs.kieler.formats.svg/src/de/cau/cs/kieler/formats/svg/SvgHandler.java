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
package de.cau.cs.kieler.formats.svg;

import java.io.StringWriter;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.formats.IGraphFormatHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.formats.TransformationException;

/**
 * Transformation handler for SVG format. The handler supports two options:
 * <ul>
 *   <li>{@link SvgHandler#USE_CSS} switches usage of CSS properties on or off.</li>
 *   <li>{@link KAwtRenderer#SCALE} defines a scale factor for all coordinates, which is useful
 *     for increasing precision, since the AWT graphics interface only supports integer
 *     coordinates.</li>
 * </ul>
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class SvgHandler implements IGraphFormatHandler<SVGGraphics2D> {
    
    /** the identifier of the SVG format. */
    public static final String ID = "org.w3.svg";
    
    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<SVGGraphics2D, ElkNode> transData) {
        throw new UnsupportedOperationException("SVG parsing is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final TransformationData<ElkNode, SVGGraphics2D> transData) {
        StringWriter writer = new StringWriter();
        boolean useCss = transData.getProperty(SvgOptions.USE_CSS);
        try {
            for (SVGGraphics2D graphics : transData.getTargetGraphs()) {
                graphics.stream(writer, useCss);
            }
        } catch (SVGGraphics2DIOException e) {
            throw new TransformationException(e);
        }
        return writer.toString();
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<SVGGraphics2D, ElkNode> getImporter() {
        throw new UnsupportedOperationException("SVG import is not supported.");
    }
    
    private SvgExporter exporter = new SvgExporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, SVGGraphics2D> getExporter() {
        return exporter;
    }

}
