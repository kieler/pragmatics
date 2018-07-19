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
 * Singleton class to map a graph id (String) found in SGraphs to their different parts needed for handling KGraph models
 * @author stu114054
 *
 */
@Singleton
class KGraphDiagramState {
    Map<String, ViewContext> kgraphContexts = new HashMap
    Map<String, ArrayList<Pair <KGraphElement, SModelElement>>> elementMapping = new HashMap
    Map<String, ArrayList<SKLabel>> labels = new HashMap
    Map<String, Map<String, KText>> textMapping = new HashMap
    
    def ViewContext getKGraphContext(String key) {
        kgraphContexts.get(key)
    }
    
    def putKGraphContext(String key, ViewContext value) {
        kgraphContexts.put(key, value)
    }
    
    def ArrayList<Pair <KGraphElement, SModelElement>> getElementMapping(String key) {
        elementMapping.get(key)
    }
    
    def putElementMapping(String key, ArrayList<Pair <KGraphElement, SModelElement>> value) {
        elementMapping.put(key, value)
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
    
}