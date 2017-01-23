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
package de.cau.cs.kieler.graphs.klighd.syntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KEdgeLayout
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import org.eclipse.elk.core.math.KVectorChain
import org.eclipse.elk.graph.ElkEdge
import org.eclipse.elk.graph.ElkLabel
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.ElkPort
import org.eclipse.elk.graph.ElkShape
import org.eclipse.elk.graph.util.ElkGraphUtil
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import org.eclipse.elk.graph.ElkGraphElement
import de.cau.cs.kieler.klighd.kgraph.KGraphElement

/**
 * Turns an ELK graph into a diagram KLighD knows how to display.
 * 
 * <p>Note: The coordinate transformtion here is not correct yet, at least for edges.</p>
 */
class ElkGraphDiagramSynthesis extends AbstractStyledDiagramSynthesis<ElkNode> {
    
    @Inject extension KNodeExtensions;
    @Inject extension KPortExtensions;
    @Inject extension KLabelExtensions;
    @Inject extension KEdgeExtensions;
    
    override transform(ElkNode elkGraph) {
        // Transform everything
        val KNode result = elkGraph.transformEverythingExceptEdges();
        elkGraph.transformEdges();

        // Enable label management
        addLabelManager(result)
        
        // Initialize the rendering code
        initRenderings(result);

        // Enrich the rendering
        enrichRenderings(result)

        return result
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // TRANSFORMATION OF THINGS TO OTHER THINGS
    
    private def KNode transformEverythingExceptEdges(ElkNode node) {
        val KNode result = node.transformNode();
        
        node.ports.forEach[elkPort |
            elkPort.transformPort();
            elkPort.labels.forEach[elkLabel | elkLabel.transformLabel()];
        ];
        node.labels.forEach[elkLabel | elkLabel.transformLabel()];
        node.children.forEach[elkNode | elkNode.transformEverythingExceptEdges()];
        
        return result;
    }
    
    private def void transformEdges(ElkNode node) {
        node.containedEdges.forEach[elkEdge | elkEdge.transformEdge()];
        node.children.forEach[elkNode | elkNode.transformEdges()];
    }
    
    private def void transformId(ElkGraphElement elkEle, KGraphElement kEle) {
        // Convert the id
        if (!elkEle.identifier.isNullOrEmpty) {
            val id = KGraphFactory.eINSTANCE.createKIdentifier
            id.id = elkEle.identifier
            kEle.data += id
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////
    // TRANSFORMATION DETAILS
    
    private def create node : createNode() transformNode(ElkNode elkNode) {
        // Structural information
        if (elkNode.parent != null) {
            // We're not transforming the graph itself, so we actually do have a parent
            node.parent = elkNode.parent.transformNode();
        }
        
        // Id
        elkNode.transformId(node)
        
        // Layout information
        elkNode.copyShapeLayoutTo(node);
    }
    
    private def create port : createPort() transformPort(ElkPort elkPort) {
        // Structural information
        port.node = elkPort.parent.transformNode();
        
        // Id
        elkPort.transformId(port)
        
        // Structural information
        elkPort.copyShapeLayoutTo(port);
    }
    
    private def create label : createLabel(null) transformLabel(ElkLabel elkLabel) {
        // Structural information
        if (elkLabel.parent instanceof ElkNode) {
            label.parent = (elkLabel.parent as ElkNode).transformNode();
        } else if (elkLabel.parent instanceof ElkPort) {
            label.parent = (elkLabel.parent as ElkPort).transformPort();
        } else if (elkLabel.parent instanceof ElkEdge) {
            label.parent = (elkLabel.parent as ElkEdge).transformEdge();
        } else {
            // We may have created the label, but there is no way to attach it to the graph element
            // it was attached to in the original ELK graph
            return;
        }
        
        // Id
        elkLabel.transformId(label)
        
        // Structural information
        elkLabel.copyShapeLayoutTo(label);
    }
    
    private def create edge : createEdge() transformEdge(ElkEdge elkEdge) {
        // Ensure that the edge has exactly one source and one target
        if (elkEdge.sources.size() != 1 || elkEdge.targets.size() != 1) {
            throw new IllegalArgumentException("Graph contains an edge that does not have exactly "
                + "one source and one target");
        }
        
        // Structural information
        val ElkNode elkSourceNode = ElkGraphUtil.connectableShapeToNode(elkEdge.sources.get(0));
        val ElkPort elkSourcePort = ElkGraphUtil.connectableShapeToPort(elkEdge.sources.get(0));
        val ElkNode elkTargetNode = ElkGraphUtil.connectableShapeToNode(elkEdge.targets.get(0));
        val ElkPort elkTargetPort = ElkGraphUtil.connectableShapeToPort(elkEdge.targets.get(0));
        
        edge.source = elkSourceNode.transformNode();
        edge.target = elkTargetNode.transformNode();
        
        if (elkSourcePort != null) {
            edge.sourcePort = elkSourcePort.transformPort();
        }
        
        if (elkTargetPort != null) {
            edge.targetPort = elkTargetPort.transformPort();
        }
        
        // Id 
        elkEdge.transformId(edge)
        
        // Structural information
        elkEdge.copyEdgeLayoutTo(edge);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // LAYOUT THINGS
    
    /**
     * Copies layout information as well as properties from source to target.
     */
    private def void copyShapeLayoutTo(ElkShape source, KShapeLayout target) {
        target.xpos = source.x as float;
        target.ypos = source.y as float;
        target.height = source.height as float;
        target.width = source.width as float;
        
        target.copyProperties(source);
    }
    
    private def void copyEdgeLayoutTo(ElkEdge source, KEdgeLayout target) {
        // We require the edge to have exactly one edge segment
        if (source.sections.size() > 1) {
            throw new IllegalArgumentException("Graph contains an edge with >1 edge sections.");
        } else if (source.sections.size() == 1) {
            // Copy the edge section's information over to the target edge
            val section = source.sections.get(0);
            
            target.sourcePoint.x = section.startX as float;
            target.sourcePoint.y = section.startY as float;
            
            val KVectorChain bendPointVectorChain = new KVectorChain();
            section.bendPoints.forEach[bendPoint |
                bendPointVectorChain.add(bendPoint.x, bendPoint.y);
            ];
            target.applyVectorChain(bendPointVectorChain);
            
            target.targetPoint.x = section.endX as float;
            target.targetPoint.y = section.endY as float;
        }
        
        target.copyProperties(source);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // RENDERING THINGS
    
    /**
     * Make sure the node has a valid size and a label (if defaults are switched on).
     * 
     * @param node the node whose rendering to enrich.
     */
    protected def override void enrichNodeRendering(KNode node) {
        super.enrichNodeRendering(node);
        KGraphUtil.configureWithDefaultValues(node);
    }

    /**
     * Make sure the port has a valid size and a label (if defaults are switched on).
     * 
     * @param port the port whose rendering to enrich.
     */
    protected def override void enrichPortRendering(KPort port) {
        super.enrichPortRendering(port);
        KGraphUtil.configureWithDefaultValues(port);
    }

    /**
     * Possibly adds a proper rendering to the given edge. If no rendering is associated with the edge
     * yet, a default rendering with an arrowhead is added to it.
     * 
     * @param edge the edge whose rendering to enrich.
     */
    protected def override void enrichEdgeRendering(KEdge edge) {
        super.enrichEdgeRendering(edge);
        KGraphUtil.configureWithDefaultValues(edge);
    }
    
}