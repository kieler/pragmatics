package de.cau.cs.kieler.klighd.xtext.transformations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

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

    public static void transform(KNode diagram) {
        // remove the empty node from the model
        KNode trashNode = diagram.getChildren().remove(0);
        diagram.getChildren().addAll(trashNode.getChildren());

        List<KNode> nodes = recursiveTraversal(diagram);
        diagram.getChildren().addAll(nodes);

        deletion(diagram.getChildren());

        // result.children.toList.forEach[
        // it.children.forEach[
        // if(!it.children.empty) {
        // val p = it
        // newArrayList(it.children).forEach[ node |
        // p.children -= node
        //
        //
        //
        // ]
        // }]
        // ]
        //
        // result.KRendering // first krendering
        // result.data.filter(KRendering).forEach[ ren |
        // println(ren.getProperty(KlighdProperties.COLLAPSED_RENDERING))
        //
        // ]

        // KlighdProperties.COLLAPSED_RENDERING
    }

    private static List<KNode> recursiveTraversal(KNode parent) {
        List<KNode> copiedChildren = new ArrayList<>();

        KLayoutData layoutData = parent.getData(KLayoutData.class);
        if (layoutData.getProperty(KlighdProperties.EXPAND)) {
            for (KNode child : parent.getChildren()) {
                // extract/copy content of children
                Copier copier = new Copier();
                KNode copy = (KNode) copier.copy(child);
                copiedChildren.add(copy);

                copiedChildren.addAll(recursiveTraversal(child));
            }
        }

        return copiedChildren;

        // collapse all children
        // KlighdProperties p = child.getData(KlighdProperties.class);
        // p.

        // for (KNode copiedChild : copiedChildren) {
        // recursiveTraversal(copiedChild);
        // }

    }

    private static void deletion(List<KNode> allNodes) {
        for (KNode child : allNodes) {
            for (KNode childOfChild : child.getChildren()) {
                childOfChild.getChildren().clear();
            }
        }
    }

}
