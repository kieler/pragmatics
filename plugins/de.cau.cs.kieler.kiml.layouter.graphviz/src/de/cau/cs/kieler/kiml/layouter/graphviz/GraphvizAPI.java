/*******************************************************************************
 * Copyright (c) 2008 Real-Time and Embedded Systems group
 *
 * INSERT LICENCE HERE
 *
 *
 * Author: Tobias Kloss, tkl@informatik.uni-kiel.de 
 *
 *******************************************************************************/
package de.cau.cs.kieler.kiml.layouter.graphviz;

import java.awt.Dimension;
import java.util.StringTokenizer;

/**
 * <p>
 * Description: Defines the JNI interface to the graphviz library.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Uni Kiel
 * </p>
 *
 * @author <a href="mailto:tkl@informatik.uni-kiel.de">Tobias Kloss </a>
 * @version $Revision$
 */
public final class GraphvizAPI {
    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_BOUNDINGBOX   = "bb";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_DPI           = "dpi";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_FIXEDSIZE     = "fixedsize";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_FONTNAME      = "fontname";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_FONTSIZE      = "fontsize";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_HEADLABEL     = "headlabel";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_HEIGHT        = "height";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_LABEL         = "label";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_LABELANGLE    = "labelangle";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_LABELDISTANCE = "labeldistance";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_LABELFONTNAME = "labelfontname";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_LABELFONTSIZE = "labelfontsize";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_LABELJUST     = "labeljust";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_LABELLOC      = "labelloc";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_LP            = "lp";
    
    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_HEADLP            = "head_lp";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_POS           = "pos";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_RANKDIR       = "rankdir";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_SHAPE         = "shape";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_TAILLABEL     = "taillabel";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_TAILLP        = "tail_lp";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_WEIGHT        = "weight";

    /**
     * This is a graphviz attribute.
     */
    protected static final String ATTR_WIDTH         = "width";

    /**
     * This is a graphviz attribute.
     */    
    protected static final String ATTR_ROTATE        = "rotate";
    
    static {
    	System.loadLibrary("GraphvizAPI");
    }

    /**
     * This is the native method to attach layout informations to graph.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void attachAttributes(final int graph);

    /**
     * This is the native method to invoke the circo cleanup routine.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void circoCleanup(final int graph);

    /**
     * This is the native method to close the graph.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void closeGraph(final int graph);

    /**
     * This is the native method to create an edge.
     *
     * @param graph
     *            pointer to the C graph object
     * @param source
     *            pointer to the C node object of source node
     * @param target
     *            pointer to the C node object of target node
     * @return pointer to the C edge object
     */
    protected static native int createEdge(final int graph,
            final int source, final int target);

    /**
     * This is the native method to create the graph.
     *
     * @param name
     *            graph's name
     * @return pointer to the C graph object
     */
    protected static native int createGraph(final String name);

    /**
     * This is the native method to create a node.
     *
     * @param graph
     *            pointer to the C graph object
     * @param name
     *            node's name
     * @return pointer to the C node object
     */
    protected static native int createNode(final int graph, final String name);

    /**
     * This is the native method to invoke the circo layout.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void doCircoLayout(final int graph);

    /**
     * This is the native method to invoke the dot layout.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void doDotLayout(final int graph);

    /**
     * This is the native method to invoke the neato layout.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void doNeatoLayout(final int graph);

    /**
     * This is the native method to invoke the dot cleanup routine.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void dotCleanup(final int graph);

    /**
     * This is the native method to invoke the twopi layout.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void doTwopiLayout(final int graph);

    /**
     * This is the native method to get an attribute.
     *
     * @param elem
     *            pointer to the C [graph|node|edge] object
     * @param attr
     *            attribute's name (see constants)
     * @return attribute's value
     */
    protected static native String getAttribute(final int elem,
            final String attr);

    /**
     * Returns the boundling box of the layouted graph.
     *
     * @param graph
     *            pointer to the C graph object
     * @return bounding box of the layouted graph
     */
    protected static Dimension getBoundingBox(final int graph) {
        StringTokenizer st;
        String attributeString;
        String widthString;
        String heightString;

        attributeString = getAttribute(graph, ATTR_BOUNDINGBOX);
        st = new StringTokenizer(attributeString, ",");
        st.nextToken(); // can be ignored, always 0
        st.nextToken(); // can be ignored, always 0
        widthString = st.nextToken();
        heightString = st.nextToken();

        return new Dimension(Integer.parseInt(widthString), Integer
                .parseInt(heightString));
    }

    /**
     * Returns the path of the edge.
     *
     * @param edge
     *            pointer to the C edge object
     * @param layerHeight
     *            the height of the bounding box
     *            (used to invert the y coordinate)
     * @return SVG Path of <code>edge</code>
     */
/*    protected static ArrayList getEdgePath(final int edge,
            final int layerHeight) {
        ArrayList path = new ArrayList();
        String posString;
        StringTokenizer posTokenizer;
        Point endPoint = null;
        Point startPoint = null;
        Point moveToPoint = null;

        posString = getAttribute(edge, ATTR_POS);
        posTokenizer = new StringTokenizer(posString, " ");
        while (moveToPoint == null) {
            String token = posTokenizer.nextToken();
            StringTokenizer pointTokenizer = new StringTokenizer(token, ",");

            if (pointTokenizer.countTokens() > 2) {
                if (pointTokenizer.nextToken().equalsIgnoreCase("e")) {
                    endPoint = new Point(Integer.parseInt(
                            pointTokenizer.nextToken()),
                            layerHeight
                            - Integer.parseInt(pointTokenizer.nextToken()));
                } else {
                    startPoint = new Point(
                            Integer.parseInt(pointTokenizer.nextToken()),
                            layerHeight
                            - Integer.parseInt(pointTokenizer.nextToken()));
                }
            } else {
                moveToPoint = new Point(Integer.parseInt(
                        pointTokenizer.nextToken()),
                        layerHeight
                        - Integer.parseInt(pointTokenizer.nextToken()));
                path.add(new MovetoPath(moveToPoint));
                if (startPoint != null) {
                    path.add(new LinetoPath(startPoint));
                    path.add(new MovetoPath(moveToPoint));
                }
            }
        }

        while (posTokenizer.hasMoreTokens()) {
            String token;
            StringTokenizer pointTokenizer;

            token = posTokenizer.nextToken();
            pointTokenizer = new StringTokenizer(token, ",");
            Point cubic1 = new Point(
                    Integer.parseInt(pointTokenizer.nextToken()),
                    layerHeight - Integer.parseInt(pointTokenizer.nextToken()));

            token = posTokenizer.nextToken();
            pointTokenizer = new StringTokenizer(token, ",");
            Point cubic2 = new Point(
                    Integer.parseInt(pointTokenizer.nextToken()),
                    layerHeight - Integer.parseInt(pointTokenizer.nextToken()));

            token = posTokenizer.nextToken();
            pointTokenizer = new StringTokenizer(token, ",");
            Point cubic3 = new Point(
                    Integer.parseInt(pointTokenizer.nextToken()),
                    layerHeight - Integer.parseInt(pointTokenizer.nextToken()));

            path.add(new CurvetoPath(cubic1, cubic2, cubic3));
        }

        if (endPoint != null) {
            path.add(new LinetoPath(endPoint));
        }

        return path;
    }
*/

    /**
     * Returns the position of the label.
     *
     * @param edge
     *            pointer to the C edge object
     * @param layerHeight
     *            the height of the bounding box
     *            (used to invert the y coordinate)
     * @return center point of edge's label
     */
/*    protected static Point getLabelPos(final int edge, final int layerHeight) {
        Point pos = new Point(-1, -1);
        String posString;
        StringTokenizer st;

        posString = getAttribute(edge, ATTR_LP);
        if ((posString != null) && (posString.length() > 0)) {
            st = new StringTokenizer(posString, ",");
            pos = new Point(Integer.parseInt(st.nextToken()),
                    layerHeight - Integer.parseInt(st.nextToken()));
        } else {
            pos = new Point(0, 0);
        }

        return pos;
    }
*/
    /**
     * Returns the label as string.
     *
     * @param elem
     *            pointer to the C [graph|node|edge] object
     * @return the label string of the element
     */
    protected static String getLabelString(final int elem) {
        return getAttribute(elem, ATTR_LABEL);
    }

    /**
     * Returns position of the node.
     *
     * @param node
     *            pointer to the C node object
     * @param layerHeight
     *            the height of the bounding box
     *            (used to invert the y coordinate)
     * @return center point of <code>node</code>
     */
/*    protected static Point getNodePos(final int node, final int layerHeight) {
        Point pos = new Point(-1, -1);
        String posString;
        StringTokenizer st;

        posString = getAttribute(node, ATTR_POS);
        if (posString.length() > 0) {
            st = new StringTokenizer(posString, ",");
            pos = new Point(Integer.parseInt(st.nextToken()),
                    layerHeight - Integer.parseInt(st.nextToken()));
        }

        return pos;
    }
*/
    
    /**
     * Returns the position of the priority.
     *
     * @param edge
     *            pointer to the C edge object
     * @param layerHeight
     *            the height of the bounding box
     *            (used to invert the y coordinate)
     * @return center point of edge's priority
     */
/*    protected static Point getPrioPos(final int edge, final int layerHeight) {
        Point pos = new Point(-1, -1);
        String posString;
        StringTokenizer st;

        posString = getAttribute(edge, ATTR_TAILLP);
        if ((posString != null) && (posString.length() > 0)) {
            st = new StringTokenizer(posString, ",");
            pos = new Point(Integer.parseInt(st.nextToken()),
                    layerHeight - Integer.parseInt(st.nextToken()));
        } else {
            pos = new Point(0, 0);
        }

        return pos;
    }
*/
    
    /**
     * Returns the priority as string.
     *
     * @param edge
     *            pointer to the C edge object
     * @return the label string of the element
     */
    protected static String getTailLabelString(final int edge) {
        return getAttribute(edge, ATTR_TAILLABEL);
    }

    /**
     * This is the native method to get the used graphviz version number.
     *
     * @return a string containing the graphviz version number
     */
    protected static native String getVersionString();

    /**
     * This is the native method to initialize the library.
     */
    protected static native void initialize();

    /**
     * This is the native method to invoke the neato cleanup routine.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void neatoCleanup(final int graph);

    /**
     * This is the native method to set a global edge attribute.
     *
     * @param graph
     *            pointer to the C graph object
     * @param attr
     *            attribute's name (see constants)
     * @param value
     *            attribute's value
     */
    protected static native void setGlobalEdgeAttribute(final int graph,
            final String attr, final String value);

    /**
     * This is the native method to set a global node attribute.
     *
     * @param graph
     *            pointer to the C graph object
     * @param attr
     *            attribute's name (see constants)
     * @param value
     *            attribute's value
     */
    protected static native void setGlobalNodeAttribute(final int graph,
            final String attr, final String value);

    /**
     * This is the native method to set a graph attribute.
     *
     * @param graph
     *            pointer to the C graph object
     * @param attr
     *            attribute's name (see constants)
     * @param value
     *            attribute's value
     */
    protected static native void setGraphAttribute(final int graph,
            final String attr, final String value);

    /**
     * This is the native method to set a local edge attribute.
     *
     * @param graph
     *            pointer to the C graph object
     * @param edge
     *            pointer to the C edge object
     * @param attr
     *            attribute's name (see constants)
     * @param value
     *            attribute's value
     */
    protected static native void setLocalEdgeAttribute(final int graph,
            final int edge, final String attr, final String value);

    /**
     * This is the native method to set a local node attribute.
     *
     * @param graph
     *            pointer to the C graph object
     * @param node
     *            pointer to the C node object
     * @param attr
     *            attribute's name (see constants)
     * @param value
     *            attribute's value
     */
    protected static native void setLocalNodeAttribute(final int graph,
            final int node, final String attr, final String value);

    /**
     * This is the native method to invoke the twopi cleanup routine.
     *
     * @param graph
     *            pointer to the C graph object
     */
    protected static native void twopiCleanup(final int graph);

    /**
     * This is the native method to write graph into dot file.
     *
     * @param graph
     *            pointer to the C graph object
     * @param filename
     *            the name of the dot file
     */
    protected static native void writeDOT(final int graph,
            final String filename);


    /**
     * This is the costructor.
     */
    private GraphvizAPI() {
    }
}
