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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.core.slimgraph.alg.ICycleRemover;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * This structure manages the layering of an acyclic directed graph.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayeredGraph {

    /** crosswise dimension of this layered graph. */
    private float crosswiseDim = 0.0f;
    /** lengthwise dimension of this layered graph. */
    private float lengthwiseDim = 0.0f;
    /** list of layers in this layered graph. */
    private List<Layer> layers = new LinkedList<Layer>();
    /** parent layout node associated with this layered graph. */
    private KNode parentNode;
    /** map of objects to their corresponding layer. */
    private Map<Object, LayerElement> obj2LayerElemMap = new HashMap<Object, LayerElement>();
    /** map of ports to connected long edges. */
    private Map<KPort, LinearSegment> longEdgesMap = new HashMap<KPort, LinearSegment>();
    /** list of linear segments in this layered graph. */
    private List<LinearSegment> linearSegments = new LinkedList<LinearSegment>();
    /** layout direction for this layered graph: RIGHT or DOWN. */
    private LayoutDirection layoutDirection;
    /** port constraints for the external ports. */
    private PortConstraints externalPortConstraints;
    /** position of this layered graph. */
    private KPoint position;

    /**
     * Creates a new layered graph.
     * 
     * @param theparentNode parent layout node
     */
    public LayeredGraph(final KNode theparentNode) {
        position = KLayoutDataFactory.eINSTANCE.createKPoint();
        position.setX(0.0f);
        position.setY(0.0f);

        // get layout options from the parent group
        this.parentNode = theparentNode;
        KLayoutData layoutData = KimlLayoutUtil.getShapeLayout(theparentNode);
        layoutDirection = LayoutOptions.getEnum(layoutData, LayoutDirection.class);
        if (layoutDirection == LayoutDirection.UNDEFINED) {
            layoutDirection = LayoutDirection.RIGHT;
        }
        externalPortConstraints = LayoutOptions.getEnum(layoutData, PortConstraints.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String text = getClass().getSimpleName();
        if (parentNode.getLabel() != null) {
            text += "(" + parentNode.getLabel().getText() + ")";
        }
        return text;
    }

    /**
     * Put the given object into a layer with specified rank. The search for the
     * right layer begins at the front of the internal list.
     * 
     * @param obj object to put
     * @param rank rank of the object
     * @param kNode the corresponding node in the acyclic KIELER graph
     */
    // TODO putFront -> put, putBack->?
    public void putFront(final KGraphElement obj, final int rank, final KSlimNode kNode) {
        ListIterator<Layer> layerIter = layers.listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            if (layer.getRank() < 0 || layer.getRank() > rank) {
                // insert a new layer into the list
                Layer newLayer = new Layer(rank, Layer.UNDEF_HEIGHT, this);
                doPut(obj, newLayer, kNode);
                layerIter.previous();
                layerIter.add(newLayer);
                return;
            } else if (layer.getRank() == rank) {
                // the right layer was found
                doPut(obj, layer, kNode);
                return;
            }
        }

        // add a new layer at the end of the list
        Layer newLayer = new Layer(rank, Layer.UNDEF_HEIGHT, this);
        doPut(obj, newLayer, kNode);
        layers.add(newLayer);
    }

    /**
     * Put the given object into a layer with specified height. The search for
     * the right layer begins at the back of the internal list.
     * 
     * @param obj object to put
     * @param height height of the object
     * @param kNode the corresponding node in the acyclic KIELER graph
     */
    public void putBack(final KGraphElement obj, final int height, final KSlimNode kNode) {
        ListIterator<Layer> layerIter = layers.listIterator(layers.size());
        while (layerIter.hasPrevious()) {
            Layer layer = layerIter.previous();
            if (layer.getHeight() < 0 || layer.getHeight() > height) {
                // insert a new layer into the list
                Layer newLayer = new Layer(Layer.UNDEF_RANK, height, this);
                doPut(obj, newLayer, kNode);
                layerIter.next();
                layerIter.add(newLayer);
                return;
            } else if (layer.getHeight() == height) {
                // the right layer was found
                doPut(obj, layer, kNode);
                return;
            }
        }

        // add a new layer at the start of the list
        Layer newLayer = new Layer(Layer.UNDEF_RANK, height, this);
        doPut(obj, newLayer, kNode);
        layers.add(0, newLayer);
    }

    /**
     * Gets the layer element that holds the given object.
     * 
     * @param obj the object
     * @return the corresponding layer element, or null if none exists
     */
    //TODO common superclass? generics?
    public LayerElement getLayerElement(final Object obj) {
        return obj2LayerElemMap.get(obj);
    }

    /**
     * Gets the list of layers.
     * 
     * @return the layers
     */
    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * Gets the parent layout node.
     * 
     * @return the parent node
     */
    public KNode getParentNode() {
        return parentNode;
    }

    /**
     * Creates connections between layer elements and creates long edges. This
     * method should be called after all nodes and ports have been put into the
     * layered graph.
     * 
     * @param kGraph acyclic version of the graph
     */
    //TODO List of reversed edges as param
    public void createConnections(final KSlimGraph kGraph) {
        for (Layer layer : layers) {
            List<LayerElement> elements = layer.getElements();
            for (LayerElement element : elements) {
                if (element.getLinearSegment() == null) {
                    // create new linear segment
                    createLinearSegment(element);
                }
                List<KSlimEdge> outgoingEdges = element.getOutgoingEdges();
                if (outgoingEdges != null) {
                    for (KSlimEdge edge : outgoingEdges) {
                        KEdge layoutEdge = (KEdge) edge.getObject();
                        KNode targetNode;
                        KPort sourcePort, targetPort;
                        if (edge.getRank() == ICycleRemover.REVERSED) {
                            targetNode = layoutEdge.getSource();
                            sourcePort = layoutEdge.getTargetPort();
                            targetPort = layoutEdge.getSourcePort();
                        } else {
                            targetNode = layoutEdge.getTarget();
                            sourcePort = layoutEdge.getSourcePort();
                            targetPort = layoutEdge.getTargetPort();
                        }

                        if (edge.getTarget().getObject() instanceof KNode) {
                            createConnection(element, obj2LayerElemMap.get(targetNode), layoutEdge,
                                    sourcePort, targetPort);
                        } else if (targetPort != null) {
                            createConnection(element, obj2LayerElemMap.get(targetPort), layoutEdge,
                                    sourcePort, targetPort);
                        }
                    }
                }
            }
            // assign ranks to all elements of the current layer
            layer.calcElemRanks();
        }
    }

    /**
     * Applies the layout of this layered graph to the contained layout graph.
     */
    public void applyLayout() {
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        KInsets insets = LayoutOptions.getObject(parentLayout, KInsets.class);
        // apply the new layout to the contained elements
        for (Layer layer : layers) {
            for (LayerElement element : layer.getElements()) {
                element.applyLayout(position, insets);
                for (LayerConnection connection : element.getIncomingConnections()) {
                    connection.applyLayout(position, insets);
                }
                for (ElementLoop loop : element.getLoops()) {
                    loop.applyLayout(position);
                }
            }
        }

        // layout the edge labels
        // TODO the label sizes are not considered yet, would need to change preceding phases
        for (KNode child : parentNode.getChildren()) {
            for (KEdge edge : child.getOutgoingEdges()) {
                layoutEdgeLabels(edge);
            }
        }
        for (KEdge edge : parentNode.getOutgoingEdges()) {
            if (parentNode.equals(edge.getTarget().getParent())) {
                layoutEdgeLabels(edge);
            }
        }

        // update the size of the parent layout node
        float width = insets.getLeft() + insets.getRight();
        float height = insets.getTop() + insets.getBottom();
        if (layoutDirection == LayoutDirection.DOWN) {
            width += crosswiseDim;
            height += lengthwiseDim;
        } else {
            width += lengthwiseDim;
            height += crosswiseDim;
        }
        parentLayout.setWidth(width);
        parentLayout.setHeight(height);

        // update layout options of the parent layout node
        LayoutOptions.setEnum(parentLayout, PortConstraints.FIXED_POS);
        KimlLayoutUtil.calcPortRanks(parentNode);
    }

    /**
     * Gets the layout direction: RIGHT or DOWN.
     * 
     * @return the layout direction
     */
    public LayoutDirection getLayoutDirection() {
        return layoutDirection;
    }

    /**
     * Returns the port constraints for external ports.
     * 
     * @return the port constraints for external ports
     */
    public PortConstraints getExternalPortConstraints() {
        return externalPortConstraints;
    }

    /**
     * Gets the list of linear segments of this layered graph. Each real node is
     * assigned a unique linear segment of size 1.
     * 
     * @return list of linear segments
     */
    public List<LinearSegment> getLinearSegments() {
        return linearSegments;
    }

    /**
     * Gets the current layout position of this layered graph.
     * 
     * @return the position
     */
    // TODO offset
    public KPoint getPosition() {
        return position;
    }
    
    /**
     * Sets the crosswiseDim.
     * TODO explain
     *
     * @param thecrosswiseDim the crosswiseDim to set
     */
    public void setCrosswiseDim(final float thecrosswiseDim) {
        this.crosswiseDim = thecrosswiseDim;
    }

    /**
     * Returns the crosswiseDim.
     *
     * @return the crosswiseDim
     */
    public float getCrosswiseDim() {
        return crosswiseDim;
    }

    /**
     * Sets the lengthwiseDim.
     *
     * @param thelengthwiseDim the lengthwiseDim to set
     */
    public void setLengthwiseDim(final float thelengthwiseDim) {
        this.lengthwiseDim = thelengthwiseDim;
    }

    /**
     * Returns the lengthwiseDim.
     *
     * @return the lengthwiseDim
     */
    public float getLengthwiseDim() {
        return lengthwiseDim;
    }

    /**
     * Puts an object into a given layer.
     * 
     * @param obj object to put
     * @param layer the layer
     * @param kNode the corresponding node in the acyclic KIELER graph
     */
    private void doPut(final KGraphElement obj, final Layer layer, final KSlimNode kNode) {
        LayerElement element = layer.put(obj, kNode);
        obj2LayerElemMap.put(obj, element);
    }

    /**
     * Creates a cross-layer connection between two layer elements, possibly
     * leading to a linear segment of edge elements.
     * 
     * @param source source element
     * @param target target element
     * @param edge the edge object connecting both elements
     * @param sourcePort the source port
     * @param targetPort the target port
     */
    private void createConnection(final LayerElement source, final LayerElement target,
            final KEdge edge, final KPort sourcePort, final KPort targetPort) {
        Layer sourceLayer = source.getLayer();
        Layer targetLayer = target.getLayer();
        if (targetLayer.getRank() - sourceLayer.getRank() == 1) {
            source.addOutgoing(edge, target, sourcePort, targetPort);
        } else {
            LayerElement currentElement = null;
            // process existing long edges for the source or target port
            LinearSegment linearSegment = longEdgesMap.get(sourcePort);
            if (linearSegment == null
                    || linearSegment.getElements().get(0).getLayer().getRank() < sourceLayer.getRank()) {
                linearSegment = longEdgesMap.get(targetPort);
            }
            if (linearSegment != null) {
                ListIterator<LayerElement> elemIter = linearSegment.getElements().listIterator();
                currentElement = elemIter.next();
                while (elemIter.hasNext() && currentElement.getLayer().getRank()
                        <= sourceLayer.getRank()) {
                    currentElement = elemIter.next();
                }
                source.addOutgoing(edge, currentElement, sourcePort, null);
                while (elemIter.hasNext() && currentElement.getLayer().getRank()
                        < targetLayer.getRank() - 1) {
                    currentElement = elemIter.next();
                }
            } else {
                currentElement = sourceLayer.getNext().put(edge, null);
                linearSegment = createLinearSegment(currentElement);
                source.addOutgoing(edge, currentElement, sourcePort, null);
            }
            // add new layer elements to the linear segment if needed
            while (currentElement.getLayer().getRank() < targetLayer.getRank() - 1) {
                LayerElement newElement = currentElement.getLayer().getNext().put(edge, null);
                linearSegment.getElements().add(newElement);
                newElement.setLinearSegment(linearSegment);
                currentElement.addOutgoing(edge, newElement);
                currentElement = newElement;
            }
            currentElement.addOutgoing(edge, target, null, targetPort);
            longEdgesMap.put(sourcePort, linearSegment);
            longEdgesMap.put(targetPort, linearSegment);
        }
    }

    /**
     * Creates a new linear segment that contains the given element.
     * 
     * @param element layer element that is put into the new linear segment
     */
    private LinearSegment createLinearSegment(final LayerElement element) {
        LinearSegment linearSegment = new LinearSegment();
        linearSegment.getElements().add(element);
        element.setLinearSegment(linearSegment);
        linearSegments.add(linearSegment);
        return linearSegment;
    }

    /** spacing for edge labels. */
    private static final int LABEL_SPACING = 3;
    
    /**
     * Performs a rudimentary layout of edge labels. Does not avoid overlappings of labels
     * with other labels, nodes, or edges.
     * 
     * @param edge the edge for which the labels shall be layouted
     */
    private void layoutEdgeLabels(final KEdge edge) {
        List<KLabel> tailLabels = new LinkedList<KLabel>();
        List<KLabel> centerLabels = new LinkedList<KLabel>();
        List<KLabel> headLabels = new LinkedList<KLabel>();
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
        int midBendPoint = edgeLayout.getBendPoints().size() / 2;
        for (KLabel edgeLabel : edge.getLabels()) {
            switch (LayoutOptions.getEnum(KimlLayoutUtil.getShapeLayout(edgeLabel),
                    EdgeLabelPlacement.class)) {
            case CENTER:
                if (midBendPoint > 0) {
                    centerLabels.add(edgeLabel);
                } else {
                    tailLabels.add(edgeLabel);
                }
                break;
            case TAIL:
                tailLabels.add(edgeLabel);
                break;
            case HEAD:
                headLabels.add(edgeLabel);
                break;
            }
        }

        // layout tail labels
        float xpos = edgeLayout.getSourcePoint().getX() + LABEL_SPACING;
        float ypos = edgeLayout.getSourcePoint().getY() + LABEL_SPACING;
        for (KLabel edgeLabel : tailLabels) {
            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(edgeLabel);
            labelLayout.setXpos(xpos);
            labelLayout.setYpos(ypos);
            ypos += labelLayout.getHeight() + LABEL_SPACING;
        }

        // layout center labels
        if (!centerLabels.isEmpty()) {
            KPoint point = edgeLayout.getBendPoints().get(midBendPoint);
            xpos = point.getX() + LABEL_SPACING;
            ypos = point.getY() + LABEL_SPACING;
            for (KLabel edgeLabel : centerLabels) {
                KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(edgeLabel);
                labelLayout.setXpos(xpos);
                labelLayout.setYpos(ypos);
                ypos += labelLayout.getHeight() + LABEL_SPACING;
            }
        }

        // layout tail labels
        xpos = edgeLayout.getTargetPoint().getX() - LABEL_SPACING;
        ypos = edgeLayout.getTargetPoint().getY();
        for (KLabel edgeLabel : headLabels) {
            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(edgeLabel);
            ypos -= labelLayout.getHeight() - LABEL_SPACING;
            labelLayout.setXpos(xpos - labelLayout.getWidth());
            labelLayout.setYpos(ypos);
        }
    }

}
