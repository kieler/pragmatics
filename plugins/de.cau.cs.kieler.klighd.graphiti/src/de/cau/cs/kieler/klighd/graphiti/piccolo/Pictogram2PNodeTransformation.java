/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.graphiti.piccolo;

import org.eclipse.graphiti.mm.pictograms.Diagram;

import edu.umd.cs.piccolo.PNode;

/**
 * A transformation from a Graphiti Pictogram model to a Piccolo PNode.
 * 
 * @author mri
 */
public class Pictogram2PNodeTransformation {

    /**
     * Constructs a Pictogram2PNodeTransformation.
     */
    public Pictogram2PNodeTransformation() {

    }

    /**
     * Performs the actual transformation from the Pictogram model to the PNode.
     * 
     * @param diagram
     *            the Pictogram diagram
     * @return the PNode
     */
    public PNode transform(final Diagram diagram) {
        PNode graph = new PNode();
        return graph;
    }

}
