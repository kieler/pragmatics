/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering.extensions

import java.util.ArrayList
import javax.inject.Inject

import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.properties.IProperty

import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.core.krendering.KSpline
import de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline
import java.util.HashMap
import java.lang.reflect.Field
import java.util.List

/**
 * @author chsch, alb, ssm
 * 
 * @containsExtensions
 */
@ViewSynthesisShared
class KEdgeExtensions {

    private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
        
    @Inject
    extension KRenderingExtensions;
    
        
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KEdgeExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def private KEdge create port: KimlUtil::createInitializedEdge internalCreateEdge(ArrayList<Object> oc) {
    }

    /**
     * The following method retrieves the internal map of the create extension. 
     * This is mandatory for queries (e.g. exist tests) on the private and hence inaccessible hash map.
     */ 
    def private HashMap<ArrayList<? extends Object>,KEdge> getInternalEdgeMap() {
        val Field internalMapField = KEdgeExtensions.getDeclaredField("_createCache_internalCreateEdge") 
        internalMapField.setAccessible(true)
        internalMapField.get(this) as HashMap<ArrayList<? extends Object>,KEdge>
    }
    
    /**
     * A convenient test method to check whether or not a specific edge exists in the create extension
     */
    def boolean edgeExists(Object o1) {
        getInternalEdgeMap().containsKey(CollectionLiterals.newArrayList(newArrayList(o1)))
    }

    /**
     * A convenient test method to check whether or not a specific edge exists in the create extension
     */
    def boolean edgeExists(Object o1, Object o2) {
        getInternalEdgeMap().containsKey(CollectionLiterals.newArrayList(newArrayList(o1, o2)))
    }

    /**
     * A convenient test method to check whether or not a specific edge exists in the create extension
     */
    def boolean edgeExists(Object o1, Object o2, Object o3) {
        getInternalEdgeMap().containsKey(CollectionLiterals.newArrayList(newArrayList(o1, o2, o3)))
    }

    /**
     * A convenient test method to check whether or not a specific edge exists in the create extension
     */
    def boolean edgeExists(Object o1, Object o2, Object o3, Object o4) {
        getInternalEdgeMap().containsKey(CollectionLiterals.newArrayList(newArrayList(o1, o2, o3, o4)))
    }

    /**
     * A convenient port getter based on a single business object preserving the
     * element image relation by a create extension.
     */
    def KEdge getEdge(Object o1) {
        return newArrayList(o1).internalCreateEdge()
    }
    
    /**
     * A convenient port getter based on a two business objects preserving the
     * element image relation by a create extension.
     */
    def KEdge getEdge(Object o1, Object o2) {
        return newArrayList(o1, o2).internalCreateEdge()
    }
    
    /**
     * A convenient port getter based on a three business objects preserving the
     * element image relation by a create extension.
     */
    def KEdge getEdge(Object o1, Object o2, Object o3) {
        return newArrayList(o1, o2, o3).internalCreateEdge()
    }
    
    /**
     * A convenient port getter based on a four business objects preserving the
     * element image relation by a create extension.
     */
    def KEdge getEdge(Object o1, Object o2, Object o3, Object o4) {
        return newArrayList(o1, o2, o3, o4).internalCreateEdge()
    }
    
    /**
     * A convenience method to create a KEdge without relating it to a business object. 
     */
    def KEdge createEdge() {
        return KimlUtil::createInitializedEdge()
    }
    
    /**
     * An alias of {@link #getEdge(Object o1)} allowing to express in business that the KEdge will
     * be created at this place. It is just syntactic sugar.  
     */
    def KEdge createEdge(Object o1) {
        return o1.getEdge()
    }
    
    /**
     * An alias of {@link #getEdge(Object o1, Object o2)} allowing to express in business that the
     * KEdge will be created at this place. It is just syntactic sugar.  
     */
    def KEdge createEdge(Object o1, Object o2) {
        return o1.getEdge(o2);
    }
    
    /**
     * An alias of {@link #getEdge(Object o1, Object o2, Object o3)} allowing to express in business
     * that the KEdge will be created at this place. It is just syntactic sugar.  
     */
    def KEdge createEdge(Object o1, Object o2, Object o3) {
        return o1.getEdge(o2, o3);
    }
    
    /**
     * An alias of {@link #getEdge(Object o1, Object o2, Object o3, Object o4)} allowing to express in
     * business that the KEdge will be created at this place. It is just syntactic sugar.  
     */
    def KEdge createEdge(Object o1, Object o2, Object o3, Object o4) {
        return o1.getEdge(o2, o3, o4);
    }
    
    /**
     * Similar to createEdge, createNewEdge creates a new edge related to an object. Furthermore, the 
     * method makes sure a new edge is created and stored in the create extensions.
     */
    def KEdge createNewEdge(Object o1) {
        var int counter = 0
        while (o1.edgeExists(counter)) counter = counter + 1 
        return o1.createEdge(counter)
    }

    /**
     * Similar to createEdge, createNewEdge creates a new edge related to an object. Furthermore, the 
     * method makes sure a new edge is created and stored in the create extensions.
     */
    def KEdge createNewEdge(Object o1, Object o2) {
        var int counter = 0
        while (o1.edgeExists(o2, counter)) counter = counter + 1 
        return o1.createEdge(o2, counter)
    }

    /**
     * Similar to createEdge, createNewEdge creates a new edge related to an object. Furthermore, the 
     * method makes sure a new edge is created and stored in the create extensions.
     */
    def KEdge createNewEdge(Object o1, Object o2, Object o3) {
        var int counter = 0
        while (o1.edgeExists(o2, o3, counter)) counter = counter + 1 
        return o1.createEdge(o2, o3, counter)
    }
    
    /**
     * The method getAllEdges retrieves all edges linked to a specific (semantic) object.
     */
    def List<KEdge> getAllEdges(Object o1) {
        var int counter = 0
        val edges = <KEdge> newArrayList
        while (o1.edgeExists(counter)) { 
            edges.add(o1.getEdge(counter)); 
            counter = counter + 1
        }
        edges
    }

    /**
     * The method getAllEdges retrieves all edges linked to a specific (semantic) object.
     */
    def List<KEdge> getAllEdges(Object o1, Object o2) {
        var int counter = 0
        val edges = <KEdge> newArrayList
        while (o1.edgeExists(o2, counter)) { 
            edges.add(o1.getEdge(o2, counter)); 
            counter = counter + 1
        }
        edges
    }

    /**
     * The method getAllEdges retrieves all edges linked to a specific (semantic) object.
     */
    def List<KEdge> getAllEdges(Object o1, Object o2, Object o3) {
        var int counter = 0
        val edges = <KEdge> newArrayList
        while (o1.edgeExists(o2, o3, counter)) { 
            edges.add(o1.getEdge(o2, o3, counter)); 
            counter = counter + 1
        }
        edges
    }
    
    /**
     * getSemanticObject returns the primary (semantic) object of an edge.
     */
    def Object getSemanticObject(KEdge edge) {
        (getInternalEdgeMap.filter[p1, p2|p2==edge].keySet.head.head as ArrayList<Object>).head
    }
    
    
    def KEdge addLayoutParam(KEdge edge, IProperty<?> property, Object value) {
        edge => [
            it.getData(typeof(KEdgeLayout)).setProperty(property, value)
        ];
    }
    
    def KPolyline addPolyline(KEdge e) {
        return renderingFactory.createKPolyline() => [
            e.data += it;
        ];
    }
    
    def KPolyline addPolyline(KEdge e, float lineWidth) {
        return e.addPolyline.lineWidth = lineWidth;
    }
    
    def KRoundedBendsPolyline addRoundedBendsPolyline(KEdge e, float bendRadius) {
        return renderingFactory.createKRoundedBendsPolyline() => [
            e.data += it;
            it.bendRadius = bendRadius;
        ];
    }

    def KRoundedBendsPolyline addRoundedBendsPolyline(KEdge e, float bendRadius, float lineWidth) {
        return e.addRoundedBendsPolyline(bendRadius).lineWidth = lineWidth;
    }
    
    def KSpline addSpline(KEdge e) {
        return renderingFactory.createKSpline => [
            e.data += it;
        ];
    }
    
    def KSpline addSpline(KEdge e, float lineWidth) {
        return e.addSpline.lineWidth = lineWidth;
    }
    
}