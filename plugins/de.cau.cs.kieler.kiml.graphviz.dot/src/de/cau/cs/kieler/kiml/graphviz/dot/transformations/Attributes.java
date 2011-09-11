/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.dot.transformations;

/**
 * Definition of Graphviz attributes.
 *
 * @author msp
 */
public final class Attributes {

    /**
     * Hidden default constructor to avoid instantiation.
     */
    private Attributes() {
    }
    
    /** Bounding box. */
    public static final String BOUNDINGBOX = "bb";
    /** Comment. */
    public static final String COMMENT = "comment";
    /** If true, allow edges between clusters. */
    public static final String COMPOUND = "compound";
    /** Set edge type for drawing arrowheads. */
    public static final String EDGEDIR = "dir";
    /** Preferred edge length, in inches (fdp, neato only). */
    public static final String EDGELEN = "len";
    /**
     * If true, the node size is specified by the values of the width and height
     * attributes only and is not expanded to contain the text label.
     */
    public static final String FIXEDSIZE = "fixedsize";
    /** Font used for text. */
    public static final String FONTNAME = "fontname";
    /** Font size, in points, used for text. */
    public static final String FONTSIZE = "fontsize";
    /** Text label to be placed near head of edge. */
    public static final String HEADLABEL = "headlabel";
    /** Head label position, in points. */
    public static final String HEADLP = "head_lp";
    /** Height of node, in inches. */
    public static final String HEIGHT = "height";
    /** Text label attached to objects. */
    public static final String LABEL = "label";
    /**
     * This, along with labeldistance, determine where the headlabel (taillabel)
     * are placed with respect to the head (tail) in polar coordinates.
     */
    public static final String LABELANGLE = "labelangle";
    /**
     * Multiplicative scaling factor adjusting the distance that the
     * headlabel(taillabel) is from the head(tail) node.
     */
    public static final String LABELDISTANCE = "labeldistance";
    /** Label position, in points. */
    public static final String LABELPOS = "lp";
    /** Logical head of an edge. */
    public static final String LHEAD = "lhead";
    /** Logical tail of an edge. */
    public static final String LTAIL = "ltail";
    /** Determines if and how node overlaps should be removed (not dot). */
    public static final String OVERLAP = "overlap";
    /** Position of node, or spline control points. */
    public static final String POS = "pos";
    /** Specifies the minimum separation between all nodes (circo only). */
    public static final String MINDIST = "mindist";
    /** Sets direction of graph layout (dot only). */
    public static final String RANKDIR = "rankdir";
    /**
     * In dot, this gives the desired rank separation, in inches. In twopi,
     * specifies radial separation of concentric circles. (twopi, dot only)
     */
    public static final String RANKSEP = "ranksep";
    /** Set the shape of a node. */
    public static final String SHAPE = "shape";
    /**
     * Controls how, and if, edges are represented. If true, edges are drawn as
     * splines routed around nodes; if false, edges are drawn as line segments.
     */
    public static final String SPLINES = "splines";
    /**
     * Parameter used to determine the initial layout of nodes (fdp, neato
     * only).
     */
    public static final String START = "start";
    /** Set style for node or edge. */
    public static final String STYLE = "style";
    /** Text label to be placed near tail of edge. */
    public static final String TAILLABEL = "taillabel";
    /** Tail label position, in points. */
    public static final String TAILLP = "tail_lp";
    /** Width of node, in inches. */
    public static final String WIDTH = "width";

}
