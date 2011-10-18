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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Utility class for duplicating layout information between structually identical
 * graphs. Every graph element gets annotated with an unique identifier.
 * This is used to duplicate the calculated layout information from a 
 * graph which was layout done on back to the original graph.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
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
            HashMap<String, KGraphElement> sourceMap = createHashmapByUniqueID(sourceGraph);
            HashMap<String, KGraphElement> targetMap = createHashmapByUniqueID(targetGraph);
            for (String srcId : sourceMap.keySet()) {                
                duplicateGraphElementLayout(sourceMap.get(srcId), targetMap.get(srcId));
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
    private static void duplicateGraphElementLayout(final KGraphElement sourceElement,
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
            TreeIterator<EObject> iterator = graph.eAllContents();
            EObject eObject = null;
            KGraphElement kgraphElement = null;
            KIdentifier kidentifier = null;
            while (iterator.hasNext()) {
                eObject = iterator.next();
                if (eObject instanceof KGraphElement) {
                    kgraphElement = (KGraphElement) eObject;
                    kidentifier = (KIdentifier) kgraphElement.getData(KIdentifier.class);
                    if (kidentifier != null) {
                        map.put(kidentifier.getId(), kgraphElement);
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
            TreeIterator<EObject> identifier = graph.eAllContents();
            EObject eObject = null;
            while (identifier.hasNext()) {
                eObject = identifier.next();
                if (eObject instanceof KGraphElement) {
                    KimlUtil.createIdentifier((KGraphElement) eObject);
                }
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
        return getAllElementsOfType(graph, KGraphElement.class, true).size();       
    }

   /**
    * Determines the total number of nodes in the given graph.
    *
    * @param graph 
    *            parent layout node to examine
    * @return total number of child nodes
    */
   public static int countNodes(final KNode graph) {
       return getAllElementsOfType(graph, KNode.class, false).size();       
   }

   /**
    * Determines the total number of edges in the given graph.
    *
    * @param graph 
    *            parent layout node to examine
    * @return total number of edges
    */
   public static int countEdges(final KNode graph) {
       return getAllElementsOfType(graph, KEdge.class, false).size();       
   }

   /**
    * Determines the total number of ports in the given graph.
    *
    * @param graph 
    *            parent layout node to examine
    * @return total number of ports
    */
   public static int countPorts(final KNode graph) {
       return getAllElementsOfType(graph, KPort.class, false).size();       
   }

   /**
    * Determines the total number of labels in the given graph.
    *
    * @param graph 
    *            parent layout node to examine
    * @return total number of labels
    */
   public static int countLabels(final KNode graph) {
       return getAllElementsOfType(graph, KLabel.class, false).size();       
   }

   /**
    * Ensures that each element contained in a KGraph instance is attributed correctly for
    * usage in KIML.
    * 
    * @param graph
    *            the KGraph instance to validate the elements of 
    */
   public static void validateAllElements(final KNode graph) {
       if (graph == null) {
           throw new IllegalArgumentException("Graph instance is null");
       }
       KGraphFactory elementFactory = KGraphFactory.eINSTANCE;
       KLayoutDataFactory layoutFactory = KLayoutDataFactory.eINSTANCE;
       List<KGraphElement> elements = getAllElementsOfType(graph, KGraphElement.class);
       for (KGraphElement element : elements) {
           KShapeLayout sLayout = element.getData(KShapeLayout.class);
           KEdgeLayout eLayout = element.getData(KEdgeLayout.class);
           // Make sure nodes are OK
           if (element instanceof KNode) {                   
               if (sLayout == null) {
                   sLayout = layoutFactory.createKShapeLayout();                   
                   element.getData().add(sLayout);
               } 
               if (sLayout.getInsets() == null) {
                   sLayout.setInsets(layoutFactory.createKInsets());
               }
               KNode node = (KNode) element;
               if (node.getLabel() == null) {
                   KLabel label = elementFactory.createKLabel();
                   label.getData().add(layoutFactory.createKShapeLayout());
                   label.setText("");
                   node.setLabel(label);
               }        
           // Make sure ports are OK           
           } else if (element instanceof KPort) {                   
               if (sLayout == null) {
                   element.getData().add(layoutFactory.createKShapeLayout());
               }    
               KPort port = (KPort) element;
               if (port.getLabel() == null) {
                   KLabel label = elementFactory.createKLabel();
                   label.getData().add(layoutFactory.createKShapeLayout());
                   label.setText("");
                   port.setLabel(label);
               }                       
           // Make sure labels are OK
           } else if (element instanceof KLabel) {                   
               if (sLayout == null) {
                   element.getData().add(layoutFactory.createKShapeLayout());
               }
           // Make sure edges are OK
           } else if (element instanceof KEdge) {                   
               if (eLayout == null) {
                   eLayout = layoutFactory.createKEdgeLayout();
                   eLayout.setSourcePoint(layoutFactory.createKPoint());
                   eLayout.setTargetPoint(layoutFactory.createKPoint());
                   element.getData().add(eLayout);
               }   
           }
       }
   }
   
   /**
    * Returns a list containing all the elements from a given graph which are of the specified
    * type or sub classes of it.
    * 
    * @param <T>
    *            the type of the elements
    * @param graph
    *            the graph of which the elements of the defined type shall be returned
    * @param type
    *            class defining the type of the elements which are to be returned
    * @return a list containing all the elements from a given graph which are of the specified type
    *         or sub classes of it
    */
   public static <T> List<T> getAllElementsOfType(final KNode graph, final Class<T> type) {
       return getAllElementsOfType(graph, type, true);
   }
   
   /**
    * Returns a list containing all the elements from a given graph which are of the specified
    * type or sub classes of it.
    * 
    * @param <T>
    *            the type of the elements
    * @param graph
    *            the graph of which the elements of the defined type shall be returned
    * @param type
    *            class defining the type of the elements which are to be returned
    * @param maySubclass
    *            whether the returned list may contain subclass instances
    * @return a list containing all the elements from a given graph which are of the specified type
    */
   @SuppressWarnings("unchecked")
   public static <T> List<T> getAllElementsOfType(final KNode graph, final Class<T> type, 
       final boolean maySubclass) {
       if (graph == null) {
           throw new IllegalArgumentException("Graph can not be null");
       }
       if (type == null) {
           throw new IllegalArgumentException("Type can not be null");
       }
       List<T> result = new LinkedList<T>();
       TreeIterator<EObject> teo = graph.eAllContents();
       EObject eObject = null;
       while (teo.hasNext()) {
           eObject = teo.next();
           if (assignable(eObject, type, maySubclass)) {
               result.add((T) eObject);               
           }
       }
       if (assignable(graph, type, maySubclass)) {
           result.add((T) graph);
       }
       return result;
   }
   
   /**
    * Checks whether a given instance is an instance of a given class or sub class of it.
    * 
    * @param eObject
    *            the instance to be checked
    * @param type
    *            the class to test against
    * @param maySubclass
    *            whether the match must be equal or sub classing is allowed
    * @return whether a given instance is an instance of a given class or sub class of it
    */
   private static <T> boolean assignable(final EObject eObject, final Class<T> type, 
       final boolean maySubclass) {
       if (eObject != null && type != null) {
           if (maySubclass) {
               return type.isAssignableFrom(eObject.getClass());
           } else {
               return eObject.getClass().equals(type);
           }
       }
       return (eObject.equals(type));
   }
   
   /**
    * Private Constructor. Utility class must not
    * be instantiated.
    */
   private Graphs() {
   }

}
