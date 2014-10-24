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
import de.cau.cs.kieler.kiml.formats.gml.gml.Element
import de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.util.FixedLayoutProvider
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.core.math.KVectorChain
import de.cau.cs.kieler.core.math.KielerMath

/**
 * TODO create rendering asap, with the node, to set the colors in
 * case the type is specified later 
 * 
 * @author uru
 */
class GMLDiagramSynthesis extends AbstractDiagramSynthesis<GmlModel> {
    
    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KColorExtensions
    @Inject extension KPolylineExtensions
    
    val idMap = Maps.<Integer, KNode>newHashMap
    var hasPositions = false
    
    public static val SynthesisOption SHOW_LABELS = SynthesisOption::createCheckOption(
        "Labels", true)
        
    override getDisplayedSynthesisOptions() {
         return ImmutableList::of(
             SHOW_LABELS
         )
    } 
    
    override transform(GmlModel model) {
        
        return createNode => [ root | 
            
            // first only convert all the nodes
            model.elements.forEach[it.convert(root)]
            
            // shift positions as gml specifies node centers
            root.centerNodes
            
            // draw edges to node centers and clip
            if (hasPositions)
                root.clipEdges
            
            if (hasPositions) {
                root.setLayoutOption(LayoutOptions.ALGORITHM, FixedLayoutProvider.ID)
            }
        ]
    }
     
     
    private def clipEdges(KNode parent) {
        
        parent.children.forEach[ n |
            
            n.outgoingEdges.forEach [ e |
                
                val bps = e.layout.getProperty(LayoutOptions.BEND_POINTS)
                
                if(bps.isEmpty) {
                    
                    val sShape = e.source.shapeLayout
                    val src = e.source.layout.getProperty(LayoutOptions.POSITION).clone.add(sShape.width / 2f, sShape.height / 2f)
                    val tShape = e.target.shapeLayout
                    val tgt = e.target.layout.getProperty(LayoutOptions.POSITION).clone.add(tShape.width / 2f, tShape.height / 2f)
                    
                    val st = tgt.clone.sub(src).clip(e.source).add(src)
                    bps.add(0, st)
                    val ts = src.clone.sub(tgt).clip(e.target).add(tgt)
                    bps.add(ts)   
                }
            ]
            
            n.clipEdges
        ]
    }
    
    private def clip(KVector v, KNode n) {
        KielerMath.clipVector(v, n.shapeLayout.width, n.shapeLayout.height)
    }
    
    private def void convert(Element d, KNode parent) {
        switch d.key {
            case "graph" : {
                d.elements.filter[it.key == "node"].forEach[ e |
                    e.createNode => [ node |
                        parent.children += node
                        
                        node.layout.setProperty(LayoutOptions.POSITION, new KVector())
                        node.addRectangle => [it.lineWidth = 1 ]
                        // style and position of created noode
                        e.elements.forEach[it.convertNode(node)]
                    ]
                ]
                
                d.elements.filter[it.key == "edge"].forEach[ e |
                    e.createEdge => [ edge |
                        edge.addPolyline
                        edge.layout.setProperty(LayoutOptions.BEND_POINTS, new KVectorChain())
                        e.elements.forEach[it.convertEdge(edge)]
                    ]
                ]    
                
            }
        
//            case "type" : d.convertType(parent)
        }
        
    } 
    
    private def void convertNode(Element e, KNode node) {
        switch e.key {
            
            case "id" : idMap.put(e.value.toInt, node)
            
            case "label": if (SHOW_LABELS.booleanValue) 
                            KimlUtil.createInitializedLabel(node).text = e.value.stripQuotes
            //case "label" : node.KContainerRendering.addText(e.value.stripQuotes)
            
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
            
            case "fill": node.KRendering.background = e.value.stripQuotes.color
            
            // compound nodes
            case "node": e.convert(node)
        }
    }
    
    private def void convertEdge(Element e, KEdge edge) {
        switch e.key {
            case "source": {
                val src = idMap.get(e.value.toInt)
                edge.source = src
//                val bps = edge.layout.getProperty(LayoutOptions.BEND_POINTS) 
//                bps += new KVector(src.shapeLayout.width / 2f, src.shapeLayout.height / 2f)
            }
            
            case "target": {
                val tgt = idMap.get(e.value.toInt)
                edge.target = tgt
  //              val bps = edge.layout.getProperty(LayoutOptions.BEND_POINTS) 
  //              bps += new KVector(tgt.shapeLayout.width / 2f, tgt.shapeLayout.height / 2f)
            }
            
            
            case "graphics" : e.elements.forEach[it.convertEdge(edge)]
//            case "type": 
            
//            case "fill":
            
            case "arrow": switch e.value.stripQuotes {
                case "head": (edge.KRendering as KPolyline).addHeadArrowDecorator
                case "tail": (edge.KRendering as KPolyline).addTailArrowDecorator
                case "last": (edge.KRendering as KPolyline).addHeadArrowDecorator
            } 

//              case "width"

     
            case "Line": e.elements.filter[it.key == "point"].forEach[it.convertEdgeBendpoints(edge)]
            
        }
    }
    
    private def convertEdgeBendpoints(Element bp, KEdge e) {
        
        val bps = e.layout.getProperty(LayoutOptions.BEND_POINTS)
        val x = bp.elements.findFirst[it.key == "x"]
        val y = bp.elements.findFirst[it.key == "y"]
        
        val offset = new KVector(e.source.shapeLayout.width / 2f, e.source.shapeLayout.height / 2f)
        bps += new KVector(Double.valueOf(x.value) + offset.x, Double.valueOf(y.value) + offset.y)
        
    }
    
    var minX = Double.MAX_VALUE
    var minY = Double.MAX_VALUE
    
    private def void centerNodes(KNode parent) {
        
        parent.children.forEach[ n |
            val pos = n.layout.getProperty(LayoutOptions.POSITION)
            if (pos != null) {
                minX = Math.min(minX, pos.x - n.width / 2)
                minY = Math.min(minY, pos.y - n.height / 2)
            }
            
            // start recursion            
            n.centerNodes    
        ]
        
        
        println(minX + " " + minY)
        
        val borderSpacing = parent.layout.getProperty(LayoutOptions.BORDER_SPACING)
        // minX and minY are determined now
        parent.children.forEach[ n |
            val pos = n.layout.getProperty(LayoutOptions.POSITION)
            
            if (pos != null) {
                // gml specifies center positions
                pos.x = -minX + pos.x - n.width / 2 + borderSpacing
                pos.y = -minY + pos.y - n.height / 2 + borderSpacing
            }
            
            // shift labels to the center of the node
            n.labels.forEach[ l |
                l.shapeLayout.xpos = n.width / 2
                l.shapeLayout.ypos = n.height / 2
            ]
        ]
    }
    
    
    private def convertType(Element d, KNode parent) {
        println(d.value + "--" + d.value.stripQuotes)
        switch d.value.stripQuotes {
            case "rectangle" : parent.addRectangle
            case "oval": parent.addEllipse
        }
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
    
    private def getEdgeLayout(KEdge e) {
        e.getData(typeof(KEdgeLayout))
    }
}