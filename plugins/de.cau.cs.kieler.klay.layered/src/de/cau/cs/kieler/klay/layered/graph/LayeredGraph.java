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
package de.cau.cs.kieler.klay.layered.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.kiml.layout.options.PortType;

/**
 * A layered graph has a set of layers that contain the nodes.
 *
 * @author msp
 */
public class LayeredGraph {

    /** the total size of the drawing, without offset. */
    private Coord size = new Coord();
    /** the offset to be added to all positions. */
    private Coord offset = new Coord();
    /** the original object from which the graph was created. */
    private Object origin;
    /** the layers of the layered graph. */
    private List<Layer> layers = new LinkedList<Layer>();

    /**
     * Creates a layered graph.
     * 
     * @param theorigin the original object for the graph
     */
    public LayeredGraph(final Object theorigin) {
        this.origin = theorigin;
    }
    
    /**
     * Creates a layered graph.
     */
    public LayeredGraph() {
        this(null);
    }

    /**
     * @return the size
     */
    public Coord getSize() {
        return size;
    }

    /**
     * @return the offset
     */
    public Coord getOffset() {
        return offset;
    }

    /**
     * @return the origin
     */
    public Object getOrigin() {
        return origin;
    }

    /**
     * @return the layers
     */
    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * Split the long edges of the layered graph to obtain a proper layering.
     */
    public void splitEdges() {
        // TODO create additional dummy nodes for feedback edges
        ListIterator<Layer> layerIter = layers.listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            for (LNode node : layer.getNodes()) {
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getEdges()) {
                        LPort targetPort = edge.getTarget();
                        int targetIndex = targetPort.getNode().getLayer().getIndex();
                        if (targetIndex != layerIter.nextIndex()) {
                            ListIterator<Layer> dummyIter = layers.listIterator(layerIter.nextIndex());
                            LEdge dummyEdge = edge;
                            while (dummyIter.hasNext() && dummyIter.nextIndex() < targetIndex) {
                                LNode dummyNode = new LNode(edge, null, LNode.Type.LONG_EDGE);
                                dummyNode.setLayer(dummyIter.next());
                                LPort dummyInput = new LPort(PortType.INPUT);
                                dummyInput.setNode(dummyNode);
                                LPort dummyOutput = new LPort(PortType.OUTPUT);
                                dummyOutput.setNode(dummyNode);
                                dummyEdge.setTarget(dummyInput);
                                dummyEdge = new LEdge(edge.getOrigin());
                                dummyEdge.setSource(dummyOutput);
                            }
                            dummyEdge.setTarget(targetPort);
                        }
                    }
                }
            }
        }
    }
    
}
