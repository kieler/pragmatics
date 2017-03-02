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

import java.util.List;

import org.eclipse.elk.alg.radial.sorting.IRadialSorter;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;

/**
 * @author Yella Lasch
 *
 */
public class PolarCoordinateSorter implements IRadialSorter {

	@Override
	public List<ElkNode> sort(List<ElkNode> nodes) {
		if (!nodes.isEmpty()) {
			ElkNode firstNode = nodes.get(0);
			ElkEdge incomingEdgeFromParent = Iterables.get(ElkGraphUtil.allIncomingEdges(firstNode), 0);
			ElkNode parent = ElkGraphUtil.connectableShapeToNode(incomingEdgeFromParent.getSources().get(0));
			List<ElkNode> sortedSuccessor =  HierarchicalUtil.sortNode(parent, HierarchicalUtil.createPolarComparator(parent, 0));
			return sortedSuccessor;
		} else {
			return nodes;
		}
	}
}
