/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.diagram;

import java.util.List;

import org.eclipse.core.runtime.IAdapterFactory;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;

/**
 * A layout mapping describes the relation between a graphical diagram and the layout graph
 * that is created by diagram layout managers.
 *
 * @param <T> the type of diagram parts to store in the mapping
 * @author msp
 */
public class LayoutMapping<T> extends MapPropertyHolder {
    
    /** the bidirectional mapping of layout graph elements to diagram parts. */
    private BiMap<KGraphElement, T> graphElemMap = HashBiMap.create();
    /** additional layout configurations for specification of layout options. */
    private List<ILayoutConfig> layoutConfigs = Lists.newLinkedList();
    /** the top-level parent node of the layout graph. */
    private KNode layoutGraph;
    /** the top-level diagram part. */
    private T parentElement;
    /** the adapter factory for accessing elements. */
    private IAdapterFactory adapterFactory;
    
    /**
     * Create a layout mapping with given adapter factory.
     * 
     * @param theadapterFactory the adapter factory for accessing elements
     */
    public LayoutMapping(final IAdapterFactory theadapterFactory) {
        this.adapterFactory = theadapterFactory;
    }
    
    /**
     * Returns the bidirectional mapping of layout graph elements to diagram parts.
     * 
     * @return the graph element map
     */
    public BiMap<KGraphElement, T> getGraphMap() {
        return graphElemMap;
    }
    
    /**
     * Set the top-level parent node of the layout graph.
     * 
     * @param thelayoutGraph the layout graph
     */
    public void setLayoutGraph(final KNode thelayoutGraph) {
        this.layoutGraph = thelayoutGraph;
    }
    
    /**
     * Returns the top-level parent node of the layout graph.
     * 
     * @return the layout graph
     */
    public KNode getLayoutGraph() {
        return layoutGraph;
    }
    
    /**
     * Returns the additional layout configurations for specification of layout options. The
     * returned list is initially empty.
     * 
     * @return the layout configurations
     */
    public List<ILayoutConfig> getLayoutConfigs() {
        return layoutConfigs;
    }
    
    /**
     * Set the top-level diagram part.
     * 
     * @param theparentElem the parent diagram part
     */
    public void setParentElement(final T theparentElem) {
        this.parentElement = theparentElem;
    }
    
    /**
     * Returns the top-level diagram part.
     * 
     * @return the parent diagram part
     */
    public T getParentElement() {
        return parentElement;
    }
    
    /**
     * Returns the adapter factory for accessing elements.
     * 
     * @return the adapter factory
     */
    public IAdapterFactory getAdapterFactory() {
        return adapterFactory;
    }

}
