/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.impl;

import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.hierarchical.modules.ILayerAssigner;
import de.cau.cs.kieler.klodd.hierarchical.structures.*;

/**
 * Layer assigner working with the longest path layering algorithm.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LongestPathLayerAssigner extends AbstractAlgorithm implements
		ILayerAssigner {
	
	/** the layered graph that is created by the layer assigner */
	private LayeredGraph layeredGraph = null;

	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.core.algorithms.AbstractAlgorithm#reset()
	 */
	public void reset() {
		super.reset();
		layeredGraph = null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.hierarchical.modules.ILayerAssigner#assignLayers(de.cau.cs.kieler.core.slimgraph.KSlimGraph, de.cau.cs.kieler.core.kgraph.KNode, boolean)
	 */
	public LayeredGraph assignLayers(KSlimGraph slimGraph, KNode parentNode,
	        boolean balanceOverSize) {
		getMonitor().begin("Longest path layering", 1);
		layeredGraph = new LayeredGraph(parentNode);
		
		// process child nodes
		for (KSlimNode node : slimGraph.nodes) {
			visit(node);
		}
		
		// fill rank information for all layers
		ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
		if (layerIter.hasNext()) {
    		Layer currentLayer = layerIter.next();
    		int rank = currentLayer.rank;
    		if (rank == Layer.UNDEF_RANK) {
    			rank = 1;
    			currentLayer.rank = rank;
    		}
    		while (layerIter.hasNext()) {
    			Layer nextLayer = layerIter.next();
    			nextLayer.rank = ++rank;
    			currentLayer.next = nextLayer;
    			currentLayer = nextLayer;
    		}
		}
		
		getMonitor().done();
		return layeredGraph;
	}
	
	/**
	 * Visit a node: if not already visited, find the longest path
	 * to a sink.
	 * 
	 * @param node node to visit
	 * @return height of the given node in the layered graph
	 */
	private int visit(KSlimNode node) {
		LayerElement layerElement = layeredGraph.getLayerElement(node.object);
		if (layerElement != null) {
			// the node was already visited
			return layerElement.getLayer().height;
		}
		else if (node.object instanceof KPort) {
			KPort port = (KPort)node.object;
			if (KimlLayoutUtil.calcFlow(port) < 0) {
				layeredGraph.putFront(port, 0, node);
				return Layer.UNDEF_HEIGHT;
			}
			else {
				layeredGraph.putBack(port, 0, node);
				return 0;
			}
		}
		else
		{
			int maxHeight = 1;
			for (KSlimNode.IncEntry edgeEntry : node.incidence) {
				if (edgeEntry.type == KSlimNode.IncEntry.Type.OUT) {
					KSlimNode targetNode = edgeEntry.edge.target;
					// do not follow loops over a single node
					if (targetNode.id != node.id) {
						int height = visit(targetNode) + 1;
						maxHeight = Math.max(height, maxHeight);
					}
				}
			}
			layeredGraph.putBack((KGraphElement)node.object,
			        maxHeight, node);
			return maxHeight;
		}
	}

}
