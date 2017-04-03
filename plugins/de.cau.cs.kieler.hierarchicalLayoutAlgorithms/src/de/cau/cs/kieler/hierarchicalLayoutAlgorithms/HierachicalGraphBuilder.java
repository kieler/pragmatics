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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.service.DiagramLayoutEngine;
import org.eclipse.elk.core.service.DiagramLayoutEngine.Parameters;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.krendering.KLineStyle;
import de.cau.cs.kieler.klighd.krendering.KPolyline;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;
import de.cau.cs.kieler.klighd.krendering.LineStyle;
import de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl;
import de.cau.cs.kieler.klighd.util.KlighdProperties;

/**
 * Modifies the graph such that copies are created that represent the
 * hierarchical nodes and adds edges between them.
 */
public final class HierachicalGraphBuilder {

	/** Constructor should not be public visible. */
	private HierachicalGraphBuilder() {
		// Do nothing.
	}

	/** Maps the copy to it's original node. */
	private static Map<KNode, KNode> parents;

	/**
	 * Transform the graph to have the representation we want.
	 * 
	 * @param diagram
	 *            the method takes a graph as input.
	 * @param layout
	 *            the layout algorithm that should be used for the connection of
	 *            the hierarchical nodes we extracted
	 */
	public static KNode transform(final KNode diagram, final String layout) {
		parents = new HashMap<>();
		KGraphDataUtil.loadDataElements(diagram);

		// put the inner nodes onto the highest hierarchy level
		List<KNode> nodes = recursiveTraversal(diagram);

		diagram.getChildren().clear();
		diagram.getChildren().addAll(nodes);

		// add inter level edges
		addHierarchicalEdges();

		// make sure every node was layouted
		Parameters params = new Parameters();
		DiagramLayoutEngine.invokeLayout(null, diagram, params);

		// set the CoreOptions.Position
		initializePositions(diagram);

		// apply layout
		setLayoutAlgorithm(diagram, layout);
		return diagram;
	}

	/**
	 * Add the layout algorithm to the options
	 * 
	 * @param diagram
	 * @param layoutAlgorithm
	 */
	private static void setLayoutAlgorithm(final KNode diagram, String layoutAlgorithm) {
		if (layoutAlgorithm.equals("Radial")) {
			diagram.setProperty(CoreOptions.ALGORITHM, "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial");
		} else if (layoutAlgorithm.equals("Stress")) {
			diagram.setProperty(CoreOptions.ALGORITHM, "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.stress");
		} else if (layoutAlgorithm.equals("Grid Snap")) {
			diagram.setProperty(CoreOptions.ALGORITHM, "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.grid");
		} else if (layoutAlgorithm.equals("Tree")) {
			diagram.setProperty(CoreOptions.ALGORITHM, "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.tree");
		} else if (layoutAlgorithm.equals("Radial Original")) {
			diagram.setProperty(CoreOptions.ALGORITHM, "org.eclipse.elk.radial");
		} else if (layoutAlgorithm.equals("Elk Layered")) {
			// Standard algorithm is already layered
		}
	}

	/**
	 * Recursively travels through the graph and copies the hierarchical nodes.
	 * 
	 * @param parent
	 * @return
	 */
	private static List<KNode> recursiveTraversal(final KNode parent) {
		List<KNode> copiedChildren = new ArrayList<>();

		for (KNode child : parent.getChildren()) {

			List<KNode> grandChildren = child.getChildren();
			boolean isBlueBox = grandChildren.size() == 1 && !grandChildren.get(0).getChildren().isEmpty();

			// deep search
			if (isBlueBox) {
				for (KNode grandChild : grandChildren) {
					copiedChildren.addAll(recursiveTraversal(grandChild));
				}
			} else {
				copiedChildren.addAll(recursiveTraversal(child));
			}

			// Copy only nodes with more children, otherwise it would not be a
			// hierarchical node
			if (!grandChildren.isEmpty()) {
				KNode copyNode = copyNode(child, isBlueBox);
				copiedChildren.add(copyNode);
				parents.put(copyNode, parent);
			}
		}
		return copiedChildren;
	}

	private static KNode copyNode(KNode child, boolean isBlueBox) {
		// extract/copy content of children
		Copier copier = new Copier();
		KNode copy = (KNode) copier.copy(child);
		copier.copyReferences();

		int id = child.hashCode();
		child.setProperty(HierarchicalMetaDataProvider.NODE_ID, id);
		copy.setProperty(HierarchicalMetaDataProvider.PARENT_ID, id);

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
		if (rendering != null && rendering.getActions() != null) {
			rendering.getActions().clear();
		}

		// TODO remove the actions of the copy, such that the root children are
		// not
		// expandable. If we simply remove the action of the copy, we can
		// minimize the
		// node once but can not expand it anymore...
		return copy;
	}

	/**
	 * Resizes the nodes that have been copied to their non expanded form.
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
		}

		if (node.getChildren().size() > 0) {
			node.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
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

			// Set style for hierarchical edges
			KPolyline line = KRenderingFactory.eINSTANCE.createKPolyline();
			KLineStyle ls = KRenderingFactory.eINSTANCE.createKLineStyle();
			ls.setLineStyle(LineStyle.CUSTOM);
			ls.getDashPattern().add(10.0f);
			ls.getDashPattern().add(10.0f);
			line.getStyles().add(ls);
			edge.getData().add(line);
			edge.setSource(parent);
			edge.setTarget(child);

			parent.getOutgoingEdges().add(edge);
		}
	}

	/**
	 * Initializes positions for the original nodes of the copied hierarchical
	 * nodes, such that the original nodes can be sorted correctly.
	 * 
	 * @param diagram
	 */
	public static void initializePositions(final KNode diagram) {
		List<KNode> children = diagram.getChildren();
		HashMap<Integer, KNode> idMap = new HashMap<Integer, KNode>();

		for (KNode node : children) {
			Integer id = node.getProperty(HierarchicalMetaDataProvider.PARENT_ID);
			idMap.put(id, node);
		}

		for (KNode node : children) {
			List<KNode> grandChildren = node.getChildren();
			boolean isBlueBox = grandChildren.size() == 1 && !grandChildren.get(0).getChildren().isEmpty();
			List<KNode> iteratorList = grandChildren;
			if (isBlueBox) {
				iteratorList = grandChildren.get(0).getChildren();
			}
			for (KNode child : iteratorList) {
				Integer id = child.getProperty(HierarchicalMetaDataProvider.NODE_ID);
				if (id != null) {
					KNode n = idMap.get(id);
					KVector childPosition = new KVector();
					childPosition.x = child.getXpos() + child.getWidth() / 2 - node.getWidth() / 2;
					childPosition.y = child.getYpos() + child.getHeight() / 2 - node.getHeight() / 2;
					n.setProperty(CoreOptions.POSITION, childPosition);
				}
			}
		}
	}

}
