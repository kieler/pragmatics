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
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.math.DoubleMath;

/**
 * Util Class that defines often used methods.
 * 
 * @author dja
 * @author ybl
 *
 */
public class HierarchicalUtil {

	/**
	 * Computes a List of nodes that are the neighbours with outgoing edges.
	 * 
	 * @param node
	 * @return List of neighbors with outgoing edges.
	 */
	public static List<ElkNode> getSuccessor(ElkNode node) {
		List<ElkNode> children = new ArrayList<>();
		for (ElkEdge outgoingEdge : ElkGraphUtil.allOutgoingEdges(node)) {
			ElkNode target = ElkGraphUtil.connectableShapeToNode(outgoingEdge.getTargets().get(0));
			if (!node.getChildren().contains(target)) {
				children.add(target);
			}
		}
		return children;
	}

	/**
	 * Computes the root node of a graph.
	 * 
	 * @param graph
	 * @return root node of graph.
	 */
	public static ElkNode findRoot(ElkNode graph) {
		for (ElkNode child : graph.getChildren()) {
			if (!ElkGraphUtil.allIncomingEdges(child).iterator().hasNext()) {
				return child;
			}
		}
		return null;
	}

	/**
	 * Computes the number of Leafs that a node has.
	 * 
	 * @param node
	 * @return number of leafs.
	 */
	public static int getNumberOfLeafs(ElkNode node) {
		int leafs = 0;
		if (HierarchicalUtil.getSuccessor(node).isEmpty()) {
			return 1;
		} else {
			for (ElkNode child : HierarchicalUtil.getSuccessor(node)) {
				leafs += getNumberOfLeafs(child);
			}
		}
		return leafs;
	}

	/**
	 * Computes a List of Edges that are the Hierarchical edges of the Graph.
	 * 
	 * @param graph
	 * @return List of Hierarchical Edges.
	 */
	public static List<ElkEdge> getHierarchicalEdges(ElkNode graph) {
		List<ElkEdge> edges = new ArrayList<ElkEdge>();
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
	
	/**
	 * Comparator for sorting a node by polar coordinates. Offset is set between
	 * 0 and 2*Pi. 0 starts the sorting in the right middle of a node and sorts
	 * clockwise.
	 * 
	 * @param node
	 * @param offset
	 * @return Comparator for polar sorting.
	 */
	public static Comparator<ElkNode> createPolarComparator(ElkNode node, double offset) {
		Comparator<ElkNode> comparator = new Comparator<ElkNode>() {

			@Override
			public int compare(ElkNode child1, ElkNode child2) {
				double xPos1 = child1.getX() + child1.getWidth() / 2 - node.getWidth()/2;
				double yPos1 = child1.getY() + child1.getHeight() / 2 - node.getHeight()/2;
				double arc1 = Math.atan2(yPos1, xPos1);
				if (arc1 < 0) {
					arc1 += 2 * Math.PI;
				}
				arc1+=offset;
				if (arc1 > 2 * Math.PI) {
						arc1 -= 2 * Math.PI;
				}

				double xPos2 = child2.getX() + child2.getWidth() / 2 - node.getWidth()/2;
				double yPos2 = child2.getY() + child2.getHeight() / 2 - node.getHeight()/2;
				double arc2 = Math.atan2(yPos2, xPos2);
				if (arc2 < 0) {
					arc2 += 2 * Math.PI;
				}
				arc2+=offset;
				if (arc2 > 2 * Math.PI) {
						arc2 -= 2 * Math.PI;
				}

				return DoubleMath.fuzzyCompare(arc1, arc2, 1e-10);
			}
		};
		return comparator;
	}

//	public static List<ElkNode> sortSuccesorsByPolarCoordinate(ElkNode node) {
//		return sortSuccesorsByPolarCoordinate(node,0);
//	}
	
	/**
	 * Sorts the children of a node with help of the comparator.
	 * 
	 * @param node
	 * @param comparator
	 * @return Sorted list of nodes with help of the comparator.
	 */
	public static List<ElkNode> sortNode(ElkNode node, Comparator<ElkNode> comparator) {
		List<ElkNode> successors = HierarchicalUtil.getSuccessor(node);

		if (successors.size() > 1) {
			List<ElkNode> children = new ArrayList<>();
			List<ElkNode> rootChildren = node.getChildren();
			boolean isBlueBox = rootChildren.size() == 1 && !rootChildren.get(0).getChildren().isEmpty();
			// blue boxing
			if (!isBlueBox) {
				children.addAll(rootChildren);
			} else {
				children.addAll(rootChildren.get(0).getChildren());
			}
			List<ElkNode> sortedSuccessors = new ArrayList<>();

			// sort children
//			Comparator<ElkNode> comparator = new Comparator<ElkNode>() {
//
//				@Override
//				public int compare(ElkNode child1, ElkNode child2) {
//					double xPos1 = child1.getX() + child1.getWidth() / 2 - node.getWidth()/2;
//					double yPos1 = child1.getY() + child1.getHeight() / 2 - node.getHeight()/2;
//					double arc1 = Math.atan2(yPos1, xPos1);
//					if (arc1 < 0) {
//						arc1 += 2 * Math.PI;
//					}
//					arc1+=offset;
//
//					double xPos2 = child2.getX() + child2.getWidth() / 2 - node.getWidth()/2;
//					double yPos2 = child2.getY() + child2.getHeight() / 2 - node.getHeight()/2;
//					double arc2 = Math.atan2(yPos2, xPos2);
//					if (arc2 < 0) {
//						arc2 += 2 * Math.PI;
//					}
//					arc2+=offset;
//
//					return DoubleMath.fuzzyCompare(arc1, arc2, 1e-10);
//				}
//			};
			children.sort(comparator);

			// map child to its successor
			for (ElkNode child : children) {
				Integer childID = child.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);
				if (childID != null) {

					ElkNode removeNode = null;
					for (ElkNode successor : successors) {
						Integer successorID = successor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);
						if (childID.equals(successorID)) {
							sortedSuccessors.add(successor);
							removeNode = successor;
							break;
						}
					}
					successors.remove(removeNode);
				}
			}

			return sortedSuccessors;
		} else {
			return successors;
		}

	}

	/**
	 * Retrieve the original node of a copy node.
	 * 
	 * @param parent
	 * @param successor
	 * @return
	 */
	public static ElkNode getOriginalNode(ElkNode parent, ElkNode successor) {
		Integer successorID = successor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);

		for (ElkNode child : parent.getChildren()) {
			Integer childID = child.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);
			if (successorID.equals(childID)) {
				return child;
			}
		}
		return null;
	}

	/**
	 * Calculates the distance of the given node to the root.
	 * 
	 * @param node
	 * @param root
	 * @return Distance of node to root.
	 */
	public static int getDepths(ElkNode node, ElkNode root) {
		int depth = 0;
		ElkNode parent = null;
		if (node != root) {
			while (parent != root) {
				for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
					ElkNode source = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
					if (!node.getChildren().contains(source)) {
						parent = source;
					}
				}
				node = parent;
				depth++;
			}
		}
		return depth;
	}

	/**
	 * Search for the biggest diameter of all nodes.
	 * 
	 * @param graph
	 * @return
	 */
	public static double findBiggestNodeInGraph(ElkNode graph) {
		double biggestChildSize = 0;

		for (ElkNode child : graph.getChildren()) {

			double width = child.getWidth();
			double height = child.getHeight();
			double diameter = Math.sqrt(width * width + height * height);

			if (biggestChildSize < diameter) {
				biggestChildSize = diameter;
			}

			double biggestChild = findBiggestNodeInGraph(child);
			if (biggestChild > biggestChildSize) {
				biggestChildSize = biggestChild;
			}
		}
		return biggestChildSize;
	}

	/**
	 * Calculate the size of the graph, such that it will be drawn properly on
	 * the display.
	 * 
	 * @param layoutGraph
	 */
	public static void postProcess(ElkNode layoutGraph) {
		// calculate min size
		double minHeight = 0;
		double minXPos = 0;
		for (ElkNode node : layoutGraph.getChildren()) {
			minHeight = Math.min(minHeight, node.getY());
			minXPos = Math.min(minXPos, node.getX());
		}

		// shift graph
		for (ElkNode node : layoutGraph.getChildren()) {
			node.setX(node.getX() - minXPos);
			node.setY(node.getY() - minHeight);
		}

		// calculate graph size
		double height = 0;
		double width = 0;
		for (ElkNode node : layoutGraph.getChildren()) {
			height = Math.max(height, node.getY() + node.getHeight());
			width = Math.max(width, node.getX() + node.getWidth());
		}
		layoutGraph.setHeight(height + 100);
		layoutGraph.setWidth(width + 100);
	}

}
