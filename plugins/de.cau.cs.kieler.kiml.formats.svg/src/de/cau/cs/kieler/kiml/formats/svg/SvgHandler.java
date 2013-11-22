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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.formats.TransformationException;

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
public class SvgHandler implements ITransformationHandler<SVGGraphics2D> {
    
    /** the identifier of the SVG format. */
    public static final String ID = "org.w3.svg";
    
    /** whether to use CSS style properties in SVG output, as opposed to plain attributes. */
    public static final IProperty<Boolean> USE_CSS = new Property<Boolean>(
            "de.cau.cs.kieler.svg.css", false);

    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<SVGGraphics2D, KNode> transData) {
        throw new UnsupportedOperationException("SVG parsing is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final TransformationData<KNode, SVGGraphics2D> transData) {
        StringWriter writer = new StringWriter();
        boolean useCss = transData.getProperty(USE_CSS);
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
