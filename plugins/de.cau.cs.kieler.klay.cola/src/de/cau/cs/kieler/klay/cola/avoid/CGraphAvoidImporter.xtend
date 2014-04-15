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
package de.cau.cs.kieler.klay.cola.avoid

import com.google.common.collect.Maps
import de.cau.cs.kieler.core.math.KVector
import de.cau.cs.kieler.core.util.Pair
import de.cau.cs.kieler.kiml.options.Direction
import de.cau.cs.kieler.kiml.options.EdgeRouting
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.klay.cola.graph.CEdge
import de.cau.cs.kieler.klay.cola.graph.CGraph
import de.cau.cs.kieler.klay.cola.graph.CNode
import de.cau.cs.kieler.klay.cola.graph.CPort
import de.cau.cs.kieler.klay.cola.graphimport.IGraphImporter
import java.util.Map
import org.adaptagrams.AvoidRectangle
import org.adaptagrams.ConnEnd
import org.adaptagrams.ConnRef
import org.adaptagrams.ConnType
import org.adaptagrams.Point
import org.adaptagrams.Router
import org.adaptagrams.ShapeRef
import org.adaptagrams.ShapeConnectionPin
import org.adaptagrams.adaptagrams
import org.adaptagrams.ConnDirFlag
import org.adaptagrams.RouterFlag
import org.adaptagrams.RoutingParameter
import de.cau.cs.kieler.klay.cola.properties.InternalColaProperties
import org.adaptagrams.Checkpoint
import org.adaptagrams.AvoidCheckpoints

/**
 * TODO document
 * 
 * Libavoid requires unique IDs for all elements (ie it has no separate namespace for nodes and edges).
 * Ports and Edges are already in the same namespace, thats fine, however edges have to be offset by the maximal node id. 
 * 
 * @author uru
 */
class CGraphAvoidImporter implements IGraphImporter<CGraph, Router> {

  override importGraph(CGraph graph) {
    
    val edgeRouting = if (graph.getProperty(LayoutOptions.EDGE_ROUTING) == EdgeRouting.ORTHOGONAL) 
                        RouterFlag.OrthogonalRouting else RouterFlag.PolyLineRouting
    val router = new Router(edgeRouting)
    
    router.setRoutingParameter(RoutingParameter.shapeBufferDistance, 5)
    router.setRoutingParameter(RoutingParameter.crossingPenalty, 1000)
    router.setRoutingParameter(RoutingParameter.clusterCrossingPenalty, 10000 )
    
    // transform all the nodes
    for (node : graph.children) {
      node.transform(router)
      // and ports
      for (port : node.ports) {
        port.transform(router)
      } 
    } 
    
    for (node : graph.children) {
      for (edge : node.outgoingEdges) {
        edge.transform(router)
      }
    }
    
    router
  } 
  
  
  def dispatch transform(CNode node, Router router) {
    
    
    // the raw size already concludes margins
    val rect = new AvoidRectangle(node.rectPosRaw.toPoint, 
                node.rectPosRaw.sumCreate(node.rectSizeRaw).toPoint)
    //println("rect " + node.rectPosRaw + node.rectSizeRaw)
    createAndRegisterShapeRef(rect, node, router)
    
    
  }
  
  def dispatch transform(CPort port, Router router) {
    
    // the shaperef of the port's node
    val nodeSr = port.owner?.shapeRef
    
    if (nodeSr == null) {
      // this could be an external port, just return  
      return null
    }

    // get the bounding box of the node
    val box = nodeSr.polygon().offsetBoundingBox(0);

    // calculate width and height
    val width = box.getMax().getX() - box.getMin().getX();
    val height = box.getMax().getY() - box.getMin().getY();

    // determine the pin's positions relative on the respective side
    val relativePortPos = port.rectPosRaw.differenceCreate(port.owner.rectPosRaw)
    relativePortPos.add(port.rectSizeRaw.scale(0.5))
    val relX = relativePortPos.x / width
    val relY = relativePortPos.y / height
    
    val portId = port.cIndex + PORT_ID_START
    var ShapeConnectionPin pin = null;
    switch (port.side) {
      case NORTH:
        pin = new ShapeConnectionPin(nodeSr, portId, relX, adaptagrams.getATTACH_POS_TOP(), 0d,
          ConnDirFlag.ConnDirUp)
      case EAST:
        pin = new ShapeConnectionPin(nodeSr, portId, adaptagrams.getATTACH_POS_RIGHT(), relY, 0d,
          ConnDirFlag.ConnDirRight)
      case SOUTH:
        pin = new ShapeConnectionPin(nodeSr, portId, relX, adaptagrams.getATTACH_POS_BOTTOM(), 0d,
          ConnDirFlag.ConnDirDown)
      case WEST:
        pin = new ShapeConnectionPin(nodeSr, portId, adaptagrams.getATTACH_POS_LEFT(), relY, 0d,
          ConnDirFlag.ConnDirLeft)
      default:
        throw new IllegalStateException("Every port must be assigned to a side.")
    }

    pin.setExclusive(false);
  }
  
  def dispatch transform(CEdge edge, Router router) {

    val srcRef = edge.source.shapeRef
    val tgtRef = edge.target.shapeRef

    val pins = edge.pins

    val srcConnEnd = new ConnEnd(srcRef, pins.first)
    val tgtConnEnd = new ConnEnd(tgtRef, pins.second)

    val edgeRouting = edge.graph.getProperty(LayoutOptions.EDGE_ROUTING)

    // important to offset the index by the whole range of node indices here!
    val id = edge.cIndex + edge.graph.lastNodeIndex
    val connRef = new ConnRef(router, srcConnEnd, tgtConnEnd, id)
    connRef.register(id, edge)
    connRef.setRoutingType(
      if (edgeRouting == EdgeRouting.ORTHOGONAL) ConnType.ConnType_Orthogonal else ConnType.ConnType_PolyLine)



    // for hierarchical edges we have to add checkpoints
    if (edge.crossHierarchy) {
      val ports = edge.getProperty(InternalColaProperties.EDGE_CHECKPOINTS) 
      val checkpoints = new AvoidCheckpoints
      for (port : ports.take(Math.max(0, ports.size - 1))) {
        val cp = new Checkpoint(new Point(port.x, port.y))
        checkpoints.add(cp)
      }
      connRef.setRoutingCheckpoints(checkpoints)
    }
  }
  

  /*----------------------------------------------------------
   *    Applying Layout
   */  
  override applyLayout(Router constrainedGraph) {
    
    for (cr : idConnRefMap.values) {
      
      // Be sure to use #displayRoute() here and not route(), as the
      // second method only contains the "raw" route, eg, without any
      // nudging done.
      // Remark: don't be confused by the polygon type here
      // in c++ polyline is just a typedef of polygon
      val route = cr.displayRoute
      
      val edge = cr.edge
      edge.bendpoints.clear
      
      val pts = route.getPs()
      for (i : 0 ..< pts.size.intValue) {
        val offset = new KVector
        
        if (i == 0) {
          offset.add(edge.sourcePort.portMarginAndSizeOffset)
        } else if (i == pts.size - 1) {
          offset.add(edge.targetPort.portMarginAndSizeOffset)
        }
        
        edge.bendpoints.add(pts.get(i).toKVector.add(offset))
      }
      
      
      // apply checkpoints back
      //route.
      }
    
  }
  
  /**
   * We have to adjust the port position according to two aspects.
   *  - Libavoid pins are dimension-less, hence consider the port's size
   *  - Ports are placed on the bound of rectangles that include
   *    margins, hence consider the parent node's margins  
   * 
   * This mehod is null-safe, ie if port is null, a 0,0 offset is returned.
   */
  def KVector portMarginAndSizeOffset(CPort port) {
     val offset = new KVector
     if (port != null) {
       val margins = port.owner.margins
       switch (port.side) {
        case WEST: {
          offset.translate(margins.left, 0)
          offset.translate(-port.rectSize.x, 0)
        }
        case EAST: {
          offset.translate(-margins.right, 0)
          offset.translate(port.rectSize.x, 0)
        }
        case NORTH: {
          offset.translate(0, margins.top)
          offset.translate(0, -port.rectSize.y)
        }
        case SOUTH: {
          offset.translate(0, -margins.bottom)
          offset.translate(0, port.rectSize.y)  
        }
      }
    }
    
    return offset
  }
  
  
  
  
  
  
  /*----------------------------------------------------------
   *    Convenience
   */
  
  
  
  
      /** First id used to reference ports. */
    val PORT_ID_START = 5;
    /** First id used to reference nodes. */
    val NODE_ID_START = 5;
  
      /*
     * Pin Types
     * 
     * Per definition the ids of passed ports start at 5. Thus, [1..4] are free for arbitrary
     * definition. Remark: the id has to be > 0! Otherwise a c++ assertion fails.
     */
    /** Indicates pins that can be used by an arbitrary endpoint of an edge. */
    val PIN_ARBITRARY = 1;
    /** Indicates pins reserved for incoming edges. */
    val PIN_INCOMING = 2;
    /** Indicates pins reserved for outgoing edges. */
    val PIN_OUTGOING = 3;
  
  val Map<Integer, ShapeRef> idShapeRefMap = Maps.newHashMap
  val Map<Integer, ConnRef> idConnRefMap = Maps.newHashMap
  val Map<ConnRef, CEdge> connRefEdgeMap = Maps.newHashMap
  
  
  def ShapeRef createAndRegisterShapeRef(AvoidRectangle rect, CNode node, Router router) {
    val sr = new ShapeRef(router, rect, node.cIndex)
    idShapeRefMap.put(node.cIndex + NODE_ID_START, sr)
    return sr
  }
  
  def ShapeRef shapeRef(CNode node) {
    return idShapeRefMap.get(node.cIndex + NODE_ID_START)
  }
  
  def register(ConnRef connRef, int id, CEdge edge) {
    idConnRefMap.put(id, connRef)
    connRefEdgeMap.put(connRef,edge)
  }
  
  def CEdge edge(ConnRef connRef) {
    return connRefEdgeMap.get(connRef)
  }
  
  
  /**
   * Return the source pin (aka port) for this edge. 
   * In case the edge does not connect to a port this is calculated based 
   * on the direction.
   */
  def Pair<Integer, Integer> pins(CEdge edge) {

    val direction = edge.graph.getProperty(LayoutOptions.DIRECTION)

    // determine the pin locations for this edge
    var srcPin = PIN_ARBITRARY;
    var tgtPin = PIN_ARBITRARY;

    // determine the type of the edge, ie, if it involves ports
    if (edge.srcPort != null && edge.tgtPort != null) {
      srcPin = edge.srcPort.cIndex + PORT_ID_START;
      tgtPin = edge.tgtPort.cIndex + PORT_ID_START;
    } else if (edge.srcPort != null) {
      srcPin = edge.srcPort.cIndex;
      if (direction != Direction.UNDEFINED) {
        tgtPin = PIN_INCOMING;
      }
    } else if (edge.tgtPort != null) {
      if (direction != Direction.UNDEFINED) {
        srcPin = PIN_OUTGOING;
      }
      tgtPin = edge.tgtPort.cIndex + PORT_ID_START;
    } else {
      if (direction != Direction.UNDEFINED) {
        tgtPin = PIN_INCOMING;
        srcPin = PIN_OUTGOING;
      }
    }

    return Pair.of(srcPin, tgtPin)
  }
  
  
  def Point toPoint(KVector v) {
    return new Point(v.x, v.y)
  }
  
  def toKVector(Point p) {
    return new KVector(p.x, p.y)
  }
} 