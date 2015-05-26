/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/* 
 * 
 * 
 * changes in:
 * IntermediateProcessorStrategy
 * GraphConfigurator
 * Properties
 */

/**
 * @author dag
 *
 */
public class OneDCompactor implements ILayoutProcessor {

	private List<Pair<LGraphElement, Rectangle2D.Double>> boxes = new ArrayList<Pair<LGraphElement, Rectangle2D.Double>>();

	/**
	 * {@inheritDoc}
	 */
	public void process(final LGraph layeredGraph,
			final IKielerProgressMonitor progressMonitor) {

		progressMonitor.begin("1 D compacting", 1);

		boxes.clear();
		
		for (Layer layer : layeredGraph) {
			for (LNode node : layer) {
				// add all kinds of nodes
				Rectangle2D.Double r = new Rectangle2D.Double(
						node.getPosition().x, node.getPosition().y,
						node.getSize().x, node.getSize().y);

				boxes.add(new Pair<LGraphElement, Rectangle2D.Double>(node, r));
				
				//add vertical edge segments
				for (LEdge edge : node.getOutgoingEdges()) {
					if (edge.getBendPoints().iterator().hasNext()) {
						
						Iterator<KVector> bends = edge.getBendPoints().iterator();
						KVector bend1 = bends.next();
						KVector bend2 = bends.next();
						
						double x,y,h;
						if (bend1.y < bend2.y) {
							x = bend1.x;
							y = bend1.y;
							h = bend2.y-y;
						} else {
							x = bend2.x;
							y = bend2.y;
							h = bend1.y-y;
						}
						
						Rectangle2D.Double rEdge = new Rectangle2D.Double(x, y, 0, h);
						
						boxes.add(new Pair<LGraphElement, Rectangle2D.Double>(edge, rEdge));
					}
				}
			}
		}


		drawDebugView(boxes, 800, 600, layeredGraph); // FIXME determine size

		progressMonitor.done();

	}

	private void drawDebugView(
			final List<Pair<LGraphElement, Rectangle2D.Double>> boxes,
			final int width, final int height, final LGraph layeredGraph) {

		double xMin = 0, xMax = 0, yMin = 0, yMax = 0;
		
		

		List<Rectangle> regularNodes = new ArrayList<Rectangle>();
		List<Rectangle> longEdgeNodes = new ArrayList<Rectangle>();
		List<Rectangle> vertEdgeSeg = new ArrayList<Rectangle>();
		

		/*for (Pair<LGraphElement, Rectangle2D.Double> box : boxes) {
			if (box.getSecond().getMinX() < xMin)
				xMin = box.getSecond().getMinX();
			if (box.getSecond().getMaxX() > xMax)
				xMax = box.getSecond().getMaxX();
			if (box.getSecond().getMinY() < yMin)
				yMin = box.getSecond().getMinY();
			if (box.getSecond().getMaxY() > yMax)
				yMax = box.getSecond().getMaxY();
		}
		
		System.out.println(xMin+" "+xMax+" - "+yMin+" "+yMax+" ^= "+layeredGraph.getSize().x+" "+layeredGraph.getSize().y);
*/
		xMax = layeredGraph.getSize().x;
		yMax = layeredGraph.getSize().y;
		
		double f = width / (xMax - xMin); //TODO simplify

		double xOffset = -xMin, yOffset = -yMin;

		for (Pair<LGraphElement, Rectangle2D.Double> box : boxes) {

			if (box.getFirst().getClass() == LNode.class) {

				LNode node = (LNode) box.getFirst();

				if (node.getNodeType().equals(NodeType.NORMAL)) {

					regularNodes.add(new Rectangle((int) Math.round(box
							.getSecond().x * f + xOffset), (int) Math.round(box
							.getSecond().y * f + yOffset), (int) Math.round(box
							.getSecond().width * f), (int) Math.round(box
							.getSecond().height * f)));
				}

				if (node.getNodeType().equals(NodeType.LONG_EDGE)) {

					longEdgeNodes.add(new Rectangle((int) Math.round(box
							.getSecond().x * f + xOffset - 1), (int) Math
							.round(box.getSecond().y * f + yOffset - 1), 2, 2));
				}
			} else { // probably an edge
				
				vertEdgeSeg.add(new Rectangle((int) Math.round(box
						.getSecond().x * f + xOffset), (int) Math.round(box
						.getSecond().y * f + yOffset), (int) Math.round(box
						.getSecond().width * f), (int) Math.round(box
						.getSecond().height * f)));
			}
		}

		new DebugFrame(width, height, layeredGraph, regularNodes, longEdgeNodes, vertEdgeSeg);

	}

	private class DebugFrame extends JFrame {

		private List<Rectangle> regularNodes = new ArrayList<Rectangle>();
		private List<Rectangle> longEdgeNodes = new ArrayList<Rectangle>();
		private List<Rectangle> vertEdgeSeg = new ArrayList<Rectangle>();

		private int BORDER_SPACING, OBJECT_SPACING, EDGE_SPACING;

		public DebugFrame(final int width, final int height,
				final LGraph layeredGraph, final List<Rectangle> regularNodes,
				final List<Rectangle> longEdgeNodes, final List<Rectangle> vertEdgeSeg) {

			super("Debug Frame");

			this.regularNodes = regularNodes;
			this.longEdgeNodes = longEdgeNodes;
			this.vertEdgeSeg = vertEdgeSeg;

			this.BORDER_SPACING = (int) Math.round(layeredGraph
					.getProperty(InternalProperties.BORDER_SPACING));
			this.OBJECT_SPACING = (int) Math
					.round(layeredGraph.getProperty(InternalProperties.SPACING)
							* layeredGraph
									.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR));
			this.EDGE_SPACING = (int) Math
					.round(layeredGraph.getProperty(InternalProperties.SPACING)
							* layeredGraph
									.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR)
							* layeredGraph
									.getProperty(Properties.EDGE_SPACING_FACTOR));
			

			setContentPane(new DrawPane());

			setSize(width + 2 * BORDER_SPACING, height + 2 * BORDER_SPACING);

			setVisible(true);
		}

		private class DrawPane extends JPanel {
			/**
			 * whatever
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				
				g.setColor(Color.green);
				for (Rectangle rectangle : regularNodes) {
					g.drawRect(rectangle.x + BORDER_SPACING, rectangle.y
							+ BORDER_SPACING, rectangle.width + OBJECT_SPACING, rectangle.height + OBJECT_SPACING);
				}
				
				g.setColor(Color.yellow);
				for (Rectangle rectangle : vertEdgeSeg) {
					g.drawRect(rectangle.x + BORDER_SPACING - EDGE_SPACING, rectangle.y
							+ BORDER_SPACING - EDGE_SPACING, rectangle.width + 2 * EDGE_SPACING, rectangle.height + 2 * EDGE_SPACING);
				}
				
				
				g.setColor(Color.black);
				for (Rectangle rectangle : regularNodes) {
					g.drawRect(rectangle.x + BORDER_SPACING, rectangle.y
							+ BORDER_SPACING, rectangle.width, rectangle.height);
				}

				
				g.setColor(Color.red);
				for (Rectangle rectangle : longEdgeNodes) {
					g.drawRect(rectangle.x + BORDER_SPACING, rectangle.y
							+ BORDER_SPACING, rectangle.width, rectangle.height);
				}
				
				
				g.setColor(Color.blue);
				for (Rectangle rectangle : vertEdgeSeg) {
					g.drawRect(rectangle.x + BORDER_SPACING, rectangle.y
							+ BORDER_SPACING, rectangle.width, rectangle.height);
				}


			}
		}

	}
}
