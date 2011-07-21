/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.util;

import java.util.HashMap;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.logging.Logger;

/**
 * Utility class for duplicating layout information between structually identical
 * graphs. Every graph element gets annotated with an unique identifier.
 * This is used to duplicate the calculated layout information from a layouted
 * graph back to the original graph.
 *
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
public final class Graphs {

    /**
     * Duplicates layout information from source model to target model.
     * 
     * @param sourceGraph the model which contains the layout information
     * @param targetGraph the model which gets the layout information
     */
    public static void duplicateGraphLayoutByUniqueID(final KNode sourceGraph,
        final KNode targetGraph) {
        if (sourceGraph != null && targetGraph != null) {
            HashMap<String, KGraphElement> sourceMap
                = createHashmapByUniqueID(sourceGraph);
            HashMap<String, KGraphElement> targetMap 
                = createHashmapByUniqueID(targetGraph);
            KGraphElement kgeSource = null;
            KGraphElement kgeTarget = null;
            for (String srcId : sourceMap.keySet()) {
                if (targetMap.containsKey(srcId)) {
                    kgeSource = sourceMap.get(srcId);
                    kgeTarget = targetMap.get(srcId);
                    duplicateGraphElementLayout(kgeSource, kgeTarget);
                }
            }
        }
    }

    /**
     * Copies layout information from the sourceElement to the targetElement.
     * 
     * @param sourceElement
     *            the element which contains the layout information
     * @param targetElement 
     *            the element which gets the layout information
     */
    private static void duplicateGraphElementLayout(
        final KGraphElement sourceElement,
        final KGraphElement targetElement) {
        if (sourceElement != null && targetElement != null) {
            KShapeLayout sourceKSL = sourceElement.getData(KShapeLayout.class);
            KEdgeLayout sourceKEL = sourceElement.getData(KEdgeLayout.class);
            KShapeLayout targetKSL = targetElement.getData(KShapeLayout.class);
            KEdgeLayout targetKEL = targetElement.getData(KEdgeLayout.class);
            if (sourceKSL != null) {
                if (targetKSL != null) {
                    targetElement.getData().remove(targetKSL);
                }
                targetElement.getData().add(sourceKSL);
            }
            if (sourceKEL != null) {
                if (targetKEL != null) {
                    targetElement.getData().remove(targetKEL);
                }
                targetElement.getData().add(sourceKEL);
            }
        }
    }

    /**
     * Creates a {@code HashMap} of all {@code KGraphElement} objects of the given
     * graph indexed by by the id of it's {@code KIdentifier} objects.
     * 
     * @param graph 
     *            the graph model
     * @return HashMap
     *            a map containing the graph elements indexed by their unique identifier
     */
    private static HashMap<String, KGraphElement> createHashmapByUniqueID(final KNode graph) {
        HashMap<String, KGraphElement> map = new HashMap<String, KGraphElement>();
        if (graph != null) {
            TreeIterator<EObject> teo = graph.eAllContents();
            EObject eObject = null;
            KGraphElement kge = null;
            KIdentifier kid = null;
            while (teo.hasNext()) {
                eObject = teo.next();
                if (eObject instanceof KGraphElement) {
                    kge = ((KGraphElement) eObject);
                    kid = (KIdentifier) kge.getData(KIdentifier.class);
                    if (kid == null) {
                        Logger.log("Source element without identifier, ignoring element");
                    } else {
                        map.put(kid.getId(), kge);
                    }
                }
            }
        }
        return map;
    }

    /**
     * Annotates each element of a KGraph instance with an unique id.
     * 
     * @param graph the graph to be annotated
     */
    public static void annotateGraphWithUniqueID(final KNode graph) {
        if (graph != null) {
            TreeIterator<EObject> teo = graph.eAllContents();
            EObject eObject = null;
            while (teo.hasNext()) {
                eObject = teo.next();
                if (eObject instanceof KGraphElement) {
                    KimlUtil.createIdentifier((KGraphElement) eObject);
                }
            }
        }
    }

   /**
    * Determines the total number of nodes in the given graph.
    *
    * @param node 
    *            parent layout node to examine
    * @return total number of child nodes
    */
   public static int countNodes(final KNode node) {
       int count = node.getChildren().size();
       for (KNode child : node.getChildren()) {
           if (!child.getChildren().isEmpty()) {
               count += countNodes(child);
           }
       }
       return count;
   }

   /**
    * Private Constructor. Utility class must not
    * be instantiated.
    */
   private Graphs() {
   }

}
