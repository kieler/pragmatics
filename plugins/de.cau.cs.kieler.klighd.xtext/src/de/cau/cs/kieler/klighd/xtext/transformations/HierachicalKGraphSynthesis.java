package de.cau.cs.kieler.klighd.xtext.transformations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.util.KlighdProperties;

/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */

/**
 * @author lucy
 *
 */
public class HierachicalKGraphSynthesis {

    /**
     * Transform the graph to have the representation we want
     */
    public static void transform(final KNode diagram) {
        // put the inner nodes onto the highest hierarchy level
        List<KNode> nodes = recursiveTraversal(diagram);
        diagram.getChildren().clear();
        diagram.getChildren().addAll(nodes);

        deletion(diagram.getChildren());
    }

    private static List<KNode> recursiveTraversal(final KNode parent) {
        List<KNode> copiedChildren = new ArrayList<>();

        for (KNode child : parent.getChildren()) {
            if (!child.getChildren().isEmpty()) { 

                // extract/copy content of children
                Copier copier = new Copier();
                KNode copy = (KNode) copier.copy(child);
                copiedChildren.add(copy);

                copiedChildren.addAll(recursiveTraversal(child));
            }
        }

        return copiedChildren;
    }

    /**
     * delete all the deeper hierachy levels of the copied children
     * 
     * @param allNodes
     */
    private static void deletion(final List<KNode> allNodes) {
        for (KNode node : allNodes) {
            for (KNode childOfChild : node.getChildren()) {
                childOfChild.getChildren().clear();
                KLayoutData layoutDataChildOfChild = childOfChild.getData(KLayoutData.class);
                layoutDataChildOfChild.setProperty(KlighdProperties.COLLAPSED_RENDERING, true);
                layoutDataChildOfChild.setProperty(KlighdProperties.EXPANDED_RENDERING, false);
                layoutDataChildOfChild.setProperty(KlighdProperties.EXPAND, true);
            }

            //try to expand all the original children
            KLayoutData layoutDataNode = node.getData(KLayoutData.class);
            layoutDataNode.setProperty(KlighdProperties.COLLAPSED_RENDERING, false);
            layoutDataNode.setProperty(KlighdProperties.EXPANDED_RENDERING, true);
            //layoutDataNode.setProperty(KlighdProperties.EXPAND, true);
        }
    }

}
