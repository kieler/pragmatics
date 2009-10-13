/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.hierarchical.modules.INodePlacer;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerElement;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;
import de.cau.cs.kieler.klodd.hierarchical.structures.LinearSegment;


/**
 * Node placing algorithm that orders all linear segments and creates an
 * unbalanced placement
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class BasicNodePlacer extends AbstractAlgorithm implements INodePlacer {

	/** factor for the actual distance between nodes */
	private static final float DIST_FACTOR = 1.37f;
	
	/** minimal distance between two nodes or edges in each layer */
	private float minDist;
	/** layout direction for this algorithm instance */
	private LayoutDirection layoutDirection;
	/** array of sorted segments */
	private LinearSegment[] sortedSegments = null;
	/** amount of available spacing for each layer element */
	private Map<LayerElement, Float> spacingMap = new HashMap<LayerElement, Float>();
	/** the last element that was processed in each layer */
	private LayerElement[] lastElements;
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.core.algorithms.AbstractAlgorithm#reset()
	 */
	public void reset() {
		super.reset();
		sortedSegments = null;
		spacingMap.clear();
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.hierarchical.modules.INodePlacer#placeNodes(de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph, float, boolean)
	 */
	public void placeNodes(LayeredGraph layeredGraph, float minDist,
	        boolean balanceOverSize) {
		getMonitor().begin("Basic node placement", 1);
		
		this.minDist = minDist;
		this.layoutDirection = layeredGraph.getLayoutDirection();
		this.lastElements = new LayerElement[layeredGraph.getLayers().size()
		        + layeredGraph.getLayers().get(0).rank];
		
		// sort the linear segments of the layered graph
		sortedSegments = sortLinearSegments(layeredGraph);
		// create an unbalanced placement from the sorted segments
		createUnbalancedPlacement(sortedSegments);
		
		// process external ports
		Layer externalLayer = layeredGraph.getLayers().get(0);
		if (externalLayer.rank == 0) {
			processExternalLayer(externalLayer);
		}
		externalLayer = layeredGraph.getLayers().get(layeredGraph.getLayers().size() - 1);
		if (externalLayer.height == 0) {
			processExternalLayer(externalLayer);
		}
		
		// set the proper crosswise dimension for the whole graph
		for (Layer layer : layeredGraph.getLayers()) {
			layer.crosswiseDim += minDist;
			layeredGraph.crosswiseDim = Math.max(layeredGraph.crosswiseDim,
					layer.crosswiseDim); 
		}
		
		getMonitor().done();
	}
	
	/**
	 * Gets the array of movable linear segments. This excludes the external
	 * ports if their position is to be held fixed.
	 * 
	 * @return movable linear segments
	 */
	public LinearSegment[] getMovableSegments() {
		return sortedSegments;
	}
	
	/**
	 * Gets a map for the amount of spacing available for each layer
	 * element. The last element of each layer is not stored in this map.
	 * 
	 * @return spacing map
	 */
	public Map<LayerElement, Float> getElementSpacing() {
	    return spacingMap;
	}
	
	/**
	 * Sorts the linear segments of the given layered graph by finding
	 * a topological ordering in the corresponding segment ordering graph.
	 * 
	 * @param layeredGraph layered graph to process
	 * @return a sorted array of linear segments
	 */
	@SuppressWarnings("unchecked")
	private LinearSegment[] sortLinearSegments(LayeredGraph layeredGraph) {
		// create and initialize segment ordering graph
		LinearSegment[] linearSegments;
		if (layeredGraph.getExternalPortConstraints()
		        == PortConstraints.FIXED_POS) {
			List<LinearSegment> filteredSegments = new LinkedList<LinearSegment>();
			for (LinearSegment segment : layeredGraph.getLinearSegments()) {
				if (segment.elements.size() == 1) {
					Layer layer = segment.elements.get(0).getLayer();
					if (layer.rank != 0 && layer.height != 0)
						filteredSegments.add(segment);
				}
				else
					filteredSegments.add(segment);
			}
			linearSegments = filteredSegments.toArray(new LinearSegment[0]);
		}
		else
			linearSegments = layeredGraph.getLinearSegments().toArray(new LinearSegment[0]);
		List<LinearSegment>[] outgoing = new List[linearSegments.length];
		int[] incomingCount = new int[linearSegments.length];
		int[] newRanks = new int[linearSegments.length];
		for (int i = 0; i < linearSegments.length; i++) {
			linearSegments[i].rank = i;
			outgoing[i] = new LinkedList<LinearSegment>();
			incomingCount[i] = 0;
		}
		
		// create edges in the segment ordering graph
		for (Layer layer : layeredGraph.getLayers()) {
			if (!(layeredGraph.getExternalPortConstraints()
	                == PortConstraints.FIXED_POS
					&& (layer.rank == 0 || layer.height == 0))) {
				Iterator<LayerElement> elemIter = layer.getElements().iterator();
				LayerElement elem1 = elemIter.next();
				while (elemIter.hasNext()) {
					LayerElement elem2 = elemIter.next();
					outgoing[elem1.linearSegment.rank].add(elem2.linearSegment);
					incomingCount[elem2.linearSegment.rank]++;
					elem1 = elem2;
				}
			}
		}
		
		// find a topological ordering of the segment ordering graph
		int nextRank = 0;
		List<LinearSegment> noIncoming = new LinkedList<LinearSegment>();
		for (int i = 0; i < linearSegments.length; i++) {
			if (incomingCount[i] == 0)
				noIncoming.add(linearSegments[i]);
		}
		while (!noIncoming.isEmpty()) {
			LinearSegment segment = noIncoming.remove(0);
			newRanks[segment.rank] = nextRank++;
			while (!outgoing[segment.rank].isEmpty()) {
				LinearSegment target = outgoing[segment.rank].remove(0);
				incomingCount[target.rank]--;
				if (incomingCount[target.rank] == 0)
					noIncoming.add(target);
			}
		}
		
		// apply the new ordering to the array of linear segments
		for (int i = 0; i < linearSegments.length; i++) {
		    assert outgoing[i].isEmpty();
			linearSegments[i].rank = newRanks[i];
		}
		Arrays.sort(linearSegments);
		return linearSegments;
	}
	
	/**
	 * Creates an unbalanced placement for the input graph.
	 * 
	 * @param sortedSegments array of sorted linear segments
	 */
	private void createUnbalancedPlacement(LinearSegment[] sortedSegments) {
		for (LinearSegment segment : sortedSegments) {
			// determine the leftmost / uppermost placement for the linear segment
			float leftmostPlace = 0.0f;
			for (LayerElement element : segment.elements) {
				leftmostPlace = Math.max(leftmostPlace,
						element.getLayer().crosswiseDim);
			}
			// apply the leftmost / uppermost placement to all elements
			float newPos = leftmostPlace + DIST_FACTOR * minDist;
			for (LayerElement element : segment.elements) {
				Layer layer = element.getLayer();
				if (lastElements[layer.rank] != null)
				    spacingMap.put(lastElements[layer.rank], Float.valueOf(
				            leftmostPlace - layer.crosswiseDim));
				element.setCrosswisePos(newPos, minDist);
				float totalCrosswiseDim = element.getTotalCrosswiseDim(minDist);
				layer.crosswiseDim = newPos + totalCrosswiseDim;
				layer.lengthwiseDim = Math.max(layer.lengthwiseDim,
						layoutDirection == LayoutDirection.VERTICAL
						? element.getRealHeight() : element.getRealWidth());
				lastElements[layer.rank] = element;
			}
		}
	}
	
	/**
	 * Adjusts the size properties of a layer that contains only
	 * external ports.
	 * 
	 * @param layer layer with external ports
	 */
	private void processExternalLayer(Layer layer) {
		LayeredGraph layeredGraph = layer.getLayeredGraph();
		if (layeredGraph.getExternalPortConstraints()
                == PortConstraints.FIXED_POS) {
			// process fixed external layer
			for (LayerElement element : layer.getElements()) {
				KPort port = (KPort)element.getElemObj();
				KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
				PortSide placement = LayoutOptions.getPortSide(portLayout);
				KPoint position = element.getPosition();
				position.setX(portLayout.getXpos());
				position.setY(portLayout.getYpos());
				if (layoutDirection == LayoutDirection.VERTICAL) {
					layer.lengthwiseDim = Math.max(layer.lengthwiseDim,
							element.getRealHeight());
					if (placement != PortSide.EAST
							&& placement != PortSide.WEST)
						layer.crosswiseDim = Math.max(layer.crosswiseDim,
								position.getX() + element.getRealWidth());
					if (placement != PortSide.NORTH
							&& placement != PortSide.SOUTH)
						layeredGraph.lengthwiseDim = Math.max(layeredGraph.lengthwiseDim,
								position.getY());
				}
				else {
					layer.lengthwiseDim = Math.max(layer.lengthwiseDim,
							element.getRealWidth());
					if (placement != PortSide.NORTH
							&& placement != PortSide.SOUTH)
						layer.crosswiseDim = Math.max(layer.crosswiseDim,
								position.getY() + element.getRealHeight());
					if (placement != PortSide.EAST
							&& placement != PortSide.WEST)
						layeredGraph.lengthwiseDim = Math.max(layeredGraph.lengthwiseDim,
								position.getX());
				}
			}
		}
	}

}
