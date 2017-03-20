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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

/** Util Class that defines often used methods. */
public final class HierarchicalUtil {

	/** Constructor should not be public visible. */
	private HierarchicalUtil() {
		// Do nothing.
	}

	/**
	 * Initializes positions for the original nodes of the copied hierarchical
	 * nodes, such that the original nodes can be sorted correctly.
	 * 
	 * @param layoutGraph
	 */
	public static void initializePositions(final ElkNode layoutGraph) {
		List<ElkNode> children = layoutGraph.getChildren();
		HashMap<Integer, ElkNode> idMap = new HashMap<Integer, ElkNode>();

		for (ElkNode node : children) {
			Integer id = node.getProperty(HierarchicalMetaDataProvider.PARENT_ID);
			idMap.put(id, node);
		}

		for (ElkNode node : children) {
			List<ElkNode> grandChildren = node.getChildren();
			boolean isBlueBox = grandChildren.size() == 1 && !grandChildren.get(0).getChildren().isEmpty();
			List<ElkNode> iteratorList = grandChildren;
			if (isBlueBox) {
				iteratorList = grandChildren.get(0).getChildren();
			}
			for (ElkNode child : iteratorList) {
				Integer id = child.getProperty(HierarchicalMetaDataProvider.NODE_ID);
				if (id != null) {
					ElkNode n = idMap.get(id);
					KVector childPosition = new KVector();
					childPosition.x = child.getX() + child.getWidth() / 2 - node.getWidth() / 2;
					childPosition.y = child.getY() + child.getHeight() / 2 - node.getHeight() / 2;
					n.setProperty(CoreOptions.POSITION, childPosition);
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
