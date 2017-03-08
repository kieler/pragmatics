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
package de.cau.cs.kieler.graphs.klighd.syntheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeType;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalMetaDataProvider;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.krendering.KPolyline;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;
import de.cau.cs.kieler.klighd.krendering.LineStyle;
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions;
import de.cau.cs.kieler.klighd.krendering.impl.KRenderingFactoryImpl;
import de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.overlapRemoval.helper.Edge;

/**
 * 
 * @author ybl
 *
 */
public final class HierachicalKGraphSynthesis {

    /** */
    private static Map<KNode, KNode> parents;

    /**
     * Transform the graph to have the representation we want.
     * 
     * @param diagram
     *            the method takes a graph as input.
     * @param layout
     *            the layoutalgorithm that should be used for the connection of the hierarchical
     *            nodes we extracted
     */
    public static void transform(final KNode diagram, final String layout) {
        parents = new HashMap<>();

        // put the inner nodes onto the highest hierarchy level
        List<KNode> nodes = recursiveTraversal(diagram);

        diagram.getChildren().clear();
        diagram.getChildren().addAll(nodes);

        addHierarchicalEdges();

        if (layout.equals("Radial")) {
            diagram.setProperty(CoreOptions.ALGORITHM,
                    "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial");
        } else if (layout.equals("Stress")) {
            diagram.setProperty(CoreOptions.ALGORITHM,
                    "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.stress");
        } else if (layout.equals("Grid Snap")) {
            diagram.setProperty(CoreOptions.ALGORITHM,
                    "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.grid");
        } else if (layout.equals("Tree")) {
            diagram.setProperty(CoreOptions.ALGORITHM,
                    "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.tree");
        } else if (layout.equals("Overlap Removal")) {

        } else if (layout.equals("H-Layouter")) {

        }
    }

    /**
     * 
     * @param parent
     * @return
     */
    private static List<KNode> recursiveTraversal(final KNode parent) {
        List<KNode> copiedChildren = new ArrayList<>();

        for (KNode child : parent.getChildren()) {

            List<KNode> grandChildren = child.getChildren();
            boolean isBlueBox =
                    grandChildren.size() == 1 && !grandChildren.get(0).getChildren().isEmpty();

            // deep search
            if (isBlueBox) {
                for (KNode grandChild : grandChildren) {
                    copiedChildren.addAll(recursiveTraversal(grandChild));
                }
            } else {
                copiedChildren.addAll(recursiveTraversal(child));
            }

            // The child has no children, therefore it is not hierarchical (no copy)
            if (!grandChildren.isEmpty()) {

                // extract/copy content of children
                Copier copier = new Copier();
                KNode copy = (KNode) copier.copy(child);
                copier.copyReferences();

                copiedChildren.add(copy);
                int id = child.hashCode();
                child.setProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_ID, id);
                copy.setProperty(HierarchicalMetaDataProvider.HIERARCHICAL_ID, id);

                parents.put(copy, parent);

                // if it is a blue box reset the pointer to the parent
                if (isBlueBox) {
                    for (Entry<KNode, KNode> savedParent : parents.entrySet()) {
                        if (savedParent.getValue().equals(child.getChildren().get(0))) {
                            savedParent.setValue(copy);
                        }
                    }
                }
                restoreLayout(copy, isBlueBox);

                // keep track of the right parent
                for (Entry<KNode, KNode> savedParent : parents.entrySet()) {
                    if (savedParent.getValue().equals(child)) {
                        savedParent.setValue(copy);
                    }
                }

                // Remove the actions of the inner nodes so they are not expandable
                KRenderingImpl rendering = child.getData(KRenderingImpl.class);
                // TODO throws NullPointer?
                rendering.getActions().clear();

                // TODO remove the actions of the copy, such that the root children are not
                // expandable. If we simply remove the action of the copy, we can minimize the
                // node once but can not expand it anymore...

            }
        }
        return copiedChildren;
    }

    /**
     * 
     * @param node
     */
    private static void restoreLayout(final KNode node, final boolean isBlueBox) {
        List<KNode> children = node.getChildren();
        if (isBlueBox) {
            children = children.get(0).getChildren();
        }

        for (KNode child : children) {

            // delete the content of the original node
            child.getChildren().clear();
            KGraphDataUtil.loadDataElements(child);
            child.setProperty(KlighdProperties.EXPAND, false);

            // if (child.getHeight() == 0) {
            // child.setHeight(CoreOptions.NODE_SIZE_MIN_HEIGHT.getDefault());
            // }
            // if (child.getWidth() == 0) {
            // child.setWidth(CoreOptions.NODE_SIZE_MIN_WIDTH.getDefault());
            // }
        }

        if (node.getChildren().size() > 0) {
            node.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
        }
    }

    /**
     * Add the edges representing the Hierarchical connection.
     */
    private static void addHierarchicalEdges() {
        for (Entry<KNode, KNode> entry : parents.entrySet()) {
            KNode child = entry.getKey();
            KNode parent = entry.getValue();

            // create an edge
            KEdge edge = KGraphUtil.createInitializedEdge();

//            KRendering rendering = edge.getData(KRendering.class);
//            KRendering line = KRenderingFactory.eINSTANCE.createKPolyline();
//            System.out.println(line);
//            System.out.println(line.getStyles());
//            System.out.println("1: " + rendering);
//            KRenderingExtensions ext = new KRenderingExtensions();
//            ext.setLineStyle(rendering, LineStyle.DASH);
//            ext.setProperty(KRenderingExtensions, value)
//            edge.setProperty(Edge, value)
            edge.setSource(parent);
            edge.setTarget(child);

            parent.getOutgoingEdges().add(edge);
        }
    }

}
