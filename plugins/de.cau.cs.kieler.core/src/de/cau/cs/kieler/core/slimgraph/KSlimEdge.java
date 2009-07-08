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
package de.cau.cs.kieler.core.slimgraph;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * An edge in the slim graph structure.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KSlimEdge extends KSlimGraphElement {

	/**
	 * Definition of an edge bend for orthogonal drawing.
	 */
	public static class Bend {
		/** Type of edge bend, from the perspective of the source node */
		public enum Type {
			UNDEFINED, LEFT, RIGHT
		}
		/** the type of edge bend */
		public Type type;
		/** the x coordinate position */
		public float xpos;
		/** the y coordinate position */
		public float ypos;
		/** the index of this bend */
		public int index;
		
		/** the edge associated with this bend */
		private KSlimEdge edge;
		
		/**
		 * Creates an edge bend of given type.
		 * 
		 * @param edge the edge to which the new bend is added
		 * @param type type of edge bend
		 */
		public Bend(KSlimEdge edge, Type type) {
			this.edge = edge;
			this.type = type;
			index = edge.bends.size();
		}
		
		/**
		 * Returns the slim edge associated with this bend.
		 * 
		 * @return the edge
		 */
		public KSlimEdge getEdge() {
			return edge;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return type.toString();
		}
	}
	
	/** source node */
	public KSlimNode source;
	/** target node */
	public KSlimNode target;
	/** left face */
	public KSlimFace leftFace;
	/** right face */
	public KSlimFace rightFace;
	/** the bends of this edge */
	public final List<Bend> bends = new LinkedList<Bend>();
	/** the side on which the edge leaves the source */
	public KSlimNode.Side sourceSide = KSlimNode.Side.UNDEFINED;
	/** the side on which the edge reaches the target */
	public KSlimNode.Side targetSide = KSlimNode.Side.UNDEFINED;
	
	/**
	 * Creates an edge connecting two existing nodes.
	 * 
	 * @param graph the graph to which the new edge shall be added
	 * @param source source node
	 * @param target target node
	 */
	public KSlimEdge(KSlimGraph graph, KSlimNode source, KSlimNode target) {
		graph.edges.add(this);
		this.id = graph.nextEdgeId++;
		this.source = source;
		this.target = target;
	}
	
	/**
	 * Creates an edge connecting two existing nodes, with an object
	 * to be contained.
	 * 
	 * @param graph the graph to which the new edge shall be added
	 * @param source source node
	 * @param target target node
	 * @param obj object to be contained
	 */
	public KSlimEdge(KSlimGraph graph, KSlimNode source, KSlimNode target, Object obj) {
		this(graph, source, target);
		this.object = obj;
	}
	
	/**
	 * Connects this edge with the source and target. New incidence
	 * entries are created for the incidence lists of the source and
	 * the target.
	 */
	public void connectNodes() {
		source.incidence.add(new KSlimNode.IncEntry(this,
				KSlimNode.IncEntry.Type.OUT));
		target.incidence.add(new KSlimNode.IncEntry(this,
				KSlimNode.IncEntry.Type.IN));
	}
	
	/**
	 * Connects this edge with the source and target with given ranks.
	 * New incidence entries are created for the incidence lists of the
	 * source and the target.
	 * 
	 * @param sourceRank rank of the edge at source
	 * @param targetRank rank of the edge at target
	 * @param forwardSelfLoop for self-loops: is the target rank greater
	 *     than the source rank?
	 */
	public void connectNodes(int sourceRank, int targetRank, boolean
			forwardSelfLoop) {
		if (source.id == target.id && (sourceRank < targetRank
				|| (sourceRank == targetRank && forwardSelfLoop)))
			targetRank++;
		source.incidence.add(sourceRank, new KSlimNode.IncEntry(this,
				KSlimNode.IncEntry.Type.OUT));
		target.incidence.add(targetRank, new KSlimNode.IncEntry(this,
				KSlimNode.IncEntry.Type.IN));		
	}
	
	/**
	 * Connects this edge with the source and target respecting the
	 * order of incidence according to the given node sides.
	 * 
	 * @param sourceSide port side at the source node
	 * @param targetSide port side at the target node
	 */
	public void connectNodes(KSlimNode.Side sourceSide, KSlimNode.Side targetSide) {
		this.sourceSide = sourceSide;
		this.targetSide = targetSide;
		ListIterator<KSlimNode.IncEntry> incIter = source.incidence.listIterator();
		while (incIter.hasNext()) {
			KSlimNode.IncEntry nextEntry = incIter.next();
			KSlimNode.Side side = (nextEntry.type == KSlimNode.IncEntry.Type.OUT
					? nextEntry.edge.sourceSide : nextEntry.edge.targetSide);
			if (sourceSide.compareTo(side) <= 0) {
				incIter.previous();
				break;
			}
		}
		incIter.add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.OUT));
		incIter = target.incidence.listIterator();
		while (incIter.hasNext()) {
			KSlimNode.IncEntry nextEntry = incIter.next();
			KSlimNode.Side side = (nextEntry.type == KSlimNode.IncEntry.Type.OUT
					? nextEntry.edge.sourceSide : nextEntry.edge.targetSide);
			if (targetSide.compareTo(side) <= 0) {
				incIter.previous();
				break;
			}
		}
		incIter.add(new KSlimNode.IncEntry(this, KSlimNode.IncEntry.Type.IN));
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.orthogonal.structures.TSMGraphElement#toString()
	 */
	public String toString() {
		String baseString = super.toString();
		if (source != null && target != null)
			return baseString + " " + source.id + ">" + target.id;
		else return baseString;
	}
	
}
