package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.force.ForceLayoutProvider;
import org.eclipse.elk.alg.force.properties.ForceOptions;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.klayoutdata.KPoint;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class GridSnapLayoutProvider extends AbstractLayoutProvider {

	/**
	 * Our representation of infinity, allow to add two of them without getting
	 * an int overflow.
	 */
	private static final double INFINITY = Double.MAX_VALUE;
	// TODO make as option
	private static final float SPACING = 100;
	private static float width;
	private static float height;
	/** */
	private static List<KEdge> edges = new ArrayList<KEdge>();
	private static List<KNode> children = new ArrayList<KNode>();
	private static float gridwidth;
	private static float gridheight;
	private static List<Point2D.Double> availablePositions = new ArrayList<Point2D.Double>();

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Grid Snap Layouter", 1);
		
		BasicProgressMonitor forceMonitor = new BasicProgressMonitor();
		HierarchicalStressLayoutProvider stress = new HierarchicalStressLayoutProvider();
		stress.layout(layoutGraph, forceMonitor);

		minimizingWhitespaceGrid(layoutGraph);

		routeEdges();

		progressMonitor.done();
	}

	/**
	 * 
	 * @param diagram
	 */
	private static void minimizingWhitespaceGrid(KNode diagram) {
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
		KShapeLayout diagramLayout = diagram.getData(KShapeLayout.class);
		float diagramwidth = diagramLayout.getWidth();
		float diagramheight = diagramLayout.getHeight();
		gridwidth = xPos - 50;
		gridheight = yPos - 50;
		Map<Integer, KNode> gridNodeMap = new HashMap<Integer, KNode>();

		for (KNode node : children) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (children.contains(edge.getTarget())) {
					edges.add(edge);
				}
			}

			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
//			System.out.println("xpos: " + nodeLayout.getXpos());
//			System.out.println("width: " + diagramwidth);
//			System.out.println("gridwidth: " + gridwidth);
			float gridXpos = ((nodeLayout.getXpos() + nodeLayout.getWidth() / 2) / diagramwidth) * gridwidth;
			float gridYpos = ((nodeLayout.getYpos() + nodeLayout.getHeight() / 2) / diagramheight) * gridheight;
			// TODO Sort x- and y-axis first
//			System.out.println("gridpos: " + gridXpos);
//			System.out.println(round(gridXpos, gridwidth));
			Point2D point = nearestAvailablePosition(
					new Point2D.Double(round(gridXpos, gridwidth), round(gridYpos, gridheight)));
			int mapPosition = gridNodePosition.get(point);
//			System.out.println(mapPosition);
			gridNodeMap.put(mapPosition, node);
		}
		System.out.println("Hallo");

		float newWidth = 20;
		float newHeight = 20;
		Map<Integer, Float> rowwidth = new HashMap<Integer, Float>();
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (!(gridNodeMap.get(i * gridSize + j) == null)) {
					KNode node = gridNodeMap.get(i * gridSize + j);
					KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
					if (rowwidth.containsKey(j)) {
						if (rowwidth.get(j) < nodeLayout.getWidth()) {
							rowwidth.put(j, nodeLayout.getWidth());
						}
					} else {
						rowwidth.put(j, nodeLayout.getWidth());
					}
				}
			}
		}

		float nextXpos = 20;
		float nextYpos = 20;
		float rowheight = 20;
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (!(gridNodeMap.get(i * gridSize + j) == null)) {
					KNode node = gridNodeMap.get(i * gridSize + j);
					KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
					if (rowheight < nodeLayout.getHeight()) {
						rowheight = nodeLayout.getHeight();
					}
					nodeLayout.setPos(nextXpos, nextYpos);
					if (newWidth < nodeLayout.getXpos() + nodeLayout.getWidth()) {
						newWidth = nodeLayout.getXpos() + nodeLayout.getWidth();
					}
					if (newHeight < nodeLayout.getYpos() + nodeLayout.getHeight()) {
						newHeight = nodeLayout.getYpos() + nodeLayout.getHeight();
					}
					nextXpos = nodeLayout.getXpos() + rowwidth.get(j) + SPACING;
				} else {
					nextXpos += rowwidth.get(j) + SPACING;
				}
			}
			nextYpos += rowheight + SPACING;
			rowheight = 0;
			nextXpos = 20;
		}

		diagramLayout.setWidth(newWidth + 20);
		diagramLayout.setHeight(newHeight + 20);
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
	private static float round(float x, float maxvalue) {
		float result;
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
	private static void simpleGrid(KNode diagram) {
		KLayoutData data = diagram.getData(KLayoutData.class);
		// LayeredLayoutProvider layered = new LayeredLayoutProvider();
		// BasicProgressMonitor layeredMonitor = new BasicProgressMonitor();
		// layered.layout(diagram, layeredMonitor);

		ForceLayoutProvider force = new ForceLayoutProvider();
		data.setProperty(ForceOptions.SPACING_NODE, 20.0f);
		data.setProperty(ForceOptions.SPACING_BORDER, 20.0f);
		BasicProgressMonitor forceMonitor = new BasicProgressMonitor();
		force.layout(diagram, forceMonitor);

		edges.clear();
		children.clear();
		width = 0.0f;
		height = 0.0f;

		KShapeLayout diagramLayout = diagram.getData(KShapeLayout.class);
		width = diagramLayout.getWidth();
		height = diagramLayout.getHeight();

		children.addAll(diagram.getChildren());

		gridwidth = (int) (width / (children.size() / 2));
		gridheight = (int) (height / (children.size() / 2));

		for (KNode node : children) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (children.contains(edge.getTarget())) {
					edges.add(edge);
				}
			}

			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
			float xPos = nodeLayout.getXpos();
			float yPos = nodeLayout.getYpos();

			xPos = calculateNewPos(xPos, gridwidth);
			yPos = calculateNewPos(yPos, gridheight);

			// TODO check availability
			nodeLayout.setPos(xPos, yPos);
		}

	}

	/**
	 * 
	 * @param pos
	 * @param size
	 * @return
	 */
	private static float calculateNewPos(float pos, float size) {
		float x = pos / size;
		double y = x - Math.floor(x);

		if (y > 0.5) {
			pos = ((float) Math.ceil(x)) * size;
		} else {
			pos = ((float) Math.floor(x)) * size;
		}
		return pos;
	}

	/**
	 * 
	 */
	private static void routeEdges() {
		for (KEdge edge : edges) {
			KNode source = edge.getSource();
			KShapeLayout sLayout = source.getData(KShapeLayout.class);
			KPoint sPoint = KLayoutDataFactory.eINSTANCE.createKPoint();

			KNode target = edge.getTarget();
			KShapeLayout tLayout = target.getData(KShapeLayout.class);
			KPoint tPoint = KLayoutDataFactory.eINSTANCE.createKPoint();

			sPoint.setPos(sLayout.getXpos(), sLayout.getYpos());
			tPoint.setPos(tLayout.getXpos(), tLayout.getYpos());

			KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
			edgeLayout.setSourcePoint(sPoint);
			edgeLayout.setTargetPoint(tPoint);
		}
	}

	/**
	 * 
	 */
	private static void routeBetterEdges() {
		for (KEdge edge : edges) {
			KNode source = edge.getSource();
			KShapeLayout sLayout = source.getData(KShapeLayout.class);
			KPoint sPoint = KLayoutDataFactory.eINSTANCE.createKPoint();

			KNode target = edge.getTarget();
			KShapeLayout tLayout = target.getData(KShapeLayout.class);
			KPoint tPoint = KLayoutDataFactory.eINSTANCE.createKPoint();

			// Check at which position the edge should start/end
			if (sLayout.getXpos() + sLayout.getWidth() <= tLayout.getXpos()
					&& sLayout.getYpos() > tLayout.getYpos() + tLayout.getHeight()) {
				sPoint.setPos(sLayout.getXpos() + sLayout.getWidth() / 1.5f, sLayout.getYpos());
				tPoint.setPos(tLayout.getXpos() + tLayout.getWidth() / 1.5f, tLayout.getYpos() + tLayout.getHeight());
			} else if (sLayout.getXpos() + sLayout.getWidth() <= tLayout.getXpos()
					&& sLayout.getYpos() + sLayout.getHeight() < tLayout.getYpos()) {
				sPoint.setPos(sLayout.getXpos() + sLayout.getWidth() / 1.5f, sLayout.getYpos() + sLayout.getHeight());
				tPoint.setPos(tLayout.getXpos() + tLayout.getWidth() / 1.5f, tLayout.getYpos());
			} else if (sLayout.getXpos() + sLayout.getWidth() <= tLayout.getXpos()) {
				sPoint.setPos(sLayout.getXpos() + sLayout.getWidth(), sLayout.getYpos() + sLayout.getHeight() / 1.5f);
				tPoint.setPos(tLayout.getXpos(), tLayout.getYpos() / 1.5f);
			} else if (sLayout.getXpos() > tLayout.getXpos() + tLayout.getWidth()
					&& sLayout.getYpos() > tLayout.getYpos() + tLayout.getHeight()) {
				sPoint.setPos(sLayout.getXpos() + sLayout.getWidth() / 2.5f, sLayout.getYpos());
				tPoint.setPos(tLayout.getXpos() + tLayout.getWidth() / 2.5f, tLayout.getYpos() + tLayout.getHeight());
			} else if (sLayout.getXpos() > tLayout.getXpos() + tLayout.getHeight()
					&& sLayout.getYpos() + sLayout.getHeight() < tLayout.getYpos()) {
				sPoint.setPos(sLayout.getXpos() + sLayout.getWidth() / 2.5f, sLayout.getYpos() + sLayout.getHeight());
				tPoint.setPos(tLayout.getXpos() + tLayout.getWidth() / 2.5f, tLayout.getYpos());
			} else if (sLayout.getXpos() > tLayout.getXpos() + tLayout.getWidth()) {
				sPoint.setPos(sLayout.getXpos(), sLayout.getYpos() + sLayout.getHeight() / 2.5f);
				tPoint.setPos(tLayout.getXpos() + tLayout.getWidth(), tLayout.getYpos() + tLayout.getHeight() / 2.5f);
			} else if (sLayout.getYpos() > tLayout.getYpos() + tLayout.getHeight()) {
				sPoint.setPos(sLayout.getXpos() + sLayout.getWidth() / 1.5f, sLayout.getYpos());
				tPoint.setPos(tLayout.getXpos() + tLayout.getWidth() / 1.5f, tLayout.getYpos() + tLayout.getHeight());
			} else {
				sPoint.setPos(sLayout.getXpos() + sLayout.getWidth() / 2.5f, sLayout.getYpos());
				tPoint.setPos(tLayout.getXpos() + tLayout.getWidth() / 2.5f, tLayout.getYpos() + tLayout.getHeight());
			}

			KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
			edgeLayout.setSourcePoint(sPoint);
			edgeLayout.setTargetPoint(tPoint);
		}
	}
}
