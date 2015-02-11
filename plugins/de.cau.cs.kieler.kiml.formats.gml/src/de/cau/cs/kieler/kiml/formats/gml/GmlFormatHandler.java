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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.formats.TransformationException;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;

/**
 * Formats handler for the gml format.
 * 
 * @author mkr
 */
public class GmlFormatHandler implements IGraphFormatHandler<GMLModel> {
    
    /** the maximal allowed character value for GML. */
    private static final char MAX_CHAR = 127;

    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<GMLModel, KNode> transData) {
        try {
            InputStream is = new ByteArrayInputStream(serializedGraph.getBytes());
            transData.setSourceGraph(GMLParser.parse(is));
        } catch (IOException e) {
            throw new TransformationException("Cannot parse the passed " + "gml. ("
                    + e.getMessage() + ")", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final TransformationData<KNode, GMLModel> transData) {
        StringBuffer sb = new StringBuffer();
        for (GMLModel gm : transData.getTargetGraphs()) {
            sb.append(GMLSerializer.serialize(gm));
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private GmlImporter importer = new GmlImporter();
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<GMLModel, KNode> getImporter() {
        return importer;
    }

    private GmlExporter exporter = new GmlExporter();
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, GMLModel> getExporter() {
        return exporter;
    }
    
    /**
     * Extracts a list (subgraph of a CollectionElement).
     * 
     * @param element element to extract list from
     * @return the list of subgraph to transform
     */
    public static List<Element> getElements(final Element element) {
        if (element instanceof CollectionElement) {
            CollectionElement collectEle = (CollectionElement) element;
            return collectEle.getElements();
        } else {
            return Collections.emptyList();
        }
    }
    
    /**
     * Creates a GML Point for a KPoint.
     * 
     * @param parent the parent collection the GML Point is attached to
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
    
    /**
     * Create a GML label for a KLabel.
     * 
     * @param parent the parent collection the GML Label is attached to
     * @param klabel a KLabel
     * @return a GML element with the label text
     */
    public static Element createLabel(final Element parent, final KLabel klabel) {
        StringBuilder text = new StringBuilder(klabel.getText());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\"') {
                text.replace(i, i + 1, "&quot;");
            } else if (c == '&') {
                text.replace(i, i + 1, "&amp;");
            } else if (c > MAX_CHAR) {
                text.replace(i, i + 1, "&#" + (int) c + ";");
            }
        }
        // text.insert(0, '\"');
        // text.append('\"');
        Element l = new StringElement(parent, "label", text.toString());
        return l;
    }

}
