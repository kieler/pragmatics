/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.intermediate;

import java.util.ArrayList;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * A processor connect all roots of a given graph to a super root which then is the new root of the
 * graph.
 * 
 * @author sor
 * @author sgu
 */
public class RootProcessor implements ILayoutProcessor {

    private ArrayList<TNode> roots = new ArrayList<TNode>();

    /**
     * {@inheritDoc}
     */
    public void process(final TGraph tGraph, final IKielerProgressMonitor progressMonitor) {

        /** clear list of roots if processor is reused */
        roots.clear();

        /** find all roots in the graph */
        for (TNode node : tGraph.getNodes()) {
            if (node.getIncomingEdges().isEmpty()) {
                node.setProperty(Properties.ROOT, true);
                roots.add(node);
            }
        }
        /**
         * if there are more than one root add a super root and set the current roots as its
         * children
         */
        switch (roots.size()) {
        case 0:
            assert tGraph.getNodes().isEmpty();
            TNode root = new TNode(0, tGraph, "DUMMY_ROOT");
            root.setProperty(Properties.ROOT, true);
            root.setProperty(Properties.DUMMY, true);
            tGraph.getNodes().add(root);
            break;
            
        case 1:
            // perfect, we already have only one root
            break;

        default:
            TNode superRoot = new TNode(0, tGraph, "SUPER_ROOT");

            for (TNode tRoot : roots) {
                superRoot.addChild(tRoot);
                tRoot.setProperty(Properties.ROOT, false);
            }
            superRoot.setProperty(Properties.ROOT, true);
            superRoot.setProperty(Properties.DUMMY, true);
            tGraph.getNodes().add(superRoot);
            break;
        }
    }
}
