/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPoint
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout
import de.cau.cs.kieler.klighd.krendering.KAreaPlacementData
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData
import de.cau.cs.kieler.klighd.krendering.KGridPlacement
import de.cau.cs.kieler.klighd.krendering.KPlacement
import de.cau.cs.kieler.klighd.krendering.KPointPlacementData
import de.cau.cs.kieler.klighd.krendering.KPolygon
import de.cau.cs.kieler.klighd.krendering.KPolyline
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KRenderingRef
import de.cau.cs.kieler.klighd.microlayout.Bounds
import de.cau.cs.kieler.klighd.microlayout.GridPlacementUtil
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath
import de.cau.cs.kieler.klighd.piccolo.internal.util.PiccoloPlacementUtil
import de.cau.cs.kieler.klighd.util.KlighdProperties
import java.awt.geom.Point2D
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

/**
 * @author nir
 *
 */
public class BoundsUtil {
    public static def void calculateAbsoluteBounds(KGraphElement element) {
        // in case this is a KRenderingRef:
        // don't put the bounds directly in this rendering, but a map with bounds of the form:
        // <id of the rendering-tree in the library: bounds in this instance>
        // ...
        // <id of some child rendering of a rendering in the library: bounds in this instance>
        // ..        
        
        // maybe copy code from AbstractKGERenderingController handleGridPlacementRendering etc.
                
        // calculate the sizes of all renderings:
        for (data : element.data) {
            switch(data) {
                KRenderingLibrary: {
                    // the library needs to generate ids for all later KRenderingRefs to refer to, but no own bounds,
                    // since these are generic renderings
                    for (rendering : data.renderings) {
                        KRenderingIDGenerator.generateIdsRecursive(rendering)
                    }
                }
                KRenderingRef: {
                    // all references to KRenderings need to place a map with the ids of the renderings and their 
                    // sizes in this case in the properties of the reference.
                    var boundsMap = new HashMap<String, Bounds>
                    handleKRendering(element, data.rendering, boundsMap)
                    // add new Property to contain the boundsMap
                    data.properties.put(KlighdProperties.CALCULATED_BOUNDS_MAP, boundsMap) 
                    // remember the id of the rendering in the reference
                    data.id = data.rendering.id
                    
                }
                KRendering: {
                    // every rendering needs an ID, generate it here
                    KRenderingIDGenerator.generateIdsRecursive(data, element.data.indexOf(data))
                    handleKRendering(element, data, null)
                }
            }
        }
        
        // recursively call this method for every child KGraphElement of this
        // (all labels, child nodes, outgoing edges and ports)
        
        if (element instanceof KLabeledGraphElement) {
            for (label : element.labels) {
                calculateAbsoluteBounds(label)
            }
        }
        if (element instanceof KNode) {
            for (node : element.children) {
                calculateAbsoluteBounds(node)
            }
            for (edge : element.outgoingEdges) {
                calculateAbsoluteBounds(edge)
            }
            for (port : element.ports) {
                calculateAbsoluteBounds(port)
            }
        }
    }
    /**
     * calculate the size and position of any rendering and store it in the boundsMap or if the boundsMap is null as a
     * property in the rendering itself
     */
    private static def void handleKRendering(KGraphElement element, KRendering rendering, Map<String, Bounds> boundsMap) {
        var Bounds bounds
        if (element instanceof KShapeLayout) {
            bounds = new Bounds(element.width, element.height)
        } else {
            // in this case the element is a KEdge.
            bounds = edgeBounds(element as KEdge)
        }
        if (boundsMap === null) {
            rendering.setBounds(bounds)
        } else {
            boundsMap.put(rendering.id, bounds)
        }
        if (rendering instanceof KContainerRendering) {
            handleChildren(rendering.children, rendering.childPlacement, bounds, boundsMap, element)
        }
    }
    
    private static def void handleChildren(List<KRendering> renderings, KPlacement placement, Bounds parentBounds,
        Map<String, Bounds> boundsMap, KGraphElement element) {
        if (placement instanceof KGridPlacement) {
            handleGridPlacementRendering(renderings, placement, parentBounds, boundsMap, element)
        } else {
            for (rendering : renderings) {
                handleAreaAndPointAndDecoratorPlacementRendering(rendering, parentBounds, boundsMap, element)
            }
        }
    }
    
    private static def void handleGridPlacementRendering(List<KRendering> renderings, KGridPlacement gridPlacement, 
        Bounds parentBounds, Map<String, Bounds> boundsMap, KGraphElement parent) {
        
        val Bounds[] elementBounds = GridPlacementUtil.evaluateGridPlacement(gridPlacement, renderings, parentBounds);
        
        for (var i = 0; i < renderings.size; i++) {
            val rendering = renderings.get(i)
            val bounds = elementBounds.get(i)
            if (boundsMap === null) {
                rendering.setBounds(bounds)
            } else {
                boundsMap.put(rendering.id, bounds)
            }
            if (rendering instanceof KContainerRendering) {
                handleChildren(rendering.children, rendering.childPlacement, bounds, boundsMap, parent)
            }
        }
    }
    
    private static def void handleAreaAndPointAndDecoratorPlacementRendering(KRendering rendering, Bounds parentBounds,
        Map<String, Bounds> boundsMap, KGraphElement parent) {
        val placementData = rendering.placementData
        var Bounds bounds = parentBounds
        
        switch (placementData) {
            KAreaPlacementData: {
                bounds = PlacementUtil.evaluateAreaPlacement(placementData, parentBounds)
            }
            KPointPlacementData: {
                bounds = PlacementUtil.evaluatePointPlacement(rendering, placementData, parentBounds)
            }
            KDecoratorPlacementData: {
                var path = new KlighdPath(rendering)
                val parentRendering = rendering.eContainer
                if (parentRendering instanceof KPolygon) {
                    var points = newArrayOfSize(parentRendering.points.size)
                    for (var i = 0; i < parentRendering.points.size; i++) {
                        points.set(i, 
                            PlacementUtil.evaluateKPosition(parentRendering.points.get(i), parentBounds, true).toPoint2D)
                    }
                    path.setPathToPolygon(points)
                } else if (parentRendering instanceof KPolyline) {
                    var List<KPoint> pointList = new ArrayList()
                    if (parent instanceof KEdge) {
                        val edge = parent as KEdge
                        
                        pointList.add(edge.sourcePoint)
                        pointList.addAll(edge.bendPoints)
                        pointList.add(edge.targetPoint)
                    } else if (!parentRendering.points.empty) {
                        pointList.addAll(parentRendering.points.map[position | 
                            PlacementUtil.evaluateKPosition(position, parentBounds, true)])
                    } else {
                        throw new IllegalArgumentException("The parent element of the KPolyline is not a KEdge or " +
                            "the pointList of the KPolyline rendering is empty")
                    }
                    
                    var points = newArrayOfSize(pointList.size)
                    for (var i = 0; i < pointList.size; i++) {
                        val point = pointList.get(i)
                        points.set(i, new Point2D.Float(point.x, point.y))
                    }
                    path.setPathToPolyline(points)
                }
                
                val decoration = PiccoloPlacementUtil.evaluateDecoratorPlacement(placementData, path)
                bounds = new Bounds(decoration.bounds.x + decoration.origin.x as float, 
                    decoration.bounds.y + decoration.origin.y as float,
                    decoration.bounds.width,
                    decoration.bounds.height)
            }
        }
        if (boundsMap === null) {
            rendering.setBounds(bounds)
        } else {
            boundsMap.put(rendering.id, bounds)
        }
        if (rendering instanceof KContainerRendering) {
            handleChildren(rendering.children, rendering.childPlacement, bounds, boundsMap, parent)
        }
    }
    
    /**
     * calculates the bounding box of a KEdge
     */
    public static def Bounds edgeBounds(KEdge edge) {
        var float minX = Float.MAX_VALUE
        var float minY = Float.MAX_VALUE
        var float maxX = Float.MIN_VALUE
        var float maxY = Float.MIN_VALUE
        var List<KPoint> pointList = new ArrayList()
        pointList.add(edge.sourcePoint)
        pointList.addAll(edge.bendPoints)
        pointList.add(edge.targetPoint)
        for (point : pointList) {
            if (point.x < minX) {
                minX = point.x
            }
            if (point.x > maxX) {
                maxX = point.x
            }
            if (point.y < minY) {
                minY = point.y
            }
            if (point.y > maxY) {
                maxY = point.y
            }
        }
        return new Bounds(minX, minY, maxX - minX, maxY - minY)
    }
    
    /**
     * Convenience method to set the calculated bounds property of the given rendering
     */
    public static def setBounds(KRendering rendering, Bounds bounds) {
        rendering.properties.put(KlighdProperties.CALCULATED_BOUNDS, bounds)
    }
}