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

import com.google.common.collect.Maps
import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.math.KVector
import de.cau.cs.kieler.kiml.formats.gml.gml.Element
import de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.kiml.util.KimlUtil

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
    @Inject extension KColorExtensions
    
    val idMap = Maps.<Integer, KNode>newHashMap
    
    
    override transform(GmlModel model) {
        
        return createNode => [ root | 
            
            root.setLayoutOption(LayoutOptions.ALGORITHM, "de.cau.cs.kieler.fixed")
            
            model.elements.forEach[it.convert(root)]
            
            // shift positions as gml specifies node centers
            root.centerNodes
        ]
    }
    
    
    private def void convert(Element d, KNode parent) {
        switch d.key {
            case "graph" : d.elements.forEach[it.convert(parent)]
            
            case "id" : {
                idMap.put(d.value.toInt, parent); println("id " + d.value)
            }
            case "node" : createNode => [ node |
                    parent.children += node
                    d.elements.forEach[it.convert(node)]
                ]
                
            case "label" : {
                println(d.value) //parent.addText(d.value)
                val lab = KimlUtil.createInitializedLabel(parent)
                lab.text = d.value.stripQuotes
            }
            case "graphics" : d.elements.forEach[it.convert(parent)]
            
            case "type" : d.convertType(parent)
            case "fill" : {
                val ren = parent.getData(typeof(KRendering)) 
                if (ren != null)
                    ren.background = d.value.stripQuotes.color
            }
            case "line": {
                val ren = parent.getData(typeof(KRendering)) 
                if (ren != null)
                    ren.foreground = d.value.stripQuotes.color
            }
            case "x": {
                if (parent.layout.getProperty(LayoutOptions.POSITION) == null) {
                    parent.layout.setProperty(LayoutOptions.POSITION, new KVector())
                }
                val v = parent.layout.getProperty(LayoutOptions.POSITION)
                v.x = d.value.toFloat
            }
            case "y": {
                if (parent.layout.getProperty(LayoutOptions.POSITION) == null) {
                    parent.layout.setProperty(LayoutOptions.POSITION, new KVector())
                }
                val v = parent.layout.getProperty(LayoutOptions.POSITION)
                v.y = d.value.toFloat
            }
            case "w" : parent.width = d.value.toFloat
            case "h" : parent.height = d.value.toFloat
            
            case "edge": {
                d.createEdge => [
                    it.addPolyline
                ]
                d.elements.forEach[it.convert(parent)]
            }
            
            case "source": {
                val edge = d.eContainer.edge
                val src = idMap.get(d.value.toInt)
                edge.source = src
            }
            
            case "target": {
                val edge = d.eContainer.edge
                val tgt = idMap.get(d.value.toInt)
                edge.target = tgt
            }
        }
        
    } 
    
    private def void centerNodes(KNode parent) {
        parent.children.forEach[ n |
            val pos = n.layout.getProperty(LayoutOptions.POSITION)
            
            if(pos != null) {
                pos.x = pos.x - n.width / 2
                pos.y = pos.y - n.height / 2    
            }
            
            n.centerNodes
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
    
}