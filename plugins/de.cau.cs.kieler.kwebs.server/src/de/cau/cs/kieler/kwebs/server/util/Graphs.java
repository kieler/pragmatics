/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.impl.KEdgeImpl;
import de.cau.cs.kieler.core.kgraph.impl.KLabelImpl;
import de.cau.cs.kieler.core.kgraph.impl.KNodeImpl;
import de.cau.cs.kieler.core.kgraph.impl.KPortImpl;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Utility class for duplicating layout information between structurally identical graphs. Every
 * graph element gets annotated with an unique identifier. This is used to duplicate the calculated
 * layout information from a graph which was layout done on back to the original graph.
 * 
 * @kieler.design 2011-08-02 reviewed by ckru, mri, msp
 * @author swe
 */
public final class Graphs {

    /**
     * Duplicates layout information from source model to target model.
     * Note that the source graph may not be used anymore afterwards, since layout data instances
     * are moved to the target graph.
     * 
     * @param sourceGraph
     *            the model which contains the layout information
     * @param targetGraph
     *            the model which gets the layout information
     */
    public static void duplicateGraphLayoutByUniqueID(final KNode sourceGraph,
            final KNode targetGraph) {
        HashMap<String, KGraphElement> targetMap = createHashmapByUniqueID(targetGraph);
        LinkedList<KGraphElement> elementQueue = new LinkedList<KGraphElement>();
        elementQueue.add(sourceGraph);
        do {
            // move the layout of the first graph element in the list
            KGraphElement sourceElement = elementQueue.removeFirst();
            KIdentifier kidentifier = sourceElement.getData(KIdentifier.class);
            if (kidentifier != null) {
                KGraphElement targetElement = targetMap.get(kidentifier.getId());
                if (targetElement != null) {
                    moveGraphElementLayout(sourceElement, targetElement);
                }
            }
            
            // find all child elements of the graph element
            for (EObject content : sourceElement.eContents()) {
                if (content instanceof KGraphElement) {
                    elementQueue.addLast((KGraphElement) content);
                }
            }
        } while (!elementQueue.isEmpty());
    }

    /**
     * Moves layout information from the source element to the target element.
     * Note that the source element may not be usable afterwards.
     * 
     * @param sourceElement
     *            the element which contains the layout information
     * @param targetElement
     *            the element which gets the layout information
     */
    private static void moveGraphElementLayout(final KGraphElement sourceElement,
            final KGraphElement targetElement) {
        KLayoutData sourceData = sourceElement.getData(KLayoutData.class);
        if (sourceData != null) {
            KLayoutData targetData = targetElement.getData(KLayoutData.class);
            if (targetData != null) {
                targetElement.getData().remove(targetData);
            }
            targetElement.getData().add(sourceData);
        }
    }

    /**
     * Creates a {@code HashMap} of all {@code KGraphElement} objects of the given graph indexed by
     * by the id of it's {@code KIdentifier} objects.
     * 
     * @param graph
     *            the graph model
     * @return HashMap a map containing the graph elements indexed by their unique identifier
     */
    private static HashMap<String, KGraphElement> createHashmapByUniqueID(final KNode graph) {
        HashMap<String, KGraphElement> map = new HashMap<String, KGraphElement>();
        TreeIterator<EObject> iterator = graph.eAllContents();
        while (iterator.hasNext()) {
            EObject eObject = iterator.next();
            if (eObject instanceof KGraphElement) {
                KGraphElement kgraphElement = (KGraphElement) eObject;
                KIdentifier kidentifier = (KIdentifier) kgraphElement.getData(KIdentifier.class);
                if (kidentifier != null) {
                    map.put(kidentifier.getId(), kgraphElement);
                }
            }
        }
        return map;
    }

    /**
     * Annotates each element of a KGraph instance with an unique id.
     * 
     * @param graph
     *            the graph to be annotated
     */
    public static void annotateGraphWithUniqueID(final KNode graph) {
        TreeIterator<EObject> identifier = graph.eAllContents();
        while (identifier.hasNext()) {
            EObject eObject = identifier.next();
            if (eObject instanceof KGraphElement) {
                KimlUtil.createIdentifier((KGraphElement) eObject);
            }
        }
    }

    /**
     * Determines the total number of elements in the given graph.
     * 
     * @param graph
     *            parent layout node to examine
     * @return total number of elements
     */
    public static int countElements(final KNode graph) {
        int count = 0;
        Iterable<KGraphElement> iterable = getAllElementsOfType(graph, KGraphElement.class, true);
        for (Iterator<KGraphElement> it = iterable.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        return count;
    }

    /**
     * Determines the total number of nodes in the given graph.
     * 
     * @param graph
     *            parent layout node to examine
     * @return total number of child nodes
     */
    public static int countNodes(final KNode graph) {
        int count = 0;
        Iterable<KNodeImpl> iterable = getAllElementsOfType(graph, KNodeImpl.class, false);
        for (Iterator<KNodeImpl> it = iterable.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        return count;
    }

    /**
     * Determines the total number of edges in the given graph.
     * 
     * @param graph
     *            parent layout node to examine
     * @return total number of edges
     */
    public static int countEdges(final KNode graph) {
        int count = 0;
        Iterable<KEdgeImpl> iterable = getAllElementsOfType(graph, KEdgeImpl.class, false);
        for (Iterator<KEdgeImpl> it = iterable.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        return count;
    }

    /**
     * Determines the total number of ports in the given graph.
     * 
     * @param graph
     *            parent layout node to examine
     * @return total number of ports
     */
    public static int countPorts(final KNode graph) {
        int count = 0;
        Iterable<KPortImpl> iterable = getAllElementsOfType(graph, KPortImpl.class, false);
        for (Iterator<KPortImpl> it = iterable.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        return count;
    }

    /**
     * Determines the total number of labels in the given graph.
     * 
     * @param graph
     *            parent layout node to examine
     * @return total number of labels
     */
    public static int countLabels(final KNode graph) {
        int count = 0;
        Iterable<KLabelImpl> iterable = getAllElementsOfType(graph, KLabelImpl.class, false);
        for (Iterator<KLabelImpl> it = iterable.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        return count;
    }

    /**
     * Returns an iterable over all the elements from a given graph which are of the specified type
     * or subclasses of it.
     * 
     * @param <T>
     *            the type of the elements
     * @param graph
     *            the graph of which the elements of the defined type shall be returned
     * @param type
     *            class defining the type of the elements which are to be returned
     * @return an iterable over all the elements from a given graph which are of the specified type
     *         or subclasses of it
     */
    public static <T> Iterable<T> getAllElementsOfType(final KNode graph, final Class<T> type) {
        return getAllElementsOfType(graph, type, true);
    }

    /**
     * Returns an iterable over all the elements from a given graph which are of the specified type
     * or subclasses of it.
     * 
     * @param <T>
     *            the type of the elements
     * @param graph
     *            the graph of which the elements of the defined type shall be returned
     * @param type
     *            class defining the type of the elements which are to be returned
     * @param maySubclass
     *            whether the returned list may contain subclass instances
     * @return an iterable over all the elements from a given graph which are of the specified type
     */
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> getAllElementsOfType(final KNode graph, final Class<T> type,
            final boolean maySubclass) {
        Iterable<EObject> allContents = new Iterable<EObject>() {
            public Iterator<EObject> iterator() {
                return graph.eAllContents();
            }
        };
        return (Iterable<T>) Iterables.filter(allContents, new Predicate<EObject>() {
            public boolean apply(final EObject input) {
                if (maySubclass) {
                    return type.isAssignableFrom(input.getClass());
                } else {
                    return input.getClass().equals(type);
                }
            }
        });
    }

    /**
     * Private Constructor. Utility class must not be instantiated.
     */
    private Graphs() {
    }

}
