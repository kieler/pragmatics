/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.dot.transform;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Definition of Graphviz attributes.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class Attributes {
    
    /**
     * Hidden default constructor to avoid instantiation.
     */
    private Attributes() {
    }
    
    /** Target aspect ratio (width of the layout divided by the height) of the graph drawing. */
    public static final String ASPECT = "aspect";
    
    /** Whether ports should be moved to the point where edges cross the node's bounds. */
    public static final IProperty<Boolean> ADAPT_PORT_POSITIONS = new Property<Boolean>(
            "de.cau.cs.kieler.graphviz.adaptPortPositions", false);
    
    /** Bounding box. */
    public static final String BOUNDINGBOX = "bb";
    
    /** Comment. */
    public static final String COMMENT = "comment";
    
    /** If true, allow edges between clusters. */
    public static final String COMPOUND = "compound";
    
    /** If true, use edge concentrators. This merges multiedges into a single edge and
     *  causes partially parallel edges to share part of their paths. */
    public static final String CONCENTRATE = "concentrate";
    /** edge concentration property. */
    public static final IProperty<Boolean> CONCENTRATE_PROP = new Property<Boolean>(
            "de.cau.cs.kieler.graphviz.concentrate", false);
    
    /** Multiplicative scale factor used to alter the MinQuit (default = 8) and MaxIter
     *  (default = 24) parameters used during crossing minimization. */
    public static final String CROSSMIN_LIMIT = "mclimit";
    /** iterations limit property (includes {@link #CROSSMIN_LIMIT} and {@link #SIMPLEX_LIMIT}). */
    public static final IProperty<Float> ITER_LIMIT_PROP = new Property<Float>(
            "de.cau.cs.kieler.graphviz.iterationsLimit", -1.0f);
    
    /** Factor damping force motions. On each iteration, a nodes movement is limited to this
     *  factor of its potential motion. */
    public static final String DAMPING = "Damping";
    /** damping property. */
    public static final IProperty<Float> DAMPING_PROP = new Property<Float>(
            "de.cau.cs.kieler.graphviz.damping", -1.0f);
    
    
    /** Set edge type for drawing arrowheads. */
    public static final String EDGEDIR = "dir";
    
    /** Preferred edge length, in inches (fdp, neato only). */
    public static final String EDGELEN = "len";
    
    /** Terminating condition. If the length squared of all energy gradients
     *  are < epsilon, the algorithm stops. */
    public static final String EPSILON = "epsilon";
    /** epsilon value property. */
    public static final IProperty<Float> EPSILON_PROP = new Property<Float>(
            "de.cau.cs.kieler.graphviz.epsilon", -1.0f);
    
    /** If true, the node size is specified by the values of the width and height
     *  attributes only and is not expanded to contain the text label. */
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
    
    /** This, along with labeldistance, determine where the headlabel (taillabel)
     *  are placed with respect to the head (tail) in polar coordinates. */
    public static final String LABELANGLE = "labelangle";
    /** label angle property. */
    public static final IProperty<Float> LABEL_ANGLE_PROP = new Property<Float>(
            "de.cau.cs.kieler.graphviz.labelAngle", -25.0f);
    
    /** Multiplicative scaling factor adjusting the distance that the
     *  head label (tail label) is from the head (tail) node. */
    public static final String LABELDISTANCE = "labeldistance";
    /** label distance property. */
    public static final IProperty<Float> LABEL_DISTANCE_PROP = new Property<Float>(
            "de.cau.cs.kieler.graphviz.labelDistance", 1.0f);
        
    /** Label position, in points. */
    public static final String LABELPOS = "lp";
    
    /** Specifies the name of the layout algorithm to use, such as "dot" or "neato". */
    public static final String LAYOUT = "layout";
    
    /** Logical head of an edge. */
    public static final String LHEAD = "lhead";
    
    /** Logical tail of an edge. */
    public static final String LTAIL = "ltail";
    
    /** Determines if and how node overlaps should be removed (not dot). */
    public static final String OVERLAP = "overlap";
    /** node overlap removal property. */
    public static final IProperty<OverlapMode> OVERLAP_PROP = new Property<OverlapMode>(
            "de.cau.cs.kieler.graphviz.overlapMode", OverlapMode.PRISM);
    
    /** If true, each connected component of the graph is laid out separately, and then
     *  the graphs are packed together. */
    public static final String PACK = "pack";
    
    /** The pad attribute specifies how much, in inches, to extend the drawing area around
     *  the minimal area needed to draw the graph. */
    public static final String PAD = "pad";
    
    /** Position of node, or spline control points. */
    public static final String POS = "pos";
    
    /** For graphs, this sets x and y margins of canvas, in inches. If the margin is a
     *  single double, both margins are set equal to the given value. */
    public static final String MARGIN = "margin";
    
    /** Sets the number of iterations used. */
    public static final String MAXITER = "maxiter";
    /** maximum number of iterations property. */
    public static final IProperty<Integer> MAXITER_PROP = new Property<Integer>(
            "de.cau.cs.kieler.graphviz.maxiter", -1);
    
    /** Specifies the minimum separation between all nodes (circo only). */
    public static final String MINDIST = "mindist";
    
    /** Specifies how the distance matrix is computed for the input graph. */
    public static final String NEATO_MODEL = "model";
    /** Neato distance model property. */
    public static final IProperty<NeatoModel> NEATO_MODEL_PROP = new Property<NeatoModel>(
            "de.cau.cs.kieler.graphviz.neatoModel", NeatoModel.SHORTPATH);
    
    /** Minimum space between two adjacent nodes in the same rank, in inches. */
    public static final String NODESEP = "nodesep";
    
    /** Used to set number of iterations in network simplex applications. */
    public static final String SIMPLEX_LIMIT = "nslimit";
    
    /** Sets direction of graph layout (dot only). */
    public static final String RANKDIR = "rankdir";
    
    /** In dot, this gives the desired rank separation, in inches. In twopi,
     *  specifies radial separation of concentric circles. (twopi, dot only) */
    public static final String RANKSEP = "ranksep";
    /** property for the factor for rank separation used in dot. */
    public static final IProperty<Float> RANK_SEP_PROP = new Property<Float>(
            "de.cau.cs.kieler.graphviz.layerSpacingFactor", 1.0f, 0.0f);
    
    /** Specifies margin to leave around nodes when removing node overlap. */
    public static final String SEP = "sep";
    
    /** Set the shape of a node. */
    public static final String SHAPE = "shape";
    
    /** Controls how, and if, edges are represented. If true, edges are drawn as
     *  splines routed around nodes; if false, edges are drawn as line segments. */
    public static final String SPLINES = "splines";
    
    /** Spring constant used in virtual physical model. */
    public static final String SPRING_CONSTANT = "K";
    
    /** Parameter used to determine the initial layout of nodes (fdp, neato only). */
    public static final String START = "start";
    
    /** Set style for node or edge. */
    public static final String STYLE = "style";
    
    /** Text label to be placed near tail of edge. */
    public static final String TAILLABEL = "taillabel";
    
    /** Tail label position, in points. */
    public static final String TAILLP = "tail_lp";
    
    /** Weight of edge. In dot, the heavier the weight, the shorter, straighter
     *  and more vertical the edge is. */
    public static final String WEIGHT = "weight";

    /** Width of node, in inches. */
    public static final String WIDTH = "width";

}
