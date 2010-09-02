/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package net.ogdf.lib;

/**
 * The java-side wrapper for the OGDF library.
 * 
 * @author mri
 */
public class Ogdf {

	/** the association edge type. */
	public static final int EDGE_TYPE_ASSOCIATION = 0;
	/** the generalization edge type. */
	public static final int EDGE_TYPE_GENERALIZATION = 1;
	/** the dependency edge type. */
	public static final int EDGE_TYPE_DEPENDENCY = 2;

	/** the end1 label type. */
	public static final int LABEL_TYPE_END1 = 0;
	/** the mult1 label type. */
	public static final int LABEL_TYPE_MULT1 = 1;
	/** the name label type. */
	public static final int LABEL_TYPE_NAME = 2;
	/** the end2 label type. */
	public static final int LABEL_TYPE_END2 = 3;
	/** the mult2 label type. */
	public static final int LABEL_TYPE_MULT2 = 4;

	/** the north direction. */
	public static final int DIRECTION_NORTH = 0;
	/** the south direction. */
	public static final int DIRECTION_SOUTH = 1;
	/** the west direction. */
	public static final int DIRECTION_WEST = 2;
	/** the east direction. */
	public static final int DIRECTION_EAST = 3;

	/** the GorgeousAndEfficient option. */
	public static final int GORGEOUS_AND_EFFICIENT = 0;
	/** the BeautifulAndFast option. */
	public static final int BEAUTIFUL_AND_FAST = 1;
	/** the NiceAndIncredibleSpeed option. */
	public static final int NICE_AND_INCREDIBLE_SPEED = 2;

	/** the standard cost option. */
	public static final int COSTS_STANDARD = 0;
	/** the repulse cost option. */
	public static final int COSTS_REPULSE = 1;
	/** the planar cost option. */
	public static final int COSTS_PLANAR = 2;

	/** the fast speed option. */
	public static final int SPEED_FAST = 0;
	/** the medium speed option. */
	public static final int SPEED_MEDIUM = 1;
	/** the hq speed option. */
	public static final int SPEED_HQ = 2;

	/** the top-to-bottom orientation option. */
	public static final int ORIENTATION_TOP_TO_BOTTOM = 0;
	/** the bottom-to-top orientation option. */
	public static final int ORIENTATION_BOTTOM_TO_TOP = 1;
	/** the left-to-right orientation option. */
	public static final int ORIENTATION_LEFT_TO_RIGHT = 2;
	/** the right-to-left orientation option. */
	public static final int ORIENTATION_RIGHT_TO_LEFT = 3;

	/** whether the library was loaded successfully. */
	private static boolean loaded = false;

	/**
	 * Load the native OGDF library.
	 * 
	 * @throws UnsatisfiedLinkError
	 *             if the library is not found or cannot be loaded
	 */
	public static synchronized void loadLibrary() throws UnsatisfiedLinkError {
		if (!loaded) {
			String os = System.getProperty("os.name");
			if (os.toLowerCase().contains("win")) {
				System.loadLibrary("libgcc_s_dw2-1");
				System.loadLibrary("libstdc++-6");
			}
			System.loadLibrary("ogdf");
		}
	}

	/*
	 * Global ogdf
	 */

	/**
	 * Creates new Graph and GraphAttributes and sets them as the current graph.
	 * If umlGraph is {@code true} an UMLGraph is created instead of the
	 * GraphAttributes.<br>
	 * This method has to be called everytime before a layout graph-build and
	 * layout process. Also see {@code cleanup}.
	 * 
	 * @param umlGraph
	 *            true if the graph is an uml graph
	 */
	public final static native void createGraph(boolean umlGraph);

	/**
	 * Adds a new node to the current graph.
	 * 
	 * @param x
	 *            the nodes x-coordinate
	 * @param y
	 *            the nodes y-coordinate
	 * @param w
	 *            the nodes width
	 * @param h
	 *            the nodes height
	 * @return a unique identifier for the node
	 */
	public final static native long Graph_addNode(float x, float y, float w,
			float h);

	/**
	 * Adds a new edge to the current graph.
	 * 
	 * @param node1
	 *            the edges source node identifier
	 * @param node2
	 *            the edges target node identifier
	 * @return the edge identifier
	 */
	public final static native long Graph_addEdge(long node1, long node2);

	/**
	 * Sets the edge type of the given edge.
	 * 
	 * @param edge
	 *            the edge
	 * @param type
	 *            the edge type (one of EDGE_TYPE_ASSOCIATION,
	 *            EDGE_TYPE_GENERALIZATION or EDGE_TYPE_DEPENDENCY)
	 */
	public final static native void Graph_setEdgeType(long edge, int type);

	/**
	 * Returns the nodes x-coordinate.
	 * 
	 * @param node
	 *            the node identifier
	 * @return the x-coordinate
	 */
	public final static native float Graph_getNodeX(long node);

	/**
	 * Returns the nodes y-coordinate.
	 * 
	 * @param node
	 *            the node identifier
	 * @return the y-coordinate
	 */
	public final static native float Graph_getNodeY(long node);

	/**
	 * Calculates the current bounding box and stores it as the current bounding
	 * box.
	 */
	public static final native void Graph_getBoundingBox();

	/**
	 * Returns the x-coordinate of the current bounding box.
	 * 
	 * @return the bounding boxes x-coordinate
	 */
	public static final native float Graph_getBoundingBoxX();

	/**
	 * Returns the y-coordinate of the current bounding box.
	 * 
	 * @return the bounding boxes y-coordinate
	 */
	public static final native float Graph_getBoundingBoxY();

	/**
	 * Returns the width of the graphs bounding box.
	 * 
	 * @return the bounding boxes width
	 */
	public final static native float Graph_getBoundingBoxWidth();

	/**
	 * Returns the height of the graphs bounding box.
	 * 
	 * @return the bounding boxes height
	 */
	public final static native float Graph_getBoundingBoxHeight();

	/**
	 * Returns the number of bendpoints for the given edge.
	 * 
	 * @param edge
	 *            the edge identifier
	 * @return the number of bends
	 */
	public final static native int Graph_getNumberOfBends(long edge);

	/**
	 * Adds intersections of the bends and the node center with the nodes
	 * bounding boxes to the list of bends.
	 */
	public final static native void Graph_addNodeCenter2Bends();

	/**
	 * Creates an iterator for the edge and sets it as the current bends
	 * iterator.
	 * 
	 * @param edge
	 *            the edge identifier
	 */
	public final static native void createBendsInterator(long edge);

	/**
	 * Returns whether the current bends iterator can iterate more bend points.
	 * 
	 * @return true if the bends iterator can iterate more bend points
	 */
	public final static native boolean BendsIterator_hasNext();

	/**
	 * Moves the current bends iterator forward.
	 */
	public final static native void BendsIterator_next();

	/**
	 * Returns the x-coordinate of the bend the current bends iterator is
	 * pointing at.
	 * 
	 * @return the x-coordinate of the bend
	 */
	public final static native float BendsIterator_getX();

	/**
	 * Returns the y-coordinate of the bend the current bends iterator is
	 * pointing at.
	 * 
	 * @return the y-coordinate of the bend
	 */
	public final static native float BendsIterator_getY();

	/*
	 * Label layouter
	 */

	/**
	 * Creates a new ELabelInterface and sets it as the current label interface.<br>
	 * This requires a preceding call to {@code createGraph}.<br>
	 * Furthermore his has to be called before any other Label and LabelLayouter
	 * method and after any call to {@code cleanup}.
	 */
	public final static native void createLabelInterface();

	/**
	 * Adds an edge label to the label interface.
	 * 
	 * @param edge
	 *            the edge identifier
	 * @param type
	 *            the label type (one of LABEL_TYPE_END1, LABEL_TYPE_MULT1,
	 *            LABEL_TYPE_NAME, LABEL_TYPE_END2 or LABEL_TYPE_MULT2)
	 * @param width
	 *            the label width
	 * @param height
	 *            the label height
	 */
	public final static native void Label_addLabel(long edge, int type,
			float width, float height);

	/**
	 * Returns the x-coordinate of an edge label.
	 * 
	 * @param edge
	 *            the edge
	 * @param type
	 *            the label type (one of LABEL_TYPE_END1, LABEL_TYPE_MULT1,
	 *            LABEL_TYPE_NAME, LABEL_TYPE_END2 or LABEL_TYPE_MULT2)
	 * @return the labels x-coordinate
	 */
	public final static native float Label_getX(long edge, int type);

	/**
	 * Returns the y-coordinate of an edge label.
	 * 
	 * @param edge
	 *            the edge
	 * @param type
	 *            the label type (one of LABEL_TYPE_END1, LABEL_TYPE_MULT1,
	 *            LABEL_TYPE_NAME, LABEL_TYPE_END2 or LABEL_TYPE_MULT2)
	 * @return the labels y-coordinate
	 */
	public final static native float Label_getY(long edge, int type);

	/**
	 * Creates a new ELabelPosSimple and sets it as the current label layouter.<br>
	 * This has to be called before any other label layouter method and after
	 * any call to {@code cleanup}.
	 */
	public final static native void createLabelLayouter();

	/**
	 * Sets the edge distance option for the label layouter.
	 * 
	 * @param distance
	 *            the edge distance
	 */
	public final static native void LabelLayouter_setEdgeDistance(float distance);

	/**
	 * Sets the margin distance option for the label layouter.
	 * 
	 * @param distance
	 *            the margin distance
	 */
	public final static native void LabelLayouter_setMarginDistance(
			float distance);

	/**
	 * Layouts the current label interface with the current label layouter.
	 */
	public final static native void LabelLayouter_layout();

	/*
	 * Sugiyama layouter
	 */

	/**
	 * Creates a new Sugiyama layouter.
	 * 
	 * @param nodeDistance
	 *            the node distance
	 * @param layerDistance
	 *            the layer distance
	 */
	public final static native void createSugiyamaLayouter(float nodeDistance,
			float layerDistance);

	/*
	 * Mixed-Upward Planarization
	 */

	/**
	 * Creates a new Mixed-Upward Planarization layouter.
	 * 
	 * @param pageRatio
	 *            the desired page ratio
	 * @param separation
	 *            the separation distance
	 * @param direction
	 *            the layout direction (one of DIRECTION_NORTH, DIRECTION_SOUTH,
	 *            DIRECTION_WEST or DIRECTION_EAST)
	 */
	public final static native void createMixedUpwardPlanarizationLayouter(
			float pageRatio, float separation, int direction);

	/*
	 * FMMM layouter
	 */

	/**
	 * Creates a new FMMM layouter.
	 * 
	 * @param unitEdgeLength
	 *            the unit edge length
	 * @param newInitialPlacement
	 *            true if the algorithm should start with different starting
	 *            positions every time
	 * @param qualityVsSpeed
	 *            the QualityVsSpeed option (one of GORGEOUS_AND_EFFICIENT,
	 *            BEAUTIFUL_AND_FAST or NICE_AND_INCREDIBLE_SPEED)
	 */
	public final static native void createFMMMLayouter(float unitEdgeLength,
			boolean newInitialPlacement, int qualityVsSpeed);

	/**
	 * Creates a new FMMM layouter with low level options.
	 * 
	 * @param coolTemperature
	 *            the cool temperature
	 * @param coolValue
	 *            the cool value
	 * @param fineTuneScalar
	 *            the fine tune scalar
	 * @param fineTuningIterations
	 *            the fine tuning iterations
	 * @param fixedIterations
	 *            the fixed iterations
	 * @param forceScalingFactor
	 *            the force scaling factor
	 * @param gridQuotient
	 *            the grid quotient
	 * @param maxIterFactor
	 *            the max iter factor
	 * @param minDistCC
	 *            the min dist cc
	 * @param minGraphSize
	 *            the min graph size
	 * @param particlesInLeaves
	 *            the particles in leaves
	 * @param precision
	 *            the precision
	 * @param postSpringStrength
	 *            the post spring strength
	 * @param strengthOfRepForces
	 *            the strength of rep forces
	 * @param randomTries
	 *            the random tries
	 * @param repForcesStrength
	 *            the rep forces strength
	 * @param springStrength
	 *            the spring strength
	 * @param stepsForRotatingComponents
	 *            the steps for rotating components
	 * @param threshold
	 *            the threshold
	 */
	public final static native void createFMMMLayouterDetail(
			boolean coolTemperature, float coolValue, float fineTuneScalar,
			int fineTuningIterations, int fixedIterations,
			float forceScalingFactor, int gridQuotient, int maxIterFactor,
			float minDistCC, int minGraphSize, int particlesInLeaves,
			int precision, float postSpringStrength, float strengthOfRepForces,
			int randomTries, float repForcesStrength, float springStrength,
			int stepsForRotatingComponents, float threshold);

	/*
	 * Davidson-Harel layouter
	 */

	/**
	 * Creates the Davidson-Harel layouter.
	 * 
	 * @param costs
	 *            the costs option (one of COSTS_STANDARD, COSTS_REPULSE or
	 *            COSTS_PLANAR)
	 * @param speed
	 *            the speed option (one of SPEED_FAST, SPEED_MEDIUM or SPEED_HQ)
	 * @param edgeLength
	 *            the desired edge length
	 */
	public final static native void createDavidsonHarelLayouter(int costs,
			int speed, float edgeLength);

	/*
	 * SpringEmbedderFR layouter
	 */

	/**
	 * Creates the Spring Embedder layouter by Fruchterman and Reingold.
	 * 
	 * @param iterations
	 *            the number of iterations that are performed by the algorithm
	 */
	public final static native void createSpringEmbedderFRLayouter(
			int iterations);

	/*
	 * Circular layouter
	 */

	/**
	 * Creates the circular layouter.
	 * 
	 * @param minDistCircle
	 *            the minimum distance between nodes on a circle
	 * @param minDistLevel
	 *            the minimum distance between father and child circle
	 * @param minDistSibling
	 *            the minimum distance between circles on the same level
	 * @param minDistCC
	 *            the minimum distance between connected components
	 */
	public final static native void createCircularLayouter(float minDistCircle,
			float minDistLevel, float minDistSibling, float minDistCC);

	/*
	 * Tree layouter
	 */

	/**
	 * Creates the tree layouter.
	 * 
	 * @param siblingDistance
	 *            the horizontal spacing between adjacent sibling nodes
	 * @param subtreeDistance
	 *            the horizontal spacing between adjacent subtrees
	 * @param levelDistance
	 *            the vertical spacing between adjacent levels
	 * @param treeDistance
	 *            the horizontal spacing between adjacent trees in a forest
	 * @param orthogonal
	 *            determines whether edges are routed in an orthogonal or
	 *            straight-line fashion
	 * @param orientation
	 *            determines if the orientation of the tree (one of
	 *            ORIENTATION_TOP_TO_BOTTOM, ORIENTATION_BOTTOM_TO_TOP,
	 *            ORIENTATION_LEFT_TO_RIGHT or ORIENTATION_RIGHT_TO_LEFT)
	 */
	public final static native void createTreeLayouter(float siblingDistance,
			float subtreeDistance, float levelDistance, float treeDistance,
			boolean orthogonal, int orientation);

	/**
	 * Creates the radial tree layouter.
	 * 
	 * @param minDistLevel
	 *            the minimum distance between father and child layer
	 * @param minDistCC
	 *            the minimum distance between connected components
	 */
	public final static native void createRadialTreeLayouter(
			float minDistLevel, float minDistCC);

	/*
	 * Upward Planarization
	 */

	/**
	 * Creates the upward planarization layouter.
	 */
	public final static native void createUpwardPlanarizationLayouter(
			float nodeDistance, float layerDistance);

	/*
	 * Layout and cleanup
	 */

	/**
	 * Layouts the current graph with the current layouter.
	 */
	public final static native void layout();

	/**
	 * Deallocates memory that was allocated during the last graph-build and
	 * layout process.<br>
	 * This method should be called everytime after such a process finished.
	 */
	public final static native void cleanup();
}
