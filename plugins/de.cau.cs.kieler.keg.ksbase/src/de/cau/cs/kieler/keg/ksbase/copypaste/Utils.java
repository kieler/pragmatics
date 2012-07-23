/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.ksbase.copypaste;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.Node;

/**
 * Utility class for cut, copy and paste. The main purpose is to provide funcitionality to work with
 * the clipboard.
 * 
 * @author soh (synccharts version)
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public final class Utils {

    /**
     * 
     */
    private Utils() {
        // does nothing
    }

    /** Clipboard for copy and paste. */
    private static volatile EObject nodeClipBoard = null;
    /** Clipboard for copy and paste. */
    private static volatile Collection<Node> nodesClipBoard = null;
    /** Clipboard for copy and paste. */
    private static volatile EObject edgeClipBoard = null;
    /** Clipboard for copy and paste. */
    private static volatile Collection<Edge> edgesClipBoard = null;

    /**
     * Remove a node from the parent region.
     * 
     * @param node
     *            the node to remove
     */
    private static void removeNodeFromParent(final Node node) {
        node.getParent().getChildren().remove(node);
        List<KEdge> removeEdges = new LinkedList<KEdge>();
        for (KEdge edge : node.getOutgoingEdges()) {
            KNode target = edge.getTarget();
            target.getIncomingEdges().remove(edge);
            removeEdges.add(edge);
        }
        for (KEdge edge : node.getIncomingEdges()) {
            KNode source = edge.getSource();
            source.getOutgoingEdges().remove(edge);
            removeEdges.add(edge);
        }
        node.getOutgoingEdges().clear();
        node.getIncomingEdges().clear();
    }

    /**
     * Copy an object to clipboard and remove it from the model.
     * 
     * @param object
     *            the object to cut
     */
    public static void cutObject(final Object object) {
        objectToClipboard(object);
        if (object instanceof EObject) {
            if (object instanceof Node) {
                Node s = (Node) object;
                removeNodeFromParent(s);
            } else if (object instanceof Edge) {
                Edge t = (Edge) object;
                t.getSource().getOutgoingEdges().remove(t);
            }
        } else if (object instanceof List<?>) {
            List<?> list = (List<?>) object;
            for (Object o : list) {
                if (o instanceof Node) {
                    removeNodeFromParent((Node) o);
                } else if (o instanceof Edge) {
                    Edge t = (Edge) o;
                    t.getSource().getOutgoingEdges().remove(t);
                    t.getTarget().getIncomingEdges().remove(t);
                }
            }
        }
    }

    /**
     * Clone all edges on a node.
     * 
     * @param source
     *            the source
     * @param target
     *            the clone
     */
    private static void cloneEdges(final Node source, final Node target) {
        target.getOutgoingEdges().clear();
        target.getIncomingEdges().clear();
        for (KEdge t : source.getOutgoingEdges()) {
            if (t.getTarget() == source) {
                Edge clone = (Edge) EcoreUtil.copy(t);
                target.getOutgoingEdges().add(clone);
                clone.setTarget(target);
            }
        }
    }

    /**
     * Copy an object to clipboard.
     * 
     * @param object
     *            the object
     */
    public static void objectToClipboard(final Object object) {
        resetClipboard();
        if (object instanceof EObject) {
            EObject o = EcoreUtil.copy((EObject) object);
            if (o instanceof Node) {
                // cloneEdges((Node) object, (Node) o);
                nodeClipBoard = o;
            } else if (o instanceof Edge) {
                edgeClipBoard = o;
            }
        } else if (object instanceof List<?>) {
            List<?> list = (List<?>) object;

            if (list.get(0) instanceof Node) {
                nodesToClipboard(list);
            } else if (list.get(0) instanceof Edge) {
                edgesToClipboard(list);
            }
        }
    }

    /**
     * Copy pure edge list to clipboard. If node is encountered copy node list instead.
     * 
     * @param list
     *            the list that should be added to clipboard
     */
    private static void edgesToClipboard(final List<?> list) {
        Collection<Edge> coll = new LinkedList<Edge>();
        boolean foundNode = false;
        for (Object o : list) {
            if (o instanceof Edge) {
                coll.add((Edge) o);
            } else if (o instanceof Node) {
                foundNode = true;
                break;
            }
        }
        if (foundNode) {
            nodesToClipboard(list);
        } else {
            edgesClipBoard = EcoreUtil.copyAll(coll);
        }
    }

    /**
     * Copy node list to clipboard and clear of all subnodes.
     * 
     * @param list
     *            the list that should be added to clipboard
     */
    private static void nodesToClipboard(final List<?> list) {
        Collection<Node> coll = new LinkedList<Node>();
        for (Object o : list) {
            if (o instanceof Node) {
                Node node = (Node) o;
                KNode parent = node.getParent();
                if (parent == null || !list.contains(parent)) {
                    coll.add(node);
                }
            }
        }
        nodesClipBoard = EcoreUtil.copyAll(coll);
    }

    /**
     * Clears the clipboard.
     */
    private static void resetClipboard() {
        nodeClipBoard = null;
        nodesClipBoard = null;
        edgeClipBoard = null;
        edgesClipBoard = null;
    }

    /**
     * Get a node from the clipboard.
     * 
     * @return the node
     */
    public static Node getNodeFromClipboard() {
        if (nodeClipBoard != null) {
            Node newNode = (Node) EcoreUtil.copy(nodeClipBoard);
            cloneEdges((Node) nodeClipBoard, newNode);
            return newNode;
        }
        return null;
    }

    /**
     * Get a list of nodes from the clipboard.
     * 
     * @return the nodes
     */
    public static List<Node> getNodesFromClipboard() {
        if (nodesClipBoard != null && !nodesClipBoard.isEmpty()) {
            Collection<Node> nodes = EcoreUtil.copyAll(nodesClipBoard);
            List<Node> dummy = new LinkedList<Node>();
            for (Node node : nodes) {
                dummy.add(node);
                // remove edges that leave the current selection
                Iterator<KEdge> iter = node.getOutgoingEdges().iterator();
                while (iter.hasNext()) {
                    Edge trans = (Edge) iter.next();
                    if (!nodes.contains(trans.getTarget())) {
                        iter.remove();
                    }
                }
            }
            return dummy;
        }
        return null;
    }

    /**
     * Get an edge from the clipboard.
     * 
     * @return the edge
     */
    public static Edge getEdgeFromClipboard() {
        if (edgeClipBoard != null) {
            Edge newTrans = (Edge) EcoreUtil.copy(edgeClipBoard);
            return newTrans;
        }
        return null;
    }

    /**
     * Get an edge from the clipboard.
     * 
     * @return the edge
     */
    public static List<Edge> getEdgesFromClipboard() {
        if (edgesClipBoard != null && !edgesClipBoard.isEmpty()) {
            Collection<Edge> edges = EcoreUtil.copyAll(edgesClipBoard);

            List<Edge> dummy = new LinkedList<Edge>();
            for (Edge edge : edges) {
                dummy.add(edge);
            }
            return dummy;
        }
        return null;
    }

    /**
     * Copy the object.
     * 
     * @param object
     *            the object
     * @return the copy
     */
    public static EObject copy(final Object object) {
        return EcoreUtil.copy((EObject) object);
    }
}
