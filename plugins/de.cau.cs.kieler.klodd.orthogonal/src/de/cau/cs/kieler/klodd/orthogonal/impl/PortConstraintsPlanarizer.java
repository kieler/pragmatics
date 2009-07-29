/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.orthogonal.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.orthogonal.impl.ec.EmbeddingConstraint;
import de.cau.cs.kieler.klodd.orthogonal.modules.IPlanarizer;
import de.cau.cs.kieler.klodd.orthogonal.structures.*;


/**
 * Planarizer implementation that uses an EC planarizer to handle port
 * constraints.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class PortConstraintsPlanarizer extends AbstractAlgorithm implements
		IPlanarizer {

	/** the embedding constraints planarizer */
	private IPlanarizer ecPlanarizer;
	
	/**
	 * Creates a port constraints planarizer with a given embedding
	 * constraints planarizer.
	 * 
	 * @param ecPlanarizer EC planarizer
	 */
	public PortConstraintsPlanarizer(IPlanarizer ecPlanarizer) {
		this.ecPlanarizer = ecPlanarizer;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.orthogonal.modules.IPlanarizer#planarize(de.cau.cs.kieler.klodd.orthogonal.structures.TSMGraph)
	 */
	public void planarize(KSlimGraph graph) {
		getMonitor().begin("Port constraints planarization", 1);
		// create constraints for the input graph
		createConstraints(graph);
		
		// planarize the input graph with embedding constraints
		ecPlanarizer.reset(getMonitor().subTask(1));
		ecPlanarizer.planarize(graph);
		getMonitor().done();
	}
	
	/**
	 * Creates embedding constraints for all nodes of a graph.
	 * 
	 * @param graph for which constraints shall be created
	 */
	private void createConstraints(KSlimGraph graph) {
		for (KSlimNode node : graph.nodes) {
			KNode layoutNode = (KNode)node.object;
			KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
			PortConstraints portConstraints = LayoutOptions.getPortConstraints(nodeLayout);
			TSMNode tsmNode = (TSMNode)node;
			if (!layoutNode.getPorts().isEmpty()) {
				if (portConstraints == PortConstraints.FIXED_POS
				        || portConstraints == PortConstraints.FIXED_ORDER) {
					// create port constraints 
					KPort[] sortedPorts = layoutNode.getPorts().toArray(new KPort[0]);
					Arrays.sort(sortedPorts, new Comparator<KPort>() {
                        public int compare(KPort port1, KPort port2) {
                            KShapeLayout layout1 = KimlLayoutUtil.getShapeLayout(port1);
                            KShapeLayout layout2 = KimlLayoutUtil.getShapeLayout(port2);
                            int rank1 = LayoutOptions.getPortRank(layout1);
                            int rank2 = LayoutOptions.getPortRank(layout2);
                            return rank1 - rank2;
                        }
					});
					EmbeddingConstraint portConstraint = new EmbeddingConstraint(
							EmbeddingConstraint.Type.ORIENTED, null, layoutNode);
					for (KPort port : sortedPorts) {
						EmbeddingConstraint constraint = createConstraintFor(port,
								portConstraint, tsmNode);
						if (constraint != null)
							portConstraint.children.add(constraint);
					}
					if (!portConstraint.children.isEmpty())
						tsmNode.embeddingConstraint = portConstraint;
				}
				else {
					// create side constraints
					EmbeddingConstraint sideConstraint = new EmbeddingConstraint(
							EmbeddingConstraint.Type.ORIENTED, null, layoutNode);
					EmbeddingConstraint northConstraint = null, eastConstraint = null,
							southConstraint = null, westConstraint = null;
					for (KPort port : layoutNode.getPorts()) {
						EmbeddingConstraint constraint = createConstraintFor(port,
								null, tsmNode);
						if (constraint != null) {
							switch (LayoutOptions.getPortSide(
							        KimlLayoutUtil.getShapeLayout(port))) {
							case NORTH:
								if (northConstraint == null)
									northConstraint = new EmbeddingConstraint(
											EmbeddingConstraint.Type.GROUPING,
											sideConstraint, null);
								constraint.parent = northConstraint;
								northConstraint.children.add(constraint);
								break;
							case EAST:
								if (eastConstraint == null)
									eastConstraint = new EmbeddingConstraint(
											EmbeddingConstraint.Type.GROUPING,
											sideConstraint, null);
								constraint.parent = eastConstraint;
								eastConstraint.children.add(constraint);
								break;
							case SOUTH:
								if (southConstraint == null)
									southConstraint = new EmbeddingConstraint(
											EmbeddingConstraint.Type.GROUPING,
											sideConstraint, null);
								constraint.parent = southConstraint;
								southConstraint.children.add(constraint);
								break;
							case WEST:
								if (westConstraint == null)
									westConstraint = new EmbeddingConstraint(
											EmbeddingConstraint.Type.GROUPING,
											sideConstraint, null);
								constraint.parent = westConstraint;
								westConstraint.children.add(constraint);
								break;
							}
						}
					}
					if (northConstraint != null)
						sideConstraint.children.add(northConstraint);
					if (eastConstraint != null)
						sideConstraint.children.add(eastConstraint);
					if (southConstraint != null)
						sideConstraint.children.add(southConstraint);
					if (westConstraint != null)
						sideConstraint.children.add(westConstraint);
					if (!sideConstraint.children.isEmpty())
						tsmNode.embeddingConstraint = sideConstraint;
				}
			}
		}
	}
	
	/**
	 * Creates an embedding constraint for a given port.
	 * 
	 * @param port port to process
	 * @param parent constraint for new constraints, or null if not specified
	 * @param node the node that is currently processed
	 * @return a constraint for the given port, or null if the port has
	 *     no incoming or outgoing edges
	 */
	private EmbeddingConstraint createConstraintFor(KPort port,
			EmbeddingConstraint parent, TSMNode node) {
		// find edges connected with the given port
		List<TSMEdge> portEdges = new LinkedList<TSMEdge>();
		for (KSlimNode.IncEntry edgeEntry : node.incidence) {
			TSMEdge tsmEdge = (TSMEdge)edgeEntry.edge;
			KEdge layoutEdge = (KEdge)tsmEdge.object;
			if (layoutEdge.getSourcePort() == port
					|| layoutEdge.getTargetPort() == port)
				portEdges.add(tsmEdge);
		}
		if (!portEdges.isEmpty()) {
			EmbeddingConstraint groupConstraint = new EmbeddingConstraint(
					EmbeddingConstraint.Type.GROUPING, parent, port);
			for (TSMEdge edge : portEdges) {
			    KEdge layoutEdge = (KEdge)edge.object;
				EmbeddingConstraint.Type constraintType = layoutEdge.getSourcePort()
						== port ? EmbeddingConstraint.Type.OUT_EDGE
						: EmbeddingConstraint.Type.IN_EDGE;
				EmbeddingConstraint edgeConstraint = new EmbeddingConstraint(
						constraintType, groupConstraint, edge);
				groupConstraint.children.add(edgeConstraint);
			}
			return groupConstraint;
		}
		else
			return null;
	}

}
