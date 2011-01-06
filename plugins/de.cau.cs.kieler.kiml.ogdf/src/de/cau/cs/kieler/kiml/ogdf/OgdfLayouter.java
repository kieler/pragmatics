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

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import net.ogdf.lib.Ogdf;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.EdgeType;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.IDebugCanvas;

/**
 * The base wrapper class for all OGDF layouters.
 * 
 * @author mri
 */
public abstract class OgdfLayouter {

    /** default value for border spacing. */
    public static final float DEF_BORDER_SPACING = 15;
    
    /** layout option identifier for label edge distance. */
    public static final String LABEL_EDGE_DIST_ID
            = "de.cau.cs.kieler.kiml.ogdf.option.labelEdgeDistance";
    /** default value for label edge distance. */
    public static final float DEF_LABEL_EDGE_DIST = 15.0f;
    /** label edge distance property. */
    public static final IProperty<Float> LABEL_EDGE_DIST = new Property<Float>(
            LABEL_EDGE_DIST_ID, DEF_LABEL_EDGE_DIST);
    
    /** layout option identifier for label margin distance. */
    public static final String LABEL_MARGIN_DIST_ID
            = "de.cau.cs.kieler.kiml.ogdf.option.labelMarginDistance";
    /** default value for label margin distance. */
    public static final float DEF_LABEL_MARGIN_DIST = 15.0f;
    /** label margin distance property. */
    public static final IProperty<Float> LABEL_MARGIN_DIST = new Property<Float>(
            LABEL_MARGIN_DIST_ID, DEF_LABEL_MARGIN_DIST);
    
    /** the minimal distance between the source point and the first bend point. */
    private static final float SOURCE_POINT_FIRST_BEND_DISTANCE = 4;
    
    /** the debug canvas, which can be used to debug the OGDF wrapper. */
    private IDebugCanvas debugCanvas;

    /**
     * Set the debug canvas.
     * 
     * @param thecanvas the debug canvas
     */
    public void setDebugCanvas(final IDebugCanvas thecanvas) {
        this.debugCanvas = thecanvas;
    }
    
    /**
     * Initialize the default values for the layout provider. Subclasses should extend
     * this method with their own default values.
     * 
     * @param defaultsHolder the layout options holder for default values
     */
    public void initDefaults(final IPropertyHolder defaultsHolder) {
        defaultsHolder.setProperty(LayoutOptions.BORDER_SPACING, DEF_BORDER_SPACING);
        defaultsHolder.setProperty(LayoutOptions.RANDOM_SEED, 1);
    }
    
    /**
     * Layouts the given graph.
     * 
     * @param layoutNode
     *            the node representing the graph
     * @param progressMonitor
     *            the progress monitor
     * @throws KielerException
     *             if the layout failed
     */
    public void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("OGDF Layout", 1);
        Ogdf.loadLibrary();

        try {
            // set the random number generator seed
            setRandomSeed(layoutNode);
            // prepare the algorithm for use and pre-process the graph
            prepareLayouter(layoutNode);
            // transform the graph
            transformGraph(layoutNode);
            // call the algorithm to determine node and edge positions
            Ogdf.layout();
            // include intersections with the nodes bounding boxes in the bends
            Ogdf.Graph_addNodeCenter2Bends();
            // calculate new label positions and assign them to the mapped labels
            layoutLabels(layoutNode);
            // apply the layout back to the original graph
            applyLayout(layoutNode);
            // perform post-processing
            postProcess(layoutNode);
        } finally {
            // deallocate objects in the library
            Ogdf.cleanup();
            progressMonitor.done();
        }
    }
    
    /**
     * Set the random number generator seed.
     * 
     * @param node parent node from which the seed option is taken
     */
    private void setRandomSeed(final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        Integer seed = nodeLayout.getProperty(LayoutOptions.RANDOM_SEED);
        if (seed == null) {
            Ogdf.randSeed(1);
        } else if (seed == 0) {
            Ogdf.randSeed((int) System.currentTimeMillis());
        } else {
            Ogdf.randSeed(seed);
        }
    }

    /**
     * Sets the layout specific options and modules depending on the options
     * defined in the node.
     * 
     * @param layoutNode
     *            the parent node
     */
    protected abstract void prepareLayouter(final KNode layoutNode);

    /**
     * Performs post-processing on the given node. The default implementation
     * does nothing.
     * 
     * @param layoutNode
     *            the parent node
     */
    protected void postProcess(final KNode layoutNode) {
    }

    /**
     * Layout the edge labels.
     * 
     * @param layoutNode
     *            the parent layout node
     */
    protected void layoutLabels(final KNode layoutNode) {
        // create the label layouter
        Ogdf.createLabelLayouter();
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // TODO as soon as OGDF provides a better label layouter this should be reworked
        float edgeDistance = parentLayout.getProperty(LABEL_EDGE_DIST);
        if (edgeDistance < 0) {
            edgeDistance = DEF_LABEL_EDGE_DIST;
        }
        Ogdf.LabelLayouter_setEdgeDistance(edgeDistance);

        // get the margin distance
        float marginDistance = parentLayout.getProperty(LABEL_MARGIN_DIST);
        if (marginDistance < 0) {
            marginDistance = DEF_LABEL_MARGIN_DIST;
        }
        Ogdf.LabelLayouter_setMarginDistance(marginDistance);

        // call the label layouter
        Ogdf.LabelLayouter_layout();
    }

    /** map of KIELER nodes to OGDF node identifier. */
    private Map<KNode, Long> knode2ogdfNodeMap =
            new LinkedHashMap<KNode, Long>();
    /** map of KIELER edges to OGDF edge identifier. */
    private Map<KEdge, Long> kedge2ogdfEdgeMap =
            new LinkedHashMap<KEdge, Long>();

    /**
     * Transforms an ogdf point to a KPoint.
     * 
     * @param x
     *            the x coordinate of the ogdf point
     * @param y
     *            the y coordinate of the ogdf point
     * @return the point as KPoint
     */
    private KPoint toKPoint(final float x, final float y, final float offsetX,
            final float offsetY) {
        KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        kpoint.setX(x + offsetX);
        kpoint.setY(y + offsetY);
        return kpoint;
    }

    /**
     * Assigns OGDF geometry to a KShape layout.
     * 
     * @param layout
     *            the shape layout
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param width
     *            the width
     * @param height
     *            the height
     */
    private void toKShape(final KShapeLayout layout, final float x,
            final float y, final float width, final float height) {
        layout.setXpos(x - width / 2);
        layout.setYpos(y - height / 2);
    }

    /**
     * Determines whether the given graph should be layouted as UML graph.
     * 
     * @param layoutNode
     *            a parent graph
     * @return true if UML layout should be performed
     */
    protected boolean isUmlGraph(final KNode layoutNode) {
        for (KNode child : layoutNode.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                if (child.getParent() == kedge.getTarget().getParent()) {
                    EdgeType edgeType = kedge.getData(KEdgeLayout.class)
                            .getProperty(LayoutOptions.EDGE_TYPE);
                    if (edgeType == EdgeType.GENERALIZATION
                            || edgeType == EdgeType.ASSOCIATION
                            || edgeType == EdgeType.DEPENDENCY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Transforms the given layout graph into an OGDF graph.
     * 
     * @param layoutNode
     *            the parent node of the layout graph
     * @return an OGDF graph with attached layout attributes
     */
    private void transformGraph(final KNode layoutNode) {
        // reset the mapping
        knode2ogdfNodeMap.clear();
        kedge2ogdfEdgeMap.clear();

        // create the graph
        final boolean isUmlLayout = isUmlGraph(layoutNode);
        Ogdf.createGraph(isUmlLayout);

        // process nodes
        for (KNode knode : layoutNode.getChildren()) {
            KShapeLayout shapeLayout = knode.getData(KShapeLayout.class);
            long ogdfNode = Ogdf.Graph_addNode(
                            shapeLayout.getXpos() + shapeLayout.getWidth() / 2,
                            shapeLayout.getYpos() + shapeLayout.getHeight() / 2,
                            shapeLayout.getWidth(), shapeLayout.getHeight());
            knode2ogdfNodeMap.put(knode, ogdfNode);
        }

        // process edges
        for (KNode knode1 : layoutNode.getChildren()) {
            for (KEdge kedge : knode1.getOutgoingEdges()) {
                KNode knode2 = kedge.getTarget();
                if (knode2.getParent() == knode1.getParent()) {
                    long ogdfNode1 = knode2ogdfNodeMap.get(knode1);
                    long ogdfNode2 = knode2ogdfNodeMap.get(knode2);
                    long ogdfEdge = Ogdf.Graph_addEdge(ogdfNode1, ogdfNode2);
                    kedge2ogdfEdgeMap.put(kedge, ogdfEdge);
                    if (isUmlLayout) {
                        // set the hierarchy type of the edge
                        EdgeType edgeType = kedge.getData(KEdgeLayout.class)
                                .getProperty(LayoutOptions.EDGE_TYPE);
                        switch (edgeType) {
                        case ASSOCIATION:
                            Ogdf.Graph_setEdgeType(ogdfEdge,
                                    Ogdf.EDGE_TYPE_ASSOCIATION);
                            break;
                        case GENERALIZATION:
                            Ogdf.Graph_setEdgeType(ogdfEdge,
                                    Ogdf.EDGE_TYPE_GENERALIZATION);
                            break;
                        case DEPENDENCY:
                            Ogdf.Graph_setEdgeType(ogdfEdge,
                                    Ogdf.EDGE_TYPE_DEPENDENCY);
                            break;
                        }
                    }
                }
            }
        }

        // process edge labels
        Ogdf.createLabelInterface();
        for (Map.Entry<KEdge, Long> entry : kedge2ogdfEdgeMap.entrySet()) {
            KEdge kedge = entry.getKey();
            long ogdfEdge = entry.getValue();
            boolean makeMult1 = false, makeMult2 = false;
            for (KLabel label : kedge.getLabels()) {
                KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                EdgeLabelPlacement placement = labelLayout.getProperty(
                        LayoutOptions.EDGE_LABEL_PLACEMENT);
                int labelTyp = Ogdf.LABEL_TYPE_NAME;
                switch (placement) {
                case HEAD:
                    if (makeMult2) {
                        labelTyp = Ogdf.LABEL_TYPE_MULT2;
                    } else {
                        labelTyp = Ogdf.LABEL_TYPE_END2;
                    }
                    makeMult2 = !makeMult2;
                    break;
                case TAIL:
                    if (makeMult1) {
                        labelTyp = Ogdf.LABEL_TYPE_MULT1;
                    } else {
                        labelTyp = Ogdf.LABEL_TYPE_END1;
                    }
                    makeMult1 = !makeMult1;
                    break;
                }
                Ogdf.Label_addLabel(ogdfEdge, labelTyp, labelLayout.getWidth(),
                        labelLayout.getHeight());
            }
        }
    }

    /**
     * Applies the layout result to the original graph.
     * 
     * @param parentNode
     *            the parent node of the layout graph
     */
    protected void applyLayout(final KNode parentNode) {
        // get the parent node layout
        KShapeLayout parentNodeLayout = parentNode.getData(KShapeLayout.class);
        Ogdf.Graph_getBoundingBox();
        float boundingBoxX = Ogdf.Graph_getBoundingBoxX();
        float boundingBoxY = Ogdf.Graph_getBoundingBoxY();
        float boundingBoxWidth = Ogdf.Graph_getBoundingBoxWidth();
        float boundingBoxHeight = Ogdf.Graph_getBoundingBoxHeight();
        // get the border spacing
        float borderSpacing = parentNodeLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = DEF_BORDER_SPACING;
        }
        // calculate offsets
        float offsetX = (float) -boundingBoxX + borderSpacing;
        float offsetY = (float) -boundingBoxY + borderSpacing;
        // apply node layout
        for (Map.Entry<KNode, Long> entry : knode2ogdfNodeMap.entrySet()) {
            KShapeLayout nodeLayout = entry.getKey().getData(KShapeLayout.class);
            long ogdfNode = entry.getValue();
            toKShape(nodeLayout, Ogdf.Graph_getNodeX(ogdfNode) + offsetX,
                    Ogdf.Graph_getNodeY(ogdfNode) + offsetY,
                    nodeLayout.getWidth(), nodeLayout.getHeight());
        }
        // apply edge layout
        for (Map.Entry<KEdge, Long> entry : kedge2ogdfEdgeMap.entrySet()) {
            KEdge kedge = entry.getKey();
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            EList<KPoint> kbends = edgeLayout.getBendPoints();
            kbends.clear();
            long ogdfEdge = entry.getValue();
            // are source and target point present?
            if (Ogdf.Graph_getNumberOfBends(ogdfEdge) >= 2) {
                Ogdf.createBendsInterator(ogdfEdge);
                // set the source point
                KPoint sourcePoint =
                        toKPoint(Ogdf.BendsIterator_getX(),
                                Ogdf.BendsIterator_getY(), offsetX, offsetY);
                edgeLayout.setSourcePoint(sourcePoint);
                Ogdf.BendsIterator_next();
                // set the bend points
                boolean first = true;
                while (Ogdf.BendsIterator_hasNext()) {
                    float x = Ogdf.BendsIterator_getX();
                    float y = Ogdf.BendsIterator_getY();
                    Ogdf.BendsIterator_next();
                    KPoint point = toKPoint(x, y, offsetX, offsetY);
                    if (!Ogdf.BendsIterator_hasNext()) {
                        // set the target point
                        edgeLayout.setTargetPoint(point);
                    } else {
                        // workaround for a gef problem that occures when too
                        // bend points are very close to each other like the
                        // source point and the first bend that ogdf computes
                        // sometimes
                        if (first) {
                            first = false;
                            if (sourcePoint.getX() < point.getX()
                                    + SOURCE_POINT_FIRST_BEND_DISTANCE
                                    && sourcePoint.getX() > point.getX()
                                            - SOURCE_POINT_FIRST_BEND_DISTANCE
                                    && sourcePoint.getY() < point.getY()
                                            + SOURCE_POINT_FIRST_BEND_DISTANCE
                                    && sourcePoint.getY() > point.getY()
                                            - SOURCE_POINT_FIRST_BEND_DISTANCE) {
                                continue;
                            }
                        }
                        kbends.add(point);
                    }
                }
            }

            // set the edge labels
            boolean makeMult1 = false, makeMult2 = false;
            for (KLabel label : kedge.getLabels()) {
                KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                EdgeLabelPlacement placement = labelLayout.getProperty(
                        LayoutOptions.EDGE_LABEL_PLACEMENT);
                int labelTyp = Ogdf.LABEL_TYPE_NAME;
                switch (placement) {
                case HEAD:
                    if (makeMult2) {
                        labelTyp = Ogdf.LABEL_TYPE_MULT2;
                    } else {
                        labelTyp = Ogdf.LABEL_TYPE_END2;
                    }
                    makeMult2 = !makeMult2;
                    break;
                case TAIL:
                    if (makeMult1) {
                        labelTyp = Ogdf.LABEL_TYPE_MULT1;
                    } else {
                        labelTyp = Ogdf.LABEL_TYPE_END1;
                    }
                    makeMult1 = !makeMult1;
                    break;
                }
                toKShape(labelLayout, Ogdf.Label_getX(ogdfEdge, labelTyp)
                        + offsetX, Ogdf.Label_getY(ogdfEdge, labelTyp)
                        + offsetY, labelLayout.getWidth(),
                        labelLayout.getHeight());
            }
        }

        // get the insets
        KInsets insets = parentNodeLayout.getProperty(LayoutOptions.INSETS);
        // set the width/height of the graph
        parentNodeLayout.setWidth(boundingBoxWidth + 2 * borderSpacing
                + insets.getLeft() + insets.getRight());
        parentNodeLayout.setHeight(boundingBoxHeight + 2 * borderSpacing
                + insets.getTop() + insets.getBottom());
    }

}
