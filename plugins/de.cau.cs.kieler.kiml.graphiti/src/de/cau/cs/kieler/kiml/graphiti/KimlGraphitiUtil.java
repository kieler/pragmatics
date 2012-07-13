/**
 * 
 */
package de.cau.cs.kieler.kiml.graphiti;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.SWTException;

import com.google.common.collect.BiMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KEdgeLayoutImpl;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KShapeLayoutImpl;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Utility Methods for Diagram Layout Manager for Graphiti based editors
 * 
 * @author jayant
 */
public final class KimlGraphitiUtil {

	/**
	 * Hidden constructor to avoid instantiation.
	 */
	private KimlGraphitiUtil() {
	}

	/** diagram editor of the currently layouted diagram. */
	public static final IProperty<DiagramEditor> DIAGRAM_EDITOR = new Property<DiagramEditor>(
			"graphiti.diagramEditor");

	/** the command that is executed for applying automatic layout. */
	public static final IProperty<Command> LAYOUT_COMMAND = new Property<Command>(
			"graphiti.applyLayoutCommand");

	/** list of all connections in the diagram. */
	public static final IProperty<List<Connection>> CONNECTIONS = new Property<List<Connection>>(
			"graphiti.connections");

	/**
	 * the volatile layout config for static properties such as minimal node
	 * sizes.
	 */
	public static final IProperty<VolatileLayoutConfig> STATIC_CONFIG = new Property<VolatileLayoutConfig>(
			"graphiti.staticLayoutConfig");

	/**
	 * Set up a label for a node or a port.
	 * 
	 * @param element
	 *            the graph element to which the label is added
	 * @param abstractText
	 *            the text graphics algorithm to set up the label
	 * @param offsetx
	 *            the x coordinate offset
	 * @param offsety
	 *            the y coordinate offset
	 * @return a new label
	 */
	public static KLabel createLabel(final KLabeledGraphElement element,
			final AbstractText abstractText, final float offsetx,
			final float offsety) {
		String labelText = abstractText.getValue();
		if (labelText != null) {
			KLabel label = KimlUtil.createInitializedLabel(element);
			label.setText(labelText);
			IGaService gaService = Graphiti.getGaService();
			Font font = gaService.getFont(abstractText, true);

			IDimension textSize = null;
			try {
				textSize = GraphitiUi.getUiLayoutService().calculateTextSize(
						labelText, font);
			} catch (SWTException exception) {
				// ignore exception
			}
			int xpos = abstractText.getX(), ypos = abstractText.getY();
			int width = abstractText.getWidth(), height = abstractText
					.getHeight();
			if (textSize != null) {
				if (textSize.getWidth() < width) {
					int diff = width - textSize.getWidth();
					switch (gaService
							.getHorizontalAlignment(abstractText, true)) {
					case ALIGNMENT_CENTER:
						xpos += diff / 2;
						break;
					case ALIGNMENT_RIGHT:
						xpos += diff;
						break;
					}
					width -= diff;
				}
				if (textSize.getHeight() < height) {
					int diff = height - textSize.getHeight();
					switch (gaService.getVerticalAlignment(abstractText, true)) {
					case ALIGNMENT_MIDDLE:
						ypos += diff / 2;
						break;
					case ALIGNMENT_BOTTOM:
						ypos += diff;
						break;
					}
					height -= diff;
				}
			}

			KShapeLayout labelLayout = label.getData(KShapeLayout.class);
			labelLayout.setPos(xpos + offsetx, ypos + offsety);
			labelLayout.setSize(width, height);
			// the modification flag must initially be false
			((KShapeLayoutImpl) labelLayout).resetModificationFlag();
			return label;
		}
		return null;
	}

	/** minimal value for the relative location of head labels. */
	private static final double HEAD_LOCATION = 0.7;
	/** maximal value for the relative location of tail labels. */
	private static final double TAIL_LOCATION = 0.3;

	/**
	 * Create an edge for the layout graph.
	 * 
	 * @param mapping
	 *            the mapping of pictogram elements to graph elements
	 * @param connection
	 *            a pictogram connection
	 */
	public static void createEdge(
			final LayoutMapping<PictogramElement> mapping,
			final Connection connection) {
		KEdge edge = KimlUtil.createInitializedEdge();
		BiMap<KGraphElement, PictogramElement> graphMap = mapping.getGraphMap();
		VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);

		// set target node and port
		KNode targetNode;
		Anchor targetAnchor = connection.getEnd();
		KPort targetPort = (KPort) graphMap.inverse().get(targetAnchor);
		if (targetPort != null) {
			edge.setTargetPort(targetPort);
			targetPort.getEdges().add(edge);
			targetNode = targetPort.getNode();
		} else {
			targetNode = (KNode) graphMap.inverse().get(
					targetAnchor.getParent());
		}
		edge.setTarget(targetNode);

		// set source node and port
		KNode sourceNode;
		Anchor sourceAnchor = connection.getStart();
		KPort sourcePort = (KPort) graphMap.inverse().get(sourceAnchor);
		if (sourcePort != null) {
			edge.setSourcePort(sourcePort);
			sourcePort.getEdges().add(edge);
			sourceNode = sourcePort.getNode();
		} else {
			sourceNode = (KNode) graphMap.inverse().get(
					sourceAnchor.getParent());
		}
		edge.setSource(sourceNode);

		if (sourceNode == null || targetNode == null) {
			return;
		}

		// calculate offset for bend points and labels
		KNode referenceNode = sourceNode;
		if (!KimlUtil.isDescendant(targetNode, sourceNode)) {
			referenceNode = sourceNode.getParent();
		}
		KVector offset = new KVector();
		KimlUtil.toAbsolute(offset, referenceNode);

		// set source and target point
		KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
		KVector sourcePoint = calculateAnchorEnds(sourceNode, sourcePort,
				referenceNode);
		edgeLayout.getSourcePoint().applyVector(sourcePoint);
		KVector targetPoint = calculateAnchorEnds(targetNode, targetPort,
				referenceNode);
		edgeLayout.getTargetPoint().applyVector(targetPoint);
		// set bend points for the new edge
		KVectorChain allPoints = new KVectorChain();
		allPoints.add(sourcePoint);
		if (connection instanceof FreeFormConnection) {
			for (Point point : ((FreeFormConnection) connection)
					.getBendpoints()) {
				KVector v = new KVector(point.getX(), point.getY());
				v.sub(offset);
				allPoints.add(v);
				KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
				kpoint.applyVector(v);
				edgeLayout.getBendPoints().add(kpoint);
			}
		}
		allPoints.add(targetPoint);
		// the modification flag must initially be false
		((KEdgeLayoutImpl) edgeLayout).resetModificationFlag();

		graphMap.put(edge, connection);

		// find labels for the connection
		for (ConnectionDecorator decorator : connection
				.getConnectionDecorators()) {
			GraphicsAlgorithm ga = decorator.getGraphicsAlgorithm();
			if (ga instanceof AbstractText) {
				AbstractText text = (AbstractText) ga;
				String labelText = text.getValue();
				KLabel label = KimlUtil.createInitializedLabel(edge);
				label.setText(labelText);
				graphMap.put(label, decorator);

				// set label placement
				KShapeLayout labelLayout = label.getData(KShapeLayout.class);
				EdgeLabelPlacement placement = EdgeLabelPlacement.CENTER;
				if (decorator.isLocationRelative()) {
					if (decorator.getLocation() >= HEAD_LOCATION) {
						placement = EdgeLabelPlacement.HEAD;
					} else if (decorator.getLocation() <= TAIL_LOCATION) {
						placement = EdgeLabelPlacement.TAIL;
					}
				}
				staticConfig.setValue(LayoutOptions.EDGE_LABEL_PLACEMENT,
						label, LayoutContext.GRAPH_ELEM, placement);

				// set label position
				KVector labelPos;
				if (decorator.isLocationRelative()) {
					labelPos = allPoints.getPointOnLine(decorator.getLocation()
							* allPoints.getLength());
				} else {
					labelPos = allPoints
							.getPointOnLine(decorator.getLocation());
				}
				labelPos.x += ga.getX();
				labelPos.y += ga.getY();
				labelLayout.applyVector(labelPos);

				// set label size (width and height)
				// @author jayant
				IGaService gaService = Graphiti.getGaService();
				Font font = gaService.getFont(text, true);

				IDimension textSize = null;
				try {
					textSize = GraphitiUi.getUiLayoutService()
							.calculateTextSize(labelText, font);
				} catch (SWTException exception) {
					// ignore exception
				}
				if (textSize != null)
					labelLayout.setSize(textSize.getWidth(),
							textSize.getHeight());

				// the modification flag must initially be false
				((KShapeLayoutImpl) labelLayout).resetModificationFlag();
			}
		}
	}

	/**
	 * Returns an end point for an anchor.
	 * 
	 * @param node
	 *            the node that owns the anchor
	 * @param port
	 *            the port that represents the anchor
	 * @param referenceNode
	 *            the parent node to which edge points are relative
	 * @return the position of the anchor, relative to the reference node
	 */
	public static KVector calculateAnchorEnds(final KNode node,
			final KPort port, final KNode referenceNode) {
		KVector pos = new KVector();
		if (port != null) {
			// the anchor end is represented by a port (box-relative anchor or
			// fix-point anchor)
			KShapeLayout portLayout = port.getData(KShapeLayout.class);
			pos.x = portLayout.getXpos() + portLayout.getWidth() / 2;
			pos.y = portLayout.getYpos() + portLayout.getHeight() / 2;
			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
			pos.x += nodeLayout.getXpos();
			pos.y += nodeLayout.getYpos();
		} else {
			// the anchor end is calculated by a chopbox anchor
			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
			pos.x = nodeLayout.getWidth() / 2 + nodeLayout.getXpos();
			pos.y = nodeLayout.getHeight() / 2 + nodeLayout.getYpos();
		}
		KimlUtil.toAbsolute(pos, node.getParent());
		KimlUtil.toRelative(pos, referenceNode);
		return pos;
	}

	/**
	 * Given a graphics algorithm, find the first child that is not invisible.
	 * If the GA itself is visible, it is returned.
	 * 
	 * @param graphicsAlgorithm
	 *            the parent graphics algorithm
	 * @return a visible graphics algorithm
	 */
	public static GraphicsAlgorithm findVisibleGa(
			final GraphicsAlgorithm graphicsAlgorithm) {
		if (graphicsAlgorithm.getLineVisible() || graphicsAlgorithm.getFilled()) {
			return graphicsAlgorithm;
		}
		for (GraphicsAlgorithm ga : graphicsAlgorithm
				.getGraphicsAlgorithmChildren()) {
			GraphicsAlgorithm result = findVisibleGa(ga);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Calculate insets from an invisible rectangle to the first visible shape.
	 * 
	 * @param graphicsAlgorithm
	 *            the parent graphics algorithm
	 * @return the insets
	 */
	public static KInsets calcInsets(final GraphicsAlgorithm graphicsAlgorithm) {
		GraphicsAlgorithm visibleGa = findVisibleGa(graphicsAlgorithm);
		int left = 0;
		int top = 0;
		int right = 0;
		int bottom = 0;
		while (visibleGa != graphicsAlgorithm) {
			left += visibleGa.getX();
			top += visibleGa.getY();
			GraphicsAlgorithm parentGa = visibleGa.getParentGraphicsAlgorithm();
			right += parentGa.getWidth() - visibleGa.getX()
					- visibleGa.getWidth();
			bottom += parentGa.getHeight() - visibleGa.getY()
					- visibleGa.getHeight();
			visibleGa = parentGa;
		}
		KInsets insets = KLayoutDataFactory.eINSTANCE.createKInsets();
		insets.setLeft(left);
		insets.setRight(right);
		insets.setTop(top);
		insets.setBottom(bottom);
		return insets;
	}

}
