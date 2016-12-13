package de.cau.cs.kieler.klighd.xtext.transformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.impl.KGraphFactoryImpl;
import de.cau.cs.kieler.klighd.krendering.KRectangle;
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

        // deletion(diagram.getChildren());
        // addEdges(diagram);

    }

    private static KNode copyWithoutBlueBox(final KNode parent) {
        List<KNode> copies = new ArrayList<KNode>();
        Copier copier = new Copier();

        for (KNode child : parent.getChildren()) {
            List<KNode> children = child.getChildren();
            KNode copy;
            // Remove useless blue boxes, if there is only one expandable child inside
            if (!(children.size() == 1 && !children.get(0).getChildren().isEmpty())) {
                clearGrandchildren(child);
                copy = (KNode) copier.copy(child);
                copy.getChildren().clear();

            } else {
                for (KNode grandChild : child.getChildren()) {
                    clearGrandchildren(grandChild);
                }
                // copy = (KNode) copier.copy(child.getChildren().get(0));
                copy = (KNode) copier.copy(child);
            }

            copier.copyReferences();

            copies.add(copy);
        }

        parent.getChildren().clear();
        parent.getChildren().addAll(copies);
        return parent;
    }

    private static List<KNode> recursiveTraversal(final KNode parent) {
        List<KNode> copiedChildren = new ArrayList<>();

        if (parent.getChildren().isEmpty()) {
            return copiedChildren;
        }

        for (KNode child : parent.getChildren()) {
            copiedChildren.addAll(recursiveTraversal(child));

            List<KNode> grandChildren = child.getChildren();

            // Ignore blue boxes and copied only the child inside
            if (!(grandChildren.size() == 1 && !grandChildren.get(0).getChildren().isEmpty())) {
                if (!grandChildren.isEmpty()) {

                    // extract/copy content of children

                    KNode copy = copyWithoutBlueBox(child);

                    // delete the existing edges for the copy
                    if (copy.getOutgoingEdges() != null) {
                        copy.getOutgoingEdges().clear();
                    }
                    if (copy.getIncomingEdges() != null) {
                        copy.getIncomingEdges().clear();
                    }
                    copiedChildren.add(copy);
                    parents.put(copy, parent);

                    // Display expandable Nodes smaller without their content.
                    // TODO shrink size of root node.
                    Map<IProperty<?>, Object> map =
                            child.getData(KRectangle.class).getAllProperties();
                    if (!map.isEmpty()) {
                        for (Map.Entry<IProperty<?>, Object> entry : map.entrySet()) {
                            if (entry.getKey().toString()
                                    .equals("de.cau.cs.kieler.klighd.collapsedRendering")) {
                                child.setHeight(200.0f);
                                child.setWidth(200.0f);
                            }
                        }
                    }
                }
            }
        }

        return copiedChildren;
    }

    private static void clearGrandchildren(KNode child) {
        for (KNode grandChild : child.getChildren()) {
            grandChild.getChildren().clear();
        }
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
                // KLayoutData layoutDataChildOfChild = childOfChild.getData(KLayoutData.class);
                node.setProperty(KlighdProperties.COLLAPSED_RENDERING, true);
                node.setProperty(KlighdProperties.EXPANDED_RENDERING, false);
                node.setProperty(KlighdProperties.EXPAND, true);
            }

            // try to expand all the original children
            // KLayoutData layoutDataNode = node.getData(KLayoutData.class);
            node.setProperty(KlighdProperties.COLLAPSED_RENDERING, false);
            node.setProperty(KlighdProperties.EXPANDED_RENDERING, true);
            node.setProperty(KlighdProperties.EXPAND, true);
            // KShapeLayout shape = node.getData(KShapeLayout.class);
            node.setHeight(200.0f);
            node.setWidth(200.0f);
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
            // KEdgeLayout edgeLayout = KLayoutDataFactory.eINSTANCE.createKEdgeLayout();
            // edge.getData().add(edgeLayout);

            edge.setSource(parent);
            edge.setTarget(child);

            parent.getOutgoingEdges().add(edge);
        }

    }

}
