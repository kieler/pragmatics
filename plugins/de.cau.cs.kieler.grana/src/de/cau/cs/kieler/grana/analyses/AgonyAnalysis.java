/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.grana.analyses;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * 
 * A drawing analysis that ...
 * 
 * @author kcm
 *
 */
public class AgonyAnalysis implements IAnalysis {
    /**
     * 
     * Identifier of the agony analysis.
     * 
     */
    public static final String ID = "de.cau.cs.kieler.grana.agony";

    /**
     * 
     * Index of the agony value in the result array.
     * 
     */
    public static final int INDEX_AGONY_NORMAL = 0;
    public static final int INDEX_AGONY_LINEAR = 1;
    public static final int INDEX_AGONY_QUADRATIC = 2;
    public static final int INDEX_AGONY_LOGARITHMIC = 3;

    private Map<ElkPort, EList<ElkEdge>> outgoingPorts;
    private Map<ElkPort, EList<ElkEdge>> incomingPorts;

    private List<List<Layer>> layerList = new LinkedList<List<Layer>>();

    private Map<ElkNode, List<Layer>> parentLayers = new HashMap<ElkNode, List<Layer>>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {

        progressMonitor.begin("Agony analysis", 1);

        EList<ElkNode> nodes = parentNode.getChildren();

        double agonyNormal = 0.0;
        double agonyLinear = 0.0;
        double agonyQuadratic = 0.0;
        double agonyLogarithmic = 0.0;

        outgoingPorts = new HashMap<ElkPort, EList<ElkEdge>>();
        incomingPorts = new HashMap<ElkPort, EList<ElkEdge>>();

        portEdgesToNodes(parentNode);

        insertInLayerNew(parentNode, 0);
        
        sortLayersNew(0);

//        for (ElkNode parent : parentLayers.keySet()) {
//            System.out.println(parent.getIdentifier());
//            for (Layer l : parentLayers.get(parent)) {
//                System.out.println(l.layerId + ": " + l.nodes);
//            }
//        }

        // insert every node in one layer
//        for (ElkNode node : nodes) {
//            insertInLayer(node, 0);
//        }

        // sort the layers and set the layer id
//        int hierarchieLevel = 0;
//        for (List<Layer> layers : layerList) {
//            sortLayers(layers, hierarchieLevel);
//            hierarchieLevel++;
//        }

        // show the layers in the console
        // for (List<Layer> list : layerList) {
        // System.out.println(
        // "# of layers: " + list.size() + ", level: " + list.get(0).hierarchieLevel);
        // for (Layer l : list) {
        // System.out.println("nodes: " + l.nodes);
        // }
        // }

        for (ElkNode parent : parentLayers.keySet()) {
            for (Layer layer : parentLayers.get(parent)) {
                for (ElkNode node : layer.nodes) {
                    Object[] obj = calcAgony(node, layer.layerId, layer.hierarchieLevel);
                    agonyNormal += (double) obj[INDEX_AGONY_NORMAL];
                    agonyLinear += (double) obj[INDEX_AGONY_LINEAR];
                    agonyQuadratic += (double) obj[INDEX_AGONY_QUADRATIC];
                    agonyLogarithmic += (double) obj[INDEX_AGONY_LOGARITHMIC];
                }
            }
        }
        
//        for (List<Layer> layers : layerList) {
//            for (Layer layer : layers) {
//                for (ElkNode node : layer.nodes) {
//                    Object[] obj = calcAgony(node, layer.layerId, layer.hierarchieLevel);
//                    agonyNormal += (double) obj[INDEX_AGONY_NORMAL];
//                    agonyLinear += (double) obj[INDEX_AGONY_LINEAR];
//                    agonyQuadratic += (double) obj[INDEX_AGONY_QUADRATIC];
//                    agonyLogarithmic += (double) obj[INDEX_AGONY_LOGARITHMIC];
//                }
//            }
//        }

        nodeEdgesToPorts();

//         System.out.println("overall agony: " + agonyNormal + ", " + agonyLinear + ", "
//         + agonyQuadratic + ", " + agonyLogarithmic);

        progressMonitor.done();

        return new Object[] { agonyNormal, agonyLinear, agonyQuadratic, agonyLogarithmic };
    }

    private Object[] calcAgony(final ElkNode source, final int sourceLayerId,
            final int sourceHierarchieLevel) {
        double agonyNormal = 0;
        double agonyLinear = 0;
        double agonyQuadratic = 0;
        double agonyLogarithmic = 0;

        for (ElkEdge edge : source.getOutgoingEdges()) {
            // System.out.println("----------");
            ElkNode target = (ElkNode) edge.getTargets().get(0);

            if (target.getParent() == source.getParent()) {
                int targetLayerId = getLayerOfNode(target).layerId;
                // System.out.println(source.getIdentifier() + " -> " + target.getIdentifier());
                // System.out.println(sourceLayerId + " - " + targetLayerId);
                int difference = sourceLayerId - targetLayerId;
                agonyNormal += Math.max((difference + 1), 0);
                agonyLinear += Math.max(difference, 0);
                if (difference > 0) {
                    agonyQuadratic += Math.pow(difference, 2);
                    agonyLogarithmic += Math.log10(difference + 1);
                }
                // System.out.println("agony: " + agonyNormal + ", " + agonyLinear + ", "
                // + agonyQuadratic + ", " + agonyLogarithmic);
            } else {
                ElkNode sourceParent = source;
                ElkNode targetParent = target;
                int targetHierarchieLevel = getLayerOfNode(target).hierarchieLevel;

                if (targetHierarchieLevel < sourceHierarchieLevel) {
                    // System.out.println(targetHierarchieLevel + " < " + sourceHierarchieLevel);
                    for (int i = sourceHierarchieLevel; i > targetHierarchieLevel; i--) {
                        sourceParent = sourceParent.getParent();
                    }
                    // System.out.println(sourceParent + ", " + targetParent);
                } else if (sourceHierarchieLevel < targetHierarchieLevel) {
                    // System.out.println(sourceHierarchieLevel + " < " + targetHierarchieLevel);
                    for (int i = targetHierarchieLevel; i > sourceHierarchieLevel; i--) {
                        targetParent = targetParent.getParent();
                    }
                    // System.out.println(sourceParent + ", " + targetParent);
                } else {
                    // System.out.println(sourceHierarchieLevel + " = " + targetHierarchieLevel);
                    for (int i = sourceHierarchieLevel; i > 0; i--) {
                        sourceParent = sourceParent.getParent();
                        targetParent = targetParent.getParent();
                    }
                    // System.out.println(sourceParent + ", " + targetParent);
                }

                int sourceParentLayerId = getLayerOfNode(sourceParent).layerId;
                int targetParentLayerId = getLayerOfNode(targetParent).layerId;
                int difference = sourceParentLayerId - targetParentLayerId;
                agonyNormal += Math.max((difference + 1), 0);
                agonyLinear += Math.max(difference, 0);

                if (difference > 0) {
                    agonyQuadratic += Math.pow(difference, 2);
                    agonyLogarithmic += Math.log10(difference + 1);
                }
                // System.out.println(agonyNormal + ", " + agonyLinear + ", " + agonyQuadratic + ",
                // "
                // + agonyLogarithmic);
            }
            // System.out.println("----------");
        }

        return new Object[] { agonyNormal, agonyLinear, agonyQuadratic, agonyLogarithmic };
    }

    private void portEdgesToNodes(final ElkNode node) {
        for (ElkPort port : node.getPorts()) {
            for (ElkEdge outgoing : port.getOutgoingEdges()) {
                if (outgoingPorts.keySet().contains(port)) {
                    EList<ElkEdge> edges = outgoingPorts.get(port);
                    edges.add(outgoing);
                    outgoingPorts.put(port, edges);
                } else {
                    EList<ElkEdge> edges = new BasicEList<ElkEdge>();
                    edges.add(outgoing);
                    outgoingPorts.put(port, edges);
                }

                outgoing.getSources().add(node);
                node.getOutgoingEdges().add(outgoing);
            }
            for (ElkEdge incoming : port.getIncomingEdges()) {
                if (incomingPorts.keySet().contains(port)) {
                    EList<ElkEdge> edges = incomingPorts.get(port);
                    edges.add(incoming);
                    incomingPorts.put(port, edges);
                } else {
                    EList<ElkEdge> edges = new BasicEList<ElkEdge>();
                    edges.add(incoming);
                    incomingPorts.put(port, edges);
                }

                incoming.getTargets().add(node);
                node.getIncomingEdges().add(incoming);
            }
            port.getOutgoingEdges().removeAll(port.getOutgoingEdges());
            port.getIncomingEdges().removeAll(port.getIncomingEdges());
        }

        for (ElkNode child : node.getChildren()) {
            portEdgesToNodes(child);
        }
    }

    private void nodeEdgesToPorts() {
        for (ElkPort port : outgoingPorts.keySet()) {
            EList<ElkEdge> edges = outgoingPorts.get(port);
            for (ElkEdge edge : edges) {
                edge.getSources().remove(port.getParent());
                edge.getSources().add(port);
            }
        }
        for (ElkPort port : incomingPorts.keySet()) {
            EList<ElkEdge> edges = incomingPorts.get(port);
            for (ElkEdge edge : edges) {
                edge.getTargets().remove(port.getParent());
                edge.getTargets().add(port);
            }
        }
    }

    private void insertInLayerNew(final ElkNode parent, final int hierarchyLevel) {
        for (ElkNode child : parent.getChildren()) {
            if (!child.getChildren().isEmpty()) {
                insertInLayerNew(child, hierarchyLevel + 1);
            }
        }

        List<Layer> layers = new BasicEList<AgonyAnalysis.Layer>();

        for (ElkNode child : parent.getChildren()) {
            double nodeStart = child.getX();
            double nodeEnd = child.getX() + child.getWidth();

            Layer layer = null;
            Iterator<Layer> layerIterator = layers.iterator();

            while (layerIterator.hasNext()) {
                Layer current = layerIterator.next();
                if (nodeStart <= current.end && nodeEnd >= current.start) {
                    if (layer == null) {
                        layer = current;
                        layer.start = Math.min(current.start, nodeStart);
                        layer.end = Math.max(current.end, nodeEnd);
                    } else {
                        layerIterator.remove();
                        layer.start = Math.min(layer.start, current.start);
                        layer.end = Math.max(layer.end, current.end);
                        layer.nodes.addAll(current.nodes);
                    }
                }
            }

            if (layer == null) {
                layer = new Layer();
                layer.start = nodeStart;
                layer.end = nodeEnd;
                layers.add(layer);
            }

            layer.nodes.add(child);
            layer.hierarchieLevel = hierarchyLevel;
        }

        parentLayers.put(parent, layers);
    }

    private void insertInLayer(final ElkNode node, final int hierarchieLayer) {
        double nodeStart = node.getX();
        ElkNode parent = node.getParent();
        while (parent != null) {
            nodeStart += parent.getX();
            parent = parent.getParent();
        }
        double nodeEnd = nodeStart + node.getWidth();

        if (layerList.size() < hierarchieLayer + 1) {
            layerList.add(new LinkedList<Layer>());
        }

        Layer layer = null;
        Iterator<Layer> layerIterator = layerList.get(hierarchieLayer).iterator();

        while (layerIterator.hasNext()) {
            Layer current = layerIterator.next();
            if (nodeStart <= current.end && nodeEnd >= current.start) {
                if (layer == null) {
                    layer = current;
                    layer.start = Math.min(current.start, nodeStart);
                    layer.end = Math.max(current.end, nodeEnd);
                } else {
                    layerIterator.remove();
                    layer.start = Math.min(layer.start, current.start);
                    layer.end = Math.max(layer.end, current.end);
                    layer.nodes.addAll(current.nodes);
                }
            }
        }

        if (layer == null) {
            layer = new Layer();
            layer.start = nodeStart;
            layer.end = nodeEnd;
            layerList.get(hierarchieLayer).add(layer);
        }

        layer.nodes.add(node);

        if (node.isHierarchical()) {
            EList<ElkNode> children = node.getChildren();
            for (ElkNode child : children) {
                insertInLayer(child, hierarchieLayer + 1);
            }
        }

    }

    private void sortLayersNew(int hierarchieLevel) {
        for (ElkNode parent : parentLayers.keySet()) {
            Collections.sort(parentLayers.get(parent), new Comparator<Layer>() {

                @Override
                public int compare(final Layer l1, final Layer l2) {
                    return Double.compare(l1.start, l2.start);
                }

            });

            for (Layer layer : parentLayers.get(parent)) {
                layer.layerId = parentLayers.get(parent).indexOf(layer) + 1;
            }
        }
    }

    private void sortLayers(final List<Layer> layers, final int hierarchieLevel) {
        Collections.sort(layers, new Comparator<Layer>() {

            @Override
            public int compare(final Layer l1, final Layer l2) {
                return Double.compare(l1.start, l2.start);
            }
        });

        for (Layer layer : layers) {
            layer.layerId = layers.indexOf(layer) + 1; // layerId should start with 1
            layer.hierarchieLevel = hierarchieLevel;
        }
    }

    private Layer getLayerOfNode(final ElkNode node) {
//        for (List<Layer> layers : layerList) {
//            for (Layer layer : layers) {
//                if (layer.nodes.indexOf(node) > -1) {
//                    return layer;
//                }
//            }
//        }
        
        for (ElkNode parent : parentLayers.keySet()) {
            for (Layer layer : parentLayers.get(parent)) {
                if (layer.nodes.indexOf(node) > -1) {
                    return layer;
                }
            }
        }
        return null;
    }

    /**
     * 
     * Class to present a layer.
     * 
     * @author kcm
     *
     */
    public class Layer {
        private double start = 0;
        private double end = 0;
        private int layerId = 0;
        private int hierarchieLevel = 0;
        private List<ElkNode> nodes = Lists.newArrayList();

    }

}
