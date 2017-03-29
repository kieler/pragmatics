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

import java.util.function.Function
import java.util.function.Predicate
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.util.IGraphElementVisitor
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.elk.graph.util.ElkGraphUtil

/**
 * A collection of graph filters and modifiers that can be re-used during graph test case generation.
 */
final class FiltersAndModifiers {


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*                                   F I L T E R S                                       */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    public static val Function<Integer, Predicate<ElkNode>> MINIMUM_NODES = [ nodeCnt | 
        [ root | root.allNodes.size > nodeCnt ]
    ]

    public static val Predicate<ElkNode> NO_HYPERNODES = [ root | 
        root.allNodes.findFirst[n | n.getProperty(CoreOptions.HYPERNODE)] == null
    ]
    
    public static val Predicate<ElkNode> NO_HIERARCHICAL_NODES = [ root |
        val v  = root.allNodes.findFirst[ n | 
            !n.children.nullOrEmpty
        ] 
        v == null
    ]

    public static def allNodes(ElkNode n) {
        n.eAllContents.filter(ElkNode)
    }        


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*                                M O D I F I E R S                                      */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    
    public static val IGraphElementVisitor REMOVE_ISOLATED_NODES = [ node |
        switch node {
            ElkNode case node.eContainer == null: {
                node.allNodes
                    .toList // avoid concurrent modification
                    .filter [ n | n.children.empty ]                        // atomic nodes
                    .filter[ n | ElkGraphUtil.allIncidentEdges(n).isEmpty ] // isolated
                    .forEach[ n | n.parent = null ]                         // remove them
            }
        }
    ]
    
    public static val IGraphElementVisitor REMOVE_UNCONNECTED_PORTS = [ node |
        switch node {
            ElkNode case node.eContainer == null: {
                node.allNodes
                    .toList // avoid concurrent modification
                    .forEach[ n |
                        val unconnectedPorts = n.ports.filter[p | ElkGraphUtil.allIncidentEdges(p).empty].toList 
                        n.ports -= unconnectedPorts    
                    ]
            }
        }
    ]
    
    public static val Function<IProperty, IGraphElementVisitor> REMOVE_PROPERTY = [ prop |
        [ e |
           e.setProperty(prop, null) 
        ]
    ]
    
    public static val IGraphElementVisitor UNSET_NODE_LABEL_PLACEMENT_IF_SPECIFIED_ON_LABELS = [ ele |
        switch ele {
            ElkNode: {
                val specifiedForAllPorts = ele.labels
                    .map[it.getProperty(CoreOptions.NODE_LABELS_PLACEMENT)]
                    .filter[it == null]
                    .empty
                if (specifiedForAllPorts) {
                    ele.setProperty(CoreOptions.NODE_LABELS_PLACEMENT, null)
                }
            }
        }
    ]

}