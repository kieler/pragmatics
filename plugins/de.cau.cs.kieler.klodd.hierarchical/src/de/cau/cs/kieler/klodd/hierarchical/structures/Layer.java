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
package de.cau.cs.kieler.klodd.hierarchical.structures;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * A single Layer used in a layered graph.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class Layer {

    /** value to use if the rank is undefined. */
    public static final int UNDEF_RANK = -1;
    /** value to use if the height is undefined. */
    public static final int UNDEF_HEIGHT = -1;

    /** the rank of this layer. */
    public int rank;
    /** the height of this layer. */
    public int height;
    /** lengthwise position of this layer. */
    public float lengthwisePos = 0.0f;
    /** crosswise dimension of this layer. */
    public float crosswiseDim = 0.0f;
    /** lengthwise dimension of this layer. */
    public float lengthwiseDim = 0.0f;
    /** next layer in the layered graph. */
    public Layer next;

    /** the containing layered graph. */
    private LayeredGraph layeredGraph;
    /** list of elements in this layer. */
    private List<LayerElement> elements = new LinkedList<LayerElement>();

    /**
     * Creates a new layer with given rank and height.
     * 
     * @param therank the rank, may be UNDEF_RANK
     * @param theheight the height, may be UNDEF_HEIGHT
     * @param thelayeredGraph layered graph in which the layer is created
     */
    public Layer(final int therank, final int theheight, final LayeredGraph thelayeredGraph) {
        this.rank = therank;
        this.height = theheight;
        this.layeredGraph = thelayeredGraph;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return elements.toString();
    }

    /**
     * Puts an object into this layer.
     * 
     * @param obj the element object
     * @param kNode the corresponding node in the acyclic KIELER graph
     * @return the new layer element
     */
    public LayerElement put(final KGraphElement obj, final KSlimNode kNode) {
        LayerElement element = new LayerElement(obj, this, kNode);
        elements.add(element);
        return element;
    }

    /**
     * Sets the lengthwise position of this layer and all layer elements.
     * 
     * @param layerPos starting lengthwise position of this layer
     * @param minDist minimal distance between nodes and connections
     */
    public void layoutElements(final float layerPos, final float minDist) {
        LayoutDirection layoutDirection = layeredGraph.getLayoutDirection();
        float backPadding = 0.0f;
        float frontPadding = 0.0f;

        // determine padding values
        for (LayerElement element : elements) {
            float sideSpace = (lengthwiseDim - (layoutDirection == LayoutDirection.DOWN ? element
                    .getRealHeight() : element.getRealWidth())) / 2;
            backPadding = Math.max(backPadding, element.getEdgesBack() * minDist - sideSpace);
            frontPadding = Math.max(frontPadding, element.getEdgesFront() * minDist - sideSpace);
        }
        lengthwisePos = layerPos + frontPadding;

        // set the lengthwise position of each node
        for (LayerElement element : elements) {
            if (rank > 0 && height > 0) {
                float sideSpace;
                if (layoutDirection == LayoutDirection.DOWN) {
                    if (element.getIncomingConnections().isEmpty()
                            && !element.getOutgoingConnections().isEmpty()) {
                        sideSpace = (lengthwiseDim - element.getRealHeight());
                    } else if (element.getOutgoingConnections().isEmpty()
                            && !element.getIncomingConnections().isEmpty()) {
                        sideSpace = 0;
                    } else {
                        sideSpace = (lengthwiseDim - element.getRealHeight()) / 2;
                    }
                    element.getPosition().setY(lengthwisePos + sideSpace);
                } else {
                    if (element.getIncomingConnections().isEmpty()
                            && !element.getOutgoingConnections().isEmpty()) {
                        sideSpace = (lengthwiseDim - element.getRealWidth());
                    } else if (element.getOutgoingConnections().isEmpty()
                            && !element.getIncomingConnections().isEmpty()) {
                        sideSpace = 0;
                    } else {
                        sideSpace = (lengthwiseDim - element.getRealWidth()) / 2;
                    }
                    element.getPosition().setX(lengthwisePos + sideSpace);
                }
            }
        }

        lengthwiseDim += backPadding;
    }

    /**
     * Gets the elements of this layer.
     * 
     * @return the layer elements
     */
    public List<LayerElement> getElements() {
        return elements;
    }

    /**
     * Gets the layered graph.
     * 
     * @return the layeredGraph
     */
    public LayeredGraph getLayeredGraph() {
        return layeredGraph;
    }

    /**
     * Sorts the elements in this layer and assigns them new rank values based
     * on the ranks of contained ports. This method may only be called on
     * external ports layers with either {@code rank == 0} or {@code height == 0}.
     * 
     * @param newRanks if true new port ranks are determined for the contained ports
     */
    public void sortByPorts(final boolean newRanks) {
        boolean forward;
        if (rank == 0) {
            forward = true;
        } else if (height == 0) {
            forward = false;
        } else {
            throw new UnsupportedOperationException();
        }

        if (newRanks) {
            // set port ranks according to current order in this layer
            int portrank = 0;
            for (LayerElement element : elements) {
                KPort port = (KPort) element.getElemObj();
                LayoutOptions.setPortRank(KimlLayoutUtil.getShapeLayout(port), portrank++);
            }
        }
        // sort elements according to port sides and ranks
        final Comparator<KPort> portComparator = new KimlLayoutUtil.PortComparator(forward, layeredGraph
                .getLayoutDirection());
        Collections.sort(elements, new Comparator<LayerElement>() {
            public int compare(final LayerElement elem1, final LayerElement elem2) {
                KPort port1 = (KPort) elem1.getElemObj();
                KPort port2 = (KPort) elem2.getElemObj();
                return portComparator.compare(port1, port2);
            }
        });
        if (newRanks) {
            // set port ranks according to new sorted order
            int portrank = 0;
            for (LayerElement element : elements) {
                KPort port = (KPort) element.getElemObj();
                LayoutOptions.setPortRank(KimlLayoutUtil.getShapeLayout(port), portrank++);
            }
        }

        // calculate concrete rank values for the layer elements
        calcElemRanks();
    }

    /**
     * Calculates the element rank of each element in this layer. The rank is
     * induced by the order of elements in the internal list and the rank width
     * of each element.
     */
    public void calcElemRanks() {
        int elemrank = 0;
        for (LayerElement element : elements) {
            element.rank = elemrank;
            elemrank += element.getRankWidth();
        }
    }

}
