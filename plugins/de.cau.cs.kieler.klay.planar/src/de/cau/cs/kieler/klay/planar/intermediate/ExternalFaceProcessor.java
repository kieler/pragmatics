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
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.Iterator;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Calculates the external face. The face with the most adjacent edges is chosen to be external, if
 * there are more edges with the same adjacent edge count, take the first found.
 * 
 * @author pkl
 */
public class ExternalFaceProcessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("external face processor", 1);
        
        pGraph.setChangedFaces();
        Iterator<PFace> it = pGraph.getFaces().iterator();
        PFace externalFace = null;
        if (it.hasNext()) {
            externalFace = it.next();
            while (it.hasNext()) {
                PFace face = it.next();
                // Choose the one with the most connected elements.
                if (face.getAdjacentNodeCount() > externalFace.getAdjacentNodeCount()) {
                    // expansion cycle face should not be set as external face.
                    if (face.getProperty(Properties.EXPANSION_CYCLE_FACE) == null) {
                        externalFace = face;
                    }
                }
            }
        }
        pGraph.setExternalFace(externalFace);

        monitor.done();
    }

}
