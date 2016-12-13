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
public final class HierachicalKGraphSynthesis {

    private HierachicalKGraphSynthesis() {
        // not available
    }

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

        // addEdges(diagram);

    }

    private static List<KNode> recursiveTraversal(final KNode parent) {
        List<KNode> copiedChildren = new ArrayList<>();

        for (KNode child : parent.getChildren()) {
            // deep search, first look at the children
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
//                    for (KNode k : grandChildren) {
//                        Map<IProperty<?>, Object> map =
//                                k.getData(KRectangle.class).getAllProperties();
//                        if (!map.isEmpty()) {
//                            for (Map.Entry<IProperty<?>, Object> entry : map.entrySet()) {
//                                if (entry.getKey().toString()
//                                        .equals("de.cau.cs.kieler.klighd.collapsedRendering")) {
//                                    k.setHeight(200.0f);
//                                    k.setWidth(200.0f);
//                                }
//                            }
//                        }
//                    }

                }
            }
        }

        return copiedChildren;
    }

    /**
     * Copy a node or if it is a blue box skip it. On the way, clear the grandchildren to flatten
     * the hierarchy.
     * 
     * @param parent
     * @return
     */
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
            } else {
                for (KNode grandChild : child.getChildren()) {
                    clearGrandchildren(grandChild);
                }
                // copy = (KNode) copier.copy(child.getChildren().get(0));
                copy = (KNode) copier.copy(child);
            }
            
            copy.getChildren().clear();
            copier.copyReferences();

            copies.add(copy);
        }

        parent.getChildren().clear();
        parent.getChildren().addAll(copies);
        return parent;
    }

    /**
     * Delete all the grandchildren of a node.
     * 
     * @param child
     */
    private static void clearGrandchildren(KNode child) {
        for (KNode grandChild : child.getChildren()) {
            grandChild.getChildren().clear();
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
