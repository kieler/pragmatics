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

import net.ogdf.lib.DPolyline;
import net.ogdf.lib.EdgeElement;
import net.ogdf.lib.Graph;
import net.ogdf.lib.GraphAttributes;
import net.ogdf.lib.LayoutModule;
import net.ogdf.lib.NodeElement;
import net.ogdf.lib.Ogdf;
import net.ogdf.lib.SugiyamaLayout;
import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 *
 * @author msp
 */
public class OgdfLayoutProvider extends AbstractLayoutProvider {

    static {
        Ogdf.loadLibrary();
    }
    
    /** definition of available layout algorithms. */
    enum LayoutAlgorithm {
        SUGIYAMA
    }
    
    /** the layout algorithm selected for this layout provider. */
    private LayoutAlgorithm layoutAlgorithm;
    
    /**
     * {@inheritDoc}
     */
    public void initialize(final String parameter) {
        layoutAlgorithm = LayoutAlgorithm.valueOf(parameter);
    }
    
    /**
     * {@inheritDoc}
     */
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("OGDF layout", 100);
        
        GraphAttributes graphAttributes = transformGraph(layoutNode);
        
        LayoutModule layoutModule;
        switch (layoutAlgorithm) {
        case SUGIYAMA:
            layoutModule = new SugiyamaLayout();
            break;
        default:
            throw new KielerException("No valid OGDF layout algorithm was selected.");
        }
        layoutModule.call(graphAttributes);
        
        applyLayout(graphAttributes);
        
        progressMonitor.done();
    }
    
    /** map of KIELER nodes to OGDF nodes. */
    private Map<KNode, NodeElement> knode2ogdfNodeMap = new LinkedHashMap<KNode, NodeElement>();
    /** map of KIELER edges to OGDF edges. */
    private Map<KEdge, EdgeElement> kedge2ogdfEdgeMap = new LinkedHashMap<KEdge, EdgeElement>();
    
    /**
     * Transforms the given layout graph into an OGDF graph.
     * 
     * @param layoutNode the parent node of the layout graph.
     * @return an OGDF graph with attached layout attributes
     */
    private GraphAttributes transformGraph(final KNode layoutNode) {
        knode2ogdfNodeMap.clear();
        Graph graph = new Graph();
        GraphAttributes graphAttributes = new GraphAttributes(graph,
                GraphAttributes.nodeGraphics | GraphAttributes.edgeGraphics);
        // process nodes
        for (KNode knode : layoutNode.getChildren()) {
            NodeElement ogdfNode = graph.newNode();
            knode2ogdfNodeMap.put(knode, ogdfNode);
        }
        // process edges
        for (KNode knode1 : layoutNode.getChildren()) {
            for (KEdge kedge : knode1.getOutgoingEdges()) {
                KNode knode2 = kedge.getTarget();
                if (knode2.getParent() == knode1.getParent()) {
                    NodeElement ogdfNode1 = knode2ogdfNodeMap.get(knode1);
                    NodeElement ogdfNode2 = knode2ogdfNodeMap.get(knode2);
                    EdgeElement ogdfEdge = graph.newEdge(ogdfNode1, ogdfNode2);
                    kedge2ogdfEdgeMap.put(kedge, ogdfEdge);
                }
            }
        }
        return graphAttributes;
    }
    
    /**
     * Applies the layout result to the original graph.
     * 
     * @param graphAttributes OGDF graph with attached layout attributes
     */
    private void applyLayout(final GraphAttributes graphAttributes) {
        // apply node layout
        for (KNode knode : knode2ogdfNodeMap.keySet()) {
            NodeElement ogdfNode = knode2ogdfNodeMap.get(knode);
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(knode);
            nodeLayout.setXpos((float)graphAttributes.x(ogdfNode));
            nodeLayout.setYpos((float)graphAttributes.y(ogdfNode));
        }
        // apply edge layout
        for (KEdge kedge : kedge2ogdfEdgeMap.keySet()) {
            EdgeElement ogdfEdge = kedge2ogdfEdgeMap.get(kedge);
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
            DPolyline bends = graphAttributes.bends(ogdfEdge);
            
        }
    }

}
