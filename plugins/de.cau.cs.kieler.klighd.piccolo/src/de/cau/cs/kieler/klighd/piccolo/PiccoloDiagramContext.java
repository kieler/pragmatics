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
package de.cau.cs.kieler.klighd.piccolo;

import java.util.LinkedList;
import java.util.List;

import edu.umd.cs.piccolo.PNode;

/**
 * Instances of this class can be displayed in a Piccolo viewer by providing the required Piccolo
 * root nodes for the diagram layers and an instance of the model resolver which is used to retrieve
 * the model object represented by a given Piccolo node.
 * 
 * @author mri
 */
public class PiccoloDiagramContext {

    /** the Piccolo root nodes for the diagram layers. */
    private List<PNode> layerRoots = new LinkedList<PNode>();

    /**
     * Adds a Piccolo node as root for a new diagram layer.
     * 
     * @param root
     *            the root node
     */
    public void addLayerRoot(final PNode root) {
        layerRoots.add(root);
    }

    /**
     * Returns the Piccolo root nodes for the layers in the diagram.
     * 
     * @return the root nodes
     */
    public List<PNode> getLayerRoots() {
        return layerRoots;
    }

}
