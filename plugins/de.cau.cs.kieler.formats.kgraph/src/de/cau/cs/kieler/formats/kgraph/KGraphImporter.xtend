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

import de.cau.cs.kieler.formats.IGraphTransformer
import de.cau.cs.kieler.formats.TransformationData
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KEdgeLayout
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout
import org.eclipse.elk.core.util.ElkUtil
import org.eclipse.elk.graph.ElkEdge
import org.eclipse.elk.graph.ElkGraphElement
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.ElkShape
import org.eclipse.elk.graph.util.ElkGraphUtil
import de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil

/**
 * Converts a KGraph into an ELKGraph by simply translating 
 * the graph elements of the KGraph into the corresponding 
 * graph elements of the ELKGraph, e.g. {@link KNode} to {@link ElkNode}.
 */
class KGraphImporter implements IGraphTransformer<KNode, ElkNode> {
    
    override transform(TransformationData<KNode, ElkNode> data) {
        data.targetGraphs += data.sourceGraph.transform
    }
    
    public def ElkNode transform(KNode kGraph) {
        KGraphDataUtil.loadDataElements(kGraph)
        
        // Transform everything
        val result = kGraph.transformEverythingExceptEdges
        // edges
        kGraph.transformEdges
        
        return result
    }
     
    ///////////////////////////////////////////////////////////////////////////////
    // TRANSFORMATION OF THINGS TO OTHER THINGS
    
    private def ElkNode transformEverythingExceptEdges(KNode node) {
        val ElkNode result = node.transformNode
        
        node.ports.forEach[elkPort |
            elkPort.transformPort
            elkPort.labels.forEach[elkLabel | elkLabel.transformLabel]
        ]
        node.labels.forEach[elkLabel | elkLabel.transformLabel]
        node.children.forEach[elkNode | elkNode.transformEverythingExceptEdges]
        
        return result;
    }
    
    private def void transformEdges(KNode node) {
        node.outgoingEdges.forEach[kEdge | kEdge.transformEdge]
        node.children.forEach[kNode | kNode.transformEdges]
    }
    
    private def void transformId(KGraphElement kEle, ElkGraphElement elkEle) {
        // Convert the id
        val id = kEle.getData(KIdentifier)
        if (id !== null) {
            elkEle.identifier = id.id
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////
    // TRANSFORMATION DETAILS
    
    private def create eNode : ElkGraphUtil.createNode(null) transformNode(KNode kNode) {
        // Structural information
        if (kNode.parent !== null) {
            // We're not transforming the graph itself, so we actually do have a parent
            eNode.parent = kNode.parent.transformNode
        }
        
        kNode.transformId(eNode)
        kNode.copyShapeLayoutTo(eNode)
    }
    
    private def create ePort : ElkGraphUtil.createPort(null) transformPort(KPort kPort) {
        // Structural information
        ePort.parent = kPort.node.transformNode
        
        kPort.transformId(ePort)
        kPort.copyShapeLayoutTo(ePort);
    }
    
    private def create eLabel : ElkGraphUtil.createLabel(null) transformLabel(KLabel kLabel) {
        // Structural information
        val parent = kLabel.parent
        eLabel.parent = switch parent {
            KNode: parent.transformNode
            KPort: parent.transformPort
            KEdge: parent.transformEdge
            default: {
                // We may have created the label, but there is no way to attach it to the graph element
                // it was attached to in the original ELK graph
                return
            }
        }
        
        kLabel.transformId(eLabel)
        kLabel.copyShapeLayoutTo(eLabel);
    }
    
    private def create eEdge : ElkGraphUtil.createEdge(null) transformEdge(KEdge kEdge) {

        // Structural information
        if (kEdge.sourcePort !== null) {
            eEdge.sources += kEdge.sourcePort.transformPort 
        } else {
            eEdge.sources += kEdge.source.transformNode
        }
        
        if (kEdge.targetPort !== null) {
            eEdge.targets += kEdge.targetPort.transformPort
        } else {
            eEdge.targets += kEdge.target.transformNode
        }
        
        val containingNode = ElkGraphUtil.findBestEdgeContainment(eEdge)
        eEdge.containingNode = containingNode
        
        kEdge.transformId(eEdge)
        kEdge.labels.forEach[ l | l.transformLabel ]
        kEdge.copyEdgeLayoutTo(eEdge);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // LAYOUT THINGS
    
    /**
     * Copies layout information as well as properties from source to target.
     */
    private def void copyShapeLayoutTo(KShapeLayout source, ElkShape target) {
        target.x = source.xpos;
        target.y = source.ypos;
        target.height = source.height;
        target.width = source.width;
        println(source.allProperties)
        target.copyProperties(source);
    }
    
    private def void copyEdgeLayoutTo(KEdgeLayout source, ElkEdge target) {
        
        val vc = source.createVectorChain
        val section = ElkGraphUtil.firstEdgeSection(target, true, true)
        ElkUtil.applyVectorChain(vc, section)
        
        target.copyProperties(source);
    }

    override transferLayout(TransformationData<KNode, ElkNode> data) {
        throw new UnsupportedOperationException()
    }
    
}