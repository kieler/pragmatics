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
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.Iterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;

/**
 * 
 * @author pkl
 */
public class ExternalFaceProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph) {
        getMonitor().begin("external face processor", 1);

        // Creating sink nodes for every graph face
        Iterator<PFace> it = pGraph.getFaces().iterator();
        PFace externalFace = null;
        if (it.hasNext()) {
            externalFace = it.next();
            while (it.hasNext()) {
                PFace face = it.next();
                // Choose the one with the most connected elements.
                if (face.getAdjacentNodeCount() > externalFace.getAdjacentNodeCount()) {
                    externalFace = face;
                }
            }
        } else {
            throw new InconsistentGraphModelException("TamassiaOrthogonalizer, createFlowNetwork: "
                    + "the graph has to have at least one face!");
        }
        pGraph.setExternalFace(externalFace);

        getMonitor().done();
    }

}
