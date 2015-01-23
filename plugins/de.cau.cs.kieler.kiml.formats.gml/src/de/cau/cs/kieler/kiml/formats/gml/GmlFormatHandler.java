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
     * {@inheritDoc}
     */
    public IGraphTransformer<GMLModel, KNode> getImporter() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, GMLModel> getExporter() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
