/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 * + Department of Computer Science
 * + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p4nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.text.Segment;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Node placement implementation inspired by Buchheim, Jünger und Leipert
 * 
 * @author kpe
 */

public final class BJLNodePlacer implements ILayoutPhase {

    private LinkedList<LSegment> lSegments;
    private HashMap<Integer, List<LSegment>> leftClasses;
    private LGraph layeredGraph;

    /** additional processor dependencies for graphs with hierarchical ports. */
    private static final IntermediateProcessingConfiguration HIERARCHY_PROCESSING_ADDITIONS =
            new IntermediateProcessingConfiguration(
                    IntermediateProcessingConfiguration.BEFORE_PHASE_5,
                    LayoutProcessorStrategy.HIERARCHICAL_PORT_POSITION_PROCESSOR);

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {

        if (graph.getProperty(Properties.GRAPH_PROPERTIES).contains(GraphProperties.EXTERNAL_PORTS)) {
            return HIERARCHY_PROCESSING_ADDITIONS;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredG, final IKielerProgressMonitor monitor) {
        monitor.begin("Buchheim, Jünger and Leipert Node Placement", 1);
        this.layeredGraph = layeredG;
        
        // Initialize spacing value from layout options.
        float normalSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING) 
                * layeredGraph.getProperty(Properties.OBJ_SPACING_VERTICAL_FACTOR);
        float  smallSpacing = normalSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
       

        // // first iteration: determine the height of each layer
        // double maxHeight = 0;
        // for (Layer layer : layeredGraph.getLayers()) {
        // KVector layerSize = layer.getSize();
        // layerSize.y = 0;
        // LNode lastNode = null;
        // for (LNode node : layer.getNodes()) {
        // if (lastNode != null) {
        // if (lastNode.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
        // layerSize.y += normalSpacing;
        // } else {
        // layerSize.y += smallSpacing;
        // }
        // }
        // layerSize.y += node.getMargin().top + node.getSize().y + node.getMargin().bottom;
        // lastNode = node;
        // }
        // maxHeight = Math.max(maxHeight, layerSize.y);
        // }
        // // second iteration: center the nodes of each layer around the tallest layer
        // for (Layer layer : layeredGraph.getLayers()) {
        // KVector layerSize = layer.getSize();
        // double pos = (maxHeight - layerSize.y) / 2;
        // LNode lastNode = null;
        // for (LNode node : layer.getNodes()) {
        // if (lastNode != null) {
        // if (lastNode.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
        // pos += normalSpacing;
        // } else {
        // pos += smallSpacing;
        // }
        // }
        // pos += node.getMargin().top;
        // node.getPosition().y = pos;
        // pos += node.getSize().y + node.getMargin().bottom;
        // lastNode = node;
        // }
        // }
        for (Layer layer : layeredGraph.getLayers()) {
            System.out.println("Layer:");
            System.out.println(layer.getIndex());
            for (LNode node : layer) {
                System.out.println(node.getProperty(Properties.NODE_TYPE).name());
                System.out.println(node.getName());
            }
        }

        /**
         * place the virtual nodes as close to each other as possible respecting the minimal
         * distance between direct siblings, the given order of the nodes and the straightness of
         * all inner segments of long edges
         */
        placeVirtual(layeredGraph);

        /**
         * placeOriginal minimizes the total length of all outer segments. the positons of virtual
         * nodes are regarded as fixed. Thus the original sequences can be placed independently,
         * wether a dummy node exists between the sequences.
         */
        placeOrigninal(layeredGraph);

        monitor.done();
    }

    /**
     * A linear segment contains a single regular node or all dummy nodes of a long edge.
     */
    /**
     * @author katja
     * 
     */
    private static final class LSegment {
        /** Identifier value, used as index in the segments array. */
        private int segId;
        /** Identifier value of the layer of the first node in a linear segment. */
        private int layerId;
        /** Identifier value of the computed classes. */
        private int computedClassId;
        /** Nodes of the linear segment. */
        private List<LNode> nodes;
        /** Flag if the Nodes of the linear Segment are already placed. */
        private boolean isPlaced;

        private LSegment(final int segId, final int layerId) {
            this.segId = segId;
            this.layerId = layerId;
            this.computedClassId = -1;
            this.isPlaced = false;
            this.nodes = new LinkedList<LNode>();

        }

        /**
         * @return the isPlaced
         */
        public boolean isPlaced() {
            return isPlaced;
        }

        /**
         * @param isPlaced
         *            the isPlaced to set
         */
        public void setPlaced(final boolean isPlaced2) {
            this.isPlaced = isPlaced2;
        }

        /**
         * @return the segId
         */
        public int getSegId() {
            return segId;
        }

        /**
         * @param segId
         *            the segId to set
         */
        public void setSegId(final int segId) {
            this.segId = segId;
        }

        /**
         * @return the layerId
         */
        public int getLayerId() {
            return layerId;
        }

        /**
         * @param layerId
         *            the layerId to set
         */
        public void setLayerId(final int layerId) {
            this.layerId = layerId;
        }

        /**
         * @return the computedClassId
         */
        public int getComputedClassId() {
            return computedClassId;
        }

        /**
         * @param computedClassId
         *            the computedClassId to set
         */
        public void setComputedClassId(final int computedClassId) {
            this.computedClassId = computedClassId;
        }

        /**
         * @return the nodes
         */
        public List<LNode> getSegNodes() {
            return nodes;
        }

        /**
         * @param nodes
         *            the nodes to set
         */
        public void setSegNodes(final List<LNode> lNodes) {
            this.nodes = lNodes;
        }

        /**
         * @param node
         */
        public void addNode(final LNode node) {
            this.nodes.add(node);
        }

        public boolean containsNode(final LNode node) {
            return this.nodes.contains(node);
        }
        // /**
        // * @param lSegs
        // * @param layerId
        // * @return
        // */
        // public LinkedList<LSegment> getSegmentsByLayerId(LinkedList<LSegment> lSegs, int layerId)
        // {
        // LinkedList<LSegment> lSegsByLayer = new LinkedList<LSegment>();
        // for (int i = 0; i < lSegs.size(); i++) {
        // if (lSegs.get(i).getLayerId() == layerId) {
        // lSegsByLayer.add(lSegs.get(i));
        // }
        // }
        // return lSegsByLayer;
        // }
        //
        // if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
        // for (LEdge edge : node.getIncomingEdges()) {
        // if (edge.getSource().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
        //
        // }
        // }
        // }

    }

    /************************************** Begin**placeVirtual **************************************/
    /**
     * @param layeredGraph
     */
    private void placeVirtual(final LGraph layeredG) {
        this.layeredGraph = layeredG;

        LGraph layeredGraphLeft = layeredGraph;
        LGraph layeredGraphRight = layeredGraph;

        computePositionLeft(layeredGraphLeft);
        computePositionRight(layeredGraphRight);

        // Combine the computed right-oriented Graph and the left-oriented Graph
        for (int i = 0; i < layeredGraph.getLayers().size(); i++) {

            for (int j = 0; j < layeredGraph.getLayers().get(i).getNodes().size(); j++) {
                layeredGraph.getLayers().get(i).getNodes().get(j).getPosition().y =
                        (layeredGraphLeft.getLayers().get(i).getNodes().get(j).getPosition().y + layeredGraphRight
                                .getLayers().get(i).getNodes().get(j).getPosition().y) / 2;
            }
        }
    }

    /**
     * @param layeredGraphRight
     */
    private void computePositionRight(final LGraph layeredGraphRight) {
        // TODO Auto-generated method stub
    }

    /**
     * @param layeredGraphLeft
     */
    private void computePositionLeft(final LGraph layeredGraphLeft) {

        computeLeftClasses(new HashMap<Integer, List<LSegment>>());
        
//        System.out.println(leftClasses.get(2).size());
//        System.out.println(leftClasses.entrySet().size());

//        // all left classes
//        for (Iterator<Integer> it = leftClasses.keySet().iterator(); it.hasNext();) {
//         
//            // all Segments of the current class
//            for (LSegment seg : leftClasses.get(it.next())) {
//                // proof wether the current Segment is placed or not
//                if (seg.isPlaced == false) {
//                    // places all nodes of the current linear Segment simultaneously
//                    placeLeft(seg, layeredGraphLeft, it.next());
//                    seg.setPlaced(true);
//                }
//            }
//            // adjusts the current class to the previously placed classes
//            adjustLeftClass(it.next(), layeredGraphLeft, leftClasses.get(it.next()));
//        }
    }

    /**
     * @param classID
     * @param layeredGraphLeft
     * @param classLeft
     */
    private void adjustLeftClass(final Integer classID, final LGraph layeredGraphLeft,
            final LinkedList<LSegment> classLeft) {
        // TODO Auto-generated method stub

    }

    /**
     * @param currentSegment
     * @param layeredGraphLeft
     * @param leftClasses2
     */
    private void placeLeft(final LSegment currentSegment, final LGraph layeredGraphLeft,
            final int classID) {

//        int p = Integer.MIN_VALUE;
//
//        for (LNode node : currentSegment.getSegNodes()) {
//
//            // Proof wether node is the first node of a layer
//            if (!(node.getIndex() == 0)) {
//
//                // compute left sibbling
//                LNode leftSibbling = node.getLayer().getNodes().get(node.getIndex() - 1);
//                //über alle Segmente
//                for (LSegment segment : leftClasses.get(classID)) {
//                    if (segment.containsNode(leftSibbling)) {
//                        if (!segment.isPlaced) {
//                            placeLeft(segment, layeredGraphLeft, classID);
//                        }
//                        // TODO Position setzen, Mindestabstand Dummy? spacingfactor, Typ von p
//                        double m =
//                                node.getPosition().y + node.getSize().y + node.getMargin().bottom;
//                        p = Math.max(p, (int) (leftSibbling.getPosition().y + m));
//                    }
//                }
//            }
//        }
//        if (p == Integer.MIN_VALUE) {
//            p = 0;
//        }
//
//        // set the position of all nodes of the current Segment to the same y coordinate p
//        for (LNode node : currentSegment.getSegNodes()) {
//            // TODO sehr unschön
//            layeredGraphLeft.getLayers().get(currentSegment.getLayerId()).getNodes()
//                    .get(node.getIndex()).getPosition().y = p;
//           
//        }
    }

    /**
     *
     */
    private void computeLinearSegments() {

        // Kopie des Originalgraphen erstellen.
        final LGraph segGraph = layeredGraph;
        lSegments = new LinkedList<LSegment>();

        int segId = 0;
        // alle Knoten des Graphen als unvisited markieren, node.id = -1
        for (int i = 0; i < segGraph.getLayers().size(); i++) {
            for (LNode node : segGraph.getLayers().get(i).getNodes()) {
                node.id = -1;
            }
        }

        for (int i = 0; i < segGraph.getLayers().size(); i++) {
            for (LNode node : segGraph.getLayers().get(i).getNodes()) {

                if ((node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE)
                        && (node.id == -1)) {
                    //debug
                    System.out.println(node.getName());
                    
                    boolean longEdgeDummy = true;

                    // neues Lineares Segment erstellen
                    LSegment newLSeg = new LSegment(segId, i);
                    // füge Dummy dem neuen Segment hinzu
                    newLSeg.addNode(node);
                    // Knoten als besucht markieren
                    node.id = 1;

                    // finde die nächsten Knoten der langen Kante
                    while (longEdgeDummy) {

                        for (LEdge edge : node.getOutgoingEdges()) {
                            // Successor Dummy
                            LNode successor = edge.getTarget().getNode();
                            if (successor.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                                //debug
                                System.out.println(successor.getProperty(Properties.NODE_TYPE));
                                // Knoten dem Segment hinzufügen
                                newLSeg.addNode(successor);

                                // nächster Knoten
                                //TODO selfloops?
                                if(!node.equals(successor)){
                                node = successor;
                                }

                                // Knoten als besucht markieren
                                successor.id = 1;

                            } else {
                                longEdgeDummy = false;
                                // nachdem alle Knoten der langen Kante eingesammelt wurden,
                                // zur Segmentliste hinzufügen
                                lSegments.add(newLSeg);
                                segId++;
                            }
                        }
                    }

                    // for all other Nodes create a new Linear Segment
                } else {
                    LSegment newLSeg = new LSegment(segId, i);
                    newLSeg.addNode(node);
                    lSegments.add(newLSeg);
                    node.id = 1;
                    segId++;
                }
            }
        }
        System.out.println("letztes Segment:");
        System.out.println(lSegments.getLast().getComputedClassId());
        System.out.println(lSegments.getLast().getLayerId());
        System.out.println(lSegments.getLast().getSegId());
        System.out.println(lSegments.getLast().isPlaced);
        
        System.out.println("erstes Segment:");
        System.out.println(lSegments.getFirst().getComputedClassId());
        System.out.println(lSegments.getFirst().segId);
        System.out.println(lSegments.getFirst().getLayerId());
        System.out.println(lSegments.getFirst().isPlaced);
    }

    /**
     * @param lClasses
     */
    private void computeLeftClasses(final HashMap<Integer, List<LSegment>> lClasses) {
        this.leftClasses = lClasses;

        // split Graph into linear segments
        computeLinearSegments();

        // traverse all levels from left to right
        for (int layerId = 0; layerId < layeredGraph.getLayers().size(); layerId++) {
            int c = layerId;

            // TODO nicht sehr elegant :-)
            for (LSegment seg : lSegments) {
                if (seg.getLayerId() == layerId) {
                    if (seg.getComputedClassId() == -1) {
                        // set the classID in each linear segment
                        seg.setComputedClassId(c);
                        // insert Segment into the current class c
                        LinkedList<LSegment> currentClass = new LinkedList<LSegment>();
                        if (leftClasses.get(c) != null) {
                            currentClass = leftClasses.get(c);
                        }
                        currentClass.add(seg);
                        leftClasses.put(c, currentClass);

                        // Segment already inserted into a class
                        // set the current class to this value
                    } else {
                        c = seg.getComputedClassId();
                    }
                }
            }
        }
    }

    /************************************** End**placeVirtual *****************************************/

    /************************************** Begin**placeOriginal **************************************/

    /**
     * @param layeredGraph
     */
    private void placeOrigninal(final LGraph layeredG) {
        // TODO Auto-generated method stub

    }
    /************************************** End**placeOriginal **************************************/
}
