/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.kgraph

import de.cau.cs.kieler.kiml.formats.IGraphTransformer
import de.cau.cs.kieler.kiml.formats.TransformationData
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KEdgeLayout
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import org.eclipse.elk.core.math.KVector
import org.eclipse.elk.core.math.KVectorChain
import org.eclipse.elk.graph.ElkEdge
import org.eclipse.elk.graph.ElkGraphElement
import org.eclipse.elk.graph.ElkLabel
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.ElkPort
import org.eclipse.elk.graph.ElkShape
import org.eclipse.elk.graph.util.ElkGraphUtil

class KGraphExporter implements IGraphTransformer<ElkNode, KNode> {
    
    override transform(TransformationData<ElkNode, KNode> data) {
        data.targetGraphs.add(data.sourceGraph.transform)
    }
    
    public def KNode transform(ElkNode elkGraph) {
        // Transform everything
        val KNode result = elkGraph.transformEverythingExceptEdges()
        elkGraph.transformEdges()
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
    
    public def create node : createNode() transformNode(ElkNode elkNode) {
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
    
    public def create port : createPort() transformPort(ElkPort elkPort) {
        // Structural information
        port.node = elkPort.parent.transformNode();
        
        // Id
        elkPort.transformId(port)
        
        // Structural information
        elkPort.copyShapeLayoutTo(port);
    }
    
    public def KLabel create label : createLabel() transformLabel(ElkLabel elkLabel) {
        // label text
        label.text = elkLabel.text

        // Structural information
        val parent = elkLabel.parent
        label.parent = switch parent {
            ElkNode: parent.transformNode
            ElkPort: parent.transformPort
            ElkEdge: parent.transformEdge
            default: {
                // We may have created the label, but there is no way to attach it to the graph element
                // it was attached to in the original ELK graph
                return
            }
        }
        
        // Id
        elkLabel.transformId(label)
        
        // Structural information
        elkLabel.copyShapeLayoutTo(label)
    }
    
    public def create edge : createEdge() transformEdge(ElkEdge elkEdge) {
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
            val KVectorChain bendPointVectorChain = new KVectorChain();

            bendPointVectorChain.add(new KVector(section.startX, section.startY))
            section.bendPoints.forEach[bendPoint |
                bendPointVectorChain.add(bendPoint.x, bendPoint.y);
            ];
            bendPointVectorChain.add(new KVector(section.endX, section.endY))

            target.applyVectorChain(bendPointVectorChain);
        }
        
        target.copyProperties(source);
    }
    
    def KNode createNode() {
        return KGraphUtil::createInitializedNode()
    }
    
    def KPort createPort() {
        return KGraphUtil::createInitializedPort
    }
    
    def KEdge createEdge() {
        return KGraphUtil::createInitializedEdge
    }

    def KLabel createLabel() {
        return KGraphUtil::createInitializedLabel(null)
    }
    
    
    override transferLayout(TransformationData<ElkNode, KNode> data) {
        throw new UnsupportedOperationException("TODO: auto-generated method stub")
    }
    
}
    
