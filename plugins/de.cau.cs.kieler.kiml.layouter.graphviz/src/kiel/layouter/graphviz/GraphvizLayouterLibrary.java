/*******************************************************************************
 * Copyright (c) 2008 Real-Time and Embedded Systems group
 *
 * INSERT LICENCE HERE
 *
 *
 * Author: Arne Schipper, ars@informatik.uni-kiel.de 
 *
 *******************************************************************************/
package kiel.layouter.graphviz;

import java.awt.Dimension;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.layouter.graphviz.Activator;
import de.cau.cs.kieler.kiml.layouter.graphviz.preferences.PreferenceConstants;


/**
 * Basic layout algorithm employing the GraphViz library (e.g. dot layout) to do
 * a graphical layout on the passed KNode data structure. The basic principle is simple:
 * <ol>
 * <li>Read the KNode data structure and use the {@link GraphvizAPI} to
 * fill a GraphViz internal (native) data structure.</li>
 * <li>Call a GraphViz layout engine (e.g. the dot layouter) on the GraphViz
 * data structure. The data structure will get augmented by positioning
 * attributes.</li>
 * <li>Read the position attributes from the GraphViz datastructure and write
 * them back to the KIELER Graph data structure.</li>
 * </ol>
 * <p/>
 * Supported features are node sizes and positions, tail and mid label
 * positions. Edges in GraphViz are described as B-splines curves. The bezier
 * curves are converted here to polyline to be able to work with an GEF/GMF
 * editor.
 * <p/>
 * No hierarchy is supported by this implementation. Rather, some preprocessing
 * should take care hierarchy handling. One possibility is to send every
 * hierarchy level separately to a new GraphViz layouter, using the size
 * information gained in a previous step as the size of the nodes in the current
 * step.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 * 
 */
public class GraphvizLayouterLibrary implements GraphvizLayouter{

	/* Store the GraphViz internal C-Pointers of our nodes and edges */
	private HashMap<KNode, Integer> mapNode2Pointer = new HashMap<KNode, Integer>();
	private HashMap<KEdge, Integer> mapEdge2Pointer = new HashMap<KEdge, Integer>();

	/* C-pointer to the root GraphvizGraph data structure */
	private int graphvizGraph;

	/*
	 * Dots per inch specification. GraphViz uses inch for some internal sizes
	 * (e.g. width, height) but not for all. Hence finding the right DPI is
	 * crucial for the layout. Setting the DPI attribute for a graph within
	 * GraphViz seems to have no effect.
	 */
	private final float dpi = 72.0f;

	/* padding from the borders. pad attribute of GraphViz has no effect */
	private int prefPadX = 15;
	private int prefPadY = 15;

	/* holds the String denoting the desired layouter of the GraphViz suite */
	private String command;

	/**
	 * Creates a new KIML GraphViz layouter with the specified concrete GraphViz
	 * layouter (e.g. GraphViz Circo). Loads the preference values.
	 * 
	 * @param layoutProviderName
	 *            A String denoting the layouter. Must be one of those declared
	 *            in {@link GraphvizLayoutProviderNames}.
	 */
	public GraphvizLayouterLibrary(String command) {
		this.command = command;
		GraphvizAPI.initialize();
	}

	/**
	 * Updates the preferences for the GraphViz layouter. Is called from visit
	 * at the beginning.
	 */
	private void updatePreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		prefPadX = store.getInt(PreferenceConstants.PREF_GRAPHVIZ_PADDING_X);
		prefPadY = store.getInt(PreferenceConstants.PREF_GRAPHVIZ_PADDING_Y);
	}

	/**
	 * Performs the actual work of the layout process. Translates the
	 * {@link de.cau.cs.kieler.kiml.layout.KimlLayoutGraph.KNode
	 * KNode} into a structure GraphViz understands, calls the desired
	 * GraphViz layouter and annotates the KLayoutGraph with the position and
	 * size information provided by GraphViz.
	 * 
	 * @param layoutNode the KNode to process
	 * @param progressMonitor the progress monitor
	 * @throws KielerException if the layout process fails
	 */
	public void visit(KNode layoutNode,
			IKielerProgressMonitor progressMonitor) throws KielerException {
		progressMonitor.begin("GraphViz layout", 15);
		updatePreferences();

		/* return if there is nothing in this group */
		if (layoutNode.getChildren().isEmpty()) {
			progressMonitor.done();
			return;
		}

		/* create fresh internal GraphViz graph */
		graphvizGraph = GraphvizAPI.createGraph("");
		GraphvizAPI.setGlobalNodeAttribute(graphvizGraph,
				GraphvizAPI.ATTR_SHAPE, "box");

		/* translate the KLayoutGraph to GraphViz */
		mapLayoutNode2Graphviz(layoutNode);

		/* check if Emma wants to layout horizontal */
		KLayoutData layoutData = KimlLayoutUtil.getShapeLayout(layoutNode);
        if (LayoutOptions.getLayoutDirection(layoutData)
                == LayoutDirection.VERTICAL) {
			GraphvizAPI.setGraphAttribute(graphvizGraph, "rankdir", "BT");
		} else {
			GraphvizAPI.setGraphAttribute(graphvizGraph, "rankdir", "LR");
		}
		progressMonitor.worked(5);

		/* pick up desired layouter */
		if (command.equals(GraphvizLayouter.CIRCO_COMMAND)) {
			GraphvizAPI.doCircoLayout(graphvizGraph);
			GraphvizAPI.attachAttributes(graphvizGraph);
			mapGraphviz2LayoutNode(layoutNode);
			GraphvizAPI.circoCleanup(graphvizGraph);
		} else if (command
				.equals(GraphvizLayouter.NEATO_COMMAND)) {
			GraphvizAPI.doNeatoLayout(graphvizGraph);
			GraphvizAPI.attachAttributes(graphvizGraph);
			mapGraphviz2LayoutNode(layoutNode);
			GraphvizAPI.neatoCleanup(graphvizGraph);
		} else if (command
				.equals(GraphvizLayouter.TWOPI_COMMAND)) {
			GraphvizAPI.doTwopiLayout(graphvizGraph);
			GraphvizAPI.attachAttributes(graphvizGraph);
			mapGraphviz2LayoutNode(layoutNode);
			GraphvizAPI.twopiCleanup(graphvizGraph);
		} else {
			GraphvizAPI.doDotLayout(graphvizGraph);
			GraphvizAPI.attachAttributes(graphvizGraph);
			mapGraphviz2LayoutNode(layoutNode);
			GraphvizAPI.dotCleanup(graphvizGraph);
		}

		/* should Emma debug? */
		if (Activator.getDefault().getPreferenceStore().getBoolean(
				PreferenceConstants.PREF_GRAPHVIZ_ENABLE_DEBUG_OUTPUT)) {

			String outputName = Integer.toString(layoutNode.hashCode());
			String outputDir = Activator.getDefault().getPreferenceStore()
					.getString(PreferenceConstants.PREF_GRAPHVIZ_DEBUG_DIR);
			if (outputDir.equals("")) {
				outputDir = System.getProperty("user.home");

			}
			GraphvizAPI.writeDOT(graphvizGraph, outputDir + "/" + outputName
					+ ".dot");
		}

		/* cleanup */
		mapNode2Pointer.clear();
		mapEdge2Pointer.clear();
		progressMonitor.done();
	}

	/**
	 * Maps a KNode to the internal GraphvizAPI data structure. This is stored in
	 * the GraphvizAPI internally.
	 * 
	 * @param layoutNode
	 *            A KNode with the graph to lay out, the actual nodes of
	 *            the graph are stored as subgroups of the layoutNode.
	 */
	private void mapLayoutNode2Graphviz(KNode layoutNode) {
		int i = 0;
		/*
		 * First process all nodes to have the pointers for them when creating
		 * the edges.
		 */
		for (KNode subNode : layoutNode.getChildren()) {
			int pointer = GraphvizAPI.createNode(graphvizGraph, String
					.valueOf(i));
			mapNode2Pointer.put(subNode, new Integer(pointer));
			String label = subNode.getLabel().getText();
			if (label == null)
				label = "";
			/*
			 * Use NumberFormat to format the number into a String to workaround
			 * different possible locales of machines on that GraphViz could run
			 * (could result in different number formats, e.g. 0.33 on English
			 * local, 0,33 on German)
			 */
			KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(subNode);
			String height = pixels2GraphVizInches((int) shapeLayout.getHeight());
			String width = pixels2GraphVizInches((int) shapeLayout.getWidth());
			GraphvizAPI.setLocalNodeAttribute(graphvizGraph, pointer, "label",
					label);
			GraphvizAPI.setLocalNodeAttribute(graphvizGraph, pointer, "height",
					height);
			GraphvizAPI.setLocalNodeAttribute(graphvizGraph, pointer, "width",
					width);
			i++;
		}

		/*
		 * Then create the edges
		 */
		for (KNode subNode : layoutNode.getChildren()) {
			for (KEdge outgoingEdge : subNode.getOutgoingEdges()) {
				if (mapNode2Pointer.get(outgoingEdge.getTarget()) != null) {

					// create actual edge
					int pointer = GraphvizAPI.createEdge(graphvizGraph,
							mapNode2Pointer.get(subNode), mapNode2Pointer
									.get(outgoingEdge.getTarget()));
					GraphvizAPI.setLocalEdgeAttribute(graphvizGraph, pointer,
							"arrowhead", "none");
					GraphvizAPI.setLocalEdgeAttribute(graphvizGraph, pointer,
							"arrowtail", "none");
					// store edge in map
					mapEdge2Pointer.put(outgoingEdge, new Integer(pointer));

					// process labels of edge
					for (KLabel label : outgoingEdge.getLabels()) {

						/*
						 * As graphViz just handles three labels properly (well,
						 * actually just two, the mid and tail label), hard code
						 * it here. First 'normal' label.
						 */
					    KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(label);
					    EdgeLabelPlacement labelPlacement = LayoutOptions.getEdgeLabelPlacement(shapeLayout);
						if (labelPlacement == EdgeLabelPlacement.CENTER) {
							String labelString = label.getText();

							GraphvizAPI.setLocalEdgeAttribute(graphvizGraph,
									pointer, GraphvizAPI.ATTR_LABEL,
									labelString);
						}
						/*
						 * Give a try for head label.
						 */
						else if (labelPlacement == EdgeLabelPlacement.HEAD) {
							String labelString = label.getText();

							GraphvizAPI.setLocalEdgeAttribute(graphvizGraph,
									pointer, GraphvizAPI.ATTR_HEADLABEL,
									labelString);
						}
						/*
						 * Give a try for tail label.
						 */
						else if (labelPlacement == EdgeLabelPlacement.TAIL) {
							String labelString = label.getText();

							GraphvizAPI.setLocalEdgeAttribute(graphvizGraph,
									pointer, GraphvizAPI.ATTR_TAILLABEL,
									labelString);
						}
					}
				}
			}
		}
	}

	/**
	 * Reads the internal GraphViz data structure that was filled by the
	 * GraphViz library and writes the required parameters back to the
	 * {@link de.cau.cs.kieler.kiml.layout.KimlLayoutGraph.KNode
	 * KNode}.
	 * 
	 * @param layoutNode the KNode to be filled with the layout information
	 */
	private void mapGraphviz2LayoutNode(KNode layoutNode) {
		mapGraphvizNodes2KNodes();
		mapGraphvizEdges2KEdges();
		setTopNodeAttributes(layoutNode);
	}

	/**
	 * Writes all node related information from GraphViz into the KNode.
	 * Uses HashMaps for the mapping.
	 */
	private void mapGraphvizNodes2KNodes() {
		/*
		 * iterate over all nodes in the GraphViz graph and copy their
		 * attributes to the Graph data structure
		 */
		for (KNode layoutNode : mapNode2Pointer.keySet()) {

			int nodePointer = mapNode2Pointer.get(layoutNode);
			String posString = GraphvizAPI.getAttribute(nodePointer,
					GraphvizAPI.ATTR_POS);
			String heightString = GraphvizAPI.getAttribute(nodePointer,
					GraphvizAPI.ATTR_HEIGHT);
			String widthString = GraphvizAPI.getAttribute(nodePointer,
					GraphvizAPI.ATTR_WIDTH);
			KPoint location = KLayoutDataFactory.eINSTANCE.createKPoint();
			KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
			try {
				List<Integer> position = string2Ints(posString);
				// use NumberFormat for parsing, see respective methods below
				shapeLayout.setHeight(graphVizInches2Pixels(heightString));
				shapeLayout.setWidth(graphVizInches2Pixels(widthString));
				// in GraphViz position is the center of the node
				// in draw2D it's the upper left corner
				location = graphviz2KPoint(position.get(0).intValue(), position
						.get(1).intValue(), shapeLayout.getWidth(),
						shapeLayout.getHeight());

			} catch (Exception exception) {
				/* nothing, might have been invalid String */
				Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						"Error while mapping nodes (" + posString + ")", exception);
				StatusManager.getManager().handle(status, StatusManager.SHOW);
			}
			shapeLayout.setXpos(location.getX());
			shapeLayout.setYpos(location.getY());
		}
	}

	/**
	 * Writes all edge related information from GraphViz into the the
	 * KNode. Uses HashMaps for the mapping.
	 */
	private void mapGraphvizEdges2KEdges() {
		/*
		 * iterate over all edges in the GraphViz graph and copy their
		 * attributes to the Graph data structure
		 */
		for (KEdge edge : mapEdge2Pointer.keySet()) {

			int edgePointer = mapEdge2Pointer.get(edge);
			String posString = GraphvizAPI.getAttribute(edgePointer,
					GraphvizAPI.ATTR_POS);
			List<Integer> intList = string2Ints(posString);
			try {
				/*
				 * ars, 2008-11-15: when setting arrowhead to 'none' than there
				 * is no separate end coordinate for the edge given, as assumed
				 * before.
				 * 
				 * ars, 2008-11-21, GraphViz uses cubic B-Splines, some
				 * generaliZation of Bezier Curves. The B-Splines are converted
				 * here to a polyline that Eclipse understands
				 * 
				 * Addressing Bezier curves in eclipse:
				 * 
				 * @see Eclipse bug: 234808 and 168307
				 */

				/* first two points in list denote the start point */
			    KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
			    edgeLayout.setSourcePoint(
						graphviz2KPoint(intList.get(0), intList.get(1)));

				for (int i = 0; i < intList.size() - 7; i += 6) {
					/* convert the bezier representation to a poly line */
					bezierToPolyline(edgeLayout, new Point(intList
							.get(i + 0), intList.get(i + 1)), new Point(intList
							.get(i + 2), intList.get(i + 3)), new Point(intList
							.get(i + 4), intList.get(i + 5)), new Point(intList
							.get(i + 6), intList.get(i + 7)));
				}
				/*
				 * need to remove the last grid point, as this is the same as
				 * the target point below
				 */
				edgeLayout.getBendPoints().remove(
				        edgeLayout.getBendPoints().size() - 1);

				/* last two points in the GraphViz list denote the end point */
				edgeLayout.setTargetPoint(
						graphviz2KPoint(intList.get(intList.size() - 2),
								intList.get(intList.size() - 1)));

				/* tell all users that we produced some sort of spline */
				//edgeLayout.getOptions().add(new KIntOption(KEdgeType.SPLINE));

			} catch (Exception exception) {
				/* in any failure, leave list empty */
				Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						"Error while mapping edges (" + posString + ")", exception);
				StatusManager.getManager().handle(status, StatusManager.SHOW);
			}
			/*
			 * Process labels, there is a maximum of three that can be handled
			 * by GraphViz. Well, actually two with locations.
			 */
			for (KLabel label : edge.getLabels()) {
			    KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(label);
			    EdgeLabelPlacement labelPlacement = LayoutOptions.getEdgeLabelPlacement(shapeLayout);

				// head label
				if (labelPlacement == EdgeLabelPlacement.HEAD) {
					;/*
					 * not possible to get the head label (placement) with
					 * GraphViz
					 */
				}

				/* mid label */
				else if (labelPlacement == EdgeLabelPlacement.CENTER) {
					String midLoc = GraphvizAPI.getAttribute(edgePointer,
							GraphvizAPI.ATTR_LP);
					List<Integer> midInts = string2Ints(midLoc);
					KPoint midLocation = KLayoutDataFactory.eINSTANCE
							.createKPoint();
					if (midInts.size() == 2) {
						midLocation = graphviz2KPoint(
								midInts.get(0).intValue() + 2, midInts.get(1)
										.intValue(), shapeLayout.getWidth(),
										shapeLayout.getHeight());
					}
					shapeLayout.setXpos(midLocation.getX());
					shapeLayout.setYpos(midLocation.getY());
				}

				/* tail label */
				else if (labelPlacement == EdgeLabelPlacement.TAIL) {
					String tailLoc = GraphvizAPI.getAttribute(edgePointer,
							GraphvizAPI.ATTR_TAILLP);
					List<Integer> tailInts = string2Ints(tailLoc);
					KPoint tailLocation = KLayoutDataFactory.eINSTANCE
							.createKPoint();
					if (tailInts.size() == 2) {
						/* small adjust of size, and therefore of position */
					    shapeLayout.setHeight(shapeLayout.getHeight() + 7);
						tailLocation = graphviz2KPoint(tailInts.get(0)
								.intValue(), tailInts.get(1).intValue(),
								shapeLayout.getWidth(), shapeLayout.getHeight());
					}
					shapeLayout.setXpos(tailLocation.getX());
                    shapeLayout.setYpos(tailLocation.getY());
				}
			}
		}
	}

	/**
	 * Sets the required size of the top KNode by obtaining the bounding
	 * box of the GraphViz Graph, resulting from the positions of the contained
	 * sub nodes. If insets are given in the top KNode, they are added to
	 * the size. Insets denote the difference between the resulting size of the
	 * area the contained elements cover after the layout process and the real
	 * size the composite node should have. An example is a compartment with a
	 * header. The size (height) of the header has to be added to the desired
	 * resulting size of the top KNode.
	 * 
	 * @param layoutNode the top KNode to set the size of
	 */
	private void setTopNodeAttributes(KNode layoutNode) {
		Dimension bb = GraphvizAPI.getBoundingBox(graphvizGraph);
        KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        float left = 0, right = 0, bottom = 0, top = 0;
        try {
            KInsets insets = LayoutOptions.getInsets(shapeLayout);
            top = insets.getTop();
            bottom = insets.getBottom();
            left = insets.getLeft();
            right = insets.getRight();
        } catch (Exception e) {
            /* no insets available, stay silent */
        }
        shapeLayout.setWidth((bb.width + 2 * prefPadX) + left + right);
        shapeLayout.setHeight((bb.height + 2 * prefPadY) + top + bottom);
	}

	/**
	 * Transforms pixel into a GraphViz inch string, taking care of the
	 * platform's current locale settings. Under different locales, the height
	 * and width values of GraphViz use dots, respective periods.
	 * 
	 * @param pixels
	 *            the pixels to transform
	 * @return a localized inch string
	 */
	private String pixels2GraphVizInches(int pixels) {
		return NumberFormat.getInstance().format(pixels / (float) dpi);
	}

	/**
	 * Transforms GraphViz written inches into pixel, using the internal dpi
	 * factor and taking care of the platform's current locale settings. Under
	 * different locales, the height and width values of GraphViz use dots,
	 * respective periods.
	 * 
	 * @param dotInches
	 *            localized inches to transform into pixels
	 * @return pixel corresponding to the provided inches
	 */
	private int graphVizInches2Pixels(String dotInches) throws ParseException {
		return (int) (NumberFormat.getInstance().parse(dotInches).floatValue() * dpi);
	}

	/**
	 * Transforms GraphvizCoordinates to
	 * {@link de.cau.cs.kieler.kiml.layout.KimlLayoutGraph.KPoint
	 * KPoint} (KLayoutGraph) coordinates. The size of the provided item is used
	 * to adjust the resulting location. Padding is also used.
	 * 
	 * @param x
	 *            GraphViz x coordinate
	 * @param y
	 *            GraphViz y coordinate
	 * @param size
	 *            Size of the item. Required because GraphViz coordinates are at
	 *            the center of any item and KPoint coordinates are at the upper
	 *            left corner.
	 * @return KPoint the location of the item in terms of the upper left corner
	 */
	private KPoint graphviz2KPoint(int x, int y, float width, float height) {
		KPoint newLocation = KLayoutDataFactory.eINSTANCE.createKPoint();
		newLocation.setX(x - (width / 2) + prefPadX);
		newLocation.setY(y - (height / 2) + prefPadY);
		return newLocation;
	}

	/**
	 * Transforms GraphvizCoordinates to
	 * {@link de.cau.cs.kieler.kiml.layout.KimlLayoutGraph.KPoint
	 * KPoint} (KLayoutGraph) coordinates. Padding is also used.
	 * 
	 * @param location
	 *            GraphViz Coordinates
	 * @return Draw2D coordinates
	 */
	private KPoint graphviz2KPoint(int x, int y) {
		KPoint newLocation = KLayoutDataFactory.eINSTANCE.createKPoint();
		newLocation.setX(x + prefPadX);
		newLocation.setY(y + prefPadY);
		return newLocation;
	}

	private void bezierToPolyline(KEdgeLayout layout, Point p0, Point p1,
			Point p2, Point p3) {

		/*
		 * as the start point is not added below, that means the number of
		 * points added to the polyline are the integer below -1
		 */
		int numberOfPoints = 4;
		float dt;
		int i;

		dt = (float) (1.0 / (numberOfPoints - 1));
		float ax, bx, cx;
		float ay, by, cy;
		float tSquared, tCubed;

		cx = (float) (3.0 * (p1.x - p0.x));
		bx = (float) (3.0 * (p2.x - p1.x) - cx);
		ax = p3.x - p0.x - cx - bx;

		cy = (float) (3.0 * (p1.y - p0.y));
		by = (float) (3.0 * (p2.y - p1.y) - cy);
		ay = p3.y - p0.y - cy - by;

		for (i = 1; i < numberOfPoints; i++) {
			float t = i * dt;

			tSquared = t * t;
			tCubed = tSquared * t;
			int x = (int) (((ax * tCubed) + (bx * tSquared) + (cx * t)) + p0.x);
			int y = (int) (((ay * tCubed) + (by * tSquared) + (cy * t)) + p0.y);
			layout.getBendPoints().add(graphviz2KPoint(x, y));
		}
	}

	/**
	 * Converts a string containing a list of integers into a List of Integer
	 * objects. Used for converting GraphViz position Strings (e.g. list of
	 * bendpoints) into a real list.
	 * 
	 * @param integerStringList
	 *            A comma separated string with integers
	 * @return A list holding all the provided Integers of the string
	 */
	private List<Integer> string2Ints(String integerStringList) {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		if (integerStringList != null) {
			/* \s = any whitespace char */
			String[] tokens = integerStringList.split(",|\\s");
			for (int i = 0; i < tokens.length; i++) {
				try {
					intList.add(new Integer(tokens[i]));
				} catch (Exception e) {
					/* nothing */
				}
			}
		}
		return intList;
	}
}
