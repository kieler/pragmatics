/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter;

/**
 * Defines constants used in the Graphviz Dot language.
 *
 * @author <a href="mailto:tkl@informatik.uni-kiel.de">Tobias Kloss</a>
 */
public final class GraphvizAPI {
    
    /** Style of arrowhead on the head node of an edge. */
    public static final String ATTR_ARROWHEAD     = "arrowhead";
    /** Style of arrowhead on the tail node of an edge. */
    public static final String ATTR_ARROWTAIL     = "arrowtail";
    /** Bounding box. */
    public static final String ATTR_BOUNDINGBOX   = "bb";
    /** Comment. */
    public static final String ATTR_COMMENT       = "comment";
    /** This specifies the expected number of pixels per inch on a display device. */
    public static final String ATTR_DPI           = "dpi";
    /** If true, the node size is specified by the values of the width
     * and height attributes only and is not expanded to contain the text label. */
    public static final String ATTR_FIXEDSIZE     = "fixedsize";
    /** Font used for text. */
    public static final String ATTR_FONTNAME      = "fontname";
    /** Font size, in points, used for text. */
    public static final String ATTR_FONTSIZE      = "fontsize";
    /** Text label to be placed near head of edge. */
    public static final String ATTR_HEADLABEL     = "headlabel";
    /** Head label position. */
    public static final String ATTR_HEADLP        = "head_lp";
    /** Height of node, in inches. */
    public static final String ATTR_HEIGHT        = "height";
    /** Text label attached to objects. */
    public static final String ATTR_LABEL         = "label";
    /** This, along with labeldistance, determine where the headlabel
     * (taillabel) are placed with respect to the head (tail) in polar coordinates. */
    public static final String ATTR_LABELANGLE    = "labelangle";
    /** Multiplicative scaling factor adjusting the distance that the
     * headlabel(taillabel) is from the head(tail) node. */
    public static final String ATTR_LABELDISTANCE = "labeldistance";
    /** Font used for headlabel and taillabel. */
    public static final String ATTR_LABELFONTNAME = "labelfontname";
    /** Font size, in points, used for headlabel and taillabel. */
    public static final String ATTR_LABELFONTSIZE = "labelfontsize";
    /** Justification for cluster labels. */
    public static final String ATTR_LABELJUST     = "labeljust";
    /** Top/bottom placement of graph and cluster labels. */
    public static final String ATTR_LABELLOC      = "labelloc";
    /** Label position, in points. */
    public static final String ATTR_LP            = "lp";
    /** Position of node, or spline control points. */
    public static final String ATTR_POS           = "pos";
    /** Sets direction of graph layout. */
    public static final String ATTR_RANKDIR       = "rankdir";
    /** If 90, set drawing orientation to landscape. */    
    public static final String ATTR_ROTATE        = "rotate";
    /** Set the shape of a node. */
    public static final String ATTR_SHAPE         = "shape";
    /** Text label to be placed near tail of edge. */
    public static final String ATTR_TAILLABEL     = "taillabel";
    /** Tail label position. */
    public static final String ATTR_TAILLP        = "tail_lp";
    /** Weight of edge. */
    public static final String ATTR_WEIGHT        = "weight";
    /** Width of node, in inches. */
    public static final String ATTR_WIDTH         = "width";

}
