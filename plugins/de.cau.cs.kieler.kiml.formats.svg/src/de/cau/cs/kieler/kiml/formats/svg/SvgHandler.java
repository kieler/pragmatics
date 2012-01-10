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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Transformation handler for SVG format.
 *
 * @author msp
 */
public class SvgHandler implements ITransformationHandler<String> {

    /**
     * {@inheritDoc}
     */
    public void deserialize(String serializedGraph, TransformationData<String, KNode> transData) {
        throw new UnsupportedOperationException("SVG parsing is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(String graph) {
        return graph;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<String, KNode> getImporter() {
        throw new UnsupportedOperationException("SVG import is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, String> getExporter() {
        // TODO Auto-generated method stub
        return null;
    }

}
