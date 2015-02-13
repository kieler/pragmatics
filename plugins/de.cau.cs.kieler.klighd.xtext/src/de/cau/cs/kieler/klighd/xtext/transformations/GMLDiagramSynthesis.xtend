/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext.transformations

import com.google.common.collect.ImmutableList
import com.google.common.collect.Maps
import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.math.KVector
import de.cau.cs.kieler.core.math.KVectorChain
import de.cau.cs.kieler.core.math.KielerMath
import de.cau.cs.kieler.kiml.formats.gml.CollectionElement
import de.cau.cs.kieler.kiml.formats.gml.Element
import de.cau.cs.kieler.kiml.formats.gml.GMLModel
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.util.FixedLayoutProvider
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.util.KlighdProperties
import java.util.List

/**
 * Generic diagram synthesis for GML files. 
 * 
 * @author uru
 */
class GMLDiagramSynthesis extends AbstractDiagramSynthesis<GMLModel> {
    
    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KColorExtensions
    @Inject extension KPolylineExtensions
    
    // TODO one gml may contain multiple graphs, thus this needs to be 
    // in the scope of the currently handles graph 
    val idMap = Maps.<Integer, KNode>newHashMap
    var hasPositions = false
    
    public static val SynthesisOption SHOW_LABELS = SynthesisOption::createCheckOption(
        "Labels", true)
        
    override getDisplayedSynthesisOptions() {
         return ImmutableList::of(
             SHOW_LABELS
         )
    } 
    
    override transform(GMLModel model) {
        
        return createNode => [ root | 
            
            // first only convert all the nodes
            model.elements.forEach[it.convert(root)]
 
            // shift positions as gml specifies node centers
            root.centerNodes
 
            // gml edges point to node centers, clip them at node boundaries
            if (hasPositions) {
                root.clipEdges
            }
                       
            // set the fixed layouter if we found positions    
            if (hasPositions) {
                root.setLayoutOption(LayoutOptions.ALGORITHM, FixedLayoutProvider.ID)
            }
        ]
    }
    
    private def Iterable<Element> getElements(Element d) {
        switch (d) {
            CollectionElement: {
                val CollectionElement ce = (d as CollectionElement)
                ce.elements
            } 
            default: #[]
        }
    }
     
    private def void convert(Element d, KNode parent) {
        switch d.key {
            case "graph": {
                // first convert all nodes such that they are available for edges
                d.elements.filter[it.key == "node"].forEach[ e |
                    e.createKNode(parent)
                ]
                
                // convert the edges
                d.elements.filter[it.key == "edge"].forEach[ e |
                    createKEdge(e)
                ]    
                
                // currently there is nothing else we want to convert
            }
        }
    } 
    
    private def void createKNode(Element e, KNode parent) {
        e.createNode => [ node |
            parent.children += node
            
            node.layout.setProperty(LayoutOptions.POSITION, new KVector())
            node.addRectangle => [it.lineWidth = 1 ]
            // style and position of created noode
            e.elements.forEach[it.convertNode(node)]
        ]
    }
    
    private def void createKEdge(Element e) {
        e.createEdge => [ edge |
            edge.addPolyline
            edge.layout.setProperty(LayoutOptions.BEND_POINTS, new KVectorChain())
            e.elements.forEach[it.convertEdge(edge)]
        ]
    }
    
    private def void convertNode(Element e, KNode node) {
        switch e.key {
            
            case "id" : idMap.put(e.value.toInt, node)
            
            case "label": if (SHOW_LABELS.booleanValue) 
                            KimlUtil.createInitializedLabel(node).text = e.value.stripQuotes
            
            case "graphics" : e.elements.forEach[it.convertNode(node)]
            
            case "x": {
                hasPositions = true
                val v = node.layout.getProperty(LayoutOptions.POSITION)
                v.x = e.value.toFloat
            }
            case "y": {
                hasPositions = true
                val v = node.layout.getProperty(LayoutOptions.POSITION)
                v.y = e.value.toFloat
            }
            case "w" : node.width = e.value.toFloat
            case "h" : node.height = e.value.toFloat
            
            case "line": node.KRendering.foreground = e.value.stripQuotes.color
            
            case "fill": {
                node.KRendering.background = e.value.stripQuotes.color
                //node.KRendering.background.propagateToChildren = true
            }
            
            case "type": switch e.value.stripQuotes {
                 case "oval": {
                     node.KContainerRendering.invisible = true
                     node.KContainerRendering.addEllipse
                 }
            } 
            
            // compound nodes
            case "node": {
                
                // create a knode and 
                // recursively add children
                e.createKNode(node)
            
                // by default, do not expand
                node.layout.setProperty(KlighdProperties.EXPAND, false)

                // if this is the first child node, style the parent as a 
                // compound node with special rendering and the ability 
                // to collapse and expand
                val ren = node.KContainerRendering
                if (!ren.getProperty(KlighdProperties.COLLAPSED_RENDERING)) {
                    // use the currently attached krendering as collapsed rendering
                    ren.setProperty(KlighdProperties.COLLAPSED_RENDERING, true)
                    ren.addDoubleClickAction(KlighdConstants.ACTION_COLLAPSE_EXPAND)

                    // add another rendering which will be used if the node is expanded
                    node.addRoundedRectangle(5, 5) => [ er |
                        er.background = "#0000FF".color
                        er.background.alpha = 20
                        er.setProperty(KlighdProperties.EXPANDED_RENDERING, true)
                        er.addDoubleClickAction(KlighdConstants.ACTION_COLLAPSE_EXPAND)
                    ]        
                }
            }
           
            // edges of the compound node
            case "edge": e.createKEdge()
        }
    }
    
    private def void convertEdge(Element e, KEdge edge) {
        switch e.key {
            case "source": {
                val src = idMap.get(e.value.toInt)
                edge.source = src
            }
            
            case "target": {
                val tgt = idMap.get(e.value.toInt)
                edge.target = tgt
            }
            
            case "graphics" : e.elements.forEach[it.convertEdge(edge)]
            
            case "arrow": switch e.value.stripQuotes {
                case "head": (edge.KRendering as KPolyline).addHeadArrowDecorator
                case "tail": (edge.KRendering as KPolyline).addTailArrowDecorator
                case "last": (edge.KRendering as KPolyline).addHeadArrowDecorator
            } 

            case "width": edge.KRendering.lineWidth = Float.valueOf(e.value)

            // convert the bendpoints
            // note that the source and target anchor points are included in the list of bendpoints
            case "Line": e.elements.filter[it.key == "point"].forEach[it.convertEdgeBendpoints(edge)]
            
        }
    }
    
    private def convertEdgeBendpoints(Element bp, KEdge e) {
        
        val bps = e.layout.getProperty(LayoutOptions.BEND_POINTS)
        val x = bp.elements.findFirst[it.key == "x"]
        val y = bp.elements.findFirst[it.key == "y"]
        
        bps += new KVector(Double.valueOf(x.value), Double.valueOf(y.value))
        
    }
         
    private def void clipEdges(KNode parent) {
        
        parent.children.forEach[ n |
            
            n.outgoingEdges.forEach [ e |
                
                val bps = e.layout.getProperty(LayoutOptions.BEND_POINTS)
                
                val sShape = e.source.shapeLayout
                val tShape = e.target.shapeLayout
                
                if (bps.isEmpty) {
                    
                    
                    val src = e.source.layout.getProperty(LayoutOptions.POSITION).clone.add(sShape.width / 2f, sShape.height / 2f)
                    val tgt = e.target.layout.getProperty(LayoutOptions.POSITION).clone.add(tShape.width / 2f, tShape.height / 2f)
                    
                    val st = tgt.clone.sub(src).clip(e.source).add(src)
                    bps.add(0, st)
                    val ts = src.clone.sub(tgt).clip(e.target).add(tgt)
                    bps.add(ts)   
    
                } else if (bps.size >= 2) {

                    // clip the first and the last point of the bends
                    val src = bps.head
                    val snd = bps.tail.head
                    src.add(snd.clone.sub(src).clip(e.source))
                    
                    val tgt = (bps.clone as List<KVector>).reverse.head
                    val penult = (bps.clone as List<KVector>).reverse.tail.head
                    tgt.add(penult.clone.sub(tgt).clip(e.target))
                }
            ]
            
            n.clipEdges
        ]
    }
    
    private def clip(KVector v, KNode n) {
        KielerMath.clipVector(v, n.shapeLayout.width, n.shapeLayout.height)
    }
    
    var minX = Double.MAX_VALUE
    var minY = Double.MAX_VALUE
    
    /**
     * KGraph's node positions refer to the top-left corner
     * while GML specifies center positions. Here we move 
     * the node by half its width and height towards the 
     * top left position.
     */    
    private def void centerNodes(KNode parent) {
        
        // determine maximal extend of a node in x and y direction
        parent.children.forEach[ n |
            val pos = n.layout.getProperty(LayoutOptions.POSITION)
            if (pos != null) {
                minX = Math.min(minX, pos.x - n.width / 2)
                minY = Math.min(minY, pos.y - n.height / 2)
            }
            
            // start recursion            
            n.centerNodes    
        ]
        
        val borderSpacing = parent.layout.getProperty(LayoutOptions.BORDER_SPACING)
        
        // minX and minY are determined
        parent.children.forEach[ n |
            val pos = n.layout.getProperty(LayoutOptions.POSITION)
            
            if (pos != null) {
                // gml specifies center positions
                pos.x = -minX + pos.x - n.width / 2 + borderSpacing
                pos.y = -minY + pos.y - n.height / 2 + borderSpacing
            }
            
            // move all the bend points
            n.outgoingEdges.forEach [ e |
                val bps = e.layout.getProperty(LayoutOptions.BEND_POINTS)
                if (bps != null) {
                    bps.forEach [ bp |
                        bp.x = bp.x - minX
                        bp.y = bp.y - minY
                    ]
                }
            ]
            
            // shift labels to the center of the node
            n.labels.forEach[ l |
                l.shapeLayout.xpos = n.width / 2
                l.shapeLayout.ypos = n.height / 2
            ]
        ]
    }
    
    private def toInt(String s) {
        return Integer.valueOf(s)
    }

    private def toFloat(String s) {
        return Float.valueOf(s)
    }
    
    private def stripQuotes(String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
             s.substring(1, s.length - 1)
        } else {
            s
        }
    }
    
    private def getLayout(KGraphElement e) {
        e.getData(typeof(KLayoutData))
    }
    
    private def getShapeLayout(KGraphElement e) {
        e.getData(typeof(KShapeLayout))
    }
    
}