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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.graphs.diagram.custom;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Edit policy used to update the hypernodes structure. This edit policy creates a
 * {@link HypernodesCommand} to add and remove hypernodes in the graph.
 * 
 * @author msp
 */
public class HypernodesEditPolicy extends AbstractEditPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return (HypernodesRequest.REQ_UPDATE_HYPERNODES.equals(req.getType()));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(final Request request) {
        if (HypernodesRequest.REQ_UPDATE_HYPERNODES.equals(request.getType())) {
            if (request instanceof HypernodesRequest) {
                HypernodesRequest hyperRequest = (HypernodesRequest) request;
                Map<Set<KNode>, List<KNode>> oldHyperedgeMap = hyperRequest.getHyperedgeMap();
                
                IGraphicalEditPart hostEditPart = (IGraphicalEditPart) getHost();
                HypernodesCommand command = new HypernodesCommand(
                        hostEditPart.getEditingDomain(), "Update Hypernodes",
                        new EObjectAdapter((View) hostEditPart.getModel()));
                
                
                
                return new ICommandProxy(command);
            }
            return null;
        } else {
            return super.getCommand(request);
        }
    }
    
    /**
     * Creates a map of hyperedges to the lists of representing hypernodes.
     * 
     * @param rootNode the root node of the layout graph
     * @return a hyperedge map
     */
    public static Map<Set<KNode>, List<KNode>> createHyperedgeMap(final KNode rootNode) {
        // build the map of hypernodes to their hyperedges
        Map<KNode, Set<KNode>> hypernodeMap = new HashMap<KNode, Set<KNode>>();
        findHyperedges(rootNode, hypernodeMap);
        // build the map of hyperedges to their hypernodes
        Map<Set<KNode>, List<KNode>> hyperedgeMap = new HashMap<Set<KNode>, List<KNode>>();
        for (Map.Entry<KNode, Set<KNode>> entry : hypernodeMap.entrySet()) {
            List<KNode> nodeList = hyperedgeMap.get(entry.getValue());
            if (nodeList == null) {
                nodeList = new LinkedList<KNode>();
                hyperedgeMap.put(entry.getValue(), nodeList);
            }
            nodeList.add(entry.getKey());
        }
        return hyperedgeMap;
    }
    
    private static void findHyperedges(final KNode parentNode,
            final Map<KNode, Set<KNode>> hypernodeMap) {
        for (KNode child : parentNode.getChildren()) {
            if (KimlUtil.getShapeLayout(child).getProperty(
                    LayoutOptions.HYPERNODE)) {
                for (KEdge edge : child.getOutgoingEdges()) {
                    handleNeighbor(child, edge.getTarget(), hypernodeMap);
                }
                for (KEdge edge : child.getIncomingEdges()) {
                    handleNeighbor(child, edge.getSource(), hypernodeMap);
                }
            } else if (!child.getChildren().isEmpty()) {
                findHyperedges(child, hypernodeMap);
            }
        }
    }
    
    private static void handleNeighbor(final KNode hypernode, final KNode neighbor,
            final Map<KNode, Set<KNode>> hypernodeMap) {
        if (!KimlUtil.getShapeLayout(neighbor).getProperty(
                LayoutOptions.HYPERNODE)) {
            // add the normal node to the hyperedge
            Set<KNode> hyperedge = hypernodeMap.get(hypernode);
            if (hyperedge == null) {
                hyperedge = new HashSet<KNode>();
                hypernodeMap.put(hypernode, hyperedge);
            }
            hyperedge.add(neighbor);
        } else if (hypernode != neighbor) {
            // merge the hyperedges of both hypernodes
            Set<KNode> hyperedge1 = hypernodeMap.get(hypernode);
            Set<KNode> hyperedge2 = hypernodeMap.get(neighbor);
            if (hyperedge1 == null && hyperedge2 == null) {
                Set<KNode> hyperedge = new HashSet<KNode>();
                hypernodeMap.put(hypernode, hyperedge);
                hypernodeMap.put(neighbor, hyperedge);
            } else if (hyperedge1 == null) {
                hypernodeMap.put(hypernode, hyperedge2);
            } else if (hyperedge2 == null) {
                hypernodeMap.put(neighbor, hyperedge1);
            } else if (hyperedge1 != hyperedge2) {
                hyperedge1.addAll(hyperedge2);
                hypernodeMap.put(neighbor, hyperedge1);
            }
        }
    }

}
