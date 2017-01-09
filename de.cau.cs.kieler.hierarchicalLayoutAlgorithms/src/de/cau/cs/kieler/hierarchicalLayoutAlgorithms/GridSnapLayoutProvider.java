package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.force.ForceLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.klayoutdata.KPoint;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class GridSnapLayoutProvider extends AbstractLayoutProvider {

	/**
	 * Our representation of infinity, allow to add two of them without getting
	 * an int overflow.
	 */
	private static final int INFINITY = (Integer.MAX_VALUE / 2) - 1;
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

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Grid Snap Layouter", 1);

		simpleGrid(layoutGraph, progressMonitor);
		
//		initializeGrid(layoutGraph);
//		initializeNodes(layoutGraph);
//		pStress();
		routeEdges();
		
		progressMonitor.done();
	}
	
	private static void simpleGrid(KNode diagram, IElkProgressMonitor progressMonitor) {
		ForceLayoutProvider force = new ForceLayoutProvider();
		force.layout(diagram, progressMonitor);
		
		edges.clear();
		children.clear();
		width = 0.0f;
		height = 0.0f;
		
		KShapeLayout diagramLayout = diagram.getData(KShapeLayout.class);
		width = diagramLayout.getWidth();
		height = diagramLayout.getHeight();
		
		children.addAll(diagram.getChildren());
		
		gridwidth = (int) (width / children.size());
		gridheight = (int) (height / children.size());
		
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
					if(children.indexOf(u) < children.indexOf(v)) {
						float duv = getPathLength(u, v);
						float wuv = 1 / (duv*duv);
						pStressLeft += wuv * z(duv - dist) * z((duv - dist));
					}
					wp = 1/dL;
					pStressRight += wp * z(dist - dL) * z(dist - dL);
				}
			}
			pStress = pStressLeft + pStressRight;
			dispMap.put(u, pStress);
		}
		
		for (KNode n : children) {
			KShapeLayout nLayout = n.getData(KShapeLayout.class);
//			System.out.println("x: " + dispMap.get(n));
			float newXpos = nLayout.getXpos() - dispMap.get(n) - nLayout.getWidth()/2;
			float newYpos = nLayout.getYpos() - dispMap.get(n) - nLayout.getHeight()/2;
			nLayout.setPos(newXpos, newYpos);
//			System.out.println("x: " + newXpos);
//			System.out.println("y: " + newYpos);
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
