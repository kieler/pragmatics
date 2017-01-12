package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ProgressMonitor;

import org.eclipse.elk.alg.force.ForceLayoutProvider;
import org.eclipse.elk.alg.force.properties.ForceOptions;
import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
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
	private static final int INFINITY = (Integer.MAX_VALUE / 2) - 1;
	private static final double DOUBLE_INFINITY = Double.MAX_VALUE;
	// TODO make as option
	private static final float SPACING = 100;
	private static float dL;
	private static float wp;
	private static float width;
	private static float height;
	/** */
	private static List<KEdge> edges = new ArrayList<KEdge>();
	private static List<KNode> children = new ArrayList<KNode>();
	private static KPoint[][] grid;
	private static List<KNode> visited = new ArrayList<KNode>();
	private static int distance;
	private static Map<KNode, Float> dispMap = new HashMap<KNode, Float>();
	private static int gridwidth;
	private static int gridheight;
	private static List<Point2D.Double> availablePositions = new ArrayList<Point2D.Double>();
	private static int averageNodeWidth;

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Grid Snap Layouter", 1);

		minimizingWhitespaceGrid(layoutGraph);

		// initializeGrid(layoutGraph);
		// initializeNodes(layoutGraph);
		// pStress();

		routeEdges();
		// routeBetterEdges();

		progressMonitor.done();
	}

	/**
	 * 
	 * @param diagram
	 */
	private static void minimizingWhitespaceGrid(KNode diagram) {
		// KLayoutData diagramData = diagram.getData(KLayoutData.class);
		ForceLayoutProvider force = new ForceLayoutProvider();
		// diagramData.setProperty(ForceOptions.SPACING_NODE, 20.0f);
		// diagramData.setProperty(ForceOptions.SPACING_BORDER, 20.0f);
		BasicProgressMonitor forceMonitor = new BasicProgressMonitor();
		force.layout(diagram, forceMonitor);

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

		// calculate ralative closest grid position and check if available
		KShapeLayout diagramLayout = diagram.getData(KShapeLayout.class);
		double diagramwidth = diagramLayout.getWidth();
		double diagramheight = diagramLayout.getHeight();
		gridwidth = xPos - 50;
		gridheight = yPos - 50;
		Map<Integer, KNode> gridNodeMap = new HashMap<Integer, KNode>();
		int nodewidth = 0;

		for (KNode node : children) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (children.contains(edge.getTarget())) {
					edges.add(edge);
				}
			}

			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
			nodewidth += nodeLayout.getWidth();
			int gridXpos = (int) ((nodeLayout.getXpos() + nodeLayout.getWidth() / diagramwidth) * gridwidth);
			int gridYpos = (int) ((nodeLayout.getYpos() + nodeLayout.getHeight() / diagramheight) * gridheight);
			// TODO Fix calculation of nearest Position
			Point2D point = nearestAvailablePosition(
					new Point2D.Double(round(gridXpos, gridwidth), round(gridYpos, gridheight)));
			int mapPosition = gridNodePosition.get(point);
			gridNodeMap.put(mapPosition, node);
		}
		averageNodeWidth = nodewidth / children.size();
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
		double smallestDistance = DOUBLE_INFINITY;
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
	private static double round(int x, int maxvalue) {
		int result;
		if ((x % 50) > 50 / 2) {
			result = x + 50 - x % 50;
		} else {
			result = x - x % 50;
		}

		if (result > maxvalue) {
			result = maxvalue;
		}

		return (double) result;
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
			// if (node.getChildren().size() == 3) {
			// KShapeLayout shape = node.getData(KShapeLayout.class);
			// shape.setHeight(100);
			// shape.setWidth(700);
			// shape.setPos(0, 0);
			// BasicProgressMonitor layeredMonitor = new BasicProgressMonitor();
			// LayeredLayoutProvider layered = new LayeredLayoutProvider();
			// layered.layout(node, layeredMonitor);
			// }
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
	private static float calculateNewPos(float pos, int size) {
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
	 * @param layoutGraph
	 */
	private static void initializeGrid(KNode layoutGraph) {
		edges.clear();
		children.clear();
		dispMap.clear();
		width = 0.0f;
		height = 0.0f;

		for (KNode node : layoutGraph.getChildren()) {
			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
			width += nodeLayout.getWidth();
			height += nodeLayout.getHeight();
		}

		KShapeLayout layout = layoutGraph.getData(KShapeLayout.class);
		layout.setWidth(width);
		layout.setHeight(height);

		// grid = new KPoint[(int) (width / 10 + 1)][(int) (height / 10 + 1)];
		// for (int i = 10; i <= width; i += 10) {
		// for (int j = 10; j <= height; j += 10) {
		// KPoint gridPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
		// gridPoint.setPos(i, j);
		// grid[i / 10][j / 10] = gridPoint;
		// }
		// }
	}

	/**
	 * 
	 * @param layoutGraph
	 */
	private static void initializeNodes(KNode layoutGraph) {
		children.addAll(layoutGraph.getChildren());
		for (KNode node : children) {
			// Calculate edges we need to draw
			for (KEdge edge : node.getOutgoingEdges()) {
				if (children.contains(edge.getTarget())) {
					edges.add(edge);
				}
			}
			// Initial random positions
			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
			nodeLayout.setPos((float) Math.random() * width, (float) Math.random() * height);
		}
	}

	/**
	 * 
	 */
	private static void pStress() {
		dL = 100;
		float pStress = 0.0f;
		for (KNode u : children) {
			float pStressLeft = 0.0f;
			float pStressRight = 0.0f;
			for (KNode v : children) {
				if (u != v) {
					float dist = distance(u, v);
					if (children.indexOf(u) < children.indexOf(v)) {
						float duv = getPathLength(u, v);
						float wuv = 1 / (duv * duv);
						pStressLeft += wuv * z(duv - dist) * z((duv - dist));
					}
					wp = 1 / dL;
					pStressRight += wp * z(dist - dL) * z(dist - dL);
				}
			}
			pStress = pStressLeft + pStressRight;
			dispMap.put(u, pStress);
		}

		for (KNode n : children) {
			KShapeLayout nLayout = n.getData(KShapeLayout.class);
			// System.out.println("x: " + dispMap.get(n));
			float newXpos = nLayout.getXpos() - dispMap.get(n) - nLayout.getWidth() / 2;
			float newYpos = nLayout.getYpos() - dispMap.get(n) - nLayout.getHeight() / 2;
			nLayout.setPos(newXpos, newYpos);
			// System.out.println("x: " + newXpos);
			// System.out.println("y: " + newYpos);
		}
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	private static float z(float i) {
		return Math.max(i, 0.0f);
	}

	/**
	 * 
	 */
	private static void gridSnap() {

	}

	/**
	 * 
	 */
	private static void nodeSnap() {

	}

	/**
	 * 
	 */
	private static void ACA() {
		for (KEdge edge : edges) {

		}
	}

	/**
	 * 
	 * @param k1
	 * @param k2
	 * @return
	 */
	private static float distance(KNode k1, KNode k2) {
		KShapeLayout k1Layout = k1.getData(KShapeLayout.class);
		KShapeLayout k2Layout = k2.getData(KShapeLayout.class);
		float k1_X = k1Layout.getXpos() + k1Layout.getWidth() / 2;
		float k2_X = k2Layout.getXpos() + k2Layout.getWidth() / 2;
		float k1_Y = k1Layout.getYpos() + k1Layout.getHeight() / 2;
		float k2_Y = k2Layout.getYpos() + k2Layout.getHeight() / 2;
		return (float) Math.sqrt((k1_X - k2_X) * (k1_X - k2_X) + (k1_Y - k2_Y) * (k1_Y - k2_Y));
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

	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private static int getPathLength(KNode start, KNode end) {
		distance = INFINITY;
		visited.clear();
		int distance;
		if (start == end) {
			return 0;
		} else {
			distance = pathLengthRecursive(start, end, 0);
		}
		return distance;
	}

	/**
	 * 
	 * @param node
	 * @param end
	 * @param temp
	 * @return
	 */
	private static int pathLengthRecursive(KNode node, KNode end, int temp) {
		visited.add(node);
		List<KNode> neighbours = getNeighbours(node);
		if (!neighbours.isEmpty()) {
			for (KNode n : getNeighbours(node)) {
				if (!visited.contains(n)) {
					if (n == end) {
						temp += 1;
						if (temp < distance) {
							distance = temp;
						}
					} else {
						pathLengthRecursive(n, end, temp + 1);
					}
				}
			}
		}
		return distance;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private static List<KNode> getNeighbours(KNode node) {
		List<KNode> neighbours = new ArrayList<KNode>();
		for (KEdge e : edges) {
			if (e.getSource() == node) {
				neighbours.add(e.getTarget());
			} else if (e.getTarget() == node) {
				neighbours.add(e.getSource());
			}
		}
		return neighbours;
	}

}
