package de.cau.cs.kieler.klighd.xtext.transformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KGraphFactory;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.impl.KGraphFactoryImpl;
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
 * @author ybl
 *
 */
public class HierachicalKGraphSynthesis {
    private static Map<KNode, KNode> parents = new HashMap<>();

    /**
     * Transform the graph to have the representation we want.
     * 
     * @param diagram
     *            the method takes a graph as input.
     */
    public static void transform(final KNode diagram) {
        // put the inner nodes onto the highest hierarchy level
        List<KNode> nodes = recursiveTraversal(diagram);
        diagram.getChildren().clear();
        diagram.getChildren().addAll(nodes);

        deletion(diagram.getChildren());
        addEdges(diagram);

    }

    private static List<KNode> recursiveTraversal(final KNode parent) {
        List<KNode> copiedChildren = new ArrayList<>();

        for (KNode child : parent.getChildren()) {
            if (!child.getChildren().isEmpty()) {

                // extract/copy content of children
                Copier copier = new Copier();
                KNode copy = (KNode) copier.copy(child);
                
                // delete the existing edges for the copy
                if (copy.getOutgoingEdges() != null) {
                    copy.getOutgoingEdges().clear();
                }
                if (copy.getIncomingEdges() != null) {
                    copy.getIncomingEdges().clear();
                }
                copiedChildren.add(copy);
                parents.put(copy, parent);

                copiedChildren.addAll(recursiveTraversal(copy));
            }
        }

        return copiedChildren;
    }

    /**
     * Delete all the deeper hierachy levels of the copied children.
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

            // try to expand all the original children
            KLayoutData layoutDataNode = node.getData(KLayoutData.class);
            layoutDataNode.setProperty(KlighdProperties.COLLAPSED_RENDERING, false);
            layoutDataNode.setProperty(KlighdProperties.EXPANDED_RENDERING, true);
            layoutDataNode.setProperty(KlighdProperties.EXPAND, true);
            KShapeLayout shape = node.getData(KShapeLayout.class);
            shape.setHeight(200.0f);
            shape.setWidth(200.0f);
        }
    }

    /**
     * @param diagram
     */
    private static void addEdges(final KNode diagram) {
        for (Entry<KNode, KNode> entry : parents.entrySet()) {
            KNode child = entry.getKey();
            KNode parent = entry.getValue();

            // create an edge
            KEdge edge = KGraphFactoryImpl.eINSTANCE.createKEdge();
            KEdgeLayout edgeLayout = KLayoutDataFactory.eINSTANCE.createKEdgeLayout();
            edge.getData().add(edgeLayout);

            edge.setSource(parent);
            edge.setTarget(child);
            
            parent.getOutgoingEdges().add(edge);
        }

    }

}
