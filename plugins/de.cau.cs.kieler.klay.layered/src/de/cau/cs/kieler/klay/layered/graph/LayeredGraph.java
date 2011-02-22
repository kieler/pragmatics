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

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.klay.layered.Properties;

/**
 * A layered graph has a set of layers that contain the nodes, as well as a
 * list of nodes that are not yet assigned to a layer. Layout algorithms are
 * required to layout the graph from left to right. If another layout direction
 * is desired, it can be obtained by pre-processing and post-processing the graph.
 * 
 * <p>
 * TODO add methods to rotate / mirror the graph for alternative layout directions
 *
 * @author msp
 */
public class LayeredGraph extends LGraphElement {

    /** the total size of the drawing, without offset. */
    private KVector size = new KVector();
    /** the offset to be added to all positions. */
    private KVector offset = new KVector();
    /** nodes that are not currently part of a layer. */
    private List<LNode> layerlessNodes = new LinkedList<LNode>();
    /** the layers of the layered graph. */
    private List<Layer> layers = new LinkedList<Layer>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "G" + layers.toString();
    }

    /**
     * Returns the size of the graph, that is the bounding box that covers the
     * whole drawing.
     * 
     * @return the size of the layered graph
     */
    public KVector getSize() {
        return size;
    }

    /**
     * Returns the offset for the graph, that is a coordinate vector that has
     * to be added to all position values of nodes and edges.
     * 
     * @return the offset of the layered graph
     */
    public KVector getOffset() {
        return offset;
    }
    
    /**
     * Returns the list of nodes that are not currently assigned to a layer.
     * 
     * @return the layerless nodes.
     */
    public List<LNode> getLayerlessNodes() {
        return layerlessNodes;
    }

    /**
     * Returns the list of layers of the graph.
     * 
     * @return the layers
     */
    public List<Layer> getLayers() {
        return layers;
    }
    
    /**
     * Arrange the ports of all nodes in the layered graph.
     * 
     * TODO move this out into its own module?
     */
    public void arrangePorts() {
        for (Layer layer : layers) {
            for (LNode node : layer.getNodes()) {
                PortConstraints constraints = node.getProperty(Properties.PORT_CONS);
                if (constraints != PortConstraints.FIXED_POS) {
                    int northCount = 1, eastCount = 1, southCount = 1, westCount = 1;
                    for (LPort port : node.getPorts()) {
                        switch (port.getSide()) {
                        case NORTH:
                            northCount++;
                            break;
                        case EAST:
                            eastCount++;
                            break;
                        case SOUTH:
                            southCount++;
                            break;
                        default:
                            westCount++;
                        }
                    }
                    KVector nodeSize = node.getSize();
                    double northDelta = nodeSize.x / northCount;
                    double northX = northDelta;
                    double eastDelta = nodeSize.y / eastCount;
                    double eastY = eastDelta;
                    double southDelta = nodeSize.x / southCount;
                    double southX = nodeSize.x - southDelta;
                    double westDelta = nodeSize.y / westCount;
                    double westY = nodeSize.y - westDelta;
                    for (LPort port : node.getPorts()) {
                        switch (port.getSide()) {
                        case NORTH:
                            port.getPos().x = northX;
                            port.getPos().y = 0;
                            northX += northDelta;
                            break;
                        case EAST:
                            port.getPos().x = nodeSize.x;
                            port.getPos().y = eastY;
                            eastY += eastDelta;
                            break;
                        case SOUTH:
                            port.getPos().x = southX;
                            port.getPos().y = nodeSize.y;
                            southX -= southDelta;
                            break;
                        default:
                            port.getPos().x = 0;
                            port.getPos().y = westY;
                            westY -= westDelta;
                        }
                    }
                }
            }
        }
    }
    
}
