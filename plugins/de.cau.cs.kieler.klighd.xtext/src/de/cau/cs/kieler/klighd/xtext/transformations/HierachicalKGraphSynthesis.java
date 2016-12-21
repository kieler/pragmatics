package de.cau.cs.kieler.klighd.xtext.transformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.impl.KGraphFactoryImpl;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil;
import de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl;
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
 * 
 * 
 * @author ybl
 *
 */
public final class HierachicalKGraphSynthesis {

    /**
     * 
     */
    private HierachicalKGraphSynthesis() {
        // not available
    }

    /** */
    private static Map<KNode, KNode> parents;

    /**
     * Transform the graph to have the representation we want.
     * 
     * @param diagram
     *            the method takes a graph as input.
     */
    public static void transform(final KNode diagram) {
        parents = new HashMap<>();
        // put the inner nodes onto the highest hierarchy level

        List<KNode> nodes = recursiveTraversal(diagram);

        deleteEdges(nodes);

        diagram.getChildren().clear();
        diagram.getChildren().addAll(nodes);

        addHierarchicalEdges();

    }

    /**
     * 
     * @param parent
     * @return
     */
    private static List<KNode> recursiveTraversal(final KNode parent) {
        List<KNode> copiedChildren = new ArrayList<>();

        for (KNode child : parent.getChildren()) {
            // deep search
            copiedChildren.addAll(recursiveTraversal(child));

            List<KNode> grandChildren = child.getChildren();
            boolean isBlueBox =
                    grandChildren.size() == 1 && !grandChildren.get(0).getChildren().isEmpty();

            // Ignore blue boxes and copy only the child inside
            if (!isBlueBox) {
                if (!grandChildren.isEmpty()) {

                    // extract/copy content of children
                    Copier copier = new Copier();
                    KNode copy = (KNode) copier.copy(child);
                    copier.copyReferences();

                    // KNode parentWithDifferentChild = copyWithoutBlueBox(child);
                    copiedChildren.add(copy);
                    parents.put(copy, parent);
                    restoreLayout(copy);

                    // keep track of the right parent
                    for (Entry<KNode, KNode> savedParent : parents.entrySet()) {
                        if (savedParent.getValue().equals(child)) {
                            savedParent.setValue(copy);
                        }
                    }

                } else {
                    // The child has no children, therefore it is not hierarchical (no copy)
                }
            } else {
                // if it is a blue box reset the pointer to the parent
                for (Entry<KNode, KNode> savedParent : parents.entrySet()) {
                    if (savedParent.getValue().equals(child)) {
                        savedParent.setValue(parent);
                    }
                }
            }
        }

        return copiedChildren;
    }

    /**
     * 
     * @param node
     */
    @SuppressWarnings("deprecation")
    private static void restoreLayout(final KNode node) {
        // set size
        KGraphDataUtil.loadDataElements(node);
        node.setHeight(CoreOptions.NODE_SIZE_MIN_HEIGHT.getDefault());
        node.setWidth(CoreOptions.NODE_SIZE_MIN_WIDTH.getDefault());

        KRenderingImpl rendering = node.getData(KRenderingImpl.class);
        rendering.getActions().clear();

        for (KNode child : node.getChildren()) {
            child.setProperty(KlighdProperties.EXPAND, false);
        }

        if (node.getChildren().size() > 0) {
            node.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
        }

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

            copier.copyReferences();
            copy.getChildren().clear();
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
     * Delete the edges of the root children such that the hierarchical edges can be added in that
     * layer instead.
     * 
     * @param diagram
     */
    private static void deleteEdges(final List<KNode> diagram) {
        // for (KNode node : diagram) {
        //
        // List<KEdge> deleteEdges = new ArrayList<>();
        // for (KEdge edge : node.getIncomingEdges()) {
        //
        // KNode target = edge.getTarget();
        // KNode source = edge.getSource();
        //
        // if (!source.getPorts().contains(edge.getSourcePort())) {
        // deleteEdges.add(edge);
        // }
        //
        // // if (!KGraphUtil.isDescendant(target, source) || target.getChildren().size() != 0)
        // // {
        // // deleteEdges.add(edge);
        // // }
        // }
        // node.getOutgoingEdges().removeAll(deleteEdges);
        // }
    }

    /**
     * Add the edges representing the Hierarchical connection.
     */
    private static void addHierarchicalEdges() {
        for (Entry<KNode, KNode> entry : parents.entrySet()) {
            KNode child = entry.getKey();
            KNode parent = entry.getValue();

            // create an edge
            KEdge edge = KGraphFactoryImpl.eINSTANCE.createKEdge();

            edge.setSource(parent);
            edge.setTarget(child);

            parent.getOutgoingEdges().add(edge);
        }

    }

}
