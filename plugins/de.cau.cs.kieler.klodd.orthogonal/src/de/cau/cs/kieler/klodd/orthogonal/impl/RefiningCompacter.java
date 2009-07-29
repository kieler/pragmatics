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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimFace;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.klodd.orthogonal.modules.ICompacter;
import de.cau.cs.kieler.klodd.orthogonal.structures.*;

/**
 * Compacter implementation that refines the graph and executes
 * another compacter for refined orthogonal representations. This
 * compacter expects a normalized orthogonal representation as input.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class RefiningCompacter extends AbstractAlgorithm implements ICompacter {
	
	/**
	 * Structure that describes the needed data for addition of an edge
	 * for refinement of the graph.
	 */
	private static class RefinementEdge {
		/** source node for the new edge */
		TSMNode source;
		/** side at the source node for the new edge */
		KSlimNode.Side sourceSide;
		/** edge which is split to create the target node */
		TSMEdge targetEdge;
		/** indicates whether the target is forward for the crossed face */
		boolean targetForward;
		/** side of external frame on which the target edge lies */
		KSlimNode.Side externalSide = KSlimNode.Side.UNDEFINED;
		
		/**
		 * Creates a refinement edge with given values.
		 * 
		 * @param source source node for the new edge
		 * @param sourceSide side at the source node for the new edge
		 * @param targetEdge edge which is split to create the target node
		 * @param targetForward indicates whether the target is forward
		 *     for the crossed face
		 */
		RefinementEdge(TSMNode source, KSlimNode.Side sourceSide,
				TSMEdge targetEdge, boolean targetForward) {
			this.source = source;
			this.sourceSide = sourceSide;
			this.targetEdge = targetEdge;
			this.targetForward = targetForward;
		}
	}
	
	/** the embedded compacter for refined orthogonal representations */
	private ICompacter refinedCompacter;
	
	/** edge for the external refinement frame on north side */
	private TSMEdge northFrame;
	/** edge for the external refinement frame on east side */
	private TSMEdge eastFrame;
	/** edge for the external refinement frame on south side */
	private TSMEdge southFrame;
	/** edge for the external refinement frame on west side */
	private TSMEdge westFrame;
	/** indicates whether an edge connecting the external frame with the
	 *  contained graph has already been inserted */
	private boolean frameConnected = false;
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.core.algorithms.AbstractAlgorithm#reset()
	 */
	public void reset() {
		super.reset();
		frameConnected = false;
	}
	
	/**
	 * Creates a refining compacter based on the given compacter,
	 * which should work on a refined orthogonal representation.
	 * 
	 * @param refinedCompacter compacter that expects a refined
	 *     orthogonal representation
	 */
	public RefiningCompacter(ICompacter refinedCompacter) {
		this.refinedCompacter = refinedCompacter;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.orthogonal.modules.ICompacter#compact(de.cau.cs.kieler.klodd.orthogonal.structures.TSMGraph, float)
	 */
	public void compact(KSlimGraph graph, float minDist) {
		getMonitor().begin("Refined compaction", 1);
		
		// refine the internal faces
		ListIterator<KSlimFace> faceIter = graph.faces.listIterator();
		while (faceIter.hasNext()) {
			KSlimFace nextFace = faceIter.next();
			List<RefinementEdge> refinements = getRefinements(nextFace);
			List<KSlimFace> newFaces = applyRefinements(graph, refinements);
			for (KSlimFace newFace : newFaces)
				faceIter.add(newFace);
		}
		// build the external frame
		KSlimFace oldExternalFace = buildExternalFrame(graph);
		// refine the external face
		List<RefinementEdge> refinements = getRefinements(oldExternalFace);
		List<KSlimFace> newFaces = applyRefinements(graph, refinements);
		graph.faces.addAll(newFaces);
		
		// execute the embedded compacter
		refinedCompacter.reset(getMonitor().subTask(1));
		refinedCompacter.compact(graph, minDist);
		getMonitor().done();
	}

	/**
	 * Creates refinement edges for the given face.
	 * 
	 * @param face face to be processed
	 * @return list of refinement edges
	 */
	private List<RefinementEdge> getRefinements(KSlimFace face) {
		// !! the graph is assumed to be connected here !!
		List<KSlimFace.BorderEntry> border = face.borders.get(0);
		List<RefinementEdge> refinementEdges = new LinkedList<RefinementEdge>();
		
		KSlimFace.BorderEntry currentEntry = border.get(border.size() - 1);
		ListIterator<KSlimFace.BorderEntry> entryIter = border.listIterator();
		while (entryIter.hasNext()) {
			KSlimFace.BorderEntry nextEntry = entryIter.next();
			KSlimNode.Side side1 = currentEntry.secondSide();
			KSlimNode.Side side2 = nextEntry.firstSide();
			if (side1 == side2.left() || side1 == side2) {
				// found a left turn, add refinement edge
				KSlimFace.BorderEntry frontEntry = getFrontEdge(border,
						entryIter.previousIndex() < 1 ? border.size() - 1
						: entryIter.previousIndex() - 1);
				if (frontEntry == null) {
					TSMEdge frontEdge = null;
					switch (side1.opposed()) {
					case NORTH:
						frontEdge = northFrame;
						break;
					case EAST:
						frontEdge = eastFrame;
						break;
					case SOUTH:
						frontEdge = southFrame;
						break;
					case WEST:
						frontEdge = westFrame;
						break;
					}
					RefinementEdge refinementEdge = new RefinementEdge(
							(TSMNode)currentEntry.secondNode(), side1.opposed(),
							frontEdge, true);
					refinementEdge.externalSide = side1.opposed();
					refinementEdges.add(refinementEdge);
				}
				else {
					refinementEdges.add(new RefinementEdge(
							(TSMNode)currentEntry.secondNode(), side1.opposed(),
							(TSMEdge)frontEntry.edge, frontEntry.forward));
				}
			}
			currentEntry = nextEntry;
		}
		
		return refinementEdges;
	}
	
	/**
	 * Determines the front edge for the given position in the border.
	 * 
	 * @param border border in which the front edge shall be found
	 * @param startIndex index of the starting edge in the border
	 * @return border entry containing the front edge, or null if there
	 *     is no front edge
	 */
	private KSlimFace.BorderEntry getFrontEdge(List<KSlimFace.BorderEntry> border,
			int startIndex) {
		ListIterator<KSlimFace.BorderEntry> entryIter = border
				.listIterator(startIndex);
		KSlimFace.BorderEntry currentEntry = entryIter.next();
		int cornerSum = 0;
		while (entryIter.nextIndex() != startIndex) {
			if (!entryIter.hasNext())
				entryIter = border.listIterator();
			KSlimFace.BorderEntry nextEntry = entryIter.next();
			KSlimNode.Side side1 = currentEntry.secondSide();
			KSlimNode.Side side2 = nextEntry.firstSide();
			if (side1 == side2.left())
				cornerSum--;
			else if (side1 == side2.right())
				cornerSum++;
			else if (side1 == side2)
				cornerSum -= 2;
			if (cornerSum == 1)
				return nextEntry;
			currentEntry = nextEntry;
		}
		return null;
	}
	
	/**
	 * Applies a set of refinements to the given graph.
	 * 
	 * @param graph graph to which new nodes and edges are added
	 * @param refinements list of refinement edges
	 * @return list of newly created faces
	 */
	private List<KSlimFace> applyRefinements(KSlimGraph graph,
			List<RefinementEdge> refinements) {
		List<KSlimFace> newFaces = new LinkedList<KSlimFace>();
		for (RefinementEdge refinementEdge : refinements) {
			// look for the right target edge
			while (!containsNode(refinementEdge.targetEdge,
					refinementEdge.targetForward, refinementEdge.source))
				refinementEdge.targetEdge = refinementEdge.targetEdge.nextEdge;
			
			// create new node and insert it into the border
			KSlimNode newNode = new TSMNode(graph, TSMNode.Type.REFINEMENT);
			TSMEdge edge1 = refinementEdge.targetEdge;
			KSlimNode oldNode2;
			TSMEdge edge2;
			if (refinementEdge.targetForward) {
				oldNode2 = edge1.target;
				edge1.target = newNode;
				edge2 = new TSMEdge(graph, newNode, oldNode2, (KEdge)edge1.object);
			}
			else {
				oldNode2 = edge1.source;
				edge1.source = newNode;
				edge2 = new TSMEdge(graph, oldNode2, newNode, (KEdge)edge1.object);
			}
			ListIterator<KSlimNode.IncEntry> oldNode2Iter = oldNode2.getIterator(
					edge1, !refinementEdge.targetForward);
			oldNode2Iter.remove();
			oldNode2Iter.add(new KSlimNode.IncEntry(edge2,
					refinementEdge.targetForward ? KSlimNode.IncEntry.Type.IN
					: KSlimNode.IncEntry.Type.OUT));
			newNode.incidence.add(new KSlimNode.IncEntry(edge1,
					refinementEdge.targetForward ? KSlimNode.IncEntry.Type.IN
							: KSlimNode.IncEntry.Type.OUT));
			newNode.incidence.add(new KSlimNode.IncEntry(edge2,
					refinementEdge.targetForward ? KSlimNode.IncEntry.Type.OUT
							: KSlimNode.IncEntry.Type.IN));
			edge2.sourceSide = edge1.sourceSide;
			edge2.targetSide = edge1.targetSide;
			
			// update links of chains of split edges 
			edge2.nextEdge = edge1.nextEdge;
			if (edge2.nextEdge != null)
				edge2.nextEdge.previousEdge = edge2;
			edge2.previousEdge = edge1;
			edge1.nextEdge = edge2;
			
			// update faces left and right of edge1
			edge2.leftFace = edge1.leftFace;
			edge2.rightFace = edge1.rightFace;
			KSlimFace currentFace = refinementEdge.targetForward
					? edge1.rightFace : edge1.leftFace;
			if (refinementEdge.externalSide == KSlimNode.Side.UNDEFINED
					|| frameConnected) {
				ListIterator<KSlimFace.BorderEntry> currentFaceIter = currentFace 
						.getIterator(edge1, refinementEdge.targetForward);
				currentFaceIter.add(new KSlimFace.BorderEntry(edge2,
						refinementEdge.targetForward));
			}
			KSlimFace opposedFace = refinementEdge.targetForward
					? edge1.leftFace : edge1.rightFace;
			ListIterator<KSlimFace.BorderEntry> opposedFaceIter = opposedFace
					.getIterator(edge1, !refinementEdge.targetForward);
			opposedFaceIter.previous();
			opposedFaceIter.add(new KSlimFace.BorderEntry(edge2,
					!refinementEdge.targetForward));
			
			// insert edge and add new face
			KSlimFace newFace = insertEdge(graph, refinementEdge, currentFace,
					newNode, edge2);
			if (newFace != null)
				newFaces.add(newFace);
		}
		return newFaces;
	}
	
	/**
	 * Creates a new edge and inserts it into the graph, updating the
	 * related faces.
	 * 
	 * @param graph graph to which the new edge is added
	 * @param refinementEdge refinement edge for which the new edge is added
	 * @param face face which is crossed by the new edge
	 * @param targetNode target node of the new edge
	 * @param nextEdge the next edge for the border at the right of the
	 *     new edge
	 * @return a newly created face, or null if none was created
	 */
	private KSlimFace insertEdge(KSlimGraph graph, RefinementEdge refinementEdge,
			KSlimFace face, KSlimNode targetNode, KSlimEdge nextEdge) {
		// add edge to the graph
		KSlimEdge newEdge = new KSlimEdge(graph, refinementEdge.source, targetNode);
		newEdge.connectNodes(refinementEdge.sourceSide,
				refinementEdge.sourceSide.opposed());
		
		if (refinementEdge.externalSide != KSlimNode.Side.UNDEFINED
				&& !frameConnected) {
			// connect the external frame with the contained graph
			ListIterator<KSlimFace.BorderEntry> faceIter = getIteratorFor(face,
					refinementEdge.source, refinementEdge.sourceSide.opposed());
			faceIter.add(new KSlimFace.BorderEntry(newEdge, true));
			faceIter.add(new KSlimFace.BorderEntry(nextEdge, true));
			switch (refinementEdge.externalSide) {
			case NORTH:
				faceIter.add(new KSlimFace.BorderEntry(eastFrame, true));
				faceIter.add(new KSlimFace.BorderEntry(southFrame, true));
				faceIter.add(new KSlimFace.BorderEntry(westFrame, true));
				break;
			case EAST:
				faceIter.add(new KSlimFace.BorderEntry(southFrame, true));
				faceIter.add(new KSlimFace.BorderEntry(westFrame, true));
				faceIter.add(new KSlimFace.BorderEntry(northFrame, true));
				break;
			case SOUTH:
				faceIter.add(new KSlimFace.BorderEntry(westFrame, true));
				faceIter.add(new KSlimFace.BorderEntry(northFrame, true));
				faceIter.add(new KSlimFace.BorderEntry(eastFrame, true));
				break;
			case WEST:
				faceIter.add(new KSlimFace.BorderEntry(northFrame, true));
				faceIter.add(new KSlimFace.BorderEntry(eastFrame, true));
				faceIter.add(new KSlimFace.BorderEntry(southFrame, true));
				break;
			}
			faceIter.add(new KSlimFace.BorderEntry(refinementEdge.targetEdge, true));
			faceIter.add(new KSlimFace.BorderEntry(newEdge, false));
			newEdge.leftFace = face;
			newEdge.rightFace = face;
			frameConnected = true;
			return null;
		}
		else {
			// split the crossed face
			KSlimFace newFace = new KSlimFace(graph, false);
			List<KSlimFace.BorderEntry> newBorder = new LinkedList<KSlimFace.BorderEntry>();
			ListIterator<KSlimFace.BorderEntry> faceIter = getIteratorFor(face,
					refinementEdge.source, refinementEdge.sourceSide.opposed());
			int targetIndex = face.getIterator(refinementEdge.targetEdge,
					refinementEdge.targetForward).nextIndex();
			int nextIndex = faceIter.nextIndex();
			while (nextIndex != targetIndex) {
				if (!faceIter.hasNext()) {
					faceIter = face.borders.get(0).listIterator();
					nextIndex = 0;
				}
				KSlimFace.BorderEntry nextEntry = faceIter.next();
				newBorder.add(new KSlimFace.BorderEntry(nextEntry));
				faceIter.remove();
				if (nextEntry.forward)
					nextEntry.edge.rightFace = newFace;
				else
					nextEntry.edge.leftFace = newFace;
				nextIndex++;
			}
			faceIter.add(new KSlimFace.BorderEntry(newEdge, true));
			newBorder.add(new KSlimFace.BorderEntry(newEdge, false));
			newEdge.leftFace = newFace;
			newEdge.rightFace = face;
			newFace.borders.add(newBorder);
			return newFace;
		}
	}
	
	/**
	 * Gets an iterator over border entries of the given face pointing
	 * at the given node.
	 * 
	 * @param face face for which the iterator shall be created
	 * @param node node that is searched
	 * @param side side of the node at which the preceding edge is incident
	 * @return an iterator pointing at <code>node</code>, or null if the
	 *     node was not found
	 */
	private ListIterator<KSlimFace.BorderEntry> getIteratorFor(KSlimFace face,
			KSlimNode node, KSlimNode.Side side) {
		for (List<KSlimFace.BorderEntry> border : face.borders) {
			ListIterator<KSlimFace.BorderEntry> entryIter = border.listIterator();
			while (entryIter.hasNext()) {
				KSlimFace.BorderEntry nextEntry = entryIter.next();
				if (nextEntry.forward && nextEntry.edge.target.id == node.id
							&& nextEntry.edge.targetSide == side
							|| !nextEntry.forward && nextEntry.edge.source.id
							== node.id && nextEntry.edge.sourceSide == side)
						return entryIter;
			}
		}
		return null;
	}
	
	/**
	 * Checks whether the face bordered by the given edge contains the given
	 * node.
	 * 
	 * @param edge edge on the border
	 * @param forward indicates whether the edge is forward on the border
	 * @param node node to look up
	 * @return true if the corresponding face contains the node
	 */
	private boolean containsNode(KSlimEdge edge, boolean forward, KSlimNode node) {
		KSlimFace face = forward ? edge.rightFace : edge.leftFace;
		for (List<KSlimFace.BorderEntry> border : face.borders) {
			for (KSlimFace.BorderEntry borderEntry : border) {
				if (borderEntry.edge.source.id == node.id
						|| borderEntry.edge.target.id == node.id)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Builds the external frame of the refined graph.
	 * 
	 * @param graph graph that is being refined
	 * @return the old external face of the graph
	 */
	private KSlimFace buildExternalFrame(KSlimGraph graph) {
		KSlimFace oldExternal = graph.externalFace;
		KSlimFace newExternal = new KSlimFace(graph, false);
		List<KSlimFace.BorderEntry> newBorder = new LinkedList<KSlimFace.BorderEntry>();
		newExternal.borders.add(newBorder);
		graph.externalFace = newExternal;
		graph.faces.add(oldExternal);
		KSlimNode neNode = new TSMNode(graph, TSMNode.Type.REFINEMENT);
		KSlimNode seNode = new TSMNode(graph, TSMNode.Type.REFINEMENT);
		KSlimNode swNode = new TSMNode(graph, TSMNode.Type.REFINEMENT);
		KSlimNode nwNode = new TSMNode(graph, TSMNode.Type.REFINEMENT);
		northFrame = new TSMEdge(graph, nwNode, neNode);
		northFrame.connectNodes();
		northFrame.leftFace = newExternal;
		northFrame.rightFace = oldExternal;
		northFrame.sourceSide = KSlimNode.Side.EAST;
		northFrame.targetSide = KSlimNode.Side.WEST;
		eastFrame = new TSMEdge(graph, neNode, seNode);
		eastFrame.connectNodes();
		eastFrame.leftFace = newExternal;
		eastFrame.rightFace = oldExternal;
		eastFrame.sourceSide = KSlimNode.Side.SOUTH;
		eastFrame.targetSide = KSlimNode.Side.NORTH;
		southFrame = new TSMEdge(graph, seNode, swNode);
		southFrame.connectNodes();
		southFrame.leftFace = newExternal;
		southFrame.rightFace = oldExternal;
		southFrame.sourceSide = KSlimNode.Side.WEST;
		southFrame.targetSide = KSlimNode.Side.EAST;
		westFrame = new TSMEdge(graph, swNode, nwNode);
		westFrame.connectNodes();
		westFrame.leftFace = newExternal;
		westFrame.rightFace = oldExternal;
		westFrame.sourceSide = KSlimNode.Side.NORTH;
		westFrame.targetSide = KSlimNode.Side.SOUTH;
		newBorder.add(new KSlimFace.BorderEntry(westFrame, false));
		newBorder.add(new KSlimFace.BorderEntry(southFrame, false));
		newBorder.add(new KSlimFace.BorderEntry(eastFrame, false));
		newBorder.add(new KSlimFace.BorderEntry(northFrame, false));
		return oldExternal;
	}

}
