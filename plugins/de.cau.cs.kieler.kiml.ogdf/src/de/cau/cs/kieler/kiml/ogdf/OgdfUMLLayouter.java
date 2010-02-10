/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import net.ogdf.lib.UMLGraph;
import net.ogdf.lib.UMLLayoutModule;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The base class for ogdf UML layouters.
 * 
 * @author mri
 */
public abstract class OgdfUMLLayouter extends OgdfLayouter {

    /**
     * Sets the layout specific options and modules depending on the options
     * defined in the node.
     * 
     * @param node
     *            the node
     * @param progressMonitor
     *            the progress monitor
     * @return the uml layout module
     */
    protected UMLLayoutModule prepareLayouter(final KNode node,
            final IKielerProgressMonitor progressMonitor) {
        return null;
    }

    /**
     * Layouts the given graph.
     * 
     * @param layoutNode
     *            the node representing the graph
     * @param progressMonitor
     *            the progress monitor
     */
    public void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor) {
        // transform the graph
        UMLGraph graphAttributes = transformUMLGraph(layoutNode);

        // prepares the algorithm for use
        UMLLayoutModule layout = prepareLayouter(layoutNode, progressMonitor);

        if (layout != null) {
            // call the algorithm
            layout.call(graphAttributes);

            // apply the layout back to the original graph
            applyLayout(layoutNode, graphAttributes);
        }
    }
}
