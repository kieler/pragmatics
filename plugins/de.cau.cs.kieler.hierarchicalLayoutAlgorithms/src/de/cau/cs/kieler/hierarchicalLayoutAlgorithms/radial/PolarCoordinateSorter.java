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
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.elk.alg.radial.sorting.IRadialSorter;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;

/**
 * The polar coordinate sorter takes a list of nodes and sort them according to
 * their polar coordinates relative to their parents. Sublists of nodes with the
 * same parent (which are not comparable by coordinates) stay in the same order
 * as in the original list.
 */
public class PolarCoordinateSorter implements IRadialSorter {

	@Override
	public List<ElkNode> sort(List<ElkNode> nodes) {
		List<ElkNode> sortedList = new ArrayList<ElkNode>();
		List<ElkNode> nodesWithSameParent = new ArrayList<ElkNode>();
		ElkNode lastParent = null;
		Comparator<ElkNode> comparatorOfLastParent = null;
		for (ElkNode node : nodes) {
			if (lastParent == null) {
				lastParent = getNodeParent(node);
				comparatorOfLastParent = HierarchicalUtil.createPolarComparator(lastParent, 0);
			}
			if (!lastParent.equals(getNodeParent(node))) {
				sortedList.addAll(HierarchicalUtil.sortNode(lastParent, comparatorOfLastParent));
				lastParent = getNodeParent(node);
				comparatorOfLastParent = HierarchicalUtil.createPolarComparator(lastParent, 0);
			}
			nodesWithSameParent.add(node);
		}
		if (lastParent != null) {
			sortedList.addAll(HierarchicalUtil.sortNode(lastParent, comparatorOfLastParent));
		} else {
			return nodes;
		}
		return sortedList;
	}

	/**
	 * Retrieve the hierarchical parent of a node.
	 * 
	 * @param node
	 * @return The hierarchical parent of the node. 
	 */
	private ElkNode getNodeParent(ElkNode node) {
		ElkEdge incomingEdgeFromParent = Iterables.get(ElkGraphUtil.allIncomingEdges(node), 0);
		ElkNode parent = ElkGraphUtil.connectableShapeToNode(incomingEdgeFromParent.getSources().get(0));
		return parent;
	}
}
