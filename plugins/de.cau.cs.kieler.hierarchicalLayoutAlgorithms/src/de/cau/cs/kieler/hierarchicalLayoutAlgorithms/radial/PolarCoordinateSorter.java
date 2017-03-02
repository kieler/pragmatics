/*******************************************************************************
 * Copyright (c) 2016 Kiel University and others.
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

import org.eclipse.elk.alg.radial.sorting.IRadialSorter;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;

/**
 * The polar coordinate sorter takes a list of nodes and sort them according to
 * their polar coodinates relativ to their parents. Sublist of different parents stay in the same order.
 * 
 * @author Yella Lasch
 *
 */
public class PolarCoordinateSorter implements IRadialSorter {

	@Override
	public List<ElkNode> sort(List<ElkNode> nodes) {
		List<ElkNode> sortedList = new ArrayList<ElkNode>();
		List<ElkNode> nodesWithSameParent = new ArrayList<ElkNode>();
		ElkNode lastParent = null;
		for (ElkNode node : nodes) {
			if (lastParent == null) {
				lastParent = getNodeParent(node);
			}
			if (!lastParent.equals(getNodeParent(node))) {
				sortedList.addAll(
						HierarchicalUtil.sortNode(lastParent, HierarchicalUtil.createPolarComparator(lastParent, 0)));
				lastParent = getNodeParent(node);
			}
			nodesWithSameParent.add(node);
		}
		if (lastParent != null) {
			sortedList.addAll(
					HierarchicalUtil.sortNode(lastParent, HierarchicalUtil.createPolarComparator(lastParent, 0)));
		} else {
			return nodes;
		}
		return sortedList;
	}

	/**
	 * Receive the hierarchical parent of a node.
	 * 
	 * @param node
	 * @return
	 */
	private ElkNode getNodeParent(ElkNode node) {
		ElkEdge incomingEdgeFromParent = Iterables.get(ElkGraphUtil.allIncomingEdges(node), 0);
		ElkNode parent = ElkGraphUtil.connectableShapeToNode(incomingEdgeFromParent.getSources().get(0));
		return parent;
	}
}
