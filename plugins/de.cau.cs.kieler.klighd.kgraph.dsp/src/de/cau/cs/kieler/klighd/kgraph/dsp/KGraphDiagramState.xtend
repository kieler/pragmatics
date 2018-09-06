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

import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.krendering.KText
import io.typefox.sprotty.api.SModelElement
import java.util.ArrayList
import java.util.HashMap
import java.util.Map
import javax.inject.Singleton

/**
 * Singleton class to map a graph id (String) found in SGraphs to their various parts needed for handling KGraph models
 * @author nir
 *
 */
@Singleton 
public class KGraphDiagramState {
    
    /**
     * A map mapping the id of a graph to the {@link ViewContext} containing that graph.
     */
    private Map<String, ViewContext> kGraphContexts = new HashMap
    
    /**
     * A map that contains a key-value pair for each KGraphElement and its translated SModelElement counterpart.
     * Convenient for finding a specific key KGraphElement faster.
     * Mapped by the url this map belongs to.
     */
    private Map<String, Map<KGraphElement, SModelElement>> kGraphToSModelElementMap = new HashMap
    
    /**
     * A list containing all texts from the source KGraph in Sprotty labels.
     * Mapped by the url this map belongs to.
     */
    private Map<String, ArrayList<SKLabel>> labels = new HashMap
    
    /**
     * A map containing all KTexts from the source KGraph under the key of their id.
     * Mapped by the url this map belongs to.
     */
    private Map<String, Map<String, KText>> textMapping = new HashMap
    
    /**
     * A map to map the sprotty client id to the uri leading to the resource.
     */
    private Map<String, String> uriStringMap = new HashMap
    
    public def ViewContext getKGraphContext(String key) {
        kGraphContexts.get(key)
    }
    
    public def putKGraphContext(String key, ViewContext value) {
        kGraphContexts.put(key, value)
    }
    
    public def Map<KGraphElement, SModelElement> getKGraphToSModelElementMap(String key) {
        kGraphToSModelElementMap.get(key)
    }
    
    public def putKGraphToSModelElementMap(String key, Map<KGraphElement, SModelElement> value) {
        kGraphToSModelElementMap.put(key, value)
    }
    
    public def ArrayList<SKLabel> getTexts(String key) {
        labels.get(key)
    }
    
    public def putTexts(String key, ArrayList<SKLabel> value) {
        labels.put(key, value)
    }
    
    public def Map<String, KText> getTextMapping(String key) {
        textMapping.get(key)
    }
    
    public def putTextMapping(String key, Map<String, KText> value) {
        textMapping.put(key, value)
    }
    
    /**
     * removes this key from all stored maps. Should be called when the diagram view is closed.
     */
    public def remove(String clientId) {
        val key = uriStringMap.get(clientId)
        if (key !== null) {
            kGraphContexts.remove(key)
            kGraphToSModelElementMap.remove(key)
            labels.remove(key)
            textMapping.remove(key)
            uriStringMap.remove(clientId)
        }
    }
    
    public def putURIString(String clientId, String uri) {
        uriStringMap.put(clientId, uri)
    }
}