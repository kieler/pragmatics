/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.klodd.hierarchical.impl;

import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.klodd.hierarchical.modules.INodePlacer;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerConnection;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerElement;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;
import de.cau.cs.kieler.klodd.hierarchical.structures.LinearSegment;

/**
 * Node placing algorithm that improves the overall balance of the graph after
 * executing a basic node placer.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class BalancingNodePlacer extends AbstractAlgorithm implements INodePlacer {

    /** basic node placer that is executed before this algorithm begins. */
    private BasicNodePlacer basicNodePlacer;
    /** minimal distance between two nodes or edges in each layer. */
    private float objSpacing;
    /** maximal crosswise dimension of the layered graph. */
    private float maxWidth;
    /** layout direction for this algorithm instance. */
    private LayoutDirection layoutDirection;
    /** indicates whether node balancing has priority over diagram size. */
    private boolean balanceOverSize;
    /** array of move requests for the linear segments. */
    float[] moveRequests;

    /**
     * Creates a balancing node placer using a basic node placer. The basic node
     * placer is expected to create a layout where all layer elements are
     * aligned to the top for horizontal layout or to the left for vertical
     * layout.
     * 
     * @param thebasicNodePlacer node placing algorithm that creates an initial
     *            unbalanced placement for each node
     */
    public BalancingNodePlacer(final BasicNodePlacer thebasicNodePlacer) {
        this.basicNodePlacer = thebasicNodePlacer;
    }

    /**
     * {@inheritDoc}
     */
    public void placeNodes(final LayeredGraph layeredGraph, final float theobjSpacing,
            final float borderSpacing, final boolean thebalanceOverSize) {
        getMonitor().begin("Balancing node placement", 2);

        this.objSpacing = theobjSpacing;
        this.layoutDirection = layeredGraph.getLayoutDirection();
        this.balanceOverSize = thebalanceOverSize;
        // apply the basic node placement
        basicNodePlacer.reset(getMonitor().subTask(1));
        basicNodePlacer.placeNodes(layeredGraph, theobjSpacing, borderSpacing, thebalanceOverSize);
        int movableCount = basicNodePlacer.getMovableSegments().length;

        // create array of move requests
        moveRequests = new float[movableCount];
        for (int i = 0; i < movableCount; i++) {
            moveRequests[i] = Float.NaN;
        }
        // find the layer with the greatest crosswise dimension
        maxWidth = 0.0f;
        int referenceRank = 0;
        for (Layer layer : layeredGraph.getLayers()) {
            if (isMovable(layer) && layer.crosswiseDim > maxWidth) {
                maxWidth = layer.crosswiseDim;
                referenceRank = layer.rank;
            }
        }
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        Layer layer;
        do {
            layer = layerIter.hasNext() ? layerIter.next() : null;
        } while (layer != null && layer.rank <= referenceRank);

        // create move requests below the reference layer
        while (layer != null) {
            if (isMovable(layer)) {
                createRequests(layer, true);
                validateRequests(layer);
            }
            layer = layerIter.hasNext() ? layerIter.next() : null;
        }

        // revalidate all requests below the reference layer
        while (layerIter.hasPrevious() && (layer = layerIter.previous()).rank > referenceRank) {
            if (isMovable(layer)) {
                validateRequests(layer);
            }
        }

        // create move requests above the reference layer
        do {
            if (isMovable(layer)) {
                createRequests(layer, false);
                validateRequests(layer);
            }
            layer = layerIter.hasPrevious() ? layerIter.previous() : null;
        } while (layer != null);

        // revalidate all requests above the reference layer
        while (layerIter.hasNext() && (layer = layerIter.next()).rank <= referenceRank) {
            if (isMovable(layer)) {
                validateRequests(layer);
            }
        }

        // apply all move requests
        for (LinearSegment linearSegment : basicNodePlacer.getMovableSegments()) {
            float moveDelta = moveRequests[linearSegment.rank];
            if (!Float.isNaN(moveDelta)) {
                for (LayerElement element : linearSegment.elements) {
                    KPoint pos = element.getPosition();
                    if (layoutDirection == LayoutDirection.VERTICAL) {
                        pos.setX(pos.getX() + moveDelta);
                    } else {
                        pos.setY(pos.getY() + moveDelta);
                    }
                }
            }
        }

        // update crosswise dimension for the whole graph
        for (Layer layer2 : layeredGraph.getLayers()) {
            LayerElement lastElem = layer2.getElements().get(layer2.getElements().size() - 1);
            layer2.crosswiseDim = (layoutDirection == LayoutDirection.VERTICAL
                    ? lastElem.getPosition().getX()
                            + lastElem.getRealWidth() + lastElem.eastRanks * theobjSpacing
                    : lastElem.getPosition().getY()
                        + lastElem.getRealHeight() + lastElem.southRanks * theobjSpacing)
                    + borderSpacing;
            layeredGraph.crosswiseDim = Math.max(layeredGraph.crosswiseDim, layer2.crosswiseDim);
        }

        getMonitor().done();
    }

    /**
     * Creates move requests for the elements of a given layer.
     * 
     * @param layer layer to process
     * @param forward if true, incoming connections are considered, else
     *            outgoing connections are considered
     */
    private void createRequests(final Layer layer, final boolean forward) {
        float lastRequest = 0.0f;
        for (LayerElement element : layer.getElements()) {
            if (Float.isNaN(moveRequests[element.linearSegment.rank])) {
                // calculate preferred centered position as barycenter
                float sum = 0.0f;
                int edgeCount = 0;
                for (LayerConnection connection : (forward
                        ? element.getIncomingConnections() : element.getOutgoingConnections())) {
                    sum += calcPosDelta(connection, forward);
                    edgeCount++;
                }
                if (edgeCount == 0) {
                    moveRequests[element.linearSegment.rank] = lastRequest;
                } else {
                    float moveRequest = sum / edgeCount;
                    moveRequests[element.linearSegment.rank] = moveRequest;
                    lastRequest = moveRequest;
                }
            }
        }
    }

    /**
     * Validate the requests of a layer.
     * 
     * @param layer layer to process
     */
    private void validateRequests(final Layer layer) {
        ListIterator<LayerElement> elemIter = layer.getElements().listIterator(
                layer.getElements().size());
        float maxMove = Float.MAX_VALUE;
        if (!balanceOverSize) {
            maxMove = maxWidth - layer.crosswiseDim;
        }
        while (elemIter.hasPrevious()) {
            LayerElement element = elemIter.previous();
            float elemRequest = moveRequests[element.linearSegment.rank];
            elemRequest = elemRequest < 0.0f ? 0.0f : elemRequest;
            Float spacing = basicNodePlacer.getElementSpacing().get(element);
            if (spacing == null) {
                maxMove = Math.min(elemRequest, maxMove);
            } else {
                maxMove = Math.min(elemRequest, maxMove + spacing.floatValue());
            }
            moveRequests[element.linearSegment.rank] = maxMove;
        }
    }

    /**
     * Determines the difference between the endpoint positions of the source
     * and target of a connection.
     * 
     * @param connection connection to process
     * @param forward if true the source position is taken positively, else
     *            negatively
     * @return position difference
     */
    private float calcPosDelta(final LayerConnection connection, final boolean forward) {
        float sourcePos = connection.calcSourcePos(objSpacing);
        float targetPos = connection.calcTargetPos(objSpacing);

        // determine position delta, considering previous move requests
        if (forward) {
            float sourceRequest = moveRequests[connection.getSourceElement().linearSegment.rank];
            if (Float.isNaN(sourceRequest)) {
                return sourcePos - targetPos;
            } else {
                return sourcePos + sourceRequest - targetPos;
            }
        } else {
            float targetRequest = moveRequests[connection.getTargetElement().linearSegment.rank];
            if (Float.isNaN(targetRequest)) {
                return targetPos - sourcePos;
            } else {
                return targetPos + targetRequest - sourcePos;
            }
        }
    }

    /**
     * Returns whether a given layer is movable or fixed.
     * 
     * @param layer the layer
     * @return true if the layer is movable
     */
    private boolean isMovable(final Layer layer) {
        return !(layer.getLayeredGraph().getExternalPortConstraints()
                == PortConstraints.FIXED_POS && (layer.rank == 0 || layer.height == 0));
    }

}
