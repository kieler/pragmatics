/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
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
import org.eclipse.elk.core.util.Pair

/**
 * Singleton class to map a graph id (String) found in SGraphs to their various parts needed for handling KGraph models
 * @author stu114054
 *
 */
@Singleton class KGraphDiagramState {
    Map<String, ViewContext> kGraphContexts = new HashMap
    Map<String, ArrayList<Pair <KGraphElement, SModelElement>>> elementMappingList = new HashMap
    Map<String, ArrayList<SKLabel>> labels = new HashMap
    Map<String, Map<String, KText>> textMapping = new HashMap
    Map<String, String> uriStringMap = new HashMap
    
    def ViewContext getKGraphContext(String key) {
        kGraphContexts.get(key)
    }
    
    def putKGraphContext(String key, ViewContext value) {
        kGraphContexts.put(key, value)
    }
    
    def ArrayList<Pair <KGraphElement, SModelElement>> getElementMappingList(String key) {
        elementMappingList.get(key)
    }
    
    def putElementMappingList(String key, ArrayList<Pair <KGraphElement, SModelElement>> value) {
        elementMappingList.put(key, value)
    }
    
    def ArrayList<SKLabel> getTexts(String key) {
        labels.get(key)
    }
    
    def putTexts(String key, ArrayList<SKLabel> value) {
        labels.put(key, value)
    }
    
    def Map<String, KText> getTextMapping(String key) {
        textMapping.get(key)
    }
    
    def putTextMapping(String key, Map<String, KText> value) {
        textMapping.put(key, value)
    }
    
    /**
     * removes this key from all stored maps. Should be called when the diagram view is closed.
     */
    def remove(String clientId) {
        val key = uriStringMap.get(clientId)
        if (key !== null) {
            kGraphContexts.remove(key)
            elementMappingList.remove(key)
            labels.remove(key)
            textMapping.remove(key)
            uriStringMap.remove(clientId)
        }
    }
    
    def putURIString(String clientId, String uri) {
        uriStringMap.put(clientId, uri)
    }
    
}