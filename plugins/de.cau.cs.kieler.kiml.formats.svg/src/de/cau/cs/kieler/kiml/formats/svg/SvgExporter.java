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
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Exporter for SVG format.
 *
 * @author msp
 */
public class SvgExporter implements IGraphTransformer<KNode, String> {

    /**
     * {@inheritDoc}
     */
    public void transform(TransformationData<KNode, String> data) {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(TransformationData<KNode, String> data) {
        throw new UnsupportedOperationException("SVG layout transfer is not supported.");
    }

}
