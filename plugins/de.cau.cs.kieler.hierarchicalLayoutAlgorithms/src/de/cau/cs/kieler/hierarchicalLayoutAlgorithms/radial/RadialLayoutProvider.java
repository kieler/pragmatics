package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.List;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalEdgeRouting;
import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;
import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.RadialOptions;

public class RadialLayoutProvider extends AbstractLayoutProvider {
	private double radius;
	private IAnnulusWedgeCriteria annulusWedgeCriteria;
	double offset = 0;

	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Radial layouter", 1);

		// Calculate the radius or take the one given by the user.
		double layoutRadius = layoutGraph.getProperty(RadialOptions.RADIUS);
		if (layoutRadius == 0) {
			radius = HierarchicalUtil.findBiggestNodeInGraph(layoutGraph);
		} else {
			radius = layoutRadius;
		}
		//make sure a criteria for the Wedge is set
		if(annulusWedgeCriteria == null){
			annulusWedgeCriteria = new AnnulusWedgeByNodeSpace();
		}

		// calculate the positions, starting with the root node
		ElkNode root = HierarchicalUtil.findRoot(layoutGraph);
		translate(root);

		List<ElkNode> successors = HierarchicalUtil.sortSuccesorsByPolarCoordinate(root);
		RadialCompaction compacter = new RadialCompaction();
		compacter.contraction(successors);

		HierarchicalUtil.postProcess(layoutGraph);
		HierarchicalEdgeRouting.drawExplosionLines(root);
		progressMonitor.done();
	}
	
	/**
	 * Search for the best layout translation by looking at each degree
	 * @param root The root node of the graph
	 */
	private void translate(ElkNode root) {
		positionNodes(root, 0, 0, 2 * Math.PI);
		HierarchicalEdgeRouting.drawExplosionLines(root);
		double optimalOffset = 0;
		double optimalValue = evaluate(root);

		for (int i = 1; i < 360; i++) {
			offset = i;
			positionNodes(root, 0, 0, 2 * Math.PI);
			HierarchicalEdgeRouting.drawExplosionLines(root);
			double translatedValue = evaluate(root);//  
			// TODO nicht deterministisch
			if (translatedValue < optimalValue) {
				optimalOffset = offset;
				optimalValue = translatedValue;
			}
		}
		offset = optimalOffset;
		positionNodes(root, 0, 0, 2 * Math.PI);
		HierarchicalEdgeRouting.drawExplosionLines(root);
	}

	/**
	 * Place a node in the center of an wedge an calculate the wedge for the
	 * next child.
	 * 
	 * @param node
	 * @param currentRadius
	 * @param minAlpha
	 * @param maxAlpha
	 */
	private void positionNodes(ElkNode node, double currentRadius, double minAlpha, double maxAlpha) {

		double alphaPoint = (minAlpha + maxAlpha) / 2 + offset;

		// double size = layerSize.get(layer);

		// x=r*sinθ, y=r*cosθ
		double xPos = currentRadius * Math.cos(alphaPoint);
		double yPos = currentRadius * Math.sin(alphaPoint);

		// shift the nodes, such that the center of each node is on the circle
		centerNodesOnRadi(node, xPos, yPos);
		
		double numberOfLeafs = annulusWedgeCriteria.calculateWedgeSpace(node);
		double tau = 2 * Math.acos(currentRadius / currentRadius + radius);
		double s;
		double alpha;
		if (tau < maxAlpha - minAlpha) {
			s = tau / numberOfLeafs;
			alpha = (minAlpha + maxAlpha - tau) / 2;

		} else {
			s = (maxAlpha - minAlpha) / numberOfLeafs;
			alpha = minAlpha;
		}
		List<ElkNode> successors = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);
		for (ElkNode child : successors) {
			double numberOfChildLeafs = annulusWedgeCriteria.calculateWedgeSpace(child);
			positionNodes(child, currentRadius+ radius, alpha, alpha + s * numberOfChildLeafs);
			alpha += s * numberOfChildLeafs;
		}
	}

	private double evaluate(ElkNode root) {
		double edgeLength = 0;
		for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(root)) {
			if (!root.getChildren().contains(edge.getTargets().get(0))) {
				ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
				double startX = section.getStartX();
				double startY = section.getStartY();
				double endX = section.getEndX();
				double endY = section.getEndY();

				double x = endX - startX;
				double y = endY - startY;

				edgeLength += Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			}
		}
		return edgeLength;
	}

	private void centerNodesOnRadi(ElkNode node, double xPos, double yPos) {
		xPos = xPos - node.getWidth() / 2;
		yPos = yPos - node.getHeight() / 2;

		node.setX(xPos);
		node.setY(yPos);
	}

	private void shiftClosestEdgeToRadi(ElkNode node, double xPos, double yPos) {

		// center root
		if (xPos == 0 && yPos == 0) {
			node.setX(xPos - node.getWidth() / 2);
			node.setY(yPos - node.getHeight() / 2);
		} else {

			if (xPos < 0) {
				if (yPos < 0) {
					// lower left rectangle

					node.setX(xPos - node.getWidth());
					node.setY(yPos);
				} else {
					// upper left rectangle
					node.setX(xPos - node.getWidth());
					node.setY(yPos + node.getHeight());
				}
			} else {

				if (yPos < 0) {
					// lower right rectangle

					node.setX(xPos);
					node.setY(yPos);
				} else {
					// upper right rectangle

					node.setX(xPos);
					node.setY(yPos + node.getHeight());
				}
			}
		}
	}



	public IAnnulusWedgeCriteria getAnnulusWedgeCriteria() {
		return annulusWedgeCriteria;
	}

	public void setAnnulusWedgeCriteria(IAnnulusWedgeCriteria annulusWedgeCriteria) {
		this.annulusWedgeCriteria = annulusWedgeCriteria;
	}

}
