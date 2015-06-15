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

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.ViewSynthesisShared
import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.core.properties.Property
import de.cau.cs.kieler.core.util.Pair

import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.util.KimlUtil
import java.util.ArrayList
import de.cau.cs.kieler.core.math.KVector

/**
 * Provides some helpful extension methods for simplifying the composition of KGraph/KRendering-based view models.<br>
 * <br>
 * In order to employ them beyond KLighD diagram syntheses you best declare a field of type
 * {@link KNodeExtensions} in your class and annotate it with {@link javax.inject.Inject Inject}.<br>
 * <br>
 * Make sure to bind the {@link ViewSynthesisShared} annotation in the employed
 * {@link com.google.inject.Injector Injector} to a {@link com.google.inject.Scope}, e.g. by calling
 * {@code Guice.createInjector(KRenderingExtensionsPlugin.createSingletonScopeBindingModule());} or 
 * {@code Guice.createInjector(KRenderingExtensionsPlugin.createNoScopeBindingModule());}.<br>
 * <br>
 * By means of that {@link com.google.inject.Injector Injector} you may get a new instance of your class,
 * or you may inject the above mentioned attribute within instances of your class, e.g. by calling
 * {@code injector.injectMembers(this)} in the constructor of your class.
 * 
 * @author chsch
 * 
 * @containsExtensions
 */
@ViewSynthesisShared
class KNodeExtensions {
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KNodeExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def private KNode create node: KimlUtil::createInitializedNode internalCreateNode(ArrayList<Object> oc) {
    }

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def KNode getNode(Object o) {
        newArrayList(o).internalCreateNode
    }
    
    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def KNode getNode(Object o1, Object o2) {
        newArrayList(o1, o2).internalCreateNode
    }
    
    /**
     * A convenience method to create a KNode without relating it to a business object. 
     */
    def KNode createNode() {
        return KimlUtil::createInitializedNode();
    }
    
    /**
     * An alias of {@link #getNode} allowing to express in business that the KNode will
     * be created at this place. It is just syntactic sugar.  
     */
    def KNode createNode(Object o) {
        return o.node
    }
    
    /**
     * An alias of {@link #getNode} allowing to express in business that the KNode will
     * be created at this place. It is just syntactic sugar.  
     */
    def KNode createNode(Object o1, Object o2) {
        return o1.getNode(o2)
    }
    
    def Pair<Float, Float> getNodeSize(KNode node) {
        return new Pair<Float, Float> => [
            val layout = node.getData(typeof(KShapeLayout))
            it.first = layout.height
            it.second = layout.height
        ];
    }

    def float getHeight(KNode node) {
        node.getData(typeof(KShapeLayout)).height;
    }
    
    /**
     * Is used in KPortExtensions
     */    
    def float getWidth(KNode node) {
        node.getData(typeof(KShapeLayout)).width;
    }
    
    def KNode setNodeSize(KNode node, float width, float height) {
        return node => [
            getData(typeof(KShapeLayout)).setSize(width, height);
            setMinimalNodeSize(width, height);
        ];
    }
    
    def KNode setWidth(KNode node, float width) {
        val height = node.getData(typeof(KShapeLayout)).height;
        node.setNodeSize(width, height)
        return node
    }
    
    def KNode setHeight(KNode node, float height) {
        val width = node.getData(typeof(KShapeLayout)).width;
        node.setNodeSize(width, height)
        return node
    }
    
    private static val IProperty<KVector> MINIMAL_NODE_SIZE = new Property<KVector>(
            "de.cau.cs.kieler.klighd.minimalNodeSize", new KVector(10d, 10d));

    def KNode setMinimalNodeSize(KNode node, float width, float height) {
        return node.addLayoutParam(MINIMAL_NODE_SIZE, new KVector(width, height));
    }
    
    def <T> KNode addLayoutParam(KNode node, IProperty<? super T> property, T value) {
        node?.getData(typeof(KShapeLayout))?.setProperty(property, value)
        return node
    }
    
}