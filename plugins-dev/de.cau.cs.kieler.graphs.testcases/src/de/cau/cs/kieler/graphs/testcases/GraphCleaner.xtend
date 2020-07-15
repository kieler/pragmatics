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
package de.cau.cs.kieler.graphs.testcases

import java.util.Set
import org.eclipse.elk.core.options.BoxLayouterOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.SizeConstraint
import org.eclipse.elk.graph.ElkEdge
import org.eclipse.elk.graph.ElkLabel
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.ElkPort
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.elk.graph.properties.IPropertyHolder
import org.eclipse.elk.graph.util.ElkGraphUtil

import static org.eclipse.elk.core.options.CoreOptions.*
import org.eclipse.elk.alg.layered.options.LayeredOptions

/**
 *
 */
class GraphCleaner {
    
    val Set<IProperty<?>> WHITELIST_NODE = newHashSet(
        NODE_LABELS_PLACEMENT, 
        NODE_SIZE_CONSTRAINTS, 
        PORT_CONSTRAINTS,
        POSITION
    )
    
    val Set<IProperty<?>> WHITELIST_HIERARCHICAL_NODE = newHashSet(
        // box layouter specific
        EXPAND_NODES        
    )
    
    val Set<IProperty<?>> WHITELIST_PORT = newHashSet(
        PORT_SIDE,
        PORT_INDEX,
        PORT_LABELS_PLACEMENT,
        POSITION,
        
        LayeredOptions.ALLOW_NON_FLOW_PORTS_TO_SWITCH_SIDES
    )
    
    val Set<IProperty<?>> WHITELIST_LABEL = newHashSet(
        NODE_LABELS_PLACEMENT,   // can be set on both the node or the label
        EDGE_LABELS_PLACEMENT,
        POSITION  
    )
    
    val Set<IProperty<?>> WHITELIST_EDGE = newHashSet(
        BEND_POINTS  
    )


    public def createCleanCopy(ElkNode graph) {
        
        val copy = graph.cleanCopy
        graph.eAllContents.filter(ElkEdge).forEach[e | e.cleanCopy]        
        
        return copy
    }

    def ElkNode cleanCopy(ElkNode node) {
        
        val copy = node.copy
        copy.parent = node.parent.copy
        copy.identifier = node.identifier
        
        // if it's a compound node, don't set width and height,
        if (!node.isHierarchical) {
            copy.width = node.width
            copy.height = node.height
        }
        
        // if the box layouter is configured, remember that
        if (node.isHierarchical) {
            if (node.getProperty(CoreOptions.ALGORITHM) == BoxLayouterOptions.ALGORITHM_ID) {
                copy.setProperty(CoreOptions.ALGORITHM, BoxLayouterOptions.ALGORITHM_ID)
            }
        }        
        
        // preserve certain props for hierarchical nodes
        if (node.isHierarchical) {
            copy.copyProperties(node, WHITELIST_HIERARCHICAL_NODE)
        }
        
        // preserve certain props
        copy.copyProperties(node, WHITELIST_NODE)

//        if (copy.getProperty(CoreOptions.PORT_CONSTRAINTS) == PortConstraints.UNDEFINED)
//            copy.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FREE)
        
        
        // somewhat clear default values
        if (node.getProperty(CoreOptions.NODE_SIZE_CONSTRAINTS) == SizeConstraint.fixed) {
            node.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, null)
        }
        
        node.ports.forEach[ p | p.cleanCopy ]
        node.labels.forEach[ l | l.cleanCopy ]
        node.children.forEach[ c | c.cleanCopy ] 
        
        copy       
    }
    
    def cleanCopy(ElkPort port) {
        val copy = port.copy
        copy.parent = port.parent.copy
        copy.identifier = port.identifier
        copy.width = port.width
        copy.height = port.height
        
        if (port.parent.copy.getProperty(CoreOptions.PORT_CONSTRAINTS).sideFixed) {
            copy.x = port.x
            copy.y = port.y 
        }
        
        port.labels.forEach[ l | l.cleanCopy ]
        
        copy.copyProperties(port, WHITELIST_PORT)

        copy
    }
    
    def cleanCopy(ElkLabel label) {
        val copy = label.copy
        val originalParent = label.parent
        copy.parent = switch originalParent {
            ElkNode: originalParent.copy
            ElkPort: originalParent.copy
            ElkEdge: originalParent.copy
            ElkLabel: originalParent.copy
        }
        copy.identifier = label.identifier
        copy.width = label.width
        copy.height = label.height
        copy.text = label.text
        
        label.labels.forEach[ l | l.cleanCopy ]
        
        copy.copyProperties(label, WHITELIST_LABEL)
        
        copy
    }
    
    def cleanCopy(ElkEdge edge) {
        val copy = edge.copy
        copy.containingNode = edge.containingNode.copy
        copy.identifier = edge.identifier
        
        copy.sources += edge.sources.map[ s | 
            switch s {
                ElkNode: s.copy
                ElkPort: s.copy    
            }
        ]
        copy.targets += edge.targets.map[ t |
            switch t {
                ElkNode: t.copy
                ElkPort: t.copy
            }
        ]
        
        edge.labels.forEach[ l | l.cleanCopy ]
        
        copy.copyProperties(edge, WHITELIST_EDGE)
        
        copy
    }
    
    def create copy: ElkGraphUtil.createNode(null) copy(ElkNode node) { }
    def create copy: ElkGraphUtil.createEdge(null) copy(ElkEdge edge) { }
    def create copy: ElkGraphUtil.createPort(null) copy(ElkPort port) {}
    def create copy: ElkGraphUtil.createLabel(null) copy(ElkLabel label) {}
    
    def copyProperties(IPropertyHolder to, IPropertyHolder from, Set<IProperty<?>> whitelist) {
        from.allProperties.keySet
            .filter[p | whitelist.contains(p)]
            .forEach[p | to.setProperty(p as IProperty<Object>, from.getProperty(p))]
    }

}