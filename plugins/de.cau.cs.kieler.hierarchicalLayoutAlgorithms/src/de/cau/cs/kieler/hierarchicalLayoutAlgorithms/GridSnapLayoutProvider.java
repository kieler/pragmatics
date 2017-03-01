package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.force.ForceLayoutProvider;
import org.eclipse.elk.alg.force.options.ForceOptions;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

public class GridSnapLayoutProvider extends AbstractLayoutProvider {

	/**
	 * Our representation of infinity, allow to add two of them without getting
	 * an int overflow.
	 */
	private static final double INFINITY = Double.MAX_VALUE;
	// TODO make as option
	private static final double SPACING = 100.0;
	private static double width;
	private static double height;
	/** */
	private static List<ElkEdge> edges = new ArrayList<ElkEdge>();
	private static List<ElkNode> children = new ArrayList<ElkNode>();
	private static double gridwidth;
	private static double gridheight;
	private static List<Point2D.Double> availablePositions = new ArrayList<Point2D.Double>();

	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Grid Snap Layouter", 1);
		
		BasicProgressMonitor forceMonitor = new BasicProgressMonitor();
		HierarchicalStressLayoutProvider stress = new HierarchicalStressLayoutProvider();
		stress.layout(layoutGraph, forceMonitor);

		minimizingWhitespaceGrid(layoutGraph);

		HierarchicalEdgeRouting.drawExplosionLines(HierarchicalUtil.findRoot(layoutGraph));

		progressMonitor.done();
	}

	/**
	 * 
	 * @param diagram
	 */
	private static void minimizingWhitespaceGrid(ElkNode diagram) {
		edges.clear();
		children.clear();
		width = 0.0f;
		height = 0.0f;
		availablePositions.clear();

		children.addAll(diagram.getChildren());

		// Initialize Grid
		int gridNodesSize = children.size();
		int gridSize = (int) Math.ceil(Math.sqrt(gridNodesSize));
		// System.out.println("Nodes: " + gridNodesSize);
		// System.out.println("Size: " + gridSize);

		Map<Point2D.Double, Integer> gridNodePosition = new HashMap<Point2D.Double, Integer>();
		int xPos = 0;
		int yPos = 0;
		for (int i = 0; i < gridSize; i++) {
			xPos = 0;
			for (int j = 0; j < gridSize; j++) {
				Point2D.Double point = new Point2D.Double(xPos, yPos);
				gridNodePosition.put(point, i * gridSize + j);
				availablePositions.add(point);
				xPos += 50;
			}
			yPos += 50;
		}

//		calculate ralative closest grid position and check if available
		double diagramwidth = diagram.getWidth();
		double diagramheight = diagram.getHeight();
		gridwidth = xPos - 50;
		gridheight = yPos - 50;
		Map<Integer, ElkNode> gridNodeMap = new HashMap<Integer, ElkNode>();

		for (ElkNode node : children) {
			for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
				ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
				if (children.contains(target)) {
					edges.add(edge);
				}
			}

//			System.out.println("xpos: " + nodeLayout.getXpos());
//			System.out.println("width: " + diagramwidth);
//			System.out.println("gridwidth: " + gridwidth);
			double gridXpos = ((node.getX() + node.getWidth() / 2) / diagramwidth) * gridwidth;
			double gridYpos = ((node.getY() + node.getHeight() / 2) / diagramheight) * gridheight;
			// TODO Sort x- and y-axis first
//			System.out.println("gridpos: " + gridXpos);
//			System.out.println(round(gridXpos, gridwidth));
			Point2D point = nearestAvailablePosition(
					new Point2D.Double(round(gridXpos, gridwidth), round(gridYpos, gridheight)));
			int mapPosition = gridNodePosition.get(point);
//			System.out.println(mapPosition);
			gridNodeMap.put(mapPosition, node);
		}
//		System.out.println("Hallo");

		double newWidth = 20;
		double newHeight = 20;
		Map<Integer, Double> rowwidth = new HashMap<Integer, Double>();
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (!(gridNodeMap.get(i * gridSize + j) == null)) {
					ElkNode node = gridNodeMap.get(i * gridSize + j);
					if (rowwidth.containsKey(j)) {
						if (rowwidth.get(j) < node.getWidth()) {
							rowwidth.put(j, node.getWidth());
						}
					} else {
						rowwidth.put(j, node.getWidth());
					}
				}
			}
		}

		double nextXpos = 20;
		double nextYpos = 20;
		double rowheight = 20;
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (!(gridNodeMap.get(i * gridSize + j) == null)) {
					ElkNode node = gridNodeMap.get(i * gridSize + j);
					if (rowheight < node.getHeight()) {
						rowheight = node.getHeight();
					}
					node.setX(nextXpos);
					node.setY(nextYpos);
					if (newWidth < node.getX() + node.getWidth()) {
						newWidth = node.getX() + node.getWidth();
					}
					if (newHeight < node.getY() + node.getHeight()) {
						newHeight = node.getY() + node.getHeight();
					}
					nextXpos = node.getX() + rowwidth.get(j) + SPACING;
				} else {
					nextXpos += rowwidth.get(j) + SPACING;
				}
			}
			nextYpos += rowheight + SPACING;
			rowheight = 0;
			nextXpos = 20;
		}

		diagram.setWidth(newWidth + 20);
		diagram.setHeight(newHeight + 20);
	}

	/**
	 * 
	 * @param point
	 * @return
	 */
	private static Point2D nearestAvailablePosition(Point2D point) {
		Point2D.Double position = new Point2D.Double(0, 0);
		double smallestDistance = INFINITY;
		double currentDistance;
		for (Point2D.Double p : availablePositions) {
			currentDistance = point.distance(p);
			if (currentDistance < smallestDistance) {
				smallestDistance = currentDistance;
				position = p;
			}
		}
		availablePositions.remove(position);

		return position;
	}

	/**
	 * 
	 * @param x
	 * @param maxvalue
	 * @return
	 */
	private static double round(double x, double maxvalue) {
		double result;
		if ((x % 50) > 50 / 2) {
			result = x + 50 - x % 50;
		} else {
			result = x - x % 50;
		}

		if (result > maxvalue) {
			result = maxvalue;
		}

		return result;
	}

	/**
	 * 
	 * @param diagram
	 */
	private static void simpleGrid(ElkNode diagram) {
		// LayeredLayoutProvider layered = new LayeredLayoutProvider();
		// BasicProgressMonitor layeredMonitor = new BasicProgressMonitor();
		// layered.layout(diagram, layeredMonitor);

		ForceLayoutProvider force = new ForceLayoutProvider();
		diagram.setProperty(ForceOptions.SPACING_NODE_NODE, 20.0);
		BasicProgressMonitor forceMonitor = new BasicProgressMonitor();
		force.layout(diagram, forceMonitor);

		edges.clear();
		children.clear();
		width = 0.0f;
		height = 0.0f;

		width = diagram.getWidth();
		height = diagram.getHeight();

		children.addAll(diagram.getChildren());

		gridwidth = (int) (width / (children.size() / 2));
		gridheight = (int) (height / (children.size() / 2));

		for (ElkNode node : children) {
			for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
				ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
				if (children.contains(target)) {
					edges.add(edge);
				}
			}

			double xPos = node.getX();
			double yPos = node.getY();

			xPos = calculateNewPos(xPos, gridwidth);
			yPos = calculateNewPos(yPos, gridheight);

			// TODO check availability
			node.setX(xPos);
			node.setY(yPos);
		}

	}

	/**
	 * 
	 * @param pos
	 * @param size
	 * @return
	 */
	private static double calculateNewPos(double pos, double size) {
		double x = pos / size;
		double y = x - Math.floor(x);

		if (y > 0.5) {
			pos = Math.ceil(x) * size;
		} else {
			pos = Math.floor(x) * size;
		}
		return pos;
	}

}
