/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import net.ogdf.lib.FastPlanarSubgraph;
import net.ogdf.lib.LayoutModule;
import net.ogdf.lib.OrthoDir;
import net.ogdf.lib.OrthoLayout;
import net.ogdf.lib.PlanarizationLayout;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * The Mixed-Upward Planarization layouter from the OGDF library.
 * 
 * @author mri
 */
public class PlanarizationLayouter extends OgdfLayouter {

    /** layout option identifier for the page ratio. */
    public static final String OPT_PAGE_RATIO = "de.cau.cs.kieler.kiml.ogdf.option.pageRatio";
    /** layout option identifier for clique preprocessing. */
    public static final String OPT_PRE_CLIQUES = "de.cau.cs.kieler.kiml.ogdf.option.preCliques";
    /** layout option identifier for the minimum clique size. */
    public static final String OPT_MIN_CLIQUE_SIZE = "de.cau.cs.kieler.kiml.ogdf.option.minCliqueSize";
    /** layout option identifier for the number of runs. */
    public static final String OPT_RUNS = "de.cau.cs.kieler.kiml.ogdf.option.runs";
    /** layout option identifier for separation distance. */
    public static final String OPT_SEPARATION = "de.cau.cs.kieler.kiml.ogdf.option.separation";
    /** layout option identifier for overhang. */
    public static final String OPT_OVERHANG = "de.cau.cs.kieler.kiml.ogdf.option.cOverhang";
    /** layout option identifier for margin. */
    public static final String OPT_MARGIN = "de.cau.cs.kieler.kiml.ogdf.option.margin";
    /** layout option identifier for cost assoc. */
    public static final String OPT_COST_ASSOC = "de.cau.cs.kieler.kiml.ogdf.option.costAssoc";
    /** layout option identifier for cost gen. */
    public static final String OPT_COST_GEN = "de.cau.cs.kieler.kiml.ogdf.option.costGen";
    /** default value for page ratio. */
    public static final float DEF_PAGE_RATIO = 1.0f;
    /** default value for clique preprocessing. */
    public static final boolean DEF_PRE_CLIQUES = true;
    /** default value for minimum clique size. */
    public static final int DEF_MIN_CLIQUE_SIZE = 10;
    /** default value for number of runs. */
    public static final int DEF_RUNS = 0;
    /** default value for separation distance. */
    public static final float DEF_SEPARATION = 40.0f;
    /** default value for overhang. */
    public static final float DEF_OVERHANG = 0.2f;
    /** default value for margin. */
    public static final float DEF_MARGIN = 40.0f;
    /** default value for direction. */
    public static final LayoutDirection DEF_DIRECTION = LayoutDirection.UP;
    /** default value for cost assoc. */
    public static final int DEF_COST_ASSOC = 1;
    /** default value for cost gen. */
    public static final int DEF_COST_GEN = 4;
    /** default value for border spacing. */
    public static final float DEF_BORDER_SPACING = 8;
    /** default value for label edge distance. */
    public static final float DEF_LABEL_EDGE_DISTANCE = 15.0f;
    /** default value for label margin distance. */
    public static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;

    /**
     * {@inheritDoc}
     */
    protected LayoutModule prepareLayouter(final KNode layoutNode) {
        // create the layouter
        PlanarizationLayout layout = new PlanarizationLayout();

        // set options
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);

        // set page ratio
        float pageRatio = LayoutOptions.getFloat(parentLayout, OPT_PAGE_RATIO);
        if (Float.isNaN(pageRatio)) {
            pageRatio = DEF_PAGE_RATIO;
        }
        layout.pageRatio(pageRatio);

        // set clique preprocessing
        boolean preClique = LayoutOptions.getBoolean(parentLayout,
                OPT_PRE_CLIQUES);
        layout.preprocessCliques(preClique);

        // set minimum clique size
        int minCliqueSize = LayoutOptions.getInt(parentLayout,
                OPT_MIN_CLIQUE_SIZE);
        if (minCliqueSize == Integer.MAX_VALUE
                || minCliqueSize == Integer.MIN_VALUE) {
            minCliqueSize = DEF_MIN_CLIQUE_SIZE;
        }
        layout.minCliqueSize(minCliqueSize);

        FastPlanarSubgraph fps = new FastPlanarSubgraph();

        // set number of runs
        int runs = LayoutOptions.getInt(parentLayout, OPT_RUNS);
        if (runs == Integer.MAX_VALUE || runs == Integer.MIN_VALUE) {
            runs = DEF_RUNS;
        }
        fps.runs(runs);

        // set subgraph module
        layout.setSubgraph(fps);

        OrthoLayout ol = new OrthoLayout();

        // set separation
        float separation = LayoutOptions.getFloat(parentLayout, OPT_SEPARATION);
        if (Float.isNaN(separation)) {
            separation = DEF_SEPARATION;
        }
        ol.separation(separation);

        // set overhang
        float overhang = LayoutOptions.getFloat(parentLayout, OPT_OVERHANG);
        if (Float.isNaN(overhang)) {
            overhang = DEF_OVERHANG;
        }
        ol.cOverhang(overhang);

        // set margin
        float margin = LayoutOptions.getFloat(parentLayout, OPT_MARGIN);
        if (Float.isNaN(margin)) {
            margin = DEF_MARGIN;
        }
        ol.margin(margin);

        // set cost assoc
        int costAssoc = LayoutOptions.getInt(parentLayout, OPT_COST_ASSOC);
        if (costAssoc == Integer.MAX_VALUE || costAssoc == Integer.MIN_VALUE) {
            costAssoc = DEF_COST_ASSOC;
        }
        ol.costAssoc(costAssoc);

        // set cost gen
        int costGen = LayoutOptions.getInt(parentLayout, OPT_COST_GEN);
        if (costGen == Integer.MAX_VALUE || costGen == Integer.MIN_VALUE) {
            costGen = DEF_COST_GEN;
        }
        ol.costGen(costGen);

        // set layout direction
        LayoutDirection direction = LayoutOptions.getEnum(parentLayout,
                LayoutDirection.class);
        OrthoDir orthoDirection;
        switch (direction) {
        case DOWN:
            orthoDirection = OrthoDir.odNorth;
            break;
        case LEFT:
            orthoDirection = OrthoDir.odWest;
            break;
        case RIGHT:
            orthoDirection = OrthoDir.odEast;
            break;
        case UP:
            orthoDirection = OrthoDir.odSouth;
            break;
        case UNDEFINED:
        default:
            orthoDirection = OrthoDir.odUndefined;
            break;
        }
        ol.preferedDir(orthoDirection);

        // set the planar layouter
        layout.setPlanarLayouter(ol);
        
        // perform pre-processing
        preProcess(layoutNode);

        return layout;
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (optionId.equals(LayoutOptions.BORDER_SPACING)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(LayoutOptions.LAYOUT_DIRECTION)) {
            return DEF_DIRECTION;
        } else if (optionId.equals(OPT_PAGE_RATIO)) {
            return DEF_PAGE_RATIO;
        } else if (optionId.equals(OPT_PRE_CLIQUES)) {
            return DEF_PRE_CLIQUES;
        } else if (optionId.equals(OPT_MIN_CLIQUE_SIZE)) {
            return DEF_MIN_CLIQUE_SIZE;
        } else if (optionId.equals(OPT_RUNS)) {
            return DEF_RUNS;
        } else if (optionId.equals(OPT_SEPARATION)) {
            return DEF_SEPARATION;
        } else if (optionId.equals(OPT_OVERHANG)) {
            return DEF_OVERHANG;
        } else if (optionId.equals(OPT_MARGIN)) {
            return DEF_MARGIN;
        } else if (optionId.equals(OPT_COST_ASSOC)) {
            return DEF_COST_ASSOC;
        } else if (optionId.equals(OPT_COST_GEN)) {
            return DEF_COST_GEN;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_EDGE_DISTANCE;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
    
    private List<Pair<KEdge, KNode>> selfLoops = new LinkedList<Pair<KEdge, KNode>>();
    
    /**
     * Removes all self-loops from the graph, as they are not supported by the planarization
     * layouter.
     * 
     * @param layoutNode the parent node
     */
    private void preProcess(final KNode layoutNode) {
        for (KNode child : layoutNode.getChildren()) {
            ListIterator<KEdge> edgeIter = child.getOutgoingEdges().listIterator();
            while (edgeIter.hasNext()) {
                KEdge edge = edgeIter.next();
                if (edge.getTarget() == child) {
                    edgeIter.remove();
                    edge.setTarget(null);
                    selfLoops.add(new Pair<KEdge, KNode>(edge, child));
                }
            }
        }
    }
    
    /** fixed distance of self loops to their node. */
    private static final float LOOP_DIST = 12.0f;
    /** starting relative position of self loops. */
    private static final float START_POS = 0.6f;
    
    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        // manually layout all self loops
        PortSide side = PortSide.UNDEFINED;
        float relStart = 0, relEnd = 0, distance = 0;
        KNode lastNode = null;
        for (Pair<KEdge, KNode> selfLoop : selfLoops) {
            KEdge edge = selfLoop.getFirst();
            KNode node = selfLoop.getSecond();
            if (node != lastNode) {
                side = PortSide.NORTH;
                relStart = START_POS;
                relEnd = 1 - START_POS;
                distance = LOOP_DIST;
            }
            lastNode = node;
            edge.setSource(node);
            edge.setTarget(node);
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(node);
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
            edgeLayout.getBendPoints().clear();
            KPoint sourcePoint = createPoint(nodeLayout, side, relStart);
            edgeLayout.setSourcePoint(sourcePoint);
            side = side.right();
            KPoint targetPoint = createPoint(nodeLayout, side, relEnd);
            edgeLayout.setTargetPoint(targetPoint);
            addBendPoints(edgeLayout, sourcePoint, targetPoint, side, distance);
            layoutLabels(edge, side, distance);
            if (side == PortSide.NORTH) {
                relEnd = relEnd / 2;
                relStart = 1 - relEnd;
                distance += LOOP_DIST;
            }
        }
    }
    
    /**
     * Creates a point on the border of the given node layout.
     * 
     * @param nodeLayout the node layout
     * @param side the side on which the point is created
     * @param relPos the relative position on the node side
     * @return a point on the given side
     */
    private KPoint createPoint(final KShapeLayout nodeLayout, final PortSide side, final float relPos) {
        KPoint point = KLayoutDataFactory.eINSTANCE.createKPoint();
        float offsetx = nodeLayout.getXpos();
        float offsety = nodeLayout.getYpos();
        switch (side) {
        case NORTH:
            point.setX(offsetx + nodeLayout.getWidth() * relPos);
            point.setY(offsety);
            break;
        case EAST:
            point.setX(offsetx + nodeLayout.getWidth());
            point.setY(offsety + nodeLayout.getHeight() * relPos);
            break;
        case SOUTH:
            point.setX(offsetx + nodeLayout.getWidth() * (1 - relPos));
            point.setY(offsety + nodeLayout.getHeight());
            break;
        case WEST:
            point.setX(offsetx);
            point.setY(offsety + nodeLayout.getHeight() * (1 - relPos));
            break;
        }
        return point;
    }

    /**
     * Add the needed bend points to the given edge layout.
     * 
     * @param edgeLayout an edge layout
     * @param sourcePoint the source point
     * @param targetPoint the target point
     * @param side the node side of the target point
     * @param distance distance to the node
     */
    private void addBendPoints(final KEdgeLayout edgeLayout, final KPoint sourcePoint,
            final KPoint targetPoint, final PortSide side, final float distance) {
        KPoint point1 = KLayoutDataFactory.eINSTANCE.createKPoint();
        KPoint point2 = KLayoutDataFactory.eINSTANCE.createKPoint();
        KPoint point3 = KLayoutDataFactory.eINSTANCE.createKPoint();
        switch (side) {
        case EAST:
            point1.setX(sourcePoint.getX());
            point1.setY(sourcePoint.getY() - distance);
            point3.setX(targetPoint.getX() + distance);
            point3.setY(targetPoint.getY());
            break;
        case SOUTH:
            point1.setX(sourcePoint.getX() + distance);
            point1.setY(sourcePoint.getY());
            point3.setX(targetPoint.getX());
            point3.setY(targetPoint.getY() + distance);
            break;
        case WEST:
            point1.setX(sourcePoint.getX());
            point1.setY(sourcePoint.getY() + distance);
            point3.setX(targetPoint.getX() - distance);
            point3.setY(targetPoint.getY());
            break;
        case NORTH:
            point1.setX(sourcePoint.getX() - distance);
            point1.setY(sourcePoint.getY());
            point3.setX(targetPoint.getX());
            point3.setY(targetPoint.getY() - distance);
            break;
        }
        if (side == PortSide.EAST || side == PortSide.WEST) {
            point2.setX(point3.getX());
            point2.setY(point1.getY());
        } else {
            point2.setX(point1.getX());
            point2.setY(point3.getY());
        }
        edgeLayout.getBendPoints().add(point1);
        edgeLayout.getBendPoints().add(point2);
        edgeLayout.getBendPoints().add(point3);
    }
    
    /**
     * Layouts the labels of the given self loop.
     * 
     * @param edge the edge whose labels are to be layouted
     * @param side the side of the target point of the self loop
     * @param distance to the node
     */
    private void layoutLabels(final KEdge edge, final PortSide side, final float distance) {
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
        KPoint sourcePoint = edgeLayout.getSourcePoint();
        KPoint targetPoint = edgeLayout.getTargetPoint();
        List<KLabel> centerLabels = new LinkedList<KLabel>();
        List<KLabel> tailLabels = new LinkedList<KLabel>();
        List<KLabel> headLabels = new LinkedList<KLabel>();
        float centerHeight = 0, centerWidth = 0, tailHeight = 0, tailWidth = 0,
                headHeight = 0, headWidth = 0;
        for (KLabel label : edge.getLabels()) {
            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
            switch (LayoutOptions.getEnum(labelLayout, EdgeLabelPlacement.class)) {
            case CENTER:
                centerLabels.add(label);
                centerHeight += labelLayout.getHeight();
                centerWidth = Math.max(centerWidth, labelLayout.getWidth());
                break;
            case HEAD:
                headLabels.add(label);
                headHeight += labelLayout.getHeight();
                headWidth = Math.max(headWidth, labelLayout.getWidth());
                break;
            case TAIL:
                tailLabels.add(label);
                tailHeight += labelLayout.getHeight();
                tailWidth = Math.max(tailWidth, labelLayout.getWidth());
                break;
            }
        }
        switch (side) {
        case EAST:
            putLabels(tailLabels, sourcePoint.getX() + 2,
                    sourcePoint.getY() - tailHeight - 2);
            if (tailHeight > distance) {
                putLabels(centerLabels, sourcePoint.getX() + tailWidth,
                        sourcePoint.getY() - distance - centerHeight - 2);
            } else {
                putLabels(centerLabels, sourcePoint.getX() + 2,
                        sourcePoint.getY() - distance - centerHeight - 2);
            }
            putLabels(headLabels, targetPoint.getX() + 2,
                    targetPoint.getY() - headHeight - 2);
            break;
        case SOUTH:
            putLabels(tailLabels, sourcePoint.getX() + 2, sourcePoint.getY() + 2);
            if (tailWidth > distance) {
                putLabels(centerLabels, sourcePoint.getX() + distance + 2,
                        sourcePoint.getY() + tailHeight + 2);
            } else {
                putLabels(centerLabels, sourcePoint.getX() + distance + 2,
                        sourcePoint.getY() + 2);
            }
            putLabels(headLabels, targetPoint.getX() + 2, targetPoint.getY() + 2);
            break;
        case WEST:
            putLabels(tailLabels, sourcePoint.getX() - tailWidth - 2,
                    sourcePoint.getY() + 2);
            if (tailHeight > distance) {
                putLabels(centerLabels, sourcePoint.getX() - tailWidth - centerWidth - 2,
                        sourcePoint.getY() + distance + 2);
            } else {
                putLabels(centerLabels, sourcePoint.getX() - centerWidth - 2,
                        sourcePoint.getY() + distance + 2);
            }
            putLabels(headLabels, targetPoint.getX() - headWidth - 2,
                    targetPoint.getY() + 2);
            break;
        case NORTH:
            putLabels(tailLabels, sourcePoint.getX() - tailWidth - 2,
                    sourcePoint.getY() - tailHeight - 2);
            if (tailWidth > distance) {
                putLabels(centerLabels, sourcePoint.getX() - distance - centerWidth - 2,
                        sourcePoint.getY() - tailHeight - centerHeight - 2);
            } else {
                putLabels(centerLabels, sourcePoint.getX() - distance - centerWidth - 2,
                        sourcePoint.getY() - centerHeight - 2);
            }
            putLabels(headLabels, targetPoint.getX() - headWidth - 2,
                    targetPoint.getY() - headHeight - 2);
            break;
        }
    }
    
    /**
     * Places the given labels one on top of each other at the given position.
     * 
     * @param labels list of labels
     * @param xpos x coordinate
     * @param ypos y coordinate
     */
    private void putLabels(final List<KLabel> labels, final float xpos, final float ypos) {
        float y = ypos;
        for (KLabel label : labels) {
            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
            labelLayout.setXpos(xpos);
            labelLayout.setYpos(y);
            y += labelLayout.getHeight();
        }
    }
    
}
