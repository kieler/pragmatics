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
