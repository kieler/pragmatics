/*******************************************************************************
 * Copyright (c) 2017 Kiel University and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Kiel University - initial API and implementation
 *******************************************************************************/
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.alg.radial.RadialMetaDataProvider;
import org.eclipse.elk.alg.radial.RadialUtil;
import org.eclipse.elk.alg.radial.edgeRouting.IRadialEdgeRouter;
import org.eclipse.elk.alg.radial.edgeRouting.StraightLineEdgeRouter;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalMetaDataProvider;

/**
 * An edge router implementing the interface of the elk radial layouter for edge routing. It draws the explosion lines
 * of hierarchical edges.
 */
public class ExplosionLineRouter implements IRadialEdgeRouter {
    @Override
    public void routeEdges(final ElkNode root) {
        StraightLineEdgeRouter straightLineRouter = new StraightLineEdgeRouter();
        straightLineRouter.routeEdges(root);

        bendEdgesToHierarchicalEdges(root);
    }

    /**
     * Sets the source for the edge at the corresponding child in the source node.
     * 
     * @param root
     */
    private void bendEdgesToHierarchicalEdges(final ElkNode root) {
        List<ElkNode> copiedChildren = new ArrayList<>();
        List<ElkNode> children = root.getChildren();
        boolean isBlueBox = children.size() == 1 && !children.get(0).getChildren().isEmpty();
        // blue boxing
        if (!isBlueBox) {
            copiedChildren.addAll(children);
        } else {
            copiedChildren.addAll(children.get(0).getChildren());
        }

        // take a look at all the successors of the node
        for (ElkNode successor : RadialUtil.getSuccessors(root)) {
            Integer id = successor.getProperty(HierarchicalMetaDataProvider.PARENT_ID);

            // look at the incoming edges
            for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(successor)) {
                // only the edge coming from the original node shall be
                // considered
                if (edge.getSources().get(0) == root || root.getChildren().contains(edge.getSources().get(0))) {
                    ElkNode childFound = null;
                    for (ElkNode child : copiedChildren) {
                        Integer idParent = child.getProperty(HierarchicalMetaDataProvider.NODE_ID);

                        if (idParent != null && id.equals(idParent)) {
                            childFound = child;
                            double xPos = root.getX() + child.getX() + child.getWidth() / 2;
                            double yPos = root.getY() + child.getY() + child.getHeight() / 2;

                            ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
                            section.setStartLocation(xPos, yPos);

                        }
                    }
                    copiedChildren.remove(childFound);
//                    clippTargetOfEdge(edge);

                }
            }

            bendEdgesToHierarchicalEdges(successor);
        }

    }

}
