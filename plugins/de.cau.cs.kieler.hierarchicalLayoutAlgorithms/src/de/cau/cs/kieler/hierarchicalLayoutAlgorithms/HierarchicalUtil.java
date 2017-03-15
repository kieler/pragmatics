/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.HashSet;
import java.util.List;

import org.eclipse.elk.alg.radial.RadialUtil;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

/** Util Class that defines often used methods. */
public final class HierarchicalUtil {

	/** Constructor should not be public visible. */
	private HierarchicalUtil() {
		// Do nothing
	}

	/***/
	public static void initializeOriginalNodeMapping(final ElkNode root) {
		List<ElkNode> successors = RadialUtil.getSuccessors(root);

		List<ElkNode> children = root.getChildren();
		for (ElkNode child : children) {
			Integer childID = child.getProperty(HierarchicalMetaDataProvider.NODE_ID);
			if (childID != null) {

				ElkNode removeNode = null;
				for (ElkNode successor : successors) {

					List<ElkNode> grandChildren = successor.getChildren();
					boolean isBlueBox = grandChildren.size() == 1 && !grandChildren.get(0).getChildren().isEmpty();
					Integer successorID;
					if (!isBlueBox) {
						successorID = successor.getProperty(HierarchicalMetaDataProvider.PARENT_ID);
					} else {
						successorID = grandChildren.get(0).getProperty(HierarchicalMetaDataProvider.PARENT_ID);
					}

					// Integer successorID =
					// successor.getProperty(HierarchicalMetaDataProvider.PARENT_ID);
					if (childID.equals(successorID)) {
						successor.setProperty(HierarchicalTreeOptions.ORIGINAL_NODE, child);
						removeNode = successor;
						break;
					}
				}
				if (removeNode != null) {
					initializeOriginalNodeMapping(removeNode);
					successors.remove(removeNode);
				}
			}
		}
	}

	/**
	 * Computes a list of edges that are the hierarchical edges of the graph.
	 * 
	 * @param graph
	 * @return List of Hierarchical Edges.
	 */
	public static HashSet<ElkEdge> getHierarchicalEdges(final ElkNode graph) {
		HashSet<ElkEdge> edges = new HashSet<ElkEdge>();
		for (ElkNode node : graph.getChildren()) {
			for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
				ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
				if (graph.getChildren().contains(target)) {
					edges.add(edge);
				}
			}
		}
		return edges;
	}

}
