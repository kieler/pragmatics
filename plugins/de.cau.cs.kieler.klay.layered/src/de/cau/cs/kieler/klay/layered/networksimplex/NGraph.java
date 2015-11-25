/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.networksimplex;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A graph structure used by the {@link NetworkSimplex} algorithm.
 * The class holds a list of nodes and provides some convenient methods. 
 * 
 * @author uru
 */
public class NGraph {

    // SUPPRESS CHECKSTYLE NEXT 2 VisibilityModifier
    /** The nodes of the network simplex graph. */
    public List<NNode> nodes = Lists.newArrayList();
    
    /**
     * Converts this {@link NGraph} to a KGraph and writes it to the specified filed.
     * 
     * @param filePath a path to a file on the filesystem
     */
    public void writeDebugGraph(final String filePath) {
        
        KNode root = KimlUtil.createInitializedNode();
        root.getData(KLayoutData.class).setProperty(LayoutOptions.DIRECTION, Direction.DOWN);
        Map<NNode, KNode> nodeMap = Maps.newHashMap();
        
        for (NNode nNode : nodes) {
            KNode kNode = KimlUtil.createInitializedNode();
            root.getChildren().add(kNode);
            nodeMap.put(nNode, kNode);
            
            KLabel label = KimlUtil.createInitializedLabel(kNode);
            label.setText(nNode.type + " " + nNode.layer);
        }

        
        for (NNode nNode : nodes) {
            for (NEdge nEdge : nNode.getOutgoingEdges()) {
                KEdge kEdge = KimlUtil.createInitializedEdge();
                kEdge.setSource(nodeMap.get(nEdge.source));
                kEdge.setTarget(nodeMap.get(nEdge.target));
                
                KLabel label = KimlUtil.createInitializedLabel(kEdge);
                label.setText(nEdge.weight + " " + nEdge.delta);
                        
            }
        }
        
        ResourceSet rs = new ResourceSetImpl();
        Resource r = rs.createResource(URI.createFileURI(filePath));
        r.getContents().add(root);
        try {
            r.save(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a topological ordering and checks for back edges.
     * 
     * Note that this method writes to the {@link NNode#id} field.
     * 
     * @return true if the graph is acyclic, false if it is cyclic.
     */
    public boolean isAcyclic() {

        int id = 0;
        for (NNode n : nodes) {
            n.id = id++;
        }
        
        // initialize the number of incident edges for each node
        int[] incident = new int[nodes.size()];
        int[] layer = new int[nodes.size()];
        for (NNode node : nodes) {
            incident[node.id] += node.getIncomingEdges().size();
        }

        LinkedList<NNode> roots = Lists.newLinkedList();
        for (NNode node : nodes) {
            if (node.getIncomingEdges().isEmpty()) {
                roots.add(node);
            }
        }
        if (roots.isEmpty() && !nodes.isEmpty()) {
            return false;
        }
        while (!roots.isEmpty()) {
            NNode node = roots.poll();
            
            for (NEdge edge : node.getOutgoingEdges()) {
                NNode target = edge.getTarget();
                layer[target.id] = Math.max(layer[target.id], layer[node.id] + 1);
                incident[target.id]--;
                if (incident[target.id] == 0) {
                    roots.add(target);
                }
            }
        }
        
        // check for backward edges
        for (NNode node : nodes) {
            for (NEdge edge : node.getOutgoingEdges()) {
                if (layer[edge.target.id] <= layer[edge.source.id]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
