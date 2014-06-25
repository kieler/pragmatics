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
package de.cau.cs.kieler.klay.codaflow.avoid

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import de.cau.cs.kieler.adaptagrams.cgraph.CEdge
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph
import de.cau.cs.kieler.adaptagrams.cgraph.CNode
import de.cau.cs.kieler.adaptagrams.cgraph.CPort
import de.cau.cs.kieler.adaptagrams.properties.AvoidProperties
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.math.KVector
import de.cau.cs.kieler.core.math.KVectorChain
import de.cau.cs.kieler.core.util.Pair
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.Direction
import de.cau.cs.kieler.kiml.options.EdgeRouting
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.PortSide
import de.cau.cs.kieler.klay.codaflow.graphimport.IGraphImporter
import de.cau.cs.kieler.klay.codaflow.util.ColaUtil
import java.awt.geom.Line2D
import java.awt.geom.Point2D
import java.util.Iterator
import java.util.List
import java.util.Map
import org.adaptagrams.AvoidCheckpoints
import org.adaptagrams.AvoidRectangle
import org.adaptagrams.Checkpoint
import org.adaptagrams.Cluster
import org.adaptagrams.ClusterRef
import org.adaptagrams.ConnDirFlag
import org.adaptagrams.ConnEnd
import org.adaptagrams.ConnRef
import org.adaptagrams.ConnType
import org.adaptagrams.Point
import org.adaptagrams.Polygon
import org.adaptagrams.Router
import org.adaptagrams.RouterFlag
import org.adaptagrams.ShapeConnectionPin
import org.adaptagrams.ShapeRef
import org.adaptagrams.adaptagrams

import static de.cau.cs.kieler.kiml.options.PortSide.*
import de.cau.cs.kieler.klay.codaflow.properties.InternalCodaflowProperties

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
    
    // create a router instance
    val edgeRouting = if (graph.getProperty(LayoutOptions.EDGE_ROUTING) == EdgeRouting.ORTHOGONAL) 
                        RouterFlag.OrthogonalRouting else RouterFlag.PolyLineRouting
    val router = new Router(edgeRouting)

    // transfer the libavoid properties specified in the graph to the router    
    AvoidProperties.transferOptions(router, graph)
    
    // transform all the nodes
    for (node : graph.children) {
      node.transform(router)
      // and ports
      for (port : node.ports) {
        port.transform(router)
      } 
    } 
    
    // edges
    for (node : graph.children) {
      for (edge : node.outgoingEdges) {
        edge.transform(router)
      }
    }
    
    // make libavoid aware of the clusters
    graph.rootCluster.mapClusters(router)
    router.setClusteredRouting(true)
    
    
    router
  } 
  
  def private void mapClusters(Cluster c, Router r) {

        val bounds = c.bounds
        val colaRect = new AvoidRectangle(new Point(bounds.minX, bounds.minY), new Point(bounds.maxX, bounds.maxY))

        // create a cluster ref
        new ClusterRef(r, colaRect)

        val children = c.clusters
        for (i : 0 ..< children.size.intValue) {
            children.get(i).mapClusters(r)
        }
    }
  
  
  def dispatch transform(CNode node, Router router) {

    // the raw size already concludes margins
    val rect = new AvoidRectangle(node.rectPosRaw.toPoint, 
                node.rectPosRaw.clone.add(node.rectSizeRaw).toPoint)
//	  val rect = new AvoidRectangle(node.rectPos.toPoint, 
//                node.rectPos.clone.add(node.rectSize).toPoint)
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
    val relativePortPos = port.rectPosRaw.clone.sub(port.owner.rectPosRaw)
    relativePortPos.add(port.rectSizeRaw.scale(0.5))
//	val relativePortPos = port.rectPos.clone.sub(port.owner.rectPos)
//    relativePortPos.add(port.rectSize.scale(0.5))
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
      val ports = edge.getProperty(InternalCodaflowProperties.EDGE_CHECKPOINTS)
      val checkpoints = new AvoidCheckpoints()
      for (pair : ports.take(ports.size - 1)) {
        val port = pair.second
        port.x = port.x.roundDouble
        port.y = port.y.roundDouble
        val cp = new Checkpoint(new Point(port.x, port.y)).register(pair.first)

        // set arrival and departure directions for the ports        
        val portLayout = pair.first.getData(typeof(KShapeLayout))
        val portSide = portLayout.getProperty(LayoutOptions.PORT_SIDE)
        if (portSide == PortSide.EAST || portSide == PortSide.WEST) {
          // allow the edge to be routed left-right or right-left
          cp.setArrivalDirections(AvoidProperties.CONN_DIR_LEFTRIGHT)
          cp.setDepartureDirections(AvoidProperties.CONN_DIR_LEFTRIGHT)
        } else {
          // bottom-up or up-bottom
          cp.setArrivalDirections(AvoidProperties.CONN_DIR_UPDOWN)
          cp.setDepartureDirections(AvoidProperties.CONN_DIR_UPDOWN)
        }        
        checkpoints.add(cp)
      }
      connRef.setRoutingCheckpoints(checkpoints)
    }
  }
  
  private def roundDouble(double d) {
  	val toInt =  (d * 100.0).intValue
  	return toInt / 100.0
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
      
      val edgeChain = edge.getProperty(InternalCodaflowProperties.EDGE_CHAIN)
      
      if (edgeChain.empty) {
        // this is a regular edge, just write the bendpoints back
        
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
        
//        println("BPS: " + edge + " " + edge.bendpoints)
        
      } else {
        // a hierarchy crossing edge
        
        // we have to split the route into chunks
        val subRoutes = edge.determineSubRoutes(route)
        //subRoutes.forEach[e,i | println("SubRoute " + i + " " + cr.edge + " " + e)]
        edge.setProperty(InternalCodaflowProperties.EDGE_SUB_ROUTES, subRoutes)
      }
      
      edge.setProperty(InternalCodaflowProperties.LIBAVOID_WORKED, true)
    }
  }
  
  /**
   * @return for each sub route from a port to another (possibly hierarchical port one
   * {@link KVectorChain} that represents the start point, bendpoints, and the end point. 
   */
  private def List<KVectorChain> determineSubRoutes(CEdge edge, Polygon route) {
    
    // original checkpoints specified for the edge
    val chkPoints = edge.getProperty(InternalCodaflowProperties.EDGE_CHECKPOINTS)
    // println("Checkpoints: " + chkPoints)
    val ports = chkPoints.take(chkPoints.size - 1).map[it.second].iterator
    
    // the sub routes we are about to determine
    val List<KVectorChain> subRoutes = Lists.newLinkedList

    // start values of some variables
    val pts = route.getPs()
//    print("Pts: ")
//    for(i : 0..<pts.size.intValue) {
//    	print(pts.get(i) + ", ")
//    }
//    println
    
    var currentPort = ports.next()
    var KVectorChain currentSubRoute = new KVectorChain

	var i = 0
	var newLine = true;
	while (i < pts.size.intValue - 1) {

      val fst = pts.get(i)
      val snd = pts.get(i + 1)

      // add start point to the current sub route
      if (newLine) {
      	currentSubRoute.add(fst.toKVector)
      }

      // create a line for the current segment
      val segment = new Line2D.Double(fst.x, fst.y, snd.x, snd.y)
      val point = if (currentPort != null) new Point2D.Double(currentPort.x, currentPort.y) else null
	

	  val pntLineDist = if (point != null) ColaUtil.pointToSegmentDistance(currentPort, fst.toKVector, snd.toKVector) else Double.MAX_VALUE

	  // println("\t check " + segment.p1 + " " + segment.p2 + " " + point + " " + pntLineDist)
     
      // check if the checkpoint is represented by the start or end 
      // point of the segment or if it lies on the segment
      newLine = true
      if (segment.p1 == point) {
        
        throw new AssertionError("Really shouldnt happen")

      } else if (point != null && snd.toKVector.distance(currentPort) < 0.01d) { // segment.p2 == point
		// CASE 1: checkpoint is the second point of the line

        // assemble a segment
        currentSubRoute.add(currentPort.clone())
        subRoutes.add(currentSubRoute)

        // start new sub route
        currentSubRoute = new KVectorChain
        currentPort = ports.saveNext()
        
        i = i + 1

      } else if (point != null && pntLineDist < 0.01d) {
		// CASE 2: checkpoint is somewhere along the line

        // FIXME sometimes cp not on line!
        
        // assemble a segment
        currentSubRoute.add(currentPort.clone())
        subRoutes.add(currentSubRoute)

        // start new sub route
        currentSubRoute = new KVectorChain
        currentSubRoute.add(currentPort.clone())
        currentPort = ports.saveNext()
        
        // do not increase i, as we are still on the same segment
        newLine = false

      } else {
      	// CASE 3: 
      	
      	i = i + 1
      }
      
    }
 
    // finish last subroute
    currentSubRoute.add(pts.get(pts.size().intValue - 1).toKVector)
    subRoutes.add(currentSubRoute)

    return subRoutes;
  }
  
  /**
   * @return either the next element or null.
   */
  private def <T> T saveNext(Iterator<T> ite) {
    if(ite.hasNext()) {
      return ite.next()
    } else {
      return null
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
          offset.add(margins.left, 0)
          offset.add(-port.rectSize.x, 0)
        }
        case EAST: {
          offset.add(-margins.right, 0)
          offset.add(port.rectSize.x, 0)
        }
        case NORTH: {
          offset.add(0, margins.top)
          offset.add(0, -port.rectSize.y)
        }
        case SOUTH: {
          offset.add(0, -margins.bottom)
          offset.add(0, port.rectSize.y)  
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
  
  
  val Map<KPort, Checkpoint> portCheckpointMap = Maps.newHashMap
  
  def Checkpoint register(Checkpoint cp, KPort port) {
    portCheckpointMap.put(port, cp)
    return cp
  }
  
  def checkpoint(CPort p) {
    return portCheckpointMap.get(p)
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